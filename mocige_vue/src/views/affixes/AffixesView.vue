<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center justify-between">
      <h1 class="text-3xl font-bold tracking-tight">词缀管理</h1>
      <router-link to="/affixes/new">
        <Button>
          <PlusCircle class="mr-2 h-4 w-4" />
          添加词缀
        </Button>
      </router-link>
    </div>

    <Card>
      <CardHeader>
        <CardTitle>词缀</CardTitle>
        <CardDescription>管理前缀、后缀及其定义。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex justify-between items-center pb-4">
          <Input placeholder="搜索词缀..." class="max-w-sm" v-model="searchQuery" />
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>词缀</TableHead>
              <TableHead>定义</TableHead>
              <TableHead>类型</TableHead>
              <TableHead>示例</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="affix in affixes" :key="affix.id">
              <TableCell>{{ affix.id }}</TableCell>
              <TableCell>{{ affix.affix }}</TableCell>
              <TableCell>{{ affix.definition }}</TableCell>
              <TableCell>{{ affix.type === 'Prefix' ? '前缀' : '后缀' }}</TableCell>
              <TableCell>
                <span 
                  v-for="example in affix.examples" 
                  :key="example" 
                  class="inline-block mr-1 mb-1 px-2 py-1 text-xs bg-secondary rounded"
                >
                  {{ example }}
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
                    <DropdownMenuItem @click="router.push(`/affixes/${affix.id}`)">
                      <Eye class="mr-2 h-4 w-4" />
                      查看
                    </DropdownMenuItem>
                    <DropdownMenuItem @click="router.push(`/affixes/${affix.id}/edit`)">
                      <Pencil class="mr-2 h-4 w-4" />
                      编辑
                    </DropdownMenuItem>
                    <DropdownMenuItem
                      class="text-destructive focus:text-destructive"
                      @click="handleDeleteAffix(affix)"
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
            此操作无法撤消。这将永久删除此词缀及其在数据库中的关联。
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
const affixToDelete = ref(null)

// 模拟数据
const affixes = ref([
  {
    id: 1,
    affix: "pre-",
    definition: "Before",
    type: "Prefix",
    examples: ["preview", "prepaid", "preposition"],
  },
  {
    id: 2,
    affix: "un-",
    definition: "Not, opposite",
    type: "Prefix",
    examples: ["unhappy", "unclear", "undo"],
  },
  {
    id: 3,
    affix: "-able",
    definition: "Capable of being",
    type: "Suffix",
    examples: ["manageable", "readable", "comfortable"],
  },
  {
    id: 4,
    affix: "-tion",
    definition: "Action or process",
    type: "Suffix",
    examples: ["creation", "deletion", "population"],
  },
  {
    id: 5,
    affix: "re-",
    definition: "Again, back",
    type: "Prefix",
    examples: ["rebuild", "rewrite", "rethink"],
  },
])

const handleDeleteAffix = (affix) => {
  affixToDelete.value = affix
  showDeleteDialog.value = true
}

const confirmDelete = () => {
  // 删除逻辑
  console.log(`删除词缀: ${affixToDelete.value.id}`)

  // 从列表中移除
  affixes.value = affixes.value.filter(a => a.id !== affixToDelete.value.id)

  toast({
    title: "词缀已删除",
    description: `词缀 "${affixToDelete.value.affix}" 已成功删除。`,
  })

  showDeleteDialog.value = false
  affixToDelete.value = null
}
</script>
