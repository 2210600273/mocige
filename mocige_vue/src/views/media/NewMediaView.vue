<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center gap-2">
      <router-link to="/media">
        <Button variant="ghost" size="sm">
          <ArrowLeft class="mr-2 h-4 w-4" />
          返回媒体列表
        </Button>
      </router-link>
      <h1 class="text-3xl font-bold tracking-tight">添加新媒体</h1>
    </div>

    <Card>
      <form @submit.prevent="handleSubmit">
        <CardHeader>
          <CardTitle>媒体信息</CardTitle>
          <CardDescription>为词汇上传媒体文件。</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div class="space-y-2">
            <Label for="word">单词</Label>
            <Input
              id="word"
              placeholder="输入该媒体所属的单词"
              v-model="word"
              required
            />
          </div>

          <div class="space-y-2">
            <Label for="media-type">媒体类型</Label>
            <Select v-model="mediaType">
              <SelectTrigger id="media-type">
                <SelectValue placeholder="选择媒体类型" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="image">图片</SelectItem>
                <SelectItem value="audio">音频</SelectItem>
                <SelectItem value="video">视频</SelectItem>
              </SelectContent>
            </Select>
          </div>

          <div class="space-y-2">
            <Label for="file">上传文件</Label>
            <div class="flex items-center gap-4">
              <Input
                id="file"
                type="file"
                @change="handleFileChange"
                :accept="acceptedFileTypes"
                class="flex-1"
              />
            </div>
          </div>

          <div v-if="previewUrl" class="mt-4">
            <Label>预览</Label>
            <div class="mt-2 border rounded-md p-2 flex justify-center">
              <img :src="previewUrl" alt="预览" class="max-h-[200px] object-contain" />
            </div>
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button variant="outline" type="button" @click="router.back()">
            取消
          </Button>
          <Button type="submit">
            <Upload class="mr-2 h-4 w-4" />
            上传媒体
          </Button>
        </CardFooter>
      </form>
    </Card>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { ArrowLeft, Upload } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'

const router = useRouter()
const { toast } = useToast()
const word = ref('')
const mediaType = ref('')
const file = ref(null)
const previewUrl = ref(null)

const acceptedFileTypes = computed(() => {
  if (mediaType.value === 'image') return 'image/*'
  if (mediaType.value === 'audio') return 'audio/*'
  if (mediaType.value === 'video') return 'video/*'
  return undefined
})

const handleFileChange = (e) => {
  if (e.target.files && e.target.files[0]) {
    const selectedFile = e.target.files[0]
    file.value = selectedFile

    // 为图片创建预览
    if (selectedFile.type.startsWith('image/')) {
      const reader = new FileReader()
      reader.onload = (event) => {
        previewUrl.value = event.target?.result
      }
      reader.readAsDataURL(selectedFile)
    } else {
      previewUrl.value = null
    }
  }
}

const handleSubmit = () => {
  // 验证
  if (!word.value.trim() || !mediaType.value || !file.value) {
    toast({
      title: "错误",
      description: "所有字段都是必填的",
      variant: "destructive",
    })
    return
  }

  // 提交逻辑
  console.log({ 
    word: word.value, 
    mediaType: mediaType.value, 
    file: file.value 
  })

  toast({
    title: "媒体已添加",
    description: `"${word.value}" 的媒体已成功上传。`,
  })

  // 重置表单
  word.value = ''
  mediaType.value = ''
  file.value = null
  previewUrl.value = null
  
  // 返回媒体列表
  router.push('/media')
}
</script>
