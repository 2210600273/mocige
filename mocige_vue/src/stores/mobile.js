import { ref, onMounted, onUnmounted } from 'vue'
import { defineStore } from 'pinia'

export const useMobileStore = defineStore('mobile', () => {
  const isMobile = ref(false)
  
  // Function to check screen size
  const checkScreenSize = () => {
    isMobile.value = window.innerWidth < 1024 // Using 1024px as the breakpoint for lg
  }

  // Initial check and event listener setup
  onMounted(() => {
    checkScreenSize()
    window.addEventListener('resize', checkScreenSize)
  })

  // Cleanup
  onUnmounted(() => {
    window.removeEventListener('resize', checkScreenSize)
  })

  return { isMobile }
}) 