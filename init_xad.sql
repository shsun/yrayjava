/**
 * create db instance
 */
DROP DATABASE IF EXISTS xad;

CREATE DATABASE xad;

USE xad;

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
