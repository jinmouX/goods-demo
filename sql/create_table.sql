# 数据库初始化
#
#

-- 创建库
create database if not exists test_db;

-- 切换库
use test_db;

-- 库存表
create table if not exists test_stock_jinx
(
    goodsId      bigint auto_increment  comment '商品id' primary key,
    name         varchar(128) not null comment '商品名',
    number       int not null default 0 comment '商品数量'
) comment '库存表' collate = utf8mb4_unicode_ci;

INSERT INTO `test_stock_jinx` VALUES (1001, '苹果手机', 10);
INSERT INTO `test_stock_jinx` VALUES (1002, '华为手机', 20);
INSERT INTO `test_stock_jinx` VALUES (1003, '小米手机', 20);
INSERT INTO `test_stock_jinx` VALUES (1004, '天音手机', 20);

create table  if not exists test_order_jinx
(
    id           bigint auto_increment comment 'id' primary key,
    orderId      varchar(128) not null comment '订单id',
    goodsId      bigint not null comment '商品id',
    number       int not null  default 0 comment '商品数量',
    index idx_orderId(orderId)
) comment '订单表' collate = utf8mb4_unicode_ci;

