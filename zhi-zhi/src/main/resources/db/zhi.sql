CREATE DATABASE `zhizhi` DEFAULT CHARACTER SET utf8;

USE `zhizhi`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `title` varchar(255) DEFAULT NULL,
                           `content` text,
                           `publish_time` datetime DEFAULT NULL,
                           `uid` int(11) DEFAULT NULL,
                           `username` varchar(64) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `uid` (`uid`),
                           CONSTRAINT `question_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '我是任岩你是谁？', '任岩，1998，河北省邯郸市人.\n 哈哈', '2020-6-13 12:36:20', '1', 'yangefree');
INSERT INTO `question` VALUES ('2', '任岩的秘密', '市地税局看电视剧肯定会十数据库', '2020-6-13 12:44:20', '1', 'yangefree');

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `content` text,
                                `publish_time` datetime DEFAULT NULL,
                                `uid` int(11) DEFAULT NULL,
                                `username` varchar(64) DEFAULT NULL,
                                `qid` int(11) DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `uid` (`uid`),
                                KEY `qid` (`qid`),
                                CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE,
                                CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`qid`) REFERENCES `question` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('1', '任岩爆照！', '2020-6-13 12:42:10', '2', 'zgxh122', '1');
INSERT INTO `answer` VALUES ('2', '你是谁啊，啊啊啊啊啊啊啊', '2020-6-13 12:55:10', '3', 'yifudangguan', '1');
INSERT INTO `answer` VALUES ('3', '这是来水贴的吧', '2020-6-13 12:56:10', '2', 'zgxh122', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(64) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `reg_time` datetime DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'yangefree', 'yan1998haha', '2020-6-13 12:00:00');
INSERT INTO `user` VALUES ('2', 'zgxh122', 'zgxhahaha', '2020-6-13 12:00:00');
INSERT INTO `user` VALUES ('3', 'yifudangguan', 'wanfumokai', '2020-6-13 12:00:00');

SET FOREIGN_KEY_CHECKS=1;