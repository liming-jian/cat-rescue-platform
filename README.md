# 🐱 喵星守护站 - 城市流浪猫救助与领养平台

一个基于 Spring Boot 的流浪猫救助全栈 Web 平台，提供猫咪领养、寻猫招领、志愿者管理、爱心义卖等完整功能。

## 项目结构

```
├── cat-rescue-web/          # Web 层（控制器、静态页面）
├── cat-rescue-business/     # 业务层（实体、Mapper、Service）
├── cat-rescue-common/       # 通用工具层
├── cat-rescue-security/     # 安全认证层
├── backend/                 # Node.js 后端服务
├── 图片/                    # 项目静态资源图片
└── cat_rescue.sql           # 数据库初始化脚本
```

## 技术栈

- **后端**: Spring Boot 3.x, MyBatis, MySQL
- **前端**: 原生 HTML/CSS/JavaScript
- **安全**: Spring Security
- **构建**: Maven 多模块

## 快速启动

1. 导入 `cat_rescue.sql` 到本地 MySQL
2. 修改 `application.yml` 中的数据库配置
3. 启动 Spring Boot 应用
4. 访问 `http://localhost:8081`

## 功能模块

| 模块 | 说明 |
|------|------|
| 首页 | 平台概览与统计数据 |
| 百科 | 猫咪品种与养护知识 |
| 领养中心 | 待领养猫咪展示与申请 |
| 救助故事 | 流浪猫救助案例分享 |
| 寻猫招领 | 走失与拾获信息发布 |
| 志愿者 | 志愿者招募与管理 |
| 爱心义卖 | 公益周边商品购买 |
| 管理后台 | 猫咪/申请/留言/志愿者管理 |
