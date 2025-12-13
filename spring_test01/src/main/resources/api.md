## 项目接口文档 

### 1. 查询所有员工 

- **url** : `/api/emp/list`
- **method** : `GET`
- **描述**: 获取所有的员工信息 
无参数 

### 2. 分页查询 

- **url** : `/api/emp/page`
- **method**  : `GET`
- **描述** : 获取一页内的所有员工信息
**参数** : 分页大小`PageSize`和页数`PageNum`，有默认值可以不给

### 3. 按照`ID`查找

- **url** : `/api/emp/find/{id}`
- **method** : `GET`
- **描述** : 按照对应id查询员工
无参数

### 4.创建新员工

- **url** : `/api/emp/create`
- **method** : `POST`
- **描述** : 按照提供的请求体创建

- **参数** :
  -  **`Body`** ：  `Employee`对象

### 5. 更新员工

- **url** : `/api/emp/update`
- **method** : `PUT`
- **描述** : 更改指定员工信息 
- **参数** : 
  - **`Body`** `Employee`对象

### 6.删除员工

- **url** : `/api/emp/delete/{id}`
- **method** : `DELETE`
- **描述** : 根据指定ID 删除文件