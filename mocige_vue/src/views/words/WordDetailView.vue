<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center gap-2">
      <RouterLink to="/words">
        <Button variant="ghost" size="sm">
          <ArrowLeftIcon class="mr-2 h-4 w-4" />
          返回单词列表
        </Button>
      </RouterLink>
      <h1 class="text-3xl font-bold tracking-tight">{{ wordData.word }}</h1>
      <RouterLink :to="`/words/${$route.params.id}/edit`">
        <Button size="sm" variant="outline">
          <PencilIcon class="mr-2 h-4 w-4" />
          编辑
        </Button>
      </RouterLink>
    </div>

    <div class="grid gap-4 md:grid-cols-3">
      <Card class="md:col-span-2">
        <CardHeader>
          <CardTitle>单词信息</CardTitle>
          <CardDescription>关于这个词汇的详细信息</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div class="grid grid-cols-2 gap-4">
            <div>
              <h3 class="text-sm font-medium text-muted-foreground">单词</h3>
              <p class="text-lg font-medium">{{ wordData.word }}</p>
            </div>
            <div>
              <h3 class="text-sm font-medium text-muted-foreground">发音</h3>
              <p class="text-lg font-medium">{{ wordData.pronunciation }}</p>
            </div>
            <div>
              <h3 class="text-sm font-medium text-muted-foreground">词性</h3>
              <p class="text-lg font-medium capitalize">{{ wordData.partOfSpeech }}</p>
            </div>
          </div>

          <div>
            <h3 class="text-sm font-medium text-muted-foreground">定义</h3>
            <p class="mt-1">{{ wordData.definition }}</p>
          </div>

          <div>
            <h3 class="text-sm font-medium text-muted-foreground mb-2">同义词</h3>
            <div class="flex flex-wrap gap-2">
              <template v-if="wordData.synonyms.length > 0">
                <Badge v-for="synonym in wordData.synonyms" :key="synonym" variant="secondary">
                  {{ synonym }}
                </Badge>
              </template>
              <p v-else class="text-sm text-muted-foreground">没有可用的同义词</p>
            </div>
          </div>

          <div>
            <h3 class="text-sm font-medium text-muted-foreground mb-2">反义词</h3>
            <div class="flex flex-wrap gap-2">
              <template v-if="wordData.antonyms.length > 0">
                <Badge v-for="antonym in wordData.antonyms" :key="antonym" variant="secondary">
                  {{ antonym }}
                </Badge>
              </template>
              <p v-else class="text-sm text-muted-foreground">没有可用的反义词</p>
            </div>
          </div>
        </CardContent>
      </Card>

      <Card>
        <CardHeader>
          <CardTitle>学习统计</CardTitle>
          <CardDescription>用户如何学习这个单词</CardDescription>
        </CardHeader>
        <CardContent class="space-y-4">
          <div>
            <h3 class="text-sm font-medium text-muted-foreground">学习次数</h3>
            <p class="text-lg font-medium">{{ wordData.learningStats.timesStudied }}</p>
          </div>
          <div>
            <h3 class="text-sm font-medium text-muted-foreground">平均掌握度</h3>
            <div class="flex items-center gap-2 mt-1">
              <div class="relative w-full h-2 bg-muted rounded-full overflow-hidden">
                <div
                  class="absolute top-0 left-0 h-full bg-primary"
                  :style="{ width: `${(wordData.learningStats.averageMastery / 5) * 100}%` }"
                />
              </div>
              <span>{{ wordData.learningStats.averageMastery }}/5</span>
            </div>
          </div>
          <div>
            <h3 class="text-sm font-medium text-muted-foreground">难度</h3>
            <Badge
              :class="{
                'bg-green-100 text-green-800 hover:bg-green-100': wordData.learningStats.difficulty === 'Easy',
                'bg-blue-100 text-blue-800 hover:bg-blue-100': wordData.learningStats.difficulty === 'Medium',
                'bg-yellow-100 text-yellow-800 hover:bg-yellow-100': wordData.learningStats.difficulty === 'Hard'
              }"
            >
              {{ wordData.learningStats.difficulty }}
            </Badge>
          </div>
        </CardContent>
      </Card>
    </div>

    <Tabs default-value="examples" class="mt-4">
      <TabsList>
        <TabsTrigger value="examples">例句</TabsTrigger>
        <TabsTrigger value="etymology">词源</TabsTrigger>
        <TabsTrigger value="media">媒体</TabsTrigger>
      </TabsList>
      <TabsContent value="examples" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>例句</CardTitle>
            <CardDescription>这个单词在上下文中的使用方式</CardDescription>
          </CardHeader>
          <CardContent>
            <ul class="space-y-2">
              <li v-for="(example, index) in wordData.examples" :key="index" class="p-3 bg-muted rounded-md">
                {{ example }}
              </li>
            </ul>
          </CardContent>
        </Card>
      </TabsContent>
      <TabsContent value="etymology" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>词源</CardTitle>
            <CardDescription>词根和词缀</CardDescription>
          </CardHeader>
          <CardContent>
            <div class="space-y-4">
              <div>
                <h3 class="text-sm font-medium text-muted-foreground mb-2">词根</h3>
                <div class="space-y-2">
                  <div v-for="(root, index) in wordData.roots" :key="index" class="p-3 bg-muted rounded-md">
                    <span class="font-medium">{{ root.root }}</span>: {{ root.meaning }}
                  </div>
                </div>
              </div>
              <div>
                <h3 class="text-sm font-medium text-muted-foreground mb-2">词缀</h3>
                <div class="space-y-2">
                  <div v-for="(affix, index) in wordData.affixes" :key="index" class="p-3 bg-muted rounded-md">
                    <span class="font-medium">{{ affix.affix }}</span>: {{ affix.meaning }}
                  </div>
                </div>
              </div>
            </div>
          </CardContent>
        </Card>
      </TabsContent>
      <TabsContent value="media" class="space-y-4">
        <Card>
          <CardHeader>
            <CardTitle>媒体</CardTitle>
            <CardDescription>与此单词相关的图像和音频</CardDescription>
          </CardHeader>
          <CardContent>
            <div class="text-center p-8 text-muted-foreground">
              暂无可用媒体
            </div>
          </CardContent>
        </Card>
      </TabsContent>
    </Tabs>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useToast } from '@/components/ui/toast'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import { Badge } from '@/components/ui/badge'
import { ArrowLeft as ArrowLeftIcon, Pencil as PencilIcon } from 'lucide-vue-next'

const route = useRoute()
const router = useRouter()
const { toast } = useToast()

// 模拟从API获取单词数据
const wordData = ref({
  id: Number.parseInt(route.params.id),
  word: "vocabulary",
  pronunciation: "/vəˈkæb.jʊ.lər.i/",
  partOfSpeech: "noun",
  definition: "一个人或特定群体已知和使用的所有单词。",
  synonyms: ["lexicon", "terminology", "language", "words"],
  antonyms: [],
  examples: [
    "He has an extensive vocabulary.",
    "The book will help you improve your vocabulary.",
    "Technical vocabulary is used in specialized fields.",
  ],
  roots: [{ root: "voc", meaning: "呼叫，声音" }],
  affixes: [{ affix: "-ary", meaning: "与...相关，关于...的" }],
  learningStats: {
    timesStudied: 1250,
    averageMastery: 4.2,
    difficulty: "Medium",
  },
})

onMounted(() => {
  // 在实际应用中，这里会根据ID从API获取单词数据
  console.log(`加载ID为 ${route.params.id} 的单词数据`)
})
</script>