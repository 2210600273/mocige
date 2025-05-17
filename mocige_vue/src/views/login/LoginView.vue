<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { BookOpen } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'

const router = useRouter()
const username = ref('')
const password = ref('')
const isLoading = ref(false)
const error = ref('')

const handleSubmit = async (e) => {
  e.preventDefault()
  
  // Simple validation
  if (!username.value || !password.value) {
    showToast('错误', '请输入用户名和密码', 'destructive')
    return
  }
  
  isLoading.value = true
  error.value = ''
  
  try {
    // Simulate API call
    await new Promise(resolve => setTimeout(resolve, 1000))
    
    // For demo purposes, accept any login
    showToast('登录成功', '欢迎使用 Mocige 管理后台')
    
    // Store authentication token
    localStorage.setItem('mocige_auth', 'dummy_token')
    
    // Redirect to dashboard
    router.push('/')
  } catch (err) {
    console.error(err)
    showToast('登录失败', '用户名或密码错误', 'destructive')
  } finally {
    isLoading.value = false
  }
}

// Simple toast function (can be replaced with a proper toast component later)
const showToast = (title, description, variant = 'default') => {
  // This is a placeholder for a proper toast implementation
  console.log(`[${variant.toUpperCase()}] ${title}: ${description}`)
  
  if (variant === 'destructive') {
    error.value = description
  }
}
</script>

<template>
  <div class="flex min-h-screen items-center justify-center bg-muted/40 p-4">
    <Card class="w-full max-w-md">
      <CardHeader class="space-y-2 text-center">
        <div class="flex justify-center">
          <BookOpen class="h-10 w-10 text-primary" />
        </div>
        <CardTitle class="text-2xl">Mocige 管理后台</CardTitle>
        <CardDescription>输入您的凭据以访问管理后台</CardDescription>
      </CardHeader>
      <form @submit="handleSubmit">
        <CardContent class="space-y-4">
          <!-- Error Alert -->
          <div v-if="error" class="bg-destructive/15 text-destructive text-sm p-3 rounded-md">
            {{ error }}
          </div>
          
          <div class="space-y-2">
            <Label for="username">用户名</Label>
            <Input
              id="username"
              v-model="username"
              placeholder="请输入用户名"
              :disabled="isLoading"
              required
            />
          </div>
          <div class="space-y-2">
            <div class="flex items-center justify-between">
              <Label for="password">密码</Label>
              <router-link to="/forgot-password" class="text-sm text-primary hover:underline">
                忘记密码？
              </router-link>
            </div>
            <Input
              id="password"
              type="password"
              v-model="password"
              placeholder="请输入密码"
              :disabled="isLoading"
              required
            />
          </div>
        </CardContent>
        <CardFooter>
          <Button class="w-full" type="submit" :disabled="isLoading">
            <span v-if="isLoading">
              <span class="mr-2 h-4 w-4 animate-spin" />
              登录中...
            </span>
            <span v-else>登录</span>
          </Button>
        </CardFooter>
      </form>
    </Card>
  </div>
</template>
