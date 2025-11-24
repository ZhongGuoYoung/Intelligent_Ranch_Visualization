<!-- eslint-disable vue/multi-word-component-names -->
<style scoped>
.fx-legend-wrap.horizontal {
    width: 100%;
    display: flex;
    align-items: center;
    background: #0b4f6c;
    padding: 12px 20px;
    gap: 20px;
    box-shadow: 0 0 4px #000;
    font-size: 14px;

    .header {
        width: max-content;
        text-align: center;
    }

    .main {
        flex: 1;
        display: flex;
        flex-direction: column;
        gap: 4px;

        .color-box,
        .level-box,
        .extra-box {
            width: 100%;
            display: flex;

            .level,
            .extra {
                flex: 1;
            }

            label {
                width: 0;
            }

            .color {
                height: 8px;
                flex: 1;

                &:first-child {
                    border-radius: 5px 0 0 5px;
                }

                &:last-child {
                    border-radius: 0 5px 5px 0;
                }
            }
        }

        .color-slider-box {
            width: 100%;
            display: flex;
            align-items: center;
            position: relative;

            .slider-box {
                position: absolute;
            }

            :deep(.el-slider__runway) {
                background-color: transparent;

                .el-slider__bar {
                    background-color: transparent;
                }
            }
        }
    }
}
</style>

<template>
    <div class="fx-legend-wrap"
         v-if="props.vertical">
    </div>
    <div class="fx-legend-wrap horizontal"
         v-else>
        <div class="header"
             v-if="name">
            {{ name }}<br v-if="unit" />{{ unit }}
        </div>
        <div class="main">
            <div class="level-box"
                 v-if="level && level.length > 0 && (extra && extra.length > 0)">
                <template v-for="val in level"
                          :key="val">
                    <span class='level'></span><label :style="{ textIndent: -val.toString().length / 2 + 'ch' }">{{ val }}</label>
                </template>
                <span class='level'></span>
            </div>
            <div class="color-slider-box">
                <div class="color-box">
                    <span class="color"
                          v-for="(val, i) in color"
                          :key="i"
                          :style="{
                            backgroundColor: val,
                            opacity: filter[0] <= i && i <= filter[1] - 1 ? 1 : 0.3
                        }">
                    </span>
                </div>
                <el-slider class="slider-box"
                           v-model="filter"
                           :max="color.length"
                           :show-tooltip="false"
                           range
                           @change="props.filterCallback" />
            </div>
            <div class="extra-box"
                 v-if="extra && extra.length > 0">
                <span class="extra"
                      v-for="val in extra"
                      :key="val">{{ val }}</span>
            </div>
            <!-- <div class="level-box"
                 v-if="level && level.length > 0 && !(extra && extra.length > 0)">
                <template v-for="val in level"
                          :key="val">
                    <span class='level'></span><label :style="{ textIndent: -val.toString().length / 2 + 'ch' }">{{ val }}</label>
                </template>
                <span class='level'></span>
            </div> -->
        </div>
    </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';

const props = defineProps({
    choose: { type: String, default: undefined },   // 选择内置的图例
    name: { type: String, default: undefined },     // 名称
    unit: { type: String, default: undefined },     // 单位
    color: { type: Array, default: undefined },     // 颜色数组
    level: { type: Array, default: undefined },     // 等级数组
    extra: { type: Array, default: undefined },     // 额外数组
    reverse: { type: Boolean, default: false },     // 是否将 color、level、extra 倒序

    filter: { type: Array, default: () => null },
    vertical: { type: Boolean, default: false },

    colorStyle: {
        type: Object, default: (rawProps) => {
            const colorStyle = rawProps.colorStyle || {}
            return {
                width: colorStyle.width || 60,
                height: colorStyle.height || 12,
            }
        }
    },
    filterCallback: { type: Function, default: () => 'function' },
})

const builtInLegends = {
    '1小时降水': {
        'name': '降水', 'unit': '(mm)',
        'level': [0.1, 1.6, 7, 15, 40, 50],
        'color': ['#ffffff', '#a3f28f', '#3dba3d', '#61b8ff', '#0500e1', '#fa00fa', '#800040'],
        // 'extra': ['无', '小雨', '中雨', '大雨', '暴雨', '大暴雨', '特大暴雨'],
    },
    '3小时降水': {
        'name': '降水', 'unit': '(mm)',
        'level': [0.1, 3, 10, 20, 50, 70],
        'color': ['#ffffff', '#a3f28f', '#3dba3d', '#61b8ff', '#0500e1', '#fa00fa', '#800040'],
        // 'extra': ['无', '小雨', '中雨', '大雨', '暴雨', '大暴雨', '特大暴雨'],
    },
    '6小时降水': {
        'name': '降水', 'unit': '(mm)',
        'level': [0.1, 4, 13, 25, 60, 120],
        'color': ['#ffffff', '#a3f28f', '#3dba3d', '#61b8ff', '#0500e1', '#fa00fa', '#800040'],
        // 'extra': ['无', '小雨', '中雨', '大雨', '暴雨', '大暴雨', '特大暴雨'],
    },
    '12小时降水': {
        'name': '降水', 'unit': '(mm)',
        'level': [0.1, 5, 15, 30, 70, 140],
        'color': ['#ffffff', '#a3f28f', '#3dba3d', '#61b8ff', '#0500e1', '#fa00fa', '#800040'],
        // 'extra': ['无', '小雨', '中雨', '大雨', '暴雨', '大暴雨', '特大暴雨'],
    },
    '24小时降水': {
        'name': '降水', 'unit': '(mm)',
        'level': [0.1, 10, 25, 50, 100, 250],
        'color': ['#ffffff', '#a3f28f', '#3dba3d', '#61b8ff', '#0500e1', '#fa00fa', '#800040'],
        'extra': ['无', '小雨', '中雨', '大雨', '暴雨', '大暴雨', '特大暴雨'],
    },
    '雷达': {
        'name': '组合反射率', 'unit': '(dBZ)',
        'level': [5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70],
        'color': ["rgb(255,255,255)", "rgb(64,157,241)", "rgb(100,231,235)", "rgb(109,250,61)", "rgb(0,216,0)", "rgb(0,144,0)", "rgb(255,255,0)", "rgb(231,192,0)", "rgb(255,145,0)", "rgb(255,0,0)", "rgb(214,0,1)", "rgb(192,0,0)", "rgb(255,0,240)", "rgb(150,1,181)", "rgb(173,144,240)"],
    },
    '气温': {
        'name': '气温', 'unit': '(℃)',
        'level': [-32, -28, -24, -20, -16, -12, -8, -4, 0, 4, 8, 12, 16, 20, 24, 28, 32, 35],
        'color': ["rgb(128,0,128)", "rgb(0,48,158)", "rgb(24,90,180)", "rgb(32,115,220)", "rgb(65,160,230)", "rgb(114,210,250)", "rgb(150,226,240)", "rgb(190,250,255)", "rgb(240,250,255)", "rgb(220,255,213)", "rgb(192,254,179)", "rgb(180,255,130)", "rgb(250,250,143)", "rgb(255,242,192)", "rgb(255,223,176)", "rgb(255,174,118)", "rgb(255,135,138)", "rgb(255,86,0)", "rgb(143,8,20)"],
    },
    '预警等级': {
        'name': '预警等级', 'unit': '',
        'level': ['蓝色预警', '黄色预警', '橙色预警', '红色预警'],
        'color': ['blue', 'yellow', 'orange', 'red'],
        'extra': ['四级', '三级', '二级', '一级'],
    },
    '风力等级': {
        'name': '风力等级', 'unit': '(m/s)',
        'level': [0.3, 1.6, 3.4, 5.5, 8, 10.8, 13.9, 17.2, 20.8, 24.5, 28.5, 32.7, 37],
        'color': ["#e5faff", "#d2ffd2", "#aaf5aa", "#6ed76e", "#3cb43c", "#00ffff", "#00cfff", "#007eff", "#001aff", "#ffcf00", "#ff8f00", "#ff5a5a", "#ff0000", "#c80000"],
        'extra': ["0级", "1级", "2级", "3级", "4级", "5级", "6级", "7级", "8级", "9级", "10级", "11级", "12级", "13级"],
    },
    '相对湿度': {
        'name': '相对湿度', 'unit': '(%)',
        'level': [10, 20, 30, 40, 50, 60, 70, 80, 90],
        'color': ["rgb(152,50,0)", "rgb(233,113,22)", "rgb(255,155,43)", "rgb(255,195,80)", "rgb(255,228,143)", "rgb(188,222,240)", "rgb(127,186,217)", "rgb(67,147,195)", "rgb(31,98,172)", "rgb(7,48,96)"]
    }
};
let name = computed(() => props.name || (props.choose ? builtInLegends[props.choose].name : ""));
let unit = computed(() => props.unit || (props.choose ? builtInLegends[props.choose].unit : ""));
let level = computed(() => props.level || (props.choose ? builtInLegends[props.choose].level : []));
let color = computed(() => props.color || (props.choose ? builtInLegends[props.choose].color : []));
let extra = computed(() => props.extra || (props.choose ? builtInLegends[props.choose].extra : []));
let filter = ref(props.filter || [1, color.value.length]);

// if (reverse: { color.reverse(); level.reverse(); extra.reverse(); }

watch(() => props.filter, (newValue, oldValue) => {
    filter.value = newValue;
})

</script>