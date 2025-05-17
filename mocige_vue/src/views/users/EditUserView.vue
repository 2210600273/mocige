<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ArrowLeft } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import { Switch } from '@/components/ui/switch'
import { useToast } from '@/components/ui/toast'

const route = useRoute()
const router = useRouter()
const { toast } = useToast()
const isLoading = ref(false)

// 模拟从API获取用户数据
const user = ref({
  id: parseInt(route.params.id),
  username: 'user1',
  email: 'user1@example.com',
  role: 'user',
  status: 'active',
  learningMode: 'Spaced Repetition',
  dailyWordCount: 10,
  reminderTime: '08:00',
  emailNotifications: true,
})

const handleSubmit = (e) => {
  e.preventDefault()
  isLoading.value = true

  // 模拟API请求
  setTimeout(() => {
    isLoading.value = false
    toast({
      title: '用户已更新',
      description: `用户 "${user.value.username}" 已成功更新。`,
    })
  }, 1000)
}
</script>

<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center gap-2">
      <router-link :to="`/users/${route.params.id}`">
        <Button variant="ghost" size="sm">
          <ArrowLeft class="mr-2 h-4 w-4" />
          返回用户详情
        </Button>
      </router-link>
      <h1 class="text-3xl font-bold tracking-tight">编辑用户</h1>
    </div>

    <Card>
      <form @submit="handleSubmit">
        <CardHeader>
          <CardTitle>用户信息</CardTitle>
          <CardDescription>编辑用户账户信息</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="space-y-2">
              <Label for="username">用户名</Label>
              <Input
                id="username"
                v-model="user.username"
                required
              />
            </div>
            <div class="space-y-2">
              <Label for="email">邮箱</Label>
              <Input
                id="email"
                type="email"
                v-model="user.email"
                required
              />
            </div>
          </div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="space-y-2">
              <Label for="role">角色</Label>
              <Select v-model="user.role">
                <SelectTrigger id="role">
                  <SelectValue placeholder="选择角色" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="admin">管理员</SelectItem>
                  <SelectItem value="editor">编辑</SelectItem>
                  <SelectItem value="user">普通用户</SelectItem>
                </SelectContent>
              </Select>
            </div>
            <div class="space-y-2">
              <Label for="status">状态</Label>
              <Select v-model="user.status">
                <SelectTrigger id="status">
                  <SelectValue placeholder="选择状态" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="active">活跃</SelectItem>
                  <SelectItem value="inactive">非活跃</SelectItem>
                  <SelectItem value="suspended">已暂停</SelectItem>
                </SelectContent>
              </Select>
            </div>
          </div>

          <div class="space-y-4">
            <h3 class="text-lg font-medium">学习偏好</h3>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div class="space-y-2">
                <Label for="learningMode">学习模式</Label>
                <Select v-model="user.learningMode">
                  <SelectTrigger id="learningMode">
                    <SelectValue placeholder="选择学习模式" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="Spaced Repetition">间隔重复</SelectItem>
                    <SelectItem value="Flashcards">闪卡</SelectItem>
                    <SelectItem value="Quiz">测验</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              <div class="space-y-2">
                <Label for="dailyWordCount">每日单词数量</Label>
                <Input
                  id="dailyWordCount"
                  type="number"
                  min="1"
                  max="100"
                  v-model="user.dailyWordCount"
                />
              </div>
            </div>

            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div class="space-y-2">
                <Label for="reminderTime">提醒时间</Label>
                <Input
                  id="reminderTime"
                  type="time"
                  v-model="user.reminderTime"
                />
              </div>
              <div class="flex items-center justify-between space-y-0 pt-5">
                <div class="space-y-0.5">
                  <Label for="emailNotifications">邮件通知</Label>
                  <p class="text-sm text-muted-foreground">接收学习提醒和更新</p>
                </div>
                <Switch
                  id="emailNotifications"
                  v-model="user.emailNotifications"
                />
              </div>
            </div>
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button variant="outline" type="button" @click="router.back()">
            取消
          </Button>
          <Button type="submit" :disabled="isLoading">
            {{ isLoading ? '保存中...' : '保存更改' }}
          </Button>
        </CardFooter>
      </form>
    </Card>

    <Card>
      <CardHeader>
        <CardTitle>重置密码</CardTitle>
        <CardDescription>为此用户重置密码</CardDescription>
      </CardHeader>
      <CardContent>
        <p class="text-sm text-muted-foreground">
          重置密码将向用户发送一封包含密码重置链接的电子邮件。用户需要点击链接来设置新密码。
        </p>
      </CardContent>
      <CardFooter>
        <Button variant="outline" class="w-full">
          发送密码重置邮件
        </Button>
      </CardFooter>
    </Card>
  </div>
</template>