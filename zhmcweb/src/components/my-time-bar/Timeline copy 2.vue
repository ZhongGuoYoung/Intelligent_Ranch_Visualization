<!-- eslint-disable vue/multi-word-component-names -->
<style>
.laydate-theme-timeline {
    ::-webkit-scrollbar {
        width: 10px;
        height: 10px;
        background-color: rgba(128, 128, 128, 0.4);
        border-radius: 10px;
    }

    ::-webkit-scrollbar-thumb {
        background-color: rgba(128, 128, 128, 0.6);
        border-radius: 10px;
        transition: all 0.2s ease-in-out;
    }

    ::-webkit-scrollbar-thumb:hover {
        background-color: rgba(128, 128, 128, 0.8);
    }

    .layui-laydate-list li {
        transition-duration: 0ms;
    }
}
</style>
<style scoped>
.timeline-wrap {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    padding: 16px;
    box-sizing: border-box;
    gap: 20px;
    /* background: linear-gradient(to right, rgb(84, 139, 190), rgba(255, 255, 255, 0.8)),
        linear-gradient(to right, rgba(45, 165, 198, 0.8), rgba(71, 155, 168, 0.8));
    background-blend-mode: multiply; */
    background: linear-gradient(to right, #0b4f6c, #0d1520, #0d1520, #0b4f6c);
    border: 1px solid #5593a9;
    box-shadow: 0 0 10px 1px #5593a9;
    border-radius: 5px;

    .time {
        input {
            width: 136px;
            height: 36px;
            border: 1px solid #11def1;
            box-shadow: 0 0 4px #11def1;
            background-color: transparent;
            text-align: center;
            font-size: 16px;
            color: #fff;
            border-radius: 10px;
        }
    }

    .buttons {
        display: flex;
        gap: 5px;

        svg {
            width: 36px;
            height: 36px;
            cursor: pointer;
            fill: #11def1
        }
    }

    .body {
        flex: 1;
        display: flex;
        align-items: center;

        .line {
            flex: 1;
            height: 4px;
            background-color: #11def1;

            &.active {
                background-color: #ebeb21;
            }
        }

        .point-box {
            width: 0;
            height: 0;
            position: relative;
            /* line-height: 20px; */
            font-size: 16px;
            color: #fff;



            .tip {
                position: absolute;
                bottom: 26px;
                left: 50%;
                transform: translate(-50%, 0);
                background-color: #303133;
                border-radius: 4px;
                display: none;
                justify-content: center;
                align-items: center;
                padding: 10px;

                :first-child {
                    width: max-content;
                }

                :last-child {
                    position: absolute;
                    bottom: -5px;
                    width: 10px;
                    height: 10px;
                    transform: rotate(45deg);
                    border-bottom-right-radius: 2px;
                    background-color: #303133;
                }
            }

            .point {
                position: absolute;
                left: 50%;
                top: 50%;
                transform: translate(-50%, -50%);
                width: 16px;
                height: 16px;
                background-color: #11def1;
                border-radius: 50%;
                cursor: pointer;
                z-index: 1;
            }

            .label {
                position: absolute;
                left: 50%;
                transform: translate(-50%, 12px);
                width: max-content;
            }

            /* &.active:hover {
                .point {
                    border: none;
                    width: 20px;
                    height: 20px;
                    z-index: 2;
                }
            } */

            &.active {
                .point {
                    width: 18px;
                    height: 18px;
                    background-color: #ebeb21;
                    z-index: 2;
                }
            }

            &:hover {
                .point {
                    width: 14px;
                    height: 14px;
                    border: 3px solid #11def1;
                    background-color: #ebeb21;
                    z-index: 2;
                }

                /* .tip {
                    display: flex;
                } */

                .label {
                    color: #ebeb21;
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
                }
            }

            &.showtip {
                .tip {
                    display: flex;
                }
            }
        }
    }
}
</style>
<template>
    <div class="timeline-wrap">
        <div class="time"><input type="text" :id="useprops.date.id"></div>
        <div class="buttons">
            <svg viewBox="0 0 1024 1024" @click="handlePrevious" style="transform: rotate(180deg);">
                <title>上一个</title>
                <path d="M149.989688 874.093352a509.948138 509.948138 0 1 0-109.714286-162.700613 513.206978 513.206978 0 0 0 109.714286 162.700613z" fill="#4D4D4D"></path>
                <path
                    d="M682.666667 298.666667a42.666667 42.666667 0 0 1 42.666666 42.666666v320a42.666667 42.666667 0 1 1-85.333333 0V341.333333a42.666667 42.666667 0 0 1 42.666667-42.666666z m-323.306667 8.533333c9.045333 0.469333 25.941333 7.488 32.682667 10.837333 44.096 20.736 128.426667 88.682667 160.725333 130.176 5.482667 6.613333 11.413333 14.634667 13.013333 16.512 6.656 10.410667 10.218667 23.125333 10.218667 36.842667a64.469333 64.469333 0 0 1-9.472 34.432c-4.053333 6.869333-13.354667 17.92-13.354667 17.92-33.109333 42.922667-115.456 107.52-157.205333 128.341333 0 0.384-24.789333 12.736-36.608 13.205334h-1.578667c-18.133333 0-35.072-12.309333-43.733333-32.106667-4.714667-10.837333-9.045333-42.474667-9.429333-42.901333-3.584-28.309333-5.952-71.722667-5.952-119.402667 0-50.005333 2.368-95.274667 6.698666-123.114667 0-0.469333 4.352-25.898667 7.125334-34.432a58.624 58.624 0 0 1 22.037333-29.226666 47.36 47.36 0 0 1 24.832-7.082667z">
                </path>
            </svg>
            <svg viewBox="0 0 1024 1024" @click="handlePlay">
                <title>{{ playStatus ? "暂停" : "播放" }}</title>
                <path d="M149.989688 874.093352a509.948138 509.948138 0 1 0-109.714286-162.700613 513.206978 513.206978 0 0 0 109.714286 162.700613z" fill="#4D4D4D"></path>
                <path v-if="playStatus"
                    d="M417.954256 281.533601a41.046923 41.046923 0 0 0-41.77128 40.201839v385.116718a41.892007 41.892007 0 0 0 83.663287 0v-385.116718a41.167649 41.167649 0 0 0-41.892007-40.201839zM606.045744 281.533601a41.046923 41.046923 0 0 0-41.77128 40.201839v385.116718a41.892007 41.892007 0 0 0 83.663287 0v-385.116718a41.167649 41.167649 0 0 0-41.892007-40.201839z">
                </path>
                <path v-else
                    d="M429.646454 687.977369a57.331447 57.331447 0 0 0 27.277699 7.000472 60.348892 60.348892 0 0 0 32.829797-10.017916l175.977369-115.990571a68.677039 68.677039 0 0 0 30.777935-58.055634 66.504479 66.504479 0 0 0-29.812353-56.486563l-177.54644-115.749175a57.934936 57.934936 0 0 0-60.348892-3.017445 67.832155 67.832155 0 0 0-33.312588 60.348893V627.628477a67.470061 67.470061 0 0 0 34.157473 60.348892z">
                </path>
            </svg>
            <svg viewBox="0 0 1024 1024" @click="handleNext">
                <title>下一个</title>
                <path d="M149.989688 874.093352a509.948138 509.948138 0 1 0-109.714286-162.700613 513.206978 513.206978 0 0 0 109.714286 162.700613z" fill="#4D4D4D"></path>
                <path
                    d="M682.666667 298.666667a42.666667 42.666667 0 0 1 42.666666 42.666666v320a42.666667 42.666667 0 1 1-85.333333 0V341.333333a42.666667 42.666667 0 0 1 42.666667-42.666666z m-323.306667 8.533333c9.045333 0.469333 25.941333 7.488 32.682667 10.837333 44.096 20.736 128.426667 88.682667 160.725333 130.176 5.482667 6.613333 11.413333 14.634667 13.013333 16.512 6.656 10.410667 10.218667 23.125333 10.218667 36.842667a64.469333 64.469333 0 0 1-9.472 34.432c-4.053333 6.869333-13.354667 17.92-13.354667 17.92-33.109333 42.922667-115.456 107.52-157.205333 128.341333 0 0.384-24.789333 12.736-36.608 13.205334h-1.578667c-18.133333 0-35.072-12.309333-43.733333-32.106667-4.714667-10.837333-9.045333-42.474667-9.429333-42.901333-3.584-28.309333-5.952-71.722667-5.952-119.402667 0-50.005333 2.368-95.274667 6.698666-123.114667 0-0.469333 4.352-25.898667 7.125334-34.432a58.624 58.624 0 0 1 22.037333-29.226666 47.36 47.36 0 0 1 24.832-7.082667z">
                </path>
            </svg>
        </div>
        <div class="body">
            <template v-for="index in useprops.points" :key="index">
                <div class="line" :class="{ active: index - 1 <= intervalServiceCount }"></div>
                <div class="point-box" :class="{ active: index - 1 <= intervalServiceCount }" @click="handlePoint(index - 1)">
                    <!-- <div class="tip"></div> -->
                    <div class="point"></div>
                    <div class="label" v-if="useprops.label.show && (useprops.label.interval == 0 || index % (useprops.label.interval + 1) === 1)">
                        {{ useprops.extra == "站" ?
                            index == 1 ? "过去10分钟" :
                                index == 2 ? "过去1小时" :
                                    index == 3 ? "过去3小时" :
                                        index == 4 ? "过去6小时" :
                                            index == 5 ? "过去12小时" : "过去24小时" :
                            dayjs(useprops.date.date).add((index - 1 + useprops.difference), "hour").format(useprops.label.format) }}
                    </div>
                </div>
                <div class="line" v-if="index == useprops.points"></div>
            </template>
        </div>
    </div>
</template>

<script setup>
import { ref, defineProps, onMounted, watch } from "vue";
import dayjs from "dayjs";
import "layui";

onMounted(() => { renderLaydate(); })

const emits = defineEmits(["getDate"])
const props = defineProps({
    extra: { type: String },                                // 自定义
    points: { type: Number, default: 24 },                  // 时间点个数。默认 24
    unit: { type: String },                                 // 时间点的时间精度。默认根据 date.format 判断。接收 year；month；day；hour；minute；second；
    step: { type: Number, default: 1 },                     // 时间点间隔。默认 1 个 unit
    difference: { type: Number, default: 0 },               // 时间输入框与首个时间点的时间差。默认 0 个 unit
    date: {
        type: Object,
        default: (rawProps) => {
            return {
                id: "timeline-datetime",                    // 时间输入框的id。默认 timeline-datetime
                format: "YYYY-MM-DD",                       // 时间输入框的时间格式。默认 YYYY-MM-DD
                date: null,                                 // 时间输入框的时间。默认 new Date()
                hour: false,                                // 时间输入框的时间的 小时部分。接收 true：24h；false：不显示（默认）；数组
                minute: false                               // 时间输入框的时间的 分钟部分。接收 true：60m；false：不显示（默认）；数组
            }
        },
    },
    label: {
        type: Object,
        default: (rawProps) => {
            return {
                show: true,                                 // 显示标签。默认 true
                format: "MM-DD HH:mm",                      // 标签格式。默认 MM-DD HH:mm
                interval: 0,                                // 标签显示间隔。默认 0(无间隔)
            }
        },
    },
    serviceInterval: { type: Number, default: 1 },          // 任务周期。单位：秒
    service: { type: Function, default: () => "function" }, // 任务。
})
const
    useprops = ref(initializeSettings(props)),
    playStatus = ref(false),            // 播放状态
    intervalServiceCount = ref(0);      // 定时任务执行次数
let intervalService = null;             // 定时任务


function initializeSettings(options) {
    let oDate = options.date;
    let oLabel = options.label;
    // 时间输入框的id。默认 timeline-datetime
    let id = oDate.id || "timeline-datetime";
    // 时间输入框的时间。默认 new Date()
    let date = oDate.date || new Date(
        oDate.minute ? new Date().setSeconds(0, 0) :
            oDate.hour ? new Date().setMinutes(0, 0, 0) :
                new Date().setHours(0, 0, 0, 0));
    // 时间输入框的时间格式。默认 YYYY-MM-DD
    let format = oDate.format || (
        oDate.minute ? "YYYY-MM-DD HH:mm" :
            oDate.hour ? "YYYY-MM-DD HH" : "YYYY-MM-DD"
    );
    // 时间输入框的时间的 小时部分。接收 true：24h；false：不显示（默认）；数组
    let hour = format === "YYYY-MM-DD HH" || format === "YYYY-MM-DD HH:mm";
    if (hour) hour = oDate.hour === true ? Array(24).fill(0).map(function (item, index) { return index; }) : oDate.hour;
    // 时间输入框的时间的 分钟部分。接收 true：60m；false：不显示（默认）；数组
    let minute = format === "YYYY-MM-DD HH:mm";
    if (minute) minute = oDate.minute === true ? Array(60).fill(0).map(function (item, index) { return index; }) : oDate.minute;

    if (hour && hour.length != 24) {
        let currentHour = new Date().getHours();
        if (currentHour < hour[0]) {
            date.setHours(hour[hour.length == 1 ? 0 : hour.length - 1] - 24);
        } else {
            for (let i = 0; i < hour.length; i++) {
                if (hour[i] <= currentHour && (!hour[i + 1] || currentHour < hour[i + 1])) {
                    date.setHours(hour[i]);
                    break;
                }
            }
        }
    }
    if (minute && minute.length != 60) {
        let currentminute = new Date().getMinutes();
        if (currentminute < minute[0]) {
            date.setMinutes(minute[minute.length == 1 ? 0 : minute.length - 1] - 60);
        } else {
            for (let i = 0; i < minute.length; i++) {
                if (minute[i] <= currentminute && (!minute[i + 1] || currentminute < minute[i + 1])) {
                    date.setMinutes(minute[i]);
                    break;
                }
            }
        }
    }
    // 时间点的时间精度。
    let unit = options.unit || (
        format === "YYYY" ? "year" :
            format === "YYYY-MM" ? "month" :
                format === "YYYY-MM-DD" ? "day" :
                    format === "YYYY-MM-DD HH" ? "hour" :
                        format === "YYYY-MM-DD HH:mm" ? "minute" : "second");

    let serviceInterval = options.serviceInterval * 1000;   // service 执行周期。单位s。默认 1s
    let labelShow = oLabel.show === false ? false : true;   // 显示标签。默认 true
    let labelFormat = oLabel.format || "MM-DD HH:mm";        // 标签格式。默认 MM-DD HH:mm
    let labelInterval = oLabel.interval || 0;               // 标签显示间隔。默认 0(无间隔)  
    return {
        extra: options.extra,
        points: options.points,
        unit: unit,
        step: options.step,
        difference: options.difference,
        date: {
            id: id,
            date: date,
            format: format,
            hour: hour,
            minute: minute,
        },
        label: {
            show: labelShow,
            format: labelFormat,
            interval: labelInterval
        },
        serviceInterval: serviceInterval,
        service: options.service
    }
}

// 参数有改变时,初始化参数
watch(props, (newValue, oldValue) => {
    useprops.value = initializeSettings(newValue);
    // alert(8)
    intervalServiceCount.value = 0;
    renderLaydate();
})

// 点击时间点
function handlePoint(index) {
    stopIntervalService();
    intervalServiceCount.value = index;
    useprops.value.service(intervalServiceCount.value, dayjs(useprops.value.date.date)
        .add(intervalServiceCount.value + useprops.value.difference, useprops.value.unit)
        .format("YYYY-MM-DD HH:mm:ss"));
}
// 上一个
function handlePrevious() {
    stopIntervalService();
    intervalServiceCount.value = intervalServiceCount.value === 0 ? props.points - 1 : intervalServiceCount.value - 1;
    useprops.value.service(intervalServiceCount.value, dayjs(useprops.value.date.date)
        .add(intervalServiceCount.value + useprops.value.difference, useprops.value.unit)
        .format("YYYY-MM-DD HH:mm:ss"));
}
// 下一个
function handleNext() {
    stopIntervalService();
    intervalServiceCount.value = intervalServiceCount.value === props.points - 1 ? 0 : intervalServiceCount.value + 1;
    useprops.value.service(intervalServiceCount.value, dayjs(useprops.value.date.date)
        .add(intervalServiceCount.value + useprops.value.difference, useprops.value.unit)
        .format("YYYY-MM-DD HH:mm:ss"));
}
// 播放
function handlePlay() {
    !playStatus.value ? startIntervalService() : stopIntervalService();
}
// 开启定时任务
function startIntervalService() {
    if (!playStatus.value) {
        playStatus.value = !playStatus.value;
        intervalService = setInterval(function () {
            intervalServiceCount.value++;
            if (intervalServiceCount.value == useprops.value.points) intervalServiceCount.value = 0;
            useprops.value.service(intervalServiceCount.value, dayjs(useprops.value.date.date)
                .add(intervalServiceCount.value + useprops.value.difference, useprops.value.unit)
                .format("YYYY-MM-DD HH:mm:ss"));
        }, props.serviceInterval * 1000);
    }
}
// 停止定时任务
function stopIntervalService() {
    if (playStatus.value) {
        playStatus.value = !playStatus.value;
        clearInterval(intervalService);
        intervalService = null;
    }
}

function renderLaydate() {
    // eslint-disable-next-line no-undef
    layui.laydate.render({
        theme: "timeline",
        fullPanel: useprops.value.date.format.length > 10,
        elem: "#" + useprops.value.date.id,
        type: useprops.value.date.format === "YYYY" ? "year" :
            useprops.value.date.format === "YYYY-MM" ? "month" :
                useprops.value.date.format === "YYYY-MM-DD" ? "date" : "datetime",
        format: useprops.value.date.format.replace("YYYY", "yyyy").replace("DD", "dd"),
        value: useprops.value.date.date,
        max: dayjs().format("YYYY-MM-DD HH:59:59"),
        ready: function (date) { changeLaydateUI(); },
        change: function (value, date) { changeLaydateUI(); },
        done: function (value, date) {
            emits("getDate", value);
            stopIntervalService();
            useprops.value.date.date = new Date(date.year, date.month - 1, date.date, date.hours, date.minutes, date.seconds);
            useprops.value.service(intervalServiceCount.value, dayjs(useprops.value.date.date)
                .add(intervalServiceCount.value + useprops.value.difference, useprops.value.unit)
                .format("YYYY-MM-DD HH:mm:ss"));
        }
    });
}
// 修改 laydate UI
function changeLaydateUI() {
    let options = useprops.value,
        ele = document.getElementsByClassName("layui-laydate-list")[0],
        cnodes = ele.childNodes;
    ele.style.width = "auto";
    if (options.date.format == "YYYY-MM-DD HH") {
        cnodes[0].style.width = "77.25px";
        cnodes[1].remove();
        cnodes[1].remove();
        document.getElementsByClassName("layui-laydate-main")[0].style.width = "371.5px";
        cnodes = cnodes[0].childNodes[1].childNodes;
        cnodes.forEach(element => {
            if (!options.date.hour.includes(element.innerText * 1)) element.style.display = "none";
        });
    } else if (options.date.format == "YYYY-MM-DD HH:mm") {
        cnodes[0].style.width = "77.25px";
        cnodes[1].style.width = "77.25px";
        cnodes[2].remove();
        document.getElementsByClassName("layui-laydate-main")[0].style.width = "449px";
        cnodes = cnodes[0].childNodes[1].childNodes;
        cnodes.forEach(element => {
            if (!options.date.hour.includes(element.innerText * 1)) element.style.display = "none";
        });
        cnodes = cnodes[1].childNodes[1].childNodes;
        cnodes.forEach(element => {
            if (!options.date.minute.includes(element.innerText * 1)) element.style.display = "none";
        });
    }
}
</script>
