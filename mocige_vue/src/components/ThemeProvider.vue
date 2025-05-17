<script setup>
import { ref, onMounted, watch, provide } from 'vue'

const props = defineProps({
  defaultTheme: {
    type: String,
    default: 'system'
  },
  storageKey: {
    type: String,
    default: 'vueuse-color-scheme'
  }
})

const theme = ref(props.defaultTheme)

// Get system preference
const getSystemTheme = () => {
  return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'
}

// Set theme
const setTheme = (newTheme) => {
  const resolvedTheme = newTheme === 'system' ? getSystemTheme() : newTheme
  theme.value = newTheme

  // Update DOM
  const root = window.document.documentElement
  root.classList.remove('light', 'dark')
  root.classList.add(resolvedTheme)
  
  // Save to localStorage
  localStorage.setItem(props.storageKey, newTheme)
}

// Initialize
onMounted(() => {
  const savedTheme = localStorage.getItem(props.storageKey)
  if (savedTheme) {
    setTheme(savedTheme)
  } else {
    setTheme(props.defaultTheme)
  }
  
  // Listen for system changes
  const mediaQuery = window.matchMedia('(prefers-color-scheme: dark)')
  mediaQuery.addEventListener('change', () => {
    if (theme.value === 'system') {
      setTheme('system')
    }
  })
})

// Provide theme to components
provide('theme', {
  current: theme,
  setTheme
})
</script>

<template>
  <slot />
</template> 