<style scoped lang="scss">
.view {
    position: relative;

    .word-setting {
        position: absolute;
        top: 0;
        left: 20px;
        height: 80px;
        display: flex;
        align-items: center;
        gap: 20px;

        &>div {
            display: flex;
            align-items: center;

            .title {
                width: max-content;
            }
        }
    }
}
</style>

<template>
    <div class="view">
        <div class="word-setting">
            <div>
                <div class="title">预报统计时段：</div>
                <a-range-picker v-model:value="fcstTimeRange"
                                style="width:300px;"
                                class="my-ant-time-range-input"
                                :show-time="{ format: 'HH' }"
                                format="YYYY-MM-DD HH"
                                :locale="locale"
                                separator="至" />
            </div>
            <div>
                <div class="title">制作时间：</div>
                <a-date-picker v-model:value="makeTime"
                               style="width:150px;"
                               class="my-ant-time-input"
                               :show-time="{ format: 'HH' }"
                               format="YYYY-MM-DD HH"
                               :locale="locale" />
            </div>
            <div>
                <div class="title">单位：</div>
                <el-input v-model="unit"
                          style="width:140px;"
                          class="my-el-input"></el-input>
            </div>
            <div>
                <div class="title">签发：</div>
                <el-input v-model="author"
                          style="width:80px;"
                          class="my-el-input"></el-input>
            </div>
        </div>

        <MyWord v-model="wObj"
                @makeWord="makeWord"
                @downloadWord="downloadWord" />
    </div>
</template>

<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus"
import axios from "axios";

import dayjs from "dayjs"
import "dayjs/locale/zh-cn";
import locale from "ant-design-vue/es/date-picker/locale/zh_CN";

import interfaces from "@/components/interfaces.js";
import MyWord from "@/components/my-word/index.vue";


const makeTime = ref(dayjs()),
    unit = ref("吴忠市气象台"),
    author = ref("刘沛");
const wObj = ref({
    title: "奶牛热应激指数精细化预报",
    time: makeTime.value.format("YYYY-MM-DD HH"),
    author: author.value,
    unit: unit.value,
    paragraph: [
        { type: "text", title: "一、热应激程度预报", data: "" },
        { type: "text", title: "二、热应激指数预报图", data: "" },
        { type: "chart", title: "", data: "", xAxis: {}, series: {} },
        { type: "text", title: "三、热应激防御措施参照表", data: "" },
        {
            type: "table", title: "", data: [],
            column: [
                { prop: "zs", label: "湿热指数值" },
                { prop: "cd", label: "热应激程度" },
                { prop: "time", label: "关键防御时段" },
            ],
        },
    ]
});





function makeWord() {
    wObj.value.unit = unit.value;
    wObj.value.time = makeTime.value.format("YYYY-MM-DD");
    wObj.value.author = "签发：" + author.value;
    wObj.value.paragraph = [
        { type: "text", title: "一、热应激程度预报", data: "" },
        { type: "text", title: "二、热应激指数预报图", data: "" },
        { type: "chart", title: "", data: "", xAxis: {}, series: {} },
        { type: "text", title: "三、热应激防御措施参照表", data: "" },
        {
            type: "table", title: "", data: [],
            column: [
                { prop: "zs", label: "湿热指数值" },
                { prop: "cd", label: "热应激程度" },
                { prop: "time", label: "关键防御时段" },
            ],
        },
    ]

    let data = [
        { tem: 21, rhu: 51, datetime: "2025-07-01 09:00:00" },
        { tem: 22, rhu: 52, datetime: "2025-07-01 10:00:00" },
        { tem: 23, rhu: 53, datetime: "2025-07-01 11:00:00" },
        { tem: 24, rhu: 54, datetime: "2025-07-01 12:00:00" },
        { tem: 25, rhu: 55, datetime: "2025-07-01 13:00:00" },
        { tem: 26, rhu: 56, datetime: "2025-07-01 14:00:00" },
        { tem: 27, rhu: 57, datetime: "2025-07-01 15:00:00" },
        { tem: 28, rhu: 58, datetime: "2025-07-01 16:00:00" },
        { tem: 29, rhu: 59, datetime: "2025-07-01 17:00:00" },
        { tem: 30, rhu: 60, datetime: "2025-07-01 18:00:00" },
        { tem: 31, rhu: 61, datetime: "2025-07-01 19:00:00" },
        { tem: 32, rhu: 62, datetime: "2025-07-01 20:00:00" },
        { tem: 33, rhu: 63, datetime: "2025-07-01 21:00:00" },
        { tem: 34, rhu: 64, datetime: "2025-07-01 22:00:00" },
        { tem: 35, rhu: 65, datetime: "2025-07-01 23:00:00" },
        { tem: 36, rhu: 66, datetime: "2025-07-02 00:00:00" },
        { tem: 37, rhu: 67, datetime: "2025-07-02 01:00:00" },
        { tem: 38, rhu: 68, datetime: "2025-07-02 02:00:00" },
        { tem: 39, rhu: 69, datetime: "2025-07-02 03:00:00" },
        { tem: 40, rhu: 70, datetime: "2025-07-02 04:00:00" },
        { tem: 21, rhu: 51, datetime: "2025-07-02 05:00:00" },
        { tem: 42, rhu: 72, datetime: "2025-07-02 06:00:00" },
        { tem: 43, rhu: 73, datetime: "2025-07-02 07:00:00" },
        { tem: 44, rhu: 74, datetime: "2025-07-02 08:00:00" },
    ]
    wObj.value.paragraph[0].data = "根据吴忠市逐小时精细化预报，具体预报如下：\n"
        + data.map(v => {
            let zs = getStressLevel(v.tem, v.rhu);
            v.zs = zs;
            return dayjs(v.datetime).format("DD日HH时") + "，" +
                "温度为" + v.tem + "℃，" +
                "湿度为" + v.rhu + "%，" +
                "应激指数为" + zs
        }).join("；\n")
        + "。"

    wObj.value.paragraph[2].xAxis = { "时间": data.map(v => dayjs(v.datetime).format("HH")) };
    wObj.value.paragraph[2].series = { "应激指数": data.map(v => v.zs) };







    let p3Data = [
        { zs: [63.0, 67.9], cd: "无应激", time: "" },
        { zs: [68.0, 71.9], cd: "轻度应激", time: "" },
        { zs: [72.0, 79.9], cd: "中度应激", time: "" },
        { zs: [80.0, 89.9], cd: "重度应激", time: "" },
        { zs: [90.0, 107.9], cd: "严重应激", time: "" },
    ]
    for (let i = 0; i < p3Data.length; i++) {
        let item = p3Data[i];
        let filter = data.filter(v => {
            let zs = getStressLevel(v.tem, v.rhu);
            return item.zs[0] <= zs && zs <= item.zs[1];
        })
        let box = [];
        let range = [];
        for (let j = 0; j < filter.length; j++) {
            let curr = filter[j].datetime;
            let next = filter[j + 1]?.datetime;
            if (range[0] == undefined) { range[0] = curr; }
            if (next == undefined || dayjs(next).diff(dayjs(curr), "hour") > 1) {
                if (curr != range[0]) { range[1] = curr; }
                box.push(range);
                range = [];
            }
        }
        item.time = box.map(v1 => v1.map(v2 => dayjs(v2).format("DD日HH时")).join(" ~ ")).join("、");
        item.zs = item.zs.join(" ~ ");
    }
    wObj.value.paragraph[4].data = p3Data;
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
        level = "无应激";
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
    return threshold;
}









// ---------- 下载文档 ----------

function downloadWord() {
    axios.post(interfaces.serviceUrl + "word/create", {
        title: wObj.value.title,
        unit: wObj.value.unit,
        author: wObj.value.author,
        time: wObj.value.time,
        paragraph: wObj.value.paragraph,
    }).then(function (response) {
        let data = response.data;
        let path = data.data;
        window.location.href = interfaces.serverUrl + path.substring(path.indexOf("word"));
    })
        .catch(function (error) {
            ElMessage({
                showClose: true,
                message: "下载失败",
                type: "error",
                offset: document.documentElement.clientHeight / 2
            })
        });
}
</script>
