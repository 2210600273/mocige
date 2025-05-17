<template>
  <div class="flex flex-col gap-4">
    <h1 class="text-3xl font-bold tracking-tight">测试记录</h1>

    <Card>
      <CardHeader>
        <CardTitle>测试记录管理</CardTitle>
        <CardDescription>查看和管理用户测试结果和表现。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex flex-col md:flex-row gap-4 justify-between items-center pb-4">
          <Input placeholder="搜索用户..." class="md:max-w-sm w-full" v-model="searchQuery" />
          <div class="flex gap-2">
            <Select v-model="selectedUser">
              <SelectTrigger class="w-[180px]">
                <SelectValue placeholder="按用户筛选" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">所有用户</SelectItem>
                <SelectItem value="1">用户 1</SelectItem>
                <SelectItem value="2">用户 2</SelectItem>
                <SelectItem value="3">用户 3</SelectItem>
              </SelectContent>
            </Select>
            <Select v-model="selectedScore">
              <SelectTrigger class="w-[180px]">
                <SelectValue placeholder="分数范围" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">所有分数</SelectItem>
                <SelectItem value="90-100">90-100%</SelectItem>
                <SelectItem value="80-89">80-89%</SelectItem>
                <SelectItem value="70-79">70-79%</SelectItem>
                <SelectItem value="0-69">70%以下</SelectItem>
              </SelectContent>
            </Select>
          </div>
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>用户</TableHead>
              <TableHead>测试日期</TableHead>
              <TableHead>分数</TableHead>
              <TableHead>表现</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="record in testRecords" :key="record.id">
              <TableCell>{{ record.id }}</TableCell>
              <TableCell>{{ record.user }}</TableCell>
              <TableCell>{{ record.testDate }}</TableCell>
              <TableCell>{{ record.score }}%</TableCell>
              <TableCell>
                <div class="flex items-center gap-2">
                  <div class="relative w-full h-2 bg-muted rounded-full overflow-hidden">
                    <div
                      class="absolute top-0 left-0 h-full"
                      :class="{
                        'bg-green-500': record.score >= 90,
                        'bg-blue-500': record.score >= 80 && record.score < 90,
                        'bg-yellow-500': record.score >= 70 && record.score < 80,
                        'bg-red-500': record.score < 70
                      }"
                      :style="{ width: `${record.score}%` }"
                    />
                  </div>
                  <span
                    class="text-xs font-medium whitespace-nowrap"
                    :class="{
                      'text-green-600': record.score >= 90,
                      'text-blue-600': record.score >= 80 && record.score < 90,
                      'text-yellow-600': record.score >= 70 && record.score < 80,
                      'text-red-600': record.score < 70
                    }"
                  >
                    {{
                      record.score >= 90
                        ? "优秀"
                        : record.score >= 80
                          ? "良好"
                          : record.score >= 70
                            ? "一般"
                            : "需要提高"
                    }}
                  </span>
                </div>
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
                    <DropdownMenuItem @click="openDetailsDialog(record)">
                      <FileText class="mr-2 h-4 w-4" />
                      查看详情
                    </DropdownMenuItem>
                    <DropdownMenuItem @click="viewAnalytics(record)">
                      <BarChart class="mr-2 h-4 w-4" />
                      分析数据
                    </DropdownMenuItem>
                    <DropdownMenuItem
                      class="text-destructive focus:text-destructive"
                      @click="openDeleteDialog(record)"
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
            此操作无法撤消。这将永久从数据库中删除此测试记录。
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

    <!-- 详情对话框 -->
    <Dialog :open="showDetailsDialog" @update:open="showDetailsDialog = $event">
      <DialogContent class="sm:max-w-md">
        <DialogHeader>
          <DialogTitle>测试记录详情</DialogTitle>
          <DialogDescription v-if="selectedRecord">
            {{ selectedRecord.user }} 于 {{ selectedRecord.testDate }} 进行的测试的详细信息
          </DialogDescription>
        </DialogHeader>
        <div class="space-y-4" v-if="selectedRecord">
          <div class="grid grid-cols-2 gap-4">
            <div>
              <p class="text-sm font-medium text-muted-foreground">用户：</p>
              <p>{{ selectedRecord.user }}</p>
            </div>
            <div>
              <p class="text-sm font-medium text-muted-foreground">测试日期：</p>
              <p>{{ selectedRecord.testDate }}</p>
            </div>
            <div>
              <p class="text-sm font-medium text-muted-foreground">分数：</p>
              <p>{{ selectedRecord.score }}%</p>
            </div>
            <div>
              <p class="text-sm font-medium text-muted-foreground">状态：</p>
              <p
                :class="{
                  'text-green-600': selectedRecord.score >= 90,
                  'text-blue-600': selectedRecord.score >= 80 && selectedRecord.score < 90,
                  'text-yellow-600': selectedRecord.score >= 70 && selectedRecord.score < 80,
                  'text-red-600': selectedRecord.score < 70
                }"
              >
                {{
                  selectedRecord.score >= 90
                    ? "优秀"
                    : selectedRecord.score >= 80
                      ? "良好"
                      : selectedRecord.score >= 70
                        ? "一般"
                        : "需要提高"
                }}
              </p>
            </div>
          </div>

          <div>
            <p class="text-sm font-medium text-muted-foreground mb-2">测试问题：</p>
            <div class="space-y-2">
              <div
                v-for="(item, index) in testQuestions"
                :key="index"
                class="flex items-center gap-2"
              >
                <div 
                  class="w-4 h-4 rounded-full" 
                  :class="item.correct ? 'bg-green-500' : 'bg-red-500'"
                ></div>
                <p class="text-sm">{{ item.question }}</p>
              </div>
            </div>
          </div>
        </div>
      </DialogContent>
    </Dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { MoreHorizontal, FileText, Trash, BarChart } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
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
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog'

const router = useRouter()
const { toast } = useToast()
const searchQuery = ref('')
const selectedUser = ref('all')
const selectedScore = ref('all')
const showDeleteDialog = ref(false)
const showDetailsDialog = ref(false)
const selectedRecord = ref(null)

// 模拟数据
const testRecords = ref([
  { id: 1, user: "用户 1", testDate: "2023-05-15 14:30", score: 95 },
  { id: 2, user: "用户 2", testDate: "2023-05-15 10:15", score: 82 },
  { id: 3, user: "用户 3", testDate: "2023-05-14 16:45", score: 78 },
  { id: 4, user: "用户 1", testDate: "2023-05-14 09:30", score: 90 },
  { id: 5, user: "用户 4", testDate: "2023-05-13 11:20", score: 65 },
  { id: 6, user: "用户 2", testDate: "2023-05-13 15:10", score: 88 },
  { id: 7, user: "用户 5", testDate: "2023-05-12 13:45", score: 92 },
  { id: 8, user: "用户 3", testDate: "2023-05-12 10:30", score: 75 },
])

// 模拟测试问题数据
const testQuestions = ref([
  { question: "单词 'vocabulary' 的定义是什么？", correct: true },
  { question: "'happy' 的反义词是什么？", correct: true },
  { question: "'big' 的同义词是什么？", correct: false },
  { question: "'transport' 的词根是什么？", correct: true },
  { question: "'unhappy' 中的前缀是什么？", correct: true },
])

const openDeleteDialog = (record) => {
  selectedRecord.value = record
  showDeleteDialog.value = true
}

const confirmDelete = () => {
  // 删除逻辑
  console.log(`删除测试记录: ${selectedRecord.value.id}`)
  
  // 从列表中移除
  testRecords.value = testRecords.value.filter(r => r.id !== selectedRecord.value.id)
  
  toast({
    title: "测试记录已删除",
    description: "测试记录已成功从数据库中删除。",
  })
  
  showDeleteDialog.value = false
  selectedRecord.value = null
}

const openDetailsDialog = (record) => {
  selectedRecord.value = record
  showDetailsDialog.value = true
}

const viewAnalytics = (record) => {
  console.log(`查看测试记录分析: ${record.id}`)
  
  toast({
    title: "分析数据",
    description: `正在加载 ${record.user} 的测试数据分析...`,
  })
}
</script>
