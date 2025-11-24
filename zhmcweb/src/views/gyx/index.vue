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
        display: grid;
        grid: 100%/repeat(2, 1fr);
        padding: 10px 30px;
        overflow: hidden;
        gap: 15px;

        .right,
        .left {
            display: grid;
            grid: auto 1fr 1fr/100%;
            gap: 5px;

            .yb-box {
                display: grid;
                grid: 300px/auto 300px 180px;
                gap: 5px;
                position: relative;

                &>div {
                    background-color: rgba(255, 255, 255, 0.1);
                }

                .yjzs-box {
                    display: flex;
                    align-items: center;
                    color: #fff;
                    font-size: 16px;
                    line-height: 1.5em;
                    text-align: left;
                    padding: 10px;

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
                        background: url(@/assets/images/奶牛风险/mc-nainiu7_03.png) left center/cover no-repeat;
                        display: grid;
                        grid: repeat(5, 1fr)/auto auto;
                        padding: 8px 12px 20px 30px;
                    }
                }

                .rhu-box {
                    width: 300px;
                    height: 300px;
                    background-color: rgba(255, 255, 255, 0.1);
                }
            }

            .tc-box {
                display: flex;
                flex-direction: column;
            }
        }
    }
}
</style>

<template>
    <el-container class="view">
        <el-header class="header">
            <a-range-picker class="my-ant-time-input"
                            v-model:value="selectTime"
                            separator="至"
                            format="YYYY-MM-DD HH"
                            :show-time="{ format: 'HH' }"
                            :locale="locale"
                            :presets="antPresets"
                            @calendarChange="antCalendarChange"
                            @openChange="(status) => { showAntOpenChange = status }"
                            @ok="antDateOK" />
        </el-header>
        <el-main class="main">
            <div class="left">
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
                                :="cnlTCOpt152" />
                </div>
                <div class="tc-box">
                    <TableChart v-model="tCData"
                                :="yjzsTCOpt152" />
                </div>
            </div>
            <div class="right">
                <div class="yb-box">
                    <div class="yjzs-box">
                        <div><img :src="lastData.icon203"></div>
                        <div>
                            <span>观测时间：</span><span>{{ lastData.datetime }}</span>
                            <span>温度：</span><span>{{ lastData.tem203 }}℃</span>
                            <span>湿度：</span><span>{{ lastData.rhu203 }}%</span>
                            <span>奶牛状态：</span><span>{{ lastData.yjzt203 }}</span>
                            <span>应激指数：</span><span>{{ lastData.yjzs203 }}</span>
                        </div>
                    </div>
                    <div class="rhu-box"
                         id="203-rhu-chart">
                    </div>
                    <div class="tem-box"
                         id="203-tem-chart"></div>
                </div>
                <div class="tc-box">
                    <TableChart v-model="tCData"
                                :="cnlTCOpt152" />
                </div>
                <div class="tc-box">
                    <TableChart v-model="tCData"
                                :="yjzsTCOpt152" />
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script setup>
import { ref, onMounted, computed, nextTick } from "vue";
import axios from "axios";
import dayjs from "dayjs";
import "dayjs/locale/zh-cn";
import locale from "ant-design-vue/es/date-picker/locale/zh_CN";
import TableChart from "@/components/my-table-chart/index.vue";
import { getRandomNumber } from "@/components/utils.js";
import * as echarts from "echarts";

const selectTime = ref([dayjs().add(-24, "hour"), dayjs()]);
selectTime.value = [dayjs("2025-05-09 10").add(-24, "hour"), dayjs("2025-05-09 10")];
const antPresets = ref([
    { label: "前1小时", value: [selectTime.value[1].add(-1, "hour"), selectTime.value[1]] },
    { label: "前2小时", value: [selectTime.value[1].add(-2, "hour"), selectTime.value[1]] },
    { label: "前3小时", value: [selectTime.value[1].add(-3, "hour"), selectTime.value[1]] },
    { label: "前6小时", value: [selectTime.value[1].add(-6, "hour"), selectTime.value[1]] },
    { label: "前12小时", value: [selectTime.value[1].add(-12, "hour"), selectTime.value[1]] },
    { label: "前24小时", value: [selectTime.value[1].add(-24, "hour"), selectTime.value[1]] }
]),
    antCalendarChange = (range) => {
        antPresets.value = [
            { label: "前1小时", value: [range[1].add(-1, "hour"), range[1]] },
            { label: "前2小时", value: [range[1].add(-2, "hour"), range[1]] },
            { label: "前3小时", value: [range[1].add(-3, "hour"), range[1]] },
            { label: "前6小时", value: [range[1].add(-6, "hour"), range[1]] },
            { label: "前12小时", value: [range[1].add(-12, "hour"), range[1]] },
            { label: "前24小时", value: [range[1].add(-24, "hour"), range[1]] }]
    };
const showAntOpenChange = ref(false);
function antDateOK(date) {
    if (!showAntOpenChange.value) {
        console.log(date)
    }
}




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
            { prop: "cnl152", label: "产奶量", unit: "ml", axis: "y", sortable: true },
            { prop: "tem152", label: "温度", unit: "℃", axis: "y", sortable: true },
            { prop: "rhu152", label: "湿度", unit: "%", axis: "y", sortable: true },
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
            { prop: "yjzs152", label: "应激指数", unit: "", axis: "y", sortable: true },
            { prop: "tem152", label: "温度", unit: "℃", axis: "y", sortable: true },
            { prop: "rhu152", label: "湿度", unit: "%", axis: "y", sortable: true },
        ],
    },
    cnlTCOpt203 = {
        title: "产奶量对比",
        toolbar: [
            { name: "图表转换", mode: true },
            { name: "行列转换", mode: false },
            { name: "导出表格" }
        ],
        column: [
            { width: "140px", prop: "datetime", label: "时间", axis: "x", isHeader: true, disable: true, format: (v) => dayjs(v).format("DD日HH时"), sortable: true },
            { prop: "cnl203", label: "产奶量", unit: "ml", axis: "y", sortable: true },
            { prop: "tem203", label: "温度", unit: "℃", axis: "y", sortable: true },
            { prop: "rhu203", label: "湿度", unit: "%", axis: "y", sortable: true },
        ],
    },
    yjzsTCOpt203 = {
        title: "应激指数对比",
        toolbar: [
            { name: "图表转换", mode: true },
            { name: "行列转换", mode: false },
            { name: "导出表格" }
        ],
        column: [
            { width: "140px", prop: "datetime", label: "时间", axis: "x", isHeader: true, disable: true, format: (v) => dayjs(v).format("DD日HH时"), sortable: true },
            { prop: "yjzs203", label: "应激指数", unit: "", axis: "y", sortable: true },
            { prop: "tem203", label: "温度", unit: "℃", axis: "y", sortable: true },
            { prop: "rhu203", label: "湿度", unit: "%", axis: "y", sortable: true },
        ],
    },
    lastData = ref({});


onMounted(() => {
    getData();
})


function getData() {

    let data = Array.from({ length: selectTime.value[1].diff(selectTime.value[0], "hour") + 1 }, (v, i) => ({
        datetime: selectTime.value[0].add(i, "hour").format("YYYY-MM-DD HH:00:00"),
        yjzs152: getRandomNumber(40, 63),
        cnl152: getRandomNumber(20, 33),
        tem152: getRandomNumber(10, 23),
        rhu152: getRandomNumber(40, 70),
        yjzs203: getRandomNumber(40, 63),
        cnl203: getRandomNumber(20, 33),
        tem203: getRandomNumber(10, 23),
        rhu203: getRandomNumber(40, 70),
    }));


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

    dashboardChart({
        eId: "203-rhu-chart",
        data: tCData.value[tCData.value.length - 1].rhu203
    });
    thermometerChart({
        eId: "203-tem-chart",
        data: tCData.value[tCData.value.length - 1].tem203
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
        let dom = document.getElementById(info.eId);
        // if (dom._echarts_instance_) dom.removeAttribute("_echarts_instance_");
        let myChart = echarts.init(dom, null, { locale: "ZH" });
        myChart.clear();
        myChart.setOption(option);
    })
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
            offset: -myChartDom.getBoundingClientRect().width / 2 + barSize[0] / 2 + 5
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
    let thresholds = [
        [63.0, 63.5, 64.1, 64.7, 65.3, 65.8, 66.4, 67.0, 67.5, 68.1],
        [63.9, 64.5, 65.2, 65.9, 66.6, 67.2, 67.9, 68.6, 69.2, 69.9],
        [64.8, 65.5, 66.3, 67.1, 67.9, 68.6, 69.4, 70.2, 70.9, 71.7],
        [65.7, 66.5, 67.4, 68.3, 69.2, 70.0, 70.9, 71.8, 72.6, 73.5],
        [66.6, 67.5, 68.5, 69.5, 70.5, 71.4, 72.4, 73.4, 74.3, 75.3],
        [67.5, 68.5, 69.6, 70.7, 71.8, 72.8, 73.9, 75.0, 76.0, 77.1],
        [68.4, 69.5, 70.7, 71.9, 73.1, 74.2, 75.4, 76.6, 77.7, 78.9],
        [69.3, 70.5, 71.8, 73.1, 74.4, 75.6, 76.9, 78.2, 79.4, 80.7],
        [70.2, 71.5, 72.9, 74.3, 75.7, 77.0, 78.4, 79.8, 81.1, 82.5],
        [71.1, 72.5, 74.0, 75.5, 77.0, 78.4, 79.9, 81.4, 82.8, 84.3],
        [72.0, 73.5, 75.1, 76.7, 78.3, 79.8, 81.4, 83.0, 84.5, 86.1],
        [72.9, 74.5, 76.2, 77.9, 79.6, 81.2, 82.9, 84.6, 86.2, 87.9],
        [73.8, 75.5, 77.3, 79.1, 80.9, 82.6, 84.4, 86.2, 87.9, 89.7],
        [74.7, 76.5, 78.4, 80.3, 82.2, 84.0, 85.9, 87.8, 89.6, 91.5],
        [75.6, 77.5, 79.5, 81.5, 83.5, 85.4, 87.4, 89.4, 91.3, 93.3],
        [76.5, 78.5, 80.6, 82.7, 84.8, 86.8, 88.9, 91.9, 93.0, 95.1],
        [77.4, 79.5, 81.7, 83.9, 86.1, 88.2, 90.4, 92.6, 94.7, 96.9],
        [78.3, 80.5, 82.8, 85.1, 87.4, 89.6, 94.9, 94.2, 96.4, 98.7],
        [79.2, 81.5, 83.9, 86.3, 88.7, 91.0, 96.4, 95.8, 98.1, 100.5],
        [80.1, 82.5, 85.0, 87.5, 90.0, 92.4, 94.9, 97.4, 99.8, 102.3],
        [81.0, 83.5, 86.1, 88.7, 91.3, 93.8, 96.4, 99.0, 101.5, 104.1],
        [81.9, 84.5, 87.2, 89.9, 92.6, 95.2, 97.9, 100.5, 103.2, 105.9],
        [82.8, 85.5, 88.3, 91.1, 93.9, 96.6, 99.4, 102.2, 104.9, 107.7]
    ];

    if (tem < 20) { tem = 20; }
    if (tem > 42) { tem = 42; }
    if (rhu < 0) { rhu = 0; }
    if (rhu > 100) { rhu = 100.0; }

    let y = Math.floor(tem - 20),
        x = Math.floor(rhu / 10),
        threshold = thresholds[y][x],
        level = "";
    if (68.0 > threshold && threshold >= 63.0) {
        level = "正常";
    } else if (72.0 > threshold && threshold >= 68.0) {
        level = "轻度应激";
    } else if (80.0 > threshold && threshold >= 72.0) {
        level = "中度应激";
    } else if (90.0 > threshold && threshold >= 80.0) {
        level = "重度应激";
    } else if (107.7 >= threshold && threshold >= 90.0) {
        level = "严重应激";
    } else {
        level = "无法计算";
    }
    return { threshold, level };
}
</script>