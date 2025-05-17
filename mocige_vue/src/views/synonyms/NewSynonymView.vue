<template>
  <div class="flex flex-col gap-4">
    <h1 class="text-3xl font-bold tracking-tight">新增同义词</h1>
    
    <Card>
      <CardHeader>
        <CardTitle>同义词信息</CardTitle>
        <CardDescription>添加同义词关系到系统中</CardDescription>
      </CardHeader>
      <form @submit.prevent="handleSubmit">
        <CardContent class="space-y-4">
          <div class="space-y-2">
            <Label for="primary-word">主要单词</Label>
            <div class="flex items-center gap-2">
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
            <div class="flex flex-col gap-2">
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
            />
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button type="button" variant="outline" @click="router.back()">取消</Button>
          <Button type="submit" :disabled="!isFormValid">保存</Button>
        </CardFooter>
      </form>
    </Card>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Label } from '@/components/ui/label'
import { Textarea } from '@/components/ui/textarea'
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover'
import { Command, CommandEmpty, CommandGroup, CommandInput, CommandItem, CommandList } from '@/components/ui/command'
import { Plus, X } from 'lucide-vue-next'

const router = useRouter()
const { toast } = useToast()

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
const selectedPrimaryWord = ref(null)
const selectedSynonyms = ref([])
const notes = ref('')

// 表单是否有效
const isFormValid = computed(() => {
  return selectedPrimaryWord.value && selectedSynonyms.value.length > 0
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

// 导航到新增单词页面
const navigateToNewWord = () => {
  router.push('/words/new')
}

// 提交表单
const handleSubmit = () => {
  if (!isFormValid.value) return
  
  // 构建提交数据
  const submitData = {
    primaryWordId: selectedPrimaryWord.value.id,
    synonymIds: selectedSynonyms.value.map(w => w.id),
    notes: notes.value
  }
  
  // 这里添加保存逻辑
  console.log('提交同义词组:', submitData)
  
  // 模拟保存成功
  toast({
    title: "添加成功",
    description: `已成功添加 ${selectedPrimaryWord.value.word} 的同义词组`,
  })
  
  // 保存成功后返回列表页
  router.push('/synonyms')
}
</script> 