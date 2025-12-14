<template>
  <div class="min-h-screen bg-gray-50">
    <header class="bg-white border-b border-gray-200 px-6 py-4">
      <div class="flex items-center justify-between">
        <h1 class="text-xl font-semibold text-gray-900">员工管理系统</h1>
        <div class="flex items-center gap-4">
          <input
            v-model="searchText"
            type="text"
            placeholder="搜索员工姓名..."
            class="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
          />
          <button
            @click="openCreateModal"
            class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors flex items-center gap-2"
          >
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
              <path fill-rule="evenodd" d="M10 3a1 1 0 011 1v5h5a1 1 0 110 2h-5v5a1 1 0 11-2 0v-5H4a1 1 0 110-2h5V4a1 1 0 011-1z" clip-rule="evenodd" />
            </svg>
            新增员工
          </button>
        </div>
      </div>
    </header>

    <main class="p-6">
      <div class="grid grid-cols-1 md:grid-cols-4 gap-4 mb-6">
        <div class="bg-white rounded-xl p-5 border border-gray-200">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm text-gray-500">总员工数</p>
              <p class="text-2xl font-bold text-gray-900">{{ employees.length }}</p>
            </div>
            <div class="w-12 h-12 bg-blue-100 rounded-full flex items-center justify-center">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-blue-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
              </svg>
            </div>
          </div>
        </div>
        <div class="bg-white rounded-xl p-5 border border-gray-200">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm text-gray-500">在职员工</p>
              <p class="text-2xl font-bold text-green-600">{{ activeCount }}</p>
            </div>
            <div class="w-12 h-12 bg-green-100 rounded-full flex items-center justify-center">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-green-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
          </div>
        </div>
        <div class="bg-white rounded-xl p-5 border border-gray-200">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm text-gray-500">离职员工</p>
              <p class="text-2xl font-bold text-red-600">{{ inactiveCount }}</p>
            </div>
            <div class="w-12 h-12 bg-red-100 rounded-full flex items-center justify-center">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-red-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
          </div>
        </div>
        <div class="bg-white rounded-xl p-5 border border-gray-200">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm text-gray-500">平均年龄</p>
              <p class="text-2xl font-bold text-gray-900">{{ averageAge }}</p>
            </div>
            <div class="w-12 h-12 bg-purple-100 rounded-full flex items-center justify-center">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-purple-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
            </div>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-xl border border-gray-200 overflow-hidden">
        <div class="overflow-x-auto">
          <table class="w-full">
            <thead class="bg-gray-50 border-b border-gray-200">
              <tr>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">姓名</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">年龄</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">手机号</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">邮箱</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">入职时间</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">状态</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">操作</th>
              </tr>
            </thead>
            <tbody class="divide-y divide-gray-200">
              <tr v-for="emp in filteredEmployees" :key="emp.id" class="hover:bg-gray-50 transition-colors">
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{{ emp.id }}</td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="flex items-center gap-3">
                    <div class="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center text-blue-600 font-medium text-sm">
                      {{ emp.name.charAt(0) }}
                    </div>
                    <span class="text-sm font-medium text-gray-900">{{ emp.name }}</span>
                  </div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">{{ emp.age }}</td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">{{ emp.phone }}</td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">{{ emp.email }}</td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">{{ emp.entryTime }}</td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <button
                    @click="toggleStatus(emp)"
                    :class="[
                      'px-3 py-1 rounded-full text-xs font-medium transition-colors',
                      emp.isActive 
                        ? 'bg-green-100 text-green-700 hover:bg-green-200' 
                        : 'bg-red-100 text-red-700 hover:bg-red-200'
                    ]"
                  >
                    {{ emp.isActive :true? '在职' : '离职' }}
                  </button>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="flex items-center gap-2">
                    <button @click="viewDetail(emp)" class="p-2 text-gray-500 hover:text-blue-600 hover:bg-blue-50 rounded-lg transition-colors" title="查看详情">
                      <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
                      </svg>
                    </button>
                    <button @click="openEditModal(emp)" class="p-2 text-gray-500 hover:text-green-600 hover:bg-green-50 rounded-lg transition-colors" title="编辑">
                      <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                      </svg>
                    </button>
                    <button @click="deleteEmployee(emp.id)" class="p-2 text-gray-500 hover:text-red-600 hover:bg-red-50 rounded-lg transition-colors" title="删除">
                      <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                      </svg>
                    </button>
                  </div>
                </td>
              </tr>
              <tr v-if="filteredEmployees.length === 0">
                <td colspan="8" class="px-6 py-12 text-center text-gray-500">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12 mx-auto mb-4 text-gray-300" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4" />
                  </svg>
                  暂无数据
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <!-- Pagination -->
        <div class="px-6 py-4 border-t border-gray-200 flex items-center justify-between">
          <p class="text-sm text-gray-500">
            共 {{ filteredEmployees.length }} 条记录
          </p>
          <div class="flex items-center gap-2">
            <button
              @click="currentPage = Math.max(1, currentPage - 1)"
              :disabled="currentPage === 1"
              class="px-3 py-1 border border-gray-300 rounded-lg text-sm disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-50"
            >
              上一页
            </button>
            <span class="px-3 py-1 text-sm text-gray-600">第 {{ currentPage }} 页</span>
            <button
              @click="currentPage++"
              :disabled="currentPage >= totalPages"
              class="px-3 py-1 border border-gray-300 rounded-lg text-sm disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-50"
            >
              下一页
            </button>
          </div>
        </div>
      </div>
    </main>

    <div v-if="showModal" class="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div class="bg-white rounded-xl w-full max-w-2xl mx-4 max-h-[90vh] overflow-y-auto">
        <div class="px-6 py-4 border-b border-gray-200 flex items-center justify-between">
          <h2 class="text-lg font-semibold text-gray-900">{{ isEditing ? '编辑员工' : '新增员工' }}</h2>
          <button @click="closeModal" class="p-2 text-gray-500 hover:text-gray-700 hover:bg-gray-100 rounded-lg">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <form @submit.prevent="submitForm" class="p-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">姓名 <span class="text-red-500">*</span></label>
              <input
                v-model="form.name"
                type="text"
                required
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                placeholder="请输入姓名"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">年龄 <span class="text-red-500">*</span></label>
              <input
                v-model.number="form.age"
                type="number"
                required
                min="1"
                max="150"
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                placeholder="请输入年龄"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">手机号 <span class="text-red-500">*</span></label>
              <input
                v-model="form.phoneNumber"
                type="tel"
                required
                pattern="^1[3-9]\d{9}$"
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                placeholder="请输入手机号"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">邮箱 <span class="text-red-500">*</span></label>
              <input
                v-model="form.email"
                type="email"
                required
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                placeholder="请输入邮箱"
              />
            </div>
            <div class="md:col-span-2">
              <label class="block text-sm font-medium text-gray-700 mb-1">地址 <span class="text-red-500">*</span></label>
              <input
                v-model="form.address"
                type="text"
                required
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                placeholder="请输入地址"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">密码 <span class="text-red-500">*</span></label>
              <input
                v-model="form.password"
                type="password"
                :required="!isEditing"
                minlength="6"
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                :placeholder="isEditing ? '留空则不修改密码' : '请输入密码'"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">入职时间 <span class="text-red-500">*</span></label>
              <input
                v-model="form.entryTime"
                type="date"
                required
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">出生日期 <span class="text-red-500">*</span></label>
              <input
                v-model="form.birth"
                type="date"
                required
                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              />
            </div>
            <div class="flex items-center gap-3">
              <label class="block text-sm font-medium text-gray-700">状态</label>
              <button
                type="button"
                @click="form.isActive = !form.isActive"
                :class="[
                  'relative inline-flex h-6 w-11 items-center rounded-full transition-colors',
                  form.isActive ? 'bg-blue-600' : 'bg-gray-300'
                ]"
              >
                <span
                  :class="[
                    'inline-block h-4 w-4 transform rounded-full bg-white transition-transform',
                    form.isActive ? 'translate-x-6' : 'translate-x-1'
                  ]"
                />
              </button>
              <span class="text-sm text-gray-600">{{ form.isActive ? '在职' : '离职' }}</span>
            </div>
          </div>
          <div class="mt-6 flex items-center justify-end gap-3">
            <button
              type="button"
              @click="closeModal"
              class="px-4 py-2 border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-50 transition-colors"
            >
              取消
            </button>
            <button
              type="submit"
              class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
            >
              {{ isEditing ? '保存修改' : '创建员工' }}
            </button>
          </div>
        </form>
      </div>
    </div>

    <div v-if="showDetailModal" class="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div class="bg-white rounded-xl w-full max-w-lg mx-4">
        <div class="px-6 py-4 border-b border-gray-200 flex items-center justify-between">
          <h2 class="text-lg font-semibold text-gray-900">员工详情</h2>
          <button @click="showDetailModal = false" class="p-2 text-gray-500 hover:text-gray-700 hover:bg-gray-100 rounded-lg">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div v-if="selectedEmployee" class="p-6">
          <div class="flex items-center gap-4 mb-6">
            <div class="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center text-blue-600 font-bold text-2xl">
              {{ selectedEmployee.name.charAt(0) }}
            </div>
            <div>
              <h3 class="text-xl font-semibold text-gray-900">{{ selectedEmployee.name }}</h3>
              <span :class="[
                'px-2 py-1 rounded-full text-xs font-medium',
                selectedEmployee.isActive ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'
              ]">
                {{ selectedEmployee.isActive:true ? '在职' : '离职' }}
              </span>
            </div>
          </div>
          <div class="space-y-4">
            <div class="flex items-center gap-3 text-sm">
              <span class="text-gray-500 w-20">ID:</span>
              <span class="text-gray-900">{{ selectedEmployee.id }}</span>
            </div>
            <div class="flex items-center gap-3 text-sm">
              <span class="text-gray-500 w-20">年龄:</span>
              <span class="text-gray-900">{{ selectedEmployee.age }} 岁</span>
            </div>
            <div class="flex items-center gap-3 text-sm">
              <span class="text-gray-500 w-20">手机号:</span>
              <span class="text-gray-900">{{ selectedEmployee.phone }}</span>
            </div>
            <div class="flex items-center gap-3 text-sm">
              <span class="text-gray-500 w-20">邮箱:</span>
              <span class="text-gray-900">{{ selectedEmployee.email }}</span>
            </div>
            <div class="flex items-center gap-3 text-sm">
              <span class="text-gray-500 w-20">地址:</span>
              <span class="text-gray-900">{{ selectedEmployee.address }}</span>
            </div>
            <div class="flex items-center gap-3 text-sm">
              <span class="text-gray-500 w-20">入职时间:</span>
              <span class="text-gray-900">{{ selectedEmployee.entryTime }}</span>
            </div>
            <div class="flex items-center gap-3 text-sm">
              <span class="text-gray-500 w-20">出生日期:</span>
              <span class="text-gray-900">{{ selectedEmployee.birth }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'

interface Employee {
  id: number
  name: string
  address: string
  age: number
  password: string
  phone: string
  email: string
  isActive: boolean
  entryTime: string
  birth: string
}


interface EmployeeForm {
  id?: number
  name: string
  address: string
  age: number
  password: string
  phoneNumber: string
  email: string
  isActive: boolean
  entryTime: string
  birth: string
}

// Mock data - 模拟 GET /emp/list 返回的数据
const employees = ref<Employee[]>([])

const searchText = ref('')
const showModal = ref(false)
const showDetailModal = ref(false)
const isEditing = ref(false)
const selectedEmployee = ref<Employee | null>(null)
const currentPage = ref(1)
const pageSize = 10

const form = ref<EmployeeForm>({
  name: '',
  address: '',
  age: 0,
  password: '',
  phoneNumber: '',
  email: '',
  isActive: true,
  entryTime: '',
  birth: ''
})

const filteredEmployees = computed(() => {
  if (!searchText.value) return employees.value
  return employees.value.filter(emp => 
    emp.name.toLowerCase().includes(searchText.value.toLowerCase())
  )
})

const activeCount = computed(() => employees.value.filter(e => e.isActive).length)
const inactiveCount = computed(() => employees.value.filter(e => !e.isActive).length)
const averageAge = computed(() => {
  if (employees.value.length === 0) return 0
  const sum = employees.value.reduce((acc, e) => acc + e.age, 0)
  return Math.round(sum / employees.value.length)
})
const totalPages = computed(() => Math.ceil(filteredEmployees.value.length / pageSize))

const openCreateModal = () => {
  isEditing.value = false
  form.value = {
    name: '',
    address: '',
    age: 0,
    password: '',
    phoneNumber: '',
    email: '',
    isActive: true,
    entryTime: '',
    birth: ''
  }
  showModal.value = true
}

const openEditModal = (emp: Employee) => {
  isEditing.value = true
  form.value= { ...emp, password: '' }
  showModal.value = true
}

const closeModal = () => {
  showModal.value = false
}

const viewDetail = (emp: Employee) => {
  selectedEmployee.value = emp
  showDetailModal.value = true
}

import { createEmployee, updateEmployee } from '@/api/employee' // 引入 API

const submitForm = async () => {
  try {
    if (isEditing.value) {
      await updateEmployee(form.value)
      await loadEmployees()  // 更新后刷新列表
    } else {
      const res = await createEmployee(form.value)
      employees.value.push(res.data)
    }
    closeModal()
  } catch (err) {
    console.error('提交失败:', err)
  }

const deleteEmployee = (id: number) => {
  if (confirm('确定要删除该员工吗？')) {
    employees.value = employees.value.filter(e => e.id !== id)
  }
}

const toggleStatus = (emp: Employee) => {
  emp.isActive = !emp.isActive
}
</script>