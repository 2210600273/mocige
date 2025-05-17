<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ArrowLeft } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { useToast } from '@/components/ui/toast'

const router = useRouter()
const { toast } = useToast()

const username = ref('')
const email = ref('')
const password = ref('')
const confirmPassword = ref('')

const handleSubmit = (e) => {
  e.preventDefault()

  // 验证
  if (!username.value.trim() || !email.value.trim() || !password.value) {
    toast({
      title: '错误',
      description: '所有字段都是必填的',
      variant: 'destructive',
    })
    return
  }

  if (password.value !== confirmPassword.value) {
    toast({
      title: '错误',
      description: '密码不匹配',
      variant: 'destructive',
    })
    return
  }

  // 提交逻辑
  console.log({ username: username.value, email: email.value, password: password.value })

  toast({
    title: '用户已创建',
    description: `用户 "${username.value}" 已成功创建。`,
  })

  // 重置表单
  username.value = ''
  email.value = ''
  password.value = ''
  confirmPassword.value = ''
}
</script>

<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center gap-2">
      <router-link to="/users">
        <Button variant="ghost" size="sm">
          <ArrowLeft class="mr-2 h-4 w-4" />
          返回用户列表
        </Button>
      </router-link>
      <h1 class="text-3xl font-bold tracking-tight">添加新用户</h1>
    </div>

    <Card>
      <form @submit="handleSubmit">
        <CardHeader>
          <CardTitle>用户信息</CardTitle>
          <CardDescription>输入新用户账户的详细信息。</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div class="space-y-2">
            <Label for="username">用户名</Label>
            <Input
              id="username"
              placeholder="输入用户名"
              v-model="username"
              required
            />
          </div>

          <div class="space-y-2">
            <Label for="email">邮箱</Label>
            <Input
              id="email"
              type="email"
              placeholder="输入邮箱地址"
              v-model="email"
              required
            />
          </div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="space-y-2">
              <Label for="password">密码</Label>
              <Input
                id="password"
                type="password"
                placeholder="输入密码"
                v-model="password"
                required
              />
            </div>
            <div class="space-y-2">
              <Label for="confirm-password">确认密码</Label>
              <Input
                id="confirm-password"
                type="password"
                placeholder="确认密码"
                v-model="confirmPassword"
                required
              />
            </div>
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button variant="outline" type="button" @click="router.back()">
            取消
          </Button>
          <Button type="submit">创建用户</Button>
        </CardFooter>
      </form>
    </Card>
  </div>
</template>