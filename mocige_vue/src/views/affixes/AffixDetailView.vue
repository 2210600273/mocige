<template>
  <div class="flex flex-col gap-4">
    <div class="flex justify-between items-center">
      <h1 class="text-3xl font-bold tracking-tight">词缀详情</h1>
      <div class="flex gap-2">
        <Button @click="isEditing = !isEditing">
          {{ isEditing ? '取消' : '编辑' }}
        </Button>
        <Button variant="destructive" @click="openDeleteDialog">
          删除
        </Button>
      </div>
    </div>
    
    <Card>
      <CardHeader>
        <CardTitle>{{ isEditing ? '编辑词缀' : '词缀信息' }}</CardTitle>
        <CardDescription>{{ affix.affix }} 的详细信息</CardDescription>
      </CardHeader>
      <form @submit.prevent="handleSubmit">
        <CardContent class="space-y-4">
          <div class="space-y-2">
            <Label for="affix">词缀</Label>
            <Input id="affix" v-model="affix.affix" placeholder="输入词缀" :disabled="!isEditing" required />
          </div>
          
          <div class="space-y-2">
            <Label for="type">类型</Label>
            <Select v-model="affix.type" :disabled="!isEditing">
              <SelectTrigger id="type">
                <SelectValue placeholder="选择词缀类型" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="prefix">前缀</SelectItem>
                <SelectItem value="suffix">后缀</SelectItem>
                <SelectItem value="infix">中缀</SelectItem>
              </SelectContent>
            </Select>
          </div>
          
          <div class="space-y-2">
            <Label for="meaning">含义</Label>
            <Input id="meaning" v-model="affix.meaning" placeholder="词缀含义" :disabled="!isEditing" required />
          </div>
          
          <div class="space-y-2">
            <Label for="origin">词源</Label>
            <Input id="origin" v-model="affix.origin" placeholder="词缀来源" :disabled="!isEditing" />
          </div>
          
          <div class="space-y-2">
            <Label for="examples">使用示例</Label>
            <Textarea 
              id="examples" 
              v-model="affix.examples" 
              placeholder="输入包含该词缀的单词示例，每行一个" 
              rows="4"
              :disabled="!isEditing"
            />
          </div>
          
          <div class="space-y-2">
            <Label for="notes">备注</Label>
            <Textarea 
              id="notes" 
              v-model="affix.notes" 
              placeholder="任何相关说明或备注" 
              rows="3"
              :disabled="!isEditing"
            />
          </div>

          <div class="space-y-2" v-if="!isEditing">
            <Label>创建时间</Label>
            <p class="text-sm text-muted-foreground">{{ affix.createdAt }}</p>
          </div>

          <div class="space-y-2" v-if="!isEditing">
            <Label>最后更新</Label>
            <p class="text-sm text-muted-foreground">{{ affix.updatedAt }}</p>
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button type="button" variant="outline" @click="router.back()">返回</Button>
          <Button type="submit" v-if="isEditing">保存更改</Button>
        </CardFooter>
      </form>
    </Card>

    <!-- 相关单词列表 -->
    <Card v-if="!isEditing">
      <CardHeader>
        <CardTitle>相关单词</CardTitle>
        <CardDescription>包含此词缀的单词列表</CardDescription>
      </CardHeader>
      <CardContent>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>单词</TableHead>
              <TableHead>含义</TableHead>
              <TableHead>词性</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="word in relatedWords" :key="word.id">
              <TableCell>{{ word.word }}</TableCell>
              <TableCell>{{ word.meaning }}</TableCell>
              <TableCell>{{ word.partOfSpeech }}</TableCell>
              <TableCell>
                <Button variant="ghost" size="sm" @click="navigateToWord(word.id)">
                  查看
                </Button>
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
            此操作无法撤消。这将永久删除此词缀及其与其他记录的关联。
          </AlertDialogDescription>
        </AlertDialogHeader>
        <AlertDialogFooter>
          <AlertDialogCancel>取消</AlertDialogCancel>
          <AlertDialogAction @click="handleDelete" class="bg-destructive text-destructive-foreground hover:bg-destructive/90">
            删除
          </AlertDialogAction>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Textarea } from '@/components/ui/textarea'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle } from '@/components/ui/alert-dialog'

const router = useRouter()
const route = useRoute()
const { toast } = useToast()

const isEditing = ref(false)
const showDeleteDialog = ref(false)

// 词缀数据
const affix = ref({
  id: route.params.id,
  affix: 're',
  type: 'prefix',
  meaning: '重新、再次',
  origin: '拉丁语',
  examples: 'rebuild\nrestart\nreturn\nreview',
  notes: '常用前缀，表示动作的重复或返回到以前的状态',
  createdAt: '2023-04-10 09:30:00',
  updatedAt: '2023-05-15 14:22:00'
})

// 相关单词
const relatedWords = ref([
  { id: 1, word: 'rebuild', meaning: '重建', partOfSpeech: '动词' },
  { id: 2, word: 'restart', meaning: '重新开始', partOfSpeech: '动词' },
  { id: 3, word: 'return', meaning: '返回', partOfSpeech: '动词' },
  { id: 4, word: 'review', meaning: '复习', partOfSpeech: '动词' },
  { id: 5, word: 'reunion', meaning: '重聚', partOfSpeech: '名词' }
])

// 获取词缀数据
onMounted(() => {
  // 从API获取数据的逻辑，这里使用模拟数据
  console.log('获取词缀ID:', route.params.id)
})

// 提交表单
const handleSubmit = () => {
  // 保存逻辑
  console.log('保存词缀:', affix.value)
  
  // 模拟保存成功
  toast({
    title: "更新成功",
    description: `词缀 ${affix.value.affix} 已成功更新`,
  })
  
  isEditing.value = false
}

// 打开删除对话框
const openDeleteDialog = () => {
  showDeleteDialog.value = true
}

// 删除词缀
const handleDelete = () => {
  // 删除逻辑
  console.log('删除词缀:', affix.value.id)
  
  toast({
    title: "删除成功",
    description: `词缀 ${affix.value.affix} 已成功删除`,
  })
  
  router.push('/affixes')
}

// 导航到单词详情
const navigateToWord = (wordId) => {
  router.push(`/words/${wordId}`)
}
</script> 