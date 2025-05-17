<template>
  <div class="flex flex-col gap-4">
    <div class="flex justify-between items-center">
      <h1 class="text-3xl font-bold tracking-tight">词根详情</h1>
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
        <CardTitle>{{ isEditing ? '编辑词根' : '词根信息' }}</CardTitle>
        <CardDescription>{{ root.root }} 的详细信息</CardDescription>
      </CardHeader>
      <form @submit.prevent="handleSubmit">
        <CardContent class="space-y-4">
          <div class="space-y-2">
            <Label for="root">词根</Label>
            <Input id="root" v-model="root.root" placeholder="输入词根" :disabled="!isEditing" required />
          </div>
          
          <div class="space-y-2">
            <Label for="meaning">含义</Label>
            <Input id="meaning" v-model="root.meaning" placeholder="词根含义" :disabled="!isEditing" required />
          </div>
          
          <div class="space-y-2">
            <Label for="origin">词源</Label>
            <Input id="origin" v-model="root.origin" placeholder="词根来源" :disabled="!isEditing" />
          </div>
          
          <div class="space-y-2">
            <Label for="language">语言</Label>
            <Select v-model="root.language" :disabled="!isEditing">
              <SelectTrigger id="language">
                <SelectValue placeholder="选择语言" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="latin">拉丁语</SelectItem>
                <SelectItem value="greek">希腊语</SelectItem>
                <SelectItem value="french">法语</SelectItem>
                <SelectItem value="german">德语</SelectItem>
                <SelectItem value="other">其他</SelectItem>
              </SelectContent>
            </Select>
          </div>
          
          <div class="space-y-2">
            <Label for="examples">单词例子</Label>
            <Textarea 
              id="examples" 
              v-model="root.examples" 
              placeholder="包含该词根的单词例子，每行一个" 
              rows="4"
              :disabled="!isEditing"
            />
          </div>
          
          <div class="space-y-2">
            <Label for="notes">备注</Label>
            <Textarea 
              id="notes" 
              v-model="root.notes" 
              placeholder="任何相关说明或备注" 
              rows="3"
              :disabled="!isEditing"
            />
          </div>

          <div class="space-y-2" v-if="!isEditing">
            <Label>创建时间</Label>
            <p class="text-sm text-muted-foreground">{{ root.createdAt }}</p>
          </div>

          <div class="space-y-2" v-if="!isEditing">
            <Label>最后更新</Label>
            <p class="text-sm text-muted-foreground">{{ root.updatedAt }}</p>
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
        <CardDescription>包含此词根的单词列表</CardDescription>
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
            此操作无法撤消。这将永久删除此词根及其与其他记录的关联。
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

// 词根数据
const root = ref({
  id: route.params.id,
  root: 'struct',
  meaning: '建造，构造',
  origin: '来源于拉丁语 "struere"',
  language: 'latin',
  examples: 'structure\nconstruction\ninstruction\ndestructive\ninfrastructure',
  notes: '这个词根非常常见，通常与构建和组织相关的概念有关。',
  createdAt: '2023-03-15 11:20:00',
  updatedAt: '2023-05-10 16:45:00'
})

// 相关单词
const relatedWords = ref([
  { id: 1, word: 'structure', meaning: '结构，构造', partOfSpeech: '名词' },
  { id: 2, word: 'construction', meaning: '建设，建筑', partOfSpeech: '名词' },
  { id: 3, word: 'instruction', meaning: '指导，说明', partOfSpeech: '名词' },
  { id: 4, word: 'destructive', meaning: '破坏性的', partOfSpeech: '形容词' },
  { id: 5, word: 'infrastructure', meaning: '基础设施', partOfSpeech: '名词' }
])

// 获取词根数据
onMounted(() => {
  // 从API获取数据的逻辑，这里使用模拟数据
  console.log('获取词根ID:', route.params.id)
})

// 提交表单
const handleSubmit = () => {
  // 保存逻辑
  console.log('保存词根:', root.value)
  
  // 更新时间
  root.value.updatedAt = new Date().toISOString().replace('T', ' ').substring(0, 19)
  
  // 模拟保存成功
  toast({
    title: "更新成功",
    description: `词根 ${root.value.root} 已成功更新`,
  })
  
  isEditing.value = false
}

// 打开删除对话框
const openDeleteDialog = () => {
  showDeleteDialog.value = true
}

// 删除词根
const handleDelete = () => {
  // 删除逻辑
  console.log('删除词根:', root.value.id)
  
  toast({
    title: "删除成功",
    description: `词根 ${root.value.root} 已成功删除`,
  })
  
  router.push('/roots')
}

// 导航到单词详情
const navigateToWord = (wordId) => {
  router.push(`/words/${wordId}`)
}
</script> 