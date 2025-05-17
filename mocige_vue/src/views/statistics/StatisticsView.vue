<template>
  <div class="flex flex-col gap-4">
    <h1 class="text-3xl font-bold tracking-tight">统计数据</h1>

    <div class="flex flex-col md:flex-row gap-4 justify-between items-center">
      <p class="text-muted-foreground">
        查看和分析用户学习模式和词汇使用统计数据。
      </p>
      <div class="flex gap-2">
        <Select v-model="timePeriod">
          <SelectTrigger class="w-[180px]">
            <SelectValue placeholder="时间段" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="day">最近24小时</SelectItem>
            <SelectItem value="week">最近7天</SelectItem>
            <SelectItem value="month">最近30天</SelectItem>
            <SelectItem value="year">最近一年</SelectItem>
            <SelectItem value="all">所有时间</SelectItem>
          </SelectContent>
        </Select>
      </div>
    </div>

    <div class="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
      <StatCard title="总学习时间" value="1,234 小时" change="+12.5%" />
      <StatCard title="已学习单词" value="8,765" change="+8.2%" />
      <StatCard title="已完成测试" value="1,543" change="+15.3%" />
      <StatCard title="活跃用户" value="432" change="+5.7%" />
    </div>

    <Tabs v-model="activeTab" class="space-y-4">
      <TabsList>
        <TabsTrigger value="overview">概览</TabsTrigger>
        <TabsTrigger value="users">用户统计</TabsTrigger>
        <TabsTrigger value="words">单词统计</TabsTrigger>
        <TabsTrigger value="learning">学习模式</TabsTrigger>
      </TabsList>

      <TabsContent value="overview" class="space-y-4">
        <div class="grid gap-4 md:grid-cols-2">
          <Card>
            <CardHeader>
              <CardTitle>每日活跃用户</CardTitle>
              <CardDescription>每天活跃用户数量</CardDescription>
            </CardHeader>
            <CardContent class="h-[300px] flex items-center justify-center">
              <div class="text-sm text-muted-foreground">每日活跃用户图表占位符</div>
            </CardContent>
          </Card>
          <Card>
            <CardHeader>
              <CardTitle>已学习单词</CardTitle>
              <CardDescription>随时间推移学习的单词数量</CardDescription>
            </CardHeader>
            <CardContent class="h-[300px] flex items-center justify-center">
              <div class="text-sm text-muted-foreground">已学习单词图表占位符</div>
            </CardContent>
          </Card>
        </div>

        <Card>
          <CardHeader>
            <CardTitle>按小时的学习活动</CardTitle>
            <CardDescription>用户在一天中最活跃的时间</CardDescription>
          </CardHeader>
          <CardContent class="h-[300px] flex items-center justify-center">
            <div class="text-sm text-muted-foreground">小时活动图表占位符</div>
          </CardContent>
        </Card>
      </TabsContent>

      <TabsContent value="users" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>用户统计</CardTitle>
            <CardDescription>个别用户的详细统计数据</CardDescription>
          </CardHeader>
          <CardContent>
            <div class="space-y-4">
              <div class="flex justify-between items-center pb-2">
                <Input placeholder="搜索用户..." class="max-w-sm" v-model="userSearchQuery" />
              </div>
              <Table>
                <TableHeader>
                  <TableRow>
                    <TableHead>用户</TableHead>
                    <TableHead>已学习单词</TableHead>
                    <TableHead>已完成测试</TableHead>
                    <TableHead>平均分数</TableHead>
                    <TableHead>学习时间</TableHead>
                  </TableRow>
                </TableHeader>
                <TableBody>
                  <TableRow v-for="user in userStats" :key="user.id">
                    <TableCell>{{ user.name }}</TableCell>
                    <TableCell>{{ user.wordsLearned }}</TableCell>
                    <TableCell>{{ user.testsCompleted }}</TableCell>
                    <TableCell>{{ user.averageScore }}%</TableCell>
                    <TableCell>{{ user.studyTime }} 小时</TableCell>
                  </TableRow>
                </TableBody>
              </Table>
            </div>
          </CardContent>
        </Card>
      </TabsContent>

      <TabsContent value="words" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>单词统计</CardTitle>
            <CardDescription>最受欢迎和最难的单词</CardDescription>
          </CardHeader>
          <CardContent>
            <div class="space-y-4">
              <div class="flex justify-between items-center pb-2">
                <Input placeholder="搜索单词..." class="max-w-sm" v-model="wordSearchQuery" />
                <Select v-model="wordFilter">
                  <SelectTrigger class="w-[180px]">
                    <SelectValue placeholder="筛选依据" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="popular">最受欢迎</SelectItem>
                    <SelectItem value="difficult">最困难</SelectItem>
                    <SelectItem value="recent">最近添加</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              <Table>
                <TableHeader>
                  <TableRow>
                    <TableHead>单词</TableHead>
                    <TableHead>学习次数</TableHead>
                    <TableHead>正确率</TableHead>
                    <TableHead>难度</TableHead>
                  </TableRow>
                </TableHeader>
                <TableBody>
                  <TableRow v-for="word in wordStats" :key="word.id">
                    <TableCell>{{ word.word }}</TableCell>
                    <TableCell>{{ word.timesPracticed }}</TableCell>
                    <TableCell>{{ word.correctPercentage }}%</TableCell>
                    <TableCell>
                      <div class="flex items-center gap-2">
                        <div class="relative w-full h-2 bg-muted rounded-full overflow-hidden">
                          <div
                            class="absolute top-0 left-0 h-full"
                            :class="getDifficultyClass(word.difficulty)"
                            :style="{ width: `${word.difficulty}%` }"
                          ></div>
                        </div>
                        <span class="text-xs font-medium whitespace-nowrap">{{ word.difficulty }}%</span>
                      </div>
                    </TableCell>
                  </TableRow>
                </TableBody>
              </Table>
            </div>
          </CardContent>
        </Card>
      </TabsContent>

      <TabsContent value="learning" class="space-y-4">
        <div class="grid gap-4 md:grid-cols-2">
          <Card>
            <CardHeader>
              <CardTitle>学习曲线</CardTitle>
              <CardDescription>随时间推移的平均掌握进度</CardDescription>
            </CardHeader>
            <CardContent class="h-[300px] flex items-center justify-center">
              <div class="text-sm text-muted-foreground">学习曲线图表占位符</div>
            </CardContent>
          </Card>
          <Card>
            <CardHeader>
              <CardTitle>记忆保留率</CardTitle>
              <CardDescription>用户随时间记忆单词的情况</CardDescription>
            </CardHeader>
            <CardContent class="h-[300px] flex items-center justify-center">
              <div class="text-sm text-muted-foreground">记忆保留率图表占位符</div>
            </CardContent>
          </Card>
        </div>

        <Card>
          <CardHeader>
            <CardTitle>学习方法效果</CardTitle>
            <CardDescription>不同学习方法的比较</CardDescription>
          </CardHeader>
          <CardContent class="h-[300px] flex items-center justify-center">
            <div class="text-sm text-muted-foreground">学习方法图表占位符</div>
          </CardContent>
        </Card>
      </TabsContent>
    </Tabs>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import { Input } from '@/components/ui/input'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'

const timePeriod = ref('week')
const activeTab = ref('overview')
const userSearchQuery = ref('')
const wordSearchQuery = ref('')
const wordFilter = ref('popular')

// 模拟用户数据
const userStats = ref([
  { id: 1, name: "用户 1", wordsLearned: 542, testsCompleted: 48, averageScore: 92, studyTime: 85 },
  { id: 2, name: "用户 2", wordsLearned: 423, testsCompleted: 35, averageScore: 87, studyTime: 64 },
  { id: 3, name: "用户 3", wordsLearned: 390, testsCompleted: 28, averageScore: 78, studyTime: 56 },
  { id: 4, name: "用户 4", wordsLearned: 328, testsCompleted: 22, averageScore: 75, studyTime: 42 },
  { id: 5, name: "用户 5", wordsLearned: 287, testsCompleted: 19, averageScore: 81, studyTime: 38 },
])

// 模拟单词数据
const wordStats = ref([
  { id: 1, word: "vocabulary", timesPracticed: 345, correctPercentage: 82, difficulty: 65 },
  { id: 2, word: "conspicuous", timesPracticed: 298, correctPercentage: 68, difficulty: 78 },
  { id: 3, word: "ambiguous", timesPracticed: 321, correctPercentage: 72, difficulty: 75 },
  { id: 4, word: "meticulous", timesPracticed: 276, correctPercentage: 65, difficulty: 82 },
  { id: 5, word: "proliferate", timesPracticed: 312, correctPercentage: 70, difficulty: 76 },
])

const getDifficultyClass = (difficulty) => {
  if (difficulty >= 80) return 'bg-red-500'
  if (difficulty >= 70) return 'bg-yellow-500'
  if (difficulty >= 60) return 'bg-blue-500'
  return 'bg-green-500'
}
</script>

<script>
// 统计卡片组件
export default {
  components: {
    StatCard: {
      props: {
        title: String,
        value: String,
        change: String,
      },
      setup(props) {
        const isPositive = props.change.startsWith("+")
        
        return { isPositive }
      },
      template: `
        <Card>
          <CardHeader class="flex flex-row items-center justify-between space-y-0 pb-2">
            <CardTitle class="text-sm font-medium">{{ title }}</CardTitle>
          </CardHeader>
          <CardContent>
            <div class="text-2xl font-bold">{{ value }}</div>
            <p :class="['text-xs', isPositive ? 'text-green-500' : 'text-red-500']">
              {{ change }} 相比上一时段
            </p>
          </CardContent>
        </Card>
      `
    }
  }
}
</script>
