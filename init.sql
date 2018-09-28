#CREATE DATABASE redmine CHARACTER SET utf8;

drop database if exists test;
create database test;

DROP TABLE IF EXISTS `account `;
CREATE TABLE `account` (
  `user_id` varchar(127) NOT NULL DEFAULT '',
  `user_name` varchar(127) NOT NULL DEFAULT '',
  `password` varchar(127) NOT NULL DEFAULT '',
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  KEY `index_user_id` (`user_id`) KEY_BLOCK_SIZE=10
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
insert into account (user_id, user_name, password, gmt_created, gmt_modified, is_deleted)value('100000',"admin", "123456", NOW(), NULL, '0');
insert into account (user_id, user_name, password, gmt_created, gmt_modified, is_deleted)value('100001',"test", "123456", NOW(), NULL, '0');
insert into account (user_id, user_name, password, gmt_created, gmt_modified, is_deleted)value('100002',"shsun", "123456", NOW(), NULL, '0');
insert into account (user_id, user_name, password, gmt_created, gmt_modified, is_deleted)value('100003',"syn", "123456", NOW(), NULL, '0');

DROP TABLE IF EXISTS `moment `;
CREATE TABLE `moment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(127) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100000", "my content is good1", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100000", "my content is good2", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100000", "my content is good3", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100000", "my content is good4", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100000", "my content is good5", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100000", "my content is good6", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100000", "my content is good7", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100000", "my content is good8", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100000", "my content is good9", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100000", "my content is good10中文", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100001", "my content is good1", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100001", "my content is good2", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100001", "my content is good3", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100001", "my content is good4", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100001", "my content is good5", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100001", "my content is good6", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100001", "my content is good7", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100001", "my content is good8", NOW(), NULL, '0');
insert into moment (user_id, content, gmt_created, gmt_modified, is_deleted)value("100001", "my content is good9中文", NOW(), NULL, '0');
select * from moment;



drop database if exists test2;
create database test2;

DROP TABLE IF EXISTS `comment `;
CREATE TABLE `comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `moment_id` bigint(20) unsigned NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_moment_id` (`moment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(10, "2my content is good1", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(10, "2my content is good2", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(10, "2my content is good3", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(10, "2my content is good4", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(10, "2my content is good5", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(10, "2my content is good6", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(10, "2my content is good7", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(10, "2my content is good8", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(10, "2my content is good9", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(10, "2my content is good10中文", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(11, "2my content is good1", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(11, "2my content is good2", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(11, "2my content is good3", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(11, "2my content is good4", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(11, "2my content is good5", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(11, "2my content is good6", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(11, "2my content is good7", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(11, "2my content is good8", NOW(), NULL, '0');
insert into comment (moment_id, content, gmt_created, gmt_modified, is_deleted)value(11, "2my content is good9中文", NOW(), NULL, '0');
select * from comment;

