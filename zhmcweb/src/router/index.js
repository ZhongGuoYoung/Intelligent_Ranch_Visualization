import { createRouter, createWebHistory, createWebHashHistory } from "vue-router";

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    // { path: "/", component: () => import("../views/index.vue"), redirect: "/mcgk" },
    {
      path: "/", component: () => import("../views/index.vue"), redirect: "/mcgk",
      children: [
        { path: "/mcgk", component: () => import("../views/mcgk/index.vue") },
        { path: "/mcsk", component: () => import("../views/mcsk/index.vue") },
        { path: "/mcyb", component: () => import("../views/mcyb/index.vue") },
        { path: "/qxyj", component: () => import("../views/qxyj/index.vue") },
        { path: "/gyx", component: () => import("../views/gyx/index.vue") },
        { path: "/ryj", component: () => import("../views/ryj/index.vue") },
        { path: "/lyj", component: () => import("../views/lyj/index.vue") },
        { path: "/msjc", component: () => import("../views/msjc/index.vue") },
        { path: "/xtgl", component: () => import("../views/xtgl/index.vue") },
        { path: "/xtgl/user", component: () => import("../views/xtgl/user.vue") },
        {
          path: "/cpfw", component: () => import("../views/cpfw/index.vue"), redirect: encodeURI("/cpfw/热应激预报"),
          children: [
            { path: encodeURI("/cpfw/热应激预报"), component: () => import("../views/cpfw/ryj.vue") },
            { path: encodeURI("/cpfw/冷应激预报"), component: () => import("../views/cpfw/lyj.vue") },
          ]
        },
        // {
        //     path: "/fwcp", component: () => import("../views/fwcp/index.vue"), redirect: encodeURI("/fwcp/应激指数精细化预报"),
        //     children: [
        //         { path: encodeURI("/fwcp/应激指数精细化预报"), component: () => import("../views/fwcp/yjzsyb.vue") },
        //         { path: encodeURI("/fwcp/牧场常规天气预报"), component: () => import("../views/fwcp/cgtqyb.vue") },
        //     ]
        // },
      ]
    }
  ],
})

export default router

