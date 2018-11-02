/**
 * create db instance
 */
DROP DATABASE IF EXISTS xad;

CREATE DATABASE xad;

USE xad;


-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `department_key` varchar(20) NOT NULL COMMENT '部门编码',
  `department_value` varchar(40) NOT NULL COMMENT '部门名称',
  `department_level` varchar(20) DEFAULT NULL COMMENT '部门级别',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `parent_department_key` varchar(20) DEFAULT NULL COMMENT '上级部门编码',
  `create_time` varchar(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sys_department_department_key` (`department_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';




--------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_account` varchar(30) NOT NULL COMMENT '登录账号',
  `login_pass` varchar(65) NOT NULL COMMENT '登录密码',
  `user_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `user_head` varchar(30) DEFAULT NULL COMMENT '头像',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `user_email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `user_sex` int(11) DEFAULT NULL COMMENT '性别',
  `user_birthday` varchar(30) DEFAULT NULL COMMENT '生日',
  `register_time` varchar(30) NOT NULL COMMENT '注册时间',
  `department_key` varchar(20) DEFAULT NULL COMMENT '部门编码',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_sys_user_login_account` (`login_account`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';
-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'hzw2312', '63cbbfefc6a5f389ea64299134e989a9a378d1293cad8b5623331bf5d0e023a9', null, null, null, 'hzw2312@sina.com', null, null, '2017-01-18 14:39:23', null);
INSERT INTO `sys_user` VALUES ('3', 'hzw2312f', '63cbbfefc6a5f389ea64299134e989a9a378d1293cad8b5623331bf5d0e023a9', null, null, null, 'hzw23d12@sina.com', null, null, '2017-01-18 15:25:08', null);
INSERT INTO `sys_user` VALUES ('4', 'hhsykx', '63cbbfefc6a5f389ea64299134e989a9a378d1293cad8b5623331bf5d0e023a9', null, null, null, 'hhs2312@sina.com', null, null, '2017-01-18 15:25:47', null);




-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_key` varchar(30) DEFAULT NULL COMMENT '角色编码',
  `create_time` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `role_value` varchar(40) NOT NULL COMMENT '角色名称',
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_USER', null, null, '', null);
INSERT INTO `sys_role` VALUES ('2', 'ROLE_ADMIN', null, null, '', null);






/**
 * create table permission
 */
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `role_id` int(11) NOT NULL COMMENT '角色主键编号',
  `permissions` varchar(1000) DEFAULT NULL COMMENT '按钮权限',
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  CONSTRAINT `FK9q28ewrhntqeipl1t04kh1be7` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `fk_role_permission_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色按钮权限表';







-- ----------------------------
-- Table structure for sys_user_role 用户角色关联表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  `role_id` int(20) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `fk_sys_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `fk_sys_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色映射表';



-- ----------------------------
-- Table structure for sys_role_permission 角色权限关联表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(11) NOT NULL COMMENT '角色主键编号',
  `permissions` varchar(1000) DEFAULT NULL COMMENT '按钮权限',
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  CONSTRAINT `FK9q28ewrhntqeipl1t04kh1be7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `fk_sys_role_permission_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色按钮权限表';


-- --------












/**
 * create table user
 */
DROP TABLE IF EXISTS `user`;

CREATE TABLE user (
  `user_id` varchar(127) NOT NULL,
  `user_name` varchar(127) NOT NULL,
  `password` varchar(127) NOT NULL DEFAULT '123456',
  `role` enum('advertiser', 'agency', 'publisher', 'admin', 'unknown') DEFAULT 'unknown',
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `version` int(10) NOT NULL DEFAULT 1,
  PRIMARY KEY (`user_id`)
) ENGINE = InnoDB CHARSET = utf8mb4;
INSERT INTO user (user_id, user_name, password, role, gmt_created, gmt_modified, is_deleted) VALUES ('100000', "admin", "123456", 'admin', NOW(), NULL, '0');


/**
 * create table role
 */
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_key` varchar(30) DEFAULT NULL COMMENT '角色编码',
  `create_time` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `role_value` varchar(40) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
INSERT INTO `role` VALUES ('1', 'USER', null, null, '', null);
INSERT INTO `role` VALUES ('2', 'ADMIN', null, null, '', null);









/**
 * create table ad_campaign
 */
DROP TABLE IF EXISTS `ad_campaign`;

CREATE TABLE ad_campaign (
  `advertiser_id` varchar(127) NOT NULL,
  `campaign_id` varchar(127) NOT NULL,
  `campaign_name` varchar(127) NOT NULL,
  `status` ENUM('dsh', 'wdkssj', 'tfz', 'zt', 'ygq', 'yedb', 'bgdb', 'djdb', 'unknown') DEFAULT 'unknown',
  `budget` int(11) NOT NULL DEFAULT 1 COMMENT '当天预算',
  `cost` int(11) NOT NULL DEFAULT 0 COMMENT '已消耗金额',
  `price` float NOT NULL DEFAULT 0.5,
  `action` ENUM('LP', 'DL') NOT NULL DEFAULT 'LP',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `weight` int(11) NOT NULL DEFAULT 50,
  `finish_percent` int(11) NOT NULL DEFAULT 0,
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `version` int(10) NOT NULL DEFAULT 1,
  PRIMARY KEY (`campaign_id`),
  FOREIGN KEY (`advertiser_id`) REFERENCES user (`user_id`)
) ENGINE = InnoDB CHARSET = utf8mb4;

/**
 * create table ad_unit
 */
DROP TABLE IF EXISTS `ad_unit`;

CREATE TABLE ad_unit (
  `campaign_id` varchar(127) NOT NULL DEFAULT '',
  `unit_id` varchar(127) NOT NULL DEFAULT '',
  `unit_name` varchar(127) NOT NULL DEFAULT '',
  `title` varchar(127) NOT NULL DEFAULT '',
  `desc` varchar(127) NOT NULL DEFAULT '',
  `img_url` varchar(127) DEFAULT '',
  `img_url_2` varchar(127) DEFAULT '',
  `img_url_3` varchar(127) DEFAULT '',
  `img_url_4` varchar(127) DEFAULT '',
  `type` ENUM('datu', 'tuwen', 'zutu', 'wenzi', 'html') DEFAULT 'tuwen',
  `url` varchar(127) NOT NULL DEFAULT '',
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `version` int(10) NOT NULL DEFAULT 1,
  PRIMARY KEY (`unit_id`),
  FOREIGN KEY (`campaign_id`) REFERENCES ad_campaign (`campaign_id`)
) ENGINE = InnoDB CHARSET = utf8mb4;

/**
 * create table account
 */
DROP TABLE IF EXISTS `account`;

CREATE TABLE account (
  `id` int AUTO_INCREMENT,
  `advertiser_id` varchar(127) NOT NULL,
  `action` ENUM('deposit', 'refund') DEFAULT 'deposit' COMMENT '标明是存款还是退款',
  `receivalble_capital` int(11) NOT NULL DEFAULT 0 COMMENT '应收金额',
  `paidin_capital` int(11) NOT NULL DEFAULT 0 COMMENT '实收金额',
  `kickback_capital` int(11) NOT NULL DEFAULT 0 COMMENT '回扣金额',
  `donation_capital` int(11) NOT NULL DEFAULT 0 COMMENT '赠送金额',
  `donation_ratio` float NOT NULL DEFAULT 0 COMMENT '赠送比例',
  `comments` varchar(127) NOT NULL DEFAULT '' COMMENT '充钱原因',
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `version` int(10) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`advertiser_id`) REFERENCES user (`user_id`)
) ENGINE = InnoDB CHARSET = utf8mb4;


/**
 * create table account
 */
DROP TABLE IF EXISTS `ad_metric`;

CREATE TABLE ad_metric (
  `id` int AUTO_INCREMENT,
  `ngx_date` date NOT NULL,
  `ad_system` varchar(127) NOT NULL,
  `slot_id` varchar(127) NOT NULL DEFAULT '-999',
  `campaign_id` varchar(127) NOT NULL DEFAULT '-999',
  `unit_id` varchar(127) NOT NULL DEFAULT '-999',
  `event_type` varchar(127) NOT NULL,
  `num` int(11) NOT NULL DEFAULT 0,
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `version` int(10) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARSET = utf8mb4;
