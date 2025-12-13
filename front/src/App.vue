<template>
  <div id="app">
    <EmployeeList />
  </div>
</template>

<script setup lang="ts">
import EmployeeList from '@/views/EmployeeList.vue'
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background: #f9fafb;
}

#app {
  min-height: 100vh;
}
</style>
