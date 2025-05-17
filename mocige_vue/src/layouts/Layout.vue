<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import {
  BookOpen,
  Users,
  BarChart2,
  Settings,
  Menu,
  MessageSquare,
  FileText,
  Puzzle,
  ArrowLeftRight,
  Film,
  GraduationCap,
  Import
} from 'lucide-vue-next'
import TopNav from '@/components/TopNav.vue'
import { useMobileStore } from '@/stores/mobile'
import {Button} from "@/components/ui/button";
import { Sheet, SheetContent, SheetTrigger } from "@/components/ui/sheet"
const mobileStore = useMobileStore()
const route = useRoute()
const open = ref(false)

const navigation = [
  { name: "仪表盘", href: "/", icon: BarChart2 },
  { name: "用户管理", href: "/users", icon: Users },
  { name: "单词管理", href: "/words", icon: BookOpen },
  { name: "学习进度", href: "/learning-progress", icon: GraduationCap },
  { name: "例句管理", href: "/sentences", icon: FileText },
  { name: "媒体管理", href: "/media", icon: Film },
  { name: "词根管理", href: "/roots", icon: BarChart2 },
  { name: "词缀管理", href: "/affixes", icon: Puzzle },
  { name: "同义词管理", href: "/synonyms", icon: ArrowLeftRight },
  { name: "反义词管理", href: "/antonyms", icon: ArrowLeftRight },
  { name: "测试记录", href: "/test-records", icon: BarChart2 },
  { name: "统计分析", href: "/statistics", icon: BarChart2 },
  { name: "用户反馈", href: "/feedback", icon: MessageSquare },
  { name: "导入导出", href: "/import-export", icon: Import },
  { name: "系统设置", href: "/settings", icon: Settings },
]

const isMobile = computed(() => mobileStore.isMobile)

const isActive = (href) => {
  return route.path === href
}

const closeMenu = () => {
  if (isMobile.value) {
    open.value = false
  }
}
</script>

<template>
  <div class="flex h-screen">
    <!-- Mobile Sidebar Drawer -->
    <div v-if="isMobile">
      <Button
        class="fixed left-4 top-4 z-40 lg:hidden outline-none border border-gray-200 rounded-md p-2"
        @click="open = !open"
        aria-label="Toggle navigation menu"
      >
        <Menu class="h-5 w-5" />
      </Button>

      <!-- Mobile Sidebar Overlay -->
      <div
        v-if="open"
        class="fixed inset-0 z-40 bg-black bg-opacity-50"
        @click="open = false"
      ></div>

      <!-- Mobile Sidebar Content -->
      <div
        class="fixed inset-y-0 left-0 z-50 w-72 transform transition-transform duration-300 ease-in-out"
        :class="open ? 'translate-x-0' : '-translate-x-full'"
      >
        <!-- Sidebar Content -->
        <div class="flex grow flex-col gap-y-5 overflow-y-auto bg-gray-100 dark:bg-gray-800 h-full px-6 py-8">
          <div class="flex items-center gap-2">
            <BookOpen class="h-6 w-6" />
            <span class="text-xl font-bold">Mocige Admin</span>
          </div>
          <nav class="flex flex-1 flex-col">
            <ul role="list" class="flex flex-1 flex-col gap-y-2">
              <li v-for="item in navigation" :key="item.name">
                <router-link
                  :to="item.href"
                  class="group flex gap-x-3 rounded-md px-2 py-2 text-sm font-medium"
                  :class="isActive(item.href)
                    ? 'bg-gray-200 dark:bg-gray-700 text-gray-900 dark:text-white'
                    : 'text-gray-500 dark:text-gray-400 hover:bg-gray-200 dark:hover:bg-gray-700 hover:text-gray-900 dark:hover:text-white'"
                  @click="closeMenu"
                >
                  <component :is="item.icon" class="h-5 w-5 shrink-0" aria-hidden="true" />
                  {{ item.name }}
                </router-link>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>

    <!-- Desktop Sidebar -->
    <div v-else class="hidden lg:fixed lg:inset-y-0 lg:z-50 lg:flex lg:w-72 lg:flex-col">
      <div class="flex grow flex-col gap-y-5 overflow-y-auto bg-muted/40 px-6 py-8">
        <div class="flex items-center gap-2">
          <BookOpen class="h-6 w-6" />
          <span class="text-xl font-bold">Mocige Admin</span>
        </div>
        <nav class="flex flex-1 flex-col">
          <ul role="list" class="flex flex-1 flex-col gap-y-2">
            <li v-for="item in navigation" :key="item.name">
              <router-link
                :to="item.href"
                class="group flex gap-x-3 rounded-md px-2 py-2 text-sm font-medium"
                :class="isActive(item.href)
                  ? 'bg-gray-200 dark:bg-gray-700 text-gray-900 dark:text-white'
                  : 'text-gray-500 dark:text-gray-400 hover:bg-gray-200 dark:hover:bg-gray-700 hover:text-gray-900 dark:hover:text-white'"
              >
                <component :is="item.icon" class="h-5 w-5 shrink-0" aria-hidden="true" />
                {{ item.name }}
              </router-link>
            </li>
          </ul>
        </nav>
      </div>
    </div>

    <!-- Main Content Area -->
    <div class="flex flex-1 flex-col lg:pl-72">
      <TopNav />
      <main class="flex-1 p-4 md:p-8">
        <slot></slot>
      </main>
    </div>
  </div>
</template>
<style scoped>

</style>
