/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : day

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2025-07-07 15:18:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `pwd` varchar(50) NOT NULL DEFAULT '202cb962ac59075b964b07152d234b70',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10013 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('10001', 'chenle', '123123');
INSERT INTO `admin` VALUES ('10002', 'zhanghong', '4297f44b13955235245b2497399d7a93');
INSERT INTO `admin` VALUES ('10003', 'yanjie', '4297f44b13955235245b2497399d7a93');
INSERT INTO `admin` VALUES ('10004', 'liuping', '4297f44b13955235245b2497399d7a93');
INSERT INTO `admin` VALUES ('10005', 'chenle@987', '4297f44b13955235245b2497399d7a93');
INSERT INTO `admin` VALUES ('10006', 'yidan', '4297f44b13955235245b2497399d7a93');
INSERT INTO `admin` VALUES ('10007', 'zhangsan', '123123');
INSERT INTO `admin` VALUES ('10008', 'lisi', '4297f44b13955235245b2497399d7a93');
INSERT INTO `admin` VALUES ('10009', 'xiaoming', '4297f44b13955235245b2497399d7a93');
INSERT INTO `admin` VALUES ('10010', 'xingxing', '4297f44b13955235245b2497399d7a93');
INSERT INTO `admin` VALUES ('10011', 'test', '202cb962ac59075b964b07152d234b70');
INSERT INTO `admin` VALUES ('10012', 'xingcheng', '4297f44b13955235245b2497399d7a93');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(20) NOT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '人事部');
INSERT INTO `department` VALUES ('2', '后勤部');
INSERT INTO `department` VALUES ('3', '技术部');
INSERT INTO `department` VALUES ('4', '商品部');
INSERT INTO `department` VALUES ('5', '销售部');
INSERT INTO `department` VALUES ('6', '行政部');
INSERT INTO `department` VALUES ('7', '财务部');
INSERT INTO `department` VALUES ('8', '保洁部');
INSERT INTO `department` VALUES ('9', '秘书部');
INSERT INTO `department` VALUES ('10', '保安部');
INSERT INTO `department` VALUES ('11', '测试部');
INSERT INTO `department` VALUES ('12', '学术部');
INSERT INTO `department` VALUES ('13', '公关部');
INSERT INTO `department` VALUES ('14', '技术服务部');
INSERT INTO `department` VALUES ('15', '保卫科');
INSERT INTO `department` VALUES ('16', '技术研究院');
INSERT INTO `department` VALUES ('17', '研究院');
INSERT INTO `department` VALUES ('18', '创新部');

-- ----------------------------
-- Table structure for `duty`
-- ----------------------------
DROP TABLE IF EXISTS `duty`;
CREATE TABLE `duty` (
  `duty_id` int(11) NOT NULL AUTO_INCREMENT,
  `duty_name` varchar(20) NOT NULL,
  PRIMARY KEY (`duty_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of duty
-- ----------------------------
INSERT INTO `duty` VALUES ('1', '正部长');
INSERT INTO `duty` VALUES ('2', '副部长');
INSERT INTO `duty` VALUES ('3', '组长');
INSERT INTO `duty` VALUES ('4', '副组长');
INSERT INTO `duty` VALUES ('5', '正常员工');
INSERT INTO `duty` VALUES ('6', '测试工程师');
INSERT INTO `duty` VALUES ('7', '前端工程师');
INSERT INTO `duty` VALUES ('8', '后端工程师');
INSERT INTO `duty` VALUES ('9', '全栈工程师');
INSERT INTO `duty` VALUES ('10', '软件架构师');
INSERT INTO `duty` VALUES ('11', '市场专员');
INSERT INTO `duty` VALUES ('12', '渠道专员');
INSERT INTO `duty` VALUES ('13', '测试组');
INSERT INTO `duty` VALUES ('14', '销售助理');

-- ----------------------------
-- Table structure for `emp`
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `pwd` varchar(50) NOT NULL DEFAULT '202cb962ac59075b964b07152d234b70',
  `birthday` varchar(20) NOT NULL,
  `address` varchar(30) NOT NULL,
  `dept_id` int(11) NOT NULL,
  `duty_id` int(11) NOT NULL,
  PRIMARY KEY (`number`) USING BTREE,
  KEY `dept_id` (`dept_id`) USING BTREE,
  KEY `duty_id` (`duty_id`) USING BTREE,
  CONSTRAINT `emp_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`),
  CONSTRAINT `emp_ibfk_2` FOREIGN KEY (`duty_id`) REFERENCES `duty` (`duty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('121', '曹孟德', '3d186804534370c3c817db0563f0e461', '1998-09-01', '福州市连江县', '3', '5');
INSERT INTO `emp` VALUES ('122', '关云长', '4297f44b13955235245b2497399d7a93', '2022-10-11', '福建省福州市台江区', '3', '1');
INSERT INTO `emp` VALUES ('123', '张飞', '4297f44b13955235245b2497399d7a93', '2012-06-06', '福建省福州市台江区', '3', '4');
INSERT INTO `emp` VALUES ('127', '黄忠', '4297f44b13955235245b2497399d7a93', '1999-06-09', '陕西省西安市', '3', '4');
INSERT INTO `emp` VALUES ('128', '许褚', '202cb962ac59075b964b07152d234b70', '1998-06-22', '福建省福州市', '4', '5');
INSERT INTO `emp` VALUES ('129', '马超', '202cb962ac59075b964b07152d234b70', '2001-03-06', '福建省厦门市', '3', '3');
INSERT INTO `emp` VALUES ('133', '吕布', '202cb962ac59075b964b07152d234b70', '2021-01-06', '福建省福州市', '3', '2');
INSERT INTO `emp` VALUES ('134', '孙坚', '202cb962ac59075b964b07152d234b70', '2010-05-04', '福建省宁德市', '3', '4');
INSERT INTO `emp` VALUES ('135', '周瑜', '202cb962ac59075b964b07152d234b70', '2004-02-11', '福建省福州市', '1', '2');
INSERT INTO `emp` VALUES ('136', '黄盖', '202cb962ac59075b964b07152d234b70', '2001-03-14', '福建省漳州市', '2', '3');
INSERT INTO `emp` VALUES ('137', '孙策', '202cb962ac59075b964b07152d234b70', '2023-04-03', '福建省福州市', '2', '5');
INSERT INTO `emp` VALUES ('145', '魏延', '202cb962ac59075b964b07152d234b70', '2025-07-07', '福建省福州市', '1', '5');
INSERT INTO `emp` VALUES ('146', '吕蒙', 'caf1a3dfb505ffed0d024130f58c5cfa', '2025-07-01', '福建省福州市', '6', '5');
INSERT INTO `emp` VALUES ('147', '星星', '202cb962ac59075b964b07152d234b70', '1998-09-13', '福州市连江县', '6', '3');

-- ----------------------------
-- Table structure for `sign`
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `signDate` varchar(50) NOT NULL,
  `number` int(11) NOT NULL,
  `state` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `sign_address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `number` (`number`) USING BTREE,
  CONSTRAINT `sign_ibfk_1` FOREIGN KEY (`number`) REFERENCES `emp` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=1373 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('355', '2023-06-05 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('356', '2023-06-05 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('357', '2023-06-05 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('358', '2023-06-05 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('359', '2023-06-05 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('360', '2023-06-05 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('361', '2023-06-05 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('362', '2023-06-05 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('365', '2023-06-05 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('366', '2023-06-05 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('367', '2023-06-05 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('368', '2023-06-05 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('369', '2023-06-05 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('370', '2023-06-05 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('377', '2023-06-05 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('378', '2023-06-05 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('379', '2023-06-05 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('380', '2023-06-05 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('383', '2023-06-05 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('384', '2023-06-05 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('391', '2023-06-06 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('392', '2023-06-06 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('393', '2023-06-06 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('394', '2023-06-06 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('395', '2023-06-06 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('396', '2023-06-06 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('397', '2023-06-06 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('398', '2023-06-06 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('399', '2023-06-06 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('400', '2023-06-06 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('403', '2023-06-06 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('404', '2023-06-06 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('405', '2023-06-06 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('406', '2023-06-06 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('407', '2023-06-06 16:23:28:612', '123', '已签到', 'a', '福建省福州市马尾区');
INSERT INTO `sign` VALUES ('408', '2023-06-06 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('415', '2023-06-06 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('416', '2023-06-06 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('417', '2023-06-06 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('418', '2023-06-06 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('421', '2023-06-06 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('422', '2023-06-06 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('429', '2023-06-07 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('430', '2023-06-07 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('431', '2023-06-07 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('432', '2023-06-07 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('433', '2023-06-07 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('434', '2023-06-07 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('435', '2023-06-07 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('436', '2023-06-07 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('437', '2023-06-07 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('438', '2023-06-07 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('441', '2023-06-07 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('442', '2023-06-07 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('443', '2023-06-07 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('444', '2023-06-07 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('445', '2023-06-07 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('446', '2023-06-07 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('453', '2023-06-07 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('454', '2023-06-07 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('455', '2023-06-07 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('456', '2023-06-07 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('459', '2023-06-07 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('460', '2023-06-07 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('467', '2023-06-09 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('468', '2023-06-09 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('469', '2023-06-09 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('470', '2023-06-09 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('471', '2023-06-09 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('472', '2023-06-09 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('473', '2023-06-09 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('474', '2023-06-09 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('475', '2023-06-09 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('476', '2023-06-09 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('479', '2023-06-09 10:22:59:09', '121', '已签到', 'a', '福建省福州市马尾区');
INSERT INTO `sign` VALUES ('480', '2023-06-09 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('481', '2023-06-09 10:00:16:671', '122', '已签到', 'a', '福建省福州市马尾区');
INSERT INTO `sign` VALUES ('482', '2023-06-09 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('483', '2023-06-09 09:43:41:334', '123', '已签到', 'a', '福建省福州市马尾区');
INSERT INTO `sign` VALUES ('484', '2023-06-09 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('491', '2023-06-09 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('492', '2023-06-09 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('493', '2023-06-09 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('494', '2023-06-09 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('497', '2023-06-09 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('498', '2023-06-09 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('505', '2023-06-10 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('506', '2023-06-10 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('507', '2023-06-10 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('508', '2023-06-10 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('509', '2023-06-10 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('510', '2023-06-10 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('511', '2023-06-10 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('512', '2023-06-10 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('513', '2023-06-10 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('514', '2023-06-10 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('517', '2023-06-10 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('518', '2023-06-10 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('519', '2023-06-10 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('520', '2023-06-10 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('521', '2023-06-10 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('522', '2023-06-10 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('529', '2023-06-10 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('530', '2023-06-10 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('531', '2023-06-10 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('532', '2023-06-10 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('535', '2023-06-10 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('536', '2023-06-10 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('539', '2023-06-13 21:34:49:69', '123', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('540', '2023-06-13 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('545', '2023-06-13 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('546', '2023-06-13 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('547', '2023-06-13 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('548', '2023-06-13 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('549', '2023-06-13 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('550', '2023-06-13 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('551', '2023-06-13 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('552', '2023-06-13 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('553', '2023-06-13 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('554', '2023-06-13 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('557', '2023-06-13 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('558', '2023-06-13 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('559', '2023-06-13 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('560', '2023-06-13 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('567', '2023-06-13 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('568', '2023-06-13 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('569', '2023-06-13 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('570', '2023-06-13 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('573', '2023-06-13 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('574', '2023-06-13 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('577', '2023-06-14 19:09:36:804', '123', '已签到', 'a', '江苏省南通市崇川区');
INSERT INTO `sign` VALUES ('578', '2023-06-14 19:16:35:289', '123', '已签到', 'p', '江苏省南通市崇川区');
INSERT INTO `sign` VALUES ('583', '2023-06-14 19:43:41:700', '137', '已签到', 'a', '江苏省南通市崇川区');
INSERT INTO `sign` VALUES ('584', '2023-06-14 19:53:04:295', '137', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('585', '2023-06-14 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('586', '2023-06-14 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('587', '2023-06-14 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('588', '2023-06-14 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('589', '2023-06-14 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('590', '2023-06-14 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('591', '2023-06-14 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('592', '2023-06-14 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('595', '2023-06-14 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('596', '2023-06-14 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('597', '2023-06-14 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('598', '2023-06-14 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('605', '2023-06-14 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('606', '2023-06-14 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('607', '2023-06-14 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('608', '2023-06-14 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('611', '2023-06-14 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('612', '2023-06-14 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('615', '2023-06-15 08:49:52:557', '123', '已签到', 'a', '江苏省南通市崇川区');
INSERT INTO `sign` VALUES ('616', '2023-06-15 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('621', '2023-06-15 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('622', '2023-06-15 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('623', '2023-06-15 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('624', '2023-06-15 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('625', '2023-06-15 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('626', '2023-06-15 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('627', '2023-06-15 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('628', '2023-06-15 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('629', '2023-06-15 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('630', '2023-06-15 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('633', '2023-06-15 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('634', '2023-06-15 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('635', '2023-06-15 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('636', '2023-06-15 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('643', '2023-06-15 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('644', '2023-06-15 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('645', '2023-06-15 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('646', '2023-06-15 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('649', '2023-06-15 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('650', '2023-06-15 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('733', '2023-06-16 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('734', '2023-06-16 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('739', '2023-06-16 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('740', '2023-06-16 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('741', '2023-06-16 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('742', '2023-06-16 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('743', '2023-06-16 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('744', '2023-06-16 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('745', '2023-06-16 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('746', '2023-06-16 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('747', '2023-06-16 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('748', '2023-06-16 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('751', '2023-06-16 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('752', '2023-06-16 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('753', '2023-06-16 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('754', '2023-06-16 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('759', '2023-06-16 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('760', '2023-06-16 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('761', '2023-06-16 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('762', '2023-06-16 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('765', '2023-06-16 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('766', '2023-06-16 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('769', '2023-06-19 14:53:40:192', '123', '已签到', 'a', '福建省福州市鼓楼区');
INSERT INTO `sign` VALUES ('770', '2023-06-19 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('775', '2023-06-19 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('776', '2023-06-19 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('777', '2023-06-19 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('778', '2023-06-19 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('779', '2023-06-19 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('780', '2023-06-19 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('781', '2023-06-19 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('782', '2023-06-19 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('783', '2023-06-19 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('784', '2023-06-19 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('787', '2023-06-19 15:08:22:125', '121', '已签到', 'a', '福建省福州市鼓楼区');
INSERT INTO `sign` VALUES ('788', '2023-06-19 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('789', '2023-06-19 15:02:23:607', '122', '已签到', 'a', '福建省福州市鼓楼区');
INSERT INTO `sign` VALUES ('790', '2023-06-19 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('795', '2023-06-19 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('796', '2023-06-19 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('797', '2023-06-19 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('798', '2023-06-19 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('801', '2023-06-19 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('802', '2023-06-19 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('805', '2023-06-28 19:57:06:885', '123', '已签到', 'a', '福建省福州市台江区');
INSERT INTO `sign` VALUES ('806', '2023-06-28 20:19:21:519', '123', '已签到', 'p', '福建省福州市台江区');
INSERT INTO `sign` VALUES ('811', '2023-06-28 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('812', '2023-06-28 14:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('813', '2023-06-28 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('814', '2023-06-28 14:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('815', '2023-06-28 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('816', '2023-06-28 14:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('817', '2023-06-28 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('818', '2023-06-28 14:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('819', '2023-06-28 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('820', '2023-06-28 14:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('823', '2023-06-28 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('824', '2023-06-28 14:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('825', '2023-06-28 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('826', '2023-06-28 14:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('831', '2023-06-28 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('832', '2023-06-28 14:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('833', '2023-06-28 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('834', '2023-06-28 14:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('837', '2023-06-28 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('838', '2023-06-28 14:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('841', '2023-07-03 09:52:24:339', '123', '已签到', 'a', '福建省福州市马尾区');
INSERT INTO `sign` VALUES ('842', '2023-07-03 14:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('847', '2023-07-03 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('848', '2023-07-03 14:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('849', '2023-07-03 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('850', '2023-07-03 14:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('851', '2023-07-03 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('852', '2023-07-03 14:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('853', '2023-07-03 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('854', '2023-07-03 14:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('855', '2023-07-03 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('856', '2023-07-03 14:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('859', '2023-07-03 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('860', '2023-07-03 14:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('861', '2023-07-03 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('862', '2023-07-03 14:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('867', '2023-07-03 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('868', '2023-07-03 14:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('869', '2023-07-03 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('870', '2023-07-03 14:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('873', '2023-07-03 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('874', '2023-07-03 14:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('877', '2023-07-04 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('878', '2023-07-04 14:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('883', '2023-07-04 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('884', '2023-07-04 14:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('885', '2023-07-04 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('886', '2023-07-04 14:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('887', '2023-07-04 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('888', '2023-07-04 14:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('889', '2023-07-04 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('890', '2023-07-04 14:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('891', '2023-07-04 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('892', '2023-07-04 14:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('893', '2023-07-04 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('894', '2023-07-04 14:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('895', '2023-07-04 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('896', '2023-07-04 14:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('903', '2023-07-04 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('904', '2023-07-04 14:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('905', '2023-07-04 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('906', '2023-07-04 14:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('909', '2023-07-04 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('910', '2023-07-04 14:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('913', '2023-09-27 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('914', '2023-09-27 14:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('919', '2023-09-27 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('920', '2023-09-27 14:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('921', '2023-09-27 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('922', '2023-09-27 14:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('923', '2023-09-27 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('924', '2023-09-27 14:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('925', '2023-09-27 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('926', '2023-09-27 14:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('927', '2023-09-27 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('928', '2023-09-27 14:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('929', '2023-09-27 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('930', '2023-09-27 14:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('931', '2023-09-27 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('932', '2023-09-27 14:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('939', '2023-09-27 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('940', '2023-09-27 14:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('941', '2023-09-27 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('942', '2023-09-27 14:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('945', '2023-09-27 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('946', '2023-09-27 14:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('949', '2023-10-11 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('950', '2023-10-11 14:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('955', '2023-10-11 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('956', '2023-10-11 14:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('957', '2023-10-11 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('958', '2023-10-11 14:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('959', '2023-10-11 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('960', '2023-10-11 14:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('961', '2023-10-11 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('962', '2023-10-11 14:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('963', '2023-10-11 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('964', '2023-10-11 14:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('965', '2023-10-11 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('966', '2023-10-11 14:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('967', '2023-10-11 14:15:15:89', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('968', '2023-10-11 11:56:43:562', '122', '已签到', 'p', '福建省福州市马尾区');
INSERT INTO `sign` VALUES ('975', '2023-10-11 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('976', '2023-10-11 14:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('977', '2023-10-11 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('978', '2023-10-11 14:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('981', '2023-10-11 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('982', '2023-10-11 14:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('985', '2023-10-12 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('986', '2023-10-12 14:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('991', '2023-10-12 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('992', '2023-10-12 14:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('993', '2023-10-12 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('994', '2023-10-12 14:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('995', '2023-10-12 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('996', '2023-10-12 14:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('997', '2023-10-12 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('998', '2023-10-12 14:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('999', '2023-10-12 10:33:36:465', '121', '已签到', 'a', '福建省福州市马尾区');
INSERT INTO `sign` VALUES ('1000', '2023-10-12 14:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1001', '2023-10-12 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1002', '2023-10-12 14:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1003', '2023-10-12 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1004', '2023-10-12 14:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1011', '2023-10-12 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1012', '2023-10-12 14:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1013', '2023-10-12 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1014', '2023-10-12 14:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1017', '2023-10-12 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1018', '2023-10-12 14:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1021', '2024-05-14 11:06:46:79', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1022', '2024-05-14 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1027', '2024-05-14 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1028', '2024-05-14 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1029', '2024-05-14 11:09:11:899', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1030', '2024-05-14 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1031', '2024-05-14 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1032', '2024-05-14 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1033', '2024-05-14 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1034', '2024-05-14 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1035', '2024-05-14 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1036', '2024-05-14 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1037', '2024-05-14 11:45:27:128', '122', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1038', '2024-05-14 11:08:56:783', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1039', '2024-05-14 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1040', '2024-05-14 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1045', '2024-05-14 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1046', '2024-05-14 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1049', '2024-05-14 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1050', '2024-05-14 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1051', '2024-05-14 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1052', '2024-05-14 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1057', '2024-05-26 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1058', '2024-05-26 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1059', '2024-05-26 13:54:18:828', '122', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1060', '2024-05-26 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1061', '2024-05-26 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1062', '2024-05-26 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1067', '2024-05-26 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1068', '2024-05-26 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1069', '2024-05-26 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1070', '2024-05-26 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1071', '2024-05-26 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1072', '2024-05-26 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1073', '2024-05-26 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1074', '2024-05-26 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1075', '2024-05-26 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1076', '2024-05-26 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1081', '2024-05-26 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1082', '2024-05-26 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1085', '2024-05-26 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1086', '2024-05-26 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1087', '2024-05-26 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1088', '2024-05-26 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1089', '2024-11-19 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1090', '2024-11-19 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1091', '2024-11-19 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1092', '2024-11-19 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1093', '2024-11-19 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1094', '2024-11-19 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1095', '2024-11-19 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1096', '2024-11-19 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1097', '2024-11-19 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1098', '2024-11-19 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1099', '2024-11-19 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1100', '2024-11-19 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1101', '2024-11-19 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1102', '2024-11-19 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1103', '2024-11-19 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1104', '2024-11-19 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1109', '2024-11-19 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1110', '2024-11-19 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1111', '2024-11-19 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1112', '2024-11-19 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1113', '2024-11-19 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1114', '2024-11-19 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1115', '2024-12-23 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1116', '2024-12-23 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1117', '2024-12-23 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1118', '2024-12-23 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1119', '2024-12-23 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1120', '2024-12-23 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1121', '2024-12-23 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1122', '2024-12-23 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1123', '2024-12-23 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1124', '2024-12-23 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1125', '2024-12-23 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1126', '2024-12-23 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1127', '2024-12-23 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1128', '2024-12-23 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1129', '2024-12-23 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1130', '2024-12-23 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1135', '2024-12-23 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1136', '2024-12-23 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1137', '2024-12-23 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1138', '2024-12-23 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1139', '2024-12-23 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1140', '2024-12-23 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1141', '2025-06-11 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1142', '2025-06-11 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1143', '2025-06-11 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1144', '2025-06-11 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1145', '2025-06-11 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1146', '2025-06-11 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1147', '2025-06-11 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1148', '2025-06-11 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1149', '2025-06-11 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1150', '2025-06-11 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1151', '2025-06-11 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1152', '2025-06-11 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1153', '2025-06-11 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1154', '2025-06-11 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1155', '2025-06-11 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1156', '2025-06-11 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1161', '2025-06-11 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1162', '2025-06-11 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1163', '2025-06-11 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1164', '2025-06-11 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1165', '2025-06-11 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1166', '2025-06-11 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1167', '2025-06-12 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1168', '2025-06-12 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1169', '2025-06-12 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1170', '2025-06-12 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1171', '2025-06-12 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1172', '2025-06-12 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1173', '2025-06-12 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1174', '2025-06-12 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1175', '2025-06-12 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1176', '2025-06-12 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1177', '2025-06-12 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1178', '2025-06-12 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1179', '2025-06-12 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1180', '2025-06-12 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1181', '2025-06-12 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1182', '2025-06-12 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1187', '2025-06-12 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1188', '2025-06-12 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1189', '2025-06-12 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1190', '2025-06-12 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1191', '2025-06-12 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1192', '2025-06-12 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1193', '2025-06-15 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1194', '2025-06-15 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1195', '2025-06-15 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1196', '2025-06-15 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1197', '2025-06-15 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1198', '2025-06-15 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1199', '2025-06-15 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1200', '2025-06-15 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1201', '2025-06-15 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1202', '2025-06-15 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1203', '2025-06-15 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1204', '2025-06-15 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1205', '2025-06-15 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1206', '2025-06-15 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1207', '2025-06-15 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1208', '2025-06-15 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1211', '2025-06-15 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1212', '2025-06-15 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1213', '2025-06-15 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1214', '2025-06-15 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1215', '2025-06-15 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1216', '2025-06-15 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1219', '2025-06-17 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1220', '2025-06-17 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1221', '2025-06-17 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1222', '2025-06-17 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1223', '2025-06-17 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1224', '2025-06-17 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1225', '2025-06-17 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1226', '2025-06-17 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1227', '2025-06-17 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1228', '2025-06-17 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1229', '2025-06-17 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1230', '2025-06-17 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1231', '2025-06-17 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1232', '2025-06-17 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1233', '2025-06-17 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1234', '2025-06-17 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1237', '2025-06-17 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1238', '2025-06-17 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1239', '2025-06-17 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1240', '2025-06-17 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1241', '2025-06-17 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1242', '2025-06-17 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1245', '2025-06-20 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1246', '2025-06-20 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1247', '2025-06-20 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1248', '2025-06-20 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1249', '2025-06-20 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1250', '2025-06-20 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1251', '2025-06-20 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1252', '2025-06-20 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1253', '2025-06-20 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1254', '2025-06-20 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1255', '2025-06-20 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1256', '2025-06-20 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1257', '2025-06-20 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1258', '2025-06-20 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1259', '2025-06-20 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1260', '2025-06-20 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1261', '2025-06-20 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1262', '2025-06-20 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1263', '2025-06-20 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1264', '2025-06-20 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1265', '2025-06-20 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1266', '2025-06-20 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1269', '2025-06-21 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1270', '2025-06-21 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1271', '2025-06-21 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1272', '2025-06-21 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1273', '2025-06-21 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1274', '2025-06-21 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1275', '2025-06-21 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1276', '2025-06-21 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1277', '2025-06-21 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1278', '2025-06-21 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1279', '2025-07-03 15:57:50:44', '123', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1280', '2025-07-03 15:57:40:101', '123', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1281', '2025-06-21 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1282', '2025-06-21 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1283', '2025-06-21 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1284', '2025-06-21 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1285', '2025-06-21 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1286', '2025-06-21 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1287', '2025-06-21 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1288', '2025-06-21 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1289', '2025-06-21 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1290', '2025-06-21 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1293', '2025-07-01 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1294', '2025-07-01 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1295', '2025-07-01 08:30:00:00', '136', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1296', '2025-07-01 17:30:00:00', '136', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1297', '2025-07-01 08:30:00:00', '137', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1298', '2025-07-01 17:30:00:00', '137', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1299', '2025-07-01 08:30:00:00', '121', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1300', '2025-07-01 17:30:00:00', '121', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1301', '2025-07-01 08:30:00:00', '122', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1302', '2025-07-01 17:30:00:00', '122', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1303', '2025-07-01 08:30:00:00', '123', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1304', '2025-07-01 17:30:00:00', '123', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1305', '2025-07-01 08:30:00:00', '127', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1306', '2025-07-01 17:30:00:00', '127', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1307', '2025-07-01 08:30:00:00', '129', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1308', '2025-07-01 17:30:00:00', '129', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1309', '2025-07-01 08:30:00:00', '133', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1310', '2025-07-01 17:30:00:00', '133', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1311', '2025-07-01 08:30:00:00', '128', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1312', '2025-07-01 17:30:00:00', '128', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1313', '2025-07-01 08:30:00:00', '134', '已签到', 'a', null);
INSERT INTO `sign` VALUES ('1314', '2025-07-01 17:30:00:00', '134', '已签到', 'p', null);
INSERT INTO `sign` VALUES ('1317', '2025-07-06 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1318', '2025-07-06 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1319', '2025-07-06 08:30:00:00', '145', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1320', '2025-07-06 17:30:00:00', '145', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1321', '2025-07-06 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1322', '2025-07-06 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1323', '2025-07-06 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1324', '2025-07-06 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1325', '2025-07-06 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1326', '2025-07-06 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1327', '2025-07-06 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1328', '2025-07-06 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1329', '2025-07-06 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1330', '2025-07-06 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1331', '2025-07-06 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1332', '2025-07-06 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1333', '2025-07-06 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1334', '2025-07-06 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1335', '2025-07-06 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1336', '2025-07-06 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1337', '2025-07-06 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1338', '2025-07-06 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1339', '2025-07-06 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1340', '2025-07-06 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1341', '2025-07-06 08:30:00:00', '146', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1342', '2025-07-06 17:30:00:00', '146', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1343', '2025-07-06 08:30:00:00', '147', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1344', '2025-07-06 17:30:00:00', '147', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1345', '2025-07-07 08:30:00:00', '135', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1346', '2025-07-07 17:30:00:00', '135', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1347', '2025-07-07 08:30:00:00', '145', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1348', '2025-07-07 17:30:00:00', '145', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1349', '2025-07-07 08:30:00:00', '136', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1350', '2025-07-07 17:30:00:00', '136', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1351', '2025-07-07 08:30:00:00', '137', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1352', '2025-07-07 17:30:00:00', '137', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1353', '2025-07-07 08:30:00:00', '121', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1354', '2025-07-07 17:30:00:00', '121', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1355', '2025-07-07 08:30:00:00', '122', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1356', '2025-07-07 17:30:00:00', '122', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1357', '2025-07-07 08:30:00:00', '123', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1358', '2025-07-07 17:30:00:00', '123', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1359', '2025-07-07 08:30:00:00', '127', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1360', '2025-07-07 17:30:00:00', '127', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1361', '2025-07-07 08:30:00:00', '129', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1362', '2025-07-07 17:30:00:00', '129', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1363', '2025-07-07 08:30:00:00', '133', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1364', '2025-07-07 17:30:00:00', '133', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1365', '2025-07-07 08:30:00:00', '134', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1366', '2025-07-07 17:30:00:00', '134', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1367', '2025-07-07 08:30:00:00', '128', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1368', '2025-07-07 17:30:00:00', '128', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1369', '2025-07-07 08:30:00:00', '146', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1370', '2025-07-07 17:30:00:00', '146', '未签到', 'p', null);
INSERT INTO `sign` VALUES ('1371', '2025-07-07 08:30:00:00', '147', '未签到', 'a', null);
INSERT INTO `sign` VALUES ('1372', '2025-07-07 17:30:00:00', '147', '未签到', 'p', null);
