<template>
  <div class="flex flex-col gap-4">
    <div class="flex justify-between items-center">
      <h1 class="text-3xl font-bold tracking-tight">同义词详情</h1>
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
        <CardTitle>{{ isEditing ? '编辑同义词组' : '同义词组详情' }}</CardTitle>
        <CardDescription v-if="primaryWord">与 "{{ primaryWord.word }}" 相关的同义词</CardDescription>
      </CardHeader>
      <form @submit.prevent="handleSubmit">
        <CardContent class="space-y-4">
          <div class="space-y-2">
            <Label for="primary-word">主要单词</Label>
            <div v-if="!isEditing" class="p-2 border rounded-md">
              <div class="flex items-center justify-between">
                <div>
                  <span class="font-medium">{{ primaryWord.word }}</span>
                  <span class="ml-2 text-sm text-muted-foreground">{{ primaryWord.meaning }}</span>
                </div>
                <Button variant="ghost" size="sm" @click="navigateToWord(primaryWord.id)">
                  查看
                </Button>
              </div>
            </div>
            <div v-else class="flex items-center gap-2">
              <Popover>
                <PopoverTrigger asChild>
                  <Button variant="outline" class="w-full justify-start">
                    {{ selectedPrimaryWord ? selectedPrimaryWord.word : '选择单词' }}
                  </Button>
                </PopoverTrigger>
                <PopoverContent class="p-0" align="start">
                  <Command>
                    <CommandInput placeholder="搜索单词..." />
                    <CommandList>
                      <CommandEmpty>未找到单词</CommandEmpty>
                      <CommandGroup>
                        <CommandItem 
                          v-for="word in availableWords" 
                          :key="word.id"
                          @click="handleSelectPrimaryWord(word)"
                        >
                          {{ word.word }}
                          <small class="ml-2 text-muted-foreground">{{ word.meaning }}</small>
                        </CommandItem>
                      </CommandGroup>
                    </CommandList>
                  </Command>
                </PopoverContent>
              </Popover>
              <Button type="button" size="icon" variant="outline" @click="navigateToNewWord">
                <Plus class="h-4 w-4" />
              </Button>
            </div>
          </div>
          
          <div class="space-y-2">
            <Label for="synonym-words">同义词</Label>
            <div v-if="!isEditing" class="space-y-2">
              <div 
                v-for="synonym in synonyms" 
                :key="synonym.id"
                class="p-2 border rounded-md"
              >
                <div class="flex items-center justify-between">
                  <div>
                    <span>{{ synonym.word }}</span>
                    <span class="ml-2 text-sm text-muted-foreground">{{ synonym.meaning }}</span>
                  </div>
                  <Button variant="ghost" size="sm" @click="navigateToWord(synonym.id)">
                    查看
                  </Button>
                </div>
              </div>
            </div>
            <div v-else class="flex flex-col gap-2">
              <div 
                v-for="(synonym, index) in selectedSynonyms" 
                :key="index"
                class="flex items-center gap-2"
              >
                <div class="flex-1 pl-3 p-2 border rounded-md flex justify-between items-center">
                  <div>
                    <span>{{ synonym.word }}</span>
                    <small class="ml-2 text-muted-foreground">{{ synonym.meaning }}</small>
                  </div>
                  <Button type="button" size="icon" variant="ghost" @click="removeSynonym(index)">
                    <X class="h-4 w-4" />
                  </Button>
                </div>
              </div>
              
              <div class="flex items-center gap-2">
                <Popover>
                  <PopoverTrigger asChild>
                    <Button variant="outline" class="w-full justify-start">
                      添加同义词
                    </Button>
                  </PopoverTrigger>
                  <PopoverContent class="p-0" align="start">
                    <Command>
                      <CommandInput placeholder="搜索单词..." />
                      <CommandList>
                        <CommandEmpty>未找到单词</CommandEmpty>
                        <CommandGroup>
                          <CommandItem 
                            v-for="word in availableWords.filter(w => w.id !== (selectedPrimaryWord?.id || 0) && !selectedSynonyms.some(s => s.id === w.id))" 
                            :key="word.id"
                            @click="addSynonym(word)"
                          >
                            {{ word.word }}
                            <small class="ml-2 text-muted-foreground">{{ word.meaning }}</small>
                          </CommandItem>
                        </CommandGroup>
                      </CommandList>
                    </Command>
                  </PopoverContent>
                </Popover>
                <Button type="button" size="icon" variant="outline" @click="navigateToNewWord">
                  <Plus class="h-4 w-4" />
                </Button>
              </div>
            </div>
          </div>
          
          <div class="space-y-2">
            <Label for="notes">备注</Label>
            <Textarea 
              id="notes" 
              v-model="notes" 
              placeholder="同义词组的相关说明或备注" 
              rows="3"
              :disabled="!isEditing"
            />
          </div>

          <div class="space-y-2" v-if="!isEditing">
            <Label>创建时间</Label>
            <p class="text-sm text-muted-foreground">{{ createdAt }}</p>
          </div>

          <div class="space-y-2" v-if="!isEditing">
            <Label>最后更新</Label>
            <p class="text-sm text-muted-foreground">{{ updatedAt }}</p>
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button type="button" variant="outline" @click="router.back()">返回</Button>
          <Button type="submit" v-if="isEditing" :disabled="!isFormValid">保存更改</Button>
        </CardFooter>
      </form>
    </Card>

    <!-- 删除确认对话框 -->
    <AlertDialog :open="showDeleteDialog" @update:open="showDeleteDialog = $event">
      <AlertDialogContent>
        <AlertDialogHeader>
          <AlertDialogTitle>确定要删除吗？</AlertDialogTitle>
          <AlertDialogDescription>
            此操作无法撤消。这将永久删除此同义词组。
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
import { ref, computed, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Label } from '@/components/ui/label'
import { Textarea } from '@/components/ui/textarea'
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover'
import { Command, CommandEmpty, CommandGroup, CommandInput, CommandItem, CommandList } from '@/components/ui/command'
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle } from '@/components/ui/alert-dialog'
import { Plus, X } from 'lucide-vue-next'

const router = useRouter()
const route = useRoute()
const { toast } = useToast()

const isEditing = ref(false)
const showDeleteDialog = ref(false)

// 模拟可用单词数据
const availableWords = ref([
  { id: 1, word: 'happy', meaning: '高兴的，幸福的' },
  { id: 2, word: 'joyful', meaning: '欢喜的，快乐的' },
  { id: 3, word: 'cheerful', meaning: '愉快的，高兴的' },
  { id: 4, word: 'delighted', meaning: '欣喜的，开心的' },
  { id: 5, word: 'glad', meaning: '高兴的，乐意的' },
  { id: 6, word: 'sad', meaning: '悲伤的，难过的' },
  { id: 7, word: 'clever', meaning: '聪明的，机智的' },
  { id: 8, word: 'intelligent', meaning: '聪明的，智能的' },
  { id: 9, word: 'smart', meaning: '聪明的，漂亮的' },
  { id: 10, word: 'brilliant', meaning: '才华横溢的，灿烂的' }
])

// 数据
const primaryWord = ref({ id: 1, word: 'happy', meaning: '高兴的，幸福的' })
const synonyms = ref([
  { id: 2, word: 'joyful', meaning: '欢喜的，快乐的' },
  { id: 3, word: 'cheerful', meaning: '愉快的，高兴的' },
  { id: 4, word: 'delighted', meaning: '欣喜的，开心的' },
  { id: 5, word: 'glad', meaning: '高兴的，乐意的' }
])
const notes = ref('这组同义词表达积极情绪和心情愉快的状态，但在程度和使用场景上有细微差别。')
const createdAt = ref('2023-04-12 10:30:00')
const updatedAt = ref('2023-05-20 15:40:00')

// 编辑模式下的数据
const selectedPrimaryWord = ref(null)
const selectedSynonyms = ref([])

// 表单是否有效
const isFormValid = computed(() => {
  return selectedPrimaryWord.value && selectedSynonyms.value.length > 0
})

// 初始化编辑数据
onMounted(() => {
  // 从API获取数据的逻辑，这里使用模拟数据
  console.log('获取同义词组ID:', route.params.id)
  
  // 初始化编辑数据
  selectedPrimaryWord.value = { ...primaryWord.value }
  selectedSynonyms.value = [...synonyms.value]
})

// 选择主要单词
const handleSelectPrimaryWord = (word) => {
  selectedPrimaryWord.value = word
  // 如果当前选中的同义词中包含了主词，则移除
  selectedSynonyms.value = selectedSynonyms.value.filter(w => w.id !== word.id)
}

// 添加同义词
const addSynonym = (word) => {
  if (!selectedSynonyms.value.some(w => w.id === word.id) && word.id !== selectedPrimaryWord.value?.id) {
    selectedSynonyms.value.push(word)
  }
}

// 移除同义词
const removeSynonym = (index) => {
  selectedSynonyms.value.splice(index, 1)
}

// 导航到单词详情
const navigateToWord = (wordId) => {
  router.push(`/words/${wordId}`)
}

// 导航到新增单词页面
const navigateToNewWord = () => {
  router.push('/words/new')
}

// 提交表单
const handleSubmit = () => {
  if (!isFormValid.value) return
  
  // 构建提交数据
  const submitData = {
    id: route.params.id,
    primaryWordId: selectedPrimaryWord.value.id,
    synonymIds: selectedSynonyms.value.map(w => w.id),
    notes: notes.value
  }
  
  // 这里添加保存逻辑
  console.log('更新同义词组:', submitData)
  
  // 更新显示数据
  primaryWord.value = { ...selectedPrimaryWord.value }
  synonyms.value = [...selectedSynonyms.value]
  updatedAt.value = new Date().toISOString().replace('T', ' ').substring(0, 19)
  
  // 模拟保存成功
  toast({
    title: "更新成功",
    description: `同义词组已成功更新`,
  })
  
  isEditing.value = false
}

// 打开删除对话框
const openDeleteDialog = () => {
  showDeleteDialog.value = true
}

// 删除同义词组
const handleDelete = () => {
  // 删除逻辑
  console.log('删除同义词组:', route.params.id)
  
  toast({
    title: "删除成功",
    description: "同义词组已成功删除",
  })
  
  router.push('/synonyms')
}
</script> 