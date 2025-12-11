<!-- StationMapRanches.vue -->
<template>
  <div class="ds-page">
    <!-- 左：地图 -->
    <section class="map-pane">
      <div class="map-container" id="map-container"></div>
  <MapInfoBar v-if="refmap"
                :map="refmap" />

      <!-- 地图左上角芯片：当前站点名 -->
      <div class="map-chip" v-if="active">
        <span class="dot"></span>{{ active.name }}
      </div>

      <!-- 右下角：地图内控件 -->
      <div class="map-tools">
        <button class="btn ghost" @click="fitAll">总览</button>
        <button class="btn ghost" :disabled="!active" @click="active && flyToStation(active)">定位该站</button>
      </div>
    </section>

    <!-- 右：站点介绍（3 个牧场） -->
    <aside class="info-pane">
      <header class="pane-header">
        <h2>牧场信息</h2>
        <p class="sub">点击右侧按钮或地图气泡切换站点</p>
      </header>

      <!-- 三个牧场按钮（与地图联动） -->
      <div class="station-tabs">
        <button
          v-for="s in stations"
          :key="s.id"
          class="tab"
          :class="{ active: s.id === activeId }"
          @click="selectStation(s.id, 'panel')"
        >
          <span class="tab-dot"></span>{{ s.name }}
        </button>
      </div>

      <!-- 选中站点详情卡 -->
       <div v-if="!active" class="empty-pane">
        <p class="hint">点击地图上的站点或上方列表查看详情</p>
       </div>
       <div v-else class="station-card">
        <div class="card-title">
          <h3>{{ active.name }}</h3>
          <span class="kbadge">{{ active.code }}</span>
        </div>

        <div class="kv">
          <div class="row">
            <label>位置:</label>
            <div>{{ active.lon.toFixed(2) }}, {{ active.lat.toFixed(2) }}</div>
          </div>
          <div class="row">
            <label>地址:</label>
            <div>{{ active.addr || '—' }}</div>
          </div>
          <div class="row">
            <label>简介:</label>
            <div class="desc">{{ active.desc || '—' }}</div>
          </div>
        </div>


        <div class="actions">
          <button class="btn ghost" @click="fitAll">总览</button>
          <button class="btn primary" @click="flyToStation(active)">定位该站</button>
        </div>

        <div v-if="active?.photo" class="card-photo">
          <img :src="active.photo" alt="站点图片" loading="lazy" />
        </div>


      </div>
    </aside>
  </div>
</template>

<script setup>
import { onMounted, ref, computed, watch, shallowRef } from 'vue'
import L from 'leaflet'
import 'leaflet/dist/leaflet.css'
import MapInfoBar from '@/components/my-map-info-bar/MapInfoBar.vue'
import ZiDongZhan from '@/assets/GeographicData/自动站.json'
import PIN_PNG from '@/static/icons/location.png'
import PIN_ACTIVE_PNG from '@/static/icons/pin2.png'
import coreStationsData from '@/assets/data/mainmc.json'
import extraStationsData from '@/assets/data/nxmc.json'


const coreStations = ref(coreStationsData)
const extraStations = ref(extraStationsData)

const stations = coreStations
const activeId = ref(null)
const active = computed(() => coreStations.value.find(s => s.id === activeId.value) || null)
const refmap = shallowRef(null)
let map = null
const PIN_URL = PIN_PNG
const PIN_ACTIVE_URL = PIN_ACTIVE_PNG
const coreMarkerMap = new Map()
const extraMarkerMap = new Map()
const markerMap = new Map()
let initialView = null

// 统一的图钉工厂
function makePin(url = PIN_URL, size = [44, 44]) {
  return L.icon({
    iconUrl: url,
    iconSize: size,
    iconAnchor: [size[0] / 2, size[1] - 4],
    className: 'png-pin'
  })
}

const defaultPinIcon = makePin(PIN_URL, [48, 48])
const activePinIcon = makePin(PIN_ACTIVE_URL, [52, 52])
onMounted(() => {
  refmap.value = map = L.map('map-container', {
    center: [37.50, 106.30],
    zoom: 8,
    minZoom: 3,
    maxZoom: 18,
    zoomControl: false,
    doubleClickZoom: false,
    attributionControl: false
  })

  addCoreMarkers()   // 一开始三枚图钉全部显示
  addExtraMarkers()  // 新增 5 个点显示（只在地图中）

  // 初始不强制飞行；如需要让地图紧凑包含全部点，可取消注释：
  // fitAll()
  initialView = {
    center: map.getCenter(),
    zoom: map.getZoom()
  }

  setTimeout(() => map && map.invalidateSize(), 0)
})

function addCoreMarkers() {
  clearCoreMarkers()
  coreStations.value.forEach((s) => {
    const m = L.marker([s.lat, s.lon], {
      icon: defaultPinIcon,
      riseOnHover: true
    })
    m.on('click', () => selectStation(s.id, 'map'))
    m.addTo(map)
    coreMarkerMap.set(s.id, m)
  })
  syncCoreMarkerIcons()
}
function clearCoreMarkers() {
  coreMarkerMap.forEach(m => m.remove())
  coreMarkerMap.clear()
}


function addExtraMarkers() {
  clearExtraMarkers()
  coreStations.value.forEach((s) => {
    const m = L.circleMarker([s.lat, s.lon], {
      radius: 8,
      weight: 2,
      color: 'rgba(0,255,163,.9)',
      fillColor: 'rgba(0,255,163,.25)',
      fillOpacity: .9
    }).addTo(map)
    m.bindTooltip(s.name, { direction: 'top', offset: [0, -6] })
    extraMarkerMap.set(s.id, m)
  })
  extraStations.value.forEach(s => {
    const m = L.circleMarker([s.lat, s.lon], {
      radius: 4,
      weight: 2,
      color: 'rgba(0,255,163,.9)',
      fillColor: 'rgba(0,255,163,.25)',
      fillOpacity: .9
    }).addTo(map)
    m.bindTooltip(s.name, { direction: 'top', offset: [0, -6] })
    extraMarkerMap.set(s.id, m)
  })
}


function clearExtraMarkers() {
  extraMarkerMap.forEach(m => m.remove())
  extraMarkerMap.clear()
}

// —— 选择核心 3 个站点之一：保持三枚图钉常显，选中的切换样式
function selectStation(id, from) {
  if (activeId.value !== id) activeId.value = id
  syncCoreMarkerIcons()
  // 是否飞行到该站点
  // flyToStation(active.value)
}

function syncCoreMarkerIcons() {
  coreMarkerMap.forEach((m, sid) => {
    const isActive = sid === activeId.value
    m.setIcon(isActive ? activePinIcon : defaultPinIcon)
    m.setZIndexOffset(isActive ? 1000 : 0)
    if (!map.hasLayer(m)) m.addTo(map)
  })
}


function styleOf(id) {
  const isActive = id === activeId.value
  return {
    radius: isActive ? 10 : 10,
    weight: isActive ? 4 : 2,
    color: isActive ? 'rgba(43,217,254,1)' : 'rgba(0,255,163,.9)',
    fillColor: isActive ? 'rgba(43,217,254,.35)' : 'rgba(0,255,163,.25)',
    fillOpacity: 0.9,
    className: isActive ? 'pulse-on' : 'pulse-off'
  }
}

function refreshMarkerStyles() {
  markerMap.forEach((m, id) => m.setStyle(styleOf(id)))
}



// —— 地图飞行到单站
function flyToStation(s) {
  if (!map || !s) return
  const z = map.getZoom() ?? 9
  map.flyTo([s.lat, s.lon], z, { duration: 0.8 })
}

// —— 视野框出全部站点
function fitAll() {
  if (!map) return

  //恢复三枚核心图钉 + 试验点
  addCoreMarkers()
  addExtraMarkers()

  // 2. 清除当前选中的站点，让右侧回到提示态
  activeId.value = null

  // 3. 地图视图恢复到刚进入页面时的 center + zoom
  if (initialView) {
    map.flyTo(initialView.center, initialView.zoom, { duration: 0.6 })
  } else {
    // 兜底：如果没记录成功，就用当前配置的默认值
    map.setView([37.50, 106.30], 8)
  }
}


// —— 响应式：活动站点变化时刷新 marker 样式
watch(activeId, () => {
  refreshMarkerStyles()
  syncCoreMarkerIcons()
})
</script>
<style scoped lang="scss">
/* 页面整体留出顶部导航高度，避免遮挡 */
.ds-page{
  --app-header-h: var(--app-header-h, 72px);

  /* 数字可视化大屏主题变量 */
  --bg: #0E1A2B;
  --txt: rgba(255,255,255,.92);
  --sub: rgba(255,255,255,.62);
  --line: rgba(255,255,255,.10);
  --blue: #2BD9FE;
  --cyan: #00FFA3;
  --panel-grad: linear-gradient(180deg, rgba(255,255,255,.06), rgba(255,255,255,.03));

  position: relative;
  display: grid;
  grid-template-columns: 65% 35%;
  gap: 10px;

  margin-top: var(--app-header-h);
  height: 100%;
  width: 100%;
  padding: 16px;

  color: var(--txt);
  background:
    radial-gradient(1200px 700px at 18% -22%, rgba(43,217,254,.14), transparent 60%),
    radial-gradient(900px 560px  at 85% 120%, rgba(0,255,163,.12), transparent 60%),
    var(--bg);
}

/* 左：地图容器 */
.map-pane{
  position: relative;
  border: 1px solid var(--line);
  border-radius: 16px;
  overflow: hidden;
  background: #0b1422;
  box-shadow: inset 0 0 0 1px rgba(255,255,255,.02), 0 10px 30px rgba(0,0,0,.35);
}
.map-container{ width: 100%; height: 100%; }

/* 右：信息面板 */
.info-pane{
  display: flex; flex-direction: column; gap: 12px;
  border: 1px solid var(--line);
  border-radius: 16px;
  padding: 16px;
  background: var(--panel-grad);
  box-shadow: inset 0 0 0 1px rgba(255,255,255,.02), 0 10px 30px rgba(0,0,0,.35);
}
.pane-header h2{
  margin: 0 0 4px; font-size: 30px; letter-spacing: .5px;
  text-shadow: 0 0 18px rgba(43,217,254,.25);
}
.pane-header .sub{ margin: 0; font-size: 12px; color: var(--sub); }

/* 站点按钮组 */
.station-tabs{
  display: grid; grid-template-columns: 1fr;
  gap: 10px;
}
.tab{
  width: 100%;
  display: inline-flex; align-items: center; gap: 10px;
  padding: 12px 14px;
  border-radius: 12px;
  border: 1px solid rgba(255,255,255,.08);
  background: linear-gradient(180deg, rgba(255,255,255,.05), rgba(255,255,255,.02));
  color: var(--txt);
  font-size: 20px;
  text-align: left;
  cursor: pointer;
  transition: .18s ease;
}
.tab:hover{ border-color: rgba(139,233,255,.22); transform: translateY(-1px); }
.tab.active{
  background: linear-gradient(180deg, var(--blue), #1AA9E9);
  color: #0B1220; border-color: transparent;
  box-shadow: 0 0 20px rgba(43,217,254,.35), inset 0 1px 0 rgba(255,255,255,.18);
}
.tab-dot{
  width: 12px; height: 12px; border-radius: 50%;
  background: var(--cyan);
  box-shadow: 0 0 12px rgba(0,255,163,.8);
}

/* 详情卡 */
.station-card{
  margin-top: 6px;
  padding: 14px;
  height: 100%;
  border-radius: 14px;
  border: 1px solid var(--line);
  background: linear-gradient(180deg, rgba(255,255,255,.04), rgba(255,255,255,.02));
  box-shadow: inset 0 0 0 1px rgba(255,255,255,.02);
}
.card-title{
  display: flex; align-items: center; justify-content: space-between; gap: 10px;
}
.card-title h3{ margin: 0; font-size: 20px; }
.kbadge{
  font-size: 18px; padding: 4px 8px; border-radius: 999px;
  background: rgba(43,217,254,.15);
  border: 1px solid rgba(43,217,254,.45);
}
.kv .row{
  display: grid; grid-template-columns: 80px 1fr; gap: 12px;
  padding: 8px 0; border-top: 1px dashed rgba(255,255,255,.06); font-size: 17px;
}
.kv .row:first-child{ border-top: 0; }
.kv label{ color: var(--sub); font-size: 18px; }
.desc{
  line-height: 1.6;
  color: var(--txt);
  white-space: normal;   // 正常换行
  text-align: left;      // 左对齐
  text-indent: 0;        // 不要首行缩进
  word-break: break-word;
}

/* 小统计条 */
.metrics{
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 8px; margin-top: 10px;
}
.metric{
  border: 1px solid rgba(255,255,255,.08);
  border-radius: 12px;
  padding: 10px 12px;
  background: linear-gradient(180deg, rgba(255,255,255,.05), rgba(255,255,255,.02));
}
.m-name{ font-size: 20px; color: var(--sub); margin-bottom: 8px; }
.m-val{ font-size: 18px; font-weight: 700; letter-spacing: .3px; }
.m-sub{ font-size: 16px; color: var(--sub); }

.actions{ display: flex; gap: 10px; margin-top: 12px; }
.btn{
  padding: 10px 14px; border-radius: 12px; border: 1px solid var(--line);
  background: linear-gradient(180deg, rgba(255,255,255,.05), rgba(255,255,255,.02));
  color: var(--txt); cursor: pointer; transition: .18s;
}
.btn:hover{ border-color: rgba(139,233,255,.22); transform: translateY(-1px); }
.btn.primary{ background: linear-gradient(180deg, var(--blue), #1AA9E9); color:#0B1220; border-color: transparent; }
.btn.ghost{ background: transparent; }
/* 活动点位的“脉冲”动画 */
.leaflet-interactive.pulse-on{
  filter: drop-shadow(0 0 10px rgba(9, 190, 230, 0.85));
  animation: pulse 1.8s ease-in-out infinite;
}

.png-pin {
   filter: drop-shadow(0 4px 20px rgba(43,217,254,.45))
           drop-shadow(0 0 10px rgba(0,255,163,.3));
}
.card-photo{
  margin-top: 12px;
  border-radius: 12px;
  overflow: hidden;
  border: 1px solid rgba(255,255,255,.08);
  background: linear-gradient(180deg, rgba(255,255,255,.04), rgba(255,255,255,.02));
  box-shadow:
    0 10px 30px rgba(0,0,0,.35),
    inset 0 0 0 1px rgba(255,255,255,.02);
}
.card-photo img{
  display: block;
  width: 100%;
  aspect-ratio: 16 / 9;     /* 没有的话就用固定高度：height: 160px; */
  object-fit: cover;
}
.photo-title{
  margin-top: 10px;
  font-size: 14px;
  color: #bce7ff;
  opacity: .9;
}

@keyframes pulse{
  0%{ transform: scale(1); opacity: 1 }
  50%{ transform: scale(1.12); opacity: .9 }
  100%{ transform: scale(1); opacity: 1 }
}

/* 中/小屏响应式 */
@media (max-width:1440px){ .ds-page{ grid-template-columns: 60% 40%; } }
@media (max-width:992px){
  .ds-page{ grid-template-columns: 1fr; grid-template-rows: 50% 50%; }
  .metrics{ grid-template-columns: repeat(2, 1fr); }
}
.empty-pane{
  padding: 12px;
  border: 1px dashed var(--line);
  border-radius: 12px;
  color: var(--sub);
  text-align: center;
}
.empty-pane .hint{
  margin: 4px 0 2px;
  font-size: 14px;
}

</style>
