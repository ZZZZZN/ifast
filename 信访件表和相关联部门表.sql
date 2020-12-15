/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : ifast

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 14/12/2020 08:59:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 增加 sys_petition_letter 表及相关权限数据
-- ----------------------------
DROP TABLE IF EXISTS `sys_petition_letter`;
CREATE TABLE `sys_petition_letter`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sourcepetition` int(0) NOT NULL COMMENT '信访来源',
  `petitiontime` datetime(0) NOT NULL COMMENT '信访时间',
  `lettertitle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '信访标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '信访内容',
  `receiptno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收文编号',
  `servicedept` int(0) NULL DEFAULT NULL COMMENT '交办科室',
  `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交办人',
  `status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '处理状态（0：未处理 1：已处理）',
  `receivetime` datetime(0) NOT NULL COMMENT '收文时间',
  `processtime` datetime(0) NULL DEFAULT NULL COMMENT '规定回复时间',
  `actualreplytime` datetime(0) NULL DEFAULT NULL COMMENT '实际回复时间',
  `remindertime` datetime(0) NULL DEFAULT NULL COMMENT '提醒时间',
  `isrecover` tinyint(0) NULL DEFAULT 0 COMMENT '是否收回（0：不回收 1：回收）',
  `recovertime` datetime(0) NULL DEFAULT NULL COMMENT '收回时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `delflag` tinyint(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除（0：不删除 ，1：逻辑删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1337319232841920514 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;



INSERT INTO `sys_menu`(`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1334677405680713730, 0, '信访管理', '', '', 0, 'fa fa-envelope', NULL, NULL, NULL);
INSERT INTO `sys_menu`(`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1334768190002520065, 1334677405680713730, '查看信访件', '/sys/petitionLetter/', '', 0, '', NULL, NULL, NULL);
INSERT INTO `sys_menu`(`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1337309248477417473, 1334768190002520065, '添加', '/sys/petitionLetter/add', 'sys:petitionLetter:add', 2, '', NULL, NULL, NULL);
INSERT INTO `sys_menu`(`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1337309427624529922, 1334768190002520065, '编辑', '/sys/petitionLetter/edit', 'sys:petitionLetter:edit', 2, '', NULL, NULL, NULL);
INSERT INTO `sys_menu`(`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1337309759511416834, 1334768190002520065, '删除', '/sys/petitionLetter/remove', 'sys:petitionLetter:remove', 2, '', NULL, NULL, NULL);


INSERT INTO `sys_dict`(`id`, `name`, `value`, `type`, `description`, `sort`, `parentId`, `createBy`, `createDate`, `updateBy`, `updateDate`, `remarks`, `delFlag`) VALUES (1057113773140254860, '问政江西', '1', 'source_ptittion', '信访件来源', NULL, 0, NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_dict`(`id`, `name`, `value`, `type`, `description`, `sort`, `parentId`, `createBy`, `createDate`, `updateBy`, `updateDate`, `remarks`, `delFlag`) VALUES (1057113773140254861, '局长邮箱', '2', 'source_ptittion', '信访件来源', NULL, 0, NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_dict`(`id`, `name`, `value`, `type`, `description`, `sort`, `parentId`, `createBy`, `createDate`, `updateBy`, `updateDate`, `remarks`, `delFlag`) VALUES (1337318577825853442, '其它', '3', 'source_ptittion', '信访件来源', NULL, 0, NULL, NULL, NULL, NULL, '', '0');

/*
 Navicat Premium Data Transfer

 Source Server         : 172.20.11.22
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 172.20.11.22:3306
 Source Schema         : ifast

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 14/12/2020 09:11:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 增加 sys_association 表
-- ----------------------------
DROP TABLE IF EXISTS `sys_association`;
CREATE TABLE `sys_association`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `petitionid` bigint(0) NULL DEFAULT NULL COMMENT '信访主键',
  `depid` int(0) NOT NULL COMMENT '送达科室',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1337319233093578755 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_association
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;




-- ----------------------------
-- 为 sys_dept 表 添加 email 字段
-- ----------------------------
alter table sys_dept add email varchar(255) NULL DEFAULT NULL COMMENT '部门邮箱';





-- 脚本已执行