<!-- eslint-disable vue/multi-word-component-names -->
<style scoped lang="scss">
.view {
    width: 100%;
    height: 100%;

    .header {
        width: fit-content;
        height: 80px;
        margin: auto;
        position: absolute;
        left: 0;
        right: 0;
        display: flex;
        align-items: center;
    }

    main {
        padding: 0;
    }
}
</style>
<template>
    <el-container class="view">
        <el-header class="header">
            <a-date-picker v-model:value="timeChecked"
                           class="my-ant-time-input"
                           :show-time="{
                            format: 'HH',
                        }"
                           format="YYYY-MM-DD HH"
                           :locale="locale"
                           @ok="antDateOk" />
        </el-header>
        <el-main class="main">
            <MyWord v-model="wordTemplate"
                    @makeWord="getFcstData"
                    @downloadWord="downloadWord" />
        </el-main>
    </el-container>
</template>

<script setup>
import dayjs from "dayjs"
import "dayjs/locale/zh-cn";
import locale from "ant-design-vue/es/date-picker/locale/zh_CN";
import { ref, onMounted } from "vue";
import axios from "axios";
import { DocumentAdd, Search, Check, ArrowDown, Minus, Plus, FullScreen } from "@element-plus/icons-vue";
import MyWord from "@/components/my-word/index.vue";
import interfaces from "@/components/interfaces.js";

let timeChecked = ref(dayjs().set("minute", 0).set("second", 0));
let wordTemplate = ref({
    title: "奶牛热应激指数精细化预报",
    unit: "吴忠市气象台",
    time: timeChecked.value.format("YYYY-MM-DD HH"),
    author: "刘沛",
    paragraph: [
        { type: "text", title: "一、热应激程度预报", data: "" },
        { type: "text", title: "二、热应激指数预报图", data: "" },
        { type: "chart", title: "热应激指数预报图", data: "", xAxis: {}, series: {} },
    ]
});


function getFcstData() {
    wordTemplate.value.paragraph[0].data =
        `根据吴忠市逐小时精细化预报,具体预报如下：
        14日08时，温度为23.6℃，湿度为47.2%，应激指数为69.5，应激程度为轻度应激;
        14日09时，温度为21.5℃，湿度为52.1%，应激指数为67.9，应激程度为无应激;
        14日10时，温度为18℃，湿度为61.4%，应激指数为65.8，应激程度为无应激;
        14日11时，温度为16.5℃，湿度为66.5%，应激指数为65.8，应激程度为无应激;
        14日12时，温度为13.3℃，湿度为70.6%，应激指数为66.4，应激程度为无应激;
        14日13时，温度为13.1℃，湿度为78.1%，应激指数为66.4，应激程度为无应激;
`

    wordTemplate.value.paragraph[2].xAxis = { "时间": [8, 9, 10, 11, 12, 13] }
    wordTemplate.value.paragraph[2].series = { "热应激指数": [58, 69, 10, 41, 52, 33] }

}


function downloadWord() {
    axios.post(interfaces.serviceUrl + "word/create", {
        title: wordTemplate.value.title,
        unit: wordTemplate.value.unit,
        time: wordTemplate.value.time,
        author: wordTemplate.value.author,
        paragraph: wordTemplate.value.paragraph,
    }).then(response => {
        let data = response.data.data;
        console.log(data)
        window.location.href = "http://localhost:5173/zhmc/public/" + data.substring(data.indexOf("word"))
    })
}





</script>
