<template>
  <!-- ============ 大屏容器（1920×1080 等比缩放） ============ -->
  <div id="screen" class="screen-1920">
    <!-- 顶部标题栏 -->
    <header class="screen-header">
      <div class="brand">
        <span class="logo-dot"></span>
        <span class="brand-text">冷应激指数</span>
      </div>
      <a-date-picker
          class="dark-picker"
          v-model:value="value1"
          format="YYYY-MM-DD HH:mm:ss"
          :show-time="{ hideDisabledOptions: true, defaultValue: dayjs('08:00:00','HH:mm:ss') }"
          placeholder="选择时间"
          :disabled-time="disabledDateTime"
          @change="onChangeOne"
        />
        <!-- 实时时钟（新增） -->
        <div class="clock">{{ nowStr }}</div>


      <div class="header-actions">
        <!-- 站点切换（保留原逻辑） -->
        <div class="stations">
          <button
            v-for="(s, idx) in STATIONS"
            :key="s.name"
            class="station-btn neon"
            :class="{ active: activeIdx === idx }"
            @click="switchStation(idx)"
          >
            {{ s.name }}
          </button>
        </div>

        <!-- 导出按钮：室外/室内 -->
        <button
          class="btn-ghost"
          :disabled="loading || !seriesCache[activeIdx]"
          @click="exportCSV('outdoor')"
        >导出室外</button>
        <button
          class="btn-ghost"
          :disabled="loading || !seriesCache[activeIdx]"
          @click="exportCSV('indoor')"
        >导出室内</button>

      </div>
    </header>

    <!-- 主体：上图下卡的双区结构（可按需扩展为三栏） -->
    <main class="screen-main">
      <!-- 上：主图表卡片（室外） -->
      <section class="card chart-card">
        <div class="card-title">室外环境（冷应激趋势）</div>
        <div id="chart-outdoor" class="chart"></div>
        <div v-if="error" class="error">{{ error }}</div>
        <div v-if="loading" class="loading">正在加载数据…</div>
      </section>

      <!-- 下：复制的室内图表 -->
      <section class="card chart-card">
        <div class="card-title">室内环境（冷应激趋势）</div>
        <div id="chart-indoor" class="chart"></div>
        <div v-if="error" class="error">{{ error }}</div>
        <div v-if="loading" class="loading">正在加载数据…</div>
      </section>
    </main>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, onBeforeUnmount, nextTick } from 'vue'
import axios from 'axios'
import * as echarts from 'echarts'
import dayjs from 'dayjs'

/** ========= 原配置（保留） ========= */
const DATA_URL = 'data/tem/20230430000000.json'
const VALUE_IS_KELVIN = true
const RH_DEFAULT = 50
const STEP_HOURS = 3
const MAX_POINTS = 24

/** ========= 仅样式/结构相关：大屏自适应缩放 ========= */
const base = { w: 1920, h: 1080 }
function fit() { /* 布局已用视口适配，这里留空避免报错 */ }

/** ========= 时钟（新增，仅显示用） ========= */
const nowStr = ref(dayjs().format('YYYY-MM-DD HH:mm:ss'))
let _clk = null
function tick() { nowStr.value = dayjs().format('YYYY-MM-DD HH:mm:ss') }

/** ========= 时间选择（保留原逻辑） ========= */
const disabledDateTime = () => {
  const all = (n) => Array.from({ length: n }, (_, i) => i)
  return {
    disabledHours: () => all(24).filter(h => h !== 8 && h !== 20),
    disabledMinutes: () => all(60),
    disabledSeconds: () => all(60),
  }
}
function roundToHour(d) { const r = new Date(d); r.setMinutes(0,0,0); return r }
function toLocalDatetimeValue(d) {
  const y = d.getFullYear(), m = String(d.getMonth()+1).padStart(2,'0'), dd = String(d.getDate()).padStart(2,'0')
  const hh = String(d.getHours()).padStart(2,'0'), mm = String(d.getMinutes()).padStart(2,'0')
  return `${y}-${m}-${dd}T${hh}:${mm}`
}
function parseLocalDatetimeValue(s) { return new Date(s.replace('T',' ') + ':00') }
function todayAt(hour) { const d = new Date(); d.setHours(hour,0,0,0); return d }

const startISO = ref(toLocalDatetimeValue(todayAt(8)))
const value1 = ref(dayjs(`${startISO.value.replace('T',' ')}:00`, 'YYYY-MM-DD HH:mm:ss'))
async function applyStart() {
  if (!grid.value) return
  const dt = parseLocalDatetimeValue(startISO.value)
  // 重算全部站点的数据序列
  seriesCache.length = 0
  for (const s of STATIONS) {
    seriesCache.push(extractSeries(grid.value, s.lon, s.lat, dt))
  }
  // 重绘当前站点
  renderActive()
}

function onChangeOne(dj) {
  if (!dj) return
  const hour = dj.hour()
  const fixedHour = (hour === 8 || hour === 20) ? hour : (hour < 14 ? 8 : 20)
  const fixed = dj.hour(fixedHour).minute(0).second(0).millisecond(0)

  value1.value = fixed
  startISO.value = fixed.format('YYYY-MM-DD[T]HH:mm')
  applyStart()
}
function resetToNow() {
  const d8 = todayAt(8)
  startISO.value = toLocalDatetimeValue(d8)
  value1.value = dayjs(startISO.value.replace('T',' '), 'YYYY-MM-DD HH:mm')
  applyStart()
}


/** ========= 站点与计算（保留原逻辑） ========= */
const STATIONS = reactive([
  { name: '吉兴燊家庭牧场奶牛养殖', lon: 106.195, lat: 37.690 },
  { name: '罗山自然保护区',       lon: 106.13,  lat: 37.18  },
  { name: '同心县欢欢种植家庭农场', lon: 106.52,  lat: 37.690 }
])
const clamp = (v, lo, hi) => Math.max(lo, Math.min(hi, v))
const k2c = (k) => (k - 273.15)
const calcTHI = (Tc, RH) => 0.8 * Tc + (RH / 100) * (Tc - 14.4) + 46.4
const gradeTHI = (v) => (v < 68 ? '安全' : v <= 79 ? '轻度' : v <= 88 ? '中度' : '重度')
const needEarly = (v) => v >= 72

function parseDSGrid(json) {
  const { startLon, startLat, lonStep, latStep, DS } = json
  if (!Array.isArray(DS) || !Array.isArray(DS[0]) || !Array.isArray(DS[0][0])) {
    throw new Error('DS 不是 [lat][lon][time] 三维数组')
  }
  const ny = DS.length, nx = DS[0].length, nt = DS[0][0].length
  return { startLon, startLat, lonStep, latStep, nx, ny, nt, DS }
}
function llToIdx(grid, lon, lat) {
  const j = Math.round((lon - grid.startLon) / grid.lonStep)
  const i = Math.round((lat - grid.startLat) / grid.latStep)
  return { ilon: clamp(j, 0, grid.nx - 1), ilat: clamp(i, 0, grid.ny - 1) }
}
function extractSeries(grid, lon, lat, startDate) {
  const { ilon, ilat } = llToIdx(grid, lon, lat)
  const tempC = []
  for (let t = 0; t < grid.nt; t++) {
    let v = grid.DS[ilat][ilon][t]
    if (VALUE_IS_KELVIN) v = k2c(v)
    tempC.push(Number(v.toFixed(2)))
  }
  const start = startDate ? new Date(startDate) : roundToHour(new Date())
  const x = [], yTemp = [], yTHI = [], yRH = [], levels = [], early = []
  for (let k = 0; k < Math.min(MAX_POINTS, grid.nt); k++) {
    const dt = new Date(start.getTime() + k * STEP_HOURS * 3600 * 1000)
    const mm = String(dt.getMonth()+1).padStart(2,'0'), dd = String(dt.getDate()).padStart(2,'0'), hh = String(dt.getHours()).padStart(2,'0')
    x.push(`${mm}-${dd} ${hh}:00`)
    const T  = tempC[k], RH = RH_DEFAULT
    const thi = Number(calcTHI(T, RH).toFixed(1))
    yTemp.push(T); yTHI.push(thi); yRH.push(RH)
    levels.push(gradeTHI(thi)); early.push(needEarly(thi))
  }
  return { x, yTemp, yTHI, yRH, levels, early, ilon, ilat }
}

/** ========= 状态（保留） ========= */
const loading = ref(false)
const error = ref('')
const grid = ref(null)
const seriesCache = reactive([])
const activeIdx = ref(0)
let chartOutdoor = null
let chartIndoor = null

/** ========= 数据加载（保留） ========= */
async function loadData() {
  loading.value = true
  error.value = ''
  try {
    const { data } = await axios.get(DATA_URL, { responseType: 'json' })
    const g = parseDSGrid(data)
    grid.value = g
    seriesCache.length = 0
    const dt = parseLocalDatetimeValue(startISO.value)
    for (const s of STATIONS) seriesCache.push(extractSeries(g, s.lon, s.lat, dt))
  } catch (e) {
    console.error(e); error.value = String(e?.message || e)
  } finally {
    loading.value = false
  }
}

function ensureOutdoorChart() {
  const el = document.getElementById('chart-outdoor')
  if (!chartOutdoor && el) chartOutdoor = echarts.init(el)
  return chartOutdoor
}
function ensureIndoorChart() {
  const el = document.getElementById('chart-indoor')
  if (!chartIndoor && el) chartIndoor = echarts.init(el)
  return chartIndoor
}

/** ========= 图表渲染（深色大屏主题化） ========= */
function setOptionForChart(c, s) {
  if (!c || !s) return
  c.setOption({
    backgroundColor: 'transparent',
    animationDuration: 700,
    animationEasing: 'cubicOut',
    color: ['#2BD9FE', '#64B5F6', '#00E5FF'],
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'line', lineStyle: { color: 'rgba(43,217,254,.85)', width: 1 } },
      backgroundColor: 'rgba(10, 18, 32, .92)',
      borderColor: 'rgba(43, 217, 254, .35)',
      borderWidth: 1,
      textStyle: { color: 'rgba(255,255,255,.9)', fontSize: 12 },
      extraCssText: 'backdrop-filter: blur(6px); border-radius: 10px; padding:8px 10px;'
    },
    legend: { top: 8, textStyle: { color: 'rgba(255,255,255,.75)', fontWeight: 500 } },
    grid: { left: 56, right: 56, top: 52, bottom: 58 },
    xAxis: {
      type: 'category',
      data: s.x,
      axisLabel: { rotate: 40, color: 'rgba(255,255,255,.70)' },
      axisLine: { lineStyle: { color: 'rgba(255,255,255,.15)' } },
      axisTick: { show: false }
    },
    // 固定 THI 线颜色为绿色，不随阈值变化
    yAxis: [
      {
        type: 'value',
        name: 'THI / 湿度(%)',
        min: 0, max: 100,
        nameTextStyle: { color: 'rgba(255,255,255,.60)' },
        axisLabel: { color: 'rgba(255,255,255,.72)' },
        axisLine: { show: false },
        splitLine: { lineStyle: { color: 'rgba(255,255,255,.10)', type: 'dashed' } }
      },
      {
        type: 'value',
        name: '温度 (°C)',
        position: 'right',
        min: (val) => Math.floor(Math.min(val.min, 0)),
        max: (val) => Math.ceil(Math.max(val.max, 40)),
        nameTextStyle: { color: 'rgba(255,255,255,.60)' },
        axisLabel: { color: 'rgba(255,255,255,.72)' },
        axisLine: { show: false },
        splitLine: { show: false }
      }
    ],
    series: [
      {
        name: 'THI',
        type: 'line',
        yAxisIndex: 0,
        smooth: true,
        symbol: 'circle',
        symbolSize: 6,
        color: '#00C853',
        itemStyle: { color: '#00C853' },
        lineStyle: { width: 2.5, color: '#00C853', shadowBlur: 12, shadowColor: 'rgba(0,200,83,.35)' },
        data: s.yTHI,
        emphasis: { focus: 'series' },
        markArea: {
          silent: true,
          itemStyle: { opacity: 0.10 },
          data: [
            [{ yAxis: 0 },   { yAxis: 68,  itemStyle: { color: '#00C853' } }],
            [{ yAxis: 68 },  { yAxis: 79,  itemStyle: { color: '#FFD54F' } }],
            [{ yAxis: 79 },  { yAxis: 88,  itemStyle: { color: '#FF8F00' } }],
            [{ yAxis: 88 },  { yAxis: 999, itemStyle: { color: '#FF5252' } }]
          ]
        }
      },
      {
        name: '温度',
        type: 'line',
        yAxisIndex: 1,
        smooth: true,
        symbol: 'circle',
        symbolSize: 6,
        lineStyle: { width: 2.5, color: '#2BD9FE', shadowBlur: 10, shadowColor: 'rgba(43,217,254,.35)' },
        areaStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: 'rgba(43,217,254,.25)' },
            { offset: 1, color: 'rgba(43,217,254,.05)' }
          ])
        },
        data: s.yTemp,
        emphasis: { focus: 'series' }
      },
      {
        name: '湿度',
        type: 'line',
        yAxisIndex: 0,
        smooth: false,
        symbol: 'none',
        lineStyle: { type: 'dashed', width: 1.8, color: '#00E5FF' },
        data: s.yRH,
        emphasis: { focus: 'series' }
      }
    ]
  })
}

function deriveIndoorFromOutdoor(s) {
  if (!s) return s
  const x = s.x.slice()
  const yTemp = []
  const yRH = []
  const yTHI = []
  const levels = []
  const early = []
  let prev = null
  for (let i = 0; i < s.yTemp.length; i++) {
    let t = (s.yTemp[i] ?? 0) - 1.5
    if (prev != null) t = 0.7 * t + 0.3 * prev
    prev = t
    t = Number(t.toFixed(2))
    const rh = clamp(((s.yRH[i] ?? RH_DEFAULT) + 5), 30, 95)
    const thi = Number(calcTHI(t, rh).toFixed(1))
    yTemp.push(t)
    yRH.push(rh)
    yTHI.push(thi)
    levels.push(gradeTHI(thi))
    early.push(needEarly(thi))
  }
  return { x, yTemp, yTHI, yRH, levels, early, ilon: s.ilon, ilat: s.ilat }
}

function renderActive() {
  const s = seriesCache[activeIdx.value]
  if (!s) return
  const co = ensureOutdoorChart()
  const ci = ensureIndoorChart()
  const sIndoor = deriveIndoorFromOutdoor(s)
  setOptionForChart(co, s)
  setOptionForChart(ci, sIndoor)
  co && co.resize()
  ci && ci.resize()
}

function switchStation(idx) { activeIdx.value = idx; renderActive() }
function onResize() {
  chartOutdoor && chartOutdoor.resize()
  chartIndoor && chartIndoor.resize()
  fit()
}

/** ========= CSV 导出（保留原逻辑） ========= */
function build2D(series) {
  const s = series
  if (!s) return []
  const table = [['时间', 'THI', '温度(°C)', '湿度(%)']]
  for (let i = 0; i < s.x.length; i++) {
    table.push([String(s.x[i] ?? ''), String(s.yTHI[i] ?? ''), String(s.yTemp[i] ?? ''), String(s.yRH[i] ?? '')])
  }
  return table
}
function csvEscape(cell) {
  const s = String(cell == null ? '' : cell)
  if (/[",\r\n]/.test(s)) return `"${s.replace(/"/g, '""')}"`
  return s
}
function sanitizeName(name) { return String(name || '').replace(/[\\/:*?"<>|]/g, '').trim() }
function exportCSV(kind = 'outdoor') {
  const sOut = seriesCache[activeIdx.value]
  const s = kind === 'indoor' ? deriveIndoorFromOutdoor(sOut) : sOut
  const data2D = build2D(s)
  if (!data2D.length) return
  const csv = data2D.map(row => row.map(csvEscape).join(',')).join('\r\n')
  const blob = new Blob([new Uint8Array([0xEF, 0xBB, 0xBF]), csv], { type: 'text/csv;charset=utf-8;' })
  const url = URL.createObjectURL(blob)
  const station = sanitizeName(STATIONS[activeIdx.value]?.name || '站点')
  const now = new Date()
  const y = now.getFullYear(), m = String(now.getMonth()+1).padStart(2,'0'), d = String(now.getDate()).padStart(2,'0')
  const hh = String(now.getHours()).padStart(2,'0'), mm = String(now.getMinutes()).padStart(2,'0')
  const label = kind === 'indoor' ? '室内' : '室外'
  const filename = `${station}_${label}_THI导出_${y}${m}${d}_${hh}${mm}.csv`
  const a = document.createElement('a'); a.href = url; a.download = filename; document.body.appendChild(a); a.click()
  document.body.removeChild(a); URL.revokeObjectURL(url)
}

/** ========= 生命周期 ========= */
onMounted(async () => {
  await loadData()
  await nextTick()
  await applyStart()

  fit()
  window.addEventListener('resize', onResize)
  _clk = setInterval(tick, 1000)
  tick()
})
onBeforeUnmount(() => {
  window.removeEventListener('resize', onResize)
  if (chartOutdoor) { chartOutdoor.dispose(); chartOutdoor = null }
  if (chartIndoor) { chartIndoor.dispose(); chartIndoor = null }
  if (_clk) { clearInterval(_clk); _clk = null }
})
</script>

<style scoped>
/* ======= 大屏主题色 ======= */
:root{
  --bg:#0b2a45;             /* 深冷背景基调 */
  --panel:#0f1b2b;          /* 卡片底色 */
  --line:rgba(255,255,255,.10);
  --txt:rgba(255,255,255,.88);
  --txt-2:rgba(255,255,255,.66);
  --pri:#2BD9FE;            /* 冷色主色（冰蓝） */
  --acc:#00E5FF;            /* 点缀青蓝 */
  --warn:#FFD54F;           /* 告警黄 */
  --err:#FF5252;            /* 危险红 */
}

/* 1920×1080 画布，等比缩放 */
.screen-1920 {
  width: 98vw;         /* 视口宽度 */
  height: 98vh;        /* 视口高度 */
  transform: none;      /* 取消缩放 */
  margin: 0;
  padding: 0;
  border-radius: 0;     /* 如果不需要圆角 */
  box-sizing: border-box;
}


/* 背景：辐射渐变 + 轻噪点感 */
:host, .screen-1920{
  background:
    radial-gradient(1200px 700px at 15% -10%, #0f3a66, transparent 60%),
    radial-gradient(900px 600px at 85% 110%, #0b2a45, transparent 60%),
    linear-gradient(180deg, #0b1a2a 0%, #0b1a2a 100%);
  color: var(--txt);
  font-family: ui-sans-serif, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "PingFang SC", "Microsoft YaHei", "Noto Sans SC";
}

/* 顶部标题栏 */
.screen-header{
  height: 84px;
  display:flex; align-items:center; justify-content:space-between;
  padding: 0 20px;
  border-bottom: 1px solid var(--line);
  background:
    linear-gradient(90deg, rgba(43,217,254,.16), transparent 40%),
    linear-gradient(180deg, rgba(255,255,255,.02), rgba(255,255,255,0));
}
.brand{ display:flex; align-items:center; gap:10px; font-size:28px; font-weight:700; letter-spacing:.5px; }
.logo-dot{
  width:12px; height:12px; border-radius:50%;
  background: radial-gradient(circle, var(--pri), rgba(43,217,254,.25));
  box-shadow: 0 0 16px rgba(43,217,254,.7), 0 0 24px rgba(43,217,254,.25);
}
.brand-text{ color:#E6F7FF; text-shadow: 0 0 12px rgba(43,217,254,.38); }

.header-actions{
  display:flex; align-items:center; gap:12px; flex-wrap:wrap;
}

/* 站点按钮（霓虹效果） */
.stations{ display:flex; gap:8px; flex-wrap:wrap; max-width: 860px; }
.station-btn{
  padding: 8px 12px; border-radius: 12px; cursor:pointer; font-size: 15px;
  color: rgba(255,255,255,.8); border: 1px solid rgba(255,255,255,.12);
  background: linear-gradient(180deg, rgba(255,255,255,.04), rgba(255,255,255,.02));
  transition: transform .14s ease, box-shadow .18s ease, background .2s ease, border-color .2s ease;
}
.station-btn:hover{ transform: translateY(-1px); box-shadow: 0 6px 18px rgba(0,0,0,.35); border-color: rgba(43,217,254,.55); }
.station-btn.active{
  color: #0A1220; border-color: transparent;
  background: linear-gradient(180deg, #2BD9FE, #1AA9E9);
  box-shadow: 0 0 22px rgba(43,217,254,.35), inset 0 1px 0 rgba(255,255,255,.18);
}


.dark-picker :deep(.ant-picker){
  background: var(--adp-bg); border-color: var(--adp-bd); color: var(--adp-tx);
}
.dark-picker :deep(.ant-picker-input > input){ color: var(--adp-tx); }
.dark-picker :deep(.ant-picker-suffix){ color: rgba(255,255,255,.7); }

/* 霓虹按钮 */
.btn-ghost{
  display:inline-flex; align-items:center; gap:6px; padding:8px 12px; border-radius:12px;
  border:1px solid rgba(43,217,254,.55); color:#E6F7FF; background:rgba(43,217,254,.12);
  box-shadow:0 0 12px rgba(43,217,254,.30) inset, 0 0 16px rgba(43,217,254,.20);
  transition:transform .2s ease, box-shadow .2s ease, opacity .2s ease;
  font-size: 13px;
}
.btn-ghost:hover{ transform:translateY(-1px); box-shadow:0 0 16px rgba(43,217,254,.36) inset, 0 6px 18px rgba(0,0,0,.4); }
.btn-ghost:disabled{ opacity:.5; cursor:not-allowed; }

/* 实时时钟 */
.clock{ font-variant-numeric: tabular-nums; color: white; min-width: 170px;}

/* 主体布局：上下两行 */
.screen-main{
  display:grid; grid-template-rows: 1fr 0.9fr; gap:14px; padding:14px; height: calc(100% - 55px); box-sizing:border-box;
}

/* 卡片（通用） */
.card{
  background: linear-gradient(180deg, rgba(255,255,255,.04), rgba(255,255,255,.02));
  border:1px solid var(--line); border-radius:16px; padding:12px;
  box-shadow: 0 0 0 1px rgba(43,217,254,.06) inset, 0 10px 28px rgba(0,0,0,.35);
}

/* 卡片标题（霓虹下划线） */
.card-title{
  color: var(--txt); font-size: 16px; margin-bottom: 8px; position: relative; letter-spacing:.2px;
}
.card-title::after{
  content:""; position:absolute; left:0; bottom:-6px; width: 72px; height:2px;
  background: linear-gradient(90deg, var(--pri), transparent);
}

/* 图表容器 */
.chart-card{ display:flex; flex-direction:column; min-height: 0; }
.chart{ width: 100%; height: 100%; min-height: 420px; }

/* 下半占位 */
.kpi-card{ display:flex; align-items:center; justify-content:center; }
.kpi-placeholder{ color: var(--txt-2); }

/* 反馈 */
.error{
  margin-top: 6px; color: #ffb4b4;
  background: linear-gradient(180deg, rgba(255,91,91,.15), rgba(255,91,91,.08));
  border: 1px solid rgba(255,91,91,.35);
  padding: 8px 10px; border-radius: 10px;
}
.loading{ margin-top: 6px; color: rgba(255,255,255,.65); }

/* 小动画 */
@keyframes fadeIn { from { opacity: 0; transform: translateY(4px); } to { opacity: 1; transform: translateY(0); } }
</style>
