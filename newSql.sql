-- ----------------------------
-- 挂图作战菜单插入语句
-- ----------------------------
INSERT INTO sys_menu (`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1339136401678524417, 0, '挂图作战', '', '', 0, 'fa fa-hourglass-2', NULL, NULL, NULL);
INSERT INTO sys_menu (`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1339142483368992769, 1339136401678524417, '查询挂图作战信息', '/sys/chartOperation', '', 0, '', NULL, NULL, NULL);
INSERT INTO sys_menu (`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1339460301692026881, 1339142483368992769, '增加', '/sys/chartOperation/add', 'sys:chartOperation:add', 2, '', NULL, NULL, NULL);
INSERT INTO sys_menu (`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1339460630559014913, 1339142483368992769, '编辑', 'sys/chartOperation/edit', 'sys:chartOperation:edit', 2, '', NULL, NULL, NULL);
INSERT INTO sys_menu (`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1339461184446218242, 1339142483368992769, '删除', '/sys/chartOperation/remove', 'sys:chartOperation:remove', 2, '', NULL, NULL, NULL);
INSERT INTO sys_menu (`id`, `parentId`, `name`, `url`, `perms`, `type`, `icon`, `orderNum`, `gmtCreate`, `gmtModified`) VALUES (1339461436968484866, 1339142483368992769, '批量删除', '/sys/chartOperation/batchRemove', 'sys:chartOperation:batchRemove', 2, '', NULL, NULL, NULL);
