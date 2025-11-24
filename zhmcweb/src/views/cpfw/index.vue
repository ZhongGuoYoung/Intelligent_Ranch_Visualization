<!-- eslint-disable vue/multi-word-component-names -->
<style scoped lang="scss">
.aside {
    display: flex;
    flex-direction: column;
    width: 80px;
    background: rgba(255, 255, 255, 0.2);

    .model {
        flex: 1;
        text-decoration: none;
        display: flex;
        align-items: center;
        justify-content: center;
        color: #fff;
        font-size: 16px;
        line-height: 1em;
        writing-mode: vertical-lr;

        &.active {
            color: #3AEDB8;
            background: rgba(255, 255, 255, 0.3);
        }
    }
}

.main {
    padding: 0;
    position: relative;
}
</style>

<template>
    <el-container class="view">
        <el-aside class="aside">
            <router-link class="model"
                         v-for="item in models"
                         :key="item"
                         :class="{ active: modelPicked == encodeURI(item.name) }"
                         :to="encodeURI('/cpfw/' + item.name)"
                         @click="modelPicked = encodeURI(item.name)">
                <span>{{ item.name }}</span>
            </router-link>
        </el-aside>
        <el-main class="main">
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script setup>
import { ref, onMounted, computed, shallowRef } from "vue";
import { useRouter } from "vue-router"

let models = ref([
    { name: '热应激预报', key: 'ryj' },
    { name: '冷应激预报', key: 'lyj' },
]);
let modelPicked = ref(encodeURI(models.value[0].name));



// 刷新页面后，重新选中模块样式。encodeURI：解决url中的中文乱码
let url = useRouter().currentRoute.value.path,
    model = url.split("/")[2];
if (model) { modelPicked.value = model; }



</script>