<template>
  <div class="flex flex-col gap-4">
    <div class="flex justify-between items-center">
      <h1 class="text-3xl font-bold tracking-tight">媒体详情</h1>
      <div class="flex gap-2">
        <Button @click="isEditing = !isEditing">
          {{ isEditing ? '取消' : '编辑' }}
        </Button>
        <Button variant="destructive" @click="openDeleteDialog">
          删除
        </Button>
      </div>
    </div>
    
    <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
      <Card class="md:col-span-2">
        <CardHeader>
          <CardTitle>{{ isEditing ? '编辑媒体' : '媒体信息' }}</CardTitle>
          <CardDescription>{{ media.title }} 的详细信息</CardDescription>
        </CardHeader>
        <form @submit.prevent="handleSubmit">
          <CardContent class="space-y-4">
            <div class="space-y-2">
              <Label for="title">标题</Label>
              <Input id="title" v-model="media.title" placeholder="媒体标题" :disabled="!isEditing" required />
            </div>
            
            <div class="space-y-2">
              <Label for="type">类型</Label>
              <Select v-model="media.type" :disabled="!isEditing">
                <SelectTrigger id="type">
                  <SelectValue placeholder="选择媒体类型" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="image">图片</SelectItem>
                  <SelectItem value="audio">音频</SelectItem>
                  <SelectItem value="video">视频</SelectItem>
                  <SelectItem value="document">文档</SelectItem>
                </SelectContent>
              </Select>
            </div>
            
            <div class="space-y-2">
              <Label for="url">URL地址</Label>
              <Input id="url" v-model="media.url" placeholder="媒体URL地址" :disabled="!isEditing" required />
              <p class="text-xs text-muted-foreground" v-if="isEditing">输入媒体文件的完整URL地址</p>
            </div>
            
            <div class="space-y-2">
              <Label for="description">描述</Label>
              <Textarea 
                id="description" 
                v-model="media.description" 
                placeholder="媒体描述" 
                rows="3"
                :disabled="!isEditing"
              />
            </div>
            
            <div class="space-y-2">
              <Label for="tags">标签</Label>
              <div v-if="!isEditing" class="flex flex-wrap gap-2">
                <Badge v-for="tag in media.tags" :key="tag" variant="secondary">
                  {{ tag }}
                </Badge>
              </div>
              <div v-else>
                <div class="flex flex-wrap gap-2 mb-2">
                  <Badge 
                    v-for="(tag, index) in tags" 
                    :key="index" 
                    variant="secondary"
                    class="flex items-center gap-1"
                  >
                    {{ tag }}
                    <Button 
                      type="button" 
                      variant="ghost" 
                      size="icon" 
                      class="h-4 w-4 p-0 hover:bg-transparent"
                      @click="removeTag(index)"
                    >
                      <X class="h-3 w-3" />
                    </Button>
                  </Badge>
                </div>
                <div class="flex gap-2">
                  <Input 
                    v-model="newTag" 
                    placeholder="添加标签..."
                    @keydown.enter.prevent="addTag"
                  />
                  <Button type="button" size="sm" @click="addTag">添加</Button>
                </div>
              </div>
            </div>

            <div class="space-y-2" v-if="!isEditing">
              <Label>创建时间</Label>
              <p class="text-sm text-muted-foreground">{{ media.createdAt }}</p>
            </div>

            <div class="space-y-2" v-if="!isEditing">
              <Label>最后更新</Label>
              <p class="text-sm text-muted-foreground">{{ media.updatedAt }}</p>
            </div>
          </CardContent>
          <CardFooter class="flex justify-between">
            <Button type="button" variant="outline" @click="router.back()">返回</Button>
            <Button type="submit" v-if="isEditing">保存更改</Button>
          </CardFooter>
        </form>
      </Card>
      
      <Card>
        <CardHeader>
          <CardTitle>媒体预览</CardTitle>
        </CardHeader>
        <CardContent class="flex flex-col items-center justify-center">
          <div v-if="media.type === 'image'" class="w-full">
            <img 
              :src="media.url" 
              :alt="media.title" 
              class="w-full h-auto rounded-md border object-cover"
            />
          </div>
          
          <div v-else-if="media.type === 'audio'" class="w-full">
            <div class="border rounded-md p-4 flex flex-col items-center">
              <Music class="h-16 w-16 text-muted-foreground mb-4" />
              <audio controls class="w-full">
                <source :src="media.url" type="audio/mpeg">
                您的浏览器不支持音频元素。
              </audio>
            </div>
          </div>
          
          <div v-else-if="media.type === 'video'" class="w-full">
            <video 
              controls 
              class="w-full h-auto rounded-md border"
            >
              <source :src="media.url" type="video/mp4">
              您的浏览器不支持视频元素。
            </video>
          </div>
          
          <div v-else-if="media.type === 'document'" class="w-full">
            <div class="border rounded-md p-4 flex flex-col items-center">
              <FileText class="h-16 w-16 text-muted-foreground mb-4" />
              <Button @click="openDocument">查看文档</Button>
            </div>
          </div>
        </CardContent>
      </Card>
    </div>

    <!-- 关联单词列表 -->
    <Card v-if="!isEditing">
      <CardHeader>
        <CardTitle>关联单词</CardTitle>
        <CardDescription>使用此媒体的单词列表</CardDescription>
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
            此操作无法撤消。这将永久删除此媒体资源及其与其他记录的关联。
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
import { Badge } from '@/components/ui/badge'
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle } from '@/components/ui/alert-dialog'
import { Music, FileText, X } from 'lucide-vue-next'

const router = useRouter()
const route = useRoute()
const { toast } = useToast()

const isEditing = ref(false)
const showDeleteDialog = ref(false)

// 媒体数据
const media = ref({
  id: route.params.id,
  title: '商务英语对话场景',
  type: 'audio',
  url: 'https://example.com/media/business_conversation.mp3',
  description: '商务环境中的英语对话，涵盖常见商务词汇和表达。',
  tags: ['商务', '对话', '音频'],
  createdAt: '2023-04-05 14:20:00',
  updatedAt: '2023-05-12 11:35:00'
})

// 标签数据
const tags = ref([...media.value.tags])
const newTag = ref('')

// 相关单词
const relatedWords = ref([
  { id: 1, word: 'negotiate', meaning: '谈判，协商', partOfSpeech: '动词' },
  { id: 2, word: 'proposal', meaning: '提议，建议', partOfSpeech: '名词' },
  { id: 3, word: 'agenda', meaning: '议程，议事日程', partOfSpeech: '名词' },
  { id: 4, word: 'deadline', meaning: '截止日期', partOfSpeech: '名词' },
  { id: 5, word: 'revenue', meaning: '收入，收益', partOfSpeech: '名词' }
])

// 获取媒体数据
onMounted(() => {
  // 从API获取数据的逻辑，这里使用模拟数据
  console.log('获取媒体ID:', route.params.id)
})

// 添加标签
const addTag = () => {
  if (newTag.value.trim() && !tags.value.includes(newTag.value.trim())) {
    tags.value.push(newTag.value.trim())
    newTag.value = ''
  }
}

// 移除标签
const removeTag = (index) => {
  tags.value.splice(index, 1)
}

// 打开文档
const openDocument = () => {
  window.open(media.value.url, '_blank')
}

// 提交表单
const handleSubmit = () => {
  // 更新标签
  media.value.tags = [...tags.value]
  
  // 保存逻辑
  console.log('保存媒体:', media.value)
  
  // 更新时间
  media.value.updatedAt = new Date().toISOString().replace('T', ' ').substring(0, 19)
  
  // 模拟保存成功
  toast({
    title: "更新成功",
    description: `媒体 ${media.value.title} 已成功更新`,
  })
  
  isEditing.value = false
}

// 打开删除对话框
const openDeleteDialog = () => {
  showDeleteDialog.value = true
}

// 删除媒体
const handleDelete = () => {
  // 删除逻辑
  console.log('删除媒体:', media.value.id)
  
  toast({
    title: "删除成功",
    description: `媒体 ${media.value.title} 已成功删除`,
  })
  
  router.push('/media')
}

// 导航到单词详情
const navigateToWord = (wordId) => {
  router.push(`/words/${wordId}`)
}
</script> 