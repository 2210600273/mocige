<script setup>
import { RouterView, useRoute } from 'vue-router'
import { computed } from 'vue'
import Layout from './layouts/Layout.vue'
import ThemeProvider from './components/ThemeProvider.vue'

const route = useRoute()
const isLoginPage = computed(() => route.path === '/login')
</script>

<template>
  <ThemeProvider defaultTheme="light">
    <!-- 登录页不使用 Layout -->
    <template v-if="isLoginPage">
      <RouterView />
    </template>

    <!-- 其他页面使用 Layout -->
    <template v-else>
      <Layout>
        <RouterView />
      </Layout>
    </template>
  </ThemeProvider>
</template>

<style>
/* 全局样式 */
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}

#app {
  height: 100%;
  width: 100%;
  max-width: 100%;
  margin: 0;
  padding: 0;
}
</style>
