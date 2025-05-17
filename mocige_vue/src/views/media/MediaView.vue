<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center justify-between">
      <h1 class="text-3xl font-bold tracking-tight">媒体管理</h1>
      <router-link to="/media/new">
        <Button>
          <PlusCircle class="mr-2 h-4 w-4" />
          添加媒体
        </Button>
      </router-link>
    </div>

    <Card>
      <CardHeader>
        <CardTitle>媒体管理</CardTitle>
        <CardDescription>管理单词的图片、音频和其他媒体。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex flex-col md:flex-row gap-4 justify-between items-center pb-4">
          <Input placeholder="搜索媒体..." class="md:max-w-sm w-full" v-model="searchQuery" />
          <Select v-model="selectedType">
            <SelectTrigger class="w-[180px]">
              <SelectValue placeholder="媒体类型" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="all">所有类型</SelectItem>
              <SelectItem value="image">图片</SelectItem>
              <SelectItem value="audio">音频</SelectItem>
              <SelectItem value="video">视频</SelectItem>
            </SelectContent>
          </Select>
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>单词</TableHead>
              <TableHead>媒体类型</TableHead>
              <TableHead>预览</TableHead>
              <TableHead>URL</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="media in mediaItems" :key="media.id">
              <TableCell>{{ media.id }}</TableCell>
              <TableCell>{{ media.word }}</TableCell>
              <TableCell>
                <span
                  class="px-2 py-1 text-xs rounded-full"
                  :class="{
                    'bg-blue-100 text-blue-800': media.mediaType === 'image',
                    'bg-green-100 text-green-800': media.mediaType === 'audio',
                    'bg-purple-100 text-purple-800': media.mediaType === 'video'
                  }"
                >
                  {{ 
                    media.mediaType === 'image' ? '图片' : 
                    media.mediaType === 'audio' ? '音频' : '视频' 
                  }}
                </span>
              </TableCell>
              <TableCell>
                <img
                  v-if="media.mediaType === 'image'"
                  :src="media.url || '/placeholder.svg'"
                  :alt="`${media.word} 的预览`"
                  class="h-10 w-16 object-cover rounded"
                />
                <div
                  v-else-if="media.mediaType === 'audio'"
                  class="h-10 w-16 flex items-center justify-center bg-secondary rounded"
                >
                  <span class="text-xs">音频</span>
                </div>
                <div
                  v-else
                  class="h-10 w-16 flex items-center justify-center bg-secondary rounded"
                >
                  <span class="text-xs">视频</span>
                </div>
              </TableCell>
              <TableCell class="max-w-[200px] truncate">{{ media.url }}</TableCell>
              <TableCell>
                <DropdownMenu>
                  <DropdownMenuTrigger as-child>
                    <Button variant="ghost" class="h-8 w-8 p-0">
                      <span class="sr-only">打开菜单</span>
                      <MoreHorizontal class="h-4 w-4" />
                    </Button>
                  </DropdownMenuTrigger>
                  <DropdownMenuContent align="end">
                    <DropdownMenuItem @click="openPreviewDialog(media)">
                      <Eye class="mr-2 h-4 w-4" />
                      预览
                    </DropdownMenuItem>
                    <DropdownMenuItem @click="router.push(`/media/${media.id}/edit`)">
                      <Pencil class="mr-2 h-4 w-4" />
                      编辑
                    </DropdownMenuItem>
                    <DropdownMenuItem @click="handleDownload(media)">
                      <Download class="mr-2 h-4 w-4" />
                      下载
                    </DropdownMenuItem>
                    <DropdownMenuItem
                      class="text-destructive focus:text-destructive"
                      @click="openDeleteDialog(media)"
                    >
                      <Trash class="mr-2 h-4 w-4" />
                      删除
                    </DropdownMenuItem>
                  </DropdownMenuContent>
                </DropdownMenu>
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
            此操作无法撤消。这将永久从服务器和数据库中删除此媒体文件。
          </AlertDialogDescription>
        </AlertDialogHeader>
        <AlertDialogFooter>
          <AlertDialogCancel>取消</AlertDialogCancel>
          <AlertDialogAction
            @click="confirmDelete"
            class="bg-destructive text-destructive-foreground hover:bg-destructive/90"
          >
            删除
          </AlertDialogAction>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>

    <!-- 预览对话框 -->
    <Dialog :open="showPreviewDialog" @update:open="showPreviewDialog = $event">
      <DialogContent class="sm:max-w-md">
        <DialogHeader>
          <DialogTitle>媒体预览</DialogTitle>
          <DialogDescription v-if="selectedMedia">
            {{ selectedMedia.word }} 的{{ 
              selectedMedia.mediaType === 'image' ? '图片' : 
              selectedMedia.mediaType === 'audio' ? '音频' : '视频' 
            }}
          </DialogDescription>
        </DialogHeader>
        <div class="flex items-center justify-center p-4" v-if="selectedMedia">
          <img
            v-if="selectedMedia.mediaType === 'image'"
            :src="selectedMedia.url || '/placeholder.svg'"
            :alt="`${selectedMedia.word} 的预览`"
            class="max-h-[300px] object-contain"
          />
          <audio
            v-else-if="selectedMedia.mediaType === 'audio'"
            controls
            class="w-full"
          >
            <source :src="selectedMedia.url" type="audio/mpeg" />
            您的浏览器不支持音频元素。
          </audio>
          <video
            v-else
            controls
            class="max-h-[300px]"
          >
            <source :src="selectedMedia.url" type="video/mp4" />
            您的浏览器不支持视频元素。
          </video>
        </div>
      </DialogContent>
    </Dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { 
  PlusCircle, 
  MoreHorizontal, 
  Eye, 
  Pencil, 
  Trash, 
  Download 
} from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from '@/components/ui/dropdown-menu'
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from '@/components/ui/alert-dialog'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle
} from '@/components/ui/dialog'

const router = useRouter()
const { toast } = useToast()
const searchQuery = ref('')
const selectedType = ref('all')
const showDeleteDialog = ref(false)
const showPreviewDialog = ref(false)
const selectedMedia = ref(null)

// 示例数据
const mediaItems = ref([
  {
    id: 1,
    word: "vocabulary",
    mediaType: "image",
    url: "/placeholder.svg?height=80&width=120",
  },
  {
    id: 2,
    word: "ambiguous",
    mediaType: "image",
    url: "/placeholder.svg?height=80&width=120",
  },
  {
    id: 3,
    word: "conspicuous",
    mediaType: "audio",
    url: "https://example.com/audio/conspicuous.mp3",
  },
  {
    id: 4,
    word: "meticulous",
    mediaType: "audio",
    url: "https://example.com/audio/meticulous.mp3",
  },
  {
    id: 5,
    word: "proliferate",
    mediaType: "video",
    url: "https://example.com/video/proliferate.mp4",
  },
])

const openDeleteDialog = (media) => {
  selectedMedia.value = media
  showDeleteDialog.value = true
}

const confirmDelete = () => {
  // 删除逻辑
  console.log(`删除媒体: ${selectedMedia.value.id}`)
  
  // 从列表中移除
  mediaItems.value = mediaItems.value.filter(item => item.id !== selectedMedia.value.id)
  
  toast({
    title: "媒体已删除",
    description: "媒体文件已成功从服务器删除。",
  })
  
  showDeleteDialog.value = false
  selectedMedia.value = null
}

const openPreviewDialog = (media) => {
  selectedMedia.value = media
  showPreviewDialog.value = true
}

const handleDownload = (media) => {
  console.log(`下载媒体: ${media.url}`)
  
  toast({
    title: "开始下载",
    description: `正在下载 ${media.word} 的媒体文件。`,
  })
  
  // 在实际应用中，这里应该有实际的下载逻辑
}
</script>
