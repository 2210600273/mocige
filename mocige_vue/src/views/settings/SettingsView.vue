<template>
  <div class="flex flex-col gap-4">
    <h1 class="text-3xl font-bold tracking-tight">系统设置</h1>

    <Tabs v-model="activeTab" class="space-y-4">
      <TabsList class="grid w-full grid-cols-4">
        <TabsTrigger value="general">通用设置</TabsTrigger>
        <TabsTrigger value="appearance">外观设置</TabsTrigger>
        <TabsTrigger value="notifications">通知设置</TabsTrigger>
        <TabsTrigger value="database">数据库设置</TabsTrigger>
      </TabsList>

      <TabsContent value="general" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>通用设置</CardTitle>
            <CardDescription>配置管理后台的通用设置。</CardDescription>
          </CardHeader>
          <CardContent class="space-y-4">
            <div class="space-y-2">
              <Label for="site-name">网站名称</Label>
              <Input id="site-name" v-model="siteName" />
            </div>
            <div class="space-y-2">
              <Label for="admin-email">管理员邮箱</Label>
              <Input id="admin-email" type="email" v-model="adminEmail" />
            </div>
            <div class="space-y-2">
              <Label for="timezone">时区</Label>
              <select id="timezone" v-model="timezone" class="w-full p-2 rounded-md border border-input bg-background">
                <option>UTC (世界协调时间)</option>
                <option>CST (中国标准时间)</option>
                <option>EST (东部标准时间)</option>
                <option>PST (太平洋标准时间)</option>
              </select>
            </div>
            <div class="flex items-center justify-between">
              <Label for="analytics-switch">启用分析</Label>
              <Switch id="analytics-switch" v-model="isAnalyticsEnabled" />
            </div>
          </CardContent>
          <CardFooter>
            <Button @click="handleSaveSettings">保存更改</Button>
          </CardFooter>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>提醒设置</CardTitle>
            <CardDescription>配置发送用户提醒的时间。</CardDescription>
          </CardHeader>
          <CardContent class="space-y-4">
            <div class="space-y-2">
              <Label for="reminder-time">每日提醒时间</Label>
              <Input type="time" v-model="reminderTime" />
            </div>
          </CardContent>
          <CardFooter>
            <Button @click="handleSaveSettings">保存更改</Button>
          </CardFooter>
        </Card>
      </TabsContent>

      <TabsContent value="appearance" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>外观设置</CardTitle>
            <CardDescription>自定义管理后台的外观。</CardDescription>
          </CardHeader>
          <CardContent class="space-y-6">
            <div class="space-y-2">
              <Label>主题</Label>
              <div class="flex gap-4">
                <div class="border rounded-md p-2 cursor-pointer" @click="selectedTheme = 'light'" :class="{'ring-2 ring-primary': selectedTheme === 'light'}">
                  <div class="bg-white h-24 w-full rounded-md shadow-sm border"></div>
                  <p class="text-center text-sm mt-2">亮色</p>
                </div>
                <div class="border rounded-md p-2 cursor-pointer" @click="selectedTheme = 'dark'" :class="{'ring-2 ring-primary': selectedTheme === 'dark'}">
                  <div class="bg-slate-900 h-24 w-full rounded-md shadow-sm border"></div>
                  <p class="text-center text-sm mt-2">暗色</p>
                </div>
                <div class="border rounded-md p-2 cursor-pointer" @click="selectedTheme = 'system'" :class="{'ring-2 ring-primary': selectedTheme === 'system'}">
                  <div class="bg-gradient-to-b from-white to-slate-900 h-24 w-full rounded-md shadow-sm border"></div>
                  <p class="text-center text-sm mt-2">跟随系统</p>
                </div>
              </div>
            </div>

            <div class="space-y-2">
              <Label>强调色</Label>
                                          <div class="flex gap-4">                <div                  v-for="color in accentColors"                  :key="color.class"                  class="cursor-pointer"                  @click="selectedAccentColor = color.class"                  :class="{'ring-2 ring-offset-2 rounded-full': selectedAccentColor === color.class}"                >                  <div :class="`h-10 w-10 rounded-full ${color.class}`"></div>                </div>              </div>
            </div>

            <div class="space-y-2">
              <Label>字体大小</Label>
              <div class="flex gap-4">
                <Button variant="outline" size="sm" @click="selectedFontSize = 'small'" :class="{'bg-muted': selectedFontSize === 'small'}">
                  小
                </Button>
                <Button variant="outline" size="sm" @click="selectedFontSize = 'medium'" :class="{'bg-muted': selectedFontSize === 'medium'}">
                  中
                </Button>
                <Button variant="outline" size="sm" @click="selectedFontSize = 'large'" :class="{'bg-muted': selectedFontSize === 'large'}">
                  大
                </Button>
              </div>
            </div>
          </CardContent>
          <CardFooter>
            <Button @click="handleSaveSettings">保存更改</Button>
          </CardFooter>
        </Card>
      </TabsContent>

      <TabsContent value="notifications" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>通知设置</CardTitle>
            <CardDescription>配置用户和管理员的通知设置。</CardDescription>
          </CardHeader>
          <CardContent class="space-y-6">
            <div>
              <div class="flex items-center justify-between">
                <Label for="user-notifications">用户通知</Label>
                <Switch id="user-notifications" v-model="isUserNotificationsEnabled" />
              </div>
              <p class="text-sm text-muted-foreground mt-1">
                为用户发送提醒和更新通知。
              </p>
            </div>

            <Separator />

            <div class="space-y-4">
              <h3 class="text-lg font-medium">邮件模板</h3>
              <div class="space-y-2">
                <Label for="welcome-email">欢迎邮件</Label>
                <Textarea
                  id="welcome-email"
                  rows="4"
                  v-model="welcomeEmailTemplate"
                  placeholder="输入欢迎邮件内容"
                />
              </div>

              <div class="space-y-2">
                <Label for="reminder-email">提醒邮件</Label>
                <Textarea
                  id="reminder-email"
                  rows="4"
                  v-model="reminderEmailTemplate"
                  placeholder="输入提醒邮件内容"
                />
              </div>
            </div>
          </CardContent>
          <CardFooter>
            <Button @click="handleSaveSettings">保存更改</Button>
          </CardFooter>
        </Card>
      </TabsContent>

      <TabsContent value="database" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>数据库设置</CardTitle>
            <CardDescription>配置数据库备份和维护设置。</CardDescription>
          </CardHeader>
          <CardContent class="space-y-6">
            <div>
              <div class="flex items-center justify-between">
                <Label for="daily-backup">每日备份</Label>
                <Switch id="daily-backup" v-model="isDatabaseBackupEnabled" />
              </div>
              <p class="text-sm text-muted-foreground mt-1">每天自动备份数据库。</p>
            </div>

            <div class="space-y-2">
              <Label for="backup-time">备份时间</Label>
              <Input type="time" v-model="backupTime" />
            </div>

            <div class="space-y-2">
              <Label for="backup-retention">备份保留天数</Label>
              <Input id="backup-retention" type="number" v-model="backupRetention" min="1" max="365" />
            </div>

            <Separator />

            <div class="flex flex-col gap-4">
              <Button variant="outline" @click="runDatabaseOptimization">运行数据库优化</Button>
              <Button variant="outline" @click="exportDatabase">导出数据库</Button>
              <Button variant="outline" @click="importDatabase">导入数据库</Button>
            </div>
          </CardContent>
          <CardFooter>
            <Button @click="handleSaveSettings">保存更改</Button>
          </CardFooter>
        </Card>
      </TabsContent>
    </Tabs>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useToast } from '@/components/ui/toast'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import { Switch } from '@/components/ui/switch'
import { Separator } from '@/components/ui/separator'
import { Textarea } from '@/components/ui/textarea'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'

const { toast } = useToast()
const activeTab = ref('general')

// 通用设置
const siteName = ref('Mocige 词汇系统')
const adminEmail = ref('admin@example.com')
const timezone = ref('CST (中国标准时间)')
const isAnalyticsEnabled = ref(true)
const reminderTime = ref('09:00')

// 外观设置
const selectedTheme = ref('light')
const selectedAccentColor = ref('bg-blue-500')
const selectedFontSize = ref('medium')
const accentColors = [
  { class: 'bg-blue-500' },
  { class: 'bg-green-500' },
  { class: 'bg-purple-500' },
  { class: 'bg-pink-500' },
  { class: 'bg-orange-500' }
]

// 通知设置
const isUserNotificationsEnabled = ref(true)
const welcomeEmailTemplate = ref('欢迎使用我们的词汇学习系统！我们很高兴您加入我们。')
const reminderEmailTemplate = ref('这是一个友好的提醒，请继续今天的词汇练习。')

// 数据库设置
const isDatabaseBackupEnabled = ref(true)
const backupTime = ref('03:00')
const backupRetention = ref(30)

const handleSaveSettings = () => {
  toast({
    title: "设置已保存",
    description: "您的设置已成功保存。",
  })
}

const runDatabaseOptimization = () => {
  toast({
    title: "数据库优化",
    description: "数据库优化已启动。这可能需要几分钟的时间。",
  })
}

const exportDatabase = () => {
  toast({
    title: "数据库导出",
    description: "数据库导出已启动。导出完成后会通知您。",
  })
}

const importDatabase = () => {
  toast({
    title: "数据库导入",
    description: "请在导入导出页面进行数据库导入操作。",
  })
}
</script>
