<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center gap-2">
      <RouterLink :to="`/words/${$route.params.id}`">
        <Button variant="ghost" size="sm">
          <ArrowLeftIcon class="mr-2 h-4 w-4" />
          返回单词详情
        </Button>
      </RouterLink>
      <h1 class="text-3xl font-bold tracking-tight">编辑单词</h1>
    </div>

    <Card>
      <form @submit.prevent="handleSubmit">
        <CardHeader>
          <CardTitle>单词信息</CardTitle>
          <CardDescription>编辑单词及其定义</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div class="space-y-2">
              <Label for="word">单词</Label>
              <Input
                id="word"
                v-model="word.word"
                required
              />
            </div>
            <div class="space-y-2">
              <Label for="pronunciation">发音</Label>
              <Input
                id="pronunciation"
                v-model="word.pronunciation"
                placeholder="例如: /vəˈkæb.jʊ.lər.i/"
              />
            </div>
            <div class="space-y-2">
              <Label for="partOfSpeech">词性</Label>
              <Select v-model="word.partOfSpeech">
                <SelectTrigger id="partOfSpeech">
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
          </div>

          <div class="space-y-2">
            <Label for="definition">定义</Label>
            <Textarea
              id="definition"
              v-model="word.definition"
              placeholder="输入单词定义"
              rows="3"
              required
            />
          </div>

          <div class="space-y-2">
            <Label for="difficulty">难度</Label>
            <Select v-model="word.difficulty">
              <SelectTrigger id="difficulty">
                <SelectValue placeholder="选择难度" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="Easy">简单</SelectItem>
                <SelectItem value="Medium">中等</SelectItem>
                <SelectItem value="Hard">困难</SelectItem>
                <SelectItem value="Very Hard">非常困难</SelectItem>
              </SelectContent>
            </Select>
          </div>

          <div class="space-y-2">
            <Label>同义词</Label>
            <div class="flex gap-2">
              <Input
                placeholder="添加同义词"
                v-model="newSynonym"
                @keydown.enter.prevent="handleAddSynonym"
              />
              <Button type="button" @click="handleAddSynonym">
                添加
              </Button>
            </div>
            <div class="flex flex-wrap gap-2 mt-2">
              <Badge 
                v-for="synonym in word.synonyms" 
                :key="synonym" 
                variant="secondary" 
                class="flex items-center gap-1"
              >
                {{ synonym }}
                <XIcon 
                  class="h-3 w-3 cursor-pointer" 
                  @click="handleRemoveSynonym(synonym)" 
                />
              </Badge>
            </div>
          </div>

          <div class="space-y-2">
            <Label>反义词</Label>
            <div class="flex gap-2">
              <Input
                placeholder="添加反义词"
                v-model="newAntonym"
                @keydown.enter.prevent="handleAddAntonym"
              />
              <Button type="button" @click="handleAddAntonym">
                添加
              </Button>
            </div>
            <div class="flex flex-wrap gap-2 mt-2">
              <Badge 
                v-for="antonym in word.antonyms" 
                :key="antonym" 
                variant="secondary" 
                class="flex items-center gap-1"
              >
                {{ antonym }}
                <XIcon 
                  class="h-3 w-3 cursor-pointer" 
                  @click="handleRemoveAntonym(antonym)" 
                />
              </Badge>
            </div>
          </div>

          <div class="space-y-2">
            <Label>例句</Label>
            <div class="flex gap-2">
              <Input
                placeholder="添加例句"
                v-model="newExample"
                @keydown.enter.prevent="handleAddExample"
              />
              <Button type="button" @click="handleAddExample">
                添加
              </Button>
            </div>
            <div class="space-y-2 mt-2">
              <div 
                v-for="example in word.examples" 
                :key="example" 
                class="flex items-center justify-between p-2 bg-muted rounded-md"
              >
                <span>{{ example }}</span>
                <Button 
                  variant="ghost" 
                  size="sm" 
                  @click="handleRemoveExample(example)"
                >
                  <XIcon class="h-4 w-4" />
                </Button>
              </div>
            </div>
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button variant="outline" type="button" @click="$router.back()">
            取消
          </Button>
          <Button type="submit" :disabled="isLoading">
            <Loader2Icon v-if="isLoading" class="mr-2 h-4 w-4 animate-spin" />
            保存更改
          </Button>
        </CardFooter>
      </form>
    </Card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Textarea } from '@/components/ui/textarea'
import { Badge } from '@/components/ui/badge'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import { ArrowLeft as ArrowLeftIcon, X as XIcon, Loader2 as Loader2Icon } from 'lucide-vue-next'

const route = useRoute()
const router = useRouter()
const { toast } = useToast()
const isLoading = ref(false)

// 模拟从API获取单词数据
const word = ref({
  id: Number.parseInt(route.params.id),
  word: "vocabulary",
  pronunciation: "/vəˈkæb.jʊ.lər.i/",
  partOfSpeech: "noun",
  definition: "All the words known and used by a person or a particular group of people.",
  synonyms: ["lexicon", "terminology", "language", "words"],
  antonyms: [],
  examples: [
    "He has an extensive vocabulary.",
    "The book will help you improve your vocabulary.",
    "Technical vocabulary is used in specialized fields.",
  ],
  difficulty: "Medium",
})

const newSynonym = ref('')
const newAntonym = ref('')
const newExample = ref('')

const handleAddSynonym = () => {
  if (newSynonym.value.trim() && !word.value.synonyms.includes(newSynonym.value.trim())) {
    word.value.synonyms.push(newSynonym.value.trim())
    newSynonym.value = ''
  }
}

const handleRemoveSynonym = (synonym) => {
  word.value.synonyms = word.value.synonyms.filter(s => s !== synonym)
}

const handleAddAntonym = () => {
  if (newAntonym.value.trim() && !word.value.antonyms.includes(newAntonym.value.trim())) {
    word.value.antonyms.push(newAntonym.value.trim())
    newAntonym.value = ''
  }
}

const handleRemoveAntonym = (antonym) => {
  word.value.antonyms = word.value.antonyms.filter(a => a !== antonym)
}

const handleAddExample = () => {
  if (newExample.value.trim() && !word.value.examples.includes(newExample.value.trim())) {
    word.value.examples.push(newExample.value.trim())
    newExample.value = ''
  }
}

const handleRemoveExample = (example) => {
  word.value.examples = word.value.examples.filter(e => e !== example)
}

const handleSubmit = () => {
  isLoading.value = true

  // 模拟API请求
  setTimeout(() => {
    isLoading.value = false
    toast({
      title: "单词已更新",
      description: `单词 "${word.value.word}" 已成功更新。`,
    })
  }, 1000)
}

onMounted(() => {
  // 在实际应用中，这里会根据ID从API获取单词数据
  console.log(`加载ID为 ${route.params.id} 的单词数据进行编辑`)
})
</script>