import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'dashboard',
      component: () => import('../views/dashboard/DashboardView.vue'),
      meta: { title: '仪表盘' }
    },
    {
      path: '/users',
      name: 'users',
      component: () => import('../views/users/UsersView.vue'),
      meta: { title: '用户管理' }
    },
    {
      path: '/users/new',
      name: 'new-user',
      component: () => import('../views/users/NewUserView.vue'),
      meta: { title: '新增用户' }
    },
    {
      path: '/users/:id/edit',
      name: 'edit-user',
      component: () => import('../views/users/EditUserView.vue'),
      meta: { title: '编辑用户' }
    },
    {
      path: '/users/:id',
      name: 'user-detail',
      component: () => import('../views/users/UserDetailView.vue'),
      props: true,
      meta: { title: '用户详情' }
    },
    {
      path: '/words',
      name: 'words',
      component: () => import('../views/words/WordsView.vue'),
      meta: { title: '单词管理' }
    },
    {
      path: '/words/new',
      name: 'new-word',
      component: () => import('../views/words/NewWordView.vue'),
      meta: { title: '新增单词' }
    },
    {
      path: '/words/:id/edit',
      name: 'edit-word',
      component: () => import('../views/words/EditWordView.vue'),
      meta: { title: '编辑单词' }
    },
    {
      path: '/words/:id',
      name: 'word-detail',
      component: () => import('../views/words/WordDetailView.vue'),
      props: true,
      meta: { title: '单词详情' }
    },
    {
      path: '/learning-progress',
      name: 'learning-progress',
      component: () => import('../views/learning-progress/LearningProgressView.vue'),
      meta: { title: '学习进度' }
    },
    {
      path: '/sentences',
      name: 'sentences',
      component: () => import('../views/sentences/SentencesView.vue'),
      meta: { title: '例句管理' }
    },
      {
        path: '/sentences/new',
        name: 'new-sentence',
        component: () => import('../views/sentences/NewSentenceView.vue'),
        meta: { title: '新增例句' }
      },
      {
        path: '/sentences/:id/edit',
        name: 'edit-sentence',
        component: () => import('../views/sentences/EditSentenceView.vue'),
        meta: { title: '编辑例句' }
      },
      {
        path: '/sentences/:id',
        name: 'sentence-detail',
        component: () => import('../views/sentences/SentenceDetailView.vue'),
        props: true,
        meta: { title: '例句详情' }
      },
    {
      path: '/media',
      name: 'media',
      component: () => import('../views/media/MediaView.vue'),
      meta: { title: '媒体管理' }
    },
    {
      path: '/media/new',
      name: 'new-media',
      component: () => import('../views/media/NewMediaView.vue'),
      meta: { title: '新增媒体' }
    },
      {
        path: '/media/:id/edit',
        name: 'edit-media',
        component: () => import('../views/media/EditMediaView.vue'),
        meta: { title: '编辑媒体' }
      },
    {
      path: '/media/:id',
      name: 'media-detail',
      component: () => import('../views/media/MediaDetailView.vue'),
      props: true,
      meta: { title: '媒体详情' }
    },
    {
      path: '/roots',
      name: 'roots',
      component: () => import('../views/roots/RootsView.vue'),
      meta: { title: '词根管理' }
    },
    {
      path: '/roots/new',
      name: 'new-root',
      component: () => import('../views/roots/NewRootView.vue'),
      meta: { title: '新增词根' }
    },
    {
      path: '/roots/:id',
      name: 'root-detail',
      component: () => import('../views/roots/RootDetailView.vue'),
      props: true,
      meta: { title: '词根详情' }
    },
    {
      path: '/affixes',
      name: 'affixes',
      component: () => import('../views/affixes/AffixesView.vue'),
      meta: { title: '词缀管理' }
    },
    {
      path: '/affixes/new',
      name: 'new-affix',
      component: () => import('../views/affixes/NewAffixView.vue'),
      meta: { title: '新增词缀' }
    },
    {
      path: '/affixes/:id',
      name: 'affix-detail',
      component: () => import('../views/affixes/AffixDetailView.vue'),
      props: true,
      meta: { title: '词缀详情' }
    },
    {
      path: '/synonyms',
      name: 'synonyms',
      component: () => import('../views/synonyms/SynonymsView.vue'),
      meta: { title: '同义词管理' }
    },
    {
      path: '/synonyms/new',
      name: 'new-synonym',
      component: () => import('../views/synonyms/NewSynonymView.vue'),
      meta: { title: '新增同义词' }
    },
    {
      path: '/synonyms/:id',
      name: 'synonym-detail',
      component: () => import('../views/synonyms/SynonymDetailView.vue'),
      props: true,
      meta: { title: '同义词详情' }
    },
    {
      path: '/antonyms',
      name: 'antonyms',
      component: () => import('../views/antonyms/AntonymsView.vue'),
      meta: { title: '反义词管理' }
    },
    {
      path: '/antonyms/new',
      name: 'new-antonym',
      component: () => import('../views/antonyms/NewAntonymView.vue'),
      meta: { title: '新增反义词' }
    },
    {
      path: '/antonyms/:id',
      name: 'antonym-detail',
      component: () => import('../views/antonyms/AntonymDetailView.vue'),
      props: true,
      meta: { title: '反义词详情' }
    },
    {
      path: '/test-records',
      name: 'test-records',
      component: () => import('../views/test-records/TestRecordsView.vue'),
      meta: { title: '测试记录' }
    },
    {
      path: '/statistics',
      name: 'statistics',
      component: () => import('../views/statistics/StatisticsView.vue'),
      meta: { title: '统计分析' }
    },
    {
      path: '/feedback',
      name: 'feedback',
      component: () => import('../views/feedback/FeedbackView.vue'),
      meta: { title: '用户反馈' }
    },
    {
      path: '/import-export',
      name: 'import-export',
      component: () => import('../views/import-export/ImportExportView.vue'),
      meta: { title: '导入导出' }
    },
    {
      path: '/settings',
      name: 'settings',
      component: () => import('../views/settings/SettingsView.vue'),
      meta: { title: '系统设置' }
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/profile/ProfileView.vue'),
      meta: { title: '个人资料' }
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/login/LoginView.vue'),
      meta: { title: '登录' }
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'not-found',
      component: () => import('../views/NotFoundView.vue'),
      meta: { title: '页面未找到' }
    }
  ],
})

// 设置页面标题
router.beforeEach((to, from, next) => {
  document.title = to.meta.title ? `${to.meta.title} - Mocige Admin` : 'Mocige Admin'
  next()
})

export default router
