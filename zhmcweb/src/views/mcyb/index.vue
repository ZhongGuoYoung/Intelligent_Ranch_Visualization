<!-- 覆盖第二个文件的 scoped 样式 -->
<style scoped lang="scss">
/* 1) 设计系统变量（同第一个文件） */
:root {
  /* Big-screen DS tokens for dark theme (adds to existing) */
  --ds-bg:#0E1A2B;
  --ds-card: rgba(15,24,44,.82);
  --ds-line: rgba(255,255,255,.10);
  --ds-bd: rgba(139,233,255,.18);
  --ds-txt: rgba(255,255,255,.86);
  --ds-txt-2: rgba(255,255,255,.62);
  --ds-pri:#2BD9FE;
  --ds-acc:#00FFA3;
  --ds-warn:#FFAA33;
  --primary-color: #1e40af;
  --primary-dark: #1e3a8a;
  --primary-light: #3b82f6;
  --secondary-color: #059669;
  --accent-color: #dc2626;
  --success-color: #10b981;
  --warning-color: #f59e0b;
  --error-color: #ef4444;

  --surface-primary: rgba(255, 255, 255, 0.95);
  --surface-secondary: rgba(248, 250, 252, 0.9);
  --surface-elevated: rgba(255, 255, 255, 0.98);

  --text-primary: #1f2937;
  --text-secondary: #6b7280;
  --text-inverse: #ffffff;

  --border-radius-sm: 8px;
  --border-radius-md: 12px;
  --border-radius-lg: 16px;
  --border-radius-xl: 20px;

  --shadow-sm: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
  --shadow-md: 0 4px 6px -1px rgba(0,0,0,.10), 0 2px 4px -1px rgba(0,0,0,.06);
  --shadow-lg: 0 10px 15px -3px rgba(0,0,0,.10), 0 4px 6px -2px rgba(0,0,0,.05);
  --shadow-xl: 0 20px 25px -5px rgba(0,0,0,.10), 0 10px 10px -5px rgba(0,0,0,.04);
  --shadow-2xl: 0 25px 50px -12px rgba(0,0,0,.25);

  --transition-fast: all .15s cubic-bezier(.4,0,.2,1);
  --transition-normal: all .30s cubic-bezier(.4,0,.2,1);
}

/* 2) 左上工具面板（类名与第二个文件保持一致） */
/* 仅覆盖左上角控制面板：去掉透明/模糊，改为大屏深色实体背景 */
.tl-panel{
  /* 尺寸与定位保持原样 */
  width: 360px;
  position: absolute;
  top: 16px;
  left: 16px;
  z-index: 1000;
  border-radius: 16px;
  overflow: hidden;

  /* —— 核心改变 START —— */
  /* 1) 实体深色背景（非透明），叠加两层放射渐变做“能量光晕” */
  background:
    radial-gradient(1000px 700px at 12% -20%, rgba(36, 118, 226, 0.14), transparent 60%),
    radial-gradient(900px 600px at 110% 120%, rgba(0,255,163,.12), transparent 60%),
    #0E1A2B; /* 纯深色底，不透底 */

  /* 2) 去掉玻璃拟态：不模糊、不透明 */
  backdrop-filter: none !important;

  /* 3) 改边框与阴影为“霓虹描边 + 内发光”，但整体仍是实体面板 */
  border: 5px solid rgba(139,233,255,.18);
  box-shadow:
     inset 0 0 px rgba(11, 71, 235, 0.1), /* 新增内侧第二层线 */
     inset 0 0 0 1px rgba(255,255,255,.02),
     0 15px 34px rgba(0,0,0,.45),
     0 0 22px rgba(43,217,254,.14);

  color: rgba(255,255,255,.86);
  /* —— 核心改变 END —— */

  /* 分块保持你的结构与命名，仅微调色系以适配深色实体背景 */
  .fun-box{
    border-bottom: 1px solid rgba(255,255,255,.10);
    &:last-child{ border-bottom: none; }

    .header{
      height: 50px;
      padding: 0 14px 0 16px;
      display: flex; align-items: center; justify-content: space-between;
      font-size: 18px; font-weight: 700; letter-spacing: .2px;
      color: rgba(255,255,255,.92);
      position: relative;

      /* 背景条用轻微亮度差，而非透明玻璃 */
      background: linear-gradient(180deg, rgba(255,255,255,.05), rgba(255,255,255,.02));
      border-bottom: 1px solid rgba(255,255,255,.10);

      /* 左侧能量竖条（霓虹渐变） */
      &::before{
        content:''; position:absolute; inset:0 auto 0 0; width:4px;
        background: linear-gradient(180deg, #2BD9FE, #00FFA3);
        box-shadow: 0 0 22px rgba(43,217,254,.25);
      }

      /* 顶部扫光（低频，性能友好；如不需动画可删除本段） */
      &::after{
        content:''; position:absolute; left:-40%; top:0; height:100%; width:40%;
        background: linear-gradient(90deg, transparent, rgba(255,255,255,.16), transparent);
        filter: blur(6px); animation: headSweep 6s linear infinite; pointer-events:none;
      }

      .header-right{
        display:flex; align-items:center; gap:8px; padding:4px 8px;
        border-radius: 10px;
        border: 1px solid rgba(255,255,255,.10);
        background: rgba(255,255,255,.06);   /* 轻亮度块，而不是半透明玻璃 */
        color: rgba(255,255,255,.70);
      }

      /* 适配 Element/Ant 的控件深色外观（保留即可） */
      :deep(.el-checkbox),
      :deep(.ant-checkbox-wrapper){ color: rgba(255,255,255,.70); }
      :deep(.el-checkbox__inner),
      :deep(.ant-checkbox-inner){
        background: rgba(255,255,255,.06); border-color: rgba(255,255,255,.22);
      }
      :deep(.is-checked .el-checkbox__inner),
      :deep(.ant-checkbox-checked .ant-checkbox-inner){
        background-color:#2BD9FE; border-color:#2BD9FE;
        box-shadow: 0 0 10px rgba(43,217,254,.35);
      }
    }

    .main{
      padding: 18px;
      display: flex; align-items: center; flex-wrap: wrap; gap: 10px;

      /* 你的 span 选项保持，视觉统一为“芯片” */
      span{
        min-width: 100px; height: 55px; line-height: 40px; text-align: center;
        border-radius: 14px; cursor: pointer; font-size: 15px; font-weight: 600;
        color: rgba(255,255,255,.70);
        background: linear-gradient(180deg, rgba(255,255,255,.05), rgba(255,255,255,.02));
        border: 1px solid rgba(255,255,255,.16);
        box-shadow: inset 0 0 0 1px rgba(255,255,255,.02);
        position: relative; overflow: hidden;
        transition: transform .16s ease, box-shadow .16s ease, border-color .16s ease, color .16s ease;

        &::before{
          content:''; position:absolute; top:0; left:-120%; width:120%; height:100%;
          background:linear-gradient(90deg, transparent, rgba(255,255,255,.22), transparent);
          transition:left .28s cubic-bezier(.22,.8,.2,1);
        }
        &:hover{
          color:#E7FAFF; border-color: rgba(139,233,255,.35);
          box-shadow: 0 6px 20px rgba(0,0,0,.35), inset 0 0 16px rgba(43,217,254,.12);
          transform: translateY(-1px);
          &::before{ left:100%; }
        }
        &.active{
          color:#0B1220;
          background: linear-gradient(180deg, #2BD9FE, #1AA9E9);
          border-color: transparent;
          box-shadow: 0 0 24px rgba(43,217,254,.35), inset 0 1px 0 rgba(255,255,255,.18);
          transform: translateY(-1px);
          &::after{
            content:''; position:absolute; inset:0;
            background: linear-gradient(180deg, rgba(255,255,255,.28), transparent 45%);
            pointer-events:none;
          }
        }
      }
    }
  }
}
.tl-panel .fun-box .main span{
  --oval-size: 95px;
  --sy: 1.2;
  --dx: 2px;   /* 右移为正，左移为负 */
  --dy: -23px;  /* 下移为正，上移为负 */
}
.tl-panel .fun-box .main span[title="气温"] {
  --oval-size: 97px;
  --sy: 1.2;
  --dx: 0.5px;
  --dy: -22px;
}
.tl-panel .fun-box .main span[title="能见度"] {
  --oval-size: 97px;
  --sy: 1.2;
  --dx: 0.5px;
  --dy: -22px;
}
.tl-panel .fun-box .main span[title="日照"] {
  --oval-size: 97px;
  --sy: 1.2;
  --dx: 0.5px;
  --dy: -23px;
}
.tl-panel .fun-box .main span[title="示范点"] {
  --oval-size: 93px;
  --sy: 1.3;
  --dx: 0.5px;
  --dy: -21px;
}
.tl-panel .fun-box .main span[title="牧场"] {
  --oval-size: 94px;
  --sy: 1.2;
  --dx: 0.5px;
  --dy: -21px;
}
.tl-panel .fun-box .main span[title="自动站"] {
  --oval-size: 95px;
  --sy: 1.2;
  --dx: 1.5px;
  --dy: -22px;
}
.tl-panel .fun-box .main span[title="站点"] {
  --oval-size: 95px;
  --sy: 1.2;
  --dx: 0.5px;
  --dy: -20px;
}
.tl-panel .fun-box .main span[title="色斑图"] {
  --oval-size: 95px;
  --sy: 1.2;
  --dx: 0.5px;
  --dy: -22px;
}
.tl-panel .fun-box .main span[title="风场"] {
  --oval-size: 94px;
  --sy: 1.4;
  --dx: 0.5px;
  --dy: -22px;
}
.tl-panel .fun-box .main span .i-icon{
  display: none !important;           /* 隐藏 mask 版本 */
}

.tl-panel .fun-box .main span .img-fallback{
  display: inline-block !important;
  width: var(--oval-size);
  height: var(--oval-size);
  border-radius: 999px / 70%;   /* 椭圆裁剪 */
  overflow: hidden;
  object-fit: contain;
  transform: translate(var(--dx), var(--dy))  scaleY(var(--sy));
  transform-origin: center;
}
/* 扫光动画（可保留/删除） */
@keyframes headSweep{ 0%{left:-40%;} 60%,100%{left:110%;} }


/* 3) 时间条 & 图例面板（同第一个文件的玻璃卡片外观） */
.timeBar-panel {
  position: absolute; bottom: 20px; left: 50px; right: auto; transform: none;
  width: calc(100% - 40px); max-width: 1300px;
  z-index: 9999; border-radius: var(--border-radius-lg); overflow: hidden;
  background: var(--ds-card);
  border: 1px solid var(--ds-bd);
  box-shadow: var(--shadow-lg);
}
.legend-panel {
  position: absolute; bottom: 40px; right: 40px; width: 720px; z-index: 999;
  border-radius: 16px; overflow: hidden;
  background: var(--ds-card);
  border: 1px solid var(--ds-bd);
  box-shadow: var(--shadow-lg);
  color: var(--ds-txt);
}

/* 4) “气象要素信息统计”对话框（第二个文件使用 el-dialog，这里按第一个文件的对话框/抽屉风格统一） */
.station-dialog {
  .header {
    height: 50px; padding: 0 20px;
    display: flex; align-items: center; gap: 12px;
    font-size: 16px; font-weight: 600; color: var(--text-primary);
    background:
    radial-gradient(1200px 700px at 15% -30%, #25497a, transparent 60%),
    radial-gradient(1000px 640px at 90% 130%, #25497a, transparent 60%),
    #0E1A2B;
    border-bottom: 1px solid #0E1A2B;
    position: relative;
    &::before {
      content: ""; position: absolute; left: 0; top: 0; bottom: 0; width: 4px;
      background: linear-gradient(to bottom, var(--primary-color), var(--secondary-color));
    }
    span { font-size: 13px; color: var(--text-secondary); }
  }
  .info-box .main {
    width: 100%; min-height: 60px; padding: 16px 20px;
    display: flex; align-items: center; justify-content: center;
    gap: 32px; flex-wrap: wrap; background: #0E1A2B;

    /* 深色芯片，悬浮有内光 */
    div {
      display: flex; align-items: center; gap: 8px;
      padding: 8px 16px;
      border-radius: 14px;
      background: linear-gradient(180deg, rgba(255,255,255,.06), rgba(255,255,255,.02));
      border: 1px solid rgba(255,255,255,.10);
      font-size: 14px; font-weight: 500;
      color: rgba(255,255,255,.86);
      transition: .16s ease;

      &:hover {
        background: linear-gradient(180deg, rgba(255,255,255,.08), rgba(255,255,255,.03));
        box-shadow: inset 0 0 0 1px rgba(43,217,254,.20), 0 8px 18px rgba(0,0,0,.35);
      }
    }
  }


  .tc-box{ height: calc(28vh - 52px - 20px); }

}
</style>

<!-- 覆盖第二个文件的全局样式（不加 scoped） -->
<style>
.my-dialog .el-dialog {
  border-radius: var(--border-radius-xl);
  overflow: hidden;
  background: var(--surface-elevated);
  /* Use dark card to match big-screen theme */
  background: var(--ds-card);
  backdrop-filter: blur(20px);
  border: 1px solid var(--ds-bd);
  box-shadow: var(--shadow-2xl);
}
.my-dialog.station-dialog .el-dialog{
  position: fixed !important;
  left: 20px; right: 20px; bottom: 20px; top: auto;
  margin: 0 !important;
  transform: none !important;
  width: auto !important;        /* 覆盖内联宽度 */
  max-height: 40vh;              /* 你也可调成 50vh / 60vh */
  display: flex; flex-direction: column;
}

/* 去掉多余内边距并控制内部滚动 */
.my-dialog.station-dialog .el-dialog__body{
  padding: 0;
  overflow: hidden;              /* 避免 body 区域再滚动 */
}
.my-dialog .el-dialog__header{
  padding:0;
  background: linear-gradient(135deg, rgba(59,130,246,.08) 0%, rgba(16,185,129,.08) 100%);
  border-bottom: 1px solid rgba(0,0,0,.05);
  position: relative;
}
.my-dialog .el-dialog__header::before{
  content:""; position:absolute; left:0; top:0; bottom:0; width:4px;
  background: linear-gradient(to bottom, var(--primary-color), var(--secondary-color));
}
/* 关闭按钮同系列风格 */
.my-dialog .el-dialog__headerbtn{
  position:absolute; top:12px; right:12px; width:40px; height:40px; border-radius:50%;
  background: rgba(239,68,68,.1); border:1px solid rgba(239,68,68,.2);
  transition: var(--transition-fast);
}
.my-dialog .el-dialog__headerbtn:hover{ background: rgba(239,68,68,.2); transform: scale(1.05); }
.my-dialog .el-dialog__close{ width:24px; height:24px; color: var(--error-color); transition: var(--transition-fast); }
.my-dialog .el-dialog__close:hover{ transform: rotate(90deg); }

/* Segmented 风格（如果页面里有 .my-segmented） */
.my-segmented {
  width:320px; height:48px; position:absolute; z-index:1; margin:auto; left:0; right:0; top:20px;
}
.my-segmented :is(.el-segmented){
  background: rgba(248,250,252,.8); border:1px solid rgba(0,0,0,.08); border-radius: var(--border-radius-lg); padding:4px;
}
.my-segmented .el-segmented__item{
  border-radius: var(--border-radius-md); transition: var(--transition-fast); font-weight: 500;
}
.my-segmented .el-segmented__item:hover{ background: rgba(255,255,255,.6); }
.my-segmented .el-segmented__item.is-selected{
  background: linear-gradient(135deg, var(--primary-color), var(--primary-dark));
  color: var(--text-inverse); box-shadow: var(--shadow-md);
}

/* 滚动条/进场动画（与第一个文件一致） */
::-webkit-scrollbar{ width:6px; height:6px; }
::-webkit-scrollbar-track{ background: rgba(0,0,0,.05); border-radius:3px; }
::-webkit-scrollbar-thumb{ background: rgba(0,0,0,.2); border-radius:3px; }
::-webkit-scrollbar-thumb:hover{ background: rgba(0,0,0,.3); }

@keyframes fadeInUp{ from{opacity:0;transform:translateY(20px)} to{opacity:1;transform:translateY(0)} }
@keyframes slideInRight{ from{opacity:0;transform:translateX(20px)} to{opacity:1;transform:translateX(0)} }
.tl-panel,.timeBar-panel,.station-dialog{ animation: fadeInUp .6s cubic-bezier(.4,0,.2,1); }
.legend-panel{ animation: slideInRight .6s cubic-bezier(.4,0,.2,1); }
</style>

<template>
    <svg aria-hidden="true"
         style="position: absolute; width: 0; height: 0; overflow: hidden">
        <symbol id="icon-close"
                viewBox="0 0 1000 1000"
                pointer-events="all">
            <circle cx="500"
                    cy="500"
                    r="450"
                    fill="none"
                    stroke="currentColor"
                    stroke-width="100"
                    stroke-dasharray="200% 40% 60 30%"
                    stroke-linecap="round" />
            <line x1="320"
                  y1="320"
                  x2="680"
                  y2="680"
                  stroke="currentColor"
                  stroke-width="80"
                  stroke-linecap="round" />
            <line x1="320"
                  y1="680"
                  x2="680"
                  y2="310"
                  stroke="currentColor"
                  stroke-width="80"
                  stroke-linecap="round" />
        </symbol>
    </svg>

    <div id="map-container"></div>
    <MapInfoBar v-if="refmap"
                :map="refmap" />
    <div class="timeBar-panel">
        <TimeBar class="timeBar-box"
                 v-model="selectTime"
                 :="timeBarConfig"
                 @change-bar="timeBarConfigChange"
                 @point-click="timeBarPoint" />
    </div>
    <div class="legend-panel">
        <Legend v-if="showPreLegend"
                :choose="preLegendType + '小时降水'"
                :filter="preFilterRange"
                :filterChange="preFilterChange" />
        <Legend v-if="showTemLegend"
                choose="气温"
                :filter="temFilterRange"
                :filterChange="temFilterChange" />
        <Legend v-if="showWinLegend"
                choose="风"
                :filter="winFilterRange"
                :filterChange="winFilterChange" />
    </div>

    <div class="tl-panel">
        <div class="fun-box">
            <div class="header">气象要素</div>
            <div class="main">
  <span v-for="item in ['降水','气温','风','能见度']"
        :key="item"
        :class="{ active: metElement == item }"
        @click="handleMetElement(item)"
        :title="item">
     <i class="i-icon" :style="maskStyle(iconOf(item))" v-show="supportsMask"></i>
    <img class="img-fallback" :src="iconOf(item)" :alt="item" v-show="!supportsMask" />
  </span>
</div>
        </div>
        <div class="fun-box">
            <div class="header">展示方式
                <div class="header-right"
                     v-if="showStationLayer">
                    <el-checkbox v-model="showStationName"
                                 label="显示站名"
                                 border
                                 @change="handleShowStationNameValue" />
                    <el-checkbox v-model="showStationValue"
                                 label="显示站值"
                                 border
                                 @change="handleShowStationNameValue" />
                </div>
            </div>
            <div class="main">
                <span :class="{ active: showStationLayer }"
        @click="handleStationLayer"
        :title="'站点'">
    <i class="i-icon" :style="maskStyle(iconOf('站点'))" v-show="supportsMask"></i>
    <img class="img-fallback" :src="iconOf('站点')" alt="站点" v-show="!supportsMask" />
    <!-- <span class="sr-only">站点</span> 可选：保留给读屏 -->
  </span>

  <span :class="{ active: showGridLayer }"
        @click="handleGridLayer"
        :title="'色斑图'">
    <i class="i-icon" :style="maskStyle(iconOf('色斑图'))" v-show="supportsMask"></i>
    <img class="img-fallback" :src="iconOf('色斑图')" alt="色斑图" v-show="!supportsMask" />
    <!-- <span class="sr-only">色斑图</span> -->
  </span>
                <!-- <span v-if="metElement == '风'"
                      :class="{ active: showWinFieldLayer }"
                      @click="handleWinFieldLayer">风场</span> -->
            </div>
        </div>
        <!-- <div class="fun-box">
            <div class="header">信息统计
            </div>
            <div class="main">
                <span :class="{ active: showStatisticsLayer }"
                      @click="handleStatisticsLayer">统计表</span>
            </div>
        </div> -->
    </div>



    <el-dialog v-model="showStaTCDialog"
               class="my-dialog station-dialog"
               width="calc(100% - 40px)"
               z-index="9999"
               :style="{
                 position: 'fixed',
                 left:  '20px',
                 right: '20px',
                 bottom:'30px',
                 top:   'auto',
                 margin:'0',
                 transform: 'none',

              }"
               :draggable="false"
               overflow
               modal-penetrable
               :modal="false">
        <template #header>
            <div class="header">
                <span>气象要素信息统计</span>
                <span style="font-size: 14px;">
                    （ {{ timeBarConfig.bar == "时"
                        ? selectTime.format("YYYY-MM-DD HH:00:00") + " 至 " + selectTime.add(72, "hour").format("YYYY-MM-DD HH:00:00")
                        : selectTime.format("YYYY-MM-DD") + " 至 " + selectTime.add(6, "day").format("YYYY-MM-DD") }} ）</span>
            </div>
        </template>
        <div class="info-box">
          <div class="main">
            <div>牧场：{{ staTCInfo.自动站 }}</div>
            <div>累积降水：{{ staTCInfo.累积降水 ?? "--" }} mm</div>
            <div>平均降水：{{ staTCInfo.平均降水 ?? "--" }} mm/h</div>
            <div>最高温度：{{ staTCInfo.最高温度 ?? "--" }} ℃</div>
            <div>最低温度：{{ staTCInfo.最低温度 ?? "--" }} ℃</div>
            <div>平均温度：{{ staTCInfo.平均温度 ?? "--" }} ℃</div>
            <div>最大风速：{{ staTCInfo.最大风速 ?? "--" }} m/s</div>
            <div>最小风速：{{ staTCInfo.最小风速 ?? "--" }} m/s</div>
          </div>
        </div>
        <div class="tc-box">
            <TableChart :="staTCConfig"
                        v-model="staTCData" />
        </div>
    </el-dialog>


    <el-dialog class="my-dialog"
               v-model="showStatisticsLayer"
               width="1800"
               z-index="99999"
               align-center
               destroy-on-close
               close-icon=""
               @close="showStatisticsLayer = false">

        <el-segmented class="my-segmented"
                      v-model="segmentedMetElement"
                      :options="['降水', '气温', '风']"
                      @change="getStationDataByTime" />
        <TableChart style="height: 600px;"
                    v-model="tcAllStaData"
                    :="tcAllStaOpt" />
    </el-dialog>

</template>

<script setup>
import { ref, onMounted, computed, shallowRef } from "vue";
import axios from "axios";
import dayjs from "dayjs";
import * as turf from "@turf/turf";
import { ElMessage } from "element-plus"
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import "leaflet-velocity/dist/leaflet-velocity.css";
import "leaflet-velocity";
import "leaflet.glify";

import IconClose from "@/components/icons/IconClose.vue";
import Legend from "@/components/my-legend/Legend.vue";
import TimeBar from "@/components/my-time-bar/TimeBar.vue";
import TableChart from "@/components/my-table-chart/index.vue";
import MapInfoBar from "@/components/my-map-info-bar/MapInfoBar.vue";

import { idwToGrid } from "@/components/utils.js";
import interfaces from "@/components/interfaces.js";
import ZiDongZhan from "@/assets/GeographicData/自动站.json"
import ShiBianJie from "@/assets/GeographicData/吴忠市级边界.json";
import rain from '@/static/icons/rain.png'
import thermo from '@/static/icons/thermo.png'
import eye    from '@/static/icons/eye.png'
import sun from '@/static/icons/sun.png'
import wind from '@/static/icons/wind.png'
import demo from '@/static/icons/demo.png'
import ranch from '@/static/icons/ranch.png'
import tower from '@/static/icons/tower.png'
import pin from '@/static/icons/pin.png'
import grid from '@/static/icons/grid.png'
import tjb from '@/static/icons/tjb.png'
import  windfield from '@/static/icons/windfield.png'
const ICONS = {
  // 气象要素
  '降水': rain,
  '气温': thermo,
  '风': wind,
  '能见度': eye,
  '日照': sun,
  // 站点
  '示范点': demo,
  '牧场': ranch,
  '自动站': tower,
  // 展示方式
  '站点': pin,
  '色斑图': grid,
  '风场': windfield,
  '统计表': tjb,
};

const iconOf = (name) => ICONS[name] || ICONS['站点']
// 关键：给 mask 的 url 加上引号，避免路径里有特殊字符时解析失败
const maskStyle = (url) => ({ '--icon': `url("${url}")` });

// 是否支持 CSS mask（极老环境回退 <img>）
const supportsMask =
  typeof CSS !== 'undefined' &&
  ((CSS.supports && CSS.supports('-webkit-mask', 'url("")')) ||
   (CSS.supports && CSS.supports('mask', 'url("")')));



let refmap = shallowRef(null),
    map = null,
    metElement = ref(""),               // 气象要素
    showStatisticalLayer = ref(false),  // 是否显示
    showStationName = ref(false),       // 显示站点图层时，是否显示站点的名称
    showStationValue = ref(true),       // 显示站点图层时，是否显示站点的值
    showStationLayer = ref(true),       // 是否显示站点图层
    showGridLayer = ref(false),         // 是否显示网格图层
    showWinFieldLayer = ref(false),     // 是否显示风场图层
    showPreLegend = computed(() => (showStationLayer.value || showGridLayer.value) && metElement.value == "降水"),// 是否显示降水图例
    showTemLegend = computed(() => (showStationLayer.value || showGridLayer.value) && metElement.value == "气温"),// 是否显示气温图例
    showWinLegend = computed(() => (showStationLayer.value || showGridLayer.value || showWinFieldLayer.value) && metElement.value == "风"),// 是否显示大风图例
    preFilterRange = [0, 7],            // 降水图例过滤范围
    temFilterRange = [0, 19],           // 气温图例过滤范围
    winFilterRange = [0, 13],           // 大风图例过滤范围
    showStaTCDialog = ref(false),       // 是否显示单个站点的时序图
    staTCConfig = ref({}),              // 单个站点时序图配置
    staTCData = ref([]),                // 单个站点时序图数据
    staTCInfo = ref({}),                // 单个站点气象数据
    segmentedMetElement = ref("降水"),  // 统计表气象要素
    showAllStaTC = ref(true),           // 是否显示所有站点统计表
    allStaTCConfig = ref({}),           // 所有站点统计表配置
    allStaTCData = ref([]),             // 所有站点统计表数据
    GLOBAL_CACHE = {
        pre: {},
        win: {},
        tem: {}
    };

let selectTime = ref(dayjs().set("hour", 8).set("minute", 0).set("second", 0));// 时间条上的时间
selectTime.value = dayjs("2025-05-10 08:00:00")
let timeBarPointChecked = ref(0),      // 时间条上被选中的时间点，起始为 0
    timeBarConfig = ref({               // 时间条配置
        bar: "时",
        dateFormat: "YYYY-MM-DD HH",
        unit: "hour",
        difference: 1,
        points: 72,
        step: 1,
    }),
    timeBarConfigChange = (type) => {
        timeBarConfig.value = type == "时" ? {
            bar: "时",
            dateFormat: "YYYY-MM-DD HH",
            unit: "hour",
            difference: 1,
            points: 72,
            step: 1,
        } : {
            bar: "日",
            dateFormat: "YYYY-MM-DD",
            unit: "day",
            difference: 0,
            points: 7,
            step: 1,
        };
    };
// 时间条上的时间点的点击事件
function timeBarPoint(obj) {
    if (showStationLayer.value || showGridLayer.value) {
        timeBarPointChecked.value = obj.point - 1;
        if (metElement.value == "降水") getPreGridData();
        if (metElement.value == "气温") getTemGridData();
        if (metElement.value == "风") getWinGridData();
    }
}

// 等级
const
    preLegendColor = ["#ffffff", "#a3f28f", "#3dba3d", "#61b8ff", "#0500e1", "#fa00fa"],
    winLegendColor = ["#ffffff", "#9bbce8", "#6b9de1", "#3b7edb", "#2b5cc2", "#049122", "#edca0a", "#fe9900", "#fe7500", "#fe5500", "#fe2a00", "#fa0025", "#f00087"],
    temLegendColor = ["#800080", "#00309e", "#185ab4", "#2073dc", "#41a0e6", "#72d2fa", "#96e2f0", "#befaff", "#f0faff", "#dcffd5", "#c0feb3", "#b4ff82", "#fafa8f", "#fff2c0", "#ffdfb0", "#ffae76", "#ff878a", "#ff5600", "#8f0814"],
    preLegendType = computed(() => {
        let hour = timeBarConfig.value.bar == "时" ? 1 : 24;
        return hour <= 1 ? 1 :
            hour <= 3 ? 3 :
                hour <= 6 ? 6 :
                    hour <= 12 ? 12 : 24;
    }),
    preThreshold = computed(() => {
        let threshold =
            preLegendType.value == 1 ? [0.1, 1.6, 7, 15, 40, 50] :
                preLegendType.value == 3 ? [0.1, 3, 10, 20, 50, 70] :
                    preLegendType.value == 6 ? [0.1, 4, 13, 25, 60, 120] :
                        preLegendType.value == 12 ? [0.1, 5, 15, 30, 70, 140] : [0.1, 10, 25, 50, 100, 250];
        return [
            { id: 0, color: preLegendColor[0], threshold: null, name: "无" },
            { id: 1, color: preLegendColor[1], threshold: threshold[0], name: "小雨" },
            { id: 2, color: preLegendColor[2], threshold: threshold[1], name: "中雨" },
            { id: 3, color: preLegendColor[3], threshold: threshold[2], name: "大雨" },
            { id: 4, color: preLegendColor[4], threshold: threshold[3], name: "暴雨" },
            { id: 5, color: preLegendColor[5], threshold: threshold[4], name: "大暴雨" },
            { id: 6, color: preLegendColor[6], threshold: threshold[5], name: "特大暴雨" }]
    }),
    winThreshold = ref([
        { id: 0, color: winLegendColor[0], threshold: 0, name: "0级" },
        { id: 1, color: winLegendColor[1], threshold: 0.3, name: "1级" },
        { id: 2, color: winLegendColor[2], threshold: 1.6, name: "2级" },
        { id: 3, color: winLegendColor[3], threshold: 3.4, name: "3级" },
        { id: 4, color: winLegendColor[4], threshold: 5.5, name: "4级" },
        { id: 5, color: winLegendColor[5], threshold: 8.0, name: "5级" },
        { id: 6, color: winLegendColor[6], threshold: 10.8, name: "6级" },
        { id: 7, color: winLegendColor[7], threshold: 13.9, name: "7级" },
        { id: 8, color: winLegendColor[8], threshold: 17.2, name: "8级" },
        { id: 9, color: winLegendColor[9], threshold: 20.8, name: "9级" },
        { id: 10, color: winLegendColor[10], threshold: 24.5, name: "10级" },
        { id: 11, color: winLegendColor[11], threshold: 28.5, name: "11级" },
        { id: 12, color: winLegendColor[12], threshold: 32.7, name: "12级" },
    ]),
    temThreshold = ref([
        { id: 0, color: temLegendColor[0], threshold: null },
        { id: 1, color: temLegendColor[1], threshold: -32 },
        { id: 2, color: temLegendColor[2], threshold: -28 },
        { id: 3, color: temLegendColor[3], threshold: -24 },
        { id: 4, color: temLegendColor[4], threshold: -20 },
        { id: 5, color: temLegendColor[5], threshold: -16 },
        { id: 6, color: temLegendColor[6], threshold: -12 },
        { id: 7, color: temLegendColor[7], threshold: -8 },
        { id: 8, color: temLegendColor[8], threshold: -4 },
        { id: 9, color: temLegendColor[9], threshold: 0 },
        { id: 10, color: temLegendColor[10], threshold: 4 },
        { id: 11, color: temLegendColor[11], threshold: 8 },
        { id: 12, color: temLegendColor[12], threshold: 12 },
        { id: 13, color: temLegendColor[13], threshold: 16 },
        { id: 14, color: temLegendColor[14], threshold: 20 },
        { id: 15, color: temLegendColor[15], threshold: 24 },
        { id: 16, color: temLegendColor[16], threshold: 28 },
        { id: 17, color: temLegendColor[17], threshold: 32 },
        { id: 18, color: temLegendColor[18], threshold: 35 },
    ]);
function getPreThreshold(value) {
    return value < preThreshold.value[1].threshold ? preThreshold.value[0] :
        value < preThreshold.value[2].threshold ? preThreshold.value[1] :
            value < preThreshold.value[3].threshold ? preThreshold.value[2] :
                value < preThreshold.value[4].threshold ? preThreshold.value[3] :
                    value < preThreshold.value[5].threshold ? preThreshold.value[4] :
                        value < preThreshold.value[6].threshold ? preThreshold.value[5] : preThreshold.value[6];
}
function getWinThreshold(value) {
    return value < winThreshold.value[1].threshold ? winThreshold.value[0] :
        value < winThreshold.value[2].threshold ? winThreshold.value[1] :
            value < winThreshold.value[3].threshold ? winThreshold.value[2] :
                value < winThreshold.value[4].threshold ? winThreshold.value[3] :
                    value < winThreshold.value[5].threshold ? winThreshold.value[4] :
                        value < winThreshold.value[6].threshold ? winThreshold.value[5] :
                            value < winThreshold.value[7].threshold ? winThreshold.value[6] :
                                value < winThreshold.value[8].threshold ? winThreshold.value[7] :
                                    value < winThreshold.value[9].threshold ? winThreshold.value[8] :
                                        value < winThreshold.value[10].threshold ? winThreshold.value[9] :
                                            value < winThreshold.value[11].threshold ? winThreshold.value[10] :
                                                value < winThreshold.value[12].threshold ? winThreshold.value[11] : winThreshold.value[12];
}
function getTemThreshold(value) {
    return value < temThreshold.value[1].threshold ? temThreshold.value[0] :
        value < temThreshold.value[2].threshold ? temThreshold.value[1] :
            value < temThreshold.value[3].threshold ? temThreshold.value[2] :
                value < temThreshold.value[4].threshold ? temThreshold.value[3] :
                    value < temThreshold.value[5].threshold ? temThreshold.value[4] :
                        value < temThreshold.value[6].threshold ? temThreshold.value[5] :
                            value < temThreshold.value[7].threshold ? temThreshold.value[6] :
                                value < temThreshold.value[8].threshold ? temThreshold.value[7] :
                                    value < temThreshold.value[9].threshold ? temThreshold.value[8] :
                                        value < temThreshold.value[10].threshold ? temThreshold.value[9] :
                                            value < temThreshold.value[11].threshold ? temThreshold.value[10] :
                                                value < temThreshold.value[12].threshold ? temThreshold.value[11] :
                                                    value < temThreshold.value[13].threshold ? temThreshold.value[12] :
                                                        value < temThreshold.value[14].threshold ? temThreshold.value[13] :
                                                            value < temThreshold.value[15].threshold ? temThreshold.value[14] :
                                                                value < temThreshold.value[16].threshold ? temThreshold.value[15] :
                                                                    value < temThreshold.value[17].threshold ? temThreshold.value[16] :
                                                                        value < temThreshold.value[18].threshold ? temThreshold.value[17] : temThreshold.value[18];
}

onMounted(() => {
    refmap.value = map = L.map("map-container", {
        center: [37.42, 106.6],
        minZoom: 3,
        maxZoom: 18,
        zoom: 9,
        zoomControl: false,
        doubleClickZoom: false,
        attributionControl: false,
        // renderer: L.canvas()
    })
});

// ---------- 显示站值、站名 ----------
function handleShowStationNameValue() {
    if (showStationLayer.value) { renderStationLayer(GLOBAL_CACHE.curStationData); }
}
// ---------- 降水过滤 ----------
function preFilterChange(data) {
    preFilterRange = data;
    if (showStationLayer.value) { renderStationLayer(GLOBAL_CACHE.curStationData); }
    if (showGridLayer.value) { renderGridLayer(GLOBAL_CACHE.curGridData); }
}
// ---------- 温度过滤 ----------
function temFilterChange(data) {
    temFilterRange = data;
    if (showStationLayer.value) { renderStationLayer(GLOBAL_CACHE.curStationData); }
    if (showGridLayer.value) { renderGridLayer(GLOBAL_CACHE.curGridData); }
}
// ---------- 风过滤 ----------
function winFilterChange(data) {
    winFilterRange = data;
    if (showStationLayer.value) { renderStationLayer(GLOBAL_CACHE.curStationData); }
    if (showGridLayer.value) { renderGridLayer(GLOBAL_CACHE.curGridData); }
}
// ---------- 切换气象要素 ----------
function handleMetElement(data) {
    if (metElement.value != data) {
        metElement.value = data;
        if (showStationLayer.value || showGridLayer.value || showWinFieldLayer.value) {
            if (data == "降水") getPreGridData();
            if (data == "气温") getTemGridData();
            if (data == "风") getWinGridData();
        }
    }
}
// ---------- 展示方式 ----------
function handleWinFieldLayer() {
    showWinFieldLayer.value = !showWinFieldLayer.value;
    if (showWinFieldLayer.value) {
        getWinGridData();
    } else {
        removeLayerById("current-winfield-layer");
    }
}
function handleStationLayer() {
    showStationLayer.value = !showStationLayer.value;
    if (showStationLayer.value) {
        if (metElement.value == "降水") getPreGridData();
        if (metElement.value == "气温") getTemGridData();
        if (metElement.value == "风") getWinGridData();
    } else {
        removeLayerById("current-station-layer");
    }
}
function handleGridLayer() {
    showGridLayer.value = !showGridLayer.value;
    if (showGridLayer.value) {
        if (metElement.value == "降水") getPreGridData();
        if (metElement.value == "气温") getTemGridData();
        if (metElement.value == "风") getWinGridData();
    } else {
        removeLayerById("current-grid-layer");
    }
}
// // ---------- 统计表 ----------
// function handleStatisticsLayer() {
//     showStatisticsLayer.value = !showStatisticsLayer.value;
//     if (showStatisticsLayer.value) {
//         getStationDataByTime();
//     }
// }



// ---------- 获取网格数据 ----------
function getPreGridData() {
    let format = timeBarConfig.value.bar == "时" ? "YYYYMMDDHH0000" : "YYYYMMDD";
    let time = selectTime.value.format(format);
    if (GLOBAL_CACHE.pre[time]) {
        if (showStationLayer.value) { transformGridToStation(GLOBAL_CACHE.pre[time]); }
        if (showGridLayer.value) { renderGridLayer(GLOBAL_CACHE.pre[time]); }
    } else {
        axios({
            method: "get",
            url: interfaces.serverUrl + "data/pre/" + time + ".json",
        }).then(response => {
            let data = response.data;
            if (data) {
                GLOBAL_CACHE.pre[time] = data;
                if (showStationLayer.value) { transformGridToStation(data); }
                if (showGridLayer.value) { renderGridLayer(data); }
            } else {
                removeLayerById("current-grid-layer");
                removeLayerById("current-station-layer");
                ElMessage({
                    showClose: true,
                    message: `无数据（时间：${selectTime.value.format("YYYY-MM-DD HH:00:00")}）`,
                    type: "error",
                    offset: document.documentElement.clientHeight / 2
                })
            }
        })
    }
}
function getTemGridData() {
    let format = timeBarConfig.value.bar == "时" ? "YYYYMMDDHH0000" : "YYYYMMDD";
    let time = selectTime.value.format(format);
    if (GLOBAL_CACHE.tem[time]) {
        if (showStationLayer.value) { transformGridToStation(GLOBAL_CACHE.tem[time]); }
        if (showGridLayer.value) { renderGridLayer(GLOBAL_CACHE.tem[time]); }
    } else {
        axios({
            method: "get",
            url: interfaces.serverUrl + "data/tem/" + time + ".json",
        }).then(response => {
            let data = response.data;
            if (data) {
                GLOBAL_CACHE.tem[time] = data;
                if (showStationLayer.value) { transformGridToStation(data) }
                if (showGridLayer.value) { renderGridLayer(data); }
            } else {
                removeLayerById("current-grid-layer");
                removeLayerById("current-station-layer");
                ElMessage({
                    showClose: true,
                    message: `无数据（时间：${selectTime.value.format("YYYY-MM-DD HH:00:00")}）`,
                    type: "error",
                    offset: document.documentElement.clientHeight / 2
                })
            }
        })
    }
}
function getWinGridData() {
    let format = timeBarConfig.value.bar == "时" ? "YYYYMMDDHH0000" : "YYYYMMDD";
    let time = selectTime.value.format(format);
    if (GLOBAL_CACHE.pre[time]) {
        if (showStationLayer.value) { transformGridToStation(GLOBAL_CACHE.win[time]); }
        if (showGridLayer.value) { renderGridLayer(GLOBAL_CACHE.win[time]); }
    } else {
        axios({
            method: "get",
            url: interfaces.serverUrl + "data/win/" + time + ".json",
        }).then(response => {
            let data = response.data;
            if (data) {
                GLOBAL_CACHE.win[time] = data;
                if (showStationLayer.value) { transformGridToStation(data) }
                if (showGridLayer.value) { renderGridLayer(data); }
            } else {
                removeLayerById("current-grid-layer");
                removeLayerById("current-station-layer");
                ElMessage({
                    showClose: true,
                    message: `无数据（时间：${selectTime.value.format("YYYY-MM-DD HH:00:00")}）`,
                    type: "error",
                    offset: document.documentElement.clientHeight / 2
                })
            }
        })
    }
}
// ---------- 渲染格点图层 ----------
function renderGridLayer(data) {

    let filterRange = [];
    let funThreshold;
    if (metElement.value == "降水") {
        filterRange = preLegendColor.slice(preFilterRange[0], preFilterRange[1]);
        funThreshold = getPreThreshold;
    } else if (metElement.value == "气温") {
        filterRange = temLegendColor.slice(temFilterRange[0], temFilterRange[1]);
        funThreshold = getTemThreshold;
    } else if (metElement.value == "风") {
        filterRange = winLegendColor.slice(winFilterRange[0], winFilterRange[1]);
        funThreshold = getWinThreshold;
    }
    let ds = data.DS[timeBarPointChecked.value],
        startLon = data.startLon, startLat = data.startLat,
        lonStep = data.lonStep, latStep = data.latStep,
        lonCount = data.lonCount || ds[0].length, latCount = data.latCount || ds.length;
    GLOBAL_CACHE.curGridData = data;
    let box = turf.feature(ShiBianJie.features[0].geometry);
    let features = [];
    for (let y = 0; y < latCount; y++) {
        for (let x = 0; x < lonCount; x++) {
            let lon = startLon + lonStep * (x + 0.5),
                lat = startLat + latStep * (y + 0.5),
                val = ds[y][x];
            let config = funThreshold(val);

            // 过滤
            let filter = filterRange.includes(config.color);
            if (filter) {
                let cell = {
                    type: "Feature",
                    properties: {
                        color: config.color,
                        idx: x,
                        idy: y
                    },
                    geometry: {
                        type: "Polygon",
                        coordinates: [[
                            [startLon + lonStep * x, startLat + latStep * y],
                            [startLon + lonStep * (x + 1), startLat + latStep * y],
                            [startLon + lonStep * (x + 1), startLat + latStep * (y + 1)],
                            [startLon + lonStep * x, startLat + latStep * (y + 1)],
                            [startLon + lonStep * x, startLat + latStep * y],
                        ]],
                    }
                }
                // if (turf.booleanContains(box, cell)) {
                features.push(cell);
                // }
                // else if (turf.booleanOverlap(box, cell)) {
                //     // 裁剪边界
                //     var intersection = turf.intersect(turf.featureCollection([box, cell]), { properties: { color: config.color } });
                //     features.push(intersection);
                // }
            }
        }
    };
    removeLayerById("current-grid-layer");
    GLOBAL_CACHE["current-grid-layer"] = L.glify.shapes({
        map: map,
        data: { "type": "FeatureCollection", "features": features },
        // border: true,
        color: (index, feature) => {
            let hexColor = feature.properties.color.replace("#", "");
            let num = parseInt(hexColor, 16);
            let r = (num >> 16) & 255;
            let g = (num >> 8) & 255;
            let b = num & 255;
            return { r: r / 255, g: g / 255, b: b / 255, a: 0.8 }
        },
        click: (e, feature) => {
            // console.log(e, feature);
            let idx = feature.properties.idx;
            let idy = feature.properties.idy;
            let timeDiff = 24;
            let timeSeries = Array.from({ length: timeDiff }, (v, i) => selectTime.value.add(i, "hour").format("DD日HH时"));
            staTCData.value = Array.from({ length: timeDiff }, (v, i) => ({
                time: timeSeries[i],
                val: GLOBAL_CACHE.curGridData.DS[i][idy][idx],
            }));

            staTCConfig.value = {
                title: "",
                toolbar: [
                    { name: "图表转换", mode: true },
                    { name: "行列转换", mode: true },
                    // { name: "编辑表格", mode: false },
                    // { name: "导出表格" }
                ],
                column: [
                    { width: "140px", prop: "time", label: "时间", axis: "x", isHeader: true, sortable: true },
                    metElement.value == "降水"
                        ? { prop: "val", label: "降水", unit: "mm", axis: "y", sortable: true }
                        : metElement.value == "气温"
                            ? { prop: "val", label: "气温", unit: "℃", axis: "y", sortable: true, format: (v) => (v - 273.15).toFixed(1) * 1 }
                            : { prop: "val", label: "风速", unit: "m/s", axis: "y", sortable: true }
                ],
            };
            showStaTCDialog.value = true;
            gridCellChecked(feature);
        },
        // hover: (e, feature) => {
        //     console.log(e, feature)
        // },
        // hoverOff: (e, feature) => {
        //     console.log(e, feature)
        // }
    });
}

function gridCellChecked(data) {
    removeLayerById("current-cell-layer");
    GLOBAL_CACHE["current-cell-layer"] = L.geoJSON(data, {
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
}
// ---------- 网格数据转站点数据 ----------
function transformGridToStation(data) {
    let stations = structuredClone(ZiDongZhan);
    let DS = data.DS,  // 三维数组 [hour][latIdx][lonIdx]
        startLon = data.startLon, startLat = data.startLat,
        lonStep = data.lonStep, latStep = data.latStep;
    for (let sta of stations) {
        let latIdx = Math.floor((sta.lat - startLat) / latStep);
        let lonIdx = Math.floor((sta.lon - startLon) / lonStep);
        let dataSeries = [];
        for (let hour = 0; hour < DS.length; hour++) {
            let val = DS[hour]?.[latIdx]?.[lonIdx];
            dataSeries.push(val.toFixed(1) * 1);
        }
        sta.val = dataSeries[timeBarPointChecked.value] ?? "--";
        let config = metElement.value == "降水" ? getPreThreshold(sta.val || 0) : metElement.value == "气温" ? getTemThreshold(sta.val || 0) : getWinThreshold(sta.val || 0);
        sta.color = config.color;
        sta.level = config.name;
        sta.dataSeries = dataSeries;
    }
    GLOBAL_CACHE.curStationData = stations;
    renderStationLayer(stations)
}

// ---------- 渲染站点图层 ----------
function renderStationLayer(data) {
    let filterRange = [];
    if (metElement.value == "降水") {
        filterRange = preLegendColor.slice(preFilterRange[0], preFilterRange[1]);
    } else if (metElement.value == "气温") {
        filterRange = temLegendColor.slice(temFilterRange[0], temFilterRange[1]);
    } else if (metElement.value == "风") {
        filterRange = winLegendColor.slice(winFilterRange[0], winFilterRange[1]);
    }
    var group = [];
    for (let i = 0, len = data.length; i < len; i++) {
        let item = data[i];
        // 过滤
        let filter = filterRange.includes(item.color);
        if (filter) {
            let layer = L.marker([item.lat, item.lon], {
                icon: L.divIcon({
                    className: "my-marker-box",
                    iconSize: [0, 0],
                    html: `
                        <div class="my-marker${showStationValue.value ? ' show-value' : ''}${showStationName.value ? ' show-name' : ''}">
                            <div class="icon">${getIcon(item.color, item.val, item.d)}</div>
                            <div class="shade">
                                <span class="val">${item.val}</span>
                                <span class="name">${item.short_name}</span>
                            </div>
                        </div>`,
                }),
                riseOnHover: true,
                item: item,
            }).on("click", function (e) {
                // renderPreStationPopup(e.target.options.item);
                getAllMetElData(item);
            })
            group.push(layer);
        }
    }
    removeLayerById("current-station-layer");
    GLOBAL_CACHE["current-station-layer"] = L.layerGroup(group).addTo(map);
}

// ---------- 获取多个气象要素数据，渲染弹窗 ----------
async function getAllMetElData(station) {
    let format = timeBarConfig.value.bar == "时" ? "YYYYMMDDHH0000" : "YYYYMMDD";
    let time = selectTime.value.format(format);
    let preData = await getDataByTypeAndTime("pre", time),
        temData = await getDataByTypeAndTime("tem", time),
        winData = await getDataByTypeAndTime("win", time);
    let stations = StationMappingToGrid(preData, temData, winData);
    station = stations.find(v => v.sid = station.sid);
    let preSeries = preData.DS.map(v => v[station.preIdx[1]][station.preIdx[0]]);
    let temSeries = temData.DS.map(v => v[station.temIdx[1]][station.temIdx[0]]);
    let winSeries = winData.DS.map(v => v[station.winIdx[1]][station.winIdx[0]]);
    let timeSeries = Array.from({ length: timeBarConfig.value.points }, (v, i) => timeBarConfig.value.bar == "时"
        ? selectTime.value.add(timeBarConfig.value.difference + i, "h").format("DD日HH时")
        : selectTime.value.add(timeBarConfig.value.difference + i, "d").format("YYYY-MM-DD"));

    staTCData.value = Array.from({ length: timeBarConfig.value.points }, (v, i) => ({
        time: timeSeries[i],
        pre: preSeries[i],
        tem: temSeries[i],
        win: winSeries[i],
    }));
    staTCInfo.value = {
        "自动站": station.short_name,
        "第1日累积降水": (preSeries.reduce((acc, cur) => acc + cur, 0)).toFixed(1) * 1,
        "第2日累积降水": (preSeries.reduce((acc, cur) => acc + cur, 0)).toFixed(1) * 1,
        "第3日累积降水": (preSeries.reduce((acc, cur) => acc + cur, 0)).toFixed(1) * 1,

        "第1日最高温度": Math.max(...temSeries.slice(0, 23)),
        "第1日最低温度": Math.max(...temSeries.slice(0, 23)),
        "第1日平均温度": (temSeries.slice(0, 23).reduce((acc, cur) => acc + cur, 0) / 24).toFixed(1) * 1,
        "第2日最高温度": Math.max(...temSeries.slice(24, 47)),
        "第2日最低温度": Math.max(...temSeries.slice(24, 47)),
        "第2日平均温度": (temSeries.slice(24, 47).reduce((acc, cur) => acc + cur, 0) / 24).toFixed(1) * 1,
        "第3日最高温度": Math.max(...temSeries.slice(48, 71)),
        "第3日最低温度": Math.max(...temSeries.slice(48, 71)),
        "第3日平均温度": (temSeries.slice(48, 71).reduce((acc, cur) => acc + cur, 0) / 24).toFixed(1) * 1,

        "第1日最大风速": Math.max(...winSeries.slice(0, 23)),
        "第1日最小风速": Math.max(...winSeries.slice(0, 23)),
        "第1日平均风速": (winSeries.slice(0, 23).reduce((acc, cur) => acc + cur, 0) / 24).toFixed(1) * 1,
        "第2日最大风速": Math.max(...winSeries.slice(24, 47)),
        "第2日最小风速": Math.max(...winSeries.slice(24, 47)),
        "第2日平均风速": (winSeries.slice(24, 47).reduce((acc, cur) => acc + cur, 0) / 24).toFixed(1) * 1,
        "第3日最大风速": Math.max(...winSeries.slice(48, 71)),
        "第3日最小风速": Math.max(...winSeries.slice(48, 71)),
        "第3日平均风速": (winSeries.slice(48, 71).reduce((acc, cur) => acc + cur, 0) / 24).toFixed(1) * 1,
    }
    staTCConfig.value = {
        toolbar: [
            { name: "图表转换", mode: true },
            { name: "行列转换", mode: true },
            // { name: "编辑表格", mode: false },
            { name: "导出表格" }
        ],
        column: [
            { width: "140px", prop: "time", label: "时间", axis: "x", isHeader: true, disable: true, sortable: true },
            { prop: "pre", label: "降水", unit: "mm/h", axis: "y", sortable: true },
            { prop: "tem", label: "气温", unit: "℃", axis: "y", sortable: true },
            { prop: "win", label: "风速", unit: "m/s", axis: "y", sortable: true },
        ],
    }
    showStaTCDialog.value = true;
}


function StationMappingToGrid(pre, tem, win) {
    let stations = structuredClone(ZiDongZhan);
    let ds = [pre, tem, win];
    let keys = ["pre", "tem", "win"];
    for (let i = 0; i < ds.length; i++) {
        let data = ds[i];
        let key = keys[i];
        if (data) {
            let startLon = data.startLon, startLat = data.startLat, lonStep = data.lonStep, latStep = data.latStep;
            for (let j = 0; j < stations.length; j++) {
                let sta = stations[j];
                let lonIdx = Math.floor((sta.lon - startLon) / lonStep);
                let latIdx = Math.floor((sta.lat - startLat) / latStep);
                sta[key + "Idx"] = [lonIdx, latIdx];
            }
        }
    }
    return stations;
}

// ---------- 同步获取数据 ----------
async function getDataByTypeAndTime(type, time) {
    let res;
    if (GLOBAL_CACHE[type][time]) {
        res = GLOBAL_CACHE[type][time];
    } else {
        await axios({
            method: "post",
            url: interfaces.serverUrl + "data/" + type + "/" + time + ".json",
        }).then(response => {
            res = response.data;
        }).catch(err => { })
    }
    return res;
}






function renderPreStationPopup(station) {

    // let timeDiff = 24;
    // let dataSeries = station.dataSeries;
    // let timeSeries = Array.from({ length: timeDiff }, (v, i) => selectTime.value.add(i, "hour").format("DD日HH时"));
    // if (metElement.value == "降水") {
    //     staTCInfo.value = {
    //         "自动站": station.short_name,
    //         "累积降水": (dataSeries.reduce((acc, cur) => acc + cur, 0)).toFixed(1) * 1,
    //         "平均降水": (dataSeries.reduce((acc, cur) => acc + cur, 0) / dataSeries.length).toFixed(1) * 1,
    //     }
    // } else if (metElement.value == "气温") {
    //     staTCInfo.value = {
    //         "自动站": station.short_name,
    //         "最高温度": Math.max(...dataSeries),
    //         "最低温度": Math.min(...dataSeries),
    //         "平均温度": (dataSeries.reduce((acc, cur) => acc + cur, 0) / dataSeries.length).toFixed(1) * 1,
    //     }
    // } else if (metElement.value == "风") {
    //     staTCInfo.value = {
    //         "自动站": station.short_name,
    //         "最大风速": Math.max(...dataSeries),
    //         "最小风速": Math.min(...dataSeries),
    //     }
    // }
    // staTCData.value = Array.from({ length: 24 }, (v, i) => ({
    //     time: timeSeries[i],
    //     data: dataSeries[i],
    // }));


    // staTCConfig.value = {
    //     title: station.short_name,
    //     toolbar: [
    //         { name: "图表转换", mode: true },
    //         { name: "行列转换", mode: true },
    //         // { name: "编辑表格", mode: false },
    //         { name: "导出表格" }
    //     ],
    //     column: [
    //         { width: "140px", prop: "time", label: "时间", axis: "x", isHeader: true, disable: true, sortable: true },
    //         {
    //             "降水": { prop: "data", label: "降水", unit: "mm/h", axis: "y", sortable: true },
    //             "气温": { prop: "data", label: "气温", unit: "℃", axis: "y", sortable: true },
    //             "风": { prop: "data", label: "风速", unit: "m/s", axis: "y", sortable: true },
    //             // wind: { prop: "wind", label: "风向", unit: "", axis: "y" }
    //         }[metElement.value]
    //     ],
    // };
    // showStaTCDialog.value = true;
}

function getIcon(color, speed, direction) {
    let icon = ""
    if (metElement.value == "降水") {
        icon = `<svg viewBox="0 0 1024 1024">
                    <path fill="${color}"
                        d="M512 1024q94.2336-105.088 306.304-355.1488 102.0928-137.5232 102.0928-285.0048C920.3968 172.032 737.536 1.536 512 1.536 286.464 1.536 103.6032 172.032 103.6032 382.336q0 147.4816 102.1184 285.0048Q417.7664 917.4016 512 1024z"
                    ></path>
                    <circle cx="512" cy="402" r="360" fill="#000" />
                    <path fill="${color}" transform-origin="center" transform="translate(0,-80)  scale(0.53)"
                        d="M861.44128 73.15199998H162.56128A35.58400001 35.58400001 0 0 1 128.00128 36.608 35.58400001 35.58400001 0 0 1 162.56128 0h698.88a35.58400001 35.58400001 0 0 1 34.56 36.608 35.58400001 35.58400001 0 0 1-34.56 36.544zM701.88928 987.392a35.58400001 35.58400001 0 0 0-34.56-36.54400001H356.67328a36.672 36.672 0 0 0 0 73.15200001h310.656a35.58400001 35.58400001 0 0 0 34.56-36.608z m120.704-813.184v476.032a44.22399999 44.22399999 0 0 1-44.48 43.71200002H245.88928a44.22399999 44.22399999 0 0 1-44.48-43.71200002V174.208a44.22399999 44.22399999 0 0 1 44.48-43.71200002h532.224a44.22399999 44.22399999 0 0 1 44.48 43.71200002zM727.04128 553.08800001c0-19.456-11.648-35.2-25.984-35.2H311.04128c-14.336 0-25.984 15.744-25.98400001 35.2s11.648 35.2 25.98400001 35.19999999h390.016c14.336 0 25.984-15.744 25.984-35.19999999z m0-140.86400002c0-19.456-11.648-35.2-25.984-35.19999998H311.04128c-14.336 0-25.984 15.744-25.98400001 35.2s11.648 35.2 25.98400001 35.20000001h390.016c14.336 0 25.984-15.744 25.984-35.20000001z m0-140.86399999c0-19.456-11.648-35.2-25.984-35.20000002H311.04128c-14.336 0-25.984 15.744-25.98400001 35.20000002s11.648 35.2 25.98400001 35.2h390.016c14.336 0 25.984-15.744 25.984-35.2z m-170.68800001 422.59200002H467.64928v281.72799997h88.70399999z"
                    ></path>
                </svg>`;
    } else if (metElement.value == "气温") {
        icon = `<svg viewBox="0 0 1024 1024">
                    <path fill="${color}"
                        d="M512 1024q94.2336-105.088 306.304-355.1488 102.0928-137.5232 102.0928-285.0048C920.3968 172.032 737.536 1.536 512 1.536 286.464 1.536 103.6032 172.032 103.6032 382.336q0 147.4816 102.1184 285.0048Q417.7664 917.4016 512 1024z"
                    ></path>
                    <circle cx="512" cy="402" r="360" fill="#000" />
                    <path fill="${color}" transform-origin="center" transform="translate(0,-115)  scale(0.53)"
                        d="M768 20.48c79.552 0 144.064 63.424 144.064 141.632v404.096a239.296 239.296 0 0 1 89.728 270.144C968.96 936 874.56 1003.52 768 1003.52s-200.96-67.52-233.792-167.168 3.392-208.768 89.728-270.144V162.112C623.936 83.904 688.448 20.48 768 20.48z m-0.064 61.44c-43.648 0-79.04 33.984-79.04 75.904v443.584l-28.992 20.224c-64.704 45.056-91.84 124.928-67.2 197.888 24.64 72.96 95.36 122.432 175.296 122.56 79.936-0.064 150.784-49.536 175.424-122.56s-2.56-152.96-67.328-197.952l-28.992-20.224v-443.52c-0.064-41.92-35.52-75.904-79.168-75.904z m1.344 378.88a32 32 0 0 1 32 32v146.304a133.12 133.12 0 1 1-64.064-0.64L737.28 492.8a32 32 0 0 1 32-32z m-502.208 265.408a30.784 30.784 0 0 1 0 43.456l-72.384 72.384a30.784 30.784 0 0 1-43.456-43.456l72.384-72.384a30.784 30.784 0 0 1 43.456 0zM768 696.32a71.68 71.68 0 1 0-0.064 143.296A71.68 71.68 0 0 0 768 696.32zM491.52 215.04c15.488 0 30.912 1.216 46.016 3.648a30.656 30.656 0 1 1-9.792 60.608 225.28 225.28 0 0 0-90.496 441.152 30.784 30.784 0 0 1-14.72 59.648A286.72 286.72 0 0 1 491.52 215.04z m-348.16 256a30.72 30.72 0 0 1 0 61.44H40.96a30.72 30.72 0 0 1 0-61.44h102.4z m51.264-309.568l72.384 72.384a30.784 30.784 0 0 1-43.456 43.456l-72.32-72.448a30.656 30.656 0 1 1 43.392-43.392zM491.52 20.48a30.72 30.72 0 0 1 30.72 30.72v102.4a30.72 30.72 0 0 1-61.44 0V51.2a30.72 30.72 0 0 1 30.72-30.72z"
                    ></path>
                </svg>`;
    } else if (metElement.value == "风") {
        let vector = {
            "杆": '<rect x="320" y="0" width="64" height="1024" />',
            "角1": '<polygon points="352,32 592,32 352,180" stroke="currentColor" fill="none" stroke-width="64" />',
            "角2": '<polygon points="352,288 592,288 352,436" stroke="currentColor" fill="none" stroke-width="64" />',
            "羽短1": '<rect x="384" y="0" width="160" height="64" />',
            "羽长1": '<rect x="384" y="0" width="320" height="64" />',
            "羽短2": '<rect x="384" y="128" width="160" height="64" />',
            "羽长2": '<rect x="384" y="128" width="320" height="64" />',
            "羽短3": '<rect x="384" y="256" width="160" height="64" />',
            "羽长3": '<rect x="384" y="256" width="320" height="64" />',
            "羽短4": '<rect x="384" y="384" width="160" height="64" />',
            "羽长4": '<rect x="384" y="384" width="320" height="64" />',
            "羽短5": '<rect x="384" y="512" width="160" height="64" />',
            "羽长5": '<rect x="384" y="512" width="320" height="64" />',
            "羽短6": '<rect x="384" y="640" width="160" height="64" />',
            "羽长6": '<rect x="384" y="640" width="320" height="64" />',
            "羽短7": '<rect x="384" y="768" width="160" height="64" />',
            "羽长7": '<rect x="384" y="768" width="320" height="64" />',
            "羽短8": '<rect x="384" y="896" width="160" height="64" />',
            "羽长8": '<rect x="384" y="896" width="320" height="64" />',
        }
        let path = vector["杆"];
        if (speed == 0) path = vector["杆"];
        else if (speed <= 2) path += vector["羽短1"];
        else if (speed <= 4) path += vector["羽长1"];
        else if (speed <= 6) path += vector["羽长1"] + vector["羽短2"];
        else if (speed <= 8) path += vector["羽长1"] + vector["羽长2"];
        else if (speed <= 10) path += vector["羽长1"] + vector["羽长2"] + vector["羽短3"];
        else if (speed <= 12) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"];
        else if (speed <= 14) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"] + vector["羽短4"];
        else if (speed <= 16) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"] + vector["羽长4"];
        else if (speed <= 18) path += vector["羽长1"] + vector["羽长2"] + vector["羽长3"] + vector["羽长4"] + vector["羽短5"];
        else if (speed <= 20) path += vector["角1"];
        else if (speed <= 22) path += vector["角1"] + vector["羽短3"];
        else if (speed <= 24) path += vector["角1"] + vector["羽长3"];
        else if (speed <= 26) path += vector["角1"] + vector["羽长3"] + vector["羽短4"];
        else if (speed <= 28) path += vector["角1"] + vector["羽长3"] + vector["羽长4"];
        else if (speed <= 30) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽短5"];
        else if (speed <= 32) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"];
        else if (speed <= 34) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"] + vector["羽短6"];
        else if (speed <= 36) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"] + vector["羽长6"];
        else if (speed <= 38) path += vector["角1"] + vector["羽长3"] + vector["羽长4"] + vector["羽长5"] + vector["羽长6"] + vector["羽短7"];
        else if (speed <= 40) path += vector["角1"] + vector["角2"];
        else if (speed <= 42) path += vector["角1"] + vector["角2"] + vector["羽短5"];
        else if (speed <= 44) path += vector["角1"] + vector["角2"] + vector["羽长5"];
        else if (speed <= 46) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽短6"];
        else if (speed <= 48) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"];
        else if (speed <= 50) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽短7"];
        else if (speed <= 52) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽长7"];
        else if (speed <= 54) path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽长7"] + vector["羽短8"];
        else path += vector["角1"] + vector["角2"] + vector["羽长5"] + vector["羽长6"] + vector["羽长7"] + vector["羽长8"];
        icon = `<svg viewBox="0 0 1024 1024">
                    <path fill="${color}"
                        d="M512 1024q94.2336-105.088 306.304-355.1488 102.0928-137.5232 102.0928-285.0048C920.3968 172.032 737.536 1.536 512 1.536 286.464 1.536 103.6032 172.032 103.6032 382.336q0 147.4816 102.1184 285.0048Q417.7664 917.4016 512 1024z"
                    ></path>
                    <circle cx="512" cy="402" r="360" fill="#000" />
                    <g fill="${color}" color="${color}" transform-origin="center" transform="translate(0,-115) rotate(${direction}) scale(0.53)">${path}</g>
                </svg>`;
    }
    return icon;
}

function removeLayerById(id) {
    if (GLOBAL_CACHE[id]) {
        GLOBAL_CACHE[id].remove();
        GLOBAL_CACHE[id] = null;
    }
}
</script>
