<template>
  <div class="flex flex-col gap-4">
    <div class="flex flex-col md:flex-row md:items-center md:justify-between gap-2 mb-6">
      <div>
        <h1 class="text-3xl font-bold tracking-tight">仪表盘</h1>
        <p class="text-muted-foreground">欢迎使用Mocige管理后台。查看系统概览和关键指标。</p>
      </div>
      <div class="flex gap-2">
        <Button variant="outline" size="sm">
          <Clock class="mr-2 h-4 w-4" />
          今日
        </Button>
        <Button variant="outline" size="sm">
          本周
        </Button>
        <Button variant="outline" size="sm">
          本月
        </Button>
        <Button variant="outline" size="sm">
          全部
        </Button>
      </div>
    </div>

    <div v-if="loading" class="flex justify-center items-center h-64">
      <div class="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-primary"></div>
    </div>

    <div v-else class="flex flex-col gap-4">
      <div class="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
        <DashboardCard
          v-for="stat in stats"
          :key="stat.id"
          :title="stat.name"
          :value="stat.value"
          :change="stat.change"
          :trend="stat.changeType === 'increase' ? 'up' : 'down'"
          :icon="stat.icon"
        />
      </div>

      <Tabs default-value="overview" class="space-y-4">
        <TabsList>
          <TabsTrigger value="overview">概览</TabsTrigger>
          <TabsTrigger value="analytics">分析</TabsTrigger>
          <TabsTrigger value="activity">最近活动</TabsTrigger>
        </TabsList>

        <TabsContent value="overview" class="space-y-4">
          <div class="grid gap-4 md:grid-cols-2 lg:grid-cols-7">
            <Card class="col-span-4">
              <CardHeader>
                <CardTitle>用户增长</CardTitle>
                <CardDescription>用户数量随时间变化</CardDescription>
              </CardHeader>
              <CardContent class="h-[300px] flex items-center justify-center border-t pt-4">
                <div class="text-sm text-muted-foreground">用户增长图表占位符</div>
              </CardContent>
            </Card>
            <Card class="col-span-3">
              <CardHeader>
                <CardTitle>学习统计</CardTitle>
                <CardDescription>已学单词数量与复习率</CardDescription>
              </CardHeader>
              <CardContent class="h-[300px] flex items-center justify-center border-t pt-4">
                <div class="text-sm text-muted-foreground">学习统计图表占位符</div>
              </CardContent>
            </Card>
          </div>

          <div class="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
            <Card class="col-span-2">
              <CardHeader class="flex flex-row items-center justify-between space-y-0 pb-2">
                <div class="space-y-1">
                  <CardTitle>最近注册用户</CardTitle>
                  <CardDescription>最近加入平台的用户</CardDescription>
                </div>
                <router-link to="/users">
                  <Button variant="outline" size="sm">
                    查看全部
                  </Button>
                </router-link>
              </CardHeader>
              <CardContent>
                <div class="space-y-8">
                  <div v-for="user in recentUsers" :key="user.id" class="flex items-center">
                    <div class="mr-4 rounded-full bg-primary/10 p-2">
                      <User class="h-4 w-4 text-primary" />
                    </div>
                    <div class="space-y-1">
                      <p class="text-sm font-medium leading-none">{{ user.email }}</p>
                      <p class="text-sm text-muted-foreground">注册于 2 天前</p>
                    </div>
                    <div class="ml-auto font-medium">活跃</div>
                  </div>
                </div>
              </CardContent>
            </Card>
            <Card>
              <CardHeader class="flex flex-row items-center justify-between space-y-0 pb-2">
                <div class="space-y-1">
                  <CardTitle>热门单词</CardTitle>
                  <CardDescription>本周最多复习的单词</CardDescription>
                </div>
                <router-link to="/words">
                  <Button variant="outline" size="sm">
                    查看全部
                  </Button>
                </router-link>
              </CardHeader>
              <CardContent>
                <div class="space-y-8">
                  <div v-for="(word, index) in popularWords" :key="word.id" class="flex items-center">
                    <div class="mr-4 rounded-full bg-primary/10 p-2">
                      <BookOpen class="h-4 w-4 text-primary" />
                    </div>
                    <div class="space-y-1">
                      <p class="text-sm font-medium leading-none">{{ word.word }}</p>
                      <p class="text-sm text-muted-foreground">{{ 120 - index * 15 }} 次复习</p>
                    </div>
                    <div class="ml-auto font-medium">{{ 93 - index * 5 }}% 掌握度</div>
                  </div>
                </div>
              </CardContent>
            </Card>
          </div>

          <div class="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
            <Card>
              <CardHeader class="pb-2">
                <CardTitle class="text-sm font-medium">测试完成率</CardTitle>
              </CardHeader>
              <CardContent>
                <div class="text-2xl font-bold">87.4%</div>
                <div class="mt-4 h-2 w-full rounded-full bg-muted">
                  <div class="h-2 rounded-full bg-primary" style="width: 87.4%"></div>
                </div>
                <div class="mt-2 text-xs text-muted-foreground">+2.5% 相比上周</div>
              </CardContent>
            </Card>
            <Card>
              <CardHeader class="pb-2">
                <CardTitle class="text-sm font-medium">平均学习时间</CardTitle>
              </CardHeader>
              <CardContent>
                <div class="text-2xl font-bold">24.3 分钟/天</div>
                <div class="mt-4 h-2 w-full rounded-full bg-muted">
                  <div class="h-2 rounded-full bg-primary" style="width: 65%"></div>
                </div>
                <div class="mt-2 text-xs text-muted-foreground">+5.1% 相比上周</div>
              </CardContent>
            </Card>
            <Card>
              <CardHeader class="pb-2">
                <CardTitle class="text-sm font-medium">单词掌握率</CardTitle>
              </CardHeader>
              <CardContent>
                <div class="text-2xl font-bold">76.2%</div>
                <div class="mt-4 h-2 w-full rounded-full bg-muted">
                  <div class="h-2 rounded-full bg-primary" style="width: 76.2%"></div>
                </div>
                <div class="mt-2 text-xs text-muted-foreground">+1.2% 相比上周</div>
              </CardContent>
            </Card>
            <Card>
              <CardHeader class="pb-2">
                <CardTitle class="text-sm font-medium">活跃用户比例</CardTitle>
              </CardHeader>
              <CardContent>
                <div class="text-2xl font-bold">68.7%</div>
                <div class="mt-4 h-2 w-full rounded-full bg-muted">
                  <div class="h-2 rounded-full bg-primary" style="width: 68.7%"></div>
                </div>
                <div class="mt-2 text-xs text-muted-foreground">-2.3% 相比上周</div>
              </CardContent>
            </Card>
          </div>
        </TabsContent>

        <TabsContent value="analytics" class="space-y-4">
          <Card>
            <CardHeader>
              <CardTitle>高级分析</CardTitle>
              <CardDescription>深入了解用户学习行为和模式</CardDescription>
            </CardHeader>
            <CardContent>
              <div class="h-[400px] flex items-center justify-center">
                <div class="text-sm text-muted-foreground">高级分析图表占位符</div>
              </div>
            </CardContent>
          </Card>
        </TabsContent>

        <TabsContent value="activity" class="space-y-4">
          <Card>
            <CardHeader>
              <CardTitle>最近活动</CardTitle>
              <CardDescription>用户最近的学习活动和进度</CardDescription>
            </CardHeader>
            <CardContent>
              <div class="space-y-8">
                <div v-for="i in 5" :key="i" class="flex">
                  <div class="relative mr-4">
                    <div class="h-9 w-9 rounded-full bg-primary/10 flex items-center justify-center">
                      <BookOpen v-if="i % 2 === 0" class="h-4 w-4 text-primary" />
                      <User v-else class="h-4 w-4 text-primary" />
                    </div>
                    <div class="absolute -bottom-0.5 -right-0.5 h-3 w-3 rounded-full border-2 border-background bg-green-500"></div>
                  </div>
                  <div class="space-y-1">
                    <p class="text-sm">{{ i % 2 === 0 ? '学习了新单词' : '完成了测试' }} <span class="font-medium">{{ i % 2 === 0 ? popularWords[i % popularWords.length].word : '得分 85%' }}</span></p>
                    <p class="text-sm text-muted-foreground">{{ i * 10 }}分钟前</p>
                  </div>
                </div>
              </div>
            </CardContent>
          </Card>
        </TabsContent>
      </Tabs>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted, defineComponent, h} from 'vue'
import { Button } from '@/components/ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle
} from '@/components/ui/card'
import {
  Tabs,
  TabsContent,
  TabsList,
  TabsTrigger
} from '@/components/ui/tabs'
import { BarChart2, BookOpen, Users, User, Clock, GraduationCap, TrendingUp } from 'lucide-vue-next'

// 仪表板卡片组件
const DashboardCard = defineComponent({
  props: {
    title: String,
    value: String,
    change: String,
    trend: {
      type: String,
      validator: (value) => ['up', 'down'].includes(value)
    },
    icon: Object
  },
  setup(props) {
    return () => h(Card, {}, {
      default: () => [
        h(CardHeader, { class: 'flex flex-row items-center justify-between space-y-0 pb-2' }, {
          default: () => [
            h(CardTitle, { class: 'text-sm font-medium' }, () => props.title),
            h(props.icon, { class: 'h-4 w-4 text-muted-foreground' })
          ]
        }),
        h(CardContent, {}, {
          default: () => [
            h('div', { class: 'text-2xl font-bold' }, props.value),
            h('div', { class: 'text-sm text-muted-foreground' }, [
              h(TrendingUp, {
                class: `mr-2 h-4 w-4 ${props.trend === 'up' ? 'text-green-500' : 'text-red-500'}`
              }),
              h('span', {}, props.change)
            ])
          ]
        })
      ]
    })
  }
})

const stats = ref([
  { id: 1, name: '总用户数', value: '1,234', icon: Users, change: '+12.5%', changeType: 'increase' },
  { id: 2, name: '总单词数', value: '5,678', icon: BookOpen, change: '+8.2%', changeType: 'increase' },
  { id: 3, name: '学习记录', value: '45,678', icon: GraduationCap, change: '+15.3%', changeType: 'increase' },
  { id: 4, name: '反馈数量', value: '231', icon: BarChart2, change: '-3.8%', changeType: 'decrease' }
])

const recentUsers = ref([
  { id: 1, name: '张三', email: 'zhangsan@example.com', lastLogin: '今天 12:30' },
  { id: 2, name: '李四', email: 'lisi@example.com', lastLogin: '今天 10:15' },
  { id: 3, name: '王五', email: 'wangwu@example.com', lastLogin: '昨天 18:45' },
  { id: 4, name: '赵六', email: 'zhaoliu@example.com', lastLogin: '昨天 15:20' },
  { id: 5, name: '钱七', email: 'qianqi@example.com', lastLogin: '昨天 09:10' }
])

const popularWords = ref([
  { id: 1, word: 'vocabulary', meaning: '词汇', viewCount: 120 },
  { id: 2, word: 'education', meaning: '教育', viewCount: 105 },
  { id: 3, word: 'learning', meaning: '学习', viewCount: 90 },
  { id: 4, word: 'ambiguous', meaning: '模糊的', viewCount: 75 },
  { id: 5, word: 'conspicuous', meaning: '显著的', viewCount: 60 }
])

const loading = ref(true)

onMounted(() => {
  // 模拟加载数据
  setTimeout(() => {
    loading.value = false
  }, 500)
})
</script>

