<!-- eslint-disable vue/multi-word-component-names -->
<style scoped lang="scss">
:deep(.my-table) {
    --el-table-bg-color: transparent;
    --el-table-header-bg-color: rgba(255, 255, 255, 0.06);
    --el-table-tr-bg-color: transparent;
    --el-table-border: 1px solid rgba(198, 215, 222, 0.5);
    --el-table-text-color: #fff;
    --el-table-header-text-color: #EAF6FF;
    --el-table-row-hover-bg-color: rgba(255, 255, 255, 0.06);

    border-radius: 14px;
    overflow: hidden;
    backdrop-filter: blur(10px);

    .el-table--border .el-table__cell {
        border-right: none;
    }

    .table-header-row {
        height: 58px;
        font-size: 16px;
        background: linear-gradient(180deg, rgba(255, 255, 255, 0.12), rgba(255, 255, 255, 0.06));
        font-weight: 600;
        letter-spacing: 0.3px;
    }

    .table-row {
        height: 56px;
        font-size: 15px;
        transition: background-color 200ms ease, transform 200ms ease;
        &:hover {
            transform: translateY(-1px);
        }
    }

    .table-cell,
    .table-header-cell {
        font-weight: 500;
        padding: 8px 10px;
        line-height: 1.1em;
        border-bottom: 1px solid rgba(255, 255, 255, 0.08);

        img {
            width: 46px;
            height: 46px;
            margin: 5px auto;
            filter: drop-shadow(0 2px 4px rgba(0,0,0,0.25));
            transition: transform 180ms ease;
        }
        img:hover {
            transform: scale(1.04);
        }
    }

    .table-header-cell {
        text-shadow: 0 1px 0 rgba(0, 0, 0, 0.2);
    }

    .max-val-class {
        background: linear-gradient(90deg, rgba(255, 92, 92, 0.25), rgba(255, 92, 92, 0.05));
        box-shadow: inset 0 0 0 1px rgba(255, 92, 92, 0.35);
    }
}

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
    color: #fff;
    background: linear-gradient(135deg, rgba(57, 109, 87, 0.75), rgba(163, 206, 176, 0.22));
    backdrop-filter: blur(10px);
    border: 1px solid rgba(255,255,255,0.18);
    border-radius: 16px;
    box-shadow:
        0 8px 24px rgba(0, 0, 0, 0.28),
        inset 0 1px 0 rgba(255, 255, 255, 0.12);

    .fun-box {
        .header {
            height: 44px;
            text-align: left;
            font-size: 16px;
            padding: 10px 12px;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            justify-content: space-between;
            background: linear-gradient(90deg, #29881f, #79C46A 60%, rgba(143, 196, 106, 0.85));
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.18);
            border-radius: 16px 16px 0 0;

            .header-right {
                display: flex;
                align-items: center;
                gap: 6px;
                padding: 2px 6px;
                border-radius: 999px;
                background-color: rgba(255, 255, 255, 0.12);
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
            padding: 14px 16px 18px;
            gap: 12px 10px;

            span {
                width: 86px;
                height: 36px;
                line-height: 36px;
                background: linear-gradient(180deg, rgba(255,255,255,0.10), rgba(255,255,255,0.06));
                box-shadow:
                    inset 0 0 6px rgba(0, 0, 0, 0.35),
                    0 2px 6px rgba(0, 0, 0, 0.22);
                border: 1px solid rgba(255,255,255,0.18);
                border-radius: 999px;
                cursor: pointer;
                transition: 180ms ease;
                backdrop-filter: blur(6px);

                &:hover {
                    transform: translateY(-1px);
                    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.28);
                }

                &.active {
                    color: #0b1d18;
                    background: linear-gradient(180deg, #BDE8E6, #9FD3D1);
                    border-color: rgba(255,255,255,0.6);
                    box-shadow:
                        inset 1px 1px 2px rgba(255, 255, 255, 0.5),
                        inset -1px -1px 4px rgba(0, 0, 0, 0.25),
                        0 4px 10px rgba(0, 0, 0, 0.35);
                }
            }
        }
    }

    .fun-box.ybsx .main span {
        flex: 1;
        min-width: 80px;
    }
}



.station-dialog-panel {
    position: absolute;
    left: 5px;
    top: 5px;
    right: 5px;
    z-index: 9999;
    background: linear-gradient(135deg,#14253d, #14253d);
    color: #fff;
    font-size: 15px;
    backdrop-filter: blur(12px);
    border: 1px solid rgba(255,255,255,0.16);
    border-radius: 18px;
    box-shadow:
        0 10px 28px rgba(0, 0, 0, 0.35),
        inset 0 1px 0 rgba(255, 255, 255, 0.1);

    .close {
        width: 42px;
        height: 42px;
        position: absolute;
        top: 0;
        right: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;

        svg {
            width: 26px;
            height: 26px;
            color: #fff;
            transition: 180ms ease;
            opacity: 0.9;

            &:hover {
                color: #ff6b6b;
                scale: 1.08;
                filter: drop-shadow(0 2px 6px rgba(255, 107, 107, 0.35));
            }
        }
    }

    .header {
        width: 100%;
        height: 46px;
        padding: 10px 14px;
        box-sizing: border-box;
        display: flex;
        align-items: center;
        text-align: left;
        font-size: 16px;
        background: linear-gradient(90deg, #0E1A2B, #0E1A2B, #0E1A2B, #0E1A2B, rgba(243, 254, 253, 0.12));
        box-shadow: 0 1px 0 rgba(0,0,0,0.18);
        border-radius: 18px 18px 0 0;
    }

    .info-box .main {
        width: 100%;
        min-height: 56px;
        padding: 10px 14px;
        box-sizing: border-box;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 28px;
        background: #0E1A2B;
        border-bottom: 1px solid rgba(255,255,255,0.12);
    }

    .chart-box {
        width: 100%;
        padding: 10px 12px 14px;
        box-sizing: border-box;
    }
}

.timeBar-panel {
  position: absolute;
  bottom: 20px;
  left: 20px; /* 从左侧偏移20px，数值越小越靠左 */
  transform: none; /* 移除水平居中的平移效果 */
  width: calc(100% - 40px);
  max-width: 1300px;
  z-index: 9999;
  border-radius: var(--border-radius-lg);
  overflow: hidden;

  background: var(--surface-elevated);
  backdrop-filter: blur(20px);
  border: 1px solid rgba(255, 255, 255, 0.2);
  box-shadow: var(--shadow-xl);
}

.legend-panel {
    position: absolute;
    bottom: 86px;
    right: 16px;
    width: 680px;
    z-index: 999;
    color: #fff;
    background: linear-gradient(135deg, rgba(34, 56, 74, 0.6), rgba(34, 56, 74, 0.3));
    border: 1px solid rgba(255,255,255,0.14);
    border-radius: 12px;
    padding: 8px 10px;
    backdrop-filter: blur(10px);
    box-shadow: 0 8px 22px rgba(0,0,0,0.28);
}
</style>
<style>
.my-dialog {
    background: linear-gradient(135deg, rgba(72, 113, 142, 0.85), rgba(93, 146, 183, 0.35));
    backdrop-filter: blur(12px);
    color: #fff;
    border-radius: 18px;
    border: 1px solid rgba(255,255,255,0.16);
    box-shadow:
        0 12px 28px rgba(0,0,0,0.35),
        inset 0 1px 0 rgba(255,255,255,0.08);

    .el-dialog__header {
        padding: 0;
        border-radius: 18px 18px 0 0;
        overflow: hidden;
    }

    .el-dialog__header.show-close {
        padding-right: 0;
    }

    .el-dialog__headerbtn {
        position: absolute;
        top: 0;
        right: 0;
        transform: translate(50%, -50%);
        background-color: rgba(72, 113, 142, 0.55);
        box-shadow: 0 0 8px rgba(0, 0, 0, 0.35);
        width: fit-content;
        height: fit-content;
        border-radius: 50%;

        .el-dialog__close {
            width: 30px;
            height: 30px;
            transition: 200ms ease;
            background-color: white;
            mask: url(@/assets/images/close.svg) center/contain no-repeat;

            &:hover {
                background-color: #ff6b6b;
                transform: rotate(90deg) scale(1.05);
                box-shadow: 0 0 10px rgba(255, 107, 107, 0.5);
            }

            svg {
                display: none;
            }
        }
    }

    .my-segmented {
        width: 320px;
        height: 42px;
        position: absolute;
        z-index: 1;
        margin: auto;
        left: 0;
        right: 0;
        top: 8px;
        border-radius: 999px;
        background: rgba(255,255,255,0.12);
        border: 1px solid rgba(255,255,255,0.18);
        backdrop-filter: blur(6px);
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
    <!-- <div class="legend-panel">
        <Legend v-if="showPreLegend"
                :choose="preLegendType + '小时降水'"
                :filter="preFilterRange"
                :filterChange="preFilterChange" />
    </div> -->
    <div class="timeBar-panel">
        <TimeBar class="timeBar-box"
                 v-model="selectTime"
                 :="timeBarConfig"
                 @change-bar="timeBarConfigChange"
                 @point-click="timeBarPoint" />
    </div>

    <div class="station-dialog-panel"
         v-if="showStaDialog">
        <div class="close"
             @click="showStaDialog = false">
            <svg>
                <use xlink:href="#icon-close"></use>
            </svg>
        </div>

        <div class="info-box">
            <div class="header">预警信息统计
                <span style="font-size: 14px;">（ {{ selectTime.format("YYYY-MM-DD HH:00:00") + " 至 " + selectTime.add(72, "hour").format("YYYY-MM-DD HH:00:00") }} ）</span>
            </div>
            <div class="main">
                <div>牧场：{{ staChecked.short_name }}</div>
                <div>时间：{{ staDlgTable[timeBarPointChecked].datatime }}</div>
                <div>暴雨预警：{{ staDlgTable[timeBarPointChecked].preIcon ? staDlgTable[timeBarPointChecked].preIcon + "预警信号" : "无" }}</div>
                <div>大风预警：{{ staDlgTable[timeBarPointChecked].winIcon ? staDlgTable[timeBarPointChecked].winIcon + "预警信号" : "无" }}</div>
                <div>高温预警：{{ staDlgTable[timeBarPointChecked].htemIcon ? staDlgTable[timeBarPointChecked].htemIcon + "预警信号" : "无" }}</div>
            </div>
        </div>
        <div class="chart-box">
            <el-table class="my-table"
                      :data="staDlgTableConfig"
                      header-row-class-name="table-header-row"
                      header-cell-class-name="table-header-cell"
                      row-class-name="table-row"
                      cell-class-name="table-cell"
                      @selection-change="selectionChange"
                      @row-click="(row) => { staDlgTableRowChecked = staDlgTableRowChecked != row.rowIndex ? row.rowIndex : null }"
                      border>
                <el-table-column fixed
                                 align="center"><template #default="{ row, column, $index }">{{ row.name }}</template></el-table-column>
                <el-table-column v-for="(item, idx) in staDlgTable"
                                 :key="item"
                                 :label="item.datatime"
                                 align="center"
                                 width="80">
                    <template #default="{ row, column, $index }">
                        <template v-if="staDlgTable[idx][row.icon]">
                            <img v-if="staDlgTable[idx][row.icon]"
                                 :src="'/zhmc/assets/images/灾害预警图标/' + staDlgTable[idx][row.icon] + '.jpg'" />
                            <div v-show="staDlgTableRowChecked == $index">{{ staDlgTable[idx][row.key] }}</div>
                        </template>
                    </template>
                </el-table-column>
            </el-table>
        </div>


    </div>
</template>

<script setup>
import { ref, onMounted, computed, shallowRef, watch } from "vue";
import axios from "axios";
import dayjs from "dayjs";
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

import interfaces from "@/components/interfaces.js";
import ZiDongZhan from "@/assets/GeographicData/自动站.json"



let refmap = shallowRef(null),
    map = null,
    showStaDialog = ref(false),
    staDlgTableConfig = [
        { rowIndex: 0, name: '降水', key: 'pre', icon: 'preIcon' },
        { rowIndex: 1, name: '大风', key: 'win', icon: 'winIcon' },
        { rowIndex: 2, name: '高温', key: 'tem', icon: 'htemIcon' },
        // { rowIndex: 3, name: '低温', key: 'tem', icon: 'ltem_icon_low' },
    ],
    staDlgTable = ref(null),
    staDlgTableRowChecked = ref(null),
    staChecked = ref(null),

    showStationName = ref(false),
    showStationValue = ref(true),
    GLOBAL_CACHE = {
        pre: {},
        win: {},
        tem: {}
    };

let selectTime = ref(dayjs().set("hour", 8).set("minute", 0).set("second", 0));
let timeBarPointChecked = ref(0),// 从 0 开始
    timeBarConfig = {
        difference: 1,
        dateFormat: "YYYY-MM-DD HH",
        abledHours: [8, 20],
        hideDisabled: true,
        points: 72,
        unit: "hour",
        step: 1,
        service: timeBarService,
    };
function timeBarService(obj) {

}
function timeBarPoint(obj) {
    timeBarPointChecked.value = obj.point - 1;
    // console.log(obj)
    let key = selectTime.value.format("YYYYMMDDHH0000");

    renderStation(GLOBAL_CACHE[key]);
}
// let stations = ref(structuredClone(ZiDongZhan));
// watch(stations, (newValue, oldValue) => {
//     console.log(111)
// }, { deep: true })



onMounted(() => {
    refmap.value = map = L.map("map-container", {
        center: [37.42, 106.6],
        minZoom: 3,
        maxZoom: 18,
        zoom: 9,
        zoomControl: false,
        doubleClickZoom: false,
        attributionControl: false,
        renderer: L.canvas()
    })
    // console.log(Array.from({ length: 72 }, (v, i) => getRandomNumber(28, 42, 1)))
    // console.log(Array.from({ length: 72 }, (v, i) => getRandomNumber(0, 20, 1)))
    // console.log(Array.from({ length: 72 }, (v, i) => getRandomNumber(3, 20, 1)))
    getGridData();
});


function getGridData() {
    let key = selectTime.value.format("YYYYMMDDHH0000");
    let stations = structuredClone(ZiDongZhan);
    stations[0].tem = [35.1, 37.6, 36.5, 37, 32.2, 41.9, 34.5, 28.1, 35.9, 32, 32.1, 28.5, 41.3, 38.1, 38.9, 28.5, 38.1, 34.5, 39, 28.3, 37.8, 29, 30.4, 30.7, 40.3, 30.8, 35, 36.2, 37.7, 31, 39.6, 34.9, 38.3, 30.4, 40.9, 30.2, 41.4, 36.2, 32.6, 36.3, 34.3, 30.6, 39.6, 32.1, 28.4, 30.8, 38.2, 32.7, 38.3, 41.6, 30.1, 31.2, 38.1, 28.2, 30.9, 41.8, 30.8, 29.2, 29.5, 35.5, 37.2, 29.8, 39.3, 31.6, 35.6, 28.5, 34.2, 33.1, 33.2, 40.8, 40.8, 31];
    stations[0].win = [10.3, 13.2, 3.4, 2, 9, 7.3, 17.8, 8.9, 12.2, 14.6, 10.9, 15.4, 5.6, 16.6, 14.3, 7.9, 0.4, 5, 10.6, 16.7, 17, 9.8, 12.7, 13.7, 4.7, 10.5, 0.1, 3, 5.2, 0.3, 7.2, 15.2, 8.6, 15.3, 15.4, 6, 16.9, 1.8, 7.7, 10.8, 2.8, 6.1, 7.2, 5.8, 11.9, 10.3, 12, 10, 7.5, 0.2, 11.5, 1.7, 16.8, 3.1, 19.4, 3.4, 14.2, 12.3, 1.9, 9.5, 0.9, 13.1, 9.3, 10.5, 10.8, 7.4, 16.4, 13, 6, 15.1, 4.6, 0.9];
    stations[0].pre = [13.7, 14.9, 12.6, 9.8, 4.5, 6.7, 16.5, 8.8, 7.2, 11.2, 16.2, 11.9, 8, 16.1, 18.7, 19.2, 14.6, 18.5, 5.3, 17.3, 4.5, 8, 12.5, 18.8, 3.5, 5.7, 4, 15.9, 6.2, 13.9, 17.6, 7.7, 3.6, 17.1, 16.7, 19.6, 20, 4, 10.9, 7.1, 13.5, 8.7, 13.2, 10.3, 10.6, 17.3, 5.9, 14.2, 14.7, 19.5, 13.3, 10.9, 10, 17.5, 14.1, 6.8, 10.6, 7.1, 12.1, 5.4, 19.9, 15.3, 8.6, 8.5, 7.6, 12.7, 16.5, 19.1, 19.3, 5.5, 14.6, 10.3];
    stations[1].tem = [29.4, 39, 29.7, 31.2, 35.3, 32.8, 34.5, 28.3, 38.7, 38.6, 33.8, 38.9, 33.8, 41.4, 40, 38.6, 33.4, 28.9, 34.8, 34.1, 36.4, 30.7, 30.9, 34.7, 36.7, 36.9, 36.8, 38.4, 41.3, 30.9, 30.4, 30.5, 37.7, 38.1, 34.9, 39.7, 31.6, 28, 30.4, 38.9, 29.3, 32.1, 33.8, 28.8, 35.3, 30.7, 31.4, 36.9, 41.9, 28.4, 29.5, 35.1, 41.1, 40.9, 35.1, 32.1, 41.3, 41.7, 38.5, 34.4, 33.9, 35.9, 39.3, 37.3, 33.3, 31.6, 37.5, 40.2, 32.7, 41.7, 41.3, 28.8];
    stations[1].win = [2, 9.5, 1.6, 11.7, 19.1, 1.6, 13.7, 6.1, 18.7, 15.5, 15.5, 1.2, 7.8, 17.7, 0.7, 2.3, 6.5, 14.1, 0.4, 1.8, 11.4, 10.1, 13.5, 14.2, 9.5, 14.8, 1.7, 8.8, 7.1, 10.6, 5, 5.3, 2.6, 14.4, 0.2, 0.8, 5.8, 19.6, 11.4, 5.3, 3.6, 16.4, 18.8, 16.6, 18.5, 18.5, 18.1, 4.8, 10.8, 6.8, 15.4, 2.8, 18.3, 1.3, 10.4, 0.6, 14, 3.5, 4.2, 6.6, 19.5, 5.1, 6.7, 12.4, 2.9, 8.6, 2.2, 13.2, 1.6, 6.2, 4.9, 6.3];
    stations[1].pre = [18.6, 3.5, 8.3, 12.3, 16.7, 5.4, 4.5, 5.7, 8.4, 7.4, 6.3, 9.3, 10.7, 12.5, 17, 16.2, 5.7, 11.5, 15.6, 8.6, 7.3, 18.6, 14.9, 8.6, 14.4, 6.5, 18.5, 12, 9.3, 5.4, 9.9, 9, 15.5, 10.3, 18.5, 12.1, 11, 8.6, 5.4, 5.1, 8.4, 19.4, 5.6, 8.8, 4.8, 10.1, 9.4, 12.4, 8, 15, 3.5, 10.4, 11.2, 13.9, 12.6, 12.5, 6.4, 6, 10.4, 4.8, 9, 10.7, 15.5, 11.7, 16.6, 5.9, 19.7, 15.8, 3.9, 16.3, 10, 5.9];
    stations[2].tem = [36.7, 33.7, 38.4, 36.7, 30.1, 31.9, 41.5, 28.5, 38.8, 28.7, 30.8, 38.9, 28.6, 33.7, 37.4, 34.3, 33.2, 33.8, 36.3, 41.7, 36.9, 37.5, 28.6, 33.8, 28.1, 38.7, 34.8, 40.6, 39.4, 32.5, 35.8, 31.5, 29.5, 31.9, 35, 28.9, 41.1, 38.4, 29.9, 41.6, 33.2, 31.3, 29.7, 39.8, 34.6, 37.8, 37.9, 30.4, 30.9, 36.4, 39.7, 34.4, 34, 36.6, 33.6, 32.1, 40.4, 31.2, 38.6, 31.1, 41.1, 29, 34.5, 41.7, 41.9, 35, 28.6, 33.2, 33, 34.4, 39.8, 41.1];
    stations[2].win = [12, 2.2, 16.6, 12.5, 0.9, 11.9, 12.6, 11.8, 2.8, 10.9, 9.5, 3.7, 15.8, 0.1, 3.4, 2.2, 13.4, 11.5, 16.7, 1.9, 14.2, 17.8, 6.1, 3.2, 6.5, 15.2, 6.5, 9.3, 6.3, 15.8, 2.1, 2.6, 8.2, 4.5, 14.2, 15.8, 10.7, 0.4, 7.3, 7.5, 1.6, 17.9, 15.4, 18.7, 3.6, 4, 6.1, 9.6, 15.8, 2.5, 8, 12.1, 1.4, 6, 1.2, 9.1, 16.5, 0.6, 11.9, 16.7, 0.9, 6.8, 3.2, 3.8, 15.7, 6.2, 17.3, 8.9, 15.2, 13.7, 4.4, 19.7];
    stations[2].pre = [14.5, 9.8, 17.6, 10.4, 17.9, 11.4, 3.9, 4.3, 19.8, 18.8, 16.5, 17.7, 17.7, 13.4, 14.7, 7.3, 9.4, 11.4, 16.7, 13.5, 10.5, 9, 5.3, 6.3, 17.3, 3.2, 17.3, 9.9, 5.5, 5.2, 13.3, 17.6, 4.4, 3.1, 14.6, 4.5, 11, 6.8, 7.4, 7.3, 8, 3.1, 18.2, 13, 4.3, 8.6, 14.4, 13.7, 8.3, 4.5, 13, 16, 16.6, 4.8, 17.9, 6.5, 8.6, 12.3, 19.6, 8.4, 14.4, 4.4, 15.1, 7.6, 8.7, 17.2, 4.9, 12.8, 8.3, 14.7, 11.6, 4];
    stations[3].tem = [37.8, 37.2, 34.7, 41.3, 37, 28.5, 34.7, 37.1, 36.8, 32.6, 40.4, 31.5, 36.5, 28.2, 28.3, 40, 37.4, 29.5, 38.6, 40.6, 36.1, 34.4, 36.7, 32.3, 37.2, 30.3, 31, 30.4, 30.8, 40.2, 35.2, 39.5, 39.2, 37, 38, 36.6, 41.3, 33.5, 31.1, 32.7, 31.7, 31.1, 35.7, 35.3, 29.1, 29.1, 29.5, 37.9, 36.8, 41.4, 36.9, 41.5, 40.2, 40, 33.6, 40.6, 33.5, 34.3, 34.2, 29.6, 40.4, 33.5, 40.7, 32.7, 34.2, 29.3, 41.2, 41.8, 32.1, 40.6, 37.2, 38.3];
    stations[3].win = [7.7, 16, 10.9, 1.2, 8, 19.3, 2.5, 2.5, 8.1, 14.5, 4.3, 17.3, 15.2, 5.2, 14.1, 14.5, 8.7, 8.9, 6.6, 16.1, 7.3, 2.1, 2.6, 12.3, 5.9, 9.6, 17.2, 10.1, 17.7, 1.9, 4.8, 16.7, 9.6, 13.5, 1.4, 1, 1.4, 4.1, 6, 9.3, 2.2, 2.8, 17.3, 6.5, 15.9, 7.1, 3, 18.6, 15.4, 6.9, 5.4, 19.1, 9.3, 13, 6.1, 12.6, 16.2, 3.9, 12.5, 7.5, 4.8, 1.5, 17.1, 4.7, 14.9, 5.2, 1, 12.1, 11.4, 3.5, 10.1, 14.5];
    stations[3].pre = [4.3, 15, 7.6, 4.2, 14.4, 15.9, 16.3, 5.3, 3.7, 18.4, 14.2, 10.8, 8.9, 10.6, 4.1, 12.8, 14.4, 14.2, 11.8, 16.7, 15.1, 13.8, 14.1, 13, 13, 19.5, 7.2, 8.9, 13.7, 4.3, 11.3, 6.9, 18.8, 10.9, 5.9, 15.4, 8.4, 8.4, 4, 3.9, 13, 4.2, 9.9, 14, 18.5, 8.5, 18, 12.1, 9.1, 16.4, 15.8, 19.6, 19.4, 8.3, 13.6, 12.2, 19, 6.4, 7.1, 5.9, 9.8, 18.6, 6.8, 9.3, 13.3, 17.1, 5.8, 7.4, 19.6, 13.3, 12.4, 4.4];
    stations[4].tem = [37.1, 28.4, 36.2, 36.6, 38.3, 34, 41.7, 41, 39.1, 30.3, 29, 32.7, 41.1, 41.8, 36.5, 35, 33.3, 33, 31.7, 38.5, 37, 37, 38.3, 32.4, 29.9, 41.7, 34.2, 33.1, 34.9, 35.3, 28.2, 34.9, 38.9, 31.5, 40.9, 39.9, 33.9, 33.2, 37.1, 37.5, 35.2, 30.2, 34.8, 34.9, 37.1, 39.1, 34.9, 40.6, 40.9, 32.6, 37.3, 40.2, 36.6, 35.8, 31.7, 30.9, 38, 39.7, 41, 40.6, 28.6, 32.9, 41.5, 39.3, 40.7, 33.5, 37, 34.7, 34.5, 38.4, 37.9, 41.1]
    stations[4].win = [15.9, 13.1, 9.8, 16.7, 13.3, 18.9, 3, 3.1, 0.3, 7.4, 18.8, 1.6, 16, 5.9, 19.4, 19.7, 17.1, 16.1, 12.8, 8.6, 4, 16.1, 13.1, 16.2, 7.2, 19.1, 3.2, 19.3, 19.2, 13.8, 18.4, 19.7, 7.3, 18.3, 4.1, 19.3, 12.5, 18.6, 11.3, 1.7, 6.3, 2.9, 8, 17.4, 2.1, 2.6, 18, 6.7, 2.7, 8, 2.9, 6, 0.3, 8.9, 1.6, 15.8, 19.5, 18.2, 1.5, 4.8, 12.8, 2.7, 2.9, 13.4, 0.5, 13.3, 5.4, 6.8, 11.4, 14.5, 14.5, 4.8];
    stations[4].pre = [14.8, 3.8, 8.5, 7.7, 9.8, 18.1, 11.8, 16.9, 4.2, 6.5, 9, 15.6, 7, 11.1, 11.3, 10.8, 5.3, 17.7, 15.7, 4.8, 14.6, 14.8, 18.7, 16.3, 19.8, 15.4, 9.6, 12.5, 10.5, 6.8, 7, 8.9, 4.2, 18.3, 4.9, 7.5, 8, 5.2, 18.6, 5, 9.9, 6.5, 4.1, 15.8, 14.7, 19.9, 11.4, 13.1, 14.6, 15.5, 6.7, 15.4, 11.1, 14.4, 11.8, 7, 13.7, 14, 10.4, 14.4, 10.4, 12, 4.1, 18.1, 19.7, 3.4, 9.6, 3, 13.8, 9.3, 12.2, 3.5];
    stations[5].tem = [35.1, 37.6, 36.5, 37, 32.2, 41.9, 34.5, 28.1, 35.9, 32, 32.1, 28.5, 41.3, 38.1, 38.9, 28.5, 38.1, 34.5, 39, 28.3, 37.8, 29, 30.4, 30.7, 40.3, 30.8, 35, 36.2, 37.7, 31, 39.6, 34.9, 38.3, 30.4, 40.9, 30.2, 41.4, 36.2, 32.6, 36.3, 34.3, 30.6, 39.6, 32.1, 28.4, 30.8, 38.2, 32.7, 38.3, 41.6, 30.1, 31.2, 38.1, 28.2, 30.9, 41.8, 30.8, 29.2, 29.5, 35.5, 37.2, 29.8, 39.3, 31.6, 35.6, 28.5, 34.2, 33.1, 33.2, 40.8, 40.8, 31];
    stations[5].win = [10.3, 13.2, 3.4, 2, 9, 7.3, 17.8, 8.9, 12.2, 14.6, 10.9, 15.4, 5.6, 16.6, 14.3, 7.9, 0.4, 5, 10.6, 16.7, 17, 9.8, 12.7, 13.7, 4.7, 10.5, 0.1, 3, 5.2, 0.3, 7.2, 15.2, 8.6, 15.3, 15.4, 6, 16.9, 1.8, 7.7, 10.8, 2.8, 6.1, 7.2, 5.8, 11.9, 10.3, 12, 10, 7.5, 0.2, 11.5, 1.7, 16.8, 3.1, 19.4, 3.4, 14.2, 12.3, 1.9, 9.5, 0.9, 13.1, 9.3, 10.5, 10.8, 7.4, 16.4, 13, 6, 15.1, 4.6, 0.9];
    stations[5].pre = [13.7, 14.9, 12.6, 9.8, 4.5, 6.7, 16.5, 8.8, 7.2, 11.2, 16.2, 11.9, 8, 16.1, 18.7, 19.2, 14.6, 18.5, 5.3, 17.3, 4.5, 8, 12.5, 18.8, 3.5, 5.7, 4, 15.9, 6.2, 13.9, 17.6, 7.7, 3.6, 17.1, 16.7, 19.6, 20, 4, 10.9, 7.1, 13.5, 8.7, 13.2, 10.3, 10.6, 17.3, 5.9, 14.2, 14.7, 19.5, 13.3, 10.9, 10, 17.5, 14.1, 6.8, 10.6, 7.1, 12.1, 5.4, 19.9, 15.3, 8.6, 8.5, 7.6, 12.7, 16.5, 19.1, 19.3, 5.5, 14.6, 10.3];
    stations[6].tem = [29.4, 39, 29.7, 31.2, 35.3, 32.8, 34.5, 28.3, 38.7, 38.6, 33.8, 38.9, 33.8, 41.4, 40, 38.6, 33.4, 28.9, 34.8, 34.1, 36.4, 30.7, 30.9, 34.7, 36.7, 36.9, 36.8, 38.4, 41.3, 30.9, 30.4, 30.5, 37.7, 38.1, 34.9, 39.7, 31.6, 28, 30.4, 38.9, 29.3, 32.1, 33.8, 28.8, 35.3, 30.7, 31.4, 36.9, 41.9, 28.4, 29.5, 35.1, 41.1, 40.9, 35.1, 32.1, 41.3, 41.7, 38.5, 34.4, 33.9, 35.9, 39.3, 37.3, 33.3, 31.6, 37.5, 40.2, 32.7, 41.7, 41.3, 28.8];
    stations[6].win = [2, 9.5, 1.6, 11.7, 19.1, 1.6, 13.7, 6.1, 18.7, 15.5, 15.5, 1.2, 7.8, 17.7, 0.7, 2.3, 6.5, 14.1, 0.4, 1.8, 11.4, 10.1, 13.5, 14.2, 9.5, 14.8, 1.7, 8.8, 7.1, 10.6, 5, 5.3, 2.6, 14.4, 0.2, 0.8, 5.8, 19.6, 11.4, 5.3, 3.6, 16.4, 18.8, 16.6, 18.5, 18.5, 18.1, 4.8, 10.8, 6.8, 15.4, 2.8, 18.3, 1.3, 10.4, 0.6, 14, 3.5, 4.2, 6.6, 19.5, 5.1, 6.7, 12.4, 2.9, 8.6, 2.2, 13.2, 1.6, 6.2, 4.9, 6.3];
    stations[6].pre = [18.6, 3.5, 8.3, 12.3, 16.7, 5.4, 4.5, 5.7, 8.4, 7.4, 6.3, 9.3, 10.7, 12.5, 17, 16.2, 5.7, 11.5, 15.6, 8.6, 7.3, 18.6, 14.9, 8.6, 14.4, 6.5, 18.5, 12, 9.3, 5.4, 9.9, 9, 15.5, 10.3, 18.5, 12.1, 11, 8.6, 5.4, 5.1, 8.4, 19.4, 5.6, 8.8, 4.8, 10.1, 9.4, 12.4, 8, 15, 3.5, 10.4, 11.2, 13.9, 12.6, 12.5, 6.4, 6, 10.4, 4.8, 9, 10.7, 15.5, 11.7, 16.6, 5.9, 19.7, 15.8, 3.9, 16.3, 10, 5.9];

    GLOBAL_CACHE[key] = stations;
    renderStation(stations);
}
// ---------- 渲染站点 ----------
function renderStation(stations) {
    var group = [];
    for (let i = 0, len = stations.length; i < len; i++) {
        let item = stations[i];
        let preVal = item.pre[timeBarPointChecked.value],
            winVal = item.win[timeBarPointChecked.value],
            temVal = item.tem[timeBarPointChecked.value];
        let preIcon = preVal > 35 ? "暴雨红色" : preVal > 30 ? "暴雨橙色" : preVal > 25 ? "暴雨黄色" : preVal > 15 ? "暴雨蓝色" : null,
            winIcon = winVal > 25 ? "大风红色" : winVal > 20 ? "大风橙色" : winVal > 15 ? "大风黄色" : winVal > 10 ? "大风蓝色" : null,
            htemIcon = temVal > 40 ? "高温红色" : temVal > 37 ? "高温橙色" : temVal > 35 ? "高温黄色" : null;
        let icons = [];
        if (preIcon) { icons.push(preIcon); }
        if (winIcon) { icons.push(winIcon); }
        if (htemIcon) { icons.push(htemIcon); }
        // 预警图标
        let iconHtml = icons.map((val, idx) => {
            let count = icons.length,
                radius = 40,
                setpAngle = 60,
                startAngle = -(count - 1) * setpAngle / 2;
            let angle = startAngle + idx * setpAngle,
                radian = angle * (Math.PI / 180),
                x = radius * Math.sin(radian),
                y = -radius * Math.cos(radian);
            return `<img class="img"
                         style="position: absolute;width: 26px;height: 26px;left: 50%;top: 50%;
                                transform: translate(-50%, -50%) translate(${x}px, ${y}px) rotate(${angle}deg);"
                         src="/zhmc/assets/images/灾害预警图标/${val}.jpg" />`
        }).join("");

        let layer = L.marker([item.lat, item.lon], {
            icon: L.divIcon({
                className: "my-marker-box",
                iconSize: [0, 0],
                html: `<div class="my-marker${showStationName.value ? ' show-name' : ''}">
                            <div class="icon">${getIcon("牧场", "#1E80FF")}</div>
                            <div class="shade">
                                <span class="val"></span>
                                ${iconHtml}
                                <span class="name">${item.short_name}</span>
                            </div>
                        </div>`,
            }),
            riseOnHover: true,
            item: item,
        }).on("click", function (e) {
            renderStationPopup(e.target.options.item);
        })
        group.push(layer);
    }
    removeLayerById("current-station-layer");
    GLOBAL_CACHE["current-station-layer"] = L.layerGroup(group).addTo(map);
}

// ---------- 单个站点的时间序列 ----------
function renderStationPopup(station) {
    showStaDialog.value = true;
    staChecked.value = station;
    staDlgTable.value = Array.from({ length: station.tem.length }, (v, i) => {
        let preVal = station.pre[i],
            winVal = station.win[i],
            temVal = station.tem[i];
        let preIcon = preVal > 35 ? "暴雨红色" : preVal > 30 ? "暴雨橙色" : preVal > 25 ? "暴雨黄色" : preVal > 15 ? "暴雨蓝色" : null,
            winIcon = winVal > 25 ? "大风红色" : winVal > 20 ? "大风橙色" : winVal > 15 ? "大风黄色" : winVal > 10 ? "大风蓝色" : null,
            htemIcon = temVal > 40 ? "高温红色" : temVal > 37 ? "高温橙色" : temVal > 35 ? "高温黄色" : null;
        return {
            datatime: selectTime.value.add(i, "hour").format("MM-DD HH:00"),
            pre: preVal,
            preIcon: preIcon,
            win: winVal,
            winIcon: winIcon,
            tem: temVal,
            htemIcon: htemIcon,
        }
    })
}

// ---------- 定时任务 ----------
// setInterval(function () {
//     updateIconColor("red");
//     setTimeout(() => {
//         updateIconColor("#1E80FF");
//     }, 1000);
// }, 2000);

// function updateIconColor(color) {
//     GLOBAL_CACHE["current-station-layer"].eachLayer(function (layer) {
//         let item = layer.options.item;
//         let icon = `<div class="my-marker${showStationName.value ? ' show-name' : ''}">
//                         <div class="icon">${getIcon("牧场", color)}</div>
//                         <div class="shade">
//                             <span class="val"></span>
//                             <span class="name">${item.short_name}</span>
//                         </div>
//                     </div>`;
//         if (layer.options.item.warning) {
//             layer.setIcon(L.divIcon({
//                 className: "my-marker-box",
//                 iconSize: [0, 0],
//                 html: icon,
//             }));
//         }
//     });
// }






// ---------- 网格数据转站点数据 ----------
function transformGridToStation(data) {
    let stations = structuredClone(ZiDongZhan);
    let DS = data.DS,  // 三维数组 [hour][latIdx][lonIdx]
        startLon = data.startLon, startLat = data.startLat,
        lonStep = data.lonStep, latStep = data.latStep;
    for (let sta of stations) {
        let latIdx = Math.floor((sta.lat - startLat) / latStep);
        let lonIdx = Math.floor((sta.lon - startLon) / lonStep);
        let dataSeries = [];
        for (let hour = 0; hour < DS.length; hour++) {
            let val = DS[hour]?.[latIdx]?.[lonIdx];
            if (val != undefined) {
                if (metElement.value == "气温") {
                    val = val - 273.15;
                }
            }
            dataSeries.push(val.toFixed(1) * 1);
        }
        sta.val = dataSeries[tbPoint] ?? "--";
        let config = metElement.value == "降水" ? getPreThreshold(sta.val || 0) : metElement.value == "气温" ? getTemThreshold(sta.val || 0) : getWinThreshold(sta.val || 0);
        sta.color = config.color;
        sta.level = config.name;
        sta.dataSeries = dataSeries;
    }
    GLOBAL_CACHE.curStationData = stations;
    renderStationLayer(stations)
}

function getIcon(type, color, speed, direction) {
    let icon = ""
    if (type == "牧场") {
        icon = `
                <svg viewBox="0 0 1024 1024" color="${color}" fill="${color}">
                    <path
                        d="
                        M 225.85 684.98
                        L 512 994
                        L 798.15 684.98
                        A 390 390 0 0 1 225.85 684.98
                        A 390 390 0 1 1 798.15 684.98
                        A 390 390 0 1 0 225.85 684.98
                        z
                        "
                        stroke-linejoin="round" fill="currentColor" stroke="currentColor"
                        stroke-width="60"
                        data-msg="半径390的边框"></path>
                    <path transform-origin="center" transform="translate(0,-80) scale(0.66)"
                        d="M844.9 526.05c0-34.14-5.87-67.68-17.45-99.77 23.57-9.37 43.81-25.53 57.34-45.88 14.54-21.24 17.72-48.08 8.5-71.81l-0.12-0.28c-11.36-24.73-35.01-42.8-62.77-48.44 33.28-59.81 26.36-128.57 10.5-157.38-13.03-23.76-32.1-23.95-39.73-22.93-28.88 4.13-35.74 38.94-38.33 52.05l-0.21 1.08c-12.25 65.9-73.29 93.79-122.97 105.58-40.26-15.62-82.95-23.54-126.88-23.54-44.22 0-87.14 8.01-127.6 23.81-50.16-11.71-111.76-39.57-124.09-105.78-2.1-12.09-8.5-48.88-38.59-53.19-7.58-1.02-26.62-0.82-39.68 22.91-9.17 16.51-14.15 42.6-13.31 69.8 0.68 22.07 5.5 54.62 24.06 87.61-27.83 5.57-51.46 23.65-62.74 48.47l-0.11 0.26c-9.23 23.73-6.05 50.58 8.48 71.78 13.91 20.71 34.65 37.08 58.71 46.43-11.45 31.93-17.25 65.28-17.25 99.27 0.06 7.88 0.43 15.87 1.1 23.8-25.76 38.4-39.38 82.74-39.42 128.33v24.98c0.06 64.56 26.92 125.24 75.63 170.85 48.63 45.53 113.25 70.64 181.98 70.69h240.66c68.75-0.01 133.39-25.1 182.03-70.64 48.73-45.63 75.57-106.33 75.58-170.91v-24.98c-0.05-53.79-18.5-104.61-53.38-147.1 0.04-1.85 0.06-3.52 0.06-5.07zM290.46 293.37l-26.43-25.77c-22.24-22.99-36.25-52.03-40.32-82.5 9.03 18.05 21.52 34.23 37.33 48.32 16.56 14.76 36.79 27.26 60.24 37.24a316.87 316.87 0 0 0-30.82 22.71z m467.29 22.49l27.15-6.25c26.54-5.96 52.51 1.76 59.23 17.58 3.13 9.08 1.54 19.19-4.26 27.08l-0.12 0.16c-7.89 11.67-19.61 20.89-33.24 26.2-13.22-23.42-29.59-45.15-48.76-64.77zM640.83 436.67h-0.23l-43.73-0.01c5.73-11.96 14.21-19.85 21.89-19.85 6.79-0.01 15.92 6.79 22.07 19.86zM403.1 416.83h0.85c6.78 0 15.89 6.78 21.98 19.83h-25.98c-6.03 0-12.1 0.21-18.12 0.6 5.57-11.77 13.83-19.88 21.27-20.43z m356.63-149.25l-26.39 25.73a333.847 333.847 0 0 0-30.75-22.73c23.45-9.99 43.67-22.52 60.21-37.3 15.75-14.08 28.2-30.25 37.21-48.3-4.08 30.49-18.08 59.56-40.28 82.6zM697.8 442.7c-9.27-44.95-41.39-76.05-79.05-76.05-35.91 0-66.67 27.97-77.54 70.02H481.6c-5-19.56-14.5-36.45-27.63-49.02-14.35-13.74-31.65-21-50.03-21-19.73 0-38.65 8.6-53.29 24.23-13.57 14.48-22.95 34.45-26.6 56.52-31.9 9.23-61.99 24.48-87.87 44.47 18.3-129.87 135-226.46 276.56-226.64 134.91 0.09 250.88 91.57 274.76 214.66-27.03-17.62-57.75-30.37-89.7-37.19zM238.9 309.34l27.28 6.28c-19.08 19.62-35.45 41.37-48.79 64.8-13.66-5.39-25.44-14.65-33.43-26.32l-0.1-0.14c-5.79-7.87-7.39-17.96-4.28-27.04 5.15-11.55 20.54-19.11 39.52-19.49l1.43 0.02c6.16 0.12 12.34 0.75 18.37 1.89z m-43.35 368.88c0.03-22.89 4.35-45.29 12.83-66.58l23.66-33.34c34.95-49.38 91.02-82.1 153.8-89.75l1.51-0.18c1.43-0.17 2.87-0.31 4.3-0.45 7.23-0.68 14.56-1.03 21.79-1.05h68.05l5.25-0.03h49.11v0.03h91.47c13.98 0.04 28.01 1.35 41.71 3.89l9.42 1.77c46.63 10.54 88.79 35.62 118.72 70.63l28.24 32.91c13.14 25.75 19.83 53.38 19.87 82.13v24.97c-0.16 105.37-91.99 191.22-204.7 191.37H400.26c-112.72-0.15-204.54-86-204.7-191.36v-24.96z     M636.91 804.08l-1.25 1.14h12.93c44.76 0 79.82-48.73 79.82-110.94 0-62.2-35.06-110.93-79.81-110.93s-79.81 48.73-79.81 110.93c0 56.67 29.1 102.16 68.12 109.8z m-4.71-155.01c5.86-10.52 12.46-15.24 16.39-15.24 3.94 0 10.54 4.71 16.4 15.24 6.76 12.12 10.48 28.28 10.48 45.5s-3.72 33.38-10.48 45.51c-5.86 10.52-12.46 15.23-16.39 15.23-3.93 0-10.53-4.71-16.39-15.23-6.76-12.13-10.48-28.29-10.48-45.51s3.72-33.38 10.47-45.5zM401.39 805.01v0.21h5c44.75 0 79.81-48.73 79.81-110.94 0-62.2-35.06-110.93-79.81-110.93s-79.81 48.73-79.81 110.93c0.01 59.87 32.48 107.25 74.81 110.73z m21.39-64.93c-5.86 10.52-12.46 15.23-16.39 15.23s-10.53-4.71-16.39-15.23c-6.76-12.13-10.48-28.29-10.48-45.51s3.72-33.38 10.47-45.51c5.86-10.52 12.46-15.23 16.4-15.23s10.53 4.71 16.39 15.23c6.75 12.12 10.47 28.29 10.47 45.51 0.01 17.22-3.71 33.38-10.47 45.51z"></path>
                </svg>
            `;
    }

    // if (metElement.value == "降水") {
    //     icon = `<svg viewBox="0 0 1024 1024">
    //                 <path fill="${color}"
    //                     d="M512 1024q94.2336-105.088 306.304-355.1488 102.0928-137.5232 102.0928-285.0048C920.3968 172.032 737.536 1.536 512 1.536 286.464 1.536 103.6032 172.032 103.6032 382.336q0 147.4816 102.1184 285.0048Q417.7664 917.4016 512 1024z"
    //                 ></path>
    //                 <circle cx="512" cy="402" r="360" fill="#000" />
    //                 <path fill="${color}" transform-origin="center" transform="translate(0,-80)  scale(0.53)"
    //                     d="M861.44128 73.15199998H162.56128A35.58400001 35.58400001 0 0 1 128.00128 36.608 35.58400001 35.58400001 0 0 1 162.56128 0h698.88a35.58400001 35.58400001 0 0 1 34.56 36.608 35.58400001 35.58400001 0 0 1-34.56 36.544zM701.88928 987.392a35.58400001 35.58400001 0 0 0-34.56-36.54400001H356.67328a36.672 36.672 0 0 0 0 73.15200001h310.656a35.58400001 35.58400001 0 0 0 34.56-36.608z m120.704-813.184v476.032a44.22399999 44.22399999 0 0 1-44.48 43.71200002H245.88928a44.22399999 44.22399999 0 0 1-44.48-43.71200002V174.208a44.22399999 44.22399999 0 0 1 44.48-43.71200002h532.224a44.22399999 44.22399999 0 0 1 44.48 43.71200002zM727.04128 553.08800001c0-19.456-11.648-35.2-25.984-35.2H311.04128c-14.336 0-25.984 15.744-25.98400001 35.2s11.648 35.2 25.98400001 35.19999999h390.016c14.336 0 25.984-15.744 25.984-35.19999999z m0-140.86400002c0-19.456-11.648-35.2-25.984-35.19999998H311.04128c-14.336 0-25.984 15.744-25.98400001 35.2s11.648 35.2 25.98400001 35.20000001h390.016c14.336 0 25.984-15.744 25.984-35.20000001z m0-140.86399999c0-19.456-11.648-35.2-25.984-35.20000002H311.04128c-14.336 0-25.984 15.744-25.98400001 35.20000002s11.648 35.2 25.98400001 35.2h390.016c14.336 0 25.984-15.744 25.984-35.2z m-170.68800001 422.59200002H467.64928v281.72799997h88.70399999z"
    //                 ></path>
    //             </svg>`;
    // } else if (metElement.value == "气温") {
    //     icon = `<svg viewBox="0 0 1024 1024">
    //                 <path fill="${color}"
    //                     d="M512 1024q94.2336-105.088 306.304-355.1488 102.0928-137.5232 102.0928-285.0048C920.3968 172.032 737.536 1.536 512 1.536 286.464 1.536 103.6032 172.032 103.6032 382.336q0 147.4816 102.1184 285.0048Q417.7664 917.4016 512 1024z"
    //                 ></path>
    //                 <circle cx="512" cy="402" r="360" fill="#000" />
    //                 <path fill="${color}" transform-origin="center" transform="translate(0,-115)  scale(0.53)"
    //                     d="M768 20.48c79.552 0 144.064 63.424 144.064 141.632v404.096a239.296 239.296 0 0 1 89.728 270.144C968.96 936 874.56 1003.52 768 1003.52s-200.96-67.52-233.792-167.168 3.392-208.768 89.728-270.144V162.112C623.936 83.904 688.448 20.48 768 20.48z m-0.064 61.44c-43.648 0-79.04 33.984-79.04 75.904v443.584l-28.992 20.224c-64.704 45.056-91.84 124.928-67.2 197.888 24.64 72.96 95.36 122.432 175.296 122.56 79.936-0.064 150.784-49.536 175.424-122.56s-2.56-152.96-67.328-197.952l-28.992-20.224v-443.52c-0.064-41.92-35.52-75.904-79.168-75.904z m1.344 378.88a32 32 0 0 1 32 32v146.304a133.12 133.12 0 1 1-64.064-0.64L737.28 492.8a32 32 0 0 1 32-32z m-502.208 265.408a30.784 30.784 0 0 1 0 43.456l-72.384 72.384a30.784 30.784 0 0 1-43.456-43.456l72.384-72.384a30.784 30.784 0 0 1 43.456 0zM768 696.32a71.68 71.68 0 1 0-0.064 143.296A71.68 71.68 0 0 0 768 696.32zM491.52 215.04c15.488 0 30.912 1.216 46.016 3.648a30.656 30.656 0 1 1-9.792 60.608 225.28 225.28 0 0 0-90.496 441.152 30.784 30.784 0 0 1-14.72 59.648A286.72 286.72 0 0 1 491.52 215.04z m-348.16 256a30.72 30.72 0 0 1 0 61.44H40.96a30.72 30.72 0 0 1 0-61.44h102.4z m51.264-309.568l72.384 72.384a30.784 30.784 0 0 1-43.456 43.456l-72.32-72.448a30.656 30.656 0 1 1 43.392-43.392zM491.52 20.48a30.72 30.72 0 0 1 30.72 30.72v102.4a30.72 30.72 0 0 1-61.44 0V51.2a30.72 30.72 0 0 1 30.72-30.72z"
    //                 ></path>
    //             </svg>`;
    // } else if (metElement.value == "风") {
    //     let vector = {
    //         "杆": '<rect x="320" y="0" width="64" height="1024" />',
    //         "角1": '<polygon points="352,32 592,32 352,180" stroke="currentColor" fill="none" stroke-width="64" />',
    //         "角2": '<polygon points="352,288 592,288 352,436" stroke="currentColor" fill="none" stroke-width="64" />',
    //         "羽短1": '<rect x="384" y="0" width="160" height="64" />',
    //         "羽长1": '<rect x="384" y="0" width="320" height="64" />',
    //         "羽短2": '<rect x="384" y="128" width="160" height="64" />',
    //         "羽长2": '<rect x="384" y="128" width="320" height="64" />',
    //         "羽短3": '<rect x="384" y="256" width="160" height="64" />',
    //         "羽长3": '<rect x="384" y="256" width="320" height="64" />',
    //         "羽短4": '<rect x="384" y="384" width="160" height="64" />',
    //         "羽长4": '<rect x="384" y="384" width="320" height="64" />',
    //         "羽短5": '<rect x="384" y="512" width="160" height="64" />',
    //         "羽长5": '<rect x="384" y="512" width="320" height="64" />',
    //         "羽短6": '<rect x="384" y="640" width="160" height="64" />',
    //         "羽长6": '<rect x="384" y="640" width="320" height="64" />',
    //         "羽短7": '<rect x="384" y="768" width="160" height="64" />',
    //         "羽长7": '<rect x="384" y="768" width="320" height="64" />',
    //         "羽短8": '<rect x="384" y="896" width="160" height="64" />',
    //         "羽长8": '<rect x="384" y="896" width="320" height="64" />',
    //     }
    //     let path = vector["杆"];
    //     if (speed == 0) path = vector["杆"];
    //     else if (speed <= 2) path += vector["羽短1"];
    //     else if (speed <= 4) path += vector["羽长1"];
    //     else if (speed <= 6) path += vector["羽长1"] + vector["羽短2"];
    //     else if (speed <= 8) path += vector["羽长1"] + vector["羽长2"];
    //     else if (speed <= 10) path += vector["羽长1"] + vector["羽长2"] + vector["羽短3"];
    //     else if (speed <= 12) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"];
    //     else if (speed <= 14) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"] + vector["羽短4"];
    //     else if (speed <= 16) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"] + vector["羽长4"];
    //     else if (speed <= 18) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"] + vector["羽长4"] + vector["羽短5"];
    //     else if (speed <= 20) path += vector["角1"];
    //     else if (speed <= 22) path += vector["角1"] + vector["羽短3"];
    //     else if (speed <= 24) path += vector["角1"] + vector["羽长3"];
    //     else if (speed <= 26) path += vector["角1"] + vector["羽长3"] + vector["羽短4"];
    //     else if (speed <= 28) path += vector["角1"] + vector["羽长3"] + vector["羽长4"];
    //     else if (speed <= 30) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽短5"];
    //     else if (speed <= 32) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"];
    //     else if (speed <= 34) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"] + vector["羽短6"];
    //     else if (speed <= 36) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"] + vector["羽长6"];
    //     else if (speed <= 38) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"] + vector["羽长6"] + vector["羽短7"];
    //     else if (speed <= 40) path += vector["角1"] + vector["角2"];
    //     else if (speed <= 42) path += vector["角1"] + vector["角2"] + vector["羽短5"];
    //     else if (speed <= 44) path += vector["角1"] + vector["角2"] + vector["羽长5"];
    //     else if (speed <= 46) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽短6"];
    //     else if (speed <= 48) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"];
    //     else if (speed <= 50) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽短7"];
    //     else if (speed <= 52) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽长7"];
    //     else if (speed <= 54) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽长7"] + vector["羽短8"];
    //     else path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽长7"] + vector["羽长8"];
    //     icon = `<svg viewBox="0 0 1024 1024">
    //                 <path fill="${color}"
    //                     d="M512 1024q94.2336-105.088 306.304-355.1488 102.0928-137.5232 102.0928-285.0048C920.3968 172.032 737.536 1.536 512 1.536 286.464 1.536 103.6032 172.032 103.6032 382.336q0 147.4816 102.1184 285.0048Q417.7664 917.4016 512 1024z"
    //                 ></path>
    //                 <circle cx="512" cy="402" r="360" fill="#000" />
    //                 <g fill="${color}" color="${color}" transform-origin="center" transform="translate(0,-115) rotate(${direction}) scale(0.53)">${path}</g>
    //             </svg>`;
    // }
    return icon;
}

function removeLayerById(id) {
    if (GLOBAL_CACHE[id]) {
        GLOBAL_CACHE[id].remove();
        GLOBAL_CACHE[id] = null;
    }
}
</script>
