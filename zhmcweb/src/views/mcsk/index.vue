<!-- eslint-disable vue/multi-word-component-names -->
<style scoped lang="scss">


// 企业级控制面板
:root{
  --ds-bg: #0E1A2B;
  --ds-card: rgba(15, 24, 44, .78);
  --ds-border: rgba(139, 233, 255, .18);
  --ds-glow: 0 0 22px rgba(43,217,254,.25);
  --ds-txt: rgba(255,255,255,.86);
  --ds-txt-2: rgba(255,255,255,.62);
  --ds-line: rgba(255,255,255,.10);
  --ds-pri: #2BD9FE;
  --ds-pri-2: #00FFA3;
  --ds-warn: #FFAA33;
  --ds-bd:rgba(139,233,255,.18);
  --ds-acc:#00FFA3;



  --border-radius-sm: 10px;
  --border-radius-lg: 14px;
  --border-radius-xl: 16px;

  --shadow-md: 0 10px 24px rgba(0,0,0,.35);
  --shadow-lg: 0 16px 40px rgba(0,0,0,.45);
  --transition-fast: .18s ease;
  --transition-normal: .28s cubic-bezier(.22,.8,.2,1);
}

/* ===== 数字可视化大屏风格控制面板 ===== */
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
  border: 1px solid rgba(224, 96, 11, 0.18);
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


/* ===== 响应式（可选） ===== */
@media (max-width: 1440px){
  .tl-panel{ width: 320px; }
}
@media (max-width: 1200px){
  .tl-panel{ position: fixed; left: 12px; top: 12px; width: 300px; }
}

// 企业级图例面板
.legend-panel {
  position: absolute;
  bottom: 40px; /* 比原来增加20px，向下移动 */
  right: 40px;   /* 比原来增加20px，向左移动 */
  width: 720px;
  z-index: 999;
  border-radius: 16px;
  overflow: hidden;

  background: var(--ds-card);
  backdrop-filter: blur(20px);
  border: 1px solid rgba(255, 255, 255, 0.2);
  box-shadow: var(--shadow-lg);
}
// 企业级时间栏面板
.timeBar-panel {
  position: absolute;
  bottom: 10px;
  left: 20px; /* 从左侧偏移20px，数值越小越靠左 */
  transform: none; /* 移除水平居中的平移效果 */
  width: calc(100% - 40px);
  max-width: 1300px;
  z-index: 9999;
  border-radius: var(--border-radius-lg);
  overflow: hidden;

  background: var(--ds-card);
  backdrop-filter: blur(20px);
  border: 1px solid rgba(255, 255, 255, 0.2);
  box-shadow: var(--shadow-lg);
}

// 企业级站点表格图表
/* 数字可视化大屏：站点统计大卡（实体深色，非模糊，霓虹描边） */
.station-table-chart {
  position: absolute;
  left: 20px;
  bottom: 10px;
  right: 20px;
  z-index: 9999;
  border-radius: 16px;
  overflow: hidden;

  /* 深色实体背景 + 能量光晕（看得见） */
  background:
    radial-gradient(1200px 700px at 15% -30%, #25497a, transparent 60%),
    radial-gradient(1000px 640px at 90% 130%, #25497a, transparent 60%),
    #0E1A2B;

  /* 关键：去掉模糊透明 */
  backdrop-filter: none !important;

  /* 霓虹描边 + 轻内外发光，确保对比度 */
  border: 1px solid rgba(139,233,255,.18);
  box-shadow:
    inset 0 0 0 1px rgba(255,255,255,.02),
    0 16px 40px rgba(0,0,0,.46),
    0 0 24px rgba(43,217,254,.14);

  color: rgba(255,255,255,.86);
  font-size: 14px;

  .close {
    width: 48px;
    height: 48px;
    display: flex; align-items: center; justify-content: center;
    position: absolute; top: 8px; right: 8px;
    cursor: pointer; border-radius: 50%;
    background: rgba(255, 91, 91, 0.10);
    border: 1px solid rgba(255, 91, 91, 0.28);
    transition: .16s ease;
    z-index: 10;

    &:hover { background: rgba(255, 91, 91, 0.18); transform: scale(1.05); }

    svg {
      width: 24px; height: 24px;
      color: #FF5B5B;
      transition: .16s ease;
      &:hover { transform: rotate(90deg); }
    }
  }

  .header {
    width: 100%; height: 52px; padding: 0 20px;
    display: flex; align-items: center;
    font-size: 16px; font-weight: 600;
    color: rgba(255,255,255,.92);

    /* 顶部条：浅亮度渐变，非透明玻璃 */
    background: linear-gradient(180deg, rgba(255,255,255,.05), rgba(255,255,255,.02));
    border-bottom: 1px solid rgba(255,255,255,.10);
    position: relative;

    /* 左侧能量竖条（蓝→青渐变+发光） */
    &::before {
      content: '';
      position: absolute; left: 0; top: 0; bottom: 0; width: 4px;
      background: linear-gradient(180deg, #2BD9FE, #00FFA3);
      box-shadow: 0 0 20px rgba(43,217,254,.25);
    }

    span {
      font-size: 13px; color: rgba(255,255,255,.62); margin-left: 12px;
    }
  }

  .info-box .main {
    width: 100%; min-height: 60px; padding: 16px 20px;
    display: flex; align-items: center; justify-content: center;
    gap: 32px; flex-wrap: wrap;

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

  .tc-box {
    width: 100%; height: 320px; padding: 0 20px 20px;
  }
}

.i-icon{
  width:30px;height:30px;display:inline-block;background:currentColor;
  -webkit-mask-image: var(--icon);
  mask-image: var(--icon);
  -webkit-mask-repeat: no-repeat;
  mask-repeat: no-repeat;
  -webkit-mask-position: center;
  mask-position: center;
  -webkit-mask-size: contain;
  mask-size: contain;
}
.img-fallback{ width:18px;height:18px;display:none; }

/* 兜底：万一 CSS.supports 不可用 */
@supports not (-webkit-mask: url("")){
  .i-icon{ display:none; }
  .img-fallback{ display:block; }
}
</style>

<style>
:root{
  --ds-bg:#0E1A2B;
  --ds-card: rgba(15,24,44,.82);
  --ds-line: rgba(255,255,255,.10);
  --ds-bd: rgba(139,233,255,.18);
  --ds-txt: rgba(255,255,255,.86);
  --ds-txt-2: rgba(255,255,255,.62);
  --ds-pri:#2BD9FE;
  --ds-acc:#00FFA3;
}

/* 1) 对话框里的 TableChart（你的“全部站点”统计） */
.my-dialog :deep(.el-table){
  background: var(--ds-card);
  color: var(--ds-txt);
  border: 1px solid var(--ds-bd);
  box-shadow:
    inset 0 0 0 1px rgba(255,255,255,.02),
    0 12px 32px rgba(0,0,0,.45);
    }

  .el-table__inner-wrapper::before {
    background: transparent;
  }

  th.el-table__cell{
    background: linear-gradient(180deg, rgba(255,255,255,.06), rgba(255,255,255,.02));
    color: rgba(255,255,255,.92);
    font-weight: 700;
    border-bottom: 1px solid var(--ds-line);
  }

  td.el-table__cell{
    background: transparent;
    color: var(--ds-txt);
    border-bottom: 1px solid rgba(255,255,255,.06);
  }

  .el-table__row:hover>td{ /* 悬浮高亮 */
    background: linear-gradient(180deg, rgba(43,217,254,.08), rgba(0,255,163,.06));
  }

  .el-table__row:nth-child(odd)>td{ /* 斑马纹 */
    background: rgba(255,255,255,.02);
  }

  .ascending .sort-caret.ascending,
  .descending .sort-caret.descending{
    border-bottom-color: var(--ds-pri);
    border-top-color: var(--ds-pri);
  }

  /* 滚动条适配深色 */
  .el-scrollbar__bar.is-vertical .el-scrollbar__thumb{ background: rgba(255,255,255,.18); }


/* 2) 底部 .station-table-chart 容器里的表格（单站“图表/表格”切换后的表格） */
.station-table-chart .tc-box :deep(.el-table){
  background: var(--ds-card);
  color: var(--ds-txt);
  border: 1px solid var(--ds-bd);
  }

  th.el-table__cell{
    background: linear-gradient(180deg, rgba(255,255,255,.06), rgba(255,255,255,.02));
    color: rgba(255,255,255,.92);
    border-bottom: 1px solid var(--ds-line);
  }
  td.el-table__cell{
    border-bottom: 1px solid rgba(255,255,255,.06);
  }
  .el-table__row:hover>td{
    background: linear-gradient(180deg, rgba(43,217,254,.08), rgba(0,255,163,.06));
  }


/* 表格里的按钮/标签可选美化 */
:deep(.el-tag){
  background: rgba(255,255,255,.08);
  color: var(--ds-txt); border-color: var(--ds-line);
}
:deep(.el-button--primary){
  background: linear-gradient(180deg, var(--ds-pri), #1AA9E9);
  border: none; color: #0A1220;
  box-shadow: 0 0 16px rgba(43,217,254,.28);
}

/* 动画继续沿用你的入场动效 */
.station-table-chart{ animation: fadeInUp .6s cubic-bezier(.4,0,.2,1); }
</style>

<template>
  <svg aria-hidden="true" style="position: absolute; width: 0; height: 0; overflow: hidden">
    <symbol id="icon-close" viewBox="0 0 1000 1000" pointer-events="all">
      <circle cx="500" cy="500" r="450" fill="none" stroke="currentColor" stroke-width="100" stroke-dasharray="200% 40% 60 30%" stroke-linecap="round" />
      <line x1="320" y1="320" x2="680" y2="680" stroke="currentColor" stroke-width="80" stroke-linecap="round" />
      <line x1="320" y1="680" x2="680" y2="310" stroke="currentColor" stroke-width="80" stroke-linecap="round" />
    </symbol>
  </svg>

  <div id="map-container"></div>
  <MapInfoBar v-if="refmap" :map="refmap" />

  <div class="timeBar-panel">
    <TimeBar class="timeBar-box"
             v-model="selectTime"
             :="timeBarConfig"
             @change-bar="timeBarConfigChange"
             @point-click="timeBarPoint" />
  </div>

  <div class="tl-panel">
  <div class="fun-box">
    <div class="header">气象要素</div>
    <div class="main">
  <span v-for="item in ['降水','气温','风','能见度','日照']"
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
      <div class="header-right" v-if="showStationLayer">
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

  <span v-if="metElement == '风'"
        :class="{ active: showWinFieldLayer }"
        @click="handleWinFieldLayer"
        :title="'风场'">
    <i class="i-icon" :style="maskStyle(iconOf('风场'))" v-show="supportsMask"></i>
    <img class="img-fallback" :src="iconOf('风场')" alt="风场" v-show="!supportsMask" />
    <!-- <span class="sr-only">风场</span> -->
  </span>
</div>
  </div>
  <div class="fun-box">
    <div class="header">信息统计</div>
    <div class="main">
  <span :class="{ active: showStatisticalLayer }"
        @click="handleStatisticalLayer"
        :title="'统计表'">
    <i class="i-icon" :style="maskStyle(iconOf('统计表'))" v-show="supportsMask"></i>
    <img class="img-fallback" :src="iconOf('统计表')" alt="统计表" v-show="!supportsMask" />
  </span>
</div>
  </div>
</div>


  <div class="station-table-chart" v-if="showStaTC">
    <div class="close" @click="showStaTC = false">
      <svg>
        <use xlink:href="#icon-close"></use>
      </svg>
    </div>
    <div class="info-box">
      <div class="header">气象要素信息统计
        <span style="font-size: 14px;">
          （ {{ timeBarConfig.bar == "时"
              ? selectTime.add(-23, "hour").format("YYYY-MM-DD HH:00:00") + " 至 " + selectTime.format("YYYY-MM-DD HH:00:00")
              : selectTime.add(-6, "day").format("YYYY-MM-DD") + " 至 " + selectTime.format("YYYY-MM-DD") }} ）</span>
      </div>
      <div class="main">
        <div>自动站：{{ staTCInfo.自动站 }}</div>
        <div>室内温度：{{ staTCInfo.平均温度 *0.8}} ℃</div>
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
      <TableChart :="staTCConfig" v-model="staTCData" />
    </div>
  </div>

  <el-dialog class="my-dialog"
             v-model="showStatisticalLayer"
             width="1800"
             z-index="99999"
             align-center
             destroy-on-close
             close-icon="">
    <el-segmented class="my-segmented"
                  v-model="segmentedMetElement"
                  :options="['降水', '气温', '风']"
                  @change="getStationsDataByTimeRange" />
    <TableChart style="height: 600px;"
                v-model="allStaTCData"
                :="allStaTCConfig" />
  </el-dialog>

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

import Legend from "@/components/my-legend/Legend.vue";
import TimeBar from "@/components/my-time-bar/TimeBar.vue";
import TableChart from "@/components/my-table-chart/index.vue";
import MapInfoBar from "@/components/my-map-info-bar/MapInfoBar.vue";

import { idwToGrid } from "@/components/utils.js";
import interfaces from "@/components/interfaces.js";
import ZiDongZhan from "@/assets/GeographicData/自动站.json"
import ShiBianJie from "@/assets/GeographicData/吴忠市级边界.json";
import FengChang from "@/assets/GeographicData/风场.json";
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
    showStaTC = ref(false),             // 是否显示单个站点的时序图
    staTCConfig = ref({}),              // 单个站点时序图配置
    staTCData = ref([]),                // 单个站点时序图数据
    staTCInfo = ref({}),                // 单个站点气象数据
    segmentedMetElement = ref("降水"),  // 统计表气象要素
    showAllStaTC = ref(true),           // 是否显示所有站点统计表
    allStaTCConfig = ref({}),           // 所有站点统计表配置
    allStaTCData = ref([]),             // 所有站点统计表数据
    GLOBAL_CACHE = {};

let selectTime = ref(dayjs().set("hour", 8).set("minute", 0).set("second", 0));// 时间条上的时间
selectTime.value = dayjs("2025-05-10 08:00:00")
let timeBarPointChecked = ref(23),      // 时间条上被选中的时间点，起始为 0
    timeBarConfig = ref({               // 时间条配置
        bar: "时",
        dateFormat: "YYYY-MM-DD HH",
        unit: "hour",
        difference: -23,
        points: 24,
        step: 1,
        pointChecked: 24,
    }),
    timeBarConfigChange = (type) => {
        timeBarConfig.value = type == "时" ? {
            bar: "时",
            dateFormat: "YYYY-MM-DD HH",
            unit: "hour",
            difference: -23,
            points: 24,
            step: 1,
            pointChecked: 24,
        } : {
            bar: "日",
            dateFormat: "YYYY-MM-DD",
            unit: "day",
            difference: -6,
            points: 7,
            step: 1,
            pointChecked: 7,
        };
    };
// 时间条上的时间点的点击事件
function timeBarPoint(obj) {
    if (showStationLayer.value || showGridLayer.value) {
        timeBarPointChecked.value = obj.point - 1;
        if (metElement.value == "降水") getPreStationData();
        if (metElement.value == "气温") getTemStationData();
        if (metElement.value == "风") getWinStationData();
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
    return value == "--"
        ? { id: -1, color: "#000", threshold: "--", name: "异常值" }
        : value < preThreshold.value[1].threshold ? preThreshold.value[0] :
            value < preThreshold.value[2].threshold ? preThreshold.value[1] :
                value < preThreshold.value[3].threshold ? preThreshold.value[2] :
                    value < preThreshold.value[4].threshold ? preThreshold.value[3] :
                        value < preThreshold.value[5].threshold ? preThreshold.value[4] :
                            value < preThreshold.value[6].threshold ? preThreshold.value[5] : preThreshold.value[6];
}
function getWinThreshold(value) {
    return value == "--"
        ? { id: -1, color: "#000", threshold: "--", name: "异常值" }
        : value < winThreshold.value[1].threshold ? winThreshold.value[0] :
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
    return value == "--"
        ? { id: -1, color: "#000", threshold: "--", name: "异常值" }
        : value < temThreshold.value[1].threshold ? temThreshold.value[0] :
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
function handleShowStationNameValue(checked) {
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
            if (data == "降水") getPreStationData();
            if (data == "气温") getTemStationData();
            if (data == "风") getWinStationData();
        }
    }
}
// ---------- 展示方式 ----------
function handleWinFieldLayer() {
    showWinFieldLayer.value = !showWinFieldLayer.value;
    if (showWinFieldLayer.value) {
        getWinStationData();
    } else {
        removeLayerById("current-winfield-layer");
    }
}
function handleStationLayer() {
    showStationLayer.value = !showStationLayer.value;
    if (showStationLayer.value) {
        if (metElement.value == "降水") getPreStationData();
        if (metElement.value == "气温") getTemStationData();
        if (metElement.value == "风") getWinStationData();
    } else {
        removeLayerById("current-station-layer");
    }
}
function handleGridLayer() {
    showGridLayer.value = !showGridLayer.value;
    if (showGridLayer.value) {
        if (metElement.value == "降水") getPreStationData();
        if (metElement.value == "气温") getTemStationData();
        if (metElement.value == "风") getWinStationData();
    } else {
        removeLayerById("current-grid-layer");
    }
}
// ---------- 统计表 ----------
function handleStatisticalLayer() {
    showStatisticalLayer.value = !showStatisticalLayer.value;
    if (showStatisticalLayer.value) {
        getStationsDataByTimeRange();
    }
}

// ---------- 获取所有站点、1个时次、1个气象要素数据的数据 ----------
// getPreStationData()
function getPreStationData() {
    let params = {};
    let preKey = "", temKey = "", winsKey = "", windKey = "";
    if (timeBarConfig.value.bar == "时") {
        preKey = "PRE_1h"; temKey = "TEM"; winsKey = "WIN_S"; windKey = "WIN_D";
        params = {
            // elements: "Station_Id_C,Datetime," + preKey,
            table: "t_tq_hour",
            type: "=",
            field: "Datetime",
            value: selectTime.value.add(timeBarPointChecked.value - 23, "hour").format("YYYY-MM-DD HH:00:00"),
        };
    } else {
        preKey = "PRE"; temKey = "TEM"; winsKey = "WIN_S"; windKey = "WIN_D";
        params = {
            // elements: "Station_Id_C,Datetime," + preKey,
            table: "t_tq_day",
            type: "=",
            field: "Datetime",
            value: selectTime.value.add(timeBarPointChecked.value - 6, "day").format("YYYY-MM-DD 00:00:00"),
        };
    }
    axios({
        method: "get",
        url: interfaces.serviceUrl + "db/select",
        params: params
    }).then(response => {
        let data = response.data.data;
        if (data.length == 0) {
            removeLayerById("current-station-layer");
            ElMessage({
                showClose: true,
                message: "无数据",
                offset: document.documentElement.clientHeight / 2
            })
        } else {
            let stations = structuredClone(ZiDongZhan);
            // 数据绑定到设备
            for (let sta of stations) {
                let item = data.find(v => sta.sid == v.Station_Id_C);
                sta.val = item?.[preKey] < 999 ? item[preKey] : "--";
                sta.color = getPreThreshold(sta.val).color;
                sta.warn = [];
                let preVal = item?.[preKey] < 999 ? item[preKey] : "--",
                    temVal = item?.[temKey] < 999 ? item[temKey] : "--",
                    winVal = item?.[winsKey] < 999 ? item[winsKey] : "--";
                let preWarn = preVal > 35 ? "暴雨红色" : preVal > 30 ? "暴雨橙色" : preVal > 25 ? "暴雨黄色" : preVal > 15 ? "暴雨蓝色" : null,
                    htemWarn = temVal > 40 ? "高温红色" : temVal > 37 ? "高温橙色" : temVal > 35 ? "高温黄色" : null,
                    winWarn = winVal > 25 ? "大风红色" : winVal > 20 ? "大风橙色" : winVal > 15 ? "大风黄色" : winVal > 10 ? "大风蓝色" : null;
                if (preWarn) { sta.warn.push(preWarn); }
                if (htemWarn) { sta.warn.push(htemWarn); }
                if (winWarn) { sta.warn.push(winWarn); }
            }
            GLOBAL_CACHE.curData = stations;
            if (showStationLayer.value) {
                GLOBAL_CACHE.curStationData = stations;
                renderStationLayer(stations);
            }
            if (showGridLayer.value) {
                // 插值
                let idwPoints = stations.filter(v => v.val != "--");
                let idwGrid = idwToGrid(idwPoints, { startLon: 105.55, startLat: 36.55, lonStep: 0.005, latStep: 0.005, lonCount: 425, latCount: 345 }, 50, Infinity, { valField: "val" });
                GLOBAL_CACHE.curGridData = idwGrid;
                renderGridLayer(idwGrid);
            }
        }
    })
}
function getTemStationData() {
    let params = {};
    let preKey = "", temKey = "", winsKey = "", windKey = "";
    if (timeBarConfig.value.bar == "时") {
        preKey = "PRE_1h"; temKey = "TEM"; winsKey = "WIN_S"; windKey = "WIN_D";
        params = {
            // elements: "Station_Id_C,Datetime," + temKey,
            table: "t_tq_hour",
            type: "=",
            field: "Datetime",
            value: selectTime.value.add(timeBarPointChecked.value - 23, "hour").format("YYYY-MM-DD HH:00:00"),
        };

    } else {
        preKey = "PRE"; temKey = "TEM"; winsKey = "WIN_S"; windKey = "WIN_D";
        params = {
            // elements: "Station_Id_C,Datetime," + temKey,
            table: "t_tq_day",
            type: "=",
            field: "Datetime",
            value: selectTime.value.add(timeBarPointChecked.value - 6, "day").format("YYYY-MM-DD 00:00:00"),
        };
    }
    axios({
        method: "get",
        url: interfaces.serviceUrl + "db/select",
        params: params
    }).then(response => {
        let data = response.data.data;
        if (data.length == 0) {
            removeLayerById("current-station-layer");
            ElMessage({
                showClose: true,
                message: "无数据",
                offset: document.documentElement.clientHeight / 2
            })
        } else {
            let stations = structuredClone(ZiDongZhan);
            // 数据绑定到设备
            for (let sta of stations) {
                let item = data.find(v => sta.sid == v.Station_Id_C);
                sta.val = item?.[temKey] < 999 ? item[temKey] : "--";
                sta.color = getTemThreshold(sta.val).color;
                sta.warn = [];
                let preVal = item?.[preKey] < 999 ? item[preKey] : "--",
                    temVal = item?.[temKey] < 999 ? item[temKey] : "--",
                    winVal = item?.[winsKey] < 999 ? item[winsKey] : "--";
                let preWarn = preVal > 35 ? "暴雨红色" : preVal > 30 ? "暴雨橙色" : preVal > 25 ? "暴雨黄色" : preVal > 15 ? "暴雨蓝色" : null,
                    htemWarn = temVal > 40 ? "高温红色" : temVal > 37 ? "高温橙色" : temVal > 35 ? "高温黄色" : null,
                    winWarn = winVal > 25 ? "大风红色" : winVal > 20 ? "大风橙色" : winVal > 15 ? "大风黄色" : winVal > 10 ? "大风蓝色" : null;
                if (preWarn) { sta.warn.push(preWarn); }
                if (htemWarn) { sta.warn.push(htemWarn); }
                if (winWarn) { sta.warn.push(winWarn); }
            }
            GLOBAL_CACHE.curData = stations;
            if (showStationLayer.value) {
                GLOBAL_CACHE.curStationData = stations;
                renderStationLayer(stations);
            }
            if (showGridLayer.value) {
                // 插值
                let idwPoints = stations.filter(v => v.val != "--");
                let idwGrid = idwToGrid(idwPoints, { startLon: 105.55, startLat: 36.55, lonStep: 0.005, latStep: 0.005, lonCount: 425, latCount: 345 }, 50, Infinity, { valField: "val" });
                GLOBAL_CACHE.curGridData = idwGrid;
                renderGridLayer(idwGrid);
            }
        }
    })
}
function getWinStationData() {
    let params = {};
    let preKey = "", temKey = "", winsKey = "", windKey = "";
    if (timeBarConfig.value.bar == "时") {
        preKey = "PRE_1h"; temKey = "TEM"; winsKey = "WIN_S"; windKey = "WIN_D";
        params = {
            // elements: "Station_Id_C,Datetime," + winsKey + "," + windKey,
            table: "t_tq_hour",
            type: "=",
            field: "Datetime",
            value: selectTime.value.add(timeBarPointChecked.value - 23, "hour").format("YYYY-MM-DD HH:00:00"),
        };
    } else {
        preKey = "PRE"; temKey = "TEM"; winsKey = "WIN_S"; windKey = "WIN_D";
        params = {
            // elements: "Station_Id_C,Datetime," + winsKey + "," + windKey,
            table: "t_tq_day",
            type: "=",
            field: "Datetime",
            value: selectTime.value.add(timeBarPointChecked.value - 6, "day").format("YYYY-MM-DD 00:00:00"),
        };
    }
    axios({
        method: "get",
        url: interfaces.serviceUrl + "db/select",
        params: params
    }).then(response => {
        let data = response.data.data;
        if (data.length == 0) {
            removeLayerById("current-station-layer");
            ElMessage({
                showClose: true,
                message: "无数据",
                offset: document.documentElement.clientHeight / 2
            })
        } else {
            let stations = structuredClone(ZiDongZhan);
            // 数据绑定到设备
            for (let sta of stations) {
                let item = data.find(v => sta.sid == v.Station_Id_C);
                sta.val = item?.[winsKey] ?? "--";
                sta.d = item?.[windKey] ?? 0;
                sta.v = sta.val == "--" ? sta.val * Math.cos(Math.PI / 180 * sta.d) : 0;
                sta.u = sta.val == "--" ? sta.val * Math.sin(Math.PI / 180 * sta.d) : 0;
                sta.color = getWinThreshold(sta.val).color;
                sta.warn = [];
                let preVal = item?.[preKey] < 999 ? item[preKey] : "--",
                    temVal = item?.[temKey] < 999 ? item[temKey] : "--",
                    winVal = item?.[winsKey] < 999 ? item[winsKey] : "--";
                let preWarn = preVal > 35 ? "暴雨红色" : preVal > 30 ? "暴雨橙色" : preVal > 25 ? "暴雨黄色" : preVal > 15 ? "暴雨蓝色" : null,
                    htemWarn = temVal > 40 ? "高温红色" : temVal > 37 ? "高温橙色" : temVal > 35 ? "高温黄色" : null,
                    winWarn = winVal > 25 ? "大风红色" : winVal > 20 ? "大风橙色" : winVal > 15 ? "大风黄色" : winVal > 10 ? "大风蓝色" : null;
                if (preWarn) { sta.warn.push(preWarn); }
                if (htemWarn) { sta.warn.push(htemWarn); }
                if (winWarn) { sta.warn.push(winWarn); }
            }
            GLOBAL_CACHE.curData = stations;
            if (showStationLayer.value) {
                GLOBAL_CACHE.curStationData = stations;
                renderStationLayer(stations);
            }
            if (showGridLayer.value || showWinFieldLayer.value) {
                // 插值
                let idwPoints = stations.filter(v => v.val != "--");
                let idwGrid = idwToGrid(idwPoints, { startLon: 105.55, startLat: 36.55, lonStep: 0.005, latStep: 0.005, lonCount: 425, latCount: 345 }, 50, Infinity, { valField: "val" });
                if (showGridLayer.value) {
                    GLOBAL_CACHE.curGridData = idwGrid;
                    renderGridLayer(idwGrid);
                }
                if (showWinFieldLayer.value) {
                    let wf = [];
                    wf = FengChang;
                    GLOBAL_CACHE.curWinFieldData = wf;
                    renderWinFieldLayer(wf);
                }
            }
        }
    })
}
// ---------- 获取1个站点、多个气象要素数据，渲染弹窗 ----------
function getStationDataByIdAndTimeRange(station) {
    let params = {};
    let preKey = "", temKey = "", winsKey = "", windKey = "";
    let timeSeries = [];
    if (timeBarConfig.value.bar == "时") {
        timeSeries = Array.from({ length: 24 }, (v, i) => selectTime.value.add(i - 23, "hour").format("YYYY-MM-DD HH:00:00"));
        preKey = "PRE_1h"; temKey = "TEM"; winsKey = "WIN_S"; windKey = "WIN_D";
        params = {
            elements: "Station_Id_C,Datetime," + preKey + "," + temKey + "," + winsKey + "," + windKey,
            table: "t_tq_hour",
            collection: [
                { type: "=", field: "Station_Id_C", value: station.sid },
                { type: "between", field: "Datetime", leftValue: timeSeries[0], rightValue: timeSeries[timeSeries.length - 1] },
            ]
        };
    } else {
        timeSeries = Array.from({ length: 7 }, (v, i) => selectTime.value.add(i - 6, "day").format("YYYY-MM-DD 00:00:00"));
        preKey = "PRE"; temKey = "TEM"; winsKey = "WIN_S"; windKey = "WIN_D";
        params = {
            elements: "Station_Id_C,Datetime," + preKey + "," + temKey + "," + winsKey + "," + windKey,
            table: "t_tq_day",
            collection: [
                { type: "=", field: "Station_Id_C", value: station.sid },
                { type: "between", field: "Datetime", leftValue: timeSeries[0], rightValue: timeSeries[timeSeries.length - 1] },
            ]
        };
    }
    axios({
        method: "post",
        url: interfaces.serviceUrl + "db/select",
        data: params
    }).then(responses => {
        let data = responses.data.data;
        let preSeries = Array(timeSeries.length).fill(null);
        let temSeries = Array(timeSeries.length).fill(null);
        let winsSeries = Array(timeSeries.length).fill(null);
        let windSeries = Array(timeSeries.length).fill(null);
        // 根据 timeSeries 的顺序，填充preSeries...
        for (let i = 0; i < data.length; i++) {
            let item = data[i];
            let index = timeSeries.indexOf(item?.Datetime);
            if (index != -1) {
                if (item[preKey] < 999) { preSeries[index] = item[preKey]; }
                if (item[temKey] < 999) { temSeries[index] = item[temKey]; }
                if (item[winsKey] < 999) { winsSeries[index] = item[winsKey]; }
                if (item[windKey] < 999) { windSeries[index] = item[windKey]; }
            }
        }
        staTCInfo.value = {
            "自动站": station.short_name,
            "累积降水": (preSeries.reduce((acc, cur) => acc + cur, 0)).toFixed(1) * 1,
            "平均降水": (preSeries.reduce((acc, cur) => acc + cur, 0) / preSeries.length).toFixed(1) * 1,
            "最高温度": Math.max(...temSeries),
            "最低温度": Math.min(...temSeries),
            "平均温度": (temSeries.reduce((acc, cur) => acc + cur, 0) / temSeries.length).toFixed(1) * 1,
            "最大风速": Math.max(...winsSeries),
            "最小风速": Math.min(...winsSeries),
        }
        staTCData.value = Array.from({ length: timeSeries.length }, (v, i) => ({
            time: timeSeries[i],
            pre: preSeries[i],
            tem: temSeries[i],
            wins: winsSeries[i],
            wind: windSeries[i],
        }));
        staTCConfig.value = {
            title: station.short_name,
            toolbar: [
                { name: "图表转换", mode: true },
                { name: "行列转换", mode: true },
                // { name: "编辑表格", mode: false },
                { name: "导出表格" }
            ],
            column: [
                { width: "140px", prop: "time", label: "时间", axis: "x", isHeader: true, disable: true, format: (v) => dayjs(v).format("MM-DD HH:mm"), sortable: true },
                { prop: "pre", label: "降水", unit: "mm/h", axis: "y", sortable: true },
                { prop: "tem", label: "气温", unit: "℃", axis: "y", sortable: true },
                { prop: "wins", label: "风速", unit: "m/s", axis: "y", sortable: true },
                // { prop: "wind", label: "风向", unit: "", axis: "y" },
            ],
        };
        showStaTC.value = true;
    }).catch((response) => {
        // if (response.status == 404) {
        //     GLOBAL_CACHE.curData = null;
        //     removeLayerById("ybyj-current-layer");
        //     ElMessage({
        //         showClose: true,
        //         message: "文件不存在",
        //         type: "error",
        //         offset: document.documentElement.clientHeight / 2
        //     })
        // }
    });
}
// ---------- 获取所有站点的1个气象要素数据，渲染弹窗 ----------
function getStationsDataByTimeRange() {
    let params = {};
    let preKey = "", temKey = "", winsKey = "", windKey = "";
    let timeSeries = [];
    if (timeBarConfig.value.bar == "时") {
        timeSeries = Array.from({ length: 24 }, (v, i) => selectTime.value.add(i - 23, "hour").format("YYYY-MM-DD HH:00:00"));
        preKey = "PRE_1h"; temKey = "TEM"; winsKey = "WIN_S"; windKey = "WIN_D";
        params = {
            elements: "Station_Id_C,Datetime," + preKey + "," + temKey + "," + winsKey + "," + windKey,
            table: "t_tq_hour",
            collection: [
                { type: "between", field: "Datetime", leftValue: timeSeries[0], rightValue: timeSeries[timeSeries.length - 1] },
            ]
        };
    } else {
        timeSeries = Array.from({ length: 7 }, (v, i) => selectTime.value.add(i - 6, "day").format("YYYY-MM-DD 00:00:00"));
        preKey = "PRE"; temKey = "TEM"; winsKey = "WIN_S"; windKey = "WIN_D";
        params = {
            elements: "Station_Id_C,Datetime," + preKey + "," + temKey + "," + winsKey + "," + windKey,
            table: "t_tq_day",
            collection: [
                { type: "between", field: "Datetime", leftValue: timeSeries[0], rightValue: timeSeries[timeSeries.length - 1] },
            ]
        };
    }
    axios({
        method: "post",
        url: interfaces.serviceUrl + "db/select",
        data: params
    }).then(responses => {
        let data = responses.data.data;
        let k = segmentedMetElement.value == "降水" ? preKey : segmentedMetElement.value == "气温" ? temKey : winsKey;
        let stations = structuredClone(ZiDongZhan);
        // 数据绑定到设备
        for (let sta of stations) {
            let dataSeries = Array(timeSeries.length).fill(null);
            for (let item of data) {
                if (sta.sid == item.Station_Id_C) {
                    // 使用 index 防止时间序列缺失
                    let index = timeSeries.indexOf(item.Datetime);
                    if (index != -1 && item[k] < 999) {
                        dataSeries[index] = item[k];
                    }
                }
            }
            sta.dataSeries = dataSeries;
        }
        allStaTCData.value = Array.from({ length: timeSeries.length }, (v, i) => {
            let obj = { time: timeSeries[i] };
            stations.forEach(sta => { obj[sta.short_name] = sta.dataSeries[i]; });
            return obj;
        });
        allStaTCConfig.value = {
            title: "",
            toolbar: [
                { name: "图表转换", mode: false },
                { name: "行列转换", mode: false },
                // { name: "编辑表格", mode: false },
                { name: "导出表格" }
            ],
            column: [
                { width: "120", prop: "time", label: "时间", axis: "x", isHeader: true, disable: true, format: (v) => dayjs(v).format("MM-DD HH:mm") },
                ...ZiDongZhan.map(v => ({ prop: v.short_name, label: v.short_name, unit: "", axis: "y", sortable: true }))
            ],
            export: {
                name: segmentedMetElement.value
            }
        };
        showAllStaTC.value = true;
    })
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

        // 预警图标
        let iconHtml = item.warn.map((val, idx) => {
            let count = item.warn.length,
                radius = 40,
                setpAngle = 60,
                startAngle = -(count - 1) * setpAngle / 2;
            let angle = startAngle + idx * setpAngle,
                radian = angle * (Math.PI / 180),
                x = radius * Math.sin(radian),
                y = -radius * Math.cos(radian);
            return `<img class="img"
                         style="position: absolute;width: 26px;height: 26px;left: 50%;top: 50%;
                                transform: translate(-50%, -50%) translate(${x}px, ${y}px) rotate(${angle}deg);"
                         src="/zhmc/assets/images/灾害预警图标/${val}.jpg" />`
        }).join("");

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
                                ${iconHtml}
                                <span class="val">${item.val}</span>
                                <span class="name">${item.short_name}</span>
                            </div>
                        </div>`,
                }),
                riseOnHover: true,
                item: item,
            }).on("click", function (e) {
                getStationDataByIdAndTimeRange(item);
            })
            group.push(layer);
        }
    }
    removeLayerById("current-station-layer");
    GLOBAL_CACHE["current-station-layer"] = L.layerGroup(group).addTo(map);
}
// ---------- 渲染站点弹窗 ----------
function renderStationPopup() { }
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
    let startLon = data.startLon, startLat = data.startLat,
        lonStep = data.lonStep, latStep = data.latStep,
        lonCount = data.lonCount, latCount = data.latCount,
        DS = data.DS;
    let polygon = turf.feature(ShiBianJie.features[0].geometry);
    let features = [];
    for (let y = 0; y < latCount; y++) {
        for (let x = 0; x < lonCount; x++) {
            let lon = startLon + lonStep * (x + 0.5),
                lat = startLat + latStep * (y + 0.5),
                val = DS[y][x];
            // 裁剪
            let point = turf.point([lon, lat]);
            if (turf.booleanPointInPolygon(point, polygon)) {
                let config = funThreshold(val);
                // 过滤
                let filter = filterRange.includes(config.color);
                if (filter) {
                    features.push({
                        "type": "Feature",
                        "properties": { "color": config.color },
                        "geometry": {
                            "type": "Polygon", "coordinates": [
                                [
                                    [startLon + lonStep * x, startLat + latStep * y],
                                    [startLon + lonStep * (x + 1), startLat + latStep * y],
                                    [startLon + lonStep * (x + 1), startLat + latStep * (y + 1)],
                                    [startLon + lonStep * x, startLat + latStep * (y + 1)],
                                    [startLon + lonStep * x, startLat + latStep * y],
                                ]
                            ]
                        }
                    })
                }
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
        // click: (e, feature) => {
        //     console.log(e, feature)
        // },
        // hover: (e, feature) => {
        //     console.log(e, feature)
        // },
        // hoverOff: (e, feature) => {
        //     console.log(e, feature)
        // }
    });
}
// ---------- 渲染风场图层 ----------
function renderWinFieldLayer(data) {
    console.log(data)
    var velocityLayer = L.velocityLayer({
        data: data,
        colorScale: winLegendColor,
        colorIndex: (speed) => {// 该属性为后添加属性
            return getWinThreshold(speed).id;
        },
        displayValues: false,
        // displayOptions: {
        //     velocityType: "风速",       // 标签前缀
        //     position: "bottomleft",     // leaflet 控件位置
        //     emptyString: "无速度数据",   // 鼠标处无数据
        //     angleConvention: "meteo",   // 风向计算方式。可选['bearing','meteo','CCW','CW'] bearing：吹去的方向；meteo：吹来的方向；CCW ：与正北方向逆时针夹角；CW ：与正北方向顺时针夹角
        //     showCardinal: true,         // 显示方位字母与度数
        //     speedUnit: "m/s",           // 风速单位。可选['m/s', 'k/h', 'mph', 'kt']
        //     directionString: "方向",    // 风向标签前缀
        //     speedString: "速度",        // 风速标签前缀
        // },

        // paneName: "overlayPane",        // 图层面板
        // minVelocity: 0,                 // 粒子最小速度（ m/s ）
        // maxVelocity: 10,                // 粒子最大速度（ m/s ）
        // velocityScale: 0.005,           // 风速的比例 ( 粒子的小尾巴长度 )
        // particleAge: 90,                // 粒子在再生之前绘制的最大帧数
        // lineWidth: 1,                   // 粒子的线宽
        // particleMultiplier: 1 / 300,    // 粒子的密度
        // frameRate: 15,                  // 每秒的帧数
        // opacity: 0.97,                  // 图层层透明度，默认值为 0.97
        // colorScale: [],                 // 定义自己的 hex / rgb 颜色数组 ( 粒子颜色 )
        // onAdd: null,                     // 回调函数
        // onRemove: null,                  // 回调函数
    });
    removeLayerById("current-winfield-layer");
    GLOBAL_CACHE["current-winfield-layer"] = velocityLayer.addTo(map);
}



function getIcon(color, speed, direction) {
    let icon = ""
    if (metElement.value == "降水") {
        icon = `<svg viewBox="0 0 1024 1024" fill="${color}" color="${color}">
                    <path fill="currentColor" stroke="currentColor" stroke-width="60" stroke-linejoin="round" data-msg="半径390的边框"
                        d="M 225.85 684.98 L 512 994 L 798.15 684.98 A 390 390 0 0 1 225.85 684.98 A 390 390 0 1 1 798.15 684.98 A 390 390 0 1 0 225.85 684.98 z"></path>
                    <circle cx="512" cy="420" r="360" fill="#000"/>
                    <path transform-origin="center" transform="translate(0,-70)  scale(0.53)"
                        d="M861.44128 73.15199998H162.56128A35.58400001 35.58400001 0 0 1 128.00128 36.608 35.58400001 35.58400001 0 0 1 162.56128 0h698.88a35.58400001 35.58400001 0 0 1 34.56 36.608 35.58400001 35.58400001 0 0 1-34.56 36.544zM701.88928 987.392a35.58400001 35.58400001 0 0 0-34.56-36.54400001H356.67328a36.672 36.672 0 0 0 0 73.15200001h310.656a35.58400001 35.58400001 0 0 0 34.56-36.608z m120.704-813.184v476.032a44.22399999 44.22399999 0 0 1-44.48 43.71200002H245.88928a44.22399999 44.22399999 0 0 1-44.48-43.71200002V174.208a44.22399999 44.22399999 0 0 1 44.48-43.71200002h532.224a44.22399999 44.22399999 0 0 1 44.48 43.71200002zM727.04128 553.08800001c0-19.456-11.648-35.2-25.984-35.2H311.04128c-14.336 0-25.984 15.744-25.98400001 35.2s11.648 35.2 25.98400001 35.19999999h390.016c14.336 0 25.984-15.744 25.984-35.19999999z m0-140.86400002c0-19.456-11.648-35.2-25.984-35.19999998H311.04128c-14.336 0-25.984 15.744-25.98400001 35.2s11.648 35.2 25.98400001 35.20000001h390.016c14.336 0 25.984-15.744 25.984-35.20000001z m0-140.86399999c0-19.456-11.648-35.2-25.984-35.20000002H311.04128c-14.336 0-25.984 15.744-25.98400001 35.20000002s11.648 35.2 25.98400001 35.2h390.016c14.336 0 25.984-15.744 25.984-35.2z m-170.68800001 422.59200002H467.64928v281.72799997h88.70399999z"
                    ></path>
                </svg>`;
    } else if (metElement.value == "气温") {
        icon = `<svg viewBox="0 0 1024 1024" fill="${color}" color="${color}">
                    <path fill="currentColor" stroke="currentColor" stroke-width="60" stroke-linejoin="round" data-msg="半径390的边框"
                        d="M 225.85 684.98 L 512 994 L 798.15 684.98 A 390 390 0 0 1 225.85 684.98 A 390 390 0 1 1 798.15 684.98 A 390 390 0 1 0 225.85 684.98 z"></path>
                    <circle cx="512" cy="420" r="360" fill="#000"/>
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
        icon = `<svg viewBox="0 0 1024 1024" fill="${color}" color="${color}">
                    <path fill="currentColor" stroke="currentColor" stroke-width="60" stroke-linejoin="round" data-msg="半径390的边框"
                        d="M 225.85 684.98 L 512 994 L 798.15 684.98 A 390 390 0 0 1 225.85 684.98 A 390 390 0 1 1 798.15 684.98 A 390 390 0 1 0 225.85 684.98 z"></path>
                    <circle cx="512" cy="420" r="360" fill="#000"/>
                    <g  transform-origin="center" transform="translate(0,-115) rotate(${direction}) scale(0.53)">${path}</g>
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
