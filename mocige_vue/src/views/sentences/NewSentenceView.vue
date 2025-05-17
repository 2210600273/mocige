<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center gap-2">
      <router-link to="/sentences">
        <Button variant="ghost" size="sm">
          <ArrowLeft class="mr-2 h-4 w-4" />
          返回例句列表
        </Button>
      </router-link>
      <h1 class="text-3xl font-bold tracking-tight">添加新例句</h1>
    </div>

    <Card>
      <form @submit.prevent="handleSubmit">
        <CardHeader>
          <CardTitle>例句信息</CardTitle>
          <CardDescription>输入新示例句子的详细信息。</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div class="space-y-2">
            <Label for="sentence">例句</Label>
            <Textarea
              id="sentence"
              placeholder="输入一个示例句子"
              :rows="3"
              v-model="sentence"
              required
            />
          </div>

          <div class="space-y-2">
            <Label for="context">上下文（可选）</Label>
            <Textarea
              id="context"
              placeholder="输入关于此句子的上下文或附加信息"
              :rows="2"
              v-model="context"
            />
          </div>

          <div class="space-y-2">
            <Label>相关单词</Label>
            <div class="flex gap-2">
              <Input
                placeholder="添加相关单词"
                v-model="relatedWord"
                @keydown.enter.prevent="handleAddRelatedWord"
              />
              <Button type="button" @click="handleAddRelatedWord">
                添加
              </Button>
            </div>
            <div class="flex flex-wrap gap-2 mt-2">
              <Badge 
                v-for="word in relatedWords" 
                :key="word" 
                variant="secondary" 
                class="flex items-center gap-1"
              >
                {{ word }}
                <X 
                  class="h-3 w-3 cursor-pointer" 
                  @click="handleRemoveRelatedWord(word)" 
                />
              </Badge>
            </div>
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button variant="outline" type="button" @click="router.back()">
            取消
          </Button>
          <Button type="submit">添加例句</Button>
        </CardFooter>
      </form>
    </Card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { ArrowLeft, X } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Textarea } from '@/components/ui/textarea'
import { Label } from '@/components/ui/label'
import { Badge } from '@/components/ui/badge'

const router = useRouter()
const { toast } = useToast()

const sentence = ref('')
const context = ref('')
const relatedWord = ref('')
const relatedWords = ref([])

const handleAddRelatedWord = () => {
  if (relatedWord.value.trim() && !relatedWords.value.includes(relatedWord.value.trim())) {
    relatedWords.value.push(relatedWord.value.trim())
    relatedWord.value = ''
  }
}

const handleRemoveRelatedWord = (word) => {
  relatedWords.value = relatedWords.value.filter(w => w !== word)
}

const handleSubmit = () => {
  // 验证
  if (!sentence.value.trim()) {
    toast({
      title: "错误",
      description: "例句是必填项",
      variant: "destructive",
    })
    return
  }

  // 提交逻辑
  console.log({ sentence: sentence.value, context: context.value, relatedWords: relatedWords.value })

  toast({
    title: "例句已添加",
    description: "例句已成功添加。",
  })

  // 重置表单
  sentence.value = ''
  context.value = ''
  relatedWords.value = []
  
  // 返回列表页
  router.push('/sentences')
}
</script> 