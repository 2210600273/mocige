<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center gap-2">
      <RouterLink to="/words">
        <Button variant="ghost" size="sm">
          <ArrowLeftIcon class="mr-2 h-4 w-4" />
          返回单词列表
        </Button>
      </RouterLink>
      <h1 class="text-3xl font-bold tracking-tight">添加新单词</h1>
    </div>

    <Card>
      <form @submit.prevent="handleSubmit">
        <CardHeader>
          <CardTitle>单词信息</CardTitle>
          <CardDescription>输入新词汇的详细信息</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="space-y-2">
              <Label for="word">单词</Label>
              <Input
                id="word"
                placeholder="输入单词"
                v-model="word"
                required
              />
            </div>
            <div class="space-y-2">
              <Label for="pronunciation">发音</Label>
              <Input
                id="pronunciation"
                placeholder="例如 /vəˈkæb.jʊ.lər.i/"
                v-model="pronunciation"
              />
            </div>
          </div>

          <div class="space-y-2">
            <Label for="part-of-speech">词性</Label>
            <Select v-model="partOfSpeech">
              <SelectTrigger id="part-of-speech">
                <SelectValue placeholder="选择词性" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="noun">名词</SelectItem>
                <SelectItem value="verb">动词</SelectItem>
                <SelectItem value="adjective">形容词</SelectItem>
                <SelectItem value="adverb">副词</SelectItem>
                <SelectItem value="preposition">介词</SelectItem>
                <SelectItem value="conjunction">连词</SelectItem>
                <SelectItem value="pronoun">代词</SelectItem>
                <SelectItem value="interjection">感叹词</SelectItem>
              </SelectContent>
            </Select>
          </div>

          <div class="space-y-2">
            <Label for="definition">定义</Label>
            <Textarea
              id="definition"
              placeholder="输入单词的定义"
              rows="4"
              v-model="definition"
            />
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button variant="outline" type="button" @click="$router.back()">
            取消
          </Button>
          <Button type="submit">添加单词</Button>
        </CardFooter>
      </form>
    </Card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Textarea } from '@/components/ui/textarea'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import { ArrowLeft as ArrowLeftIcon } from 'lucide-vue-next'

const router = useRouter()
const { toast } = useToast()
const word = ref('')
const pronunciation = ref('')
const partOfSpeech = ref('')
const definition = ref('')

const handleSubmit = () => {
  // 验证
  if (!word.value.trim()) {
    toast({
      title: "错误",
      description: "单词是必填项",
      variant: "destructive",
    })
    return
  }

  // 提交逻辑
  console.log({ 
    word: word.value, 
    pronunciation: pronunciation.value, 
    partOfSpeech: partOfSpeech.value, 
    definition: definition.value 
  })

  toast({
    title: "单词已添加",
    description: `"${word.value}" 已成功添加。`,
  })

  // 重置表单
  word.value = ''
  pronunciation.value = ''
  partOfSpeech.value = ''
  definition.value = ''
}
</script>