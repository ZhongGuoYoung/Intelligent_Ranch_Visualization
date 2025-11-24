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
                <div class="title">起报时间：</div>
                <a-date-picker v-model:value="fcstTime"
                               style="width:150px;"
                               class="my-ant-time-input"
                               :show-time="{ format: 'HH', hideDisabledOptions: true }"
                               format="YYYY-MM-DD HH"
                               :locale="locale"
                               :disabled-time="antDisabledDateTime" />
            </div>
            <div>
                <div class="title">制作时间：</div>
                <a-date-picker v-model:value="makeTime"
                               style="width:150px;"
                               class="my-ant-time-input"
                               format="YYYY-MM-DD"
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

const antDisabledDateTime = (current) => {
    let abledHours = [8],
        disabledHours = Array.from({ length: 24 }, (v, i) => i).filter(v => !abledHours.includes(v));
    return {
        disabledHours: () => disabledHours,
    };
};
const fcstTime = ref(dayjs().set("hour", 8)),
    makeTime = ref(dayjs()),
    unit = ref("吴忠市气象台"),
    author = ref("刘沛");
const wObj = ref({
    title: "牧场常规天气预报",
    unit: unit.value,
    time: makeTime.value.format("YYYY-MM-DD"),
    author: "签发：" + author.value,
    paragraph: [{ type: "text", title: "", data: "" }]
});




function makeWord() {
    wObj.value.unit = unit.value;
    wObj.value.time = makeTime.value.format("YYYY-MM-DD");
    wObj.value.author = "签发：" + author.value;
    wObj.value.paragraph = [{ type: "text", title: "牧场未来十日天气预报如下：", data: "" }];
    
    let data = [
        { minTem: 21, maxTem: 31, minRhu: 41, maxRhu: 51, winS: 2, winL: "2级", winD: "西北风", datetime: fcstTime.value.add(0, "day").format("YYYY-MM-DD HH:mm:ss") },
        { minTem: 22, maxTem: 32, minRhu: 42, maxRhu: 52, winS: 2, winL: "1级", winD: "西北风", datetime: fcstTime.value.add(1, "day").format("YYYY-MM-DD HH:mm:ss") },
        { minTem: 23, maxTem: 33, minRhu: 43, maxRhu: 53, winS: 2, winL: "2级", winD: "西北风", datetime: fcstTime.value.add(2, "day").format("YYYY-MM-DD HH:mm:ss") },
        { minTem: 24, maxTem: 34, minRhu: 44, maxRhu: 54, winS: 2, winL: "3级", winD: "西北风", datetime: fcstTime.value.add(3, "day").format("YYYY-MM-DD HH:mm:ss") },
        { minTem: 25, maxTem: 35, minRhu: 45, maxRhu: 55, winS: 2, winL: "4级", winD: "西北风", datetime: fcstTime.value.add(4, "day").format("YYYY-MM-DD HH:mm:ss") },
        { minTem: 26, maxTem: 36, minRhu: 46, maxRhu: 56, winS: 2, winL: "5级", winD: "西北风", datetime: fcstTime.value.add(5, "day").format("YYYY-MM-DD HH:mm:ss") },
        { minTem: 27, maxTem: 37, minRhu: 47, maxRhu: 57, winS: 2, winL: "6级", winD: "西北风", datetime: fcstTime.value.add(6, "day").format("YYYY-MM-DD HH:mm:ss") },
        { minTem: 28, maxTem: 38, minRhu: 48, maxRhu: 58, winS: 2, winL: "7级", winD: "西北风", datetime: fcstTime.value.add(7, "day").format("YYYY-MM-DD HH:mm:ss") },
        { minTem: 29, maxTem: 39, minRhu: 49, maxRhu: 59, winS: 2, winL: "8级", winD: "西北风", datetime: fcstTime.value.add(8, "day").format("YYYY-MM-DD HH:mm:ss") },
        { minTem: 30, maxTem: 31, minRhu: 40, maxRhu: 60, winS: 2, winL: "9级", winD: "西北风", datetime: fcstTime.value.add(9, "day").format("YYYY-MM-DD HH:mm:ss") }
    ]
    wObj.value.paragraph[0].data =
        data.map(v =>
            dayjs(v.datetime).format("DD日08时") + "至" + dayjs(v.datetime).add(1, "day").format("DD日08时") + "，" +
            v.winD + v.winL + "，" +
            "温度" + v.minTem + "℃至" + v.maxTem + "℃，" +
            "湿度" + v.minRhu + "%至" + v.maxRhu + "%"
        ).join("；\n")
        + "。"
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
        var path = response.data; console.log(interfaces.serverUrl + path.substring(path.indexOf("data")))
        // window.location.href = interfaces.serverUrl + path.substring(path.indexOf("data"));
    }).catch(function (error) {
        ElMessage({
            showClose: true,
            message: "下载失败",
            type: "error",
            offset: document.documentElement.clientHeight / 2
        })
    });
}
</script>
