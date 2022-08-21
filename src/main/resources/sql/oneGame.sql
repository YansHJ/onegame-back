/*
 Navicat MySQL Data Transfer

 Source Server         : huawei
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : 124.70.5.108:3306
 Source Schema         : oneGame

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 21/08/2022 16:48:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for game_level
-- ----------------------------
DROP TABLE IF EXISTS `game_level`;
CREATE TABLE `game_level`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '可显示的序号',
  `next` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '下一关分支，关卡的唯一id集合串',
  `last` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '上一关分支，关卡的唯一id集合串',
  `type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '关卡性质(0小怪，1boss，2奖励)',
  `monster_ids` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '怪物ids',
  `card_num` int(6) NULL DEFAULT NULL COMMENT '开局初始化卡牌数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `describe` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `number`(`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '关卡' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of game_level
-- ----------------------------
INSERT INTO `game_level` VALUES ('004e66c48098987b', '0d07-4-1', NULL, '[\"3f43c3042a793b4d\",\"88890144724bc54c\"]', 2, '', NULL, '2022-08-06 12:13:31', '获得奖励');
INSERT INTO `game_level` VALUES ('0d074134b1d9c121', '0d07-1-1', '', '', 0, '10ac1904b1ca14b6', 5, '2022-08-06 12:13:31', '主线');
INSERT INTO `game_level` VALUES ('3f43c3042a793b4d', '0d07-3-1', NULL, '[\"983747145ce99158\"]', 0, '930b3924cdb9fca1', 5, '2022-08-06 12:13:31', '战斗');
INSERT INTO `game_level` VALUES ('3jjj3129mmlk1231', '0d07-7-1', NULL, '[\"7fc6d774357a9b3a\"]', 0, '3d5670340b2911eb', 6, '2022-08-09 10:18:15', '战斗');
INSERT INTO `game_level` VALUES ('7fc6d774357a9b3a', '0d07-6-1', NULL, '[\"c95d4584d7cb193b\"]', 2, '', NULL, '2022-08-08 10:21:25', '获得奖励');
INSERT INTO `game_level` VALUES ('88890144724bc54c', '0d07-3-2', NULL, '[\"983747145ce99158\"]', 0, '37a9fb8496389cf1', 5, '2022-08-06 12:13:31', '战斗');
INSERT INTO `game_level` VALUES ('983747145ce99158', '0d07-2-1', NULL, '[\"0d074134b1d9c121\"]', 2, '', NULL, '2022-08-06 12:13:31', '获得奖励');
INSERT INTO `game_level` VALUES ('c95d4584d7cb193b', '0d07-5-1', NULL, '[\"004e66c48098987b\"]', 1, 'cb2f6e54ef090fcf', 6, '2022-08-06 12:13:31', 'BOSS');

-- ----------------------------
-- Table structure for ys_cardpool_attack
-- ----------------------------
DROP TABLE IF EXISTS `ys_cardpool_attack`;
CREATE TABLE `ys_cardpool_attack`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '卡牌id',
  `identifier` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '唯一id',
  `type` int(11) NOT NULL DEFAULT 1 COMMENT '卡牌类型,1攻击，2血量，3盾，4特殊',
  `level` int(11) NOT NULL COMMENT '卡牌品级',
  `value` bigint(20) NOT NULL DEFAULT 0 COMMENT '伤害值',
  `card_value_type` int(11) NOT NULL COMMENT '伤害类型 1 数值伤害,2 特殊效果,3 一次性',
  `final_value` double(11, 0) NULL DEFAULT NULL COMMENT '加成后最终伤害',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '卡牌名',
  `probability` int(10) NULL DEFAULT NULL COMMENT '抽取概率',
  `del_flag` tinyint(2) NULL DEFAULT 0 COMMENT '0存在，1删除',
  `describe` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '简单描述',
  `price` int(20) NULL DEFAULT NULL COMMENT '购买费用',
  `consumes` int(20) NULL DEFAULT NULL COMMENT '消耗行动力',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `probability`(`probability`) USING BTREE,
  INDEX `identfiier`(`identifier`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '攻击类卡池' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ys_cardpool_attack
-- ----------------------------
INSERT INTO `ys_cardpool_attack` VALUES (1, '9cff6044aaa95fa3', 1, 1, 2, 1, NULL, '2022-07-31 13:40:50', '斩击', 50, 0, '朴实无华的攻击', 2, 1);
INSERT INTO `ys_cardpool_attack` VALUES (2, '1d7ad934546847df', 1, 1, 4, 1, NULL, '2022-07-31 13:42:46', '鬼火', 40, 0, '非自然力量的火焰，灼烧灵魂', 3, 1);
INSERT INTO `ys_cardpool_attack` VALUES (3, '0206f554f9f8729e', 1, 1, 8, 1, NULL, '2022-07-31 14:41:47', '居合斩', 20, 0, '看得见我的刀刃吗', 4, 1);
INSERT INTO `ys_cardpool_attack` VALUES (4, '8005fb64830b5526', 1, 1, 14, 1, NULL, '2022-08-02 15:50:03', '炼狱', 15, 1, '爆发伤害', 7, 1);
INSERT INTO `ys_cardpool_attack` VALUES (5, 'a2738b94c9795273', 1, 1, 25, 1, NULL, '2022-08-02 15:57:56', '炎龙', 10, 1, '想变成灰烬吗', 10, 1);
INSERT INTO `ys_cardpool_attack` VALUES (6, 'c93baf0471cb0e19', 1, 1, 20, 1, NULL, '2022-08-02 16:01:03', '神之握', 10, 1, '皆为废土', 14, 1);
INSERT INTO `ys_cardpool_attack` VALUES (7, '2b29c8042fa8ebd6', 1, 1, 0, 2, NULL, '2022-08-10 14:56:19', '血祭', 30, 0, '献祭自己50%血量上限的生命,对敌方造成自己血量上限2倍的伤害,无视护甲(包括自己)', 12, 2);
INSERT INTO `ys_cardpool_attack` VALUES (8, '981ecd042ec90532', 1, 1, 0, 2, NULL, '2022-08-21 15:39:39', '暂定', 30, 1, NULL, 12, 2);

-- ----------------------------
-- Table structure for ys_cardpool_restore
-- ----------------------------
DROP TABLE IF EXISTS `ys_cardpool_restore`;
CREATE TABLE `ys_cardpool_restore`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '卡牌id',
  `identifier` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '唯一id',
  `type` int(11) NOT NULL DEFAULT 2 COMMENT '卡牌类型,1攻击，2血量，3盾，4特殊',
  `level` int(11) NOT NULL COMMENT '卡牌品级',
  `value` bigint(20) NOT NULL DEFAULT 0 COMMENT '恢复量',
  `card_value_type` int(11) NOT NULL COMMENT '恢复类型 1 数值伤害,2 特殊效果,3 一次性',
  `final_value` double(11, 0) NULL DEFAULT NULL COMMENT '加成后最终增恢复',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `probability` int(10) NULL DEFAULT NULL,
  `del_flag` tinyint(2) NULL DEFAULT 0 COMMENT '0存在，1删除',
  `describe` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '简单描述',
  `price` int(20) NULL DEFAULT NULL COMMENT '费用',
  `consumes` int(20) NULL DEFAULT NULL COMMENT '消耗',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `probability`(`probability`) USING BTREE,
  INDEX `identfiier`(`identifier`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '回复类卡池（回血，护甲等）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ys_cardpool_restore
-- ----------------------------
INSERT INTO `ys_cardpool_restore` VALUES (1, 'b314b504cea921fb', 2, 1, 2, 1, NULL, '2022-07-31 00:00:00', '疗愈', 40, 0, '治疗效果有限的能力', 2, 1);
INSERT INTO `ys_cardpool_restore` VALUES (2, '794cb2641c4992f7', 3, 1, 2, 1, NULL, '2022-08-02 15:49:35', '护甲', 40, 0, '朴实无华的叠甲', 3, 1);
INSERT INTO `ys_cardpool_restore` VALUES (3, '2ed67a24109b3df8', 2, 1, 0, 3, NULL, '2022-08-21 15:41:34', '参悟', 20, 0, '消耗品：提升7生命上限，并恢复7生命值', 7, 0);

-- ----------------------------
-- Table structure for ys_monster
-- ----------------------------
DROP TABLE IF EXISTS `ys_monster`;
CREATE TABLE `ys_monster`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '唯一id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `base_health` bigint(20) NOT NULL DEFAULT 0 COMMENT '基础血量',
  `max_health` bigint(20) NOT NULL DEFAULT 0 COMMENT '血量上限',
  `base_armor` bigint(20) NOT NULL DEFAULT 0 COMMENT '护甲',
  `level` tinyint(2) NOT NULL DEFAULT 0 COMMENT '等级',
  `type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '类型',
  `skill_id` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '技能，使用数组的形式，存放skill的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '怪物表\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ys_monster
-- ----------------------------
INSERT INTO `ys_monster` VALUES ('10ac1904b1ca14b6', '哥布林', 10, 10, 5, 1, 1, '[\"dda3c4f40759e20d\",\"debf5bc48aa9fe0a\"]');
INSERT INTO `ys_monster` VALUES ('37a9fb8496389cf1', '得逞的牛头人', 35, 35, 15, 1, 1, '[\"2fa3c3e4a25b4688\",\"d812a8540d88b211\"]');
INSERT INTO `ys_monster` VALUES ('3d5670340b2911eb', '冰龙幼崽', 70, 70, 30, 1, 1, '[\"dda3c4f40759e20d\",\"debf5bc48aa9fe0a\"]');
INSERT INTO `ys_monster` VALUES ('930b3924cdb9fca1', '牛头人', 25, 25, 10, 1, 1, '[\"2fa3c3e4a25b4688\",\"d812a8540d88b211\"]');
INSERT INTO `ys_monster` VALUES ('a62339843aabbfda', '哥布林头目', 20, 20, 5, 1, 1, '[\"dda3c4f40759e20d\",\"debf5bc48aa9fe0a\"]');
INSERT INTO `ys_monster` VALUES ('cb2f6e54ef090fcf', '山贼王', 50, 50, 20, 1, 1, '[\"0e1651e49c495bae\",\"63f49184d0ab78c0\"]');

-- ----------------------------
-- Table structure for ys_monster_skill
-- ----------------------------
DROP TABLE IF EXISTS `ys_monster_skill`;
CREATE TABLE `ys_monster_skill`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `value` bigint(20) NOT NULL DEFAULT 0,
  `probability` int(10) NOT NULL DEFAULT 0 COMMENT '概率',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '怪物技能表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ys_monster_skill
-- ----------------------------
INSERT INTO `ys_monster_skill` VALUES ('0e1651e49c495bae', '射击', 5, 50, '2022-08-08 15:44:11');
INSERT INTO `ys_monster_skill` VALUES ('2fa3c3e4a25b4688', '铁山靠', 4, 45, '2022-08-08 15:43:40');
INSERT INTO `ys_monster_skill` VALUES ('63f49184d0ab78c0', '劈砍', 6, 55, '2022-08-08 15:44:37');
INSERT INTO `ys_monster_skill` VALUES ('d812a8540d88b211', '冲撞攻击', 3, 50, '2022-08-08 15:43:06');
INSERT INTO `ys_monster_skill` VALUES ('dda3c4f40759e20d', '扑击', 3, 60, '2022-08-08 15:43:06');
INSERT INTO `ys_monster_skill` VALUES ('debf5bc48aa9fe0a', '棍击', 2, 80, '2022-08-08 15:43:06');

-- ----------------------------
-- Table structure for ys_player_attribute
-- ----------------------------
DROP TABLE IF EXISTS `ys_player_attribute`;
CREATE TABLE `ys_player_attribute`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '属性id',
  `base_attack` bigint(50) NOT NULL COMMENT '基础攻击',
  `base_health` bigint(50) NOT NULL COMMENT '基础血量',
  `max_health` bigint(50) NOT NULL COMMENT '血量上限',
  `base_armor` bigint(50) NOT NULL COMMENT '基础护甲',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(2) NOT NULL DEFAULT 0 COMMENT '0存在，1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ys_player_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for ys_player_role
-- ----------------------------
DROP TABLE IF EXISTS `ys_player_role`;
CREATE TABLE `ys_player_role`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '主键id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '王二狗' COMMENT '名字',
  `type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '角色类型(0 游客临时玩家，1 注册玩家)',
  `attribute_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '角色属性id',
  `user_id` int(20) NOT NULL COMMENT '用户id',
  `sex` tinyint(2) NOT NULL DEFAULT 0 COMMENT '性别,0男，1女',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` tinyint(2) NOT NULL DEFAULT 0 COMMENT '0存在，1删除',
  `balance` int(20) NOT NULL DEFAULT 0 COMMENT '费用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '玩家角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ys_player_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
