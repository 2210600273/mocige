<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center justify-between">
      <h1 class="text-3xl font-bold tracking-tight">同义词管理</h1>
      <router-link to="/synonyms/new">
        <Button>
          <PlusCircle class="mr-2 h-4 w-4" />
          添加同义词
        </Button>
      </router-link>
    </div>

    <Card>
      <CardHeader>
        <CardTitle>同义词管理</CardTitle>
        <CardDescription>管理词汇同义词，增强词汇之间的联系。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex justify-between items-center pb-4">
          <Input placeholder="搜索同义词..." class="max-w-sm" v-model="searchQuery" />
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>单词</TableHead>
              <TableHead>同义词</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="synonym in synonyms" :key="synonym.id">
              <TableCell>{{ synonym.id }}</TableCell>
              <TableCell>
                <router-link :to="`/words?search=${synonym.word}`" class="text-primary hover:underline">
                  {{ synonym.word }}
                </router-link>
              </TableCell>
              <TableCell>
                <router-link :to="`/words?search=${synonym.synonym}`" class="text-primary hover:underline">
                  {{ synonym.synonym }}
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
                    <DropdownMenuItem @click="openEditDialog(synonym)">
                      <Pencil class="mr-2 h-4 w-4" />
                      编辑
                    </DropdownMenuItem>
                    <DropdownMenuItem
                      class="text-destructive focus:text-destructive"
                      @click="openDeleteDialog(synonym)"
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
            此操作无法撤消。这将永久从数据库中删除此同义词关系。
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
          <DialogTitle>编辑同义词</DialogTitle>
          <DialogDescription v-if="selectedSynonym">
            更新 "{{ selectedSynonym.word }}" 的同义词
          </DialogDescription>
        </DialogHeader>
        <div class="grid gap-4 py-4" v-if="selectedSynonym">
          <div class="grid gap-2">
            <Label for="word">单词</Label>
            <Input id="word" :value="selectedSynonym.word" disabled />
          </div>
          <div class="grid gap-2">
            <Label for="synonym">同义词</Label>
            <Input
              id="synonym"
              v-model="editedSynonym"
              placeholder="输入同义词"
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
const selectedSynonym = ref(null)
const editedSynonym = ref('')

// 模拟数据
const synonyms = ref([
  { id: 1, word: "happy", synonym: "joyful" },
  { id: 2, word: "sad", synonym: "unhappy" },
  { id: 3, word: "big", synonym: "large" },
  { id: 4, word: "small", synonym: "tiny" },
  { id: 5, word: "fast", synonym: "quick" },
  { id: 6, word: "happy", synonym: "pleased" },
  { id: 7, word: "happy", synonym: "delighted" },
  { id: 8, word: "sad", synonym: "miserable" },
])

const openDeleteDialog = (synonym) => {
  selectedSynonym.value = synonym
  showDeleteDialog.value = true
}

const confirmDelete = () => {
  // 删除逻辑
  console.log(`删除同义词关系: ${selectedSynonym.value.id}`)
  
  // 从列表中移除
  synonyms.value = synonyms.value.filter(s => s.id !== selectedSynonym.value.id)
  
  toast({
    title: "同义词已删除",
    description: "同义词关系已成功从数据库中删除。",
  })
  
  showDeleteDialog.value = false
  selectedSynonym.value = null
}

const openEditDialog = (synonym) => {
  selectedSynonym.value = synonym
  editedSynonym.value = synonym.synonym
  showEditDialog.value = true
}

const handleEdit = () => {
  // 编辑逻辑
  console.log(`更新同义词关系 ${selectedSynonym.value.id}: ${selectedSynonym.value.word} -> ${editedSynonym.value}`)
  
  // 更新列表中的项
  const index = synonyms.value.findIndex(s => s.id === selectedSynonym.value.id)
  if (index !== -1) {
    synonyms.value[index] = {
      ...synonyms.value[index],
      synonym: editedSynonym.value
    }
  }
  
  toast({
    title: "同义词已更新",
    description: "同义词关系已成功更新。",
  })
  
  showEditDialog.value = false
}
</script>
