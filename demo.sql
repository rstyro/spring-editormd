/*
Navicat MySQL Data Transfer

Source Server         : localhost_mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-18 17:42:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT '1',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '内容',
  `browse_num` int(11) DEFAULT '0' COMMENT '浏览数',
  `comment_num` int(11) DEFAULT '0' COMMENT '评论数',
  `praise_num` int(11) DEFAULT '0' COMMENT '点赞数',
  `isdel` int(11) DEFAULT '0' COMMENT '是否删除了',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
