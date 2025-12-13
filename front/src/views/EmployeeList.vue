<template>
  <div class="employee-page">
    <div class="page-header">
      <h1>员工管理系统</h1>
      <p>管理公司所有员工信息</p>
    </div>

    <div class="stats-grid">
      <div class="stat-card">
        <div class="stat-icon total">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
            <circle cx="9" cy="7" r="4"/>
            <path d="M23 21v-2a4 4 0 0 0-3-3.87"/>
            <path d="M16 3.13a4 4 0 0 1 0 7.75"/>
          </svg>
        </div>
        <div class="stat-info">
          <span class="stat-label">员工总数</span>
          <span class="stat-value">{{ stats.total }}</span>
        </div>
      </div>
      
      <div class="stat-card">
        <div class="stat-icon active">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/>
            <polyline points="22 4 12 14.01 9 11.01"/>
          </svg>
        </div>
        <div class="stat-info">
          <span class="stat-label">在职员工</span>
          <span class="stat-value">{{ stats.active }}</span>
        </div>
      </div>
      
      <div class="stat-card">
        <div class="stat-icon inactive">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="10"/>
            <line x1="15" y1="9" x2="9" y2="15"/>
            <line x1="9" y1="9" x2="15" y2="15"/>
          </svg>
        </div>
        <div class="stat-info">
          <span class="stat-label">离职员工</span>
          <span class="stat-value">{{ stats.inactive }}</span>
        </div>
      </div>
    </div>

    <div class="action-bar">
      <div class="search-box">
        <input 
          v-model="searchKeyword" 
          type="text" 
          placeholder="搜索员工姓名、职位或部门..."
          @input="handleSearch"
        />
        <svg class="search-icon" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <circle cx="11" cy="11" r="8"/>
          <line x1="21" y1="21" x2="16.65" y2="16.65"/>
        </svg>
      </div>
      <button class="btn-add" @click="handleAdd">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <line x1="12" y1="5" x2="12" y2="19"/>
          <line x1="5" y1="12" x2="19" y2="12"/>
        </svg>
        新增员工
      </button>
    </div>

    <EmployeeTable 
      :employees="filteredEmployees"
      :loading="loading"
      @view="handleView"
      @edit="handleEdit"
      @delete="handleDelete"
    />

    <Pagination 
      v-if="total > 0"
      :total="total"
      v-model:current-page="pageNum"
      v-model:page-size="pageSize"
    />

    <EmployeeForm 
      v-model:visible="formVisible"
      :employee="currentEmployee"
      @submit="handleFormSubmit"
    />

    <EmployeeDetail 
      v-model:visible="detailVisible"
      :employee="currentEmployee"
    />

    <div class="confirm-overlay" v-if="deleteConfirmVisible" @click.self="deleteConfirmVisible = false">
      <div class="confirm-modal">
        <h3>确认删除</h3>
        <p>确定要删除员工 "{{ currentEmployee?.name }}" 吗？此操作不可恢复。</p>
        <div class="confirm-actions">
          <button class="btn-cancel" @click="deleteConfirmVisible = false">取消</button>
          <button class="btn-danger" @click="confirmDelete">确认删除</button>
        </div>
      </div>
    </div>

    <div class="toast" v-if="toast.show" :class="toast.type">
      {{ toast.message }}
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed, onMounted, watch } from 'vue'
import type { Employee } from '@/types/employee'
import { 
  getEmployeePage, 
  getEmployeeById, 
  createEmployee, 
  updateEmployee, 
  deleteEmployee 
} from '@/api/employee'
import EmployeeTable from '@/components/EmployeeTable.vue'
import EmployeeForm from '@/components/EmployeeForm.vue'
import EmployeeDetail from '@/components/EmployeeDetail.vue'
import Pagination from '@/components/Pagination.vue'

const employees = ref<Employee[]>([])
const loading = ref(false)
const total = ref(0)
const pageNum = ref(1)
const pageSize = ref(10)
const searchKeyword = ref('')

const formVisible = ref(false)
const detailVisible = ref(false)
const deleteConfirmVisible = ref(false)
const currentEmployee = ref<Employee | null>(null)

const toast = reactive({
  show: false,
  message: '',
  type: 'success'
})

const stats = computed(() => {
  const active = employees.value.filter(e => e.isActive === true).length
  return {
    total: total.value,
    active,
    inactive: total.value - active
  }
})

const filteredEmployees = computed(() => {
  if (!searchKeyword.value) return employees.value
  const keyword = searchKeyword.value.toLowerCase()
  return employees.value.filter(emp => 
    emp.name.toLowerCase().includes(keyword) 
  )
})

const fetchEmployees = async () => {
  loading.value = true
  try {
    const res = await getEmployeePage({
      pageNum: pageNum.value,
      pageSize: pageSize.value
    })
    if (res.code === 200 || res.code === 0) {
      employees.value = res.data.records,
      total.value = res.data.total
    }
  } catch (error) {
    showToast('获取数据失败', 'error')
  } finally {
    loading.value = false
  }
}

const showToast = (message: string, type = 'success') => {
  toast.message = message
  toast.type = type
  toast.show = true
  setTimeout(() => {
    toast.show = false
  }, 3000)
}

const handleAdd = () => {
  currentEmployee.value = null
  formVisible.value = true
}

const handleView = async (emp: Employee) => {
  try {
    const res = await getEmployeeById(emp.id!)
    if (res.code === 200 || res.code === 0) {
      currentEmployee.value = res.data
      detailVisible.value = true
    }
  } catch {
    currentEmployee.value = emp
    detailVisible.value = true
  }
}

const handleEdit = (emp: Employee) => {
  currentEmployee.value = { ...emp }
  formVisible.value = true
}

const handleDelete = (emp: Employee) => {
  currentEmployee.value = emp
  deleteConfirmVisible.value = true
}

const confirmDelete = async () => {
  if (!currentEmployee.value?.id) return
  try {
    await deleteEmployee(currentEmployee.value.id)
    showToast('删除成功')
    deleteConfirmVisible.value = false
    fetchEmployees()
  } catch {
    showToast('删除失败', 'error')
  }
}
const handleFormSubmit = async (data: Employee) => {
  try {
    if (data.id) {
      await updateEmployee(data)
      showToast('更新成功')
    } else {
      await createEmployee(data)
      showToast('创建成功')
    }
    formVisible.value = false
    fetchEmployees()
  } catch {
    showToast('操作失败', 'error')
  }
}



const handleSearch = () => {
  pageNum.value = 1
}

watch([pageNum, pageSize], () => {
  fetchEmployees()
})

onMounted(() => {
  fetchEmployees()
})
</script>

<style scoped>
.employee-page {
  max-width: 1400px;
  margin: 0 auto;
  padding: 24px;
  background: #f9fafb;
  min-height: 100vh;
}

.page-header {
  margin-bottom: 24px;
}

.page-header h1 {
  font-size: 28px;
  font-weight: 700;
  color: #111827;
  margin: 0 0 8px 0;
}

.page-header p {
  font-size: 14px;
  color: #6b7280;
  margin: 0;
}

/* 统计卡片 */
.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 16px;
  margin-bottom: 24px;
}

.stat-card {
  background: white;
  border-radius: 12px;
  padding: 20px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.stat-icon {
  width: 48px;
  height: 48px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.stat-icon.total {
  background: #dbeafe;
  color: #2563eb;
}

.stat-icon.active {
  background: #dcfce7;
  color: #16a34a;
}

.stat-icon.inactive {
  background: #fee2e2;
  color: #dc2626;
}

.stat-info {
  display: flex;
  flex-direction: column;
}

.stat-label {
  font-size: 14px;
  color: #6b7280;
}

.stat-value {
  font-size: 24px;
  font-weight: 700;
  color: #111827;
}

.action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
  gap: 16px;
  flex-wrap: wrap;
}

.search-box {
  position: relative;
  flex: 1;
  max-width: 400px;
}

.search-box input {
  width: 100%;
  padding: 12px 12px 12px 44px;
  border: 1px solid #d1d5db;
  border-radius: 8px;
  font-size: 14px;
  background: white;
  box-sizing: border-box;
}

.search-box input:focus {
  outline: none;
  border-color: #3b82f6;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);
}

.search-icon {
  position: absolute;
  left: 14px;
  top: 50%;
  transform: translateY(-50%);
  color: #9ca3af;
}

.btn-add {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 12px 20px;
  background: #3b82f6;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  transition: background 0.2s;
}

.btn-add:hover {
  background: #2563eb;
}

.confirm-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.confirm-modal {
  background: white;
  border-radius: 12px;
  padding: 24px;
  width: 90%;
  max-width: 400px;
}

.confirm-modal h3 {
  margin: 0 0 12px 0;
  font-size: 18px;
  color: #111827;
}

.confirm-modal p {
  margin: 0 0 20px 0;
  color: #6b7280;
  font-size: 14px;
}

.confirm-actions {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
}

.btn-cancel {
  padding: 10px 20px;
  background: #f3f4f6;
  border: 1px solid #d1d5db;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
}

.btn-danger {
  padding: 10px 20px;
  background: #ef4444;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
}

.btn-danger:hover {
  background: #dc2626;
}

.toast {
  position: fixed;
  top: 24px;
  right: 24px;
  padding: 12px 24px;
  border-radius: 8px;
  font-size: 14px;
  z-index: 2000;
  animation: slideIn 0.3s ease;
}

.toast.success {
  background: #dcfce7;
  color: #166534;
  border: 1px solid #86efac;
}

.toast.error {
  background: #fee2e2;
  color: #991b1b;
  border: 1px solid #fca5a5;
}

@keyframes slideIn {
  from {
    transform: translateX(100%);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

@media (max-width: 640px) {
  .employee-page {
    padding: 16px;
  }
  
  .action-bar {
    flex-direction: column;
    align-items: stretch;
  }
  
  .search-box {
    max-width: none;
  }
}
</style>
