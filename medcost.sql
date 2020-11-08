-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: medcost
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf16_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense_account`
--

DROP TABLE IF EXISTS `expense_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense_account` (
  `id` bigint(20) NOT NULL,
  `serial_num` bigint(20) DEFAULT NULL COMMENT '流水号（时间戳17+随机码3）',
  `f_hospital_id` bigint(20) DEFAULT NULL COMMENT '转诊前医院',
  `referral_img` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '转诊单照片',
  `l_hospital_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '转诊后医院名',
  `room` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '科室',
  `deadline` datetime DEFAULT NULL COMMENT '转诊单规定时间',
  `expense_type_id` int(4) DEFAULT NULL COMMENT '报销类型',
  `username` char(30) COLLATE utf8_bin DEFAULT NULL COMMENT '关联学工号',
  `regist_img` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '挂号单照片',
  `regist_time` datetime DEFAULT NULL COMMENT '挂号时间',
  `regist_fee` double DEFAULT NULL COMMENT '挂号费用',
  `disease` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `prescription_img` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `invoice_img` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '发票照片',
  `invoice_time` datetime DEFAULT NULL COMMENT '发票时间',
  `invoice_fee` double DEFAULT NULL COMMENT '发票费用',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `expense_accountcol` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='报销单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_account`
--

LOCK TABLES `expense_account` WRITE;
/*!40000 ALTER TABLE `expense_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `expense_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense_type`
--

DROP TABLE IF EXISTS `expense_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `f_ratio` float DEFAULT NULL COMMENT '超额前报销比例',
  `l_ratio` float DEFAULT NULL COMMENT '超额后报销比例',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='报销类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense_type`
--

LOCK TABLES `expense_type` WRITE;
/*!40000 ALTER TABLE `expense_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `expense_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '医院名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='指定就诊医院表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer_expense_account_relation`
--

DROP TABLE IF EXISTS `reviewer_expense_account_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviewer_expense_account_relation` (
  `id` bigint(20) NOT NULL,
  `reviewer_id_num` bigint(20) DEFAULT NULL COMMENT '审核人学工号',
  `expense_account_id` bigint(20) DEFAULT NULL,
  `reviewer_master_id_num` bigint(20) DEFAULT NULL COMMENT '审核负责人学工号',
  `comment` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '修改意见',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `is_cancel` tinyint(4) DEFAULT NULL,
  `total` double DEFAULT NULL COMMENT '报销总金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='审核人报销单关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer_expense_account_relation`
--

LOCK TABLES `reviewer_expense_account_relation` WRITE;
/*!40000 ALTER TABLE `reviewer_expense_account_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviewer_expense_account_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `value` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'USER','普通用户',NULL,NULL),(2,'ADMIN','管理员',NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` char(30) NOT NULL COMMENT '学工号',
  `realname` varchar(24) DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别',
  `age` int(5) DEFAULT NULL COMMENT '年龄',
  `type` int(4) DEFAULT NULL COMMENT '用户类型（员工/学生）',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `school` varchar(45) DEFAULT NULL COMMENT '学院',
  `major` varchar(45) DEFAULT NULL COMMENT '专业',
  `grade` int(5) DEFAULT NULL COMMENT '年级',
  `department` varchar(45) DEFAULT NULL COMMENT '部门',
  `annual_expense` varchar(45) DEFAULT NULL COMMENT '年度报销金额',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_num_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2020212029','史祺','$2a$10$5xiEj2d1qbuw9jfGAmjslOHQl8IevyYTFuciNzlyQHHZ6.PSQTnCW',1,22,1,'2018-09-29 13:55:30','2018-09-29 13:55:30','计算机学院','计算机科学与技术',2020,'','0','2020-10-31 19:36:49'),(35,'3de3','string','$2a$10$4iZO9tLHFm3KRC87tm7tyOhJ3d5yZ9pyxTelxA4IC/rLjGhR1caBy',0,0,0,'2020-10-24 12:54:30','2020-10-24 13:04:53','string','string',0,'string','string',NULL),(37,'123456789','string','$2a$10$OHi1BAkeUK07sWKVmpZKv.qw2SYdIfqAqFvZOJBhX4aerdYoHf.Ka',0,0,0,'2020-10-24 21:46:42','2020-10-24 21:49:22','string','string',0,'string','string',NULL),(38,'12345678901234',NULL,NULL,NULL,NULL,NULL,NULL,'2020-10-24 22:37:15',NULL,NULL,NULL,NULL,NULL,NULL),(39,'sdfsdfsd','dsfasdf','$2a$10$yDfujpCcNN0LS5EVxuPSv.vCff3nsv2AYjrgJ7s76QYmKzgkUT8D.',0,NULL,1,NULL,'2020-10-30 11:29:34',NULL,NULL,NULL,'12312312',NULL,'2020-10-30 11:30:11');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_relation`
--

DROP TABLE IF EXISTS `user_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户和角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_relation`
--

LOCK TABLES `user_role_relation` WRITE;
/*!40000 ALTER TABLE `user_role_relation` DISABLE KEYS */;
INSERT INTO `user_role_relation` VALUES (2,37,1,NULL,NULL),(3,37,2,NULL,NULL),(6,1,1,NULL,NULL);
/*!40000 ALTER TABLE `user_role_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'medcost'
--

--
-- Dumping routines for database 'medcost'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-08 15:29:55
