<template>
  <div class="table-container">
    <table class="employee-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>姓名</th>
          <th>年龄</th>
          <th>电话</th>
          <th>地址</th>
          <th>邮箱</th>
          <th>入职日期</th>
          <th>生日</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="loading">
          <td colspan="11" class="loading-cell">
            <div class="loading-spinner"></div>
            <span>加载中...</span>
          </td>
        </tr>
        <tr v-else-if="employees.length === 0">
          <td colspan="11" class="empty-cell">暂无数据</td>
        </tr>
        <tr v-else v-for="emp in employees" :key="emp.id">
          <td>{{ emp.id }}</td>
          <td>{{ emp.name }}</td>
          <td>{{ emp.age }}</td>
          <td>{{ emp.phoneNumber }}</td>
          <td>{{ emp.address }}</td>
          <td>{{ emp.email}}</td>
          <td>{{ emp.entryTime }}</td>
          <td>{{emp.birth}}</td>
          <td>
            <span :class="['status-badge', emp.active === true ? 'active' : 'inactive']">
              {{ emp.active === true? '在职' : '离职' }}
            </span>
          </td>
          <td class="action-cell">
            <button class="action-btn view" @click="$emit('view', emp)">查看</button>
            <button class="action-btn edit" @click="$emit('edit', emp)">编辑</button>
            <button class="action-btn delete" @click="$emit('delete', emp)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import type { Employee } from '@/types/employee'

interface Props {
  employees: Employee[]
  loading?: boolean
}

defineProps<Props>()

defineEmits<{
  (e: 'view', emp: Employee): void
  (e: 'edit', emp: Employee): void
  (e: 'delete', emp: Employee): void
}>()
</script>

<style scoped>
.table-container {
  overflow-x: auto;
  border-radius: 8px;
  border: 1px solid #e5e7eb;
}

.employee-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 14px;
}

.employee-table th,
.employee-table td {
  padding: 12px 16px;
  text-align: left;
  border-bottom: 1px solid #e5e7eb;
  white-space: nowrap;
}

.employee-table th {
  background: #f9fafb;
  font-weight: 600;
  color: #374151;
}

.employee-table tbody tr:hover {
  background: #f9fafb;
}

.employee-table tbody tr:last-child td {
  border-bottom: none;
}

.loading-cell,
.empty-cell {
  text-align: center;
  padding: 40px;
  color: #6b7280;
}

.loading-cell {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
}

.loading-spinner {
  width: 32px;
  height: 32px;
  border: 3px solid #e5e7eb;
  border-top-color: #3b82f6;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

.status-badge {
  display: inline-block;
  padding: 4px 12px;
  border-radius: 9999px;
  font-size: 12px;
  font-weight: 500;
}

.status-badge.active {
  background: #dcfce7;
  color: #166534;
}

.status-badge.inactive {
  background: #fee2e2;
  color: #991b1b;
}

.action-cell {
  display: flex;
  gap: 8px;
}

.action-btn {
  padding: 6px 12px;
  border: none;
  border-radius: 4px;
  font-size: 12px;
  cursor: pointer;
  transition: opacity 0.2s;
}

.action-btn:hover {
  opacity: 0.8;
}

.action-btn.view {
  background: #dbeafe;
  color: #1d4ed8;
}

.action-btn.edit {
  background: #fef3c7;
  color: #92400e;
}

.action-btn.delete {
  background: #fee2e2;
  color: #991b1b;
}
</style>
