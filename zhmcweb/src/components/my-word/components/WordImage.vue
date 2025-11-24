<style lang="scss">
.word-image-wrap {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 0.5em 0;

    .title {
        font-size: 16px;
        text-align: center;
        margin: 0.5em 0;
    }

    .content {
        min-width: 200px;
        min-height: 100px;
        width: 100%;
        border-radius: 4px;
        box-shadow: 0 0 2px rgba(0, 0, 0, 0.3);
        background-color: rgba(0, 0, 0, 0.01);
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .error {
        font-size: 16px;
    }
}
</style>

<template>
    <div class="word-image-wrap">
        <div class="content"
             :style="{
                width: props.width + 'px',
                // height: props.height + 'px',
            }">
            <el-image :src="model"
                      :preview-src-list="[model]">
                <template #error>
                    <div class="error">图片制作中...</div>
                </template>
            </el-image>
        </div>
        <div class="title"
             v-if="props.title">{{ props.title }}</div>
    </div>
</template>

<script setup>

const model = defineModel();
const props = defineProps({
    title: { type: String, default: "" },
    column: { type: Array, default: (rawProps) => ([]) },
    width: { type: Number, default: 200 },
    // height: { type: Number, default: 100 },
});


</script>