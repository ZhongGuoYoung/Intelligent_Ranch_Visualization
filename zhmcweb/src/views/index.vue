<template>
  <el-container class="container">
    <!-- 顶部：三列网格 = 左(3个) | 中(标题) | 右(4个) -->
    <el-header class="header">
      <!-- 左侧 3 个 -->
      <nav class="nav nav-left">
        <router-link
          v-for="item in leftModels"
          :key="item.key"
          :class="{ active: modelPicked === item.key }"
          :to="'/' + item.key"
          :aria-label="item.name"
          :title="item.name"
          @click="modelPicked = item.key"
        >
          <img class="nav-img" :src="getNavImg(item.key)" :alt="item.name" />
        </router-link>
      </nav>

      <!-- 中间标题（带框体与导线） -->
         <div class="title-box">
              <h1 class="title">
            <span class="title-main">奶牛养殖气象灾害监测预警系统</span>
             <span class="title-glow" aria-hidden="true">奶牛养殖气象灾害监测预警系统</span>
          </h1>
        </div>

      <!-- 右侧 4 个 -->
      <nav class="nav nav-right">
        <router-link
          v-for="item in rightModels"
          :key="item.key"
          :class="{ active: modelPicked === item.key }"
          :to="'/' + item.key"
          :aria-label="item.name"
          :title="item.name"
          @click="modelPicked = item.key"
        >
          <img class="nav-img" :class="{ 'special-nav': item.key === 'cpfw' }" :src="getNavImg(item.key)" :alt="item.name" />

        </router-link>
      </nav>
    </el-header>

    <!-- 主内容 -->
    <el-main class="main">
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";

const models = ref([
  { name: "牧场概况",   key: "mcgk", icon: "mcgk" },
  { name: "牧场实况",   key: "mcsk", icon: "mcgk" },
  { name: "牧场预报",   key: "mcyb", icon: "mcgk" },
  { name: "气象预警",   key: "qxyj", icon: "mcgk" },
  { name: "热应激预报", key: "ryj",  icon: "mcgk" },
  { name: "冷应激预报", key: "lyj",  icon: "mcgk" },
  { name: "牧舍监测",   key: "msjc", icon: "mcgk" },
  { name: "系统管理",   key: "xtgl", icon: "mcgk" },
  { name: "产品服务",   key: "cpfw", icon: "mcgk" },
]);

const getNavImg = (k) => new URL(`../static/icons/${k}.png`, import.meta.url).href;

// 分成左 3 / 右 4
const leftModels  = computed(() => models.value.slice(0, 4));
const rightModels = computed(() => models.value.slice(4));

const router = useRouter();
const modelPicked = ref("home");
// 刷新后保留选中态
const url = router.currentRoute.value.path;
const model = url.split("/")[1];
if (model) modelPicked.value = model;


</script>

<style scoped lang="scss">
/* ===== 数字可视化大屏主题（实体深色，能量光晕） ===== */
:root{
  --ds-bg:#0E1A2B;
  --ds-txt:rgba(255,255,255,.88);
  --ds-sub:rgba(255,255,255,.62);
  --ds-line:rgba(255,255,255,.10);
  --ds-blue:#2BD9FE;
  --ds-cyan:#00FFA3;
  --frame-corner: rgba(43,217,254,.85);  /* 角标颜色 */
  --frame-line:   rgba(139,233,255,.45); /* 导线颜色 */
  --trap-top: 10%;                      /* 顶边缩进百分比（越大越尖） */
  --trap-stroke: rgba(43,217,254,.85);  /* 外描边颜色 */
  --trap-glow: rgba(43,217,254,.28);    /* 外发光 */
  --trap-bg1: rgba(255,255,255,.06);    /* 面板渐变上 */
  --trap-bg2: rgba(255,255,255,.03);    /* 面板渐变下 */
  --line: rgba(139,233,255,.45);        /* 左右导线颜色 */
}

/* 整体容器背景（可叠加你的背景图） */
.container{
  position: relative;
  width: 100%; height: 100%;
  color: var(--ds-txt);
  background:
    radial-gradient(1200px 700px at 18% -22%, rgba(43,217,254,.14), transparent 60%),
    radial-gradient(900px 560px  at 85% 120%, rgba(0,255,163,.12), transparent 60%),
    #0E1A2B;
  overflow: hidden;
}

/* 顶部条：三列网格固定高度，标题绝对居中 */
.header{
  position: sticky; top: 0; z-index: 10;
  height: 110px;
  display: grid;
  grid-template-columns: 1fr auto 1fr; /* 左 | 中 | 右 */
  align-items: center;
  padding: 0 20px;
  background: linear-gradient(180deg, rgba(255,255,255,.05), rgba(255,255,255,.02));
  border-bottom: 1px solid var(--ds-line);
  box-shadow: 0 10px 28px rgba(0,0,0,.45);
}

/* 包裹标题的框体 */
.title-box{
  grid-column: 2;           /* 保证在正中 */
  position: relative;
  width: 599px;             /* 固定宽度 */
  height: 80px;             /* 固定高度 */
  margin: 0 auto;
  /* 框体本身：轻微实体 + 细描边 + 内阴影，形成“硬件面板”质感 */

  clip-path: polygon(0% 0%, 100% 0%, 90% 100%, 10% 100%);
  /* —— 四角“L”形角标（用多重背景画出来，无需额外 DOM） —— */
  /* 深蓝色渐变背景，模拟图片中的效果 */
  background: linear-gradient(
    180deg,
    #2145a8 0%,           /* 顶部稍亮的蓝色 */
    #0F172A 50%,          /* 中间过渡 */
    #020617 100%          /* 底部深蓝色 */
  );

  /* 外发光和阴影效果 */
  box-shadow:
    0 0 30px rgba(59, 130, 246, 0.3),
    0 8px 32px rgba(0, 0, 0, 0.4),
    inset 0 1px 0 rgba(255, 255, 255, 0.1);

  /* 细边框 */
  border: 3px solid rgba(59, 130, 246, 0.4);

  /* 向左拉出一条“能量导线”，带轻微流动动画 */
  &::before,
  &::after{
    content: "";
    position: absolute;
    top: 50%;
    height: 2px;
    transform: translateY(-50%);
    pointer-events: none;
    /* 虚实相间的流动线：repeating-linear-gradient + 背景位移动画 */
    background: repeating-linear-gradient(
      to right,
      transparent 0 14px,
      rgba(59, 130, 246, 0.6) 14px 22px,
      transparent 22px 36px
    );
    filter: drop-shadow(0 0 8px rgba(59, 130, 246, 0.4));
    animation: dash-move 8s linear infinite;
  }
  /* 左线 */
  &::before{
    right: 100%;
    width: 36vw;                  /* 线的长度，可调 */
    margin-right: 16px;           /* 线与框体的间距 */
    mask-image: linear-gradient(to left, rgba(0,0,0,0) 0, #000 40%);
  }
  /* 右线（反向） */
  &::after{
    left: 100%;
    width: 36vw;
    margin-left: 16px;
    mask-image: linear-gradient(to right, rgba(0,0,0,0) 0, #000 40%);
  }
}

/* 标题文本维持原有双层发光，稍作紧凑 */
.title{
  position: relative;
  margin: 0;
  line-height: 1;
  text-align: center;
  letter-spacing: .8px;
}
.title-main{
  display: inline-block;
  font-size: 34px;
  font-weight: 800;
  background: linear-gradient(180deg, #fff, #BFEFFF 62%, #6EE7F9);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  text-shadow: 0 0 24px rgba(43,217,254,.25);
}
.title-glow{
  position: absolute;
  inset: 0;
  font-size: 34px;
  font-weight: 800;
  color: transparent;
  text-shadow:
    0 0 20px rgba(43,217,254,.35),
    0 0 40px rgba(0,255,163,.25);
  pointer-events: none;
  filter: blur(1.2px);
}

/* 左右导航区：芯片按钮 + 图标 */
.nav{
  display: flex; align-items: center; gap: 10px;
}
.nav-left  { justify-content: flex-start; }
.nav-right { justify-content: flex-end; }

.nav a{
  position: relative;
  display: inline-flex; align-items: center; gap: 10px;
  height: 55px; padding: 0 10px;
  border-radius: 16px;
  border: 1px solid rgba(255,255,255,.08);
  background: linear-gradient(180deg, rgba(255,255,255,.06), rgba(255,255,255,.03));
  box-shadow: inset 0 0 0 1px rgba(255,255,255,.02);
  text-decoration: none;
  transform: translateZ(0);
  transition: transform .18s ease, box-shadow .18s ease, background .18s ease, border-color .18s ease;
  white-space: nowrap;
}
.nav a:hover{
  transform: translateY(-2px);
  background: linear-gradient(180deg, rgba(255,255,255,.08), rgba(255,255,255,.04));
  border-color: rgba(139,233,255,.22);
}
.nav a.active{
  background: linear-gradient(180deg, var(--ds-blue), #1AA9E9);
  color: #0B1220;
  border-color: transparent;
  box-shadow: 0 0 22px rgba(43,217,254,.35), inset 0 1px 0 rgba(255,255,255,.18);
}

.nav-img{
  display: block;
  width: 145px; height: 80px;
  object-fit: contain;
  filter: drop-shadow(0 2px 8px rgba(0,0,0,.25));
}
.nav-img.special-nav {
  width: 160px;
  height: 90px;
  filter: drop-shadow(0 2px 8px rgba(0,0,0,.25));

}


/* 主内容容器：实体描边卡片 */
.main{
  position: relative;
  margin: 10px; padding: 16px;
  background: linear-gradient(180deg, rgba(255,255,255,.04), rgba(255,255,255,.02));
  border: 1px solid var(--ds-line);
  border-radius: 16px;
  box-shadow: inset 0 0 0 1px rgba(255,255,255,.02), 0 10px 30px rgba(0,0,0,.35);
}


/* 流动导线动画（越慢越“高级”） */
@keyframes dash-move {
  from { background-position: 0 0; }
  to   { background-position: 400px 0; }
}

/* 响应式微调：小屏缩小框体与标题字号 */
@media (max-width: 1440px){
  .title-main, .title-glow{ font-size: 30px; }
  .title-box{ padding: 12px 32px; }
}
@media (max-width: 992px){
  .title-main, .title-glow{ font-size: 26px; }
  .title-box{ padding: 10px 28px; }
  .title-box::before,
  .title-box::after{ width: 28vw; }
}
@media (max-width: 768px){
  .title-main, .title-glow{ font-size: 24px; }
  .title-box{ padding: 8px 24px; }
  .title-box::before,
  .title-box::after{ width: 24vw; }
}
</style>
