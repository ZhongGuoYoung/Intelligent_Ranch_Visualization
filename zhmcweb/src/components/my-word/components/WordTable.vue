<style scoped lang="scss">
.word-table-wrap {
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
        width: 100%;
        min-width: 200px;
        min-height: 100px;
        border-radius: 4px;
        box-shadow: 0 0 2px rgba(0, 0, 0, 0.3);
        background-color: rgba(0, 0, 0, 0.01);
        display: flex;
        align-items: center;
        justify-content: center;
    }
}

:deep(.my-table) {
    font-size: 16px;
    --el-table-bg-color: transparent;
    --el-table-header-bg-color: transparent;
    --el-table-tr-bg-color: transparent;
    --el-table-border-color: transparent;
    --el-table-text-color: #000;
    --el-table-header-text-color: #000;
    --el-table-row-hover-bg-color: rgba(0, 0, 0, 0.03);
    --el-table-border: 1px solid #c6d7de;

    .el-table__empty-text {
        color: #000;
    }

    .table-header-row {
        background-color: rgba(0, 0, 0, 0.06);
    }

    .table-row {
        height: 2em
    }

    .table-cell {}

    .table-header-cell {}
}
</style>
<template>
    <div class="word-table-wrap">
        <div class="title"
             v-if="props.title">{{ props.title }}</div>
        <div class="content"
             :style="{
                width: props.width ? props.width + 'px' : undefined,
                // height: props.height + 'px',
            }">
            <el-table class="my-table"
                      :data="model"
                      empty-text="无数据"
                      header-row-class-name="table-header-row"
                      header-cell-class-name="table-header-cell"
                      row-class-name="table-row"
                      cell-class-name="table-cell">
                <el-table-column v-for="item in props.column"
                                 :key="item"
                                 :width="item.width"
                                 :align="item.align ? item.align : 'center'"
                                 :prop="item.prop"
                                 :label="item.label"
                                 :sortable="item.sortable">
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
const model = defineModel();
const props = defineProps({
    title: { type: String, default: "" },
    column: { type: Array, default: (rawProps) => ([]) },
    width: { type: Number, default: undefined },
    // height: { type: Number, default: 100 },
});




</script>
