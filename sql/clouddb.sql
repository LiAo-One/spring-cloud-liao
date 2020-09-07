/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : clouddb

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 07/09/2020 16:52:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `serial` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务流水号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES (1, 'Mr.Wick');
INSERT INTO `payment` VALUES (2, 'Java');
INSERT INTO `payment` VALUES (3, 'Java');
INSERT INTO `payment` VALUES (4, NULL);
INSERT INTO `payment` VALUES (5, NULL);
INSERT INTO `payment` VALUES (6, '123');
INSERT INTO `payment` VALUES (7, '123');
INSERT INTO `payment` VALUES (8, '789');

SET FOREIGN_KEY_CHECKS = 1;
