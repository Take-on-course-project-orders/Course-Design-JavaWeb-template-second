export interface Employee {
  id?: number
  name: string
  age: number
  phoneNumber: string
  address: string
  entryTime: string
  email: string
  isActive: boolean
  birth: string
  password: string
}

export interface PageParams {
  pageNum?: number
  pageSize?: number
  keyword?: string
}

export interface PageResult<T> {
  total: number
  records: T[]
  pageNum: number
  pageSize: number
}

export interface ApiResponse<T> {
  code: number
  message: string
  data: T
}
