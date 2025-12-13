<template>
  <div class="detail-overlay" v-if="visible" @click.self="$emit('update:visible', false)">
    <div class="detail-modal">
      <div class="modal-header">
        <h3>员工详情</h3>
        <button class="close-btn" @click="$emit('update:visible', false)">&times;</button>
      </div>
      
      <div class="modal-body" v-if="employee">
        <div class="detail-grid">
          <div class="detail-item">
            <label>ID</label>
            <span>{{ employee.id }}</span>
          </div>
          <div class="detail-item">
            <label>姓名</label>
            <span>{{ employee.name }}</span>
          </div>
          <div class="detail-item">
            <label>年龄</label>
            <span>{{ employee.age }}</span>
          </div>
          <div class="detail-item">
            <label>电话</label>
            <span>{{ employee.phoneNumber }}</span>
          </div>
          <div class="detail-item">
            <label>地址</label>
            <span>{{ employee.address }}</span>
          </div>
          <div class="detail-item">
            <label>入职日期</label>
            <span>{{ employee.entryTime }}</span>
          </div>
          <div>
            <label>邮箱</label>
            <span>{{employee.email}}</span>
          </div>
          <div class="detail-item">
            <label>状态</label>
            <span :class="['isActive', employee.isActive === true ? 'active' : 'inactive']">
              {{ employee.isActive === true ? '在职' : '离职' }}
            </span>
          </div>
          <!-- <div class="detail-item" v-if="employee.createTime">
            <label>创建时间</label>
            <span>{{ employee.createTime }}</span>
          </div>
          <div class="detail-item" v-if="employee.updateTime">
            <label>更新时间</label>
            <span>{{ employee.updateTime }}</span>
          </div> -->
        </div>
      </div>

      <div class="modal-footer">
        <button class="btn" @click="$emit('update:visible', false)">关闭</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Employee } from '@/types/employee'

interface Props {
  visible: boolean
  employee: Employee | null
}

defineProps<Props>()

defineEmits<{
  (e: 'update:visible', value: boolean): void
}>()
</script>

<style scoped>
.detail-overlay {
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

.detail-modal {
  background: white;
  border-radius: 8px;
  width: 90%;
  max-width: 500px;
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
}

.modal-body {
  padding: 24px;
}

.detail-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.detail-item {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.detail-item label {
  font-size: 12px;
  color: #6b7280;
  font-weight: 500;
}

.detail-item span {
  font-size: 14px;
  color: #1f2937;
}

.status {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  width: fit-content;
}

.status.active {
  background: #dcfce7;
  color: #166534;
}

.status.inactive {
  background: #fee2e2;
  color: #991b1b;
}

.modal-footer {
  padding: 16px 24px;
  border-top: 1px solid #e5e7eb;
  display: flex;
  justify-content: flex-end;
}

.btn {
  padding: 10px 20px;
  background: #f3f4f6;
  border: 1px solid #d1d5db;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
  color: #374151;
}

.btn:hover {
  background: #e5e7eb;
}
</style>
