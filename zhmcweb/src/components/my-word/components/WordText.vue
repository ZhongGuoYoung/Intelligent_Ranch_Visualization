<style scoped>
.paragraph-text-wrap {
    width: 100%;
    display: flex;
    flex-direction: column;

    .title {
        font-size: 18px;
        text-align: left;
        margin: 0.5em 0;
        font-weight: bold;
    }

    .content {
        p {
            font-size: 16px;
            text-align: left;
            margin: 0.5em 0;
            text-indent: 2em;
        }

        :deep(textarea) {
            background-color: #000;
            color: #fff;
            resize: none;
            font-size: 16px;
            line-height: 1.65em;
            overflow: hidden;
            box-shadow: none;
            transition: 200ms;
        }
    }
}
</style>
<template>
    <div class="paragraph-text-wrap">
        <div class="title"
             v-if="model.title">{{ model.title }}</div>
        <div class="content"
             v-if="model.data">
            <el-input v-if="showEditor"
                      v-model="model.data"
                      type="textarea"
                      :autosize="{ minRows: 1 }"
                      @blur="showEditor = false"
                      @dblclick="showEditor = false" />
            <template v-else>
                <p v-for="item in model.data.split(/\r?\n/)"
                   :key="item"
                   @dblclick="showEditor = true">{{ item }}</p>
            </template>
        </div>
    </div>
</template>

<script setup>
import { ref, nextTick } from "vue";
const model = defineModel();
const showEditor = ref(false);



</script>
