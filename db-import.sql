/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : forum1

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 28/12/2023 17:06:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` int NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `salt` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `isDelete` int NULL DEFAULT 0,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'hxl', '111', NULL, 0);

-- ----------------------------
-- Table structure for fopost
-- ----------------------------
DROP TABLE IF EXISTS `fopost`;
CREATE TABLE `fopost`  (
  `fpostID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mpostID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userID` int NULL DEFAULT NULL,
  `fpostTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `fpostIP` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `fpostContent` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `likeNum` int NULL DEFAULT 0,
  `ffloor` int NOT NULL DEFAULT 0,
  `refloor` int NOT NULL DEFAULT 0,
  `isDelete` int NULL DEFAULT 0,
  PRIMARY KEY (`fpostID`) USING BTREE,
  INDEX `FK_foPost_mpostID`(`mpostID` ASC) USING BTREE,
  INDEX `FK_foPost_userID`(`userID` ASC) USING BTREE,
  CONSTRAINT `FK_foPost_mpostID` FOREIGN KEY (`mpostID`) REFERENCES `mainpost` (`mpostID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_foPost_userID` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fopost
-- ----------------------------
INSERT INTO `fopost` VALUES ('f0', 'm0', 1, '2023-12-09 20:07:08', NULL, '标题党，水经验是吧', 0, 1, 0, 0);
INSERT INTO `fopost` VALUES ('f1', 'm1', 1, '2023-12-09 20:22:50', NULL, '理论上不难，其实你可以考虑一下这个论坛的管理组', 0, 1, 0, 0);
INSERT INTO `fopost` VALUES ('f10', 'm6', 1, '2023-12-12 17:45:54', NULL, '是的', 0, 2, 1, 0);
INSERT INTO `fopost` VALUES ('f11', 'm7', 2, '2023-12-12 19:49:02', NULL, '什么玩意？？', 0, 1, 0, 0);
INSERT INTO `fopost` VALUES ('f12', 'm5', 2, '2023-12-12 19:51:12', NULL, '首先你忘了货运，货物列车肯定还是机辆模式，毕竟JR的货物动车组也只有M250这一款。\n至于客运，这个完全有可能，看看隔壁，不过锅贴的车型是什么样这个真不好说呀，拭目以待吧', 0, 2, 1, 0);
INSERT INTO `fopost` VALUES ('f13', 'm5', 2, '2023-12-12 19:52:01', NULL, '至于CJ5，能不能量产鬼知道呢？', 0, 3, 0, 0);
INSERT INTO `fopost` VALUES ('f14', 'm0', 1, '2023-12-13 21:15:27', NULL, '4444444', 0, 4, 3, 0);
INSERT INTO `fopost` VALUES ('f2', 'm1', 1, '2023-12-09 20:25:04', NULL, '话说有人见过论坛管理组真人吗？长得怎么样？（无恶意）', 0, 2, 1, 0);
INSERT INTO `fopost` VALUES ('f3', 'm0', 1, '2023-12-11 17:12:20', NULL, '11111', 0, 2, 1, 0);
INSERT INTO `fopost` VALUES ('f4', 'm0', 1, '2023-12-11 17:15:24', NULL, '吃瓜', 0, 3, 0, 0);
INSERT INTO `fopost` VALUES ('f5', 'm1', 1, '2023-12-11 17:22:32', NULL, '见过，都挺好看的', 0, 3, 2, 0);
INSERT INTO `fopost` VALUES ('f6', 'm1', 1, '2023-12-11 17:23:00', NULL, '蹲一个回复', 0, 4, 0, 0);
INSERT INTO `fopost` VALUES ('f7', 'm1', 1, '2023-12-11 17:23:34', NULL, 'xd你很勇哦', 0, 5, 1, 0);
INSERT INTO `fopost` VALUES ('f8', 'm5', 1, '2023-12-12 17:44:35', NULL, '我在想国内会不会有气动车。。。', 0, 1, 0, 0);
INSERT INTO `fopost` VALUES ('f9', 'm6', 1, '2023-12-12 17:45:43', NULL, '基于lineage？', 0, 1, 0, 0);

-- ----------------------------
-- Table structure for mainpost
-- ----------------------------
DROP TABLE IF EXISTS `mainpost`;
CREATE TABLE `mainpost`  (
  `mpostID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `topicID` int NULL DEFAULT NULL,
  `userID` int NULL DEFAULT NULL,
  `mpostTitle` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `mpostTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `lastFoTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `likeNum` int NULL DEFAULT 0,
  `mpostIP` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `mpostContent` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `floorNum` int NULL DEFAULT 0,
  `isDelete` int NULL DEFAULT 0,
  PRIMARY KEY (`mpostID`) USING BTREE,
  INDEX `FK_mainPost_topicID`(`topicID` ASC) USING BTREE,
  INDEX `FK_mainPost_userID`(`userID` ASC) USING BTREE,
  CONSTRAINT `FK_mainPost_topicID` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_mainPost_userID` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mainpost
-- ----------------------------
INSERT INTO `mainpost` VALUES ('m0', 1, 1, '震惊！黄欣灵居然不会用session？', '2023-12-03 21:24:28', '2023-12-13 21:15:27', 4, NULL, '原来是她自己实现了token机制，避免了跨域的复杂设置', 4, 0);
INSERT INTO `mainpost` VALUES ('m1', 1, 1, '请教一个问题：在崴找对象很难吗？', '2023-12-04 15:50:26', '2023-12-11 17:23:35', 0, NULL, 'RT，求助一下论坛水神们', 5, 0);
INSERT INTO `mainpost` VALUES ('m2', 11, 1, '桃威换桶已成定局，威段AD钙奶已运抵济南动集所', '2023-12-06 23:53:10', '2023-12-06 23:53:10', 0, NULL, '威段奶桶已运抵济南动集所，用于替换威海到武汉，济南，菏泽的普速，应该是走青荣城际，桃威日后只办理货运<br>此前上述车次刚刚升空，没想到又来更彻底的，明年一月调图应该就会落实，且行且珍惜吧姐妹们！', 0, 0);
INSERT INTO `mainpost` VALUES ('m3', 1, 1, '为什么不用内连接而是手动循环？搞不懂了。。。', '2023-12-08 11:24:58', '2023-12-08 11:24:58', 0, NULL, '@黄欣灵 大佬能详细解释一下吗？', 0, 0);
INSERT INTO `mainpost` VALUES ('m4', 1, 1, '有人线下见过管理组吗？', '2023-12-09 20:26:11', '2023-12-09 20:26:11', 0, NULL, '纯属好奇', 0, 0);
INSERT INTO `mainpost` VALUES ('m5', 11, 1, '未来到底会不会全面动车组化？', '2023-12-12 17:42:48', '2023-12-12 19:52:02', 2, NULL, '如果会，那么CJ5这种车型会不会大行其道？', 3, 0);
INSERT INTO `mainpost` VALUES ('m6', 3, 1, '推荐一个ROM', '2023-12-12 17:44:59', '2023-12-12 17:45:54', 0, NULL, 'blissos', 2, 0);
INSERT INTO `mainpost` VALUES ('m7', 1, 1, '打卡打卡', '2023-12-12 17:47:29', '2023-12-12 19:49:03', 0, NULL, '114514', 1, 0);
INSERT INTO `mainpost` VALUES ('m8', 5, 1, '沙发', '2023-12-13 21:15:50', '2023-12-13 21:15:50', 0, NULL, '沙发', 0, 0);

-- ----------------------------
-- Table structure for postimg
-- ----------------------------
DROP TABLE IF EXISTS `postimg`;
CREATE TABLE `postimg`  (
  `imgID` int NOT NULL AUTO_INCREMENT,
  `postID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` int NULL DEFAULT 0,
  `isDelete` int NULL DEFAULT 0,
  PRIMARY KEY (`imgID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of postimg
-- ----------------------------
INSERT INTO `postimg` VALUES (1, 'm0', '/static/upload/4e25aee8-3c75-42fd-83dd-d6958a0862e6.png', 0, 0);
INSERT INTO `postimg` VALUES (2, 'm0', '/static/upload/d2434b75-093d-42ba-b78e-f12ac1f4b1a5.png', 0, 0);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `topicID` int NOT NULL AUTO_INCREMENT,
  `topicName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `topicIntro` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '该版面暂无介绍',
  `isDelete` int NULL DEFAULT 0,
  PRIMARY KEY (`topicID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (1, '崴女生', '玛珈山盛产什么？答：好看的妹子。——山威交友脱单专用板块', 0);
INSERT INTO `topic` VALUES (2, 'Android搞机', '讨论交流玩机技术，分享使用经验和宝藏软件', 0);
INSERT INTO `topic` VALUES (3, 'ROM专区', '分享好用的第三方ROM，官方包，救砖包', 0);
INSERT INTO `topic` VALUES (4, 'Magisk&xposed模块交流', '切磋交流模块开发技术，分享自己的作品', 0);
INSERT INTO `topic` VALUES (5, '崴选课', '传播选课经验，安利吐槽课程和老师', 0);
INSERT INTO `topic` VALUES (6, '华为鸿蒙', 'HarmonyOS专区，针对鸿蒙和EMUI的免root玩机教程', 0);
INSERT INTO `topic` VALUES (7, 'Java&微服务', '力争成为最有影响力的中文互联网开发者社区', 0);
INSERT INTO `topic` VALUES (8, '谷歌play专区', '从应用分发到日常服务，全世界最具影响力的Android解决方案', 0);
INSERT INTO `topic` VALUES (9, 'MIUI', '去云控，改调度，冻应用，增续航，一切如行云流水', 0);
INSERT INTO `topic` VALUES (10, '期末&四六级不挂', '学习资料，历年真题，经验分享，撩学姐...考试月，你想要的尽在这里', 0);
INSERT INTO `topic` VALUES (11, '车迷在崴', '不忘初心，牢记使命，交通强国，铁路先行', 0);
INSERT INTO `topic` VALUES (12, '盖乐世社区崴分区', 'We Are The 1%！全崴三星玩家集会地', 0);
INSERT INTO `topic` VALUES (13, '崴学时互助', '发布那些可以加志愿学时的活动，分享加分经验', 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `userID` int NOT NULL AUTO_INCREMENT,
  `PASSWORD` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `salt` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `gender` int NULL DEFAULT NULL,
  `isDelete` int NULL DEFAULT 0,
  `profile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userID`) USING BTREE,
  UNIQUE INDEX `userName`(`userName` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'EC7819B9D17652FF4D9E395A7CD1ECA9', 'CDA17F95-FE69-4D04-8D4F-D95D7B83B74B', 'hxl', '1@1', 0, 0, '');
INSERT INTO `users` VALUES (2, '8334B6CCB6593ECC2F88582DB3CF27A3', '2376776D-CCF0-413B-ADFD-FD22446AC9E5', 'lyh', '1@1', 0, 0, NULL);

-- ----------------------------
-- View structure for fofo_num
-- ----------------------------
DROP VIEW IF EXISTS `fofo_num`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `fofo_num` AS select `f1`.`fpostContent` AS `fpostContent`,`f1`.`fpostTime` AS `fpostTime`,`f1`.`userID` AS `userID`,(select count(`f2`.`fpostID`) from `fopost` `f2` where (`f2`.`mpostID` = `f1`.`mpostID`)) AS `total_fo` from `fopost` `f1`;

-- ----------------------------
-- View structure for fopost_view
-- ----------------------------
DROP VIEW IF EXISTS `fopost_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `fopost_view` AS select `mainpost`.`mpostTitle` AS `mpostTitle`,`mainpost`.`mpostContent` AS `mpostContent`,`mainpost`.`mpostTime` AS `mpostTime`,`fopost`.`fpostContent` AS `fpostContent`,`fopost`.`fpostTime` AS `fpostTime` from (`mainpost` join `fopost` on((`mainpost`.`mpostID` = `fopost`.`mpostID`)));

-- ----------------------------
-- View structure for mainfo_num
-- ----------------------------
DROP VIEW IF EXISTS `mainfo_num`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `mainfo_num` AS select `mainpost`.`mpostTitle` AS `mpostTitle`,`mainpost`.`mpostContent` AS `mpostContent`,`mainpost`.`mpostTime` AS `mpostTime`,`mainpost`.`userID` AS `userID`,count(`fopost`.`fpostID`) AS `total_fo` from (`mainpost` join `fopost` on((`mainpost`.`mpostID` = `fopost`.`mpostID`))) group by `mainpost`.`mpostTitle`,`mainpost`.`mpostContent`,`mainpost`.`mpostTime`,`mainpost`.`userID`;

-- ----------------------------
-- View structure for post_view
-- ----------------------------
DROP VIEW IF EXISTS `post_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `post_view` AS select `topic`.`topicID` AS `topicID`,`topic`.`topicName` AS `topicName`,`mainpost`.`mpostTitle` AS `mpostTitle`,`mainpost`.`mpostContent` AS `mpostContent`,`mainpost`.`mpostTime` AS `mpostTime`,`mainpost`.`userID` AS `userID` from (`topic` join `mainpost` on((`topic`.`topicID` = `mainpost`.`topicID`)));

-- ----------------------------
-- View structure for user_postnum
-- ----------------------------
DROP VIEW IF EXISTS `user_postnum`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `user_postnum` AS select `users`.`userID` AS `userID`,`users`.`userName` AS `userName`,count(`mainpost`.`mpostID`) AS `total` from (`users` join `mainpost` on((`users`.`userID` = `mainpost`.`userID`))) group by `users`.`userID`,`users`.`userName`;

-- ----------------------------
-- Function structure for fpost_formID
-- ----------------------------
DROP FUNCTION IF EXISTS `fpost_formID`;
delimiter ;;
CREATE FUNCTION `fpost_formID`()
 RETURNS varchar(32) CHARSET utf8mb4
  DETERMINISTIC
BEGIN
    DECLARE prefix CHAR(1);
    DECLARE LAST_ID VARCHAR(32);
    DECLARE num INT;
    DECLARE MID VARCHAR(32);

    SET prefix = 'f';

    SELECT fpostID INTO LAST_ID
    FROM foPost
    ORDER BY fpostTime DESC
    LIMIT 1;

    IF LAST_ID IS NOT NULL THEN
        SET num = CAST(SUBSTRING(LAST_ID, 2) AS SIGNED) + 1;
    ELSE
        SET num = 0;
    END IF;

    SET MID = CONCAT(prefix, num);

    RETURN MID;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for mpost_formID
-- ----------------------------
DROP FUNCTION IF EXISTS `mpost_formID`;
delimiter ;;
CREATE FUNCTION `mpost_formID`()
 RETURNS varchar(32) CHARSET utf8mb4
  DETERMINISTIC
BEGIN
    DECLARE prefix CHAR(1);
    DECLARE LAST_ID VARCHAR(32);
    DECLARE num INT;
    DECLARE MID VARCHAR(32);

    SET prefix = 'm';

    SELECT mpostID INTO LAST_ID
    FROM mainPost
    ORDER BY mpostTime DESC
    LIMIT 1;

    IF LAST_ID IS NOT NULL THEN
        SET num = CAST(SUBSTRING(LAST_ID, 2) AS SIGNED) + 1;
    ELSE
        SET num = 0;
    END IF;

    SET MID = CONCAT(prefix, num);

    RETURN MID;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table fopost
-- ----------------------------
DROP TRIGGER IF EXISTS `set_fpostID_default`;
delimiter ;;
CREATE TRIGGER `set_fpostID_default` BEFORE INSERT ON `fopost` FOR EACH ROW BEGIN
    SET NEW.fpostID = fpost_formID();
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table mainpost
-- ----------------------------
DROP TRIGGER IF EXISTS `set_mpostID_default`;
delimiter ;;
CREATE TRIGGER `set_mpostID_default` BEFORE INSERT ON `mainpost` FOR EACH ROW BEGIN
    SET NEW.mpostID = mpost_formID();
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table postimg
-- ----------------------------
DROP TRIGGER IF EXISTS `opImg`;
delimiter ;;
CREATE TRIGGER `opImg` BEFORE INSERT ON `postimg` FOR EACH ROW BEGIN
    DECLARE idCount INT;

    SELECT COUNT(*) INTO idCount
    FROM (
        SELECT fpostID AS postID FROM foPost
        UNION ALL
        SELECT mpostID AS postID FROM mainPost
    ) AS posts
    WHERE posts.postID = NEW.postID;

    IF idCount = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid postID';
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
