import "./assets/main.css";

import { createApp } from "vue";
import App from "./App.vue";

// router
import router from "./router";

// ant-design-vue
import "ant-design-vue/dist/reset.css";
import Antd from "ant-design-vue";

// element-plus
import "element-plus/dist/index.css";
import ElementPlus from "element-plus";
import zhCn from "element-plus/es/locale/lang/zh-cn";

// pinia
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate';
import { createPinia } from "pinia";

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

const app = createApp(App);
app.use(router)
    .use(pinia)
    .use(Antd)
    .use(ElementPlus, { locale: zhCn })
    .mount("#app")


