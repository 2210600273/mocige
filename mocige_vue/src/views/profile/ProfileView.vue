<script setup>
import { ref } from 'vue'
import { User, Mail, Phone, Shield } from 'lucide-vue-next'
import {Button} from '@/components/ui/Button'

const user = ref({
  id: 1,
  name: '管理员',
  email: 'admin@mocige.com',
  phone: '138****1234',
  role: '超级管理员',
  joined: '2023-01-15',
  lastActive: '2023-05-28T08:45:00'
})

const isEditMode = ref(false)
const formData = ref({
  name: user.value.name,
  email: user.value.email,
  phone: user.value.phone
})

const toggleEditMode = () => {
  if (isEditMode.value) {
    // Reset form data if cancelling edit
    formData.value = {
      name: user.value.name,
      email: user.value.email,
      phone: user.value.phone
    }
  }
  isEditMode.value = !isEditMode.value
}

const saveProfile = () => {
  // Update user data (in a real app would call an API)
  user.value = {
    ...user.value,
    name: formData.value.name,
    email: formData.value.email,
    phone: formData.value.phone
  }

  // Exit edit mode
  isEditMode.value = false
}
</script>

<template>
  <div>
    <div class="flex items-center justify-between mb-6">
      <h1 class="text-2xl font-bold">个人资料</h1>
      <Button
        :variant="isEditMode ? 'destructive' : 'outline'"
        @click="toggleEditMode"
      >
        {{ isEditMode ? '取消' : '编辑资料' }}
      </Button>
    </div>

    <div class="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden">
      <!-- Header -->
      <div class="bg-gray-50 dark:bg-gray-700 p-6 border-b border-gray-200 dark:border-gray-600">
        <div class="flex items-center">
          <div class="h-20 w-20 rounded-full bg-gray-200 dark:bg-gray-600 flex items-center justify-center text-gray-500 dark:text-gray-400">
            <User class="h-10 w-10" />
          </div>
          <div class="ml-6">
            <h2 class="text-xl font-semibold">{{ user.name }}</h2>
            <p class="text-sm text-gray-500 dark:text-gray-400 mt-1">{{ user.role }}</p>
            <p class="text-xs text-gray-500 dark:text-gray-400 mt-1">
              加入时间: {{ user.joined }}
            </p>
          </div>
        </div>
      </div>

      <!-- Profile Details -->
      <div class="p-6">
        <form v-if="isEditMode" @submit.prevent="saveProfile">
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium mb-1" for="name">姓名</label>
              <div class="relative">
                <User class="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-gray-400" />
                <input
                  id="name"
                  v-model="formData.name"
                  type="text"
                  class="w-full pl-10 p-2 rounded-md border border-gray-300 dark:border-gray-600 dark:bg-gray-700"
                  required
                />
              </div>
            </div>

            <div>
              <label class="block text-sm font-medium mb-1" for="email">邮箱</label>
              <div class="relative">
                <Mail class="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-gray-400" />
                <input
                  id="email"
                  v-model="formData.email"
                  type="email"
                  class="w-full pl-10 p-2 rounded-md border border-gray-300 dark:border-gray-600 dark:bg-gray-700"
                  required
                />
              </div>
            </div>

            <div>
              <label class="block text-sm font-medium mb-1" for="phone">电话</label>
              <div class="relative">
                <Phone class="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-gray-400" />
                <input
                  id="phone"
                  v-model="formData.phone"
                  type="text"
                  class="w-full pl-10 p-2 rounded-md border border-gray-300 dark:border-gray-600 dark:bg-gray-700"
                />
              </div>
            </div>

            <div class="pt-4">
              <Button type="submit">保存更改</Button>
              <Button variant="outline" class="ml-2" @click="toggleEditMode" type="button">
                取消
              </Button>
            </div>
          </div>
        </form>

        <div v-else class="space-y-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <h3 class="text-sm font-medium text-gray-500 dark:text-gray-400 mb-2">个人信息</h3>
              <div class="space-y-3">
                <div class="flex items-center">
                  <User class="h-5 w-5 text-gray-400 mr-3" />
                  <div>
                    <p class="text-xs text-gray-500 dark:text-gray-400">姓名</p>
                    <p>{{ user.name }}</p>
                  </div>
                </div>

                <div class="flex items-center">
                  <Mail class="h-5 w-5 text-gray-400 mr-3" />
                  <div>
                    <p class="text-xs text-gray-500 dark:text-gray-400">邮箱</p>
                    <p>{{ user.email }}</p>
                  </div>
                </div>

                <div class="flex items-center">
                  <Phone class="h-5 w-5 text-gray-400 mr-3" />
                  <div>
                    <p class="text-xs text-gray-500 dark:text-gray-400">电话</p>
                    <p>{{ user.phone }}</p>
                  </div>
                </div>
              </div>
            </div>

            <div>
              <h3 class="text-sm font-medium text-gray-500 dark:text-gray-400 mb-2">账户信息</h3>
              <div class="space-y-3">
                <div class="flex items-center">
                  <Shield class="h-5 w-5 text-gray-400 mr-3" />
                  <div>
                    <p class="text-xs text-gray-500 dark:text-gray-400">角色</p>
                    <p>{{ user.role }}</p>
                  </div>
                </div>

                <div class="flex items-start">
                  <div class="bg-green-100 dark:bg-green-900/30 text-green-800 dark:text-green-200 text-xs p-1 rounded mt-1">
                    活跃
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="border-t border-gray-200 dark:border-gray-700 pt-6">
            <h3 class="text-sm font-medium text-gray-500 dark:text-gray-400 mb-2">安全选项</h3>
            <div class="space-y-3">
              <Button variant="outline" size="sm">
                修改密码
              </Button>
              <Button variant="outline" size="sm" class="ml-2">
                启用两步验证
              </Button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
