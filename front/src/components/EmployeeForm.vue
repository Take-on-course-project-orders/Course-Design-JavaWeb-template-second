<template>
  <div class="employee-form-overlay" v-if="visible" @click.self="handleCancel">
    <div class="employee-form-modal">
      <div class="modal-header">
        <h3>{{ isEdit ? '编辑员工' : '新增员工' }}</h3>
        <button class="close-btn" @click="handleCancel">&times;</button>
      </div>
      
      <form class="modal-body" @submit.prevent="handleSubmit">
        <div class="form-group" v-if="isEdit">
          <label>ID</label>
          <input v-model="formData.id" type="text" placeholder="请输入ID" disabled />
        </div>

        <div class="form-group">
          <label>姓名 <span class="required">*</span></label>
          <input v-model="formData.name" type="text" placeholder="请输入姓名" required />
        </div>

        <div class="form-group">
          <label>地址</label>
          <input v-model="formData.address" type="text" placeholder="请输入地址" />
        </div>

        <div class="form-group">
          <label>年龄 <span class="required">*</span></label>
          <input v-model.number="formData.age" type="number" placeholder="请输入年龄" min="18" max="65" required />
        </div>

        <div class="form-group" v-if="!isEdit">
          <label>密码 <span class="required">*</span></label>
          <input v-model="formData.password" type="password" placeholder="请输入密码" required />
        </div>

        <div class="form-group">
          <label>手机号</label>
          <input v-model="formData.phoneNumber" type="tel" placeholder="请输入手机号" />
        </div>


        <div class="form-group">
          <label>邮箱</label>
          <input v-model="formData.email" type="email" placeholder="请输入邮箱" />
        </div>

        <div class="form-group">
          <label>状态</label>
          <select v-model="formData.active">
            <option :value=true>在职</option>
            <option :value=false>离职</option>
          </select>
        </div>

        <div class="form-group">
          <label>入职时间</label>
          <input v-model="formData.entryTime" type="date" />
        </div>

        <div class="form-group">
          <label>出生日期</label>
          <input v-model="formData.birth" type="date" />
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-cancel" @click="handleCancel">取消</button>
          <button type="submit" class="btn btn-primary" :disabled="loading">
            {{ loading ? '提交中...' : '确定' }}
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch, reactive, computed, toRefs } from 'vue'
import type { Employee } from '@/types/employee'

interface Props {
  visible: boolean
  employee?: Employee | null
}

const props = withDefaults(defineProps<Props>(), {
  visible: false,
  employee: null
})
const { visible, employee } = toRefs(props)

const emit = defineEmits<{
  (e: 'update:visible', value: boolean): void
  (e: 'submit', data: Employee): void
}>()

const loading = ref(false)
const isEdit = computed(() => !!employee.value?.id)

const initialFormData = (): Employee => ({
  id: undefined,
  name: '',
  age: undefined,
  phoneNumber: '',
  address: '',
  entryTime: new Date().toISOString().split('T')[0],
  email: '',
  password: '',
  active: true,
  birth: new Date().toISOString().split('T')[0]
})

const formData = reactive<Employee>(initialFormData())

watch(employee, (val) => {
  if (val) {
    formData.id = val.id
    formData.name = val.name
    formData.age = val.age
    formData.phoneNumber = val.phoneNumber
    formData.address = val.address
    formData.email = val.email
    formData.active = val.active
    formData.entryTime = val.entryTime || ''
    formData.birth = val.birth || ''
    formData.password = '' 
  } else {
    Object.assign(formData, initialFormData(),{id : undefined})
  } 
}, { immediate: true })

watch(visible, (val) => {
  if (!val) Object.assign(formData, initialFormData())
})

const handleSubmit = () => {
  // loading.value = true
  emit('submit', { ...formData })
  // setTimeout(() => { loading.value = false }, 500)
}

const handleCancel = () => emit('update:visible', false)
</script>

<style scoped>
.employee-form-overlay {
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

.employee-form-modal {
  background: white;
  border-radius: 8px;
  width: 90%;
  max-width: 600px;
  max-height: 90vh;
  overflow-y: auto;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 24px;
  border-bottom: 1px solid #e5e7eb;
}

.modal-header h3 {
  margin: 0;
  font-size: 18px;
  color: #1f2937;
}

.close-btn {
  background: none;
  border: none;
  font-size: 24px;
  cursor: pointer;
  color: #6b7280;
  line-height: 1;
}

.close-btn:hover {
  color: #1f2937;
}

.modal-body {
  padding: 24px;
}

.form-row {
  display: flex;
  gap: 16px;
  margin-bottom: 16px;
}

.form-group {
  flex: 1;
}

.form-group label {
  display: block;
  margin-bottom: 6px;
  font-size: 14px;
  font-weight: 500;
  color: #374151;
}

.required {
  color: #ef4444;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 10px 12px;
  border: 1px solid #d1d5db;
  border-radius: 6px;
  font-size: 14px;
  transition: border-color 0.2s, box-shadow 0.2s;
  box-sizing: border-box;
}

.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: #3b82f6;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  padding-top: 16px;
  border-top: 1px solid #e5e7eb;
  margin-top: 8px;
}

.btn {
  padding: 10px 20px;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s;
}

.btn-cancel {
  background: white;
  border: 1px solid #d1d5db;
  color: #374151;
}

.btn-cancel:hover {
  background: #f9fafb;
}

.btn-primary {
  background: #3b82f6;
  border: 1px solid #3b82f6;
  color: white;
}

.btn-primary:hover {
  background: #2563eb;
}

.btn-primary:disabled {
  background: #93c5fd;
  cursor: not-allowed;
}

@media (max-width: 640px) {
  .form-row {
    flex-direction: column;
    gap: 12px;
  }
}
</style>
