<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center gap-2">
      <RouterLink to="/roots">
        <Button variant="ghost" size="sm">
          <ArrowLeftIcon class="mr-2 h-4 w-4" />
          返回词根列表
        </Button>
      </RouterLink>
      <h1 class="text-3xl font-bold tracking-tight">添加新词根</h1>
    </div>

    <Card>
      <form @submit.prevent="handleSubmit">
        <CardHeader>
          <CardTitle>词根信息</CardTitle>
          <CardDescription>添加新的词根及其定义</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="space-y-2">
              <Label for="root">词根</Label>
              <Input
                id="root"
                v-model="root.root"
                placeholder="输入词根"
                required
              />
            </div>
            <div class="space-y-2">
              <Label for="definition">定义</Label>
              <Input
                id="definition"
                v-model="root.definition"
                placeholder="输入词根定义"
                required
              />
            </div>
          </div>

          <div class="space-y-2">
            <Label for="notes">备注</Label>
            <Textarea
              id="notes"
              v-model="root.notes"
              placeholder="输入关于这个词根的额外信息"
              rows="3"
            />
          </div>

          <div class="space-y-2">
            <Label>相关单词</Label>
            <div class="flex gap-2">
              <Input
                placeholder="添加相关单词"
                v-model="newRelatedWord"
                @keydown.enter.prevent="handleAddRelatedWord"
              />
              <Button type="button" @click="handleAddRelatedWord">
                添加
              </Button>
            </div>
            <div class="flex flex-wrap gap-2 mt-2">
              <Badge 
                v-for="word in root.relatedWords" 
                :key="word" 
                variant="secondary" 
                class="flex items-center gap-1"
              >
                {{ word }}
                <XIcon 
                  class="h-3 w-3 cursor-pointer" 
                  @click="handleRemoveRelatedWord(word)" 
                />
              </Badge>
            </div>
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button variant="outline" type="button" @click="$router.back()">
            取消
          </Button>
          <Button type="submit" :disabled="isLoading">
            {{ isLoading ? "添加中..." : "添加词根" }}
          </Button>
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
import { Badge } from '@/components/ui/badge'
import { 
  ArrowLeft as ArrowLeftIcon,
  X as XIcon 
} from 'lucide-vue-next'

const router = useRouter()
const { toast } = useToast()
const isLoading = ref(false)

const root = ref({
  root: "",
  definition: "",
  relatedWords: [],
  notes: "",
})

const newRelatedWord = ref("")

const handleAddRelatedWord = () => {
  if (newRelatedWord.value.trim() && !root.value.relatedWords.includes(newRelatedWord.value.trim())) {
    root.value.relatedWords.push(newRelatedWord.value.trim())
    newRelatedWord.value = ""
  }
}

const handleRemoveRelatedWord = (word) => {
  root.value.relatedWords = root.value.relatedWords.filter((w) => w !== word)
}

const handleSubmit = () => {
  if (!root.value.root.trim() || !root.value.definition.trim()) {
    toast({
      title: "错误",
      description: "词根和定义为必填项",
      variant: "destructive",
    })
    return
  }

  isLoading.value = true

  // 模拟API请求
  setTimeout(() => {
    isLoading.value = false
    toast({
      title: "词根已添加",
      description: `词根 "${root.value.root}" 已成功添加。`,
    })

    // 重置表单
    root.value = {
      root: "",
      definition: "",
      relatedWords: [],
      notes: "",
    }
    
    // 可选：返回词根列表页面
    // router.push('/roots')
  }, 1000)
}
</script>