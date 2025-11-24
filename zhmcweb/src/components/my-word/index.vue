<!-- eslint-disable vue/multi-word-component-names -->
<style scoped lang="scss">
.pc-bg {
    width: 100%;
    height: 100%;
    background-color: #F3F5F7;
    color: #000;
    font-size: 16px;
    --gap1: 12px;
    --gap2: 8px;
    --gap3: 4px;
    font-family: "宋体";

    .pc-header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 80px;
        padding: 10px;
        background: #fff;
        border: 1px solid rgba(0, 0, 0, 0.12);
        box-shadow: 0px 1px 4px 0px rgba(0, 0, 0, 0.08);
        border-radius: 8px;

        .menu {
            flex-direction: column;
            gap: var(--gap3);
        }
    }

    .pc-footer {
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 36px;
        padding: 10px;
        background: #fff;
        border-top: 1px solid rgba(0, 0, 0, 0.08);
    }

    .header-left,
    .header-right,
    .footer-left,
    .footer-right {
        display: flex;
        align-items: center;
        gap: var(--gap1);
    }

    .zoom-slider {
        width: 150px;
        --el-slider-button-wrapper-size: 34px;
        --el-slider-button-wrapper-offset: -14px;
    }

    .bar {
        display: flex;
        align-items: center;
        gap: var(--gap2);
    }

    .menu {
        border-radius: 4px;
        padding: 4px;
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;
        line-height: 1em;
        transition: 200ms;

        &:hover {
            background-color: rgba(51, 77, 102, 0.1);
        }

        .text {
            display: flex;
            align-items: center;
            gap: var(--gap3);
        }

        svg {
            width: 20px;
            height: 20px;
        }
    }





    .word-title {
        padding: 1em;
        font-size: 40px;
        text-align: center;
    }

    .word-unit-time {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .pc-main {
        padding: 0;
    }

    .word-page-box {
        display: flex;
        flex-direction: column;
        gap: 20px;
        width: fit-content;
        transform-origin: top center;
        margin: auto;
        padding: 20px 0;

        .word-page {
            width: 794px;
            height: 1123px;
            min-height: 1123px;
            // aspect-ratio: 794/1123;
            padding: 72px;
            box-shadow: rgba(0, 0, 0, 0.06) 0px 0px 10px 0px, rgba(0, 0, 0, 0.04) 0px 0px 0px 1px;
            background-color: #fff;
            --position-border-color: rgb(75, 75, 75, 0.2);
            --position-size: 20px;

            .page-header {
                display: flex;
                align-items: flex-end;
                justify-content: space-between;
                padding: 0;
                height: 0;

                &::before {
                    content: "";
                    width: var(--position-size);
                    height: var(--position-size);
                    border-right: 1px solid var(--position-border-color);
                    border-bottom: 1px solid var(--position-border-color);
                    transform: translateX(-100%);
                }

                &::after {
                    content: "";
                    width: var(--position-size);
                    height: var(--position-size);
                    border-left: 1px solid var(--position-border-color);
                    border-bottom: 1px solid var(--position-border-color);
                    transform: translateX(100%);
                }
            }

            .page-footer {
                display: flex;
                align-items: flex-start;
                justify-content: space-between;
                padding: 0;
                height: 0;

                &::before {
                    content: "";
                    width: var(--position-size);
                    height: var(--position-size);
                    border-right: 1px solid var(--position-border-color);
                    border-top: 1px solid var(--position-border-color);
                    transform: translateX(-100%);
                }

                &::after {
                    content: "";
                    width: var(--position-size);
                    height: var(--position-size);
                    border-left: 1px solid var(--position-border-color);
                    border-top: 1px solid var(--position-border-color);
                    transform: translateX(100%);
                }
            }

            .page-body {
                overflow: hidden;
                padding: 0;
            }
        }
    }


}

.my-dropdown-item {
    svg {
        width: 20px;
        height: 20px;
    }
}
</style>
<template>
    <svg aria-hidden="true"
         style="position: absolute; width: 0px; height: 0px; overflow: hidden">
        <symbol id="icon-makeWord"
                viewBox="0 0 1024 1024">
            <path
                  d="M599.771429 987.428571c-7.314286 0-14.628571 0-21.942858-7.314285-7.314286-7.314286-7.314286-14.628571-7.314285-29.257143l7.314285-73.142857c0-14.628571 7.314286-29.257143 21.942858-43.885715L731.428571 702.171429l43.885715-43.885715c7.314286-7.314286 14.628571-14.628571 29.257143-14.628571 21.942857-7.314286 43.885714-7.314286 65.828571 0 7.314286 7.314286 14.628571 7.314286 21.942857 14.628571 14.628571 14.628571 21.942857 36.571429 21.942857 58.514286 0 21.942857-7.314286 43.885714-21.942857 58.514286l-43.885714 43.885714L716.8 950.857143c-14.628571 14.628571-29.257143 21.942857-43.885714 21.942857l-73.142857 14.628571c7.314286 0 0 0 0 0z m153.6-212.114285l-109.714286 109.714285-7.314286 29.257143H658.285714l117.028572-109.714285-21.942857-29.257143z m51.2-43.885715l21.942857 21.942858 21.942857-21.942858s7.314286-7.314286 7.314286-14.628571 0-7.314286-7.314286-14.628571l-43.885714-21.942858 21.942857 21.942858-21.942857 29.257142z m-358.4 256H168.228571c-14.628571 0-29.257143-7.314286-43.885714-14.628571-14.628571-7.314286-14.628571-29.257143-14.628571-43.885714V95.085714c0-14.628571 7.314286-29.257143 14.628571-43.885714 14.628571-14.628571 29.257143-14.628571 43.885714-14.628571H694.857143c7.314286 0 21.942857 7.314286 21.942857 14.628571l138.971429 138.971429c14.628571 14.628571 21.942857 21.942857 21.942857 29.257142v292.571429c0 21.942857-14.628571 36.571429-36.571429 36.571429-14.628571 0-36.571429-14.628571-36.571428-36.571429V277.942857H694.857143c-14.628571 0-29.257143-7.314286-43.885714-14.628571-7.314286-14.628571-14.628571-29.257143-14.628572-43.885715V102.4H175.542857v819.2h277.942857c21.942857 0 36.571429 14.628571 36.571429 36.571429-7.314286 14.628571-21.942857 29.257143-43.885714 29.257142zM702.171429 124.342857v87.771429h87.771428L702.171429 124.342857zM446.171429 629.028571H314.514286c-21.942857 0-36.571429-14.628571-36.571429-36.571428s14.628571-36.571429 36.571429-36.571429h131.657143c21.942857 0 36.571429 14.628571 36.571428 36.571429s-14.628571 36.571429-36.571428 36.571428z m226.742857-175.542857H314.514286c-14.628571 0-29.257143-14.628571-29.257143-29.257143s14.628571-36.571429 36.571428-36.571428h351.085715c14.628571 0 36.571429 14.628571 36.571428 36.571428s-21.942857 29.257143-36.571428 29.257143zM490.057143 277.942857H314.514286c-21.942857 0-36.571429-14.628571-36.571429-36.571428s14.628571-36.571429 36.571429-36.571429h175.542857c21.942857 0 36.571429 14.628571 36.571428 36.571429s-14.628571 36.571429-36.571428 36.571428z">
            </path>
        </symbol>
        <symbol id="icon-downloadWord"
                viewBox="0 0 1024 1024">
            <path d="M256.9 191h447.6v128H256.9z"></path>
            <path d="M256.9 416.2h447.6v64H256.9zM256.9 576h319.8v64H256.9zM256.9 735.8h255.9v64H256.9z"></path>
            <path d="M927 797.3L764.4 960 601.8 797.3V659.1l98.7 98.8V524.5h127.8v233.4l98.7-98.8z"></path>
            <path d="M627.2 960H97V64h767.3v392.5h-63.9V128H160.9v768h466.3z"></path>
        </symbol>
        <symbol id="icon-连页视图"
                viewBox="0 0 1024 1024">
            <path
                  d="M854.592 288.64L639.424 73.344A32 32 0 0 0 616.768 64H192a32 32 0 0 0-32 32v832a32 32 0 0 0 32 32h640a32 32 0 0 0 32-32V311.296a32.192 32.192 0 0 0-9.408-22.72z m-64.384 37.376h-188.16v-188.16l188.16 188.16z m1.792 561.984h-560V136h302.016V352c0 23.168 18.752 41.984 41.984 41.984h216v494.016z">
            </path>
        </symbol>
    </svg>

    <el-container class="pc-bg"
                  ref="wordBg">
        <el-header class="pc-header toolbar">
            <div class="header-left">

            </div>
            <div class="header-right">
                <div class="menu"
                     title="制作"
                     @click="makeWord">
                    <svg>
                        <use xlink:href="#icon-makeWord"></use>
                    </svg>
                    制作
                </div>
                <div class="menu"
                     title="下载"
                     @click="downloadWord">
                    <svg>
                        <use xlink:href="#icon-downloadWord"></use>
                    </svg>
                    下载
                </div>
            </div>
        </el-header>
        <el-main class="pc-main">
            <div class="word-page-box"
                 :style="{ transform: 'scale(' + (zoomLevel / 100) + ')' }">
                <el-container class="word-page"
                              :style="viewType == '连页视图' ? 'height:auto;' : ''">
                    <el-header class="page-header"></el-header>
                    <el-main class="page-body">
                        <div class="word-title">{{ model.title }}</div>
                        <div class="word-unit-time"><span>{{ model.unit }}</span><span>{{ model.time }}</span><span>{{ model.author }}</span></div>
                        <WordLine />
                        <template v-for="(p, i) in model.paragraph"
                                  :key="i">
                            <WordText v-if="p.type == 'text'"
                                      v-model="model.paragraph[i]" />
                            <WordImage v-if="p.type == 'image'"
                                       v-model="model.paragraph[i].data"
                                       :title="p.title"
                                       :width="p.width"
                                       :height="p.height" />
                            <WordTable v-if="p.type == 'table'"
                                       v-model="model.paragraph[i].data"
                                       :title="p.title"
                                       :width="p.width"
                                       :height="p.height"
                                       :column="p.column" />
                            <WordChart v-if="p.type == 'chart'"
                                       v-model="model.paragraph[i].data"
                                       :title="p.title"
                                       :width="p.width"
                                       :height="p.height"
                                       :xAxis="p.xAxis"
                                       :series="p.series" />
                        </template>
                    </el-main>
                    <el-footer class="page-footer"></el-footer>
                </el-container>
            </div>
        </el-main>
        <el-footer class="pc-footer">
            <div class="footer-left">
                <el-dropdown class="my-dropdown menu"
                             placement="top"
                             trigger="click"
                             @command="v => viewType = v">
                    <div class="text">
                        <svg>
                            <use xlink:href="#icon-连页视图"></use>
                        </svg>
                        <ArrowDown />
                    </div>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item v-for="item in ['连页视图']"
                                              :key="item"
                                              class="my-dropdown-item"
                                              :command="item"
                                              :icon="item == viewType ? Check : 'Check'">
                                <svg>
                                    <use :xlink:href="'#icon-' + item"></use>
                                </svg>
                                {{ item }}
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>

                <div class="pages-count menu">页面：</div>
                <div class="words-count menu">字数：</div>
            </div>
            <div class="footer-right">
                <div class="zoom-level-bar bar">
                    <el-dropdown class="zoom-level menu"
                                 placement="top"
                                 trigger="click"
                                 @command="v => zoomLevel = v">
                        <div class="text">
                            <span>{{ zoomLevel }}%</span>
                            <ArrowDown />
                        </div>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item v-for="item in [25, 50, 75, 100, 125, 150, 200, 300, 400]"
                                                  :key="item"
                                                  :command="item"
                                                  :icon="item == zoomLevel ? Check : 'Check'">
                                    {{ item }}%
                                </el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                    <div class="zoom-out menu"
                         title="缩小"
                         @click="toggleZoom('out')">
                        <Minus />
                    </div>
                    <el-slider class="zoom-slider"
                               :min="25"
                               :max="400"
                               :format-tooltip="v => v + '%'"
                               v-model="zoomLevel" />
                    <div class="zoom-in menu"
                         title="放大"
                         @click="toggleZoom('in')">
                        <Plus />
                    </div>
                </div>
                <div class="full-screen menu"
                     title="全屏显示"
                     @click="toggleFullscreen(wordBg.$el)">
                    <FullScreen />
                </div>
            </div>
        </el-footer>
    </el-container>
</template>

<script setup>
import { ref, onMounted, nextTick } from "vue";
import { Check, ArrowDown, Minus, Plus, FullScreen } from "@element-plus/icons-vue";

import WordLine from "./components/WordLine.vue";
import WordText from "./components/WordText.vue";
import WordImage from "./components/WordImage.vue";
import WordTable from "./components/WordTable.vue";
import WordChart from "./components/WordChart.vue";

// import DocumentDownload from "@/components/icons/DocumentDownload.vue";
const model = defineModel();
const emit = defineEmits(["makeWord", "downloadWord"]);

let wordBg = ref();
let zoomLevel = ref(125);
let viewType = ref("连页视图");





function makeWord() { emit("makeWord"); }
function downloadWord() { emit("downloadWord"); }

/**
 * @function 缩放
 */
function toggleZoom(type) {
    let step = 5;
    zoomLevel.value = type == "out"
        ? zoomLevel.value % step == 0
            ? zoomLevel.value - step
            : Math.floor(zoomLevel.value / step) * step
        : zoomLevel.value % step == 0
            ? zoomLevel.value + step
            : Math.ceil(zoomLevel.value / step) * step;
}
/**
 * @function 全屏
 */
function toggleFullscreen(element = wordBg.value.$el) {
    if (!document.fullscreenElement) {
        if (element.requestFullscreen) {
            return element.requestFullscreen();
        } else if (element.webkitRequestFullscreen) {
            return element.webkitRequestFullscreen();
        } else if (element.msRequestFullscreen) {
            return element.msRequestFullscreen();
        }
    } else {
        if (document.exitFullscreen) {
            return document.exitFullscreen();
        } else if (document.webkitExitFullscreen) {
            return document.webkitExitFullscreen();
        } else if (document.msExitFullscreen) {
            return document.msExitFullscreen();
        }
    }
}




// let wordImg = ref({
//     title: "图1 南京市行政区划图",
//     data: njxzqh,
//     size: ["600px", "auto"]
// });
// let wordTab = ref({
//     title: "表1 自动站降水统计表",
//     column: [
//         { prop: "name", label: "区" },
//         { prop: "lon", label: "经度" },
//         { prop: "lat", label: "纬度" },
//         { prop: "val", label: "1h雨量", sortable: true },
//     ],
//     // data:
// });










</script>
