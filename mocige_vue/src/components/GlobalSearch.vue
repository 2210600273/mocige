<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import { Search } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import {
  CommandDialog,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
  CommandList,
} from '@/components/ui/command'

const router = useRouter()
const open = ref(false)
const query = ref('')

onMounted(() => {
  const down = (e) => {
    if (e.key === 'k' && (e.metaKey || e.ctrlKey)) {
      e.preventDefault()
      open.value = !open.value
    }
  }

  document.addEventListener('keydown', down)
  return () => document.removeEventListener('keydown', down)
})

const runCommand = (command) => {
  open.value = false
  command()
}

// 模拟搜索结果
const searchResults = computed(() => {
  if (!query.value) return []

  const lowerQuery = query.value.toLowerCase()

  const words = [
    { id: 1, title: 'vocabulary', type: 'word' },
    { id: 2, title: 'ambiguous', type: 'word' },
    { id: 3, title: 'conspicuous', type: 'word' },
  ]

  const users = [
    { id: 1, title: 'user1@example.com', type: 'user' },
    { id: 2, title: 'user2@example.com', type: 'user' },
  ]

  const sentences = [
    { id: 1, title: 'Having a rich vocabulary is essential for effective communication.', type: 'sentence' },
    { id: 2, title: 'The politician gave an ambiguous answer to avoid committing to a position.', type: 'sentence' },
  ]

  return [
    ...words.filter((item) => item.title.toLowerCase().includes(lowerQuery)),
    ...users.filter((item) => item.title.toLowerCase().includes(lowerQuery)),
    ...sentences.filter((item) => item.title.toLowerCase().includes(lowerQuery)),
  ]
})

const hasWordResults = computed(() => searchResults.value.some((item) => item.type === 'word'))
const hasUserResults = computed(() => searchResults.value.some((item) => item.type === 'user'))
const hasSentenceResults = computed(() => searchResults.value.some((item) => item.type === 'sentence'))

const wordResults = computed(() => searchResults.value.filter((item) => item.type === 'word'))
const userResults = computed(() => searchResults.value.filter((item) => item.type === 'user'))
const sentenceResults = computed(() => searchResults.value.filter((item) => item.type === 'sentence'))
</script>

<template>
  <div>
    <Button
      variant="outline"
      class="relative h-9 w-9 p-0 xl:h-10 xl:w-60 xl:justify-start xl:px-3 xl:py-2"
      @click="open = true"
    >
      <Search class="h-4 w-4 xl:mr-2" aria-hidden="true" />
      <span class="hidden xl:inline-flex">搜索...</span>
      <span class="sr-only">搜索</span>
      <kbd class="pointer-events-none absolute right-1.5 top-2 hidden h-6 select-none items-center gap-1 rounded border bg-muted px-1.5 font-mono text-xs font-medium opacity-100 xl:flex">
        <span class="text-xs">⌘</span>K
      </kbd>
    </Button>
    <CommandDialog :open="open" @update:open="open = $event">
      <CommandInput placeholder="搜索所有内容..." v-model="query" />
      <CommandList>
        <CommandEmpty>没有找到结果</CommandEmpty>
        <template v-if="searchResults.length > 0">
          <CommandGroup v-if="hasWordResults" heading="单词">
            <CommandItem
              v-for="item in wordResults"
              :key="`${item.type}-${item.id}`"
              @select="runCommand(() => router.push(`/words/${item.id}`))"
            >
              {{ item.title }}
            </CommandItem>
          </CommandGroup>
          <CommandGroup v-if="hasUserResults" heading="用户">
            <CommandItem
              v-for="item in userResults"
              :key="`${item.type}-${item.id}`"
              @select="runCommand(() => router.push(`/users/${item.id}`))"
            >
              {{ item.title }}
            </CommandItem>
          </CommandGroup>
          <CommandGroup v-if="hasSentenceResults" heading="例句">
            <CommandItem
              v-for="item in sentenceResults"
              :key="`${item.type}-${item.id}`"
              @select="runCommand(() => router.push(`/sentences/${item.id}`))"
            >
              {{ item.title.length > 50 ? `${item.title.substring(0, 50)}...` : item.title }}
            </CommandItem>
          </CommandGroup>
        </template>
        <CommandGroup heading="页面">
          <CommandItem @select="runCommand(() => router.push('/'))">
            仪表盘
          </CommandItem>
          <CommandItem @select="runCommand(() => router.push('/users'))">
            用户管理
          </CommandItem>
          <CommandItem @select="runCommand(() => router.push('/words'))">
            单词管理
          </CommandItem>
          <CommandItem @select="runCommand(() => router.push('/settings'))">
            系统设置
          </CommandItem>
        </CommandGroup>
      </CommandList>
    </CommandDialog>
  </div>
</template>

<style scoped>
/* 可以在这里添加组件特定的样式 */
</style>
