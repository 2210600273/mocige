<template>
  <div class="flex flex-col gap-4">
    <h1 class="text-3xl font-bold tracking-tight">数据导入导出</h1>
    <p class="text-muted-foreground">导入或导出系统数据，支持CSV和JSON格式。</p>

    <Tabs v-model="activeTab" class="space-y-4">
      <TabsList class="grid w-full grid-cols-2">
        <TabsTrigger value="import">数据导入</TabsTrigger>
        <TabsTrigger value="export">数据导出</TabsTrigger>
      </TabsList>

      <TabsContent value="import" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>导入数据</CardTitle>
            <CardDescription>从CSV或JSON文件导入数据</CardDescription>
          </CardHeader>
          <CardContent class="space-y-4">
            <div class="space-y-2">
              <Label for="import-type">数据类型</Label>
              <Select v-model="importType">
                <SelectTrigger id="import-type">
                  <SelectValue placeholder="选择数据类型" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="words">单词</SelectItem>
                  <SelectItem value="users">用户</SelectItem>
                  <SelectItem value="sentences">例句</SelectItem>
                  <SelectItem value="roots">词根</SelectItem>
                  <SelectItem value="affixes">词缀</SelectItem>
                </SelectContent>
              </Select>
            </div>

            <div class="space-y-2">
              <Label for="import-file">选择文件</Label>
              <div class="flex items-center gap-2">
                <Input
                  id="import-file"
                  type="file"
                  accept=".csv,.json"
                  @change="handleFileChange"
                  :disabled="isImporting"
                />
              </div>
              <p class="text-xs text-muted-foreground">支持的格式: CSV, JSON. 最大文件大小: 10MB</p>
            </div>

            <div v-if="importFile" class="flex items-center gap-2 p-2 bg-muted rounded-md">
              <FileSpreadsheet class="h-4 w-4" />
              <span class="text-sm">{{ importFile.name }}</span>
              <span class="text-xs text-muted-foreground ml-auto">
                {{ (importFile.size / 1024).toFixed(2) }} KB
              </span>
            </div>

            <div v-if="isImporting" class="space-y-2">
              <div class="flex items-center justify-between">
                <Label>导入进度</Label>
                <span class="text-xs text-muted-foreground">{{ importProgress }}%</span>
              </div>
              <Progress :value="importProgress" class="h-2" />
            </div>

            <Alert
              v-if="importResult.status"
              :variant="importResult.status === 'success' ? 'default' : 'destructive'"
            >
              <CheckCircle2 v-if="importResult.status === 'success'" class="h-4 w-4" />
              <AlertCircle v-else class="h-4 w-4" />
              <AlertTitle>{{ importResult.status === "success" ? "导入成功" : "导入错误" }}</AlertTitle>
              <AlertDescription>
                <p>{{ importResult.message }}</p>
                <div v-if="importResult.details" class="mt-2 text-sm">
                  <p>总记录数: {{ importResult.details.total }}</p>
                  <p>成功导入: {{ importResult.details.success }}</p>
                  <p>错误记录: {{ importResult.details.errors }}</p>
                </div>
              </AlertDescription>
            </Alert>
          </CardContent>
          <CardFooter>
            <Button @click="handleImport" :disabled="!importFile || isImporting">
              <Upload class="mr-2 h-4 w-4" />
              {{ isImporting ? "导入中..." : "开始导入" }}
            </Button>
          </CardFooter>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>导入模板</CardTitle>
            <CardDescription>下载数据导入模板</CardDescription>
          </CardHeader>
          <CardContent class="space-y-4">
            <p class="text-sm">为确保导入成功，请使用我们提供的模板文件。模板包含所需的列和格式示例。</p>

            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
              <Button variant="outline" class="justify-start" @click="downloadTemplate('words')">
                <Download class="mr-2 h-4 w-4" />
                单词导入模板 (CSV)
              </Button>
              <Button variant="outline" class="justify-start" @click="downloadTemplate('users')">
                <Download class="mr-2 h-4 w-4" />
                用户导入模板 (CSV)
              </Button>
              <Button variant="outline" class="justify-start" @click="downloadTemplate('sentences')">
                <Download class="mr-2 h-4 w-4" />
                例句导入模板 (CSV)
              </Button>
              <Button variant="outline" class="justify-start" @click="downloadTemplate('roots')">
                <Download class="mr-2 h-4 w-4" />
                词根词缀导入模板 (CSV)
              </Button>
            </div>
          </CardContent>
        </Card>
      </TabsContent>

      <TabsContent value="export" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>导出数据</CardTitle>
            <CardDescription>导出系统数据为CSV或JSON格式</CardDescription>
          </CardHeader>
          <CardContent class="space-y-4">
            <div class="space-y-2">
              <Label for="export-type">数据类型</Label>
              <Select v-model="exportType">
                <SelectTrigger id="export-type">
                  <SelectValue placeholder="选择数据类型" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="words">单词</SelectItem>
                  <SelectItem value="users">用户</SelectItem>
                  <SelectItem value="sentences">例句</SelectItem>
                  <SelectItem value="roots">词根</SelectItem>
                  <SelectItem value="affixes">词缀</SelectItem>
                  <SelectItem value="all">所有数据</SelectItem>
                </SelectContent>
              </Select>
            </div>

            <div class="space-y-2">
              <Label for="export-format">文件格式</Label>
              <Select v-model="exportFormat">
                <SelectTrigger id="export-format">
                  <SelectValue placeholder="选择文件格式" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="csv">CSV</SelectItem>
                  <SelectItem value="json">JSON</SelectItem>
                </SelectContent>
              </Select>
            </div>

            <div v-if="isExporting" class="space-y-2">
              <div class="flex items-center justify-between">
                <Label>导出进度</Label>
                <span class="text-xs text-muted-foreground">{{ exportProgress }}%</span>
              </div>
              <Progress :value="exportProgress" class="h-2" />
            </div>
          </CardContent>
          <CardFooter>
            <Button @click="handleExport" :disabled="isExporting">
              <Download class="mr-2 h-4 w-4" />
              {{ isExporting ? "导出中..." : "开始导出" }}
            </Button>
          </CardFooter>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>导出历史</CardTitle>
            <CardDescription>查看以前的导出文件</CardDescription>
          </CardHeader>
          <CardContent>
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>文件名</TableHead>
                  <TableHead>类型</TableHead>
                  <TableHead>大小</TableHead>
                  <TableHead>导出日期</TableHead>
                  <TableHead>操作</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                <TableRow v-for="(file, index) in exportHistory" :key="index">
                  <TableCell>{{ file.name }}</TableCell>
                  <TableCell>{{ file.type }}</TableCell>
                  <TableCell>{{ file.size }}</TableCell>
                  <TableCell>{{ file.date }}</TableCell>
                  <TableCell>
                    <Button variant="ghost" size="sm" @click="downloadExportFile(file)">
                      <Download class="h-4 w-4" />
                    </Button>
                  </TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </CardContent>
        </Card>
      </TabsContent>
    </Tabs>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useToast } from '@/components/ui/toast'
import { Download, Upload, FileSpreadsheet, AlertCircle, CheckCircle2 } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
import { Progress } from '@/components/ui/progress'
import { Alert, AlertDescription, AlertTitle } from '@/components/ui/alert'

const { toast } = useToast()
const activeTab = ref('import')
const importFile = ref(null)
const importType = ref('words')
const exportType = ref('words')
const exportFormat = ref('csv')
const isImporting = ref(false)
const isExporting = ref(false)
const importProgress = ref(0)
const exportProgress = ref(0)
const importResult = ref({ status: null, message: "" })

// 模拟导出历史数据
const exportHistory = ref([
  {
    name: "words_export_20230510.csv",
    type: "单词",
    size: "256 KB",
    date: "2023-05-10",
  },
  {
    name: "users_export_20230505.json",
    type: "用户",
    size: "128 KB",
    date: "2023-05-05",
  },
  {
    name: "sentences_export_20230501.csv",
    type: "例句",
    size: "512 KB",
    date: "2023-05-01",
  },
])

const handleFileChange = (e) => {
  if (e.target.files && e.target.files[0]) {
    importFile.value = e.target.files[0]
    importResult.value = { status: null, message: "" }
  }
}

const handleImport = () => {
  if (!importFile.value) {
    toast({
      title: "请选择文件",
      description: "请先选择要导入的文件。",
      variant: "destructive",
    })
    return
  }

  isImporting.value = true
  importProgress.value = 0
  importResult.value = { status: null, message: "" }

  // 模拟导入过程
  const interval = setInterval(() => {
    importProgress.value += 10
    
    if (importProgress.value >= 100) {
      clearInterval(interval)
      isImporting.value = false

      // 模拟导入结果
      const success = Math.random() > 0.2

      if (success) {
        importResult.value = {
          status: "success",
          message: "数据导入成功",
          details: {
            total: 150,
            success: 148,
            errors: 2,
          },
        }
      } else {
        importResult.value = {
          status: "error",
          message: "导入过程中发生错误",
          details: {
            total: 150,
            success: 120,
            errors: 30,
          },
        }
      }
    }
  }, 300)
}

const handleExport = () => {
  isExporting.value = true
  exportProgress.value = 0

  // 模拟导出过程
  const interval = setInterval(() => {
    exportProgress.value += 10
    
    if (exportProgress.value >= 100) {
      clearInterval(interval)
      isExporting.value = false

      toast({
        title: "导出完成",
        description: `${exportType.value}数据已成功导出为${exportFormat.value.toUpperCase()}格式。`,
      })
      
      // 添加到导出历史
      const now = new Date()
      const dateStr = now.toISOString().split('T')[0].replace(/-/g, '')
      
      exportHistory.value.unshift({
        name: `${exportType.value}_export_${dateStr}.${exportFormat.value}`,
        type: getTypeDisplayName(exportType.value),
        size: Math.floor(Math.random() * 500 + 100) + " KB",
        date: now.toISOString().split('T')[0],
      })
    }
  }, 200)
}

const downloadTemplate = (type) => {
  toast({
    title: "模板下载",
    description: `${getTypeDisplayName(type)}导入模板已开始下载。`,
  })
}

const downloadExportFile = (file) => {
  toast({
    title: "文件下载",
    description: `${file.name} 已开始下载。`,
  })
}

const getTypeDisplayName = (type) => {
  const typeMap = {
    words: "单词",
    users: "用户",
    sentences: "例句",
    roots: "词根",
    affixes: "词缀",
    all: "所有数据"
  }
  return typeMap[type] || type
}
</script>
