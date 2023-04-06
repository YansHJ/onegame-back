-- --------------------------------------------------------
-- 主机:                           124.70.5.108
-- 服务器版本:                        5.7.39 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 oneGame 的数据库结构
CREATE DATABASE IF NOT EXISTS `oneGame` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;
USE `oneGame`;

-- 导出  表 oneGame.game_level 结构
CREATE TABLE IF NOT EXISTS `game_level` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `number` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '可显示的序号',
  `next` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '下一关分支，关卡的唯一id集合串',
  `last` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上一关分支，关卡的唯一id集合串',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '关卡性质(0奖励，1小怪，2精英，3boss',
  `monster_ids` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '怪物ids',
  `card_num` int(6) DEFAULT NULL COMMENT '开局初始化卡牌数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `describe` varchar(300) COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
  `gold_award` int(10) DEFAULT '0' COMMENT '成功后金币奖励',
  `difficulty` int(10) DEFAULT '0' COMMENT '困难程度',
  `del_flag` int(11) DEFAULT '0' COMMENT '逻辑删除，0存在，1删除',
  PRIMARY KEY (`id`),
  KEY `number` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='关卡';

-- 正在导出表  oneGame.game_level 的数据：~18 rows (大约)
INSERT INTO `game_level` (`id`, `number`, `next`, `last`, `type`, `monster_ids`, `card_num`, `create_time`, `describe`, `gold_award`, `difficulty`, `del_flag`) VALUES
	('004e66c48098987b', '0d07-4-1', NULL, '["3f43c3042a793b4d","88890144724bc54c"]', 2, '', NULL, '2022-08-06 12:13:31', '获得奖励', 0, 0, 1),
	('04575ba46799abbd', NULL, NULL, NULL, 1, '4653e774105917ec', 5, '2022-08-22 17:33:52', '火箭队', 16, 2, 0),
	('0d074134b1d9c121', '0d07-1-1', '', '', 0, '10ac1904b1ca14b6', 5, '2022-08-06 12:13:31', '主线', 0, 0, 1),
	('1db4e8d4f5ba0946', NULL, NULL, NULL, 2, '37a9fb8496389cf1', 6, '2022-08-22 17:41:47', '哥布林头目', 26, 3, 0),
	('287e64d4068bb86e', NULL, NULL, NULL, 0, NULL, NULL, '2022-08-22 17:39:14', '商店', 0, 0, 0),
	('2b054a04bec97c46', NULL, NULL, NULL, 2, '930b3924cdb9fca1', 6, '2022-08-22 17:41:27', '得逞的牛头人', 25, 2, 0),
	('3f43c3042a793b4d', '0d07-3-1', NULL, '["983747145ce99158"]', 0, '930b3924cdb9fca1', 5, '2022-08-06 12:13:31', '战斗', 0, 0, 1),
	('3jjj3129mmlk1231', '0d07-7-1', NULL, '["7fc6d774357a9b3a"]', 0, '3d5670340b2911eb', 6, '2022-08-09 10:18:15', '战斗', 0, 0, 1),
	('528615f462db070e', NULL, NULL, NULL, 3, '3d5670340b2911eb', 7, '2022-08-22 17:43:08', '冰龙', 34, 3, 0),
	('5cd91de4f3e87c1c', NULL, NULL, NULL, 2, 'a62339843aabbfda', 6, '2022-08-22 17:41:10', '牛头人', 25, 1, 0),
	('6d8bc1747f5be317', NULL, NULL, NULL, 1, '5e53667448a944bf', 5, '2022-08-22 17:33:42', '触手怪', 13, 2, 0),
	('759802f4902a3201', NULL, NULL, NULL, 1, 'd411ecf493c8e193', 5, '2022-08-22 17:38:20', '初级魅魔', 13, 3, 0),
	('7fc6d774357a9b3a', '0d07-6-1', NULL, '["c95d4584d7cb193b"]', 2, '', NULL, '2022-08-08 10:21:25', '获得奖励', 0, 0, 1),
	('81306e648768ca9e', NULL, NULL, NULL, 1, '10ac1904b1ca14b6', 5, '2022-08-22 17:32:59', '哥布林', 12, 1, 0),
	('88890144724bc54c', '0d07-3-2', NULL, '["983747145ce99158"]', 0, '37a9fb8496389cf1', 5, '2022-08-06 12:13:31', '战斗', 0, 0, 1),
	('899fe5c4d04918ab', NULL, NULL, NULL, 1, 'c007aaa4e25b542f', 5, '2022-08-22 17:33:26', '史莱姆', 15, 1, 0),
	('904b3514950a1b51', NULL, NULL, NULL, 3, '3d5670340b2911eb', 7, '2022-08-22 17:42:57', '冰龙', 31, 2, 0),
	('983747145ce99158', '0d07-2-1', NULL, '["0d074134b1d9c121"]', 2, '', NULL, '2022-08-06 12:13:31', '获得奖励', 0, 0, 1),
	('b8e1cb2445d8f0b5', NULL, NULL, NULL, 3, 'cb2f6e54ef090fcf', 7, '2022-08-22 17:42:07', '山贼王', 30, 1, 0),
	('c95d4584d7cb193b', '0d07-5-1', NULL, '["004e66c48098987b"]', 1, 'cb2f6e54ef090fcf', 6, '2022-08-06 12:13:31', 'BOSS', 0, 0, 1),
	('ffb9d5147b8ab919', NULL, NULL, NULL, 1, 'd411ecf493c8e193', 5, '2022-08-22 17:37:37', '初级魅魔', 12, 3, 0);

-- 导出  表 oneGame.ys_cardpool_attack 结构
CREATE TABLE IF NOT EXISTS `ys_cardpool_attack` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '卡牌id',
  `identifier` varchar(50) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '唯一id',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '卡牌类型,1攻击，2血量，3盾，4特殊',
  `level` int(11) NOT NULL COMMENT '卡牌品级',
  `value` bigint(20) NOT NULL DEFAULT '0' COMMENT '伤害值',
  `card_value_type` int(11) NOT NULL COMMENT '伤害类型 1 数值伤害,2 特殊效果,3 一次性',
  `final_value` double(11,0) DEFAULT NULL COMMENT '加成后最终伤害',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '卡牌名',
  `probability` int(10) DEFAULT NULL COMMENT '抽取概率',
  `del_flag` tinyint(2) DEFAULT '0' COMMENT '0存在，1删除',
  `describe` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '简单描述',
  `price` int(20) DEFAULT NULL COMMENT '购买费用',
  `consumes` int(20) DEFAULT NULL COMMENT '消耗行动力',
  PRIMARY KEY (`id`),
  KEY `probability` (`probability`),
  KEY `identfiier` (`identifier`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='攻击类卡池';

-- 正在导出表  oneGame.ys_cardpool_attack 的数据：~7 rows (大约)
INSERT INTO `ys_cardpool_attack` (`id`, `identifier`, `type`, `level`, `value`, `card_value_type`, `final_value`, `create_time`, `name`, `probability`, `del_flag`, `describe`, `price`, `consumes`) VALUES
	(1, '9cff6044aaa95fa3', 1, 1, 2, 1, NULL, '2022-07-31 13:40:50', '斩击', 50, 0, '朴实无华的攻击', 2, 1),
	(2, '1d7ad934546847df', 1, 1, 4, 1, NULL, '2022-07-31 13:42:46', '鬼火', 40, 0, '非自然力量的火焰，灼烧灵魂', 3, 1),
	(3, '0206f554f9f8729e', 1, 1, 8, 1, NULL, '2022-07-31 14:41:47', '居合斩', 20, 0, '看得见我的刀刃吗', 4, 1),
	(4, '8005fb64830b5526', 1, 1, 14, 1, NULL, '2022-08-02 15:50:03', '炼狱', 15, 1, '爆发伤害', 7, 1),
	(5, 'a2738b94c9795273', 1, 1, 25, 1, NULL, '2022-08-02 15:57:56', '炎龙', 10, 1, '想变成灰烬吗', 10, 1),
	(6, 'c93baf0471cb0e19', 1, 1, 20, 1, NULL, '2022-08-02 16:01:03', '神之握', 10, 1, '皆为废土', 14, 1),
	(7, '2b29c8042fa8ebd6', 1, 3, 0, 2, NULL, '2022-08-10 14:56:19', '血祭', 30, 0, '献祭自己50%血量上限的生命,对敌方造成自己血量上限2倍的伤害,无视护甲(包括自己)', 12, 2),
	(8, '981ecd042ec90532', 1, 3, 0, 2, NULL, '2022-08-21 15:39:39', '盾击', 30, 0, '造成自身护盾量的伤害，但会损失一半的护盾', 8, 1);

-- 导出  表 oneGame.ys_cardpool_restore 结构
CREATE TABLE IF NOT EXISTS `ys_cardpool_restore` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '卡牌id',
  `identifier` varchar(50) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '唯一id',
  `type` int(11) NOT NULL DEFAULT '2' COMMENT '卡牌类型,1攻击，2血量，3盾，4特殊',
  `level` int(11) NOT NULL COMMENT '卡牌品级',
  `value` bigint(20) NOT NULL DEFAULT '0' COMMENT '恢复量',
  `card_value_type` int(11) NOT NULL COMMENT '恢复类型 1 数值伤害,2 特殊效果,3 一次性',
  `final_value` double(11,0) DEFAULT NULL COMMENT '加成后最终增恢复',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `probability` int(10) DEFAULT NULL,
  `del_flag` tinyint(2) DEFAULT '0' COMMENT '0存在，1删除',
  `describe` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '简单描述',
  `price` int(20) DEFAULT NULL COMMENT '费用',
  `consumes` int(20) DEFAULT NULL COMMENT '消耗',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `probability` (`probability`),
  KEY `identfiier` (`identifier`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='回复类卡池（回血，护甲等）';

-- 正在导出表  oneGame.ys_cardpool_restore 的数据：~2 rows (大约)
INSERT INTO `ys_cardpool_restore` (`id`, `identifier`, `type`, `level`, `value`, `card_value_type`, `final_value`, `create_time`, `name`, `probability`, `del_flag`, `describe`, `price`, `consumes`) VALUES
	(1, 'b314b504cea921fb', 2, 1, 2, 1, NULL, '2022-07-31 00:00:00', '疗愈', 40, 0, '治疗效果有限的能力', 2, 1),
	(2, '794cb2641c4992f7', 3, 1, 2, 1, NULL, '2022-08-02 15:49:35', '护甲', 40, 0, '朴实无华的叠甲', 3, 1),
	(3, '2ed67a24109b3df8', 2, 2, 0, 3, NULL, '2022-08-21 15:41:34', '参悟', 20, 0, '消耗品：提升7生命上限，并恢复7生命值', 7, 0);

-- 导出  表 oneGame.ys_monster 结构
CREATE TABLE IF NOT EXISTS `ys_monster` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '唯一id',
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `base_health` bigint(20) NOT NULL DEFAULT '0' COMMENT '基础血量',
  `max_health` bigint(20) NOT NULL DEFAULT '0' COMMENT '血量上限',
  `base_armor` bigint(20) NOT NULL DEFAULT '0' COMMENT '护甲',
  `level` tinyint(2) NOT NULL DEFAULT '0' COMMENT '等级，对应地图难度',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '类型，0普通，1精英，2boss',
  `skill_id` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '技能，使用数组的形式，存放skill的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='怪物表\r\n';

-- 正在导出表  oneGame.ys_monster 的数据：~9 rows (大约)
INSERT INTO `ys_monster` (`id`, `name`, `base_health`, `max_health`, `base_armor`, `level`, `type`, `skill_id`) VALUES
	('10ac1904b1ca14b6', '哥布林', 10, 10, 5, 1, 1, '["dda3c4f40759e20d","debf5bc48aa9fe0a"]'),
	('37a9fb8496389cf1', '得逞的牛头人', 35, 35, 15, 2, 2, '["2fa3c3e4a25b4688","d812a8540d88b211"]'),
	('3d5670340b2911eb', '冰龙幼崽', 70, 70, 30, 3, 3, '["dda3c4f40759e20d","debf5bc48aa9fe0a"]'),
	('4653e774105917ec', '尸人', 17, 17, 7, 2, 1, '["2a689fc4c39945b4","ea18a0548cb95066"]'),
	('5e53667448a944bf', '触手怪', 15, 15, 5, 2, 1, '["294bc52410fa2881","142ac29439bb1edf"]'),
	('930b3924cdb9fca1', '牛头人', 25, 25, 10, 2, 2, '["2fa3c3e4a25b4688","d812a8540d88b211"]'),
	('a62339843aabbfda', '哥布林头目', 20, 20, 5, 2, 2, '["63f49184d0ab78c0","44c2ba3421fa2b14"]'),
	('c007aaa4e25b542f', '史莱姆', 12, 12, 5, 1, 1, '["31f8ef94589af3ac","d85ee4f48b280496"]'),
	('cb2f6e54ef090fcf', '山贼王', 50, 50, 20, 3, 3, '["0e1651e49c495bae","63f49184d0ab78c0"]'),
	('d411ecf493c8e193', '初级魅魔', 18, 18, 8, 3, 1, '["525a8c745008f77a","2d1691a460693842"]');

-- 导出  表 oneGame.ys_monster_skill 结构
CREATE TABLE IF NOT EXISTS `ys_monster_skill` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `value` bigint(20) NOT NULL DEFAULT '0',
  `probability` int(10) NOT NULL DEFAULT '0' COMMENT '概率',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='怪物技能表';

-- 正在导出表  oneGame.ys_monster_skill 的数据：~15 rows (大约)
INSERT INTO `ys_monster_skill` (`id`, `name`, `value`, `probability`, `create_time`) VALUES
	('0e1651e49c495bae', '射击', 5, 50, '2022-08-08 15:44:11'),
	('142ac29439bb1edf', '触手缠绕', 5, 50, '2022-08-24 15:45:42'),
	('294bc52410fa2881', '触手抽打', 4, 50, '2022-08-24 15:45:05'),
	('2a689fc4c39945b4', '吸血', 4, 50, '2022-08-24 15:47:47'),
	('2d1691a460693842', '拔枪射击', 6, 50, '2022-08-24 15:52:52'),
	('2fa3c3e4a25b4688', '铁山靠', 4, 45, '2022-08-08 15:43:40'),
	('31f8ef94589af3ac', '粘液攻击', 2, 50, '2022-08-24 15:43:39'),
	('44c2ba3421fa2b14', '丢岩石', 6, 50, '2022-08-24 15:54:48'),
	('525a8c745008f77a', '魅惑', 5, 50, '2022-08-24 15:51:52'),
	('63f49184d0ab78c0', '劈砍', 6, 55, '2022-08-08 15:44:37'),
	('d812a8540d88b211', '冲撞攻击', 3, 50, '2022-08-08 15:43:06'),
	('d85ee4f48b280496', '吐口水', 3, 50, '2022-08-24 15:44:04'),
	('dda3c4f40759e20d', '扑击', 3, 60, '2022-08-08 15:43:06'),
	('debf5bc48aa9fe0a', '棍击', 2, 80, '2022-08-08 15:43:06'),
	('ea18a0548cb95066', '腐毒', 5, 50, '2022-08-24 15:50:54');

-- 导出  表 oneGame.ys_player_attribute 结构
CREATE TABLE IF NOT EXISTS `ys_player_attribute` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '属性id',
  `base_attack` bigint(50) NOT NULL COMMENT '基础攻击',
  `base_health` bigint(50) NOT NULL COMMENT '基础血量',
  `max_health` bigint(50) NOT NULL COMMENT '血量上限',
  `base_armor` bigint(50) NOT NULL COMMENT '基础护甲',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0存在，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色属性表';

-- 正在导出表  oneGame.ys_player_attribute 的数据：~1 rows (大约)

-- 导出  表 oneGame.ys_player_role 结构
CREATE TABLE IF NOT EXISTS `ys_player_role` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键id',
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT '王二狗' COMMENT '名字',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '角色类型(0 游客临时玩家，1 注册玩家)',
  `attribute_id` varchar(50) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '角色属性id',
  `user_id` int(20) NOT NULL COMMENT '用户id',
  `sex` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别,0男，1女',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0存在，1删除',
  `balance` int(20) NOT NULL DEFAULT '0' COMMENT '费用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='玩家角色表';

-- 正在导出表  oneGame.ys_player_role 的数据：~0 rows (大约)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
