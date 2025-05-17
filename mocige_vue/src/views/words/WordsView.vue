<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center justify-between">
      <h1 class="text-3xl font-bold tracking-tight">单词管理</h1>
      <RouterLink to="/words/new">
        <Button>
          <PlusCircleIcon class="mr-2 h-4 w-4" />
          添加单词
        </Button>
      </RouterLink>
    </div>

    <Card>
      <CardHeader>
        <CardTitle>词汇管理</CardTitle>
        <CardDescription>添加、编辑和删除词汇数据库中的单词。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex justify-between items-center pb-4">
          <Input placeholder="搜索单词..." class="max-w-sm" v-model="searchQuery" />
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>单词</TableHead>
              <TableHead>发音</TableHead>
              <TableHead>词性</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="word in words" :key="word.id">
              <TableCell>{{ word.id }}</TableCell>
              <TableCell>{{ word.word }}</TableCell>
              <TableCell>{{ word.pronunciation }}</TableCell>
              <TableCell>{{ word.partOfSpeech }}</TableCell>
              <TableCell>
                <DropdownMenu>
                  <DropdownMenuTrigger as-child>
                    <Button variant="ghost" class="h-8 w-8 p-0">
                      <span class="sr-only">打开菜单</span>
                      <MoreHorizontalIcon class="h-4 w-4"/>
                    </Button>
                  </DropdownMenuTrigger>
                  <DropdownMenuContent align="end">
                    <DropdownMenuItem as-child>
                      <RouterLink :to="'/words/' + word.id">
                        <EyeIcon class="mr-2 h-4 w-4"/>
                        查看
                      </RouterLink>
                    </DropdownMenuItem>
                    <DropdownMenuItem as-child>
                      <RouterLink :to="'/words/' + word.id + '/edit'">
                        <PencilIcon class="mr-2 h-4 w-4"/>
                        编辑
                      </RouterLink>
                    </DropdownMenuItem>
                    <DropdownMenuItem
                        class="text-destructive focus:text-destructive"
                        @click="handleDeleteWord(word)"
                    >
                      <TrashIcon class="mr-2 h-4 w-4"/>
                      删除
                    </DropdownMenuItem>
                  </DropdownMenuContent>
                </DropdownMenu>
              </TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </CardContent>
    </Card>

    <!-- 删除确认对话框 -->
    <AlertDialog :open="showDeleteDialog" @update:open="showDeleteDialog = $event">
      <AlertDialogContent>
        <AlertDialogHeader>
          <AlertDialogTitle>确定要删除吗？</AlertDialogTitle>
          <AlertDialogDescription>
            此操作无法撤消。这将永久删除此单词及其所有相关数据。
          </AlertDialogDescription>
        </AlertDialogHeader>
        <AlertDialogFooter>
          <AlertDialogCancel>取消</AlertDialogCancel>
          <AlertDialogAction
            @click="confirmDelete"
            class="bg-destructive text-destructive-foreground hover:bg-destructive/90"
          >
            删除
          </AlertDialogAction>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  </div>
</template>

<script setup>
import {defineComponent, ref} from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from '@/components/ui/dropdown-menu'

import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from '@/components/ui/alert-dialog'
import { PlusCircle as PlusCircleIcon ,MoreHorizontalIcon,EyeIcon,PencilIcon,TrashIcon} from 'lucide-vue-next'
// 单词操作组件


const router = useRouter()
const { toast } = useToast()
const searchQuery = ref('')
const showDeleteDialog = ref(false)
const wordToDelete = ref(null)

// 模拟数据
const words = ref([
  { id: 1, word: "vocabulary", pronunciation: "/vəˈkæb.jʊ.lər.i/", partOfSpeech: "noun" },
  { id: 2, word: "ambiguous", pronunciation: "/æmˈbɪɡ.ju.əs/", partOfSpeech: "adjective" },
  { id: 3, word: "conspicuous", pronunciation: "/kənˈspɪk.ju.əs/", partOfSpeech: "adjective" },
  { id: 4, word: "meticulous", pronunciation: "/məˈtɪk.jə.ləs/", partOfSpeech: "adjective" },
  { id: 5, word: "proliferate", pronunciation: "/prəˈlɪf.ə.reɪt/", partOfSpeech: "verb" },
])

const handleDeleteWord = (word) => {
  wordToDelete.value = word
  showDeleteDialog.value = true
}

const confirmDelete = () => {
  // 删除逻辑
  console.log(`删除单词: ${wordToDelete.value.id}`)

  // 从列表中移除
  words.value = words.value.filter(w => w.id !== wordToDelete.value.id)

  toast({
    title: "单词已删除",
    description: `单词 "${wordToDelete.value.word}" 已成功删除。`,
  })

  showDeleteDialog.value = false
  wordToDelete.value = null
}
</script>
