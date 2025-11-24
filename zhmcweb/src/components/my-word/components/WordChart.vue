<style scoped lang="scss">
.word-chart-wrap {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 0.5em 0;

    .title {
        font-size: 16px;
        text-align: center;
        margin: 0.5em 0;
    }

    .content {
        width: 100%;
        min-width: 400px;
        min-height: 200px;
        border-radius: 4px;
        box-shadow: 0 0 2px rgba(0, 0, 0, 0.3);
        background-color: rgba(0, 0, 0, 0.01);
        display: flex;
        align-items: center;
        justify-content: center;

        &>div {
            width: 100%;
            height: 100%;
        }
    }
}
</style>
<template>
    <div class="word-chart-wrap">
        <div class="content"
             :style="{
                width: props.width + 'px',
                height: props.height + 'px',
            }">
            <div ref="chartDom"></div>
        </div>
        <div class="title"
             v-if="props.title">{{ props.title }}</div>
    </div>
</template>

<script setup>
import { ref, watch, nextTick, onMounted } from "vue";
import * as echarts from "echarts";

const model = defineModel();
const props = defineProps({
    title: { type: String, default: "" },
    xAxis: { type: Object, default: (rawProps) => ({}) },
    // yAxis: { type: Array, default: (rawProps) => ([]) },
    series: { type: Object, default: (rawProps) => ({}) },
    width: { type: Number, default: undefined },
    height: { type: Number, default: 100 },
});
const chartDom = ref();


watch(() => props.series, (newValue, oldValue) => {
    renderChart(props.xAxis, props.series);
})

function renderChart(xAxisData, seriesData) {
    let textColor = "#000";
    let option = {
        // color: ["rgb(128, 255, 165)"],
        grid: { left: 0, right: 0, bottom: 0, top: 36, containLabel: true },
        legend: {
            textStyle: { color: textColor },
            data: Object.entries(seriesData).map(([k, v], i) => ({ name: k, icon: "roundRect" }))
        },
        tooltip: {
            trigger: "axis",
            axisPointer: { type: "cross" },
            textStyle: { align: "left" },
            formatter: (params) => {
                return `<div>${params[0].name}</div>
                <div style="display:grid;grid-template-columns:repeat(3,auto);gap:5px;align-items:center;">
                ${params.map(v => {
                    return `${v.marker}<span>${v.seriesName}</span><span>${v.value ?? "--"}</span>`
                }).join("")}
                </div>`;
            },
        },
        toolbox: {
            iconStyle: { borderColor: textColor },
            emphasis: { iconStyle: { shadowBlur: 1, shadowColor: "#000", textFill: textColor } },
            itemSize: 16,
            feature: {
                magicType: {
                    type: ["line", "bar"],
                    option: {
                        bar: {
                            emphasis: {
                                focus: "series",
                                itemStyle: { shadowBlur: 6, shadowColor: "rgba(0, 0, 0, 0.3)" },
                            },
                            itemStyle: {
                                color: (obj) => {
                                    return new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                        { offset: 0, color: obj.color },
                                        { offset: 1, color: obj.color + "4c" }
                                    ])
                                }
                            },
                        },
                    },
                },
            },
        },
        xAxis: {
            name: "",
            data: Object.values(xAxisData)[0],
            nameTextStyle: { color: textColor, fontSize: 14 },
            axisLabel: { color: textColor, fontSize: 14 },
            axisLine: { lineStyle: { color: "#33dba9" } },
            axisTick: { alignWithLabel: true },
        },
        yAxis: {
            name: "",
            type: "value",
            nameTextStyle: { color: textColor, fontSize: 14 },
            axisLabel: { color: textColor, fontSize: 14 },
            axisLine: { lineStyle: { color: "#33dba9" } },
        },
        series: Object.entries(seriesData).map(([k, v], i) => {
            return {
                data: v,
                name: k,
                type: "line",
                smooth: true,
                showSymbol: false,
                lineStyle: { width: 2 },
                emphasis: { focus: "series" },
                // areaStyle: {
                //     color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                //         { offset: 1, color: "rgba(128, 255, 165, 0.3)" },
                //         { offset: 0, color: "rgb(128, 255, 165)" },
                //     ]),
                // },
            }
        })
    };

    nextTick(() => {
        let mychart = echarts.init(chartDom.value, null, { locale: "ZH", });
        mychart.clear();
        mychart.setOption(option);
        mychart.on("finished", () => {
            model.value = mychart.getDataURL({
                type: "png",
                pixelRatio: 2,
                // excludeComponents: ["toolbox"]
            });
        });
    })
}

</script>
