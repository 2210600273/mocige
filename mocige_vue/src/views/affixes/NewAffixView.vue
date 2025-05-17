<template>
  <div class="flex flex-col gap-4">
    <h1 class="text-3xl font-bold tracking-tight">新增词缀</h1>
    
    <Card>
      <CardHeader>
        <CardTitle>词缀信息</CardTitle>
        <CardDescription>添加新的词缀到系统中</CardDescription>
      </CardHeader>
      <form @submit.prevent="handleSubmit">
        <CardContent class="space-y-4">
          <div class="space-y-2">
            <Label for="affix">词缀</Label>
            <Input id="affix" v-model="affix.affix" placeholder="输入词缀" required />
          </div>
          
          <div class="space-y-2">
            <Label for="type">类型</Label>
            <Select v-model="affix.type">
              <SelectTrigger id="type">
                <SelectValue placeholder="选择词缀类型" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="prefix">前缀</SelectItem>
                <SelectItem value="suffix">后缀</SelectItem>
                <SelectItem value="infix">中缀</SelectItem>
              </SelectContent>
            </Select>
          </div>
          
          <div class="space-y-2">
            <Label for="meaning">含义</Label>
            <Input id="meaning" v-model="affix.meaning" placeholder="词缀含义" required />
          </div>
          
          <div class="space-y-2">
            <Label for="origin">词源</Label>
            <Input id="origin" v-model="affix.origin" placeholder="词缀来源" />
          </div>
          
          <div class="space-y-2">
            <Label for="examples">使用示例</Label>
            <Textarea 
              id="examples" 
              v-model="affix.examples" 
              placeholder="输入包含该词缀的单词示例，每行一个" 
              rows="4"
            />
          </div>
          
          <div class="space-y-2">
            <Label for="notes">备注</Label>
            <Textarea 
              id="notes" 
              v-model="affix.notes" 
              placeholder="任何相关说明或备注" 
              rows="3"
            />
          </div>
        </CardContent>
        <CardFooter class="flex justify-between">
          <Button type="button" variant="outline" @click="router.back()">取消</Button>
          <Button type="submit">保存</Button>
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

const router = useRouter()
const { toast } = useToast()

// 词缀数据
const affix = ref({
  affix: '',
  type: 'prefix',
  meaning: '',
  origin: '',
  examples: '',
  notes: ''
})

// 提交表单
const handleSubmit = () => {
  // 这里添加保存逻辑
  console.log('提交词缀:', affix.value)
  
  // 模拟保存成功
  toast({
    title: "添加成功",
    description: `词缀 ${affix.value.affix} 已成功添加到系统`,
  })
  
  // 保存成功后返回列表页
  router.push('/affixes')
}
</script> 