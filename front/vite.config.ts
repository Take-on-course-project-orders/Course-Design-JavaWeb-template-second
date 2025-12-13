import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

export default defineConfig({
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src')
    }
  },
  plugins: [vue()],
  server: {
    port: 5173,
    open: true,
    proxy: {
      "/api/emp": {
        target: "http://localhost:9001",
        changeOrigin: true
      }
    }
  }
})
