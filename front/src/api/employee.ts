import { request } from "./request"
import type { Employee, PageParams, PageResult } from "@/types/employee"

export function getEmployeeList() {
  return request.get<Employee[]>("/api/emp/list")
}

// 分页查询 GET /api/emp/page
export function getEmployeePage(params?: PageParams) {
  return request.get<PageResult<Employee>>("/api/emp/page", { params })
}

// 按 ID 查找员工 GET /api/emp/find/{id}
export function getEmployeeById(id: number) {
  return request.get<Employee>(`/api/emp/find/${id}`)
}

// 创建新员工 POST /api/emp/create
export function createEmployee(data: Employee) {
  return request.post<Employee>("/api/emp/create", data)
}

// 更新员工 PUT /api/emp/update
export function updateEmployee(data: Employee) {
  return request.put<Employee>("/api/emp/update", data)
}

// 删除员工 DELETE /api/emp/delete/{id}
export function deleteEmployee(id: number) {
  return request.delete<void>(`/api/emp/delete/${id}`)
}
