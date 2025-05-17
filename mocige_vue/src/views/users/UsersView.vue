<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { PlusCircle, MoreHorizontal, Eye, Pencil, Trash } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table'
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

const router = useRouter()
const users = ref([
  { id: 1, username: 'user1', email: 'user1@example.com', createdAt: '2023-05-15' },
  { id: 2, username: 'user2', email: 'user2@example.com', createdAt: '2023-05-14' },
  { id: 3, username: 'user3', email: 'user3@example.com', createdAt: '2023-05-13' },
  { id: 4, username: 'user4', email: 'user4@example.com', createdAt: '2023-05-12' },
  { id: 5, username: 'user5', email: 'user5@example.com', createdAt: '2023-05-11' },
])

const showDeleteDialog = ref(false)
const selectedUser = ref(null)

const handleDelete = () => {
  // Delete logic would go here
  console.log(`Deleting user: ${selectedUser.value.id}`)
  showDeleteDialog.value = false
}

const openDeleteDialog = (user) => {
  selectedUser.value = user
  showDeleteDialog.value = true
}
</script>

<template>
  <div class="flex flex-col gap-4">
    <div class="flex items-center justify-between">
      <h1 class="text-3xl font-bold tracking-tight">用户管理</h1>
      <router-link to="/users/new">
        <Button>
          <PlusCircle class="mr-2 h-4 w-4" />
          添加用户
        </Button>
      </router-link>
    </div>

    <Card>
      <CardHeader>
        <CardTitle>用户管理</CardTitle>
        <CardDescription>管理用户账户、凭证和偏好设置。</CardDescription>
      </CardHeader>
      <CardContent>
        <div class="flex justify-between items-center pb-4">
          <Input placeholder="搜索用户..." class="max-w-sm" />
        </div>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>用户名</TableHead>
              <TableHead>邮箱</TableHead>
              <TableHead>创建时间</TableHead>
              <TableHead>操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow v-for="user in users" :key="user.id">
              <TableCell>{{ user.id }}</TableCell>
              <TableCell>{{ user.username }}</TableCell>
              <TableCell>{{ user.email }}</TableCell>
              <TableCell>{{ user.createdAt }}</TableCell>
              <TableCell>
                <DropdownMenu>
                  <DropdownMenuTrigger as-child>
                    <Button variant="ghost" class="h-8 w-8 p-0">
                      <span class="sr-only">打开菜单</span>
                      <MoreHorizontal class="h-4 w-4" />
                    </Button>
                  </DropdownMenuTrigger>
                  <DropdownMenuContent align="end">
                    <DropdownMenuItem @click="router.push(`/users/${user.id}`)">
                      <Eye class="mr-2 h-4 w-4" />
                      查看
                    </DropdownMenuItem>
                    <DropdownMenuItem @click="router.push(`/users/${user.id}/edit`)">
                      <Pencil class="mr-2 h-4 w-4" />
                      编辑
                    </DropdownMenuItem>
                    <DropdownMenuItem 
                      class="text-destructive focus:text-destructive"
                      @click="openDeleteDialog(user)"
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

    <AlertDialog :open="showDeleteDialog" @update:open="showDeleteDialog = $event">
      <AlertDialogContent>
        <AlertDialogHeader>
          <AlertDialogTitle>您确定要删除吗？</AlertDialogTitle>
          <AlertDialogDescription>
            此操作无法撤消。这将永久删除用户账户及其所有相关数据。
          </AlertDialogDescription>
        </AlertDialogHeader>
        <AlertDialogFooter>
          <AlertDialogCancel>取消</AlertDialogCancel>
          <AlertDialogAction
            @click="handleDelete"
            class="bg-destructive text-destructive-foreground hover:bg-destructive/90"
          >
            删除
          </AlertDialogAction>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  </div>
</template>