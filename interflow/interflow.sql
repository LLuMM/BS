/*
Navicat MySQL Data Transfer

Source Server         : LM
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : interflow

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2018-05-15 13:26:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `aid` varchar(50) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `supportCnt` int(11) DEFAULT '0',
  `opposeCnt` int(11) DEFAULT '0',
  `answerTo` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `uid` varchar(50) DEFAULT NULL,
  `accept` int(11) NOT NULL DEFAULT '0' COMMENT '是否采纳',
  `praise` int(11) NOT NULL DEFAULT '0',
  `username` varchar(20) DEFAULT NULL,
  `qid` varchar(50) DEFAULT NULL,
  `toid` varchar(50) DEFAULT NULL,
  `questiontitle` varchar(50) DEFAULT NULL,
  `userface` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for `board`
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `bid` varchar(50) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of board
-- ----------------------------

-- ----------------------------
-- Table structure for `friends`
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `id` varchar(50) NOT NULL,
  `userid` varchar(50) DEFAULT NULL,
  `friendid` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friends
-- ----------------------------

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` varchar(50) NOT NULL,
  `time` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `href` varchar(1000) DEFAULT NULL,
  `fromid` varchar(50) NOT NULL,
  `toid` varchar(50) DEFAULT NULL,
  `fromstatus` int(4) NOT NULL DEFAULT '0' COMMENT '来源方状态',
  `tostatus` int(4) NOT NULL DEFAULT '0' COMMENT '到达方状态',
  `type` int(11) DEFAULT '0' COMMENT '类型',
  `fromname` varchar(50) DEFAULT NULL COMMENT '来源方名称',
  `toname` varchar(50) DEFAULT NULL COMMENT '到达方名字',
  `readstatus` int(4) NOT NULL DEFAULT '0',
  `title` varchar(100) DEFAULT NULL,
  `fid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `nid` varchar(50) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `n_id` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `url` varchar(255) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `readnum` int(11) DEFAULT '0',
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` varchar(50) NOT NULL,
  `content` text,
  `time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('5c5974c5-cc65-4958-8b93-356bae5bd03b', '本系统在更新状态，请见谅！', '2018-05-06 15:42:08');
INSERT INTO `notice` VALUES ('d2cfbab8-c5ff-4c4b-b7b6-9a878306e407', '欢迎大家来本网站寻找好基友<img src=\"http://localhost:8080/resources/kindeditor/plugins/emoticons/images/42.gif\" alt=\"\" border=\"0\" /><img src=\"http://localhost:8080/resources/kindeditor/plugins/emoticons/images/42.gif\" alt=\"\" border=\"0\" /><img src=\"http://localhost:8080/resources/kindeditor/plugins/emoticons/images/44.gif\" alt=\"\" border=\"0\" /><img src=\"http://localhost:8080/resources/kindeditor/plugins/emoticons/images/82.gif\" alt=\"\" border=\"0\" /><br />', '2018-05-14 10:29:01');

-- ----------------------------
-- Table structure for `nsnews`
-- ----------------------------
DROP TABLE IF EXISTS `nsnews`;
CREATE TABLE `nsnews` (
  `nsid` varchar(50) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` mediumtext,
  `pic` varchar(50) DEFAULT NULL,
  `date` varchar(0) DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `nid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`nsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nsnews
-- ----------------------------

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `title` varchar(200) DEFAULT NULL,
  `Id` varchar(50) NOT NULL,
  `Content` varchar(10000) DEFAULT NULL,
  `SupportCnt` int(11) DEFAULT '0',
  `OpposeCnt` int(11) DEFAULT '0',
  `CommentTo` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `UserId` varchar(50) DEFAULT NULL,
  `hits` int(11) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `stick` int(11) NOT NULL DEFAULT '0' COMMENT '置顶',
  `comment` int(11) NOT NULL DEFAULT '0' COMMENT '回答数',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '加精',
  `accept` varchar(50) DEFAULT NULL COMMENT '采纳',
  `fid` varchar(50) DEFAULT NULL,
  `uname` varchar(50) DEFAULT NULL,
  `frompic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------

-- ----------------------------
-- Table structure for `sysconf`
-- ----------------------------
DROP TABLE IF EXISTS `sysconf`;
CREATE TABLE `sysconf` (
  `id` varchar(50) NOT NULL,
  `value` varchar(200) NOT NULL,
  `key` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysconf
-- ----------------------------
INSERT INTO `sysconf` VALUES ('8223baeb-a7e9-4932-a64b-d709300473e2', '2.0', 'version');
INSERT INTO `sysconf` VALUES ('c0b07303-6e23-4f89-9570-d6aef0d7b92f', '4305', 'download');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `uid` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `post` int(11) DEFAULT NULL,
  `sign` varchar(20) DEFAULT NULL,
  `face` varchar(255) DEFAULT NULL,
  `joindate` varchar(50) DEFAULT NULL,
  `lastlogin` varchar(50) DEFAULT NULL,
  `isbest` int(11) DEFAULT NULL,
  `userfav` varchar(255) DEFAULT NULL,
  `userinfo` varchar(255) DEFAULT NULL,
  `userhidden` int(11) DEFAULT NULL,
  `userviews` int(11) DEFAULT NULL,
  `privilege` int(11) DEFAULT NULL,
  `visits` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `phone` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------

-- ----------------------------
-- Table structure for `updatelog`
-- ----------------------------
DROP TABLE IF EXISTS `updatelog`;
CREATE TABLE `updatelog` (
  `id` varchar(50) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `version` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of updatelog
-- ----------------------------
