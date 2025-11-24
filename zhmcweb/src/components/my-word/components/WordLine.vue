<style scoped lang="scss">
.word-line-wrap {
    width: 100%;

    hr {
        height: 4px;
        background-color: red;
        border: none;
        border-radius: 9999px;
        margin: 10px 0;
    }
}
</style>
<template>
    <div class="word-line-wrap">
        <hr>
    </div>
</template>

<script setup>
import { ref } from "vue";
const model = defineModel();




</script>
