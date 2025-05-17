<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center justify-between">
      <h1 class="text-3xl font-bold tracking-tight">反义词管理</h1>
      <router-link to="/antonyms/new">
        <Button>
          <PlusCircle class="mr-2 h-4 w-4" />
          添加反义词
        </Button>
      </router-link>
    </div>

    <Card>
      <CardHeader>
        <CardTitle>反义词管理</CardTitle>
        <CardDescription>管理词汇反义词，增强词汇之间的联系。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex justify-between items-center pb-4">
          <Input placeholder="搜索反义词..." class="max-w-sm" v-model="searchQuery" />
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>单词</TableHead>
              <TableHead>反义词</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="antonym in antonyms" :key="antonym.id">
              <TableCell>{{ antonym.id }}</TableCell>
              <TableCell>
                <router-link :to="`/words?search=${antonym.word}`" class="text-primary hover:underline">
                  {{ antonym.word }}
                </router-link>
              </TableCell>
              <TableCell>
                <router-link :to="`/words?search=${antonym.antonym}`" class="text-primary hover:underline">
                  {{ antonym.antonym }}
                </router-link>
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
                    <DropdownMenuItem @click="openEditDialog(antonym)">
                      <Pencil class="mr-2 h-4 w-4" />
                      编辑
                    </DropdownMenuItem>
                    <DropdownMenuItem
                      class="text-destructive focus:text-destructive"
                      @click="openDeleteDialog(antonym)"
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
            此操作无法撤消。这将永久从数据库中删除此反义词关系。
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

    <!-- 编辑对话框 -->
    <Dialog :open="showEditDialog" @update:open="showEditDialog = $event">
      <DialogContent>
        <DialogHeader>
          <DialogTitle>编辑反义词</DialogTitle>
          <DialogDescription v-if="selectedAntonym">
            更新 "{{ selectedAntonym.word }}" 的反义词
          </DialogDescription>
        </DialogHeader>
        <div class="grid gap-4 py-4" v-if="selectedAntonym">
          <div class="grid gap-2">
            <Label for="word">单词</Label>
            <Input id="word" :value="selectedAntonym.word" disabled />
          </div>
          <div class="grid gap-2">
            <Label for="antonym">反义词</Label>
            <Input
              id="antonym"
              v-model="editedAntonym"
              placeholder="输入反义词"
            />
          </div>
        </div>
        <DialogFooter>
          <Button variant="outline" @click="showEditDialog = false">
            取消
          </Button>
          <Button @click="handleEdit">保存更改</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { PlusCircle, MoreHorizontal, Pencil, Trash } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
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
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog'

const router = useRouter()
const { toast } = useToast()
const searchQuery = ref('')
const showDeleteDialog = ref(false)
const showEditDialog = ref(false)
const selectedAntonym = ref(null)
const editedAntonym = ref('')

// 模拟数据
const antonyms = ref([
  { id: 1, word: "happy", antonym: "sad" },
  { id: 2, word: "big", antonym: "small" },
  { id: 3, word: "fast", antonym: "slow" },
  { id: 4, word: "hot", antonym: "cold" },
  { id: 5, word: "light", antonym: "dark" },
  { id: 6, word: "happy", antonym: "miserable" },
  { id: 7, word: "beautiful", antonym: "ugly" },
  { id: 8, word: "rich", antonym: "poor" },
])

const openDeleteDialog = (antonym) => {
  selectedAntonym.value = antonym
  showDeleteDialog.value = true
}

const confirmDelete = () => {
  // 删除逻辑
  console.log(`删除反义词关系: ${selectedAntonym.value.id}`)
  
  // 从列表中移除
  antonyms.value = antonyms.value.filter(a => a.id !== selectedAntonym.value.id)
  
  toast({
    title: "反义词已删除",
    description: "反义词关系已成功从数据库中删除。",
  })
  
  showDeleteDialog.value = false
  selectedAntonym.value = null
}

const openEditDialog = (antonym) => {
  selectedAntonym.value = antonym
  editedAntonym.value = antonym.antonym
  showEditDialog.value = true
}

const handleEdit = () => {
  // 编辑逻辑
  console.log(`更新反义词关系 ${selectedAntonym.value.id}: ${selectedAntonym.value.word} -> ${editedAntonym.value}`)
  
  // 更新列表中的项
  const index = antonyms.value.findIndex(a => a.id === selectedAntonym.value.id)
  if (index !== -1) {
    antonyms.value[index] = {
      ...antonyms.value[index],
      antonym: editedAntonym.value
    }
  }
  
  toast({
    title: "反义词已更新",
    description: "反义词关系已成功更新。",
  })
  
  showEditDialog.value = false
}
</script>
