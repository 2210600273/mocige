<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center justify-between">
      <h1 class="text-3xl font-bold tracking-tight">词根管理</h1>
      <router-link to="/roots/new">
        <Button>
          <PlusCircle class="mr-2 h-4 w-4" />
          添加词根
        </Button>
      </router-link>
    </div>

    <Card>
      <CardHeader>
        <CardTitle>词根</CardTitle>
        <CardDescription>管理词根及其定义。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex justify-between items-center pb-4">
          <Input placeholder="搜索词根..." class="max-w-sm" v-model="searchQuery" />
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>词根</TableHead>
              <TableHead>定义</TableHead>
              <TableHead>相关单词</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="root in roots" :key="root.id">
              <TableCell>{{ root.id }}</TableCell>
              <TableCell>{{ root.root }}</TableCell>
              <TableCell>{{ root.definition }}</TableCell>
              <TableCell>
                <span 
                  v-for="word in root.relatedWords" 
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
                    <DropdownMenuItem @click="router.push(`/roots/${root.id}`)">
                      <Eye class="mr-2 h-4 w-4" />
                      查看
                    </DropdownMenuItem>
                    <DropdownMenuItem @click="router.push(`/roots/${root.id}/edit`)">
                      <Pencil class="mr-2 h-4 w-4" />
                      编辑
                    </DropdownMenuItem>
                    <DropdownMenuItem
                      class="text-destructive focus:text-destructive"
                      @click="handleDeleteRoot(root)"
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
            此操作无法撤消。这将永久删除此词根及其在数据库中的关联。
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
import { PlusCircle, MoreHorizontal, Eye, Pencil, Trash } from 'lucide-vue-next'
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
const rootToDelete = ref(null)

// 模拟数据
const roots = ref([
  { id: 1, root: "voc", definition: "Voice, call", relatedWords: ["vocabulary", "vocal", "advocate"] },
  { id: 2, root: "dict", definition: "Say, speak", relatedWords: ["dictate", "predict", "verdict"] },
  { id: 3, root: "spec", definition: "See, look", relatedWords: ["spectacle", "inspect", "perspective"] },
  { id: 4, root: "port", definition: "Carry", relatedWords: ["transport", "export", "portable"] },
  { id: 5, root: "ject", definition: "Throw", relatedWords: ["project", "inject", "reject"] },
])

const handleDeleteRoot = (root) => {
  rootToDelete.value = root
  showDeleteDialog.value = true
}

const confirmDelete = () => {
  // 删除逻辑
  console.log(`删除词根: ${rootToDelete.value.id}`)

  // 从列表中移除
  roots.value = roots.value.filter(r => r.id !== rootToDelete.value.id)

  toast({
    title: "词根已删除",
    description: `词根 "${rootToDelete.value.root}" 已成功删除。`,
  })

  showDeleteDialog.value = false
  rootToDelete.value = null
}
</script>
