# 喵星守护站 — 城市流浪猫救助与领养平台

基于 **Spring Boot 3 + MyBatis + MySQL 8** 的全栈流浪猫救助 Web 平台，前端采用原生 HTML/CSS/JavaScript，提供猫咪领养、寻猫招领、志愿者管理、爱心义卖、用户留言等完整功能。

---

## 目录

- [项目概述](#项目概述)
- [技术栈](#技术栈)
- [项目结构](#项目结构)
- [快速开始](#快速开始)
- [功能模块详解](#功能模块详解)
- [API 接口文档](#api-接口文档)
- [数据库设计](#数据库设计)
- [前端页面清单](#前端页面清单)
- [常见问题](#常见问题)

---

## 项目概述

喵星守护站是一个公益性质的流浪猫救助平台，旨在连接救助者和领养者，帮助城市流浪猫找到温暖的家：

- **领养代替购买** — 展示待领养猫咪信息，在线提交领养申请
- **寻猫招领** — 发布走失/拾获信息，帮助猫咪回家
- **志愿者网络** — 招募和管理救助志愿者
- **爱心义卖** — 出售公益周边，收入 100% 用于救助
- **用户留言** — 用户可直接向平台管理员反馈

---

## 技术栈

### 后端

| 技术 | 版本 | 用途 |
|------|------|------|
| Spring Boot | 3.x | 主框架 |
| Spring Security | 6.x | 认证授权 |
| MyBatis | 3.x | ORM |
| MySQL | 8.x | 数据库 |
| Maven | 3.9+ | 构建管理 |

### 前端

| 技术 | 用途 |
|------|------|
| 原生 HTML5 + CSS3 | 页面结构与样式 |
| Vanilla JavaScript | 交互逻辑 |
| CSS Variables | 主题系统 |
| IntersectionObserver | 滚动动画 |
| Flexbox / Grid | 响应式布局 |
| Google Fonts | 字体（Noto Serif SC + Quicksand） |

---

## 项目结构

```
cat-rescue-platform/
├── cat-rescue-web/                        # Web 层（启动入口）
│   ├── src/main/java/com/miaoxing/web/
│   │   ├── CatRescueApplication.java      # 主启动类
│   │   ├── config/
│   │   │   └── StaticResourceConfig.java   # 静态资源配置
│   │   ├── controller/                    # REST 控制器
│   │   │   ├── AuthController.java        # 登录/注册/密码重置
│   │   │   ├── CatController.java         # 猫咪 CRUD
│   │   │   ├── AdoptionController.java    # 领养申请
│   │   │   ├── LostPetController.java     # 寻猫招领
│   │   │   ├── VolunteerController.java   # 志愿者
│   │   │   ├── ContactController.java     # 用户留言
│   │   │   ├── NotificationController.java # 系统通知
│   │   │   ├── PageController.java        # 页面路由
│   │   │   ├── HealthController.java      # 健康检查
│   │   │   └── GlobalExceptionHandler.java # 全局异常处理
│   │   └── dto/                           # 请求/响应 DTO
│   │       ├── LoginRequest.java
│   │       ├── RegisterRequest.java
│   │       ├── CatRequest.java
│   │       ├── AdoptionRequest.java
│   │       ├── LostPetRequest.java
│   │       ├── VolunteerRequest.java
│   │       └── StatusUpdateRequest.java
│   └── src/main/resources/
│       ├── application.yml                # 应用配置
│       └── static/                        # 前端静态页面
│           ├── index.html                 # 首页
│           ├── wiki.html                  # 百科
│           ├── adoption.html              # 领养中心
│           ├── stories.html               # 救助故事
│           ├── lost-found.html            # 寻猫招领
│           ├── volunteer.html             # 志愿者
│           ├── donate.html                # 爱心义卖
│           ├── contact.html               # 联系我们
│           ├── login.html                 # 登录
│           ├── register.html              # 注册
│           ├── reset-password.html        # 重置密码
│           ├── admin.html                 # 管理后台
│           ├── wallet.html                # 钱包
│           ├── 404.html                   # 404 页面
│           └── 图片/                      # 静态资源图片
│
├── cat-rescue-business/                   # 业务层
│   ├── src/main/java/com/miaoxing/business/
│   │   ├── entity/                        # 数据实体
│   │   │   ├── User.java                  # 用户
│   │   │   ├── Cat.java                   # 猫咪
│   │   │   ├── Adoption.java              # 领养申请
│   │   │   ├── LostPet.java               # 寻猫招领
│   │   │   ├── Volunteer.java             # 志愿者
│   │   │   ├── ContactMessage.java        # 用户留言
│   │   │   ├── SystemNotification.java    # 系统通知
│   │   │   └── view/AdoptionView.java     # 领养视图
│   │   ├── mapper/                        # MyBatis Mapper
│   │   ├── service/                       # 业务接口
│   │   └── service/impl/                  # 业务实现
│   └── src/main/resources/mapper/         # XML 映射文件
│       ├── UserMapper.xml
│       ├── CatMapper.xml
│       ├── AdoptionMapper.xml
│       ├── LostPetMapper.xml
│       ├── VolunteerMapper.xml
│       ├── ContactMessageMapper.xml
│       └── SystemNotificationMapper.xml
│
├── cat-rescue-common/                     # 通用工具层
│   └── src/main/java/com/miaoxing/common/
│       ├── aop/RequestLogAspect.java      # 请求日志切面
│       └── result/ApiResponse.java        # 统一响应封装
│
├── cat-rescue-security/                   # 安全认证层
│   └── src/main/java/com/miaoxing/security/
│       ├── config/SecurityConfig.java     # Spring Security 配置
│       ├── auth/DatabaseUserDetailsService.java
│       └── auth/PlainTextCompatiblePasswordEncoder.java
│
├── backend/                               # Node.js 辅助服务
│   ├── server.js
│   └── package.json
│
├── pom.xml                                # 父 POM（多模块聚合）
├── cat_rescue.sql                         # 数据库初始化脚本
└── 打开网页.bat                           # 快捷启动脚本
```

---

## 快速开始

### 环境要求

- JDK 21+
- Maven 3.9+
- MySQL 8.x
- Node.js（可选，用于辅助服务）

### 安装步骤

#### 1. 初始化数据库

```sql
-- 创建数据库
CREATE DATABASE cat_rescue CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 导入表结构
source cat_rescue.sql;

-- 查看所有表
USE cat_rescue;
SHOW TABLES;
```

数据库包含以下表：

| 表名 | 说明 |
|------|------|
| `users` | 用户账号（用户名、密码、角色） |
| `cats` | 猫咪信息（名字、年龄、性别、状态、描述） |
| `adoptions` | 领养申请（申请人、联系方式、住房、经验） |
| `lost_pets` | 寻猫招领（宠物名、状态、描述、联系方式） |
| `volunteers` | 志愿者申请（姓名、电话、理由） |
| `contact_messages` | 用户留言（姓名、联系方式、留言内容） |
| `system_notifications` | 系统通知 |

#### 2. 配置数据库连接

编辑 `cat-rescue-web/src/main/resources/application.yml`：

```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/cat_rescue?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password:     # 你的数据库密码
```

#### 3. 启动应用

```bash
# 方式一：Maven 直接运行
mvn spring-boot:run -pl cat-rescue-web

# 方式二：打包后运行
mvn clean package -DskipTests
java -jar cat-rescue-web/target/cat-rescue-web-1.0.0.jar
```

#### 4. 访问页面

- **用户端**: `http://localhost:8081`
- **管理员登录**: `http://localhost:8081/login.html`
- **管理后台**: `http://localhost:8081/admin.html`（需管理员账号登录）

**默认管理员账号：`admin` / `123456`**

---

## 功能模块详解

### 首页 (`index.html`)

平台形象展示页面：
- 品牌 Logo 与导航栏
- 数据统计展示（救助猫数、领养成功数、志愿者数）
- 核心功能快速入口卡片
- 底部信息栏

### 百科 (`wiki.html`)

猫咪品种与养护知识库：
- 春夏秋冬四季养护指南
- 各品种猫咪信息展示（波斯猫、英短蓝猫、三花猫、虎斑猫等）
- 响应式卡片布局

### 领养中心 (`adoption.html`)

核心领养功能页面：
- 猫咪卡片网格展示（照片、名字、年龄、性别、状态）
- 筛选功能：按性别（公/母）、年龄（幼猫/成猫/老年）、状态（待领养/已领养）筛选
- 领养申请弹窗表单
- 申请后自动通知管理员

### 救助故事 (`stories.html`)

时间线形式的救助案例记录：
- 纵向时间轴布局
- 照片 + 文字故事卡片
- 3D 悬停动画效果
- 滚动淡入动画

### 寻猫招领 (`lost-found.html`)

走失与拾获信息发布平台：
- 英雄区域：紧急氛围引导
- 状态标签：急寻（红色）/ 招领（橙色）/ 已回家（绿色）
- 信息卡片展示（照片、宠物名、描述、联系方式）
- 自动刷新（每 5 秒）
- 每个卡片可一键拨号联系

### 志愿者 (`volunteer.html`)

志愿者招募页面：
- 招募信息展示
- 在线报名表单（姓名、电话、申请理由）
- 后端数据自动入库

### 爱心义卖 (`donate.html`)

公益商品购买平台：
- 商品展示（帆布袋、台历、徽章等公益周边）
- 购物车系统：添加、删除、数量统计
- 钱包支付：基于 localStorage 的余额系统
- 一键捐助：快速捐助固定金额
- 余额实时刷新

### 联系我们 (`contact.html`)

联系信息与反馈页面：
- 联系方式卡片（地址、电话、邮箱、公众号）
- 留言表单：姓名 + 联系方式 + 留言内容
- 留言提交后管理员可在后台查看
- 常见问题 FAQ：手风琴式展开/收起

### 登录/注册 (`login.html` / `register.html`)

用户认证模块：
- 用户登录（用户名 + 密码）
- 新用户注册
- 密码重置
- 角色区分（普通用户 / 管理员）

### 管理后台 (`admin.html`)

平台统一管理中心：

| 模块 | 功能 |
|------|------|
| 仪表盘 | 猫咪总数、待审核申请、志愿者数、已领养数 |
| 猫咪管理 | 查看/发布猫咪、标记已领养、删除 |
| 寻猫招领 | 查看/发布信息、标记已回家、删除 |
| 领养申请 | 查看申请人详细信息、删除 |
| 志愿者管理 | 查看志愿者申请、删除 |
| 用户留言 | 查看用户提交的留言 |

### 钱包 (`wallet.html`)

虚拟钱包系统：
- 银行卡绑定/开卡
- 模拟存款
- 充值到平台余额
- 余额数字滚动动画

---

## API 接口文档

### 认证接口

| 方法 | 路径 | 说明 | 参数 |
|------|------|------|------|
| POST | `/api/login` | 用户登录 | `{username, password}` |
| POST | `/api/register` | 用户注册 | `{username, password, role}` |
| POST | `/api/reset-password` | 重置密码 | `{username, password}` |

### 猫咪接口

| 方法 | 路径 | 说明 | 参数 |
|------|------|------|------|
| GET | `/adoption-cats` | 获取所有猫咪 | — |
| GET | `/adoption-cats/conditions` | 条件筛选猫咪 | `?status=&gender=&ageKeyword=&nameKeyword=` |
| POST | `/adoption-cats` | 发布新猫咪 | `{name, age, gender, status, description, image_url}` |
| PUT | `/adoption-cats/{id}` | 更新猫咪状态 | `{status}` |
| DELETE | `/adoption-cats/{id}` | 删除猫咪 | — |

### 领养申请接口

| 方法 | 路径 | 说明 | 参数 |
|------|------|------|------|
| GET | `/adoptions` | 获取所有申请 | — |
| POST | `/adoptions` | 提交领养申请 | `{cat_name, applicant_name, phone, housing, experience}` |
| DELETE | `/adoptions/{id}` | 删除申请 | — |

### 寻猫招领接口

| 方法 | 路径 | 说明 | 参数 |
|------|------|------|------|
| GET | `/lost-pets` | 获取所有信息 | — |
| POST | `/lost-pets` | 发布新信息 | `{name, status, desc, contact, img}` |
| PUT | `/lost-pets/{id}` | 更新状态 | `{status}` |
| DELETE | `/lost-pets/{id}` | 删除信息 | — |

### 志愿者接口

| 方法 | 路径 | 说明 | 参数 |
|------|------|------|------|
| GET | `/volunteers` | 获取所有申请 | — |
| POST | `/volunteers` | 提交申请 | `{name, phone, reason}` |
| DELETE | `/volunteers/{id}` | 删除申请 | — |

### 留言/通知接口

| 方法 | 路径 | 说明 | 参数 |
|------|------|------|------|
| POST | `/contact/message` | 提交留言 | `{name, contact, message}` |
| GET | `/contact/messages` | 获取留言列表 | — |
| GET | `/system-notifications` | 获取系统通知 | — |

---

## 数据库设计

### E-R 关系

```
users ──┬── 系统用户认证
        ├── 管理员管理猫咪
        ├── 提交领养申请 → adoptions
        ├── 发布寻猫招领 → lost_pets
        ├── 提交志愿者申请 → volunteers
        └── 提交用户留言 → contact_messages
```

### 表结构

#### users（用户表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT PK AUTO_INCREMENT | 主键 |
| username | VARCHAR(50) UNIQUE | 用户名 |
| password | VARCHAR(255) | 密码 |
| role | VARCHAR(20) | 角色（user/admin） |
| created_at | DATETIME | 创建时间 |

#### cats（猫咪表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT PK AUTO_INCREMENT | 主键 |
| name | VARCHAR(50) | 猫咪名字 |
| age | VARCHAR(20) | 年龄（如"3岁"、"6个月"） |
| gender | VARCHAR(10) | 性别（公/母） |
| status | VARCHAR(20) | 状态（待领养/已领养） |
| description | TEXT | 描述 |
| image_url | TEXT | 图片 |
| created_at | DATETIME | 创建时间 |

#### adoptions（领养申请表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT PK AUTO_INCREMENT | 主键 |
| cat_name | VARCHAR(50) | 意向猫咪名字 |
| applicant_name | VARCHAR(50) | 申请人姓名 |
| phone | VARCHAR(20) | 联系电话 |
| housing | VARCHAR(100) | 住房情况 |
| experience | TEXT | 养宠经验 |
| apply_date | DATETIME | 创建时间 |

#### lost_pets（寻猫招领表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT PK AUTO_INCREMENT | 主键 |
| pet_name | VARCHAR(50) | 宠物名 |
| status | VARCHAR(10) | 状态（lost/found） |
| description | VARCHAR(255) | 描述 |
| contact | VARCHAR(50) | 联系方式 |
| image_url | TEXT | 图片 |
| publish_date | DATETIME | 发布时间 |

#### volunteers（志愿者申请表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT PK AUTO_INCREMENT | 主键 |
| name | VARCHAR(50) | 姓名 |
| phone | VARCHAR(20) | 电话 |
| reason | TEXT | 申请理由 |
| status | VARCHAR(20) | 审核状态（pending/approved/rejected） |
| reject_reason | VARCHAR(255) | 拒绝原因 |
| apply_date | DATETIME | 申请时间 |

#### contact_messages（用户留言表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT PK AUTO_INCREMENT | 主键 |
| name | VARCHAR(50) | 留言人姓名 |
| contact | VARCHAR(100) | 联系方式 |
| message | TEXT | 留言内容 |
| created_at | TIMESTAMP | 留言时间 |

#### system_notifications（系统通知表）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT PK AUTO_INCREMENT | 主键 |
| message | TEXT | 通知内容 |
| target | VARCHAR(20) | 目标（all/admin/user） |
| created_at | TIMESTAMP | 创建时间 |

---

## 前端页面清单

| # | 页面 | 文件 | 核心功能 |
|---|------|------|----------|
| 1 | 首页 | `index.html` | 品牌形象、数据统计、功能入口 |
| 2 | 百科 | `wiki.html` | 猫咪品种、养护知识 |
| 3 | 领养中心 | `adoption.html` | 猫咪展示、筛选、领养申请 |
| 4 | 救助故事 | `stories.html` | 时间线案例、滚动动画 |
| 5 | 寻猫招领 | `lost-found.html` | 信息发布、状态标签、自动刷新 |
| 6 | 志愿者 | `volunteer.html` | 招募信息、在线报名 |
| 7 | 爱心义卖 | `donate.html` | 商品展示、购物车、钱包支付 |
| 8 | 联系我们 | `contact.html` | 联系方式、留言表单、FAQ |
| 9 | 登录 | `login.html` | 用户认证 |
| 10 | 注册 | `register.html` | 新用户注册 |
| 11 | 重置密码 | `reset-password.html` | 密码重置 |
| 12 | 管理后台 | `admin.html` | 数据总览、CRUD 管理 |
| 13 | 钱包 | `wallet.html` | 虚拟银行卡、充值、余额管理 |
| 14 | 404 | `404.html` | 页面未找到 |

---

## 主题系统

所有页面采用统一的暖棕色系设计语言：

```css
:root {
    --bg: #f6efe6;              /* 页面背景 - 暖米色 */
    --primary: #8b5e3c;         /* 主色 - 深棕 */
    --primary-deep: #6d4529;    /* 主色深 - 深褐 */
    --accent: #dca87f;          /* 强调色 - 杏色 */
    --accent-soft: #f2d8c1;     /* 强调色浅 */
    --text: #4f3528;            /* 文字色 - 深棕 */
    --text-soft: #7d6355;       /* 文字色浅 */
    --success: #6f8f66;         /* 成功色 - 墨绿 */
}
```

---

## 常见问题

**Q: 启动报数据库连接失败怎么办？**
A: 检查 MySQL 是否运行，`application.yml` 中的用户名密码是否正确。

**Q: 管理员初始密码是什么？**
A: 默认管理员账号 `admin`，密码 `123456`，可在 `users` 表中修改。

**Q: 图片无法显示？**
A: 图片使用本地路径 `图片/xxx.png`，请确保项目完整解压且在 Spring Boot 静态资源目录下。部分示例数据使用 Unsplash 远程图片。

**Q: 修改前端页面后不生效？**
A: Spring Boot 从 `target/classes/static/` 目录加载静态资源，修改后需重启应用或手动复制文件到 target 目录。

---

## 许可证

本项目为开源公益项目，仅用于学习和非商业用途。

---

> 每一次相遇都是奇迹，从流浪到回家，喵星守护站一直在路上。