<style scoped lang="scss">
.map-info-panel {
    z-index: 999;
    position: absolute;
    right: 0;
    bottom: 0;
    font-size: 14px;
    line-height: 2em;
    color: #e9e9e9;
    text-shadow: 2px 2px 2px #000;
    box-shadow: 0 0 5px #000;
    background-color: rgba(0, 0, 0, 0.4);
    display: flex;
    gap: 10px;
    padding: 0 10px;
}

.map-switch-panel {
    z-index: 999;
    position: absolute;
    right: 5px;
    bottom: 70px;

    .icon {
        width: 30px;
        height: 30px;
        display: flex;
        align-items: center;
        justify-content: center;
        color: #edffff;
        fill: #edffff;
        background: #303336;
        border: 1px solid #444;
        box-sizing: border-box;
        cursor: pointer;
        transition: 200ms;
        padding: 2px;

        &:hover {
            fill: #fff;
            background: #48b;
            border-color: #aef;
            box-shadow: 0 0 8px #fff;
        }
    }
}

.map-switch-list {
    >div {
        display: flex;
        gap: 6px;

        div {
            box-sizing: border-box;
            position: relative;
            width: 80px;
            height: 74px;
            cursor: pointer;
            color: #fff;
            font-size: 14px;
            line-height: 1.5em;

            img {
                width: 100%;
                height: 100%;
            }

            input {
                width: 1em;
                height: 1em;
            }

            label {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 5px;
                cursor: pointer;
            }

            span {
                position: absolute;
                bottom: 0;
                left: 0;
                width: 100%;
                text-align: center;
                background-color: rgba(0, 0, 0, 0.5);
            }

            &.active {
                border: 2px solid #48b;

                span:last-child {
                    background-color: #48b;
                }
            }
        }
    }
}

.gj {
    z-index: 999;
    position: absolute;
    right: 5px;
    bottom: 140px;
    background: linear-gradient(135deg, rgb(213, 243, 255), rgb(252, 253, 255));
    border-radius: 4px;
    width: 2em;
    color: #000;
    padding: 8px;
    font-size: 15px;
    cursor: pointer;

    &:hover,
    &.active {
        background: rgb(204, 237, 255);
    }
}
</style>

<template>
    <div class="map-info-panel">
        <span v-show="mouseLng">经度：{{ mouseLng }}</span>
        <span v-show="mouseLat">纬度：{{ mouseLat }}</span>
        <span>缩放等级：{{ mapZoom }}</span>
        <span>数据来源：自然资源部 & NavInfo</span>
        <span>审图号：GS（2025）1508号</span>
    </div>

    <div class="map-switch-panel">
        <el-popover placement="left"
                    popper-class="map-switch-list"
                    width="auto">
            <template #reference>
                <span class="icon"><svg viewBox='0 0 1024 1024'>
                        <path
                              d='M511.8 64.2c-247 0-447.2 200.2-447.2 447.2s200.2 447.2 447.2 447.2S959 758.4 959 511.4 758.8 64.2 511.8 64.2zM778.5 778c-11.7 11.7-24.1 22.5-37 32.5-3-0.9-6.1-1.9-9.4-3-50-16.5-113.5 55.5-155.5 48s-30-1.5-46.4 13.5-82.1 12-114.4 0-8.7-6-44.1-37.4-38.2-28.6-57.4-34.6c-19.2-6-37.7 4.5-56.4-6.9-1.6-1.5-3.2-3-4.8-4.4-8.9-13.4 11.9-38.6 39.3-54.2 30.8-17.5 27.7-81.5 0-107s-92.3-13.5-105.2-9c-6.8 2.4-25.8-6.9-42.2-16.3-6.8-28.6-10.3-58-10.3-88 0-10.4 0.4-20.8 1.3-31.1 7.7-3.2 16.6-7.1 25.9-11.6C189.7 455 192.1 437 211 419s11.6-70.5 11.6-90 1.5-22.5 16.5-22.5 24 24 42.9 0 21.6-13.5 45-45c16.7-22.4 24.2-68.3 27.2-93 3.6-1.7 7.2-3.3 10.8-4.8 46.5-19.6 95.8-29.6 146.8-29.6s100.3 10 146.8 29.6c23.1 9.8 44.9 21.7 65.4 35.7-6 26.8-17.5 62.9-36.3 66.6-30.2 6-69.2-2.3-76.7-10.2s1.7-55.8-68.9-30.3c-19 16.5-21.1 40.5-30.8 51s-83.8 59.9-95.5 107.2-35.4 97.4-47.7 109.8c-12.3 12.4-7.6 83.1 29.9 89s80.8 8 84.1 81c2.4 52.3-13.4 54 0 61.5s33.7 1.4 38.5 19.5c4.9 18 7.7 27 16.4 23.3 0 0 42.4-15.6 67.9-47.2s12-6.3 25.5-31.6 26.6-128.9 14.8-137.9-13.3 1.7-28.3-25.4c-15-27.1-23.5-54.9-65.8-53-42.2 1.9-54-4.4-69.1-24.5s-22.4-33.6 0-47.1 83.9-4.5 106.4 0 101.8-23.3 88.4-7.1c-13.4 16.1-47.9 22.1-47.9 35.6s15 43.3 18.1 64c3.1 20.6 9.2 23.6 40.5-6.7s81.3-22.9 81.3 0.9 27.4 41 28.8 32.1c1.5-9 1.7-43.8 21.4-42.3s48.8-8.1 54.5 10.2c5.7 18.3-22.3 67.8 0 94.8 2.3 2.8 4.6 5.3 6.9 7.6-4.9 23.2-12.1 45.9-21.4 67.9-18.6 44.9-45.8 85.3-80.5 119.9z' />
                    </svg>
                </span>
            </template>
            <div>
                <div :class="{ active: tileType === 'vec' }"
                     @click="switchMapTile('vec')">
                    <label v-if="tileType === 'vec'"
                           @click.stop="switchMapTileName('cva', $event)"><input type="checkbox">地名</label><img src="./images/地图.png"><span>地图</span>
                </div>
                <div :class="{ active: tileType === 'img' }"
                     @click="switchMapTile('img')">
                    <label v-if="tileType === 'img'"
                           @click.stop="switchMapTileName('cia', $event)"><input type="checkbox">地名</label><img src="./images/影像.png"><span>影像</span>
                </div>
                <div :class="{ active: tileType === 'ter' }"
                     @click="switchMapTile('ter')">
                    <label v-if="tileType === 'ter'"
                           @click.stop="switchMapTileName('cta', $event)"><input type="checkbox">地名</label><img src="./images/地形.png"><span>地形</span>
                </div>
            </div>
        </el-popover>
    </div>


</template>

<script setup>
import { ref, onMounted } from 'vue';
import interfaces from "@/components/interfaces.js";
import L from 'leaflet';
import WuSuBianJie from "@/assets/GeographicData/吴忠县级边界.json";
import greens from "@/assets/greens.json";

const props = defineProps({ map: { type: Object, require: true } });

let map = props.map;
let mouseLat = ref();
let mouseLng = ref();
let mapZoom = ref(map.getZoom());
let tileType = ref();
let showGJMap = ref(false);
let GLOBAL_CACHE = {};
map
    .on("mousemove", function (e) {
        mouseLat.value = e.latlng.lat.toFixed(6);
        mouseLng.value = e.latlng.lng.toFixed(6);
    })
    .on("zoomend", function (e) {
        mapZoom.value = map.getZoom();
    });


switchMapTile("vec");

init();
function init() {
    let polygon = L.geoJSON(WuSuBianJie, {
        style: function (feature) {
            return {
                stroke: true,
                fill: false,
                color: "#000",
                weight: 1
            };
        },
        pane: "markerPane"
    }).addTo(map);



    // const greenLayer = L.geoJSON(greens, {
    //     style: function (geoJsonFeature) {
    //         return {
    //             color: '#33ff88',
    //             weight: 1,
    //             stroke: false,
    //             fillOpacity: 1,
    //         };
    //     },
    // }).addTo(map);
    // greenLayer.eachLayer((layer) => {
    //     let path = layer._path;
    //     let svg = path.ownerSVGElement;
    //     let defs = document.createElementNS('http://www.w3.org/2000/svg', 'defs');
    //     let gradient = document.createElementNS('http://www.w3.org/2000/svg', 'linearGradient');
    //     let stop1 = document.createElementNS('http://www.w3.org/2000/svg', 'stop');
    //     let stop2 = document.createElementNS('http://www.w3.org/2000/svg', 'stop');
    //     gradient.setAttribute('id', 'grad');
    //     gradient.setAttribute('x1', '0');
    //     gradient.setAttribute('y1', '0');
    //     gradient.setAttribute('x2', '100%');
    //     gradient.setAttribute('y2', '0');
    //     stop1.setAttribute('offset', '0');
    //     stop1.setAttribute('stop-color', 'red');
    //     stop1.setAttribute('stop-opacity', '1');
    //     stop2.setAttribute('offset', '100%');
    //     stop2.setAttribute('stop-color', 'blue');
    //     stop2.setAttribute('stop-opacity', '1');
    //     gradient.appendChild(stop1);
    //     gradient.appendChild(stop2);
    //     defs.appendChild(gradient);
    //     svg.appendChild(defs);
    //     path.setAttribute('fill', 'url(#grad)');
    // });




}






// -------- 切换底图 --------
function switchMapTile(type) {
    if (tileType.value != type) {
        tileType.value = type;
        removeLayerById("current-tile-layer");
        removeLayerById("current-tile-name-layer");
        let projType = "w";
        let token = "44b272f088be993ab7c9f74a107a06c5";
        let url = "http://t{s}.tianditu.gov.cn/" + type + "_" + projType + "/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER="
            + type + "&TILEMATRIXSET=" + projType + "&STYLE=default&FORMAT=tiles&TILEMATRIX={z}&TILEROW={y}&TILECOL={x}&tk=" + token;
        GLOBAL_CACHE["current-tile-layer"] = L.tileLayer(url, {
            subdomains: ["0", "1", "2", "3", "4", "5", "6", "7"]
        }).addTo(map);
        if (GLOBAL_CACHE["current-gj-tile-layer"]) {
            GLOBAL_CACHE["current-gj-tile-layer"].bringToFront();
        }
        GLOBAL_CACHE["current-tile-layer"].bringToBack();
    }
}

// -------- 切换注记 --------
function switchMapTileName(type, event) {
    removeLayerById("current-tile-name-layer");
    if (event.target.checked) {
        let projType = "w";
        let token = "4267820f43926eaf808d61dc07269beb";
        let url = "http://t{s}.tianditu.gov.cn/" + type + "_" + projType + "/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER="
            + type + "&TILEMATRIXSET=" + projType + "&STYLE=default&FORMAT=tiles&TILEMATRIX={z}&TILEROW={y}&TILECOL={x}&tk=" + token;
        GLOBAL_CACHE["current-tile-name-layer"] = L.tileLayer(url, {
            subdomains: ["0", "1", "2", "3", "4", "5", "6", "7"]
        }).addTo(map);
    }
}

function switchGaoJingMapTile() {
    showGJMap.value = !showGJMap.value;
    if (showGJMap.value) {
        GLOBAL_CACHE["current-gj-tile-layer"] = L.tileLayer(interfaces.mapUrl + "XiangChengMap/{z}/{x}/{y}.png", {
            minZoom: 6,
            maxZoom: 19,
        }).addTo(map);
    } else {
        removeLayerById("current-gj-tile-layer");
    }
}

function removeLayerById(id) {
    if (GLOBAL_CACHE[id]) {
        GLOBAL_CACHE[id].remove();
        GLOBAL_CACHE[id] = null;
    }
}
</script>
