/* ==============================================
   喵星守护站 - 完整数据库代码 (修正版，已统一字段名和路径)
   ============================================== */

-- 1. 如果存在旧库先删除，重新创建 (确保干净)
DROP DATABASE IF EXISTS cat_rescue;
CREATE DATABASE cat_rescue DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cat_rescue;

-- 2. 用户表 (管理员与普通用户)
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(10) DEFAULT 'user', -- 'admin' 或 'user'
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入默认管理员 (账号: admin / 密码: 123456)
INSERT INTO `users` (`username`, `password`, `role`) VALUES
('admin', '123456', 'admin'),
('user01', '123456', 'user');

-- 3. 志愿者申请表
CREATE TABLE `volunteers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `reason` text,
  `status` varchar(20) DEFAULT 'pending',
  `apply_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. 寻猫/招领信息表
CREATE TABLE `lost_pets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pet_name` varchar(50) NOT NULL,
  `status` varchar(10) NOT NULL, -- 'lost' 或 'found'
  `description` varchar(255),
  `contact` varchar(50) NOT NULL,
  `image_url` LONGTEXT, -- 统一使用 LONGTEXT 字段
  `publish_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. 领养申请表
CREATE TABLE `adoptions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(50) NOT NULL,
  `applicant_name` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `housing` varchar(50),
  `experience` text,
  `apply_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6. 待领养猫咪档案表
-- 【关键修正 1】：将 `img_url` 改为 `image_url`，与 server.js 保持一致
CREATE TABLE `cats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `age` varchar(20),
  `gender` varchar(10),
  `status` varchar(20) DEFAULT '待领养', -- 状态: 待领养, 已被领养
  `description` varchar(255),
  `image_url` LONGTEXT, -- 字段名已修正
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 7. 系统通知表 (如果你的 server.js 中有用到)
CREATE TABLE `system_notifications` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `message` TEXT NOT NULL,
    `target` VARCHAR(20) DEFAULT 'all', 
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ==========================================
-- 插入默认数据
-- ==========================================

USE cat_rescue;
-- 【关键修正 2】：插入数据时，图片字段名已修正为 image_url
-- 本地文件路径统一使用文件名，因为 server.js 会自动拼接 /myImages/
INSERT INTO cats (name, age, gender, status, description, image_url) VALUES 
('汤圆', '1岁', '公', '待领养', '性格活泼粘人，是个无敌呼噜怪。', 'https://images.unsplash.com/photo-1574158622682-e40e69881006?w=600'),
('奥利奥', '6个月', '母', '待领养', '有点慢热害羞，需要耐心陪伴。', 'https://images.unsplash.com/photo-1592194996308-7b43878e84a6?w=600'),
('大橘', '3岁', '公', '待领养', '体型健壮，性格沉稳，非常能吃。', '橘猫.png'), -- 统一为文件名
('布丁', '2岁', '母', '待领养', '拥有美丽的异瞳，性格像公主一样优雅。', 'https://images.unsplash.com/photo-1513360371669-4adf3dd7dff8?w=600');

INSERT INTO lost_pets (pet_name, status, description, contact, image_url) VALUES
('小花', 'lost', '三花猫，在小区花园丢失，很胆小。', '13800138000', '三花猫.png'), -- 统一为文件名
('团子', 'lost', '英短蓝猫，脖子上挂着铃铛。', '13900139000', '英短蓝猫.png'); -- 统一为文件名
USE cat_rescue;
-- 把图片字段改成能存大数据的类型
ALTER TABLE cats MODIFY image_url LONGTEXT;
ALTER TABLE lost_pets MODIFY image_url LONGTEXT;

USE cat_rescue;

-- 如果没有用户表，创建它
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `role` varchar(10) DEFAULT 'user',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入默认管理员 (防止你登录不进去)
INSERT IGNORE INTO `users` (`username`, `password`, `role`) VALUES ('admin', '123456', 'admin');