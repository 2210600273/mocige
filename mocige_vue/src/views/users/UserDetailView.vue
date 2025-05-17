<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ArrowLeft, Pencil } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import { Badge } from '@/components/ui/badge'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'

const route = useRoute()
const router = useRouter()
const userId = ref(route.params.id)

// 在实际应用中，您会根据ID获取用户数据
const userData = ref({
  id: parseInt(userId.value),
  username: 'user1',
  email: 'user1@example.com',
  createdAt: '2023-05-15',
  lastLogin: '2023-05-15 09:30:45',
  status: 'active',
  preferences: {
    learningMode: '间隔重复',
    dailyWordCount: 10,
    reminderTime: '08:00 AM',
    theme: '浅色',
  },
  stats: {
    totalStudyTime: '45小时30分钟',
    wordsLearned: 320,
    averageMastery: 4.2,
    testsCompleted: 28,
    averageScore: 85,
  },
  recentActivity: [
    { type: 'word_learned', word: 'vocabulary', date: '2023-05-15 09:15:00' },
    { type: 'test_completed', score: 90, date: '2023-05-14 16:30:00' },
    { type: 'word_reviewed', word: 'ambiguous', date: '2023-05-14 10:45:00' },
    { type: 'word_learned', word: 'conspicuous', date: '2023-05-13 14:20:00' },
    { type: 'word_reviewed', word: 'vocabulary', date: '2023-05-13 09:30:00' },
  ],
  learningProgress: [
    { word: 'vocabulary', masteryLevel: 5, lastReviewed: '2023-05-15' },
    { word: 'ambiguous', masteryLevel: 4, lastReviewed: '2023-05-14' },
    { word: 'conspicuous', masteryLevel: 3, lastReviewed: '2023-05-13' },
    { word: 'meticulous', masteryLevel: 2, lastReviewed: '2023-05-12' },
    { word: 'proliferate', masteryLevel: 1, lastReviewed: '2023-05-11' },
  ],
})
</script>

<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center gap-2">
      <router-link to="/users">
        <Button variant="ghost" size="sm">
          <ArrowLeft class="mr-2 h-4 w-4" />
          返回用户列表
        </Button>
      </router-link>
      <h1 class="text-3xl font-bold tracking-tight">{{ userData.username }}</h1>
      <router-link :to="`/users/${userId}/edit`">
        <Button size="sm" variant="outline">
          <Pencil class="mr-2 h-4 w-4" />
          编辑
        </Button>
      </router-link>
    </div>

    <div class="grid gap-4 md:grid-cols-3">
      <Card class="md:col-span-2">
        <CardHeader>
          <CardTitle>用户信息</CardTitle>
          <CardDescription>基本账户详情</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div class="grid grid-cols-2 gap-4">
            <div>
              <h3 class="text-sm font-medium text-muted-foreground">用户名</h3>
              <p class="text-lg font-medium">{{ userData.username }}</p>
            </div>
            <div>
              <h3 class="text-sm font-medium text-muted-foreground">邮箱</h3>
              <p class="text-lg font-medium">{{ userData.email }}</p>
            </div>
            <div>
              <h3 class="text-sm font-medium text-muted-foreground">创建时间</h3>
              <p class="text-lg font-medium">{{ userData.createdAt }}</p>
            </div>
            <div>
              <h3 class="text-sm font-medium text-muted-foreground">最后登录</h3>
              <p class="text-lg font-medium">{{ userData.lastLogin }}</p>
            </div>
            <div>
              <h3 class="text-sm font-medium text-muted-foreground">状态</h3>
              <Badge
                :class="
                  userData.status === 'active'
                    ? 'bg-green-100 text-green-800 hover:bg-green-100'
                    : 'bg-red-100 text-red-800 hover:bg-red-100'
                "
              >
                {{ userData.status === 'active' ? '活跃' : '非活跃' }}
              </Badge>
            </div>
          </div>
        </CardContent>
      </Card>

      <Card>
        <CardHeader>
          <CardTitle>学习统计</CardTitle>
          <CardDescription>用户的学习进度</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div>
            <h3 class="text-sm font-medium text-muted-foreground">总学习时间</h3>
            <p class="text-lg font-medium">{{ userData.stats.totalStudyTime }}</p>
          </div>
          <div>
            <h3 class="text-sm font-medium text-muted-foreground">已学单词</h3>
            <p class="text-lg font-medium">{{ userData.stats.wordsLearned }}</p>
          </div>
          <div>
            <h3 class="text-sm font-medium text-muted-foreground">平均掌握度</h3>
            <div class="flex items-center gap-2 mt-1">
              <div class="relative w-full h-2 bg-muted rounded-full overflow-hidden">
                <div
                  class="absolute top-0 left-0 h-full bg-primary"
                  :style="{ width: `${(userData.stats.averageMastery / 5) * 100}%` }"
                />
              </div>
              <span>{{ userData.stats.averageMastery }}/5</span>
            </div>
          </div>
          <div>
            <h3 class="text-sm font-medium text-muted-foreground">已完成测试</h3>
            <p class="text-lg font-medium">{{ userData.stats.testsCompleted }}</p>
          </div>
          <div>
            <h3 class="text-sm font-medium text-muted-foreground">平均测试分数</h3>
            <p class="text-lg font-medium">{{ userData.stats.averageScore }}%</p>
          </div>
        </CardContent>
      </Card>
    </div>

    <Tabs default-value="preferences" class="mt-4">
      <TabsList>
        <TabsTrigger value="preferences">偏好设置</TabsTrigger>
        <TabsTrigger value="activity">最近活动</TabsTrigger>
        <TabsTrigger value="progress">学习进度</TabsTrigger>
      </TabsList>
      <TabsContent value="preferences" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>用户偏好</CardTitle>
            <CardDescription>此用户的个性化设置</CardDescription>
          </CardHeader>
          <CardContent>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <h3 class="text-sm font-medium text-muted-foreground">学习模式</h3>
                <p class="text-lg font-medium">{{ userData.preferences.learningMode }}</p>
              </div>
              <div>
                <h3 class="text-sm font-medium text-muted-foreground">每日单词数量</h3>
                <p class="text-lg font-medium">{{ userData.preferences.dailyWordCount }} 个单词</p>
              </div>
              <div>
                <h3 class="text-sm font-medium text-muted-foreground">提醒时间</h3>
                <p class="text-lg font-medium">{{ userData.preferences.reminderTime }}</p>
              </div>
              <div>
                <h3 class="text-sm font-medium text-muted-foreground">主题</h3>
                <p class="text-lg font-medium">{{ userData.preferences.theme }}</p>
              </div>
            </div>
          </CardContent>
        </Card>
      </TabsContent>
      <TabsContent value="activity" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>最近活动</CardTitle>
            <CardDescription>用户的最近操作和进度</CardDescription>
          </CardHeader>
          <CardContent>
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>活动</TableHead>
                  <TableHead>详情</TableHead>
                  <TableHead>日期</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                <TableRow v-for="(activity, index) in userData.recentActivity" :key="index">
                  <TableCell class="font-medium">
                    {{ 
                      activity.type === 'word_learned' 
                        ? '学习单词' 
                        : activity.type === 'word_reviewed' 
                          ? '复习单词' 
                          : '完成测试' 
                    }}
                  </TableCell>
                  <TableCell>
                    {{ 
                      activity.type === 'test_completed' 
                        ? `分数: ${activity.score}%` 
                        : `单词: ${activity.word}` 
                    }}
                  </TableCell>
                  <TableCell>{{ activity.date }}</TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </CardContent>
        </Card>
      </TabsContent>
      <TabsContent value="progress" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>学习进度</CardTitle>
            <CardDescription>用户的单词掌握情况</CardDescription>
          </CardHeader>
          <CardContent>
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>单词</TableHead>
                  <TableHead>掌握程度</TableHead>
                  <TableHead>最后复习</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                <TableRow v-for="(progress, index) in userData.learningProgress" :key="index">
                  <TableCell class="font-medium">{{ progress.word }}</TableCell>
                  <TableCell>
                    <div class="flex items-center gap-2">
                      <div class="relative w-24 h-2 bg-muted rounded-full overflow-hidden">
                        <div
                          class="absolute top-0 left-0 h-full bg-primary"
                          :style="{ width: `${(progress.masteryLevel / 5) * 100}%` }"
                        />
                      </div>
                      <span>{{ progress.masteryLevel }}/5</span>
                    </div>
                  </TableCell>
                  <TableCell>{{ progress.lastReviewed }}</TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </CardContent>
        </Card>
      </TabsContent>
    </Tabs>
  </div>
</template>