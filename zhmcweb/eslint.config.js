import { defineConfig, globalIgnores } from 'eslint/config'
import globals from 'globals'
import js from '@eslint/js'
import pluginVue from 'eslint-plugin-vue'

export default defineConfig([
  {
    name: 'app/files-to-lint',
    files: ['**/*.{js,mjs,jsx,vue}'],
  },

  globalIgnores(['**/dist/**', '**/dist-ssr/**', '**/coverage/**']),

  {
    languageOptions: {
      globals: {
        ...globals.browser,
      },
    },
  },

  js.configs.recommended,
  ...pluginVue.configs['flat/essential'],
  {
    rules: {
      "no-unused-vars": "off", // 禁用 no-unused-vars 规则
      // 关键配置：允许 index 作为单单词组件名，其他组件仍需多单词
      "vue/multi-word-component-names": [
        "error",
        {
          ignores: ["index"] // 明确允许 index.vue 组件名
        }
      ],
      "vue/no-multiple-template-root": "off"
    }
  }
])
