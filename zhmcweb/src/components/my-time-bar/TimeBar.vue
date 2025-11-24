<!-- eslint-disable vue/multi-word-component-names -->
<style scoped lang="scss">

:deep(.my-switch) {
    height: fit-content;
    --el-switch-on-color: #51b5b1;
    --el-switch-off-color: rgba(0, 0, 0, 0.4);
    --el-switch-border-color: #51b5b1;

    .el-switch__core {
        height: 34px;
        border-radius: 17px;

        .el-switch__action {
            width: 30px;
            height: 30px;
        }

        .is-text {
            font-size: 15px;
        }
    }

    &.is-checked .el-switch__core .el-switch__action {
        left: calc(100% - 31px);
    }
}

:deep(.my-select) {
    width: 160px;

    .el-select__wrapper {
        background-color: rgba(0, 0, 0, 0.4);
        box-shadow: none;
        border: 1px solid #51b5b1;
        height: 34px;
        font-size: 15px;
        --el-text-color-placeholder: rgba(255, 255, 255, 0.5);
        --el-input-text-color: #fff;

        .el-select__prefix,
        .el-select__suffix {
            color: #fff;
        }
    }
}

:deep(.my-slider) {
    left: 0;
    height: 0;
    position: absolute;

    .el-slider__runway {
        height: 0;
        background-color: transparent;

        .el-slider__bar {
            height: 0;
            background-color: transparent;
        }

        .el-slider__button-wrapper {
            width: fit-content;
            height: fit-content;
            top: 50%;
            transform: translate(-50%, -50%);
        }

        .el-slider__button {
            width: 6px;
            height: 72px;
            border-radius: 3px;
            background: #08eae3;
            border: none
        }
    }
}

.time-bar-wrap {
    max-width: inherit;
    width: 100%;
    height: 80px;
    position: relative;
    display: flex;
    align-items: center;
    padding: 0 50px 0 0;
    box-sizing: border-box;
    gap: 10px;
    background: linear-gradient(to right, rgb(163, 206, 176, 0.8), rgb(102, 189, 144, 0.8), rgb(163, 206, 176, 0.8));
    backdrop-filter: blur(3px);
    box-shadow: 0 0 5px #fff;
    border-radius: 9999px;
    color: #fff;
    font-size: 15px;
     position: relative;
      overflow: visible;
 z-index: 10;
    :deep(.my-ant-time-input) {
        width: 145px;
        height: 36px;
        background-color: rgba(0, 0, 0, 0.1);
        border: 1px solid #51b5b1;
        border-radius: 4px;

        svg {
            fill: #fff;
        }

        input {
            color: #fff;
            font-size: 15px;

            &::-webkit-input-placeholder {
                color: #fff;
            }
        }

        .ant-picker-clear {
            background: #000;
            border-radius: 999px;
        }
    }

    .play-box {
        display: flex;
        align-items: center;
        gap: 5px;

        span {
            background-color: #4D4D4D;
            border-radius: 50%;
            width: 36px;
            height: 36px;
            cursor: pointer;

            svg {
                width: 100%;
                height: 100%;
                fill: #0edfd9;
            }
        }
    }

    .body {
        display: flex;
        align-items: center;
        position: relative;
        flex: 1;

        /* .point-box {
            display: flex;
            gap: 1px;


            &>div {
                display: grid;
                grid-template-rows: repeat(2, 1fr);
                background: rgba(255, 255, 255, 0.2);


                &>div {
                    display: flex;
                    gap: 1px;
                    align-items: center;
                    justify-content: center;
                }
            }

            .tltle {
                position: relative;

                span {
                    position: absolute;
                    max-width: 100%;
                    white-space: nowrap;
                    overflow: hidden;
                    text-overflow: ellipsis;
                }
            }
        }

        .point {
            width: 36px;
            height: 36px;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #51b5b1;
            border-radius: 2px;
            cursor: pointer;
            box-sizing: border-box;

            &.active {
                background-color: orange;
            }

            &:hover {
                color: #000;
                box-shadow: 0 0 0 1px orange inset;
            }

            &.disabled {
                cursor: not-allowed;
                background-color: rgba(81, 181, 177, 0.4);
            }
        } */
    }

    .body.axis {
        --axis-line-width: 5px;
        --axis-line-color: #5C9451;
        --axis-line-background-color: rgba(0, 0, 0, 0.5);
        --axis-tick-length: calc(var(--axis-line-width) * 2);
        --axis-point-size: calc(var(--axis-line-width) * 4);
        height: calc(var(--axis-line-width)*10);

        .item {
            flex: 1;
            display: flex;
            align-items: center;
            overflow: visible;
        }

        .middle {
            width: 0;
            height: 0;
            position: relative;
            font-size: 14px;
            line-height: 1em;
            color: #fff;
        }

        .line {
            flex: 1;
            height: var(--axis-line-width);
            background-color: var(--axis-line-background-color);

            &.active {
                background-color: var(--axis-line-color);
            }
        }

        .tick {
            position: absolute;
            left: 0;
            top: 0;
            transform: translate(-50%, calc(var(--axis-line-width) / 2));
            width: 2px;
            height: var(--axis-tick-length);
            background-color: #fff;
        }

        .minor-tick {
            height: calc(var(--axis-tick-length) / 2);
        }

        .point {
            position: absolute;
            left: 0;
            top: 0;
            transform: translate(-50%, -50%);
            width: var(--axis-point-size);
            height: var(--axis-point-size);
            border-radius: 50%;
            cursor: pointer;
            transition: 200ms;

            &:hover {
                background-color: #fff;
            }

            &.active {
                background-color: var(--axis-line-color);
                border: 2px solid #fff;
            }
        }

        .label {
            position: absolute;
            inset: 0;
            top: 0;
            transform: translate(-50%, calc(var(--axis-tick-length) + 4px));
            width: max-content;
            height: fit-content;
        }

        .point:hover+.tooltip {
            display: block;
        }

        .tooltip {
            position: absolute;
            bottom: 0;
            left: 0;
            transform: translate(-50%, calc(var(--axis-point-size) * -0.4));
            background-color: #303133;
            border-radius: 4px;
            justify-content: center;
            align-items: center;
            padding: 10px;
            width: max-content;
            display: none;
            transition: 200ms;

            &.active {
                display: block;
            }

            &::after {
                content: "";
                position: absolute;
                left: 50%;
                bottom: 0;
                width: 10px;
                height: 10px;
                transform: translate(-50%, 50%) rotate(45deg);
                border-bottom-right-radius: 2px;
                background-color: #303133;
            }
        }




        /* &:hover {
            color:
                rgb(217, 247, 211),
                rgb(183, 245, 170),
                rgb(115, 216, 110),
                rgb(62, 185, 64),
                rgb(98, 182, 254),
                rgb(1, 0, 254),
                rgb(245, 1, 250),
                rgb(130, 0, 65),
                rgb(128, 0, 128)
        } */

    }

    .time-box {}

    .type-box {
        width: 50px;
        height: 100%;
        display: flex;
        flex-direction: column;
        gap: 1px;

        span {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: right;
            background-color: #4D4D4D;
            cursor: pointer;
            padding-right: 10px;

            &:first-child {
                border-radius: 35px 4px 4px 4px;
            }

            &:last-child {
                border-radius: 4px 4px 4px 35px;
            }

            &.active {
                /* box-shadow: 0 0 10px #0edfd9 inset; */
                background-color: #0edfd9;
            }
        }
    }
}
</style>
<template>
    <svg aria-hidden="true"
         style="position: absolute; width: 0; height: 0; overflow: hidden">
        <symbol id="icon-prev"
                viewBox="0 0 1024 1024">
            <title>上一个</title>
            <path transform="rotate(180,512,512)"
                  d="M682.666667 298.666667a42.666667 42.666667 0 0 1 42.666666 42.666666v320a42.666667 42.666667 0 1 1-85.333333 0V341.333333a42.666667 42.666667 0 0 1 42.666667-42.666666z m-323.306667 8.533333c9.045333 0.469333 25.941333 7.488 32.682667 10.837333 44.096 20.736 128.426667 88.682667 160.725333 130.176 5.482667 6.613333 11.413333 14.634667 13.013333 16.512 6.656 10.410667 10.218667 23.125333 10.218667 36.842667a64.469333 64.469333 0 0 1-9.472 34.432c-4.053333 6.869333-13.354667 17.92-13.354667 17.92-33.109333 42.922667-115.456 107.52-157.205333 128.341333 0 0.384-24.789333 12.736-36.608 13.205334h-1.578667c-18.133333 0-35.072-12.309333-43.733333-32.106667-4.714667-10.837333-9.045333-42.474667-9.429333-42.901333-3.584-28.309333-5.952-71.722667-5.952-119.402667 0-50.005333 2.368-95.274667 6.698666-123.114667 0-0.469333 4.352-25.898667 7.125334-34.432a58.624 58.624 0 0 1 22.037333-29.226666 47.36 47.36 0 0 1 24.832-7.082667z">
            </path>
        </symbol>
        <symbol id="icon-next"
                viewBox="0 0 1024 1024">
            <title>下一个</title>
            <path
                  d="M682.666667 298.666667a42.666667 42.666667 0 0 1 42.666666 42.666666v320a42.666667 42.666667 0 1 1-85.333333 0V341.333333a42.666667 42.666667 0 0 1 42.666667-42.666666z m-323.306667 8.533333c9.045333 0.469333 25.941333 7.488 32.682667 10.837333 44.096 20.736 128.426667 88.682667 160.725333 130.176 5.482667 6.613333 11.413333 14.634667 13.013333 16.512 6.656 10.410667 10.218667 23.125333 10.218667 36.842667a64.469333 64.469333 0 0 1-9.472 34.432c-4.053333 6.869333-13.354667 17.92-13.354667 17.92-33.109333 42.922667-115.456 107.52-157.205333 128.341333 0 0.384-24.789333 12.736-36.608 13.205334h-1.578667c-18.133333 0-35.072-12.309333-43.733333-32.106667-4.714667-10.837333-9.045333-42.474667-9.429333-42.901333-3.584-28.309333-5.952-71.722667-5.952-119.402667 0-50.005333 2.368-95.274667 6.698666-123.114667 0-0.469333 4.352-25.898667 7.125334-34.432a58.624 58.624 0 0 1 22.037333-29.226666 47.36 47.36 0 0 1 24.832-7.082667z">
            </path>
        </symbol>
        <symbol id="icon-play"
                viewBox="0 0 1024 1024">
            <title>播放</title>
            <path
                  d="M417.954256 281.533601a41.046923 41.046923 0 0 0-41.77128 40.201839v385.116718a41.892007 41.892007 0 0 0 83.663287 0v-385.116718a41.167649 41.167649 0 0 0-41.892007-40.201839zM606.045744 281.533601a41.046923 41.046923 0 0 0-41.77128 40.201839v385.116718a41.892007 41.892007 0 0 0 83.663287 0v-385.116718a41.167649 41.167649 0 0 0-41.892007-40.201839z">
            </path>
        </symbol>
        <symbol id="icon-stop"
                viewBox="0 0 1024 1024">
            <title>播放</title>
            <path
                  d="M429.646454 687.977369a57.331447 57.331447 0 0 0 27.277699 7.000472 60.348892 60.348892 0 0 0 32.829797-10.017916l175.977369-115.990571a68.677039 68.677039 0 0 0 30.777935-58.055634 66.504479 66.504479 0 0 0-29.812353-56.486563l-177.54644-115.749175a57.934936 57.934936 0 0 0-60.348892-3.017445 67.832155 67.832155 0 0 0-33.312588 60.348893V627.628477a67.470061 67.470061 0 0 0 34.157473 60.348892z">
            </path>
        </symbol>
    </svg>

    <div class="time-bar-wrap"
         :style="{ padding: props.bar ? false : '10px 50px 10px 40px' }"
         ref="bodydom">
        <div class="type-box"
             v-show="props.bar">
            <span :class="{ active: barType == '时' }"
                  @click="changeBar('时')">时</span>
            <span :class="{ active: barType == '日' }"
                  @click="changeBar('日')">日</span>
        </div>
        <div class="time-box">
            <a-date-picker v-model:value="date"
                           class="my-ant-time-input"
                           :show-time="{
                            format: props.dateFormat.substring(11),
                            hideDisabledOptions: props.hideDisabled
                        }"
                           :format="props.dateFormat"
                           :locale="locale"
                           :disabled="selectValue != undefined"
                           @ok="antDateOk"
                           :disabled-date="antDisabledDate"
                           :disabled-time="antDisabledDateTime" />
        </div>
        <div class="play-box">
            <span @click="handlePrevious">
                <svg>
                    <use xlink:href="#icon-prev"></use>
                </svg>
            </span>
            <span @click="handlePlay">
                <svg>
                    <use :xlink:href="playStatus ? '#icon-play' : '#icon-stop'"></use>
                </svg>
            </span>
            <span @click="handleNext(true)">
                <svg>
                    <use xlink:href="#icon-next"></use>
                </svg>
            </span>
        </div>

        <div class="body axis">
            <div v-for="(val, idx) in props.points"
                 class="item"
                 :key="val">
                <div class="line"
                     :class="{ active: val <= servicePoint }"></div>
                <div class="middle">
                    <div class="tick"
                         :class="{ 'minor-tick': handleAxisLabel(idx) == '' }"></div>
                    <div class="point"
                         :class="{ active: servicePoint == val }"
                         @click="handlePoint(val, true)"></div>
                    <div class="tooltip"
                         :class="{ active: val == servicePoint }">{{ handleAxisTooltip(idx) }}</div>
                    <div class="label">{{ handleAxisLabel(idx) }}</div>
                </div>
                <div class="line"
                     :class="{ active: val < servicePoint }"></div>
            </div>
        </div>

    </div>
</template>

<script setup>
import { ref, watch, computed } from "vue";
import dayjs from "dayjs";
import 'dayjs/locale/zh-cn';
import locale from 'ant-design-vue/es/date-picker/locale/zh_CN';
import { Check, Close } from '@element-plus/icons-vue'
import axios from "axios";
import interfaces from "@/components/interfaces.js";

const emits = defineEmits(["pointClick", "changeBar"])
const date = defineModel({ type: Object, default: dayjs() })
const props = defineProps({
    dateFormat: { type: String, default: "YYYY-MM-DD" },
    disabledHours: { type: Array, default: () => [] },
    abledHours: { type: Array, default: () => [] },
    disabledMinutes: { type: Array, default: () => [] },
    abledMinutes: { type: Array, default: () => [] },
    hideDisabled: { type: Boolean, default: false },

    bar: { type: [Boolean, String], default: false },
    points: { type: Number, default: 24 },                  // 时间点个数。默认 24
    pointChecked: { type: Number, default: 1 },             // 第一个被选中的时间点。默认 1
    unit: {
        type: String, default: (rawProps) => {
            return rawProps.dateFormat === "YYYY" ? "year" :
                rawProps.dateFormat === "YYYY-MM" ? "month" :
                    rawProps.dateFormat === "YYYY-MM-DD" ? "day" :
                        rawProps.dateFormat === "YYYY-MM-DD HH" ? "hour" :
                            rawProps.dateFormat === "YYYY-MM-DD HH:mm" ? "minute" : "second";
        }
    },                                                      // 时间点的时间精度。默认根据 date.format 判断。接收 year；month；day；hour；minute；second；
    step: { type: Number, default: 1 },                     // 时间点间隔。默认 1 个 unit
    difference: { type: Number, default: 0 },               // 时间输入框与首个时间点的时间差。默认 0 个 unit

    interval: { type: Number, default: 1 },          // 任务周期。单位：秒
})


// const date = ref(computed(() => {
//     let date =
//         props.dateFormat === "YYYY-MM-DD"
//             ? props.date.set("hour", 0).set("minute", 0).set("second", 0)
//             : props.dateFormat === "YYYY-MM-DD HH"
//                 ? props.date.set("minute", 0).set("second", 0)
//                 : props.date.set("second", 0);
//     let enableHours = Array(24).fill(0).map((v, i) => i).filter((v, i) => !props.disabledHours.includes(v))
//     if (props.disabledHours.length > 0) {
//         let currentHour = dayjs().get("hour");
//         if (currentHour < enableHours[0]) {
//             date = date.set('hour', enableHours[enableHours.length == 1 ? 0 : enableHours.length - 1] - 24);
//         } else {
//             for (let i = 0; i < enableHours.length; i++) {
//                 if (enableHours[i] <= currentHour && (!enableHours[i + 1] || currentHour < enableHours[i + 1])) {
//                     date = date.set("hour", enableHours[i]);
//                     break;
//                 }
//             }
//         }
//     }
//     // if (minute && minute.length != 60) {
//     //     let currentminute = new Date().getMinutes();
//     //     if (currentminute < minute[0]) {
//     //         date.setMinutes(minute[minute.length == 1 ? 0 : minute.length - 1] - 60);
//     //     } else {
//     //         for (let i = 0; i < minute.length; i++) {
//     //             if (minute[i] <= currentminute && (!minute[i + 1] || currentminute < minute[i + 1])) {
//     //                 date.setMinutes(minute[i]);
//     //                 break;
//     //             }
//     //         }
//     //     }
//     // }
//     return date;
// }).value);



let bodydom = ref(),
    barType = ref(props.bar),
    playStatus = ref(false),                // 播放状态
    service = null,                         // 定时任务
    servicePoint = ref(props.pointChecked); // 执行定时任务的点





// 参数改变时，执行一次时间点事件
watch(props, (newValue, oldValue) => {
    barType.value = props.bar;
    servicePoint.value = props.pointChecked;
    handlePoint(servicePoint.value, true);
})
// 时间改变时，执行一次时间点事件
function antDateOk(value) {
    handlePoint(servicePoint.value, true);
}

function changeBar(type) {
    if (barType.value != type) {
        barType.value = type;
        emits("changeBar", type);
    }
}

// 点击时间点
function handlePoint(index, stop) {
    if (stop) { stopIntervalService(); }
    servicePoint.value = index;
    let obj = {
        bar: barType.value,
        point: servicePoint.value,
        date: date.value.add(((servicePoint.value - 1) + props.difference) * props.step, props.unit),
        dateString: date.value.add(((servicePoint.value - 1) + props.difference) * props.step, props.unit).format("YYYY-MM-DD HH:mm:ss"),
    }
    emits("pointClick", obj);
}
// 上一个
function handlePrevious() {
    let index = servicePoint.value == 1 ? props.points : servicePoint.value - 1;
    handlePoint(index, true);
}
// 下一个
function handleNext(stop) {
    let index = servicePoint.value == props.points ? 1 : servicePoint.value + 1;
    handlePoint(index, stop);
}
// 播放
function handlePlay() {
    !playStatus.value ? startIntervalService() : stopIntervalService();
}
// 开启定时任务
function startIntervalService() {
    if (!playStatus.value) {
        playStatus.value = !playStatus.value;
        service = setInterval(() => { handleNext(false); }, props.interval * 1000);
    }
}
// 停止定时任务
function stopIntervalService() {
    if (playStatus.value) {
        playStatus.value = !playStatus.value;
        clearInterval(service);
        service = null;
    }
}



// ant 控制未来时间不可选择
const antDisabledDate = (current) => {
    return current && current > dayjs().endOf('day');
}
const antDisabledDateTime = (current) => {
    let now = dayjs(),
        nowH = now.get("hour") + 1,
        nowM = now.get("minute") + 1,
        disabledHours = props.disabledHours.length > 0
            ? props.disabledHours
            : props.abledHours.length > 0
                ? Array.from({ length: 24 }, (v, i) => i).filter(v => !props.abledHours.includes(v))
                : current.isBefore(now, "day")
                    ? []
                    : Array.from({ length: 24 - nowH }, (v, i) => nowH + i),
        disabledMinutes = props.disabledMinutes.length > 0
            ? props.disabledMinutes
            : props.abledMinutes.length > 0
                ? Array.from({ length: 60 }, (v, i) => i).filter(v => !props.abledMinutes.includes(v))
                : current.isBefore(now, "hour")
                    ? []
                    : Array.from({ length: 60 - nowM }, (v, i) => nowM + i);

    return {
        disabledHours: () => disabledHours,
        disabledMinutes: () => disabledMinutes,
    };
};


function handleAxisLabel(idx) {
    if (props.unit == 'hour') {
        let interval = props.points <= 24 ? 2 : 12;
        return date.value.add((idx + props.difference), props.unit).get(props.unit) % interval == 0
            ? date.value.add((idx + props.difference), props.unit).format("DD日HH时")
            : ""
    }
    if (props.unit == 'day') {
        return date.value.add((idx + props.difference), props.unit).format("MM-DD");
    }
}
function handleAxisTooltip(idx) {
    let format = props.unit == 'year' ? 'YYYY'
        : props.unit == 'month' ? 'YYYY-MM'
            : props.unit == 'day' ? 'YYYY-MM-DD'
                : props.unit == 'hour' ? 'YYYY-MM-DD HH'
                    : props.unit == 'minute' ? 'YYYY-MM-DD HH:mm' : 'YYYY-MM-DD HH:mm:ss'
    return date.value.add((idx + props.difference), props.unit).format(format);
}


</script>
