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

 Date: 18/12/2020 
*/

INSERT INTO `ifast`.`sys_menu`(`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1339849043338485761, 1334768190002520065, '修改', '/sys/petitionLetter/update', 'sys:petitionLetter:update', 2, '', NULL, NULL, NULL);
INSERT INTO `ifast`.`sys_menu`(`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1339849248582557698, 1334768190002520065, '保存', '/sys/petitionLetter/save', 'sys:petitionLetter:save', 2, '', NULL, NULL, NULL);

INSERT INTO `ifast`.`sys_menu`(`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1339850010926669826, 1339142483368992769, '保存', '/sys/chartOperation/save', 'sys:chartOperation:save', 2, '', NULL, NULL, NULL);
INSERT INTO `ifast`.`sys_menu`(`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1339850161464434690, 1339142483368992769, '修改', '/sys/chartOperation/update', 'sys:chartOperation:update', 2, '', NULL, NULL, NULL);


