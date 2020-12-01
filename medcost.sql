/*
 Navicat Premium Data Transfer

 Source Server         : medcost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : medcost

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 01/12/2020 14:39:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf16 COLLATE utf16_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '计算机学院', NULL, NULL, 1);
INSERT INTO `department` VALUES (2, '信息学院', NULL, NULL, 1);
INSERT INTO `department` VALUES (3, '金融学院', NULL, NULL, 1);
INSERT INTO `department` VALUES (4, '管理学院', NULL, NULL, 1);
INSERT INTO `department` VALUES (5, '外语学院', NULL, NULL, 1);
INSERT INTO `department` VALUES (6, '财经学院', NULL, NULL, 1);
INSERT INTO `department` VALUES (7, '人事处', NULL, NULL, 1);
INSERT INTO `department` VALUES (8, '教务处', NULL, NULL, 1);
INSERT INTO `department` VALUES (9, '财务部', NULL, NULL, 1);
INSERT INTO `department` VALUES (10, '科技处', NULL, NULL, 1);
INSERT INTO `department` VALUES (11, '后勤处', NULL, NULL, 1);
INSERT INTO `department` VALUES (12, '保卫处', NULL, NULL, 1);

-- ----------------------------
-- Table structure for expense_account
-- ----------------------------
DROP TABLE IF EXISTS `expense_account`;
CREATE TABLE `expense_account`  (
  `id` bigint(20) NOT NULL,
  `serial_num` char(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '流水号（时间戳17+随机码3）',
  `f_hospital_id` bigint(20) NULL DEFAULT NULL COMMENT '转诊前医院',
  `referral_img` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '转诊单照片',
  `l_hospital_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '转诊后医院名',
  `room` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '科室',
  `deadline` datetime(0) NULL DEFAULT NULL COMMENT '转诊单规定时间',
  `expense_type_id` int(4) NULL DEFAULT NULL COMMENT '报销类型',
  `username` char(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '关联学工号',
  `regist_img` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '挂号单照片',
  `regist_time` datetime(0) NULL DEFAULT NULL COMMENT '挂号时间',
  `regist_fee` double NULL DEFAULT NULL COMMENT '挂号费用',
  `disease` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `prescription_img` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `invoice_img` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发票照片',
  `invoice_time` datetime(0) NULL DEFAULT NULL COMMENT '发票时间',
  `invoice_fee` double NULL DEFAULT NULL COMMENT '发票费用',
  `status` int(4) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '报销单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expense_account
-- ----------------------------
INSERT INTO `expense_account` VALUES (1, '111111', 1, 'c://', '北医三院', '口腔科', '2020-12-16 20:41:29', 1, 'weiser', 'c://', '2020-11-04 20:42:53', 50, '拔智齿', 'c://', 'c://', '2020-11-13 20:43:30', 1200, 1, NULL, NULL);
INSERT INTO `expense_account` VALUES (2, '222222', 1, 'c://', '北医三院', '口腔科', '2020-12-16 20:41:29', NULL, 'weiser', 'c://', '2020-11-04 20:42:53', 50, '拔智齿', 'c://', 'c://', '2020-11-13 20:43:30', 1200, 1, NULL, NULL);

-- ----------------------------
-- Table structure for expense_type
-- ----------------------------
DROP TABLE IF EXISTS `expense_type`;
CREATE TABLE `expense_type`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `f_ratio` float NULL DEFAULT NULL COMMENT '超额前报销比例',
  `l_ratio` float NULL DEFAULT NULL COMMENT '超额后报销比例',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '报销类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expense_type
-- ----------------------------
INSERT INTO `expense_type` VALUES (1, '学生报销', NULL, NULL, NULL, NULL);
INSERT INTO `expense_type` VALUES (2, '职工报销', NULL, NULL, NULL, NULL);
INSERT INTO `expense_type` VALUES (3, '离休人员报销', NULL, NULL, NULL, NULL);
INSERT INTO `expense_type` VALUES (4, '其他人员报销', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '医院名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '指定就诊医院表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hospital
-- ----------------------------
INSERT INTO `hospital` VALUES (1, '爱家医院', NULL, NULL);
INSERT INTO `hospital` VALUES (2, '301医院', NULL, NULL);
INSERT INTO `hospital` VALUES (3, '北医三院', NULL, NULL);

-- ----------------------------
-- Table structure for reviewer_expense_account_relation
-- ----------------------------
DROP TABLE IF EXISTS `reviewer_expense_account_relation`;
CREATE TABLE `reviewer_expense_account_relation`  (
  `id` bigint(20) NOT NULL,
  `reviewer_id_num` bigint(20) NULL DEFAULT NULL COMMENT '审核人学工号',
  `expense_account_id` bigint(20) NULL DEFAULT NULL,
  `reviewer_master_id_num` bigint(20) NULL DEFAULT NULL COMMENT '审核负责人学工号',
  `comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改意见',
  `status` int(4) NULL DEFAULT NULL COMMENT '状态',
  `is_cancel` tinyint(4) NULL DEFAULT NULL,
  `total` double NULL DEFAULT NULL COMMENT '报销总金额',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '审核人报销单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reviewer_expense_account_relation
-- ----------------------------
INSERT INTO `reviewer_expense_account_relation` VALUES (1, 38, 1, 37, '没有转诊单', 0, 0, 1500, '2020-11-06 20:56:21', '2020-11-11 20:56:25');
INSERT INTO `reviewer_expense_account_relation` VALUES (2, 38, 1, 37, '没有挂号单', 0, 0, 1500, NULL, NULL);
INSERT INTO `reviewer_expense_account_relation` VALUES (3, 38, 2, 39, '没有转诊单', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL,
  `value` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'USER', '普通用户', NULL, NULL);
INSERT INTO `role` VALUES (2, 'ADMIN', '管理员', NULL, NULL);
INSERT INTO `role` VALUES (3, 'REVIEWER', '审核人', NULL, NULL);
INSERT INTO `role` VALUES (4, 'MASTER', NULL, NULL, NULL);
INSERT INTO `role` VALUES (5, 'DELIVER', '收单员', NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学工号',
  `realname` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `age` int(5) NULL DEFAULT NULL COMMENT '年龄',
  `type` int(4) NULL DEFAULT NULL COMMENT '用户类型（学生0/员工1）',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `school` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院',
  `major` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `grade` int(5) NULL DEFAULT NULL COMMENT '年级',
  `department` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `annual_expense` double(45, 0) NULL DEFAULT NULL COMMENT '年度报销金额',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_num_UNIQUE`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2020212029', '史祺', '$2a$10$5xiEj2d1qbuw9jfGAmjslOHQl8IevyYTFuciNzlyQHHZ6.PSQTnCW', 1, 22, 1, '2018-09-29 13:55:30', '2018-09-29 13:55:30', '计算机学院', '计算机科学与技术', 2020, '', 0, '2020-10-31 19:36:49');
INSERT INTO `user` VALUES (35, '3de3', 'string', '$2a$10$4iZO9tLHFm3KRC87tm7tyOhJ3d5yZ9pyxTelxA4IC/rLjGhR1caBy', 0, 0, 0, '2020-10-24 12:54:30', '2020-10-24 13:04:53', 'string', 'string', 0, 'string', 0, NULL);
INSERT INTO `user` VALUES (37, 'master', '审核负责人', '$2a$10$OHi1BAkeUK07sWKVmpZKv.qw2SYdIfqAqFvZOJBhX4aerdYoHf.Ka', 0, 24, 0, '2020-10-24 21:46:42', '2020-10-24 21:49:22', 'string', 'string', 0, '审核部门', 0, NULL);
INSERT INTO `user` VALUES (38, 'reviewer', '测试审核员', '123456', 0, 23, 1, '2020-11-05 20:37:10', '2020-10-24 22:37:15', '', '计算机科学与技术', 2020, '审核部门', 0, '2020-11-14 20:39:03');
INSERT INTO `user` VALUES (39, 'weiser', '徐维泽', '123456', 0, 22, 1, '2020-11-05 20:36:20', '2020-10-30 11:29:34', '计算机学院', '计算机科学与技术', 2016, '', 0, '2020-10-30 11:30:11');
INSERT INTO `user` VALUES (40, 'xwz', 'string', '$2a$10$1XpcZMNVNbHqcA4o7Yat9.gkpDV/zNjDQtJ3SYXhQT8jLUAwCLIcy', 0, 0, 1, '2020-11-30 19:55:23', '2020-11-30 19:56:41', 'string', 'string', 0, 'string', 0, '2020-11-30 19:56:41');

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation` VALUES (1, 39, 1, NULL, NULL);
INSERT INTO `user_role_relation` VALUES (2, 37, 1, NULL, NULL);
INSERT INTO `user_role_relation` VALUES (3, 37, 2, NULL, NULL);
INSERT INTO `user_role_relation` VALUES (4, 37, 4, NULL, NULL);
INSERT INTO `user_role_relation` VALUES (5, 38, 3, NULL, NULL);
INSERT INTO `user_role_relation` VALUES (6, 1, 1, NULL, NULL);
INSERT INTO `user_role_relation` VALUES (7, 40, 1, NULL, NULL);
INSERT INTO `user_role_relation` VALUES (8, 40, 2, NULL, NULL);
INSERT INTO `user_role_relation` VALUES (9, 40, 3, NULL, NULL);
INSERT INTO `user_role_relation` VALUES (10, 40, 4, NULL, NULL);
INSERT INTO `user_role_relation` VALUES (11, 40, 5, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
