<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center justify-between">
      <h1 class="text-3xl font-bold tracking-tight">例句管理</h1>
      <RouterLink to="/sentences/new">
        <Button>
          <PlusCircle class="mr-2 h-4 w-4" />
          添加例句
        </Button>
      </RouterLink>
    </div>

    <Card>
      <CardHeader>
        <CardTitle>例句示例</CardTitle>
        <CardDescription>管理用于词汇学习的示例句子。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex justify-between items-center pb-4">
          <Input placeholder="搜索例句..." class="max-w-sm" v-model="searchQuery" />
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead class="w-[60%]">例句</TableHead>
              <TableHead>相关单词</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="sentence in sentences" :key="sentence.id">
              <TableCell>{{ sentence.id }}</TableCell>
              <TableCell class="font-medium">{{ sentence.sentence }}</TableCell>
              <TableCell>
                <span 
                  v-for="word in sentence.relatedWords" 
                  :key="word" 
                  class="inline-block mr-1 mb-1 px-2 py-1 text-xs bg-secondary rounded"
                >
                  {{ word }}
                </span>
              </TableCell>
              <TableCell>
                <DropdownMenu>
                  <DropdownMenuTrigger as-child>
                    <Button variant="ghost" class="h-8 w-8 p-0">
                      <span class="sr-only">打开菜单</span>
                      <MoreHorizontal class="h-4 w-4" />
                    </Button>
                  </DropdownMenuTrigger>
                  <DropdownMenuContent align="end">
                    <DropdownMenuItem @click="router.push(`/sentences/${sentence.id}`)">
                      <Eye class="mr-2 h-4 w-4" />
                      查看
                    </DropdownMenuItem>
                    <DropdownMenuItem @click="router.push(`/sentences/${sentence.id}/edit`)">
                      <Pencil class="mr-2 h-4 w-4" />
                      编辑
                    </DropdownMenuItem>
                    <DropdownMenuItem
                      class="text-destructive focus:text-destructive"
                      @click="handleDeleteSentence(sentence)"
                    >
                      <Trash class="mr-2 h-4 w-4" />
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
            此操作无法撤消。这将永久删除此例句及其所有关联数据。
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
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { 
  PlusCircle, 
  MoreHorizontal, 
  Eye, 
  Pencil, 
  Trash 
} from 'lucide-vue-next'
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

const router = useRouter()
const { toast } = useToast()
const searchQuery = ref('')
const showDeleteDialog = ref(false)
const sentenceToDelete = ref(null)

// 模拟数据
const sentences = ref([
  {
    id: 1,
    sentence: "Having a rich vocabulary is essential for effective communication.",
    relatedWords: ["vocabulary", "communication"],
  },
  {
    id: 2,
    sentence: "The politician gave an ambiguous answer to avoid committing to a position.",
    relatedWords: ["ambiguous", "commit"],
  },
  {
    id: 3,
    sentence: "His bright red shirt was conspicuous in the crowd of people wearing black.",
    relatedWords: ["conspicuous"],
  },
  {
    id: 4,
    sentence: "She is meticulous about keeping her financial records organized.",
    relatedWords: ["meticulous", "organize"],
  },
  {
    id: 5,
    sentence: "Social media platforms have proliferated over the last decade.",
    relatedWords: ["proliferate"],
  },
])

const handleDeleteSentence = (sentence) => {
  sentenceToDelete.value = sentence
  showDeleteDialog.value = true
}

const confirmDelete = () => {
  // 删除逻辑
  console.log(`删除例句: ${sentenceToDelete.value.id}`)

  // 从列表中移除
  sentences.value = sentences.value.filter(s => s.id !== sentenceToDelete.value.id)

  toast({
    title: "例句已删除",
    description: "例句已成功从数据库中删除。",
  })

  showDeleteDialog.value = false
  sentenceToDelete.value = null
}
</script>
