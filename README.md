# JavaWeb实验二—购物系统-GFTB

## 技术

### 前端

- Vue

- Element UI

- JWT

### 后端

- Spring
- Spring MVC
- Mybatis
- RestFul 接口

## 功能

### 客户端

#### 首页

##### 		热门Tag

​		按照销量排序

##### 		轮播图

​		轮播销量前五

##### 	个人信息

###### 	已登录

- 头像
- 昵称
- 我的订单
- 购物车
- 我的钱包

###### 	未登录

- LOGO
- Hi~欢迎来到GFTB
- 登录注册

##### 	热门商品

​		轮播销量五到二十五

#### 我的

##### 	个人信息

​		编辑个人资料

##### 	购物车

​		加入购物车的物品

##### 	我的订单

​		历史购物记录

#### 详情页

​	商品详情

### 后台

#### 管理员

- RUD用户
- RUD商品
- RUD订单
- RUD钱包

## 接口

### 所有接口

#### 接口路径

`/api`

#### 响应信息

| 响应参数 | 备注               |
| -------- | ------------------ |
| code     | 0=Fail， 1=Success |
| Msg      | String             |

### 用户	`User`

#### 验证登录状态

`GET` /account/:token

#### 登录

`POST` /account

#### 退出登录

`DELETE` /account

#### 注册+

`POST` /user

#### 更新资料+

`PATCH` /user/:userId

#### getUserById+

`GET` /user/:userId

#### getUsers+

`GET` /user

#### 删除（管理员权限）+

`DELETE` /user/:id

### 商品	`Commodity`

#### 上新

`POST` /commodity

#### getCommodityById

`GET` /commodity/:commodityId

#### 更新

`PATCH `/commodity/:commodityId

#### 删除

`DELETE `/commodity/:commodityId

购物车	`Shopping`

#### 添加

`POST` /shopping

#### getShoppingByUserId

`GET` /shopping/:userId

#### 删除

`DELETE `/shopping/:shoppingId

### 订单	`Oder`

#### 新建

`POST` /oder

#### getOderByUserId

`GET` /oder/:oderId

#### 更新

`PATCH` /oder/:oderId

#### 删除

`DELETE `/oder/:oderId

### 其他 `Else`

#### 获得100块钱

`GET` /money



## 表设计

### 用户表 `User`

| 字段       | 类型        | 其他                        |
| ---------- | ----------- | --------------------------- |
| userId     | int         | Primary Key Unique Required |
| username   | varchar(20) | Required                    |
| password   | varchar(30) | Required                    |
| role       | int         | Required defalut 0          |
| address    | varchar(50) |                             |
| money      | float       | defalut 0                   |
| createDate | timestamp   | Required                    |
| updataDate | timestamp   | Required                    |

### 商品表 `Commodity`

| 字段            | 类型         | 其他                        |
| --------------- | ------------ | --------------------------- |
| commodityId     | int          | Primary Key Unique Required |
| commodityName   | varchar(30)  | Required                    |
| commodityPic    | varchar(200) |                             |
| commodityTag    | varchar(8)   |                             |
| commodityDetail | text         |                             |
| commodityPrice  | float        |                             |
| commodityShow   | int          | defalut 1                   |

### 购物车表 `Shopping`

| 字段        | 类型 | 其他                        |
| ----------- | ---- | --------------------------- |
| shoppingId  | int  | Primary Key Unique Required |
| commodityId | int  | Required                    |
| userId      | int  | Required                    |

### 订单表 `Oder`

| 字段        | 类型        | 其他                            |
| ----------- | ----------- | ------------------------------- |
| oderId      | int         | Primary Key Unique Required     |
| commodityId | int         | Required                        |
| customerId  | int         | Required                        |
| oderNumber   | int       | Required                        |
| oderAddress | varchar(50) | Required                        |
| oderStatus  | int         | 0已取消 1未发货 2已发货 3已收货 |
| oderShow    | int         | Required defalut 1              |
| createDate  | timestamp   | Required                        |
| updataDate  | timestamp   | Required                        |

