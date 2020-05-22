/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : homeworkdb

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-05-10 16:54:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
`id`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`password`  varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`name`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`type`  int(11) NOT NULL DEFAULT 4 ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci

;

-- ----------------------------
-- Records of administrator
-- ----------------------------
BEGIN;
INSERT INTO `administrator` VALUES ('godder', 'godder', '狗蛋管理员', '1');
COMMIT;

-- ----------------------------
-- Table structure for assignhomework
-- ----------------------------
DROP TABLE IF EXISTS `assignhomework`;
CREATE TABLE `assignhomework` (
`studentid`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`teachcourseid`  int(11) NOT NULL ,
`homeworkid`  int(11) NOT NULL ,
`submittime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`score`  int(11) NULL DEFAULT '-1' ,
`remark`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
FOREIGN KEY (`teachcourseid`) REFERENCES `teachcourse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`studentid`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
UNIQUE INDEX `assignhomework_idx1` (`teachcourseid`, `homeworkid`, `studentid`) USING BTREE ,
INDEX `assignhomework_fk3` (`homeworkid`) USING BTREE ,
INDEX `assignhomework_fk2` (`studentid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci

;

-- ----------------------------
-- Records of assignhomework
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for attendcourse
-- ----------------------------
DROP TABLE IF EXISTS `attendcourse`;
CREATE TABLE `attendcourse` (
`studentid`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`teachcourseid`  int(11) NOT NULL ,
`homeworkscore`  int(11) NULL DEFAULT '-1' ,
`usualscore`  int(11) NULL DEFAULT '-1' ,
`finalscore`  int(11) NOT NULL DEFAULT '-1' ,
FOREIGN KEY (`studentid`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`teachcourseid`) REFERENCES `teachcourse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
UNIQUE INDEX `attendcourse_idx1` (`studentid`, `teachcourseid`) USING BTREE ,
INDEX `attendcourse_fk2` (`teachcourseid`) USING BTREE ,
INDEX `attendcourse_fk1` (`studentid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci

;

-- ----------------------------
-- Records of attendcourse
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`info`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`type`  enum('0','1','2','3') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES ('1', 'J2EE编程', '教授学生java web的相关知识，以servlet为主', '1'), ('2', '数据挖掘', '基于python的数据挖掘课程', '3'), ('5', '数字图像处理', '数字图像的处理的传统方法', '3');
COMMIT;

-- ----------------------------
-- Table structure for discussion
-- ----------------------------
DROP TABLE IF EXISTS `discussion`;
CREATE TABLE `discussion` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`publisherid`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`datetime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`content`  varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`teachcourseid`  int(11) NOT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`teachcourseid`) REFERENCES `teachcourse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`publisherid`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `discussion_fk1` (`teachcourseid`) USING BTREE ,
INDEX `discussion_fk2` (`publisherid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=14

;

-- ----------------------------
-- Records of discussion
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`info`  varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=4

;

-- ----------------------------
-- Records of grade
-- ----------------------------
BEGIN;
INSERT INTO `grade` VALUES ('1', '2017级计算机科学与技术1班', '西南大学中外合作办学'), ('3', '2017级软件工程1班', '西南大学中外合作办学'), ('4', '2018管道工程', '本科');
COMMIT;

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`teachcourseid`  int(11) NOT NULL ,
`name`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`deadline`  timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP ,
`info`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`proportion`  int(11) NULL DEFAULT 0 ,
`format`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' ,
PRIMARY KEY (`id`),
FOREIGN KEY (`teachcourseid`) REFERENCES `teachcourse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `homework_fk1` (`teachcourseid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=13

;

-- ----------------------------
-- Records of homework
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
`ip`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`content`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`operatorid`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`type`  int(11) NOT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci

;

-- ----------------------------
-- Records of log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`teachcourseid`  int(11) NOT NULL ,
`date`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`name`  varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`info`  varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`teachcourseid`) REFERENCES `teachcourse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `message_fk1` (`teachcourseid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=8

;

-- ----------------------------
-- Records of message
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`date`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`info`  varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of notice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sensitivewords
-- ----------------------------
DROP TABLE IF EXISTS `sensitivewords`;
CREATE TABLE `sensitivewords` (
`word`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
PRIMARY KEY (`word`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci

;

-- ----------------------------
-- Records of sensitivewords
-- ----------------------------
BEGIN;
INSERT INTO `sensitivewords` VALUES ('123'), ('fuck'), ('傻逼'), ('妈卖批'), ('山炮'), ('脑残');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
`id`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`name`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`password`  varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`portrait`  longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL ,
`gradeid`  int(11) NULL DEFAULT NULL ,
`tel`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`gradeid`) REFERENCES `grade` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `grade` (`gradeid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci

;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for teachcourse
-- ----------------------------
DROP TABLE IF EXISTS `teachcourse`;
CREATE TABLE `teachcourse` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`courseid`  int(11) NOT NULL ,
`teacherid`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`gradeid`  int(11) NOT NULL ,
`semestor`  date NOT NULL ,
`ispublic`  tinyint(1) NOT NULL DEFAULT 0 ,
PRIMARY KEY (`id`),
FOREIGN KEY (`courseid`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`gradeid`) REFERENCES `grade` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `teachcourse_fk3` (`gradeid`) USING BTREE ,
INDEX `teachcourse_fk2` (`teacherid`) USING BTREE ,
INDEX `teachcourse_idx1` (`courseid`, `teacherid`, `gradeid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=14

;

-- ----------------------------
-- Records of teachcourse
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
`id`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`name`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`password`  varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`portrait`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'default' ,
`info`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci

;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Auto increment value for course
-- ----------------------------
ALTER TABLE `course` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for discussion
-- ----------------------------
ALTER TABLE `discussion` AUTO_INCREMENT=14;

-- ----------------------------
-- Auto increment value for grade
-- ----------------------------
ALTER TABLE `grade` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for homework
-- ----------------------------
ALTER TABLE `homework` AUTO_INCREMENT=13;

-- ----------------------------
-- Auto increment value for message
-- ----------------------------
ALTER TABLE `message` AUTO_INCREMENT=8;

-- ----------------------------
-- Auto increment value for notice
-- ----------------------------
ALTER TABLE `notice` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for teachcourse
-- ----------------------------
ALTER TABLE `teachcourse` AUTO_INCREMENT=14;
