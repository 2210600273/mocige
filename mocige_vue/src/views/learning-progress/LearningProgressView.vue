<template>
  <div class="flex flex-col gap-4">
    <h1 class="text-3xl font-bold tracking-tight">学习进度</h1>

    <Card>
      <CardHeader>
        <CardTitle>用户学习进度</CardTitle>
        <CardDescription>跟踪和管理用户的词汇学习进度。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex flex-col md:flex-row gap-4 justify-between items-center pb-4">
          <Input placeholder="搜索用户或单词..." class="md:max-w-sm w-full" v-model="searchQuery" />
          <div class="flex gap-2">
            <Select v-model="selectedUser">
              <SelectTrigger class="w-[180px]">
                <SelectValue placeholder="按用户筛选" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">所有用户</SelectItem>
                <SelectItem value="1">用户 1</SelectItem>
                <SelectItem value="2">用户 2</SelectItem>
                <SelectItem value="3">用户 3</SelectItem>
              </SelectContent>
            </Select>
            <Select v-model="selectedMasteryLevel">
              <SelectTrigger class="w-[180px]">
                <SelectValue placeholder="掌握级别" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">所有级别</SelectItem>
                <SelectItem value="1">级别 1</SelectItem>
                <SelectItem value="2">级别 2</SelectItem>
                <SelectItem value="3">级别 3</SelectItem>
                <SelectItem value="4">级别 4</SelectItem>
                <SelectItem value="5">级别 5</SelectItem>
              </SelectContent>
            </Select>
          </div>
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>用户</TableHead>
              <TableHead>单词</TableHead>
              <TableHead>上次复习</TableHead>
              <TableHead>复习次数</TableHead>
              <TableHead>掌握级别</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="progress in progressData" :key="progress.id">
              <TableCell>{{ progress.id }}</TableCell>
              <TableCell>{{ progress.user }}</TableCell>
              <TableCell>{{ progress.word }}</TableCell>
              <TableCell>{{ progress.lastReviewed }}</TableCell>
              <TableCell>{{ progress.reviewsCount }}</TableCell>
              <TableCell>
                <div class="flex items-center gap-2">
                  <div class="relative w-full h-2 bg-muted rounded-full overflow-hidden">
                    <div
                      class="absolute top-0 left-0 h-full bg-primary"
                      :style="{ width: `${(progress.masteryLevel / 5) * 100}%` }"
                    />
                  </div>
                  <span class="text-xs font-medium whitespace-nowrap">级别 {{ progress.masteryLevel }}</span>
                </div>
              </TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </CardContent>
    </Card>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import { Input } from '@/components/ui/input'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'

const searchQuery = ref('')
const selectedUser = ref('all')
const selectedMasteryLevel = ref('all')

// 模拟数据
const progressData = ref([
  {
    id: 1,
    user: "用户 1",
    word: "vocabulary",
    lastReviewed: "2023-05-14",
    reviewsCount: 12,
    masteryLevel: 4,
  },
  {
    id: 2,
    user: "用户 2",
    word: "ambiguous",
    lastReviewed: "2023-05-14",
    reviewsCount: 8,
    masteryLevel: 3,
  },
  {
    id: 3,
    user: "用户 1",
    word: "conspicuous",
    lastReviewed: "2023-05-13",
    reviewsCount: 5,
    masteryLevel: 2,
  },
  {
    id: 4,
    user: "用户 3",
    word: "meticulous",
    lastReviewed: "2023-05-12",
    reviewsCount: 15,
    masteryLevel: 5,
  },
  {
    id: 5,
    user: "用户 2",
    word: "proliferate",
    lastReviewed: "2023-05-10",
    reviewsCount: 7,
    masteryLevel: 3,
  },
])
</script>
