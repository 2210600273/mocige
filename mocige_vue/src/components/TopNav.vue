<script setup>
import { ref } from 'vue'
import { User,Settings,Sun,Moon,LogOut } from 'lucide-vue-next'
import { useRouter } from 'vue-router'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import GlobalSearch from "@/components/GlobalSearch.vue";
import Notification from "@/components/Notification.vue";
import {Button} from "@/components/ui/button";
import {Avatar, AvatarFallback, AvatarImage} from "@/components/ui/avatar";

const router = useRouter()
const searchQuery = ref('')
const isSearchOpen = ref(false)
const isNotificationsOpen = ref(false)
const setTheme = (theme) => {
}
const notifications = [
  {
    id: 1,
    title: '系统通知',
    description: '系统维护将于今晚22:00-23:00进行',
    time: '10分钟前',
    isRead: false
  },
  {
    id: 2,
    title: '数据更新',
    description: '新的单词数据已导入',
    time: '30分钟前',
    isRead: true
  },
  {
    id: 3,
    title: '用户反馈',
    description: '有3条新的用户反馈需要处理',
    time: '1小时前',
    isRead: false
  }
]

const toggleSearch = () => {
  isSearchOpen.value = !isSearchOpen.value
  if (isNotificationsOpen.value) {
    isNotificationsOpen.value = false
  }
}

const toggleNotifications = () => {
  isNotificationsOpen.value = !isNotificationsOpen.value
  if (isSearchOpen.value) {
    isSearchOpen.value = false
  }
}

const goToProfile = () => {
  router.push('/profile')
}
const username = ref('username')
</script>

<template>
  <div class="flex h-16 items-center gap-4 border-b bg-background px-4 lg:px-6">
    <div class="flex-1"/>
    <div class="flex items-center gap-2">
      <GlobalSearch/>
      <Notification/>
      <DropdownMenu>
        <DropdownMenuTrigger as-child>
          <Button variant="ghost" class="relative h-10 w-10 rounded-full">
            <Avatar class="h-10 w-10">
              <AvatarImage src="" alt=""/>
              <AvatarFallback>{{ username.charAt(0) }}</AvatarFallback>
            </Avatar>
          </Button>
        </DropdownMenuTrigger>
        <DropdownMenuContent class="w-56" align="end">
          <div class="flex flex-col space-y-1 p-2">
            <p class="text-sm font-medium">{{ username }}</p>
            <p class="text-xs text-muted-foreground">admin@example.com</p>
          </div>
          <DropdownMenuSeparator/>
          <DropdownMenuItem as-child>
            <RouterLink to="/profile">
              <User class="mr-2 h-4 w-4"/>
              <span>个人资料</span>
            </RouterLink>
          </DropdownMenuItem>
          <DropdownMenuItem as-child>
            <RouterLink to="/settings">
              <Settings class="mr-2 h-4 w-4"/>
              <span>设置</span>
            </RouterLink>
          </DropdownMenuItem>
          <DropdownMenuSeparator/>
          <DropdownMenuItem @click="setTheme('light')">
            <Sun class="mr-2 h-4 w-4"/>
            <span>浅色模式</span>
          </DropdownMenuItem>
          <DropdownMenuItem @click="setTheme('dark')">
            <Moon class="mr-2 h-4 w-4"/>
            <span>深色模式</span>
          </DropdownMenuItem>
          <DropdownMenuSeparator/>
          <DropdownMenuItem as-child>
            <RouterLink to="/login">
              <LogOut class="mr-2 h-4 w-4"/>
              <span>退出登录</span>
            </RouterLink>
          </DropdownMenuItem>
        </DropdownMenuContent>
      </DropdownMenu>
    </div>
  </div>
</template>
<!--<template>-->
<!--  <div class="sticky top-0 z-30 flex h-16 items-center gap-x-4 border-b border-gray-200 bg-white dark:bg-gray-800 dark:border-gray-700 px-4 shadow-sm sm:gap-x-6 sm:px-6 lg:px-8">-->
<!--    <div class="flex flex-1 gap-x-4 self-stretch lg:gap-x-6">-->
<!--      &lt;!&ndash; Search &ndash;&gt;-->
<!--      <div class="flex flex-1 items-center">-->
<!--        <button-->
<!--          @click="toggleSearch"-->
<!--          class="group flex h-8 w-8 items-center justify-center rounded-full hover:bg-gray-100 dark:hover:bg-gray-700"-->
<!--        >-->
<!--          <Search class="h-5 w-5 text-gray-500 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white" />-->
<!--          <span class="sr-only">Search</span>-->
<!--        </button>-->

<!--        &lt;!&ndash; Search Overlay (conditionally shown) &ndash;&gt;-->
<!--        <div v-if="isSearchOpen" class="fixed inset-0 z-50">-->
<!--          &lt;!&ndash; Backdrop &ndash;&gt;-->
<!--          <div class="fixed inset-0 bg-gray-500 bg-opacity-25" @click="isSearchOpen = false"></div>-->

<!--          &lt;!&ndash; Search Dialog &ndash;&gt;-->
<!--          <div class="fixed inset-x-0 top-0 z-50 bg-white dark:bg-gray-800 p-4 shadow-lg">-->
<!--            <div class="relative max-w-3xl mx-auto">-->
<!--              <div class="flex items-center border-b border-gray-200 dark:border-gray-700 pb-4">-->
<!--                <Search class="h-5 w-5 text-gray-500 dark:text-gray-400 mr-3" />-->
<!--                <input-->
<!--                  v-model="searchQuery"-->
<!--                  class="flex-1 bg-transparent border-0 focus:ring-0 outline-none"-->
<!--                  placeholder="搜索..."-->
<!--                  type="search"-->
<!--                  @keyup.enter="isSearchOpen = false"-->
<!--                />-->
<!--                <button-->
<!--                  class="text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-200"-->
<!--                  @click="isSearchOpen = false"-->
<!--                >-->
<!--                  ESC-->
<!--                </button>-->
<!--              </div>-->

<!--              &lt;!&ndash; Search Results would go here &ndash;&gt;-->
<!--              <div class="mt-4">-->
<!--                <div v-if="searchQuery" class="py-2">-->
<!--                  搜索 "{{ searchQuery }}" 的结果-->
<!--                </div>-->
<!--                <div v-else class="text-gray-500 dark:text-gray-400 text-sm py-4">-->
<!--                  开始键入以搜索-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->

<!--      <div class="flex items-center gap-x-4 lg:gap-x-6">-->
<!--        &lt;!&ndash; Notifications &ndash;&gt;-->
<!--        <div class="relative">-->
<!--          <button-->
<!--            @click="toggleNotifications"-->
<!--            class="group flex h-8 w-8 items-center justify-center rounded-full hover:bg-gray-100 dark:hover:bg-gray-700"-->
<!--          >-->
<!--            <Bell class="h-5 w-5 text-gray-500 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white" />-->
<!--            <span class="sr-only">View notifications</span>-->

<!--            &lt;!&ndash; Notification Badge &ndash;&gt;-->
<!--            <span class="absolute -top-0.5 -right-0.5 h-4 w-4 rounded-full bg-red-500 flex items-center justify-center text-xs text-white">-->
<!--              2-->
<!--            </span>-->
<!--          </button>-->

<!--          &lt;!&ndash; Notifications Dropdown &ndash;&gt;-->
<!--          <div-->
<!--            v-if="isNotificationsOpen"-->
<!--            class="absolute right-0 mt-2 w-80 origin-top-right rounded-md bg-white dark:bg-gray-800 py-1 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none"-->
<!--          >-->
<!--            <div class="px-4 py-2 border-b border-gray-200 dark:border-gray-700">-->
<!--              <div class="flex justify-between items-center">-->
<!--                <h3 class="font-semibold">通知</h3>-->
<!--                <button class="text-sm text-blue-600 dark:text-blue-400">-->
<!--                  全部标为已读-->
<!--                </button>-->
<!--              </div>-->
<!--            </div>-->

<!--            <div class="max-h-96 overflow-y-auto">-->
<!--              <div-->
<!--                v-for="notification in notifications"-->
<!--                :key="notification.id"-->
<!--                class="px-4 py-3 hover:bg-gray-50 dark:hover:bg-gray-700 cursor-pointer"-->
<!--                :class="notification.isRead ? 'opacity-70' : ''"-->
<!--              >-->
<!--                <div class="flex justify-between">-->
<!--                  <p class="font-medium text-sm" :class="!notification.isRead ? 'text-blue-600 dark:text-blue-400' : ''">-->
<!--                    {{ notification.title }}-->
<!--                  </p>-->
<!--                  <span class="text-xs text-gray-500 dark:text-gray-400">{{ notification.time }}</span>-->
<!--                </div>-->
<!--                <p class="text-sm text-gray-700 dark:text-gray-300 mt-1">-->
<!--                  {{ notification.description }}-->
<!--                </p>-->
<!--              </div>-->
<!--            </div>-->

<!--            <div class="px-4 py-2 border-t border-gray-200 dark:border-gray-700">-->
<!--              <router-link-->
<!--                to="/notifications"-->
<!--                class="block text-center text-sm text-blue-600 dark:text-blue-400 hover:text-blue-800 dark:hover:text-blue-300"-->
<!--                @click="isNotificationsOpen = false"-->
<!--              >-->
<!--                查看全部通知-->
<!--              </router-link>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->

<!--        &lt;!&ndash; Profile dropdown &ndash;&gt;-->
<!--        <div class="relative flex-shrink-0">-->
<!--          <button-->
<!--            @click="goToProfile"-->
<!--            class="flex rounded-full bg-gray-200 dark:bg-gray-700 h-8 w-8 items-center justify-center text-sm focus:outline-none"-->
<!--          >-->
<!--            <User class="h-5 w-5 text-gray-700 dark:text-gray-300" />-->
<!--            <span class="sr-only">User menu</span>-->
<!--          </button>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->
