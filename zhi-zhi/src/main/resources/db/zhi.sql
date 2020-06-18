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
INSERT INTO `question` VALUES ('2', '任岩的秘密', '市地税局看电视剧肯定会十数据库', '2020-6-13 12:44:20', '4', 'rock');
INSERT INTO `question` VALUES ('3', '什么是矩阵范数？', '矩阵范数（matrix norm）是数学中矩阵论、线性代数、泛函分析等领域中常见的基本概念，是将一定的矩阵空间建立为赋范向量空间时为矩阵装备的范数。应用中常将有限维赋范向量空间之间的映射以矩阵的形式表现，这时映射空间上装备的范数也可以通过矩阵范数的形式表达。那么具体是什么呢？', '2020-6-13 12:44:20', '2', 'zgxh');
INSERT INTO `question` VALUES ('4', 'Java 教程', 'Java 是由Sun Microsystems公司于1995年5月推出的高级程序设计语言。Java可运行于多个平台，如Windows, Mac OS，及其他多种UNIX版本的系统。本教程通过简单的实例将让大家更好的了解JAVA编程语言。', '2020-6-14 12:44:20', '2', 'zgxh');
INSERT INTO `question` VALUES ('5', 'HTML5 标准', '对于中文网页需要使用 <meta charset="utf-8"> 声明编码，否则会出现乱码。有些浏览器(如 360 浏览器)会设置 GBK 为默认编码，则你需要设置为 <meta charset="gbk">。', '2020-6-15 12:44:20', '1', 'yangefree');
INSERT INTO `question` VALUES ('6', 'HTML 标题', '标题（Heading）是通过 <h1> - <h6> 标签进行定义的。<h1> 定义最大的标题。 <h6> 定义最小的标题。', '2020-6-16 12:44:20', '3', 'yifudangguan');
INSERT INTO `question` VALUES ('7', 'CSS (Cascading Style Sheets)', '当特殊的样式需要应用到个别元素时，就可以使用内联样式。 使用内联样式的方法是在相关的标签中使用样式属性。样式属性可以包含任何 CSS 属性。以下实例显示出如何改变段落的颜色和左外边距。', '2020-6-16 12:44:20', '4', 'rock');
INSERT INTO `question` VALUES ('8', '查看 Python 版本', '我们可以在命令窗口(Windows 使用 win+R 调出 cmd 运行框)使用以下命令查看我们使用的 Python 版本：python -V。对于大多数程序语言，第一个入门编程代码便是"Hello World！"，以下代码为使用Python输出"Hello World！"', '2020-6-16 12:44:20', '1', 'yangefree');
INSERT INTO `question` VALUES ('9', '现在开始学习正则表达式！', '正则表达式(Regular Expression)是一种文本模式，包括普通字符（例如，a 到 z 之间的字母）和特殊字符（称为"元字符"）。正则表达式使用单个字符串来描述、匹配一系列匹配某个句法规则的字符串。正则表达式是繁琐的，但它是强大的，学会之后的应用会让你除了提高效率外，会给你带来绝对的成就感。只要认真阅读本教程，加上应用的时候进行一定的参考，掌握正则表达式不是问题。许多程序设计语言都支持利用正则表达式进行字符串操作。', '2020-6-16 12:44:20', '2', 'zgxh');
INSERT INTO `question` VALUES ('10', 'XML 实例', '通过实例进行学习！使用我们的编辑器，你可以编辑 XML 代码，然后点击测试按钮查看结果。', '2020-6-17 12:44:20', '3', 'yifudangguan');
INSERT INTO `question` VALUES ('11', '什么是数据库？', 'MySQL 是最流行的关系型数据库管理系统，在 WEB 应用方面 MySQL 是最好的 RDBMS(Relational Database Management System：关系数据库管理系统)应用软件之一。', '2020-6-17 12:44:20', '4', 'rock');
INSERT INTO `question` VALUES ('12', 'MySQL 事务', 'MySQL 事务主要用于处理操作量大，复杂度高的数据。比如说，在人员管理系统中，你删除一个人员，你既需要删除人员的基本资料，也要删除和该人员相关的信息，如信箱，文章等等，这样，这些数据库操作语句就构成一个事务！', '2020-6-17 12:44:20', '3', 'yifudangguan');
INSERT INTO `question` VALUES ('13', '事务的隔离性', '数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。事务隔离分为不同级别，包括读未提交（Read uncommitted）、读提交（read committed）、可重复读（repeatable read）和串行化（Serializable）。', '2020-6-18 12:44:20', '5', 'xiaoming');

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
INSERT INTO `answer` VALUES ('4', '在 MySQL 命令行的默认设置下，事务都是自动提交的，即执行 SQL 语句后就会马上执行 COMMIT 操作。因此要显式地开启一个事务务须使用命令 BEGIN 或 START TRANSACTION，或者执行命令 SET AUTOCOMMIT=0，用来禁止使用当前会话的自动提交。', '2020-6-18 12:56:10', '1', 'yangefree', '11');
INSERT INTO `answer` VALUES ('5', '这是来水贴的吧', '2020-6-13 12:56:10', '5', 'xiaoming', '2');
INSERT INTO `answer` VALUES ('6', 'javac 后面跟着的是java文件的文件名，例如 HelloWorld.java。 该命令用于将 java 源文件编译为 class 字节码文件，如： javac HelloWorld.java。', '2020-6-13 12:56:10', '5', 'xiaoming', '4');
INSERT INTO `answer` VALUES ('7', '最新版本的 Safari、Chrome、Firefox 以及 Opera 支持某些 HTML5 特性。Internet Explorer 9 将支持某些 HTML5 特性。', '2020-6-13 12:56:10', '2', 'zgxh122', '5');
INSERT INTO `answer` VALUES ('8', '如果您想找到其中的奥秘，只需要单击右键，然后选择"查看源文件"（IE）或"查看页面源代码"（Firefox），其他浏览器的做法也是类似的。这么做会打开一个包含页面 HTML 代码的窗口。', '2020-6-13 12:56:10', '4', 'rock', '6');
INSERT INTO `answer` VALUES ('9', '以前，你必须使用图像来实现这些效果。但是，通过使用 CSS3 渐变（gradients），你可以减少下载的时间和宽带的使用。此外，渐变效果的元素在放大时看起来效果更好，因为渐变（gradient）是由浏览器生成的。', '2020-6-13 12:56:10', '2', 'zgxh122', '7');
INSERT INTO `answer` VALUES ('10', '这个模式包含一个特殊的字符^，表示该模式只匹配那些以once开头的字符串。例如该模式与字符串"once upon a time"匹配，与"There once was a man from NewYork"不匹配。正如如^符号表示开头一样，$符号用来匹配那些以给定模式结尾的字符串。', '2020-6-13 12:56:10', '3', 'yifudangguan', '8');
INSERT INTO `answer` VALUES ('11', '这个模式包含一个特殊的字符^，表示该模式只匹配那些以once开头的字符串。例如该模式与字符串"once upon a time"匹配，与"There once was a man from NewYork"不匹配。正如如^符号表示开头一样，$符号用来匹配那些以给定模式结尾的字符串。', '2020-6-13 12:56:10', '3', 'yifudangguan', '9');

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
INSERT INTO `user` VALUES ('1', 'yangefree', '$2a$10$0KretwdqYF.qz.YajuksK.q8H7tzle36zuGQPIbGbxODjUkX4.QeK', '2020-6-13 12:00:00');
INSERT INTO `user` VALUES ('2', 'zgxh', '$2a$10$0KretwdqYF.qz.YajuksK.q8H7tzle36zuGQPIbGbxODjUkX4.QeK', '2020-6-13 12:00:00');
INSERT INTO `user` VALUES ('3', 'yifudangguan', '$2a$10$0KretwdqYF.qz.YajuksK.q8H7tzle36zuGQPIbGbxODjUkX4.QeK', '2020-6-13 12:00:00');
INSERT INTO `user` VALUES ('4', 'rock', '$2a$10$0KretwdqYF.qz.YajuksK.q8H7tzle36zuGQPIbGbxODjUkX4.QeK', '2020-6-17 1:00:00');
INSERT INTO `user` VALUES ('5', 'xiaoming', '$2a$10$0KretwdqYF.qz.YajuksK.q8H7tzle36zuGQPIbGbxODjUkX4.QeK', '2020-6-18 11:00:00');


SET FOREIGN_KEY_CHECKS=1;