<!-- eslint-disable vue/multi-word-component-names -->
<style scoped lang="scss">
:deep(.my-ant-time-input) {
    width: 100%;
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

.tl-panel {
    width: 310px;
    position: absolute;
    top: 5px;
    left: 5px;
    z-index: 1000;
    color: #000;

    background: linear-gradient(to bottom right, #66BD90, rgba(163, 206, 176, 0.3));
    backdrop-filter: blur(6px);
    color: #fff;

    .fun-box {
        .header {
            height: 40px;
            text-align: left;
            font-size: 16px;
            padding: 10px;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            justify-content: space-between;
            background: linear-gradient(to right, #1F8850, #8FC46A);
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);

            .header-right {
                display: flex;
                align-items: center;
                gap: 4px;
                background-color: rgb(255, 255, 255, 0.1);

            }

            :deep(.el-checkbox) {
                --el-checkbox-checked-text-color: #fff;
            }
        }

        .main {
            text-align: center;
            font-size: 15px;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            flex-wrap: wrap;
            padding: 15px 20px;
            gap: 15px;

            span {
                width: 80px;
                height: 36px;
                line-height: 36px;
                box-shadow:
                    0 0 6px rgba(0, 0, 0, 0.6) inset,
                    0 0 3px rgba(255, 255, 255, 0.5);
                border-radius: 999px;
                cursor: pointer;
                transition: 200ms;

                &:hover {
                    font-size: large;
                    box-shadow: 0 0 3px rgba(255, 255, 255, 0.5);
                }

                &.active {
                    color: #000;
                    background-color: #AFDDDB;
                    box-shadow:
                        1px 1px 2px rgba(255, 255, 255, 0.3) inset,
                        -1px -1px 2px rgba(0, 0, 0, 0.4) inset,
                        -1px -1px 6px rgba(255, 255, 255, 0.2) inset,
                        0 0 5px rgba(0, 0, 0, 0.6);
                }
            }
        }
    }
}

.legend-panel {
    position: absolute;
    bottom: 0;
    right: 0;
    width: 700px;
    z-index: 999;
    color: #fff;
}

.timeBar-panel {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    width: fit-content;
    height: max-content;
    z-index: 999;
    max-width: 100%;
    width: 100%;
}

.station-table-chart {
    position: absolute;
    left: 5px;
    bottom: 0;
    right: 5px;
    // width: 100%;
    z-index: 9999;
    // background: linear-gradient(135deg, rgb(213, 243, 255), rgb(252, 253, 255));
    background: linear-gradient(to bottom right, rgba(72, 113, 142, 0.8), rgba(93, 146, 183, 0.2));
    color: #fff;
    font-size: 15px;
    backdrop-filter: blur(10px);
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.6);

    .close {
        width: 42px;
        height: 42px;
        display: flex;
        align-items: center;
        justify-content: center;
        position: absolute;
        top: 0;
        right: 0;
        cursor: pointer;

        svg {
            width: 26px;
            height: 26px;
            color: #fff;
            transition: 200ms;
            transform-origin: center;

            &:hover {
                color: red;
                scale: 1.1;
            }
        }
    }

    .header {
        width: 100%;
        height: 42px;
        padding: 10px;
        box-sizing: border-box;
        display: flex;
        align-items: center;
        text-align: left;
        font-size: 16px;
        // background-color: rgb(213, 243, 255);
        background: linear-gradient(to right, rgba(50, 127, 83, 0.9), rgba(91, 159, 130, 0.7), rgba(137, 191, 174, 0.5), rgba(188, 222, 216, 0.3), rgba(243, 254, 253, 0.1));
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
    }

    .info-box .main {
        width: 100%;
        height: 55px;
        padding: 10px;
        box-sizing: border-box;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 30px;
    }

    .tc-box {
        width: 100%;
        height: 300px;
    }
}
</style>
<style>
.my-dialog {
    background: linear-gradient(to bottom right, rgba(72, 113, 142, 0.8), rgba(93, 146, 183, 0.3));
    backdrop-filter: blur(10px);
    color: #fff;

    .el-dialog__header {
        padding: 0;
    }

    .el-dialog__header.show-close {
        padding-right: 0;
    }

    .el-dialog__headerbtn {
        position: absolute;
        top: 0;
        right: 0;
        transform: translate(50%, -50%);
        background-color: rgba(72, 113, 142, 0.5);
        box-shadow: 0 0 6px rgba(0, 0, 0, 0.4);
        width: fit-content;
        height: fit-content;
        border-radius: 50%;

        .el-dialog__close {
            width: 30px;
            height: 30px;
            transition: 200ms;
            background-color: white;
            mask: url(@/assets/images/close.svg) center/contain no-repeat;

            &:hover {
                background-color: red;
            }

            svg {
                display: none;
            }
        }
    }

    .my-segmented {
        width: 300px;
        height: 42px;
        position: absolute;
        z-index: 1;
        margin: auto;
        left: 0;
        right: 0;
    }
}
</style>
<template>
    <svg aria-hidden="true"
         style="position: absolute; width: 0; height: 0; overflow: hidden">
        <symbol id="icon-close"
                viewBox="0 0 1000 1000"
                pointer-events="all">
            <circle cx="500"
                    cy="500"
                    r="450"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="100"
                    stroke-dasharray="200% 40% 60 30%"
                    stroke-linecap="round" />
            <line x1="320"
                  y1="320"
                  x2="680"
                  y2="680"
                  stroke="currentColor"
                  stroke-width="80"
                  stroke-linecap="round" />
            <line x1="320"
                  y1="680"
                  x2="680"
                  y2="310"
                  stroke="currentColor"
                  stroke-width="80"
                  stroke-linecap="round" />
        </symbol>
    </svg>

    <div id="map-container"></div>
    <MapInfoBar v-if="refmap"
                :map="refmap" />
    <div class="timeBar-panel">
        <TimeBar class="timeBar-box"
                 v-model="selectTime"
                 :="timeBarConfig"
                 @change-bar="timeBarChange"
                 @point-click="timeBarPoint" />
    </div>
    <div class="tl-panel">
        <div class="fun-box">
            <div class="header">气象要素</div>
            <div class="main">
                <span v-for="item in ['降水', '气温', '风', '能见度', '日照']"
                      :key="item"
                      :class="{ active: metElement == item }"
                      @click="handleMetElement(item)">{{ item }}</span>
            </div>
        </div>
        <div class="fun-box">
            <div class="header">站点</div>
            <div class="main">
                <span v-for="item in ['示范点', '牧场', '自动站']"
                      :key="item"
                      :class="{ active: metElement == item }"
                      @click="handleMetElement(item)">{{ item }}</span>
            </div>
        </div>
        <div class="fun-box">
            <div class="header">展示方式
                <div class="header-right"
                     v-if="showStationLayer">
                    <el-checkbox v-model="showStationName"
                                 label="显示站名"
                                 border
                                 @change="handleShowStationNameValue" />
                    <el-checkbox v-model="showStationValue"
                                 label="显示站值"
                                 border
                                 @change="handleShowStationNameValue" />
                </div>
            </div>
            <div class="main">
                <span :class="{ active: showStationLayer }"
                      @click="handleStationLayer">站点</span>
                <span :class="{ active: showGridLayer }"
                      @click="handleGridLayer">色斑图</span>
                <span v-if="metElement == '风'"
                      :class="{ active: showWinFieldLayer }"
                      @click="handleWinFieldLayer">风场</span>
            </div>
        </div>
        <div class="fun-box">
            <div class="header">信息统计
            </div>
            <div class="main">
                <span :class="{ active: showStatisticalLayer }"
                      @click="handleStatisticalLayer">统计表</span>
            </div>
        </div>

    </div>


    <div class="station-table-chart"
         v-if="showTCSta">
        <div class="close"
             @click="showTCSta = false">
            <svg>
                <use xlink:href="#icon-close"></use>
            </svg>
        </div>
        <div class="info-box">
            <div class="header">气象要素信息统计
                <span style="font-size: 14px;">
                    （ {{ selectTime[0].format("YYYY-MM-DD HH:mm:00") + " 至 " + selectTime[1].format("YYYY-MM-DD HH:mm:00") }} ）</span>
            </div>
            <div class="main">
                <div>自动站：{{ tcStaInfo.自动站 }}</div>
                <div>累积降水：{{ tcStaInfo.累积降水 ?? "--" }} mm</div>
                <div>平均降水：{{ tcStaInfo.平均降水 ?? "--" }} mm/h</div>
                <div>最高温度：{{ tcStaInfo.最高温度 ?? "--" }} ℃</div>
                <div>最低温度：{{ tcStaInfo.最低温度 ?? "--" }} ℃</div>
                <div>平均温度：{{ tcStaInfo.平均温度 ?? "--" }} ℃</div>
                <div>最大风速：{{ tcStaInfo.最大风速 ?? "--" }} m/s</div>
                <div>最小风速：{{ tcStaInfo.最小风速 ?? "--" }} m/s</div>
            </div>
        </div>
        <div class="tc-box">
            <TableChart :="tcStaOpt"
                        v-model="tcStaData" />
        </div>
    </div>




    <el-dialog class="my-dialog"
               v-model="showStatisticalLayer"
               width="1800"
               z-index="99999"
               align-center
               destroy-on-close
               close-icon=""
               @close="showStatisticalLayer = false">

        <el-segmented class="my-segmented"
                      v-model="segmentedMetElement"
                      :options="['降水', '气温', '风']"
                      @change="getStationDataByTime" />
        <TableChart style="height: 600px;"
                    v-model="tcAllStaData"
                    :="tcAllStaOpt" />
    </el-dialog>

    <!-- <div class="legend-panel">
        <Legend v-if="showPreLegend"
                :choose="preLegendType + '小时降水'"
                :filter="preFilterRange"
                :filterChange="preFilterChange" />
        <Legend v-if="showTemLegend"
                choose="气温"
                :filter="temFilterRange"
                :filterChange="temFilterChange" />
        <Legend v-if="showWinLegend"
                choose="风"
                :filter="winFilterRange"
                :filterChange="winFilterChange" />
    </div> -->

</template>

<script setup>
import { ref, onMounted, computed, shallowRef } from "vue";
import axios from "axios";
import dayjs from "dayjs";
import "dayjs/locale/zh-cn";
import locale from "ant-design-vue/es/date-picker/locale/zh_CN";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import "leaflet-velocity/dist/leaflet-velocity.css";
import "leaflet-velocity";
import "leaflet.glify";
import * as turf from "@turf/turf";
import { ElMessage } from "element-plus"

import Legend from "@/components/my-legend/Legend.vue";
import TimeBar from "@/components/my-time-bar/TimeBar.vue";
import MapInfoBar from "@/components/my-map-info-bar/MapInfoBar.vue";
import TableChart from "@/components/table-chart/index.vue";

import { antDisabledDate, antDisabledDateTime, idwToGrid } from "@/components/utils.js";
import interfaces from "@/components/interfaces.js";
import ZiDongZhan from "@/assets/GeographicData/自动站.json"
import ShiBianJie from "@/assets/GeographicData/吴忠市级边界.json";
// import FengChang from "@/assets/GeographicData/风场.json";

// import xx from "@/views/mcsk/xxx.json";
// let pppp = [0, 2, 4, 3, 1, 0, 2, 4, 3, 1, 0, 2, 4, 3, 1, 0, 2, 4, 3, 1, 0, 2, 4, 3, 1, 0, 2, 4, 3, 1, 0, 2, 4, 3, 1, 0, 2, 4, 3, 1, 0, 2, 4, 3, 1,]
// let x = []
// for (const key in xx) {
//     let element = xx[key];
//     element.forEach((v, i) => {
//         let idx = Math.floor(i / 24);
//         x.push({
//             Station_Id_C: key,
//             PRE_1h: v > 9999
//                 ? v
//                 : v - 0.8 > 0
//                     ? (v - 0.8).toFixed(1) * 1
//                     : 0,
//             Datetime: dayjs("2025-05-01 09:00:00").add(i, "h").format("YYYY-MM-DD HH:00:00"),
//             TEM: getRandomNumber(28 + pppp[idx], 37 + pppp[idx], 1),
//             WIN_S: getRandomNumber(2 + pppp[idx], 10 + pppp[idx], 1),
//             WIN_D: getRandomNumber(30 + pppp[idx] * 30, 40 + pppp[idx] * 30, 1),
//             RHU: getRandomNumber(60 + pppp[idx] * 2, 80 + pppp[idx] * 2, 0),
//         })
//     })
// }
// axios({
//     method: "post",
//     url: interfaces.serviceUrl + "db/insertBatch",
//     data: {
//         table: "t_tq",
//         collection: x
//     }
// }).then(response => {
//     console.log(response.data)
// })
// function getRandomNumber(min, max, decimal) {
//     return decimal ? (Math.random() * (max - min) + min).toFixed(decimal) * 1 : Math.floor(Math.random() * (max - min + 1) + min);
// }

// axios({
//     method: "get",
//     url: interfaces.serviceUrl + "db/select",
//     params: {
//         table: "t_tq_hour",
//         group: "Station_Id_C"
//     }
// }).then(response => {
//     let data = response.data.data;
//     let zzz = []
//     for (let i = 0; i < data.length; i++) {
//         const element = data[i];
//         let p = element.data.map(v => v.PRE_1h)
//         let d = element.data.map(v => v.WIN_D)
//         let s = element.data.map(v => v.WIN_S)
//         let r = element.data.map(v => v.RHU)
//         let t = element.data.map(v => v.TEM)
//         let pp = sumGroups(p, 24).map(v => v.toFixed(1) * 1 > 9999 ? 999999 : v.toFixed(1) * 1);
//         let dd = sumGroups(d, 24).map(v => (v / 24).toFixed(1) * 1);
//         let ss = sumGroups(s, 24).map(v => (v / 24).toFixed(1) * 1);
//         let rr = sumGroups(r, 24).map(v => (v / 24).toFixed(1) * 1);
//         let tt = sumGroups(t, 24).map(v => (v / 24).toFixed(1) * 1);
//         for (let j = 0; j < pp.length; j++) {
//             zzz.push({
//                 Station_Id_C: element.val, Datetime: dayjs("2025-05-01 00:00:00").add(i, "d").format("YYYY-MM-DD HH:00:00"),
//                 PRE: pp[j],
//                 WIN_D: dd[j],
//                 WIN_S: ss[j],
//                 RHU: rr[j],
//                 TEM: tt[j],
//             })
//         }
//     }
//     axios({
//         method: "post",
//         url: interfaces.serviceUrl + "db/insertBatch",
//         data: {
//             table: "t_tq_day",
//             collection: zzz
//         }
//     }).then(response => {
//         console.log(response.data)
//     })
// })

// function sumGroups(arr, groupSize) {
//     return arr.reduce((acc, curr, index) => {
//         const groupIndex = Math.floor(index / groupSize);
//         acc[groupIndex] = (acc[groupIndex] || 0) + curr;
//         return acc;
//     }, []);
// }







let refmap = shallowRef(null),
    map = null,
    metElement = ref(""),
    segmentedMetElement = ref("降水"),
    showAntOpenChange = ref(false),
    showStationName = ref(false),
    showStationValue = ref(true),

    showStationLayer = ref(true),
    showStatisticalLayer = ref(false),
    showGridLayer = ref(false),
    showWinFieldLayer = ref(false),

    showPreLegend = computed(() => (showStationLayer.value || showGridLayer.value) && metElement.value == "降水"),
    showTemLegend = computed(() => (showStationLayer.value || showGridLayer.value) && metElement.value == "气温"),
    showWinLegend = computed(() => (showStationLayer.value || showGridLayer.value || showWinFieldLayer.value) && metElement.value == "风"),
    preFilterRange = [0, 7],
    temFilterRange = [0, 19],
    winFilterRange = [0, 13],

    showTCSta = ref(false),
    tcStaOpt = ref({}),
    tcStaData = ref([]),
    tcStaInfo = ref({}),
    showTCAllSta = ref(true),
    tcAllStaOpt = ref({}),
    tcAllStaData = ref([]),
    GLOBAL_CACHE = {};

let selectTime = ref(dayjs().set("hour", 8).set("minute", 0).set("second", 0));
selectTime.value = dayjs("2025-05-09 08:00:00")
let timeBarPointChecked = ref(0),
    timeBarConfig = ref({
        bar: "时",
        dateFormat: "YYYY-MM-DD HH",
        unit: "hour",
        difference: -23,
        points: 24,
        step: 1,
        pointChecked: 24,
    }),
    timeBarChange = (type) => {
        timeBarConfig.value = type == "时" ? {
            bar: "时",
            dateFormat: "YYYY-MM-DD HH",
            unit: "hour",
            difference: -23,
            points: 24,
            step: 1,
            pointChecked: 24,
        } : {
            bar: "日",
            dateFormat: "YYYY-MM-DD",
            unit: "day",
            difference: -6,
            points: 7,
            step: 1,
            pointChecked: 7,
        };
    };
function timeBarPoint(obj) {
    timeBarPointChecked.value = obj.point - 1;
    // let key = selectTime.value.format("YYYYMMDDHH0000");
    // renderStation(GLOBAL_CACHE[key]);
}


// 等级
const
    preLegendColor = ["#ffffff", "#a3f28f", "#3dba3d", "#61b8ff", "#0500e1", "#fa00fa"],
    winLegendColor = ["#ffffff", "#9bbce8", "#6b9de1", "#3b7edb", "#2b5cc2", "#049122", "#edca0a", "#fe9900", "#fe7500", "#fe5500", "#fe2a00", "#fa0025", "#f00087"],
    temLegendColor = ["#800080", "#00309e", "#185ab4", "#2073dc", "#41a0e6", "#72d2fa", "#96e2f0", "#befaff", "#f0faff", "#dcffd5", "#c0feb3", "#b4ff82", "#fafa8f", "#fff2c0", "#ffdfb0", "#ffae76", "#ff878a", "#ff5600", "#8f0814"],
    preLegendType = computed(() => {
        let hour = timeBarConfig.value.bar == "时" ? 1 : 24;
        return hour <= 1 ? 1 :
            hour <= 3 ? 3 :
                hour <= 6 ? 6 :
                    hour <= 12 ? 12 : 24;
    }),
    preThreshold = computed(() => {
        let threshold =
            preLegendType.value == 1 ? [0.1, 1.6, 7, 15, 40, 50] :
                preLegendType.value == 3 ? [0.1, 3, 10, 20, 50, 70] :
                    preLegendType.value == 6 ? [0.1, 4, 13, 25, 60, 120] :
                        preLegendType.value == 12 ? [0.1, 5, 15, 30, 70, 140] : [0.1, 10, 25, 50, 100, 250];
        return [
            { id: 0, color: preLegendColor[0], threshold: null, name: "无" },
            { id: 1, color: preLegendColor[1], threshold: threshold[0], name: "小雨" },
            { id: 2, color: preLegendColor[2], threshold: threshold[1], name: "中雨" },
            { id: 3, color: preLegendColor[3], threshold: threshold[2], name: "大雨" },
            { id: 4, color: preLegendColor[4], threshold: threshold[3], name: "暴雨" },
            { id: 5, color: preLegendColor[5], threshold: threshold[4], name: "大暴雨" },
            { id: 6, color: preLegendColor[6], threshold: threshold[5], name: "特大暴雨" }]
    }),
    winThreshold = ref([
        { id: 0, color: winLegendColor[0], threshold: 0, name: "0级" },
        { id: 1, color: winLegendColor[1], threshold: 0.3, name: "1级" },
        { id: 2, color: winLegendColor[2], threshold: 1.6, name: "2级" },
        { id: 3, color: winLegendColor[3], threshold: 3.4, name: "3级" },
        { id: 4, color: winLegendColor[4], threshold: 5.5, name: "4级" },
        { id: 5, color: winLegendColor[5], threshold: 8.0, name: "5级" },
        { id: 6, color: winLegendColor[6], threshold: 10.8, name: "6级" },
        { id: 7, color: winLegendColor[7], threshold: 13.9, name: "7级" },
        { id: 8, color: winLegendColor[8], threshold: 17.2, name: "8级" },
        { id: 9, color: winLegendColor[9], threshold: 20.8, name: "9级" },
        { id: 10, color: winLegendColor[10], threshold: 24.5, name: "10级" },
        { id: 11, color: winLegendColor[11], threshold: 28.5, name: "11级" },
        { id: 12, color: winLegendColor[12], threshold: 32.7, name: "12级" },
    ]),
    temThreshold = ref([
        { id: 0, color: temLegendColor[0], threshold: null },
        { id: 1, color: temLegendColor[1], threshold: -32 },
        { id: 2, color: temLegendColor[2], threshold: -28 },
        { id: 3, color: temLegendColor[3], threshold: -24 },
        { id: 4, color: temLegendColor[4], threshold: -20 },
        { id: 5, color: temLegendColor[5], threshold: -16 },
        { id: 6, color: temLegendColor[6], threshold: -12 },
        { id: 7, color: temLegendColor[7], threshold: -8 },
        { id: 8, color: temLegendColor[8], threshold: -4 },
        { id: 9, color: temLegendColor[9], threshold: 0 },
        { id: 10, color: temLegendColor[10], threshold: 4 },
        { id: 11, color: temLegendColor[11], threshold: 8 },
        { id: 12, color: temLegendColor[12], threshold: 12 },
        { id: 13, color: temLegendColor[13], threshold: 16 },
        { id: 14, color: temLegendColor[14], threshold: 20 },
        { id: 15, color: temLegendColor[15], threshold: 24 },
        { id: 16, color: temLegendColor[16], threshold: 28 },
        { id: 17, color: temLegendColor[17], threshold: 32 },
        { id: 18, color: temLegendColor[18], threshold: 35 },
    ]);
function getPreThreshold(value) {
    return value < preThreshold.value[1].threshold ? preThreshold.value[0] :
        value < preThreshold.value[2].threshold ? preThreshold.value[1] :
            value < preThreshold.value[3].threshold ? preThreshold.value[2] :
                value < preThreshold.value[4].threshold ? preThreshold.value[3] :
                    value < preThreshold.value[5].threshold ? preThreshold.value[4] :
                        value < preThreshold.value[6].threshold ? preThreshold.value[5] : preThreshold.value[6];
}
function getWinThreshold(value) {
    return value < winThreshold.value[1].threshold ? winThreshold.value[0] :
        value < winThreshold.value[2].threshold ? winThreshold.value[1] :
            value < winThreshold.value[3].threshold ? winThreshold.value[2] :
                value < winThreshold.value[4].threshold ? winThreshold.value[3] :
                    value < winThreshold.value[5].threshold ? winThreshold.value[4] :
                        value < winThreshold.value[6].threshold ? winThreshold.value[5] :
                            value < winThreshold.value[7].threshold ? winThreshold.value[6] :
                                value < winThreshold.value[8].threshold ? winThreshold.value[7] :
                                    value < winThreshold.value[9].threshold ? winThreshold.value[8] :
                                        value < winThreshold.value[10].threshold ? winThreshold.value[9] :
                                            value < winThreshold.value[11].threshold ? winThreshold.value[10] :
                                                value < winThreshold.value[12].threshold ? winThreshold.value[11] : winThreshold.value[12];
}
function getTemThreshold(value) {
    return value < temThreshold.value[1].threshold ? temThreshold.value[0] :
        value < temThreshold.value[2].threshold ? temThreshold.value[1] :
            value < temThreshold.value[3].threshold ? temThreshold.value[2] :
                value < temThreshold.value[4].threshold ? temThreshold.value[3] :
                    value < temThreshold.value[5].threshold ? temThreshold.value[4] :
                        value < temThreshold.value[6].threshold ? temThreshold.value[5] :
                            value < temThreshold.value[7].threshold ? temThreshold.value[6] :
                                value < temThreshold.value[8].threshold ? temThreshold.value[7] :
                                    value < temThreshold.value[9].threshold ? temThreshold.value[8] :
                                        value < temThreshold.value[10].threshold ? temThreshold.value[9] :
                                            value < temThreshold.value[11].threshold ? temThreshold.value[10] :
                                                value < temThreshold.value[12].threshold ? temThreshold.value[11] :
                                                    value < temThreshold.value[13].threshold ? temThreshold.value[12] :
                                                        value < temThreshold.value[14].threshold ? temThreshold.value[13] :
                                                            value < temThreshold.value[15].threshold ? temThreshold.value[14] :
                                                                value < temThreshold.value[16].threshold ? temThreshold.value[15] :
                                                                    value < temThreshold.value[17].threshold ? temThreshold.value[16] :
                                                                        value < temThreshold.value[18].threshold ? temThreshold.value[17] : temThreshold.value[18];
}



onMounted(() => {
    refmap.value = map = L.map("map-container", {
        center: [37.42, 106.6],
        minZoom: 3,
        maxZoom: 18,
        zoom: 9,
        zoomControl: false,
        doubleClickZoom: false,
        attributionControl: false,
        // renderer: L.canvas()
    })
    // .on("click", function (e) {
    //     xxx.value.push([e.latlng.lng, e.latlng.lat])
    //     removeLayerById("地图画线");
    //     GLOBAL_CACHE["地图画线"] = L.geoJSON([{
    //         "type": "Feature",
    //         "geometry": {
    //             "type": "LineString",
    //             "coordinates": xxx.value
    //         },
    //     }], {
    //         style: function (feature) {
    //             return {
    //                 color: "red",
    //                 weight: 2
    //             };
    //         },
    //     }).addTo(map);
    //     console.log(xxx.value)
    // })
    // .on("contextmenu", function (e) {
    //     xxx.value.pop();
    //     removeLayerById("地图画线");
    //     GLOBAL_CACHE["地图画线"] = L.geoJSON([{
    //         "type": "Feature",
    //         "geometry": {
    //             "type": "LineString",
    //             "coordinates": xxx.value
    //         },
    //     }], {
    //         style: function (feature) {
    //             return {
    //                 color: "red",
    //                 weight: 2
    //             };
    //         },
    //     }).addTo(map); console.log(xxx.value)
    // })

});
// ---------- 展示方式 ----------
function handleWinFieldLayer() {
    showWinFieldLayer.value = !showWinFieldLayer.value;
    if (showWinFieldLayer.value) {
        getWinStationData();
    } else {
        removeLayerById("current-winfield-layer");
    }
}
function handleStationLayer() {
    showStationLayer.value = !showStationLayer.value;
    if (showStationLayer.value) {
        if (metElement.value == "降水") getPreStationData();
        if (metElement.value == "气温") getTemStationData();
        if (metElement.value == "风") getWinStationData();
    } else {
        removeLayerById("current-station-layer");
    }
}
function handleGridLayer() {
    showGridLayer.value = !showGridLayer.value;
    if (showGridLayer.value) {
        if (metElement.value == "降水") getPreStationData();
        if (metElement.value == "气温") getTemStationData();
        if (metElement.value == "风") getWinStationData();
    } else {
        removeLayerById("current-grid-layer");
    }
}
function handleStatisticalLayer() {
    showStatisticalLayer.value = !showStatisticalLayer.value;
    if (showStatisticalLayer.value) {
        getStationDataByTime();
    }
}

// ---------- 切换气象要素 ----------
function handleMetElement(data) {
    if (metElement.value != data) {
        metElement.value = data;
        if (showStationLayer.value || showGridLayer.value || showWinFieldLayer.value) {
            if (data == "降水") getPreStationData();
            if (data == "气温") getTemStationData();
            if (data == "风") getWinStationData();
        }
    }
}

// ---------- 显示站值、站名 ----------
function handleShowStationNameValue(checked) {
    if (showStationLayer.value) { renderStationLayer(GLOBAL_CACHE.curStationData); }
}
// ---------- 降水过滤 ----------
function preFilterChange(data) {
    preFilterRange = data;
    if (showStationLayer.value) { renderStationLayer(GLOBAL_CACHE.curStationData); }
    if (showGridLayer.value) { renderGridLayer(GLOBAL_CACHE.curGridData); }
}
// ---------- 温度过滤 ----------
function temFilterChange(data) {
    temFilterRange = data;
    if (showStationLayer.value) { renderStationLayer(GLOBAL_CACHE.curStationData); }
    if (showGridLayer.value) { renderGridLayer(GLOBAL_CACHE.curGridData); }
}
// ---------- 风过滤 ----------
function winFilterChange(data) {
    winFilterRange = data;
    if (showStationLayer.value) { renderStationLayer(GLOBAL_CACHE.curStationData); }
    if (showGridLayer.value) { renderGridLayer(GLOBAL_CACHE.curGridData); }
}


// ---------- 获取站点数据 ----------
// getPreStationData()
function getPreStationData() {
    axios({
        method: "get",
        url: interfaces.serviceUrl + "db/select",
        params: {
            table: "t_tq_pre_hour",
            type: "=",
            field: "Datetime",
            value: selectTime.value.format("YYYY-MM-DD HH:00:00"),
        }
    }).then(response => {
        let data = response.data.data;
        if (data.length == 0) {
            removeLayerById("current-station-layer");
            ElMessage({
                showClose: true,
                message: "无数据",
                offset: document.documentElement.clientHeight / 2
            })
        } else {
            let stations = structuredClone(ZiDongZhan);
            // 数据绑定到设备
            for (let sta of stations) {
                let item = data.find(v => sta.sid == v.Station_Id_C);
                sta.val = item?.PRE_1h ?? "--";
                sta.color = sta.val == "--" ? getPreThreshold(0).color : getPreThreshold(sta.val).color;
            }
            GLOBAL_CACHE.curData = stations;
            if (showStationLayer.value) {
                GLOBAL_CACHE.curStationData = stations;
                renderStationLayer(stations);
            }
            if (showGridLayer.value) {
                // 插值
                let idwPoints = stations.filter(v => v.val != "--");
                let idwGrid = idwToGrid(idwPoints, { startLon: 105.55, startLat: 36.55, lonStep: 0.005, latStep: 0.005, lonCount: 425, latCount: 345 }, 50, Infinity, { valField: "val" });
                GLOBAL_CACHE.curGridData = idwGrid;
                renderGridLayer(idwGrid);
            }
        }
    })
}
function getTemStationData() {
    axios({
        method: "get",
        url: interfaces.serviceUrl + "db/select",
        params: {
            table: "t_tq_tem",
            type: "=",
            field: "Datetime",
            value: selectTime.value.format("YYYY-MM-DD HH:mm:00"),
        }
    }).then(response => {
        let data = response.data.data;
        if (data.length == 0) {
            removeLayerById("current-station-layer");
            ElMessage({
                showClose: true,
                message: "无数据",
                offset: document.documentElement.clientHeight / 2
            })
        } else {
            let stations = structuredClone(ZiDongZhan);
            // 数据绑定到设备
            for (let sta of stations) {
                let item = data.find(v => sta.sid == v.Station_Id_C);
                sta.val = item?.TEM ?? "--";
                sta.color = sta.val == "--" ? getTemThreshold(0).color : getTemThreshold(sta.val).color;
            }
            GLOBAL_CACHE.curData = stations;
            if (showStationLayer.value) {
                GLOBAL_CACHE.curStationData = stations;
                renderStationLayer(stations);
            }
            if (showGridLayer.value) {
                // 插值
                let idwPoints = stations.filter(v => v.val != "--");
                let idwGrid = idwToGrid(idwPoints, { startLon: 105.55, startLat: 36.55, lonStep: 0.005, latStep: 0.005, lonCount: 425, latCount: 345 }, 50, Infinity, { valField: "val" });
                GLOBAL_CACHE.curGridData = idwGrid;
                renderGridLayer(idwGrid);
            }
        }
    })
}
function getWinStationData() {
    axios({
        method: "get",
        url: interfaces.serviceUrl + "db/select",
        params: {
            table: "t_tq_win",
            type: "=",
            field: "Datetime",
            value: selectTime.value.format("YYYY-MM-DD HH:mm:00"),
        }
    }).then(response => {
        let data = response.data.data;
        if (data.length == 0) {
            removeLayerById("current-station-layer");
            ElMessage({
                showClose: true,
                message: "无数据",
                offset: document.documentElement.clientHeight / 2
            })
        } else {
            let stations = structuredClone(ZiDongZhan);
            // 数据绑定到设备
            for (let sta of stations) {
                let item = data.find(v => sta.sid == v.Station_Id_C);
                sta.val = item?.WIN_S ?? "--";
                sta.d = item?.WIN_D ?? 0;
                sta.v = sta.val == "--" ? item.WIN_S * Math.cos(Math.PI / 180 * item.WIN_D) : 0;
                sta.u = sta.val == "--" ? item.WIN_S * Math.sin(Math.PI / 180 * item.WIN_D) : 0;
                sta.color = sta.val == "--" ? getWinThreshold(0).color : getWinThreshold(sta.val).color;
            }
            GLOBAL_CACHE.curData = stations;
            if (showStationLayer.value) {
                GLOBAL_CACHE.curStationData = stations;
                renderStationLayer(stations);
            }
            if (showGridLayer.value || showWinFieldLayer.value) {
                // 插值
                let idwPoints = stations.filter(v => v.val != "--");
                let idwGrid = idwToGrid(idwPoints, { startLon: 105.55, startLat: 36.55, lonStep: 0.005, latStep: 0.005, lonCount: 425, latCount: 345 }, 50, Infinity, { valField: "val" });
                if (showGridLayer.value) {
                    GLOBAL_CACHE.curGridData = idwGrid;
                    renderGridLayer(idwGrid);
                }
                if (showWinFieldLayer.value) {
                    let wf = [];
                    wf = FengChang;
                    GLOBAL_CACHE.curWinFieldData = wf;
                    renderWinFieldLayer(wf);
                }
            }
        }
    })
}
// ---------- 获取1个站点的多个气象要素数据，渲染弹窗 ----------
function getStationDataByIdAndTime(station) {
    let timeRange = timeBarConfig.value.bar == "时"
        ? [selectTime.value.add(-24, "hour").format("YYYY-MM-DD HH:00:00"), selectTime.value[0].format("YYYY-MM-DD HH:00:00")]
        : [selectTime.value.add(-7, "day").format("YYYY-MM-DD 00:00:00"), selectTime.value[0].format("YYYY-MM-DD 00:00:00")];
    let where = [
        { type: "=", field: "Station_Id_C", value: station.sid },
        { type: "between", field: "Datetime", leftValue: selectTime.value[0].format("YYYY-MM-DD HH:mm:00"), rightValue: selectTime.value[1].format("YYYY-MM-DD HH:mm:00") },
    ];
    Promise.all([
        axios.post(interfaces.serviceUrl + "db/select", { table: "t_tq_pre_hour", where: where }),
        axios.post(interfaces.serviceUrl + "db/select", { table: "t_tq_tem", where: where }),
        axios.post(interfaces.serviceUrl + "db/select", { table: "t_tq_win", where: where })
    ]).then(responses => {
        let preData = responses[0].data.data;
        let temData = responses[1].data.data;
        let winData = responses[2].data.data;

        let timeDiff = selectTime.value[1].diff(selectTime.value[0], "hour") + 1;
        let timeSeries = Array.from({ length: timeDiff }, (v, i) => selectTime.value[0].add(i, "hour").format("YYYY-MM-DD HH:mm:00"));

        let pres = Array(timeDiff).fill(null);
        let tems = Array(timeDiff).fill(null);
        let winss = Array(timeDiff).fill(null);
        let winds = Array(timeDiff).fill(null);
        for (let i = 0; i < timeDiff; i++) {
            let pIndex = timeSeries.indexOf(preData[i]?.Datetime);
            let tIndex = timeSeries.indexOf(temData[i]?.Datetime);
            let wIndex = timeSeries.indexOf(winData[i]?.Datetime);
            if (pIndex != -1 && preData[i].PRE_1h < 999) {
                pres[pIndex] = preData[i].PRE_1h * 1;
            }
            if (tIndex != -1 && temData[i].TEM < 999) {
                tems[tIndex] = temData[i].TEM * 1;
            }
            if (wIndex != -1 && winData[i].WIN_S < 999) {
                winss[wIndex] = winData[i].WIN_S * 1;
                winds[wIndex] = winData[i].WIN_D * 1;
            }
        }
        tcStaInfo.value = {
            "自动站": station.short_name,
            "累积降水": (pres.reduce((acc, cur) => acc + cur, 0)).toFixed(1) * 1,
            "平均降水": (pres.reduce((acc, cur) => acc + cur, 0) / pres.length).toFixed(1) * 1,
            "最高温度": Math.max(...tems),
            "最低温度": Math.min(...tems),
            "平均温度": (tems.reduce((acc, cur) => acc + cur, 0) / tems.length).toFixed(1) * 1,
            "最大风速": Math.max(...winss),
            "最小风速": Math.min(...winss),
        }
        tcStaData.value = Array.from({ length: timeDiff }, (v, i) => ({
            time: timeSeries[i],
            pre: pres[i],
            tem: tems[i],
            wins: winss[i],
            wind: winds[i],
        }));
        tcStaOpt.value = {
            title: station.short_name,
            toolbar: [
                { name: "图表转换", mode: true },
                { name: "行列转换", mode: true },
                // { name: "编辑表格", mode: false },
                { name: "导出表格" }
            ],
            column: [
                { width: "140px", prop: "time", label: "时间", axis: "x", isHeader: true, disable: true, format: (v) => dayjs(v).format("MM-DD HH:mm"), sortable: true },
                { prop: "pre", label: "降水", unit: "mm/h", axis: "y", sortable: true },
                { prop: "tem", label: "气温", unit: "℃", axis: "y", sortable: true },
                { prop: "wins", label: "风速", unit: "m/s", axis: "y", sortable: true },
                // { prop: "wind", label: "风向", unit: "", axis: "y" },
            ],
        };
        showTCSta.value = true;
    }).catch((response) => {
        // if (response.status == 404) {
        //     GLOBAL_CACHE.curData = null;
        //     removeLayerById("ybyj-current-layer");
        //     ElMessage({
        //         showClose: true,
        //         message: "文件不存在",
        //         type: "error",
        //         offset: document.documentElement.clientHeight / 2
        //     })
        // }
    });
}
// ---------- 获取所有站点的1个气象要素数据，渲染弹窗 ----------
function getStationDataByTime() {
    let where = [
        { type: "between", field: "Datetime", leftValue: selectTime.value[0].format("YYYY-MM-DD HH:mm:00"), rightValue: selectTime.value[1].format("YYYY-MM-DD HH:mm:00") },
    ];

    axios.post(interfaces.serviceUrl + "db/select", {
        table: segmentedMetElement.value == "降水" ? "t_tq_pre_hour" : segmentedMetElement.value == "气温" ? "t_tq_tem" : "t_tq_win",
        where: where
    }).then(responses => {
        let data = responses.data.data;
        let timeDiff = selectTime.value[1].diff(selectTime.value[0], "hour") + 1;
        let timeSeries = Array.from({ length: timeDiff }, (v, i) => selectTime.value[0].add(i, "hour").format("YYYY-MM-DD HH:mm:00"));
        let k = segmentedMetElement.value == "降水" ? "PRE_1h" : segmentedMetElement.value == "气温" ? "TEM" : "WIN_S";
        let stations = structuredClone(ZiDongZhan);
        // 数据绑定到设备
        for (let sta of stations) {
            let dataSeries = Array(timeDiff).fill(null);
            for (let item of data) {
                if (sta.sid == item.Station_Id_C) {
                    // 使用 index 防止时间序列缺失
                    let index = timeSeries.indexOf(item.Datetime);
                    if (index != -1 && item[k] < 999) {
                        dataSeries[index] = item[k] * 1;
                    }
                }
            }
            sta.dataSeries = dataSeries;
        }
        tcAllStaData.value = Array.from({ length: timeDiff }, (v, i) => {
            let obj = { time: timeSeries[i] };
            stations.forEach(sta => { obj[sta.short_name] = sta.dataSeries[i]; });
            return obj;
        });
        tcAllStaOpt.value = {
            title: "",
            toolbar: [
                { name: "图表转换", mode: false },
                { name: "行列转换", mode: false },
                // { name: "编辑表格", mode: false },
                { name: "导出表格" }
            ],
            column: [
                { width: "120", prop: "time", label: "时间", axis: "x", isHeader: true, disable: true, format: (v) => dayjs(v).format("MM-DD HH:mm") },
                ...ZiDongZhan.map(v => ({ prop: v.short_name, label: v.short_name, unit: "", axis: "y", sortable: true }))
            ],
            export: {
                name: segmentedMetElement.value
            }
        };
        showTCAllSta.value = true;

    })
}

// ---------- 渲染站点图层 ----------
function renderStationLayer(data) {
    let filterArray = [];
    if (metElement.value == "降水") {
        filterArray = preLegendColor.slice(preFilterRange[0], preFilterRange[1]);
    } else if (metElement.value == "气温") {
        filterArray = temLegendColor.slice(temFilterRange[0], temFilterRange[1]);
    } else if (metElement.value == "风") {
        filterArray = winLegendColor.slice(winFilterRange[0], winFilterRange[1]);
    }
    var layers = [];
    for (let i = 0, len = data.length; i < len; i++) {
        let item = data[i];
        // 过滤
        let filter = filterArray.includes(item.color);
        // console.log(filterArray, item.color)
        if (filter) {
            layers.push(
                L.marker([item.lat, item.lon], {
                    icon: L.divIcon({
                        className: "my-marker-box",
                        iconSize: [0, 0],
                        html: `
                        <div class="my-marker${showStationValue.value ? ' show-value' : ''}${showStationName.value ? ' show-name' : ''}">
                            <div class="icon">${getIcon(item.color, item.val, item.d)}</div>
                            <div class="shade">
                                <span class="val">${item.val}</span>
                                <span class="name">${item.short_name}</span>
                            </div>
                        </div>`,
                    }),
                    riseOnHover: true,
                    item: item,
                }).on("click", function (e) {
                    // renderPreStationPopup(e.target.options.item);
                    getStationDataByIdAndTime(item);
                })
            )
        }
    }

    removeLayerById("current-station-layer");
    GLOBAL_CACHE["current-station-layer"] = L.layerGroup(layers).addTo(map);
}
// ---------- 渲染站点弹窗 ----------
function renderStationPopup() {

}
// ---------- 渲染格点图层 ----------
function renderGridLayer(data) {
    let filterArray = [];
    let funThreshold;
    if (metElement.value == "降水") {
        filterArray = preLegendColor.slice(preFilterRange[0], preFilterRange[1]);
        funThreshold = getPreThreshold;
    } else if (metElement.value == "气温") {
        filterArray = temLegendColor.slice(temFilterRange[0], temFilterRange[1]);
        funThreshold = getTemThreshold;
    } else if (metElement.value == "风") {
        filterArray = winLegendColor.slice(winFilterRange[0], winFilterRange[1]);
        funThreshold = getWinThreshold;
    }
    let startLon = data.startLon, startLat = data.startLat,
        lonStep = data.lonStep, latStep = data.latStep,
        lonCount = data.lonCount, latCount = data.latCount,
        DS = data.DS;
    let polygon = turf.feature(ShiBianJie.features[0].geometry);
    let features = [];
    for (let y = 0; y < latCount; y++) {
        for (let x = 0; x < lonCount; x++) {
            let lon = startLon + lonStep * (x + 0.5),
                lat = startLat + latStep * (y + 0.5),
                val = DS[y][x];
            // 裁剪
            let point = turf.point([lon, lat]);
            if (turf.booleanPointInPolygon(point, polygon)) {
                let config = funThreshold(val);
                // 过滤
                let filter = filterArray.includes(config.color);
                if (filter) {
                    features.push({
                        "type": "Feature",
                        "properties": { "color": config.color },
                        "geometry": {
                            "type": "Polygon", "coordinates": [
                                [
                                    [startLon + lonStep * x, startLat + latStep * y],
                                    [startLon + lonStep * (x + 1), startLat + latStep * y],
                                    [startLon + lonStep * (x + 1), startLat + latStep * (y + 1)],
                                    [startLon + lonStep * x, startLat + latStep * (y + 1)],
                                    [startLon + lonStep * x, startLat + latStep * y],
                                ]
                            ]
                        }
                    })
                }
            }
        }
    };
    removeLayerById("current-grid-layer");
    GLOBAL_CACHE["current-grid-layer"] = L.glify.shapes({
        map: map,
        data: { "type": "FeatureCollection", "features": features },
        // border: true,
        color: (index, feature) => {
            let hexColor = feature.properties.color.replace("#", "");
            let num = parseInt(hexColor, 16);
            let r = (num >> 16) & 255;
            let g = (num >> 8) & 255;
            let b = num & 255;
            return { r: r / 255, g: g / 255, b: b / 255, a: 0.8 }
        },
        // click: (e, feature) => {
        //     console.log(e, feature)
        // },
        // hover: (e, feature) => {
        //     console.log(e, feature)
        // },
        // hoverOff: (e, feature) => {
        //     console.log(e, feature)
        // }
    });
}
// ---------- 渲染风场图层 ----------
function renderWinFieldLayer(data) {
    console.log(data)
    var velocityLayer = L.velocityLayer({
        data: data,
        colorScale: winLegendColor,
        colorIndex: (speed) => {// 该属性为后添加属性
            return getWinThreshold(speed).id;
        },
        displayValues: false,
        // displayOptions: {
        //     velocityType: "风速",       // 标签前缀
        //     position: "bottomleft",     // leaflet 控件位置
        //     emptyString: "无速度数据",   // 鼠标处无数据
        //     angleConvention: "meteo",   // 风向计算方式。可选['bearing','meteo','CCW','CW'] bearing：吹去的方向；meteo：吹来的方向；CCW ：与正北方向逆时针夹角；CW ：与正北方向顺时针夹角
        //     showCardinal: true,         // 显示方位字母与度数
        //     speedUnit: "m/s",           // 风速单位。可选['m/s', 'k/h', 'mph', 'kt']
        //     directionString: "方向",    // 风向标签前缀
        //     speedString: "速度",        // 风速标签前缀
        // },

        // paneName: "overlayPane",        // 图层面板
        // minVelocity: 0,                 // 粒子最小速度（ m/s ）
        // maxVelocity: 10,                // 粒子最大速度（ m/s ）
        // velocityScale: 0.005,           // 风速的比例 ( 粒子的小尾巴长度 )
        // particleAge: 90,                // 粒子在再生之前绘制的最大帧数
        // lineWidth: 1,                   // 粒子的线宽
        // particleMultiplier: 1 / 300,    // 粒子的密度
        // frameRate: 15,                  // 每秒的帧数
        // opacity: 0.97,                  // 图层层透明度，默认值为 0.97
        // colorScale: [],                 // 定义自己的 hex / rgb 颜色数组 ( 粒子颜色 )
        // onAdd: null,                     // 回调函数
        // onRemove: null,                  // 回调函数
    });
    removeLayerById("current-winfield-layer");
    GLOBAL_CACHE["current-winfield-layer"] = velocityLayer.addTo(map);
}



function getIcon(color, speed, direction) {
    let icon = ""
    if (metElement.value == "降水") {
        icon = `<svg viewBox="0 0 1024 1024">
                    <path fill="${color}"
                        d="M512 1024q94.2336-105.088 306.304-355.1488 102.0928-137.5232 102.0928-285.0048C920.3968 172.032 737.536 1.536 512 1.536 286.464 1.536 103.6032 172.032 103.6032 382.336q0 147.4816 102.1184 285.0048Q417.7664 917.4016 512 1024z"
                    ></path>
                    <circle cx="512" cy="402" r="360" fill="#000" />
                    <path fill="${color}" transform-origin="center" transform="translate(0,-80)  scale(0.53)"
                        d="M861.44128 73.15199998H162.56128A35.58400001 35.58400001 0 0 1 128.00128 36.608 35.58400001 35.58400001 0 0 1 162.56128 0h698.88a35.58400001 35.58400001 0 0 1 34.56 36.608 35.58400001 35.58400001 0 0 1-34.56 36.544zM701.88928 987.392a35.58400001 35.58400001 0 0 0-34.56-36.54400001H356.67328a36.672 36.672 0 0 0 0 73.15200001h310.656a35.58400001 35.58400001 0 0 0 34.56-36.608z m120.704-813.184v476.032a44.22399999 44.22399999 0 0 1-44.48 43.71200002H245.88928a44.22399999 44.22399999 0 0 1-44.48-43.71200002V174.208a44.22399999 44.22399999 0 0 1 44.48-43.71200002h532.224a44.22399999 44.22399999 0 0 1 44.48 43.71200002zM727.04128 553.08800001c0-19.456-11.648-35.2-25.984-35.2H311.04128c-14.336 0-25.984 15.744-25.98400001 35.2s11.648 35.2 25.98400001 35.19999999h390.016c14.336 0 25.984-15.744 25.984-35.19999999z m0-140.86400002c0-19.456-11.648-35.2-25.984-35.19999998H311.04128c-14.336 0-25.984 15.744-25.98400001 35.2s11.648 35.2 25.98400001 35.20000001h390.016c14.336 0 25.984-15.744 25.984-35.20000001z m0-140.86399999c0-19.456-11.648-35.2-25.984-35.20000002H311.04128c-14.336 0-25.984 15.744-25.98400001 35.20000002s11.648 35.2 25.98400001 35.2h390.016c14.336 0 25.984-15.744 25.984-35.2z m-170.68800001 422.59200002H467.64928v281.72799997h88.70399999z"
                    ></path>
                </svg>`;
    } else if (metElement.value == "气温") {
        icon = `<svg viewBox="0 0 1024 1024">
                    <path fill="${color}"
                        d="M512 1024q94.2336-105.088 306.304-355.1488 102.0928-137.5232 102.0928-285.0048C920.3968 172.032 737.536 1.536 512 1.536 286.464 1.536 103.6032 172.032 103.6032 382.336q0 147.4816 102.1184 285.0048Q417.7664 917.4016 512 1024z"
                    ></path>
                    <circle cx="512" cy="402" r="360" fill="#000" />
                    <path fill="${color}" transform-origin="center" transform="translate(0,-115)  scale(0.53)"
                        d="M768 20.48c79.552 0 144.064 63.424 144.064 141.632v404.096a239.296 239.296 0 0 1 89.728 270.144C968.96 936 874.56 1003.52 768 1003.52s-200.96-67.52-233.792-167.168 3.392-208.768 89.728-270.144V162.112C623.936 83.904 688.448 20.48 768 20.48z m-0.064 61.44c-43.648 0-79.04 33.984-79.04 75.904v443.584l-28.992 20.224c-64.704 45.056-91.84 124.928-67.2 197.888 24.64 72.96 95.36 122.432 175.296 122.56 79.936-0.064 150.784-49.536 175.424-122.56s-2.56-152.96-67.328-197.952l-28.992-20.224v-443.52c-0.064-41.92-35.52-75.904-79.168-75.904z m1.344 378.88a32 32 0 0 1 32 32v146.304a133.12 133.12 0 1 1-64.064-0.64L737.28 492.8a32 32 0 0 1 32-32z m-502.208 265.408a30.784 30.784 0 0 1 0 43.456l-72.384 72.384a30.784 30.784 0 0 1-43.456-43.456l72.384-72.384a30.784 30.784 0 0 1 43.456 0zM768 696.32a71.68 71.68 0 1 0-0.064 143.296A71.68 71.68 0 0 0 768 696.32zM491.52 215.04c15.488 0 30.912 1.216 46.016 3.648a30.656 30.656 0 1 1-9.792 60.608 225.28 225.28 0 0 0-90.496 441.152 30.784 30.784 0 0 1-14.72 59.648A286.72 286.72 0 0 1 491.52 215.04z m-348.16 256a30.72 30.72 0 0 1 0 61.44H40.96a30.72 30.72 0 0 1 0-61.44h102.4z m51.264-309.568l72.384 72.384a30.784 30.784 0 0 1-43.456 43.456l-72.32-72.448a30.656 30.656 0 1 1 43.392-43.392zM491.52 20.48a30.72 30.72 0 0 1 30.72 30.72v102.4a30.72 30.72 0 0 1-61.44 0V51.2a30.72 30.72 0 0 1 30.72-30.72z"
                    ></path>
                </svg>`;
    } else if (metElement.value == "风") {
        let vector = {
            "杆": '<rect x="320" y="0" width="64" height="1024" />',
            "角1": '<polygon points="352,32 592,32 352,180" stroke="currentColor" fill="none" stroke-width="64" />',
            "角2": '<polygon points="352,288 592,288 352,436" stroke="currentColor" fill="none" stroke-width="64" />',
            "羽短1": '<rect x="384" y="0" width="160" height="64" />',
            "羽长1": '<rect x="384" y="0" width="320" height="64" />',
            "羽短2": '<rect x="384" y="128" width="160" height="64" />',
            "羽长2": '<rect x="384" y="128" width="320" height="64" />',
            "羽短3": '<rect x="384" y="256" width="160" height="64" />',
            "羽长3": '<rect x="384" y="256" width="320" height="64" />',
            "羽短4": '<rect x="384" y="384" width="160" height="64" />',
            "羽长4": '<rect x="384" y="384" width="320" height="64" />',
            "羽短5": '<rect x="384" y="512" width="160" height="64" />',
            "羽长5": '<rect x="384" y="512" width="320" height="64" />',
            "羽短6": '<rect x="384" y="640" width="160" height="64" />',
            "羽长6": '<rect x="384" y="640" width="320" height="64" />',
            "羽短7": '<rect x="384" y="768" width="160" height="64" />',
            "羽长7": '<rect x="384" y="768" width="320" height="64" />',
            "羽短8": '<rect x="384" y="896" width="160" height="64" />',
            "羽长8": '<rect x="384" y="896" width="320" height="64" />',
        }
        let path = vector["杆"];
        if (speed == 0) path = vector["杆"];
        else if (speed <= 2) path += vector["羽短1"];
        else if (speed <= 4) path += vector["羽长1"];
        else if (speed <= 6) path += vector["羽长1"] + vector["羽短2"];
        else if (speed <= 8) path += vector["羽长1"] + vector["羽长2"];
        else if (speed <= 10) path += vector["羽长1"] + vector["羽长2"] + vector["羽短3"];
        else if (speed <= 12) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"];
        else if (speed <= 14) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"] + vector["羽短4"];
        else if (speed <= 16) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"] + vector["羽长4"];
        else if (speed <= 18) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"] + vector["羽长4"] + vector["羽短5"];
        else if (speed <= 20) path += vector["角1"];
        else if (speed <= 22) path += vector["角1"] + vector["羽短3"];
        else if (speed <= 24) path += vector["角1"] + vector["羽长3"];
        else if (speed <= 26) path += vector["角1"] + vector["羽长3"] + vector["羽短4"];
        else if (speed <= 28) path += vector["角1"] + vector["羽长3"] + vector["羽长4"];
        else if (speed <= 30) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽短5"];
        else if (speed <= 32) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"];
        else if (speed <= 34) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"] + vector["羽短6"];
        else if (speed <= 36) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"] + vector["羽长6"];
        else if (speed <= 38) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"] + vector["羽长6"] + vector["羽短7"];
        else if (speed <= 40) path += vector["角1"] + vector["角2"];
        else if (speed <= 42) path += vector["角1"] + vector["角2"] + vector["羽短5"];
        else if (speed <= 44) path += vector["角1"] + vector["角2"] + vector["羽长5"];
        else if (speed <= 46) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽短6"];
        else if (speed <= 48) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"];
        else if (speed <= 50) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽短7"];
        else if (speed <= 52) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽长7"];
        else if (speed <= 54) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽长7"] + vector["羽短8"];
        else path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽长7"] + vector["羽长8"];
        icon = `<svg viewBox="0 0 1024 1024">
                    <path fill="${color}"
                        d="M512 1024q94.2336-105.088 306.304-355.1488 102.0928-137.5232 102.0928-285.0048C920.3968 172.032 737.536 1.536 512 1.536 286.464 1.536 103.6032 172.032 103.6032 382.336q0 147.4816 102.1184 285.0048Q417.7664 917.4016 512 1024z"
                    ></path>
                    <circle cx="512" cy="402" r="360" fill="#000" />
                    <g fill="${color}" color="${color}" transform-origin="center" transform="translate(0,-115) rotate(${direction}) scale(0.53)">${path}</g>
                </svg>`;
    }
    return icon;
}

function removeLayerById(id) {
    if (GLOBAL_CACHE[id]) {
        GLOBAL_CACHE[id].remove();
        GLOBAL_CACHE[id] = null;
    }
}


// function getPreStationData() {
//     axios({
//         method: "get",
//         url: interfaces.serviceUrl + "db/select",
//         params: {
//             table: "t_tq_pre",
//             type: "between",
//             field: "Datetime",
//             leftValue: selectTime.value[0].format("YYYY-MM-DD HH:mm:00"),
//             rightValue: selectTime.value[1].format("YYYY-MM-DD HH:mm:00")
//         }
//     }).then(response => {
//         let data = response.data.data;
//         // console.log(data)
//         if (data.length == 0) {
//             removeLayerById("current-station-layer");
//             ElMessage({
//                 showClose: true,
//                 message: "无数据",
//                 offset: document.documentElement.clientHeight / 2
//             })
//         } else {
//             // 时间序列
//             let timeDiff = selectTime.value[1].diff(selectTime.value[0], "minute") + 1;
//             let timeSeries = Array.from({ length: timeDiff }, (v, i) => selectTime.value[0].add(i, "minute").format("YYYY-MM-DD HH:mm:00"));
//             let stations = structuredClone(ZiDongZhan);
//             // 数据绑定到设备
//             for (let sta of stations) {
//                 let pre_minute = Array(timeDiff).fill(null);
//                 let pre_sum = 0;
//                 for (let item of data) {
//                     if (sta.sid == item.Station_Id_C) {
//                         // 使用 index 防止时间序列缺失
//                         let index = timeSeries.indexOf(item.Datetime);
//                         if (index != -1 && item.PRE < 999) {
//                             pre_minute[index] = item.PRE * 1;
//                             pre_sum += item.PRE * 1;
//                         }
//                     }
//                 }
//                 sta.val = pre_sum.toFixed(1) * 1;
//                 let config = getPreThreshold(sta.val);
//                 sta.color = config.color;
//                 sta.level = config.name;
//                 sta.dataSeries = pre_minute;
//                 sta.timeSeries = timeSeries;
//             }
//             GLOBAL_CACHE.curData = stations;
//             if (showStationLayer.value) {
//                 GLOBAL_CACHE.curStationData = stations;
//                 renderStationLayer(stations);
//             }
//             if (showGridLayer.value) {
//                 // 插值
//                 let idwGrid = idwToGrid(stations, { startLon: 83.4, startLat: 43.57, lonStep: 0.005, latStep: 0.005, lonCount: 344, latCount: 344 }, 20, Infinity, { valField: "val" });
//                 // console.log()
//                 GLOBAL_CACHE.curGridData = idwGrid;
//                 renderGridLayer(idwGrid);
//             }
//         }
//     })
// }
</script>