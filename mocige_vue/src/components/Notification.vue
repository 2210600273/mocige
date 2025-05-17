<script setup>
import { ref, computed } from 'vue'
import { Bell } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { cn } from '@/lib/utils'

// 定义通知类型
const notifications = ref([
  {
    id: "1",
    title: "新用户注册",
    description: "用户 user6@example.com 刚刚注册了账号",
    time: "5分钟前",
    read: false,
  },
  {
    id: "2",
    title: "系统更新",
    description: "系统将在今晚22:00进行维护更新",
    time: "1小时前",
    read: false,
  },
  {
    id: "3",
    title: "数据备份完成",
    description: "每日数据备份已成功完成",
    time: "3小时前",
    read: true,
  },
  {
    id: "4",
    title: "新反馈",
    description: "收到了3条新的用户反馈",
    time: "昨天",
    read: true,
  },
])

// 计算未读通知数量
const unreadCount = computed(() => notifications.value.filter(n => !n.read).length)

// 标记单个通知为已读
const markAsRead = (id) => {
  notifications.value = notifications.value.map(n =>
    n.id === id ? { ...n, read: true } : n
  )
}

// 标记所有通知为已读
const markAllAsRead = () => {
  notifications.value = notifications.value.map(n => ({ ...n, read: true }))
}
</script>

<template>
  <DropdownMenu>
    <DropdownMenuTrigger as-child>
      <Button variant="outline" size="icon" class="relative">
        <Bell class="h-4 w-4" />
        <span v-if="unreadCount > 0" class="absolute -right-1 -top-1 flex h-5 w-5 items-center justify-center rounded-full bg-destructive text-xs text-destructive-foreground">
          {{ unreadCount }}
        </span>
        <span class="sr-only">通知</span>
      </Button>
    </DropdownMenuTrigger>
    <DropdownMenuContent align="end" class="w-80">
      <DropdownMenuLabel class="flex items-center justify-between">
        <span>通知</span>
        <Button
          v-if="unreadCount > 0"
          variant="ghost"
          size="sm"
          @click="markAllAsRead"
          class="h-auto p-1 text-xs"
        >
          全部标为已读
        </Button>
      </DropdownMenuLabel>
      <DropdownMenuSeparator />
      <div v-if="notifications.length === 0" class="p-4 text-center text-sm text-muted-foreground">
        没有通知
      </div>
      <template v-else>
        <DropdownMenuItem
          v-for="notification in notifications"
          :key="notification.id"
          :class="cn('flex cursor-pointer flex-col items-start gap-1 p-4', !notification.read && 'bg-muted/50')"
          @click="markAsRead(notification.id)"
        >
          <div class="flex w-full items-start justify-between gap-2">
            <span class="font-medium">{{ notification.title }}</span>
            <span class="text-xs text-muted-foreground whitespace-nowrap">{{ notification.time }}</span>
          </div>
          <p class="text-xs text-muted-foreground">{{ notification.description }}</p>
        </DropdownMenuItem>
      </template>
      <DropdownMenuSeparator />
      <DropdownMenuItem class="cursor-pointer justify-center">
        <Button variant="ghost" class="w-full" size="sm">
          查看所有通知
        </Button>
      </DropdownMenuItem>
    </DropdownMenuContent>
  </DropdownMenu>
</template>
