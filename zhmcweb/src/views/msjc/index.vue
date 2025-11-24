<!-- eslint-disable vue/multi-word-component-names -->
<style scoped lang="scss">
:deep(.my-ant-time-input) {
    width: 300px;
    height: 36px;
    background: transparent;
    box-shadow:
        0 0 6px rgba(0, 0, 0, 0.6) inset,
        0 0 3px rgba(255, 255, 255, 0.3);
    color: #fff;
    font-size: 15px;

    svg {
        // fill: #78f7f6;
    }

    input {
        color: inherit;
        font-size: inherit;

        &::-webkit-input-placeholder {
            color: inherit;
        }
    }

    .ant-picker-clear {
        // background: #000;
    }
}

.view {
    width: 100%;
    height: 100%;

    .header {
        height: 50px;
        display: flex;
        align-items: end;
    }

    .main {
  /* 从两列 -> 单列 */
  display: grid;
  grid-template-columns: 1fr;        // 原来是 repeat(2, 1fr)
  grid-auto-rows: minmax(0, auto);
  padding: 10px 30px;
  overflow: hidden;
  gap: 15px;

  /* 临时：如果模板里还留着 .right，直接隐藏即可 */
  .right { display: none !important; }

  /* 左侧占满整行，做成 3 行：上 340px 固定高度，下两块自适应 */
  .left {
      display: grid;
      grid-template-rows: 340px minmax(0, 1fr) minmax(0, 1fr);  // 原来是 auto 1fr 1fr
      grid-template-columns: 100%;
      gap: 10px;  // 原 5px，全屏下放大一点
      min-height: 0; // 允许子元素在grid里收缩
  }


  /* 顶部一块（实况 + 两个小仪表） */
  .left .yb-box {
      display: grid;
      /* 三列：左侧信息区自适应，两个仪表固定宽；更适合大屏 */
      grid-template-columns: minmax(520px, 1fr) 360px 240px; // 原 auto 300px 180px
      grid-auto-rows: 100%;
      gap: 10px;
      position: relative;

      & > div {
          background-color: rgba(255, 255, 255, 0.1);
      }

      .yjzs-box {
          display: flex;
          align-items: center;
          color: #fff;
          font-size: 26px;
          line-height: 1.5em;
          text-align: left;
          padding: 12px 14px;

          div:first-child {
              flex: 1;
              height: 100%;
              display: flex;
              align-items: center;
              justify-content: center;

              img {
                  max-width: 100%;
                  max-height: 100%;
                  object-fit: contain;
              }
          }

          div:last-child {
              background: url(@/assets/images/奶牛风险/mc-nainiu7_03.png) left center / cover no-repeat;
              display: grid;
              grid-template-rows: repeat(5, 1fr);
              grid-template-columns: auto auto;
              gap: 4px 12px;
              padding: 10px 14px 22px 30px;
          }
      }

      /* 两个小仪表：让它们填满各自网格 */
      .rhu-box,
      .tem-box {
          width: 100%;     // 原来写死 300px
          height: 100%;    // 原来 300px
          background-color: rgba(255, 255, 255, 0.1);
          border-radius: 12px;
          overflow: hidden;

      }
  }


  /* 下方两块表格/图表容器，铺满并可滚动 */
  .left .tc-box {
      display: flex;
      flex-direction: column;
      min-height: 0;
      background: rgba(255, 255, 255, 0.06);
      border: 1px solid rgba(255, 255, 255, 0.12);
      border-radius: 12px;
      overflow: hidden;

      /* 如果内部是表格或图表，建议给个自适应高度容器 */
      > .inner,
      > .chart,
      > .table-wrap {
          flex: 1;
          min-height: 0;
          overflow: auto;
      }
  }
}

/* 1920 全高清大屏下的微调（可选） */
@media (min-width: 1600px) {
  .main .left .yb-box {
    grid-template-columns: minmax(600px, 1fr) 380px 260px;
  }
}

}
.main .left .yb-box {
  grid-template-columns: minmax(0, 1.5fr) minmax(0, 1fr) minmax(0, .8fr) !important;
}

/* 2) 如果你有大屏媒体查询里又把它改回像素，顺便一起覆盖 */
@media (min-width: 1600px) {
  .main .left .yb-box {
    grid-template-columns: minmax(0, 1.5fr) minmax(0, 0.8fr) minmax(0, 0.8fr) !important;
  }
}

/* 3) 允许每个格子按 fr 收缩，否则内容会“撑开”列宽 */
.main .left .yb-box > * { min-width: 0; }

/* 4) 子项不要再写死宽度（之前有 300px），改成占满格子 */
.rhu-box, .tem-box { width: 100%; height: 100%; }
.station-switch{
  display:flex;
  gap:10px;
  flex-wrap:wrap;
}

.tab{
  appearance:none;
  user-select:none;
  cursor:pointer;
  padding:8px 14px;
  border-radius:999px;
  border:1px solid rgba(255,255,255,.14);
  background:
    linear-gradient(180deg, rgba(255,255,255,.09), rgba(255,255,255,.03));
  color:#E8F1FF;
  font-size:14px;
  letter-spacing:.2px;
  line-height:1;
  transition: all .18s ease;
  position:relative;
  box-shadow:
    inset 0 0 0 0 rgba(0,0,0,.25),
    0 0 0 rgba(0,0,0,0);
}

.tab:hover{
  transform: translateY(-1px);
  border-color: rgba(43,217,254,.35);
  box-shadow: 0 6px 24px rgba(43,217,254,.18);
}

.tab:active{
  transform: translateY(0);
  filter: brightness(.96);
}

.tab:focus-visible{
  outline: 2px solid #2BD9FE;
  outline-offset: 2px;
}

/* —— 选中态：蓝色渐变 + 内/外发光 + 细边框“流光” —— */
.tab.active{
  color:#0B1220;
  border-color: transparent;
  background: linear-gradient(180deg, #2BD9FE, #1AA9E9);
  box-shadow:
    0 0 24px rgba(43,217,254,.35),
    inset 0 1px 0 rgba(255,255,255,.18);
}
.tab.active::after{
  content:'';
  position:absolute; inset:-2px;
  border-radius:999px;
  padding:1px; /* 用遮罩做一圈“流光边” */
  background: linear-gradient(45deg, rgba(43,217,254,.7), rgba(0,255,163,.7));
  -webkit-mask: linear-gradient(#000 0 0) content-box, linear-gradient(#000 0 0);
  -webkit-mask-composite: xor; mask-composite: exclude;
}

</style>

<template>
    <el-container class="view">
      <el-header class="header">
      <a-range-picker
        class="my-ant-time-input"
        v-model:value="selectTime"
        separator="至"
        format="YYYY-MM-DD HH"
        :show-time="{ format: 'HH' }"
        :locale="locale"
        :presets="antPresets"
        @calendarChange="antCalendarChange"
        @openChange="(status) => { showAntOpenChange.value = status }"
        @ok="antDateOK"
      />
      <div class="station-switch">
  <button
    v-for="s in stations"
    :key="s.name"
    class="tab"
    :class="{ active: s.name === selectedStation }"
    :aria-pressed="(s.name === selectedStation) ? 'true' : 'false'"
    @click="switchStation(s.name)"
  >
    {{ s.name }}
  </button>
</div>

    </el-header>

        <el-main class="main">
            <div class="left" :key="selectedStation">
                <div class="yb-box">
                    <div class="yjzs-box">
                        <div><img :src="lastData.icon152"></div>
                        <div>
                            <span>观测时间：</span><span>{{ lastData.datetime }}</span>
                            <span>温度：</span><span>{{ lastData.tem152 }}℃</span>
                            <span>湿度：</span><span>{{ lastData.rhu152 }}%</span>
                            <span>奶牛状态：</span><span>{{ lastData.yjzt152 }}</span>
                            <span>应激指数：</span><span>{{ lastData.yjzs152 }}</span>
                        </div>
                    </div>
                    <div class="rhu-box"
                         id="152-rhu-chart">
                    </div>
                    <div class="tem-box"
                         id="152-tem-chart"></div>
                </div>
                <div class="tc-box">
                    <TableChart v-model="tCData"
                                v-bind="cnlTCOpt152" />
                </div>
                <div class="tc-box">
                    <TableChart v-model="tCData"
                                v-bind="yjzsTCOpt152" />
                </div>
            </div>

        </el-main>
    </el-container>
</template>

<script setup>
import { ref, onMounted, computed, nextTick, watch } from "vue";
import axios from "axios";
import dayjs from "dayjs";
import "dayjs/locale/zh-cn";
import locale from "ant-design-vue/es/date-picker/locale/zh_CN";
import TableChart from "@/components/my-table-chart/index.vue";
import { getRandomNumber } from "@/components/utils.js";
import * as echarts from "echarts";
// ===== 1) 三个站点，仅保留一份模板，通过切换展示 =====
const stations = ref([
{ name: '吉兴燊家庭牧场奶牛养殖', lon: 106.195, lat: 37.690, key: 'jxs' },
{ name: '罗山自然保护区', lon: 106.130, lat: 37.180, key: 'ls' },
{ name: '同心县欢欢种植家庭农场', lon: 106.520, lat: 37.690, key: 'tx' },
])
const selectedStation = ref(stations.value[0].name)
const currentStation = computed(() => stations.value.find(s => s.name === selectedStation.value) || stations.value[0])
function switchStation(name){ if (name !== selectedStation.value) selectedStation.value = name }

const selectTime = ref([dayjs().add(-24, 'hour'), dayjs()])
const antPresets = ref([
{ label: '前1小时', value: [selectTime.value[1].add(-1, 'hour'), selectTime.value[1]] },
{ label: '前3小时', value: [selectTime.value[1].add(-3, 'hour'), selectTime.value[1]] },
{ label: '前6小时', value: [selectTime.value[1].add(-6, 'hour'), selectTime.value[1]] },
{ label: '前12小时', value: [selectTime.value[1].add(-12, 'hour'), selectTime.value[1]] },
{ label: '前24小时', value: [selectTime.value[1].add(-24, 'hour'), selectTime.value[1]] },
])
const showAntOpenChange = ref(false)
const antCalendarChange = (range) => {
if (!range?.[1]) return
antPresets.value = [
{ label: '前1小时', value: [range[1].add(-1, 'hour'), range[1]] },
{ label: '前3小时', value: [range[1].add(-3, 'hour'), range[1]] },
{ label: '前6小时', value: [range[1].add(-6, 'hour'), range[1]] },
{ label: '前12小时', value: [range[1].add(-12, 'hour'), range[1]] },
{ label: '前24小时', value: [range[1].add(-24, 'hour'), range[1]] },
]
}
function antDateOK(date){ if (!showAntOpenChange.value) console.log(date) }





let tCData = ref([]),
    cnlTCOpt152 = {
        title: "产奶量对比",

        toolbar: [
            { name: "图表转换", mode: true },
            { name: "行列转换", mode: false },
            { name: "导出表格" }
        ],
        column: [
            { width: "140px", prop: "datetime", label: "时间", axis: "x", isHeader: true, disable: true, format: (v) => dayjs(v).format("DD日HH时"), sortable: true },
            { prop: "cnl152", label: "产奶量", unit: "ml", axis: "y", sortable: true, color: "#228B22" },


        ],
    },
    yjzsTCOpt152 = {
        title: "应激指数对比",
        toolbar: [
            { name: "图表转换", mode: true },
            { name: "行列转换", mode: false },
            { name: "导出表格" }
        ],
        column: [
            { width: "140px", prop: "datetime", label: "时间", axis: "x", isHeader: true, disable: true, format: (v) => dayjs(v).format("DD日HH时"), sortable: true },
            { prop: "yjzs152", label: "应激指数", unit: "", axis: "y", sortable: true ,color: "#4169E1"},

        ],
    },

    lastData = ref({});


onMounted(() => {
    getData();
})

watch([selectedStation, selectTime], async () => {
  await nextTick();
  getData();
});

function getData() {

    // 基于温湿度计算应激指数(THI)，不再随机生成 yjzs
    let data = Array.from({ length: selectTime.value[1].diff(selectTime.value[0], "hour") + 1 }, (v, i) => {
        const datetime = selectTime.value[0].add(i, "hour").format("YYYY-MM-DD HH:00:00");

        const tem152 = getRandomNumber(10, 23);
        const rhu152 = getRandomNumber(40, 70);
        const thi152 = getStressLevel(tem152, rhu152).thi;

        const tem203 = getRandomNumber(10, 23);
        const rhu203 = getRandomNumber(40, 70);
        const thi203 = getStressLevel(tem203, rhu203).thi;

        return {
            datetime,
            yjzs152: thi152,
            cnl152: getRandomNumber(20, 33),
            tem152,
            rhu152,
            yjzs203: thi203,
            cnl203: getRandomNumber(20, 33),
            tem203,
            rhu203,
        };
    });


    let timeDiff = selectTime.value[1].diff(selectTime.value[0], "hour") + 1;

    tCData.value = Array.from({ length: timeDiff }, (v, i) => {
        let datetime = selectTime.value[0].add(i, "hour").format("YYYY-MM-DD HH:00:00");

        let target = data.find(v => v.datetime == datetime);
        return {
            datetime: datetime,
            yjzs152: target?.yjzs152,
            cnl152: target?.cnl152,
            tem152: target?.tem152,
            rhu152: target?.rhu152,
            yjzs203: target?.yjzs203,
            cnl203: target?.cnl203,
            tem203: target?.tem203,
            rhu203: target?.rhu203,
        }
    });
    let lastTcData = tCData.value[tCData.value.length - 1];
    lastData.value = {
        datetime: dayjs(lastTcData.datetime).format("MM-DD HH:mm"),
        icon152: new URL(`../../assets/images/奶牛风险/奶牛-${getStressLevel(lastTcData.tem152, lastTcData.rhu152).level}.png`, import.meta.url).href,
        yjzt152: getStressLevel(lastTcData.tem152, lastTcData.rhu152).level,
        yjzs152: lastTcData.yjzs152,
        cnl152: lastTcData.cnl152,
        tem152: lastTcData.tem152,
        rhu152: lastTcData.rhu152,
        icon203: new URL(`../../assets/images/奶牛风险/奶牛-${getStressLevel(lastTcData.tem203, lastTcData.rhu203).level}.png`, import.meta.url).href,
        yjzt203: getStressLevel(lastTcData.tem203, lastTcData.rhu203).level,
        yjzs203: lastTcData.yjzs203,
        cnl203: lastTcData.cnl203,
        tem203: lastTcData.tem203,
        rhu203: lastTcData.rhu203,
    }
    dashboardChart({
        eId: "152-rhu-chart",
        data: tCData.value[tCData.value.length - 1].rhu152
    });
    thermometerChart({
        eId: "152-tem-chart",
        data: tCData.value[tCData.value.length - 1].tem152
    })
}







function dashboardChart(info) {
    let textColor = "#fff";
    // let option = {
    //     series: [
    //         {
    //             type: "gauge",
    //             center: ["50%", "56%"],
    //             // startAngle: 90,
    //             // endAngle: -270,
    //             // min: 10,
    //             // max: 100,
    //             // radius: "80%",
    //             data: [{ value: info.data }],
    //             detail: {
    //                 color: "inherit",
    //                 offsetCenter: [0, 40],
    //                 valueAnimation: true,
    //                 formatter: "{value}%"
    //             },
    //             axisLine: {
    //                 lineStyle: {
    //                     width: 20,
    //                     color: [
    //                         [0.4, "#67e0e3"],
    //                         [0.8, "#37a2da"],
    //                         [1, "#fd666d"]
    //                     ]
    //                 }
    //             },
    //             splitLine: {
    //                 length: 10,
    //                 distance: -34,
    //                 lineStyle: { color: "#fff" }
    //             },
    //             axisTick: {
    //                 length: 6,
    //                 distance: -30,
    //                 lineStyle: { color: "#fff" }
    //             },
    //             axisLabel: {
    //                 distance: -8,
    //                 color: "inherit",
    //             },
    //             pointer: {
    //                 length: "100%",
    //                 itemStyle: {
    //                     color: "orange"
    //                 }
    //             },
    //         }
    //     ]
    // };
    let option = {
        series: [
            {
                type: "gauge",
                center: ["50%", "57.5%"],
                radius: "90%",
                data: [{ value: info.data }],
                detail: {
                    color: "inherit",
                    offsetCenter: [0, 40],
                    valueAnimation: true,
                    formatter: "{value}%"
                },
                axisLine: {
                    lineStyle: {
                        width: 20,
                        color: [
                            [0.4, "#67e0e3"],
                            [0.8, "#37a2da"],
                            [1, "#fd666d"]
                        ]
                    }
                },
                splitLine: {
                    length: 10,
                    distance: 4,
                    lineStyle: { color: "#fff" }
                },
                axisTick: {
                    length: 6,
                    distance: 4,
                    lineStyle: { color: "#fff" }
                },
                axisLabel: {
                    distance: 26,
                    color: "inherit",
                },
                pointer: {
                    length: "100%",
                    itemStyle: { color: "orange" }
                },
            }
        ]
    };


    nextTick(() => {
    const dom = document.getElementById(info.eId);
    if (!dom) return;
    const inst = echarts.getInstanceByDom(dom) || echarts.init(dom, null, { locale: "ZH" });
    inst.clear();
    inst.setOption(option, true);
  });
}

function thermometerChart(info) {
    let myChartDom = document.getElementById(info.eId);
    let tem = info.data;
    let thresholds = [
        { color: "#001464", range: [-999, -32] },
        { color: "#00309e", range: [-32, -28] },
        { color: "#185ab4", range: [-28, -24] },
        { color: "#2073dc", range: [-24, -20] },
        { color: "#41a0e6", range: [-20, -16] },
        { color: "#72d2fa", range: [-16, -12] },
        { color: "#96e2f0", range: [-12, -8] },
        { color: "#befaff", range: [-8, -4] },
        { color: "#f0faff", range: [-4, 0] },
        { color: "#dcffd5", range: [0, 4] },
        { color: "#c0feb3", range: [4, 8] },
        { color: "#b4ff82", range: [8, 12] },
        { color: "#fafa8f", range: [12, 16] },
        { color: "#fff2c0", range: [16, 20] },
        { color: "#ffdfb0", range: [20, 24] },
        { color: "#ffae76", range: [24, 28] },
        { color: "#ff878a", range: [28, 32] },
        { color: "#ff5600", range: [32, 36] },
        { color: "#ec5c60", range: [36, 40] },
        { color: "#e60403", range: [40, 44] },
        { color: "#8f0814", range: [44, 48] },
        { color: "#650017", range: [48, 999] },]
    thresholds = [
        { color: "orange", range: [-999, 999] },
        { color: "orange", range: [-999, 999] },]
    let gradient = [];
    for (let i = 1; i < thresholds.length; i++) {
        let item = thresholds[i];
        if (item.range[0] <= tem) { gradient.push(item.color) }
    }

    let range = [-30, 60],              // 温度计量程。10 ℃修正，实际范围[-30, 50]
        rangeSum = range[1] - range[0],
        border = 6,                     // 温度计边框。
        barSize = [18, rangeSum + 8],   // 温度条。
        circleSize = barSize[0] * 1.8;
    let option = {
        grid: { left: 0, right: 0, bottom: 40, top: 50, containLabel: false },
        yAxis: [{
            name: "℃",
            nameTextStyle: { align: "right", padding: [0, 12, 0, 0] },
            min: 0,
            max: rangeSum,
            interval: 10,
            splitLine: { show: false },
            axisLine: { show: true, lineStyle: { color: "#fff" } },
            axisTick: { show: true, length: 8 },
            minorTick: { show: true, length: 3 },
            axisLabel: {
                margin: 12,
                formatter: function (value, index) { return value == 0 ? "" : value - 40; }
            },

        }],
        xAxis: [
            { show: false, data: ["锚点"], position: "bottom", },
            { show: false, data: ["锚点"], position: "bottom", },
            { show: false, data: ["锚点"], position: "bottom", }],
        series: [{
            name: '温度',
            type: 'bar',
            xAxisIndex: 0,
            barWidth: barSize[0] - border * 2,
            data: [info.data + 40],
            label: {
                show: true,
                position: [barSize[0] + border, 0],
                fontSize: 25,
                color: gradient[gradient.length - 1],
                formatter: `${info.data}°C`,
            },
            itemStyle: { color: new echarts.graphic.LinearGradient(0, 1, 0, 0, gradient.map((v, i) => ({ color: v, offset: i / gradient.length }))) },
            z: 2
        }, {
            name: '温度计柱背景和边框',
            type: 'bar',
            xAxisIndex: 1,
            barWidth: barSize[0] + border * 2,
            data: [barSize[1]],
            itemStyle: { color: '#fff', borderRadius: [9999, 9999, 0, 0], borderWidth: border, borderColor: '#5aabff' },
            z: 1,
            silent: true,
            clip: false,
        }, {
            name: '温度计柱圆头背景',
            type: 'scatter',
            hoverAnimation: false,
            data: [0],
            xAxisIndex: 1,
            symbolSize: circleSize,
            itemStyle: { color: '#fff', opacity: 1 },
            z: 1,
            silent: true,
        }, {
            name: '温度计柱圆头背景边框',
            type: 'scatter',
            hoverAnimation: false,
            data: [0],
            xAxisIndex: 2,
            symbolSize: circleSize + border * 2,
            itemStyle: { color: '#5aabff', opacity: 1 },
            z: 0,
            silent: true,
        }, {
            name: '温度计柱圆头',
            type: 'scatter',
            hoverAnimation: false,
            data: [0],
            xAxisIndex: 0,
            symbolSize: circleSize - border * 2,
            itemStyle: { color: thresholds[0].color, opacity: 1, },
            z: 2,
            silent: true,
        },]
    };

    nextTick(() => {
        let myChart = echarts.init(myChartDom);
        myChart.clear();
        myChart.setOption(option);
    })
}


function getStressLevel(tem, rhu) {
    // 约束输入范围
    if (tem == null || rhu == null) return { thi: null, threshold: null, level: "无法计算" };
    if (rhu < 0) rhu = 0; if (rhu > 100) rhu = 100;

    // THI = (1.8*T + 32) - (0.55 - 0.0055*RH)*((1.8*T + 32) - 58)
    const tf = 1.8 * tem + 32;
    const thiRaw = tf - (0.55 - 0.0055 * rhu) * (tf - 58);
    const thi = Math.round(thiRaw * 10) / 10; // 保留一位小数

    // 分级（农业部标准）：
    // 轻度：68≤THI≤79；中度：79<THI≤88；重度：THI>88
    let level = "正常";
    let advice = "保持常规管理";
    let yieldImpact = "0%";

    if (thi >= 68 && thi <= 79) {
        level = "轻度应激";
        advice = "加强通风";
        yieldImpact = "↓10%-20%";
    } else if (thi > 79 && thi <= 88) {
        level = "中度应激";
        advice = "启动喷淋系统";
        yieldImpact = "↓20%-40%";
    } else if (thi > 88) {
        level = "重度应激";
        advice = "紧急降温，存在生命危险";
        yieldImpact = "↓>40%";
    }

    // 宁夏等北方地区：THI≥72 提前干预
    const earlyIntervene = thi >= 72;
    if (earlyIntervene && level === "轻度应激") {
        advice = "加强通风，提前干预（风扇/喷淋间歇）";
    }

    // 兼容旧字段：threshold 保持为 thi
    return { thi, threshold: thi, level, advice, yieldImpact, earlyIntervene };
}
</script>
