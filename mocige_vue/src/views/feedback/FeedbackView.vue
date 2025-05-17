<template>
  <div class="flex flex-col gap-4">
    <h1 class="text-3xl font-bold tracking-tight">用户反馈</h1>

    <Card>
      <CardHeader>
        <CardTitle>反馈管理</CardTitle>
        <CardDescription>查看和管理用户反馈以改进平台。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex justify-between items-center pb-4">
          <Input placeholder="搜索反馈..." class="max-w-sm" v-model="searchQuery" />
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>用户</TableHead>
              <TableHead class="w-[50%]">评论</TableHead>
              <TableHead>日期</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="feedback in feedbackData" :key="feedback.id">
              <TableCell>{{ feedback.id }}</TableCell>
              <TableCell>{{ feedback.user }}</TableCell>
              <TableCell>{{ feedback.comment }}</TableCell>
              <TableCell>{{ feedback.date }}</TableCell>
              <TableCell>
                <DropdownMenu>
                  <DropdownMenuTrigger as-child>
                    <Button variant="ghost" class="h-8 w-8 p-0">
                      <span class="sr-only">打开菜单</span>
                      <MoreHorizontal class="h-4 w-4" />
                    </Button>
                  </DropdownMenuTrigger>
                  <DropdownMenuContent align="end">
                    <DropdownMenuItem @click="openReplyDialog(feedback)">
                      <MessageSquare class="mr-2 h-4 w-4" />
                      回复
                    </DropdownMenuItem>
                    <DropdownMenuItem @click="markAsResolved(feedback)">
                      <Check class="mr-2 h-4 w-4" />
                      标记为已解决
                    </DropdownMenuItem>
                    <DropdownMenuItem @click="markAsUnresolved(feedback)">
                      <X class="mr-2 h-4 w-4" />
                      标记为未解决
                    </DropdownMenuItem>
                    <DropdownMenuItem
                      class="text-destructive focus:text-destructive"
                      @click="openDeleteDialog(feedback)"
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
            此操作无法撤消。这将永久删除该反馈。
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

    <!-- 回复对话框 -->
    <Dialog :open="showReplyDialog" @update:open="showReplyDialog = $event">
      <DialogContent>
        <DialogHeader>
          <DialogTitle>回复反馈</DialogTitle>
          <DialogDescription>向用户发送关于其反馈的回复。</DialogDescription>
        </DialogHeader>
        <div class="space-y-4 py-4" v-if="selectedFeedback">
          <div class="bg-muted p-3 rounded-md">
            <p class="text-sm font-medium">原始反馈：</p>
            <p class="text-sm mt-1">{{ selectedFeedback.comment }}</p>
          </div>
          <Textarea
            placeholder="在此处输入回复..."
            v-model="replyText"
            rows="5"
          />
        </div>
        <DialogFooter>
          <Button variant="outline" @click="showReplyDialog = false">
            取消
          </Button>
          <Button @click="handleReply">发送回复</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useToast } from '@/components/ui/toast'
import { MoreHorizontal, MessageSquare, Trash, Check, X } from 'lucide-vue-next'
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
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog'
import { Textarea } from '@/components/ui/textarea'

const { toast } = useToast()
const searchQuery = ref('')
const showDeleteDialog = ref(false)
const showReplyDialog = ref(false)
const selectedFeedback = ref(null)
const replyText = ref('')

// 模拟数据
const feedbackData = ref([
  {
    id: 1,
    user: "用户 1",
    comment: "新的抽认卡系统帮助我更快地学习单词！",
    date: "2023-05-15",
  },
  {
    id: 2,
    user: "用户 2",
    comment: "能否为所有单词添加发音音频？这会很有帮助。",
    date: "2023-05-14",
  },
  {
    id: 3,
    user: "用户 3",
    comment: "同义词建议很棒，但有时不太准确。",
    date: "2023-05-13",
  },
  {
    id: 4,
    user: "用户 4",
    comment: "我喜欢进度跟踪系统！非常有动力。",
    date: "2023-05-12",
  },
  {
    id: 5,
    user: "用户 5",
    comment: "在应用设置中有一个暗黑模式选项会很好。",
    date: "2023-05-11",
  },
])

const openDeleteDialog = (feedback) => {
  selectedFeedback.value = feedback
  showDeleteDialog.value = true
}

const confirmDelete = () => {
  // 删除逻辑
  console.log(`删除反馈: ${selectedFeedback.value.id}`)
  
  // 从列表中移除
  feedbackData.value = feedbackData.value.filter(f => f.id !== selectedFeedback.value.id)
  
  toast({
    title: "反馈已删除",
    description: "用户反馈已成功删除。",
  })
  
  showDeleteDialog.value = false
  selectedFeedback.value = null
}

const openReplyDialog = (feedback) => {
  selectedFeedback.value = feedback
  replyText.value = ''
  showReplyDialog.value = true
}

const handleReply = () => {
  // 回复逻辑
  console.log(`回复反馈 ${selectedFeedback.value.id}: ${replyText.value}`)
  
  toast({
    title: "回复已发送",
    description: `回复已发送给 ${selectedFeedback.value.user}。`,
  })
  
  showReplyDialog.value = false
  replyText.value = ''
}

const markAsResolved = (feedback) => {
  console.log(`将反馈 ${feedback.id} 标记为已解决`)
  
  toast({
    title: "状态已更改",
    description: "反馈已标记为已解决。",
  })
}

const markAsUnresolved = (feedback) => {
  console.log(`将反馈 ${feedback.id} 标记为未解决`)
  
  toast({
    title: "状态已更改",
    description: "反馈已标记为未解决。",
  })
}
</script>
