-- jy system init
--插入组数据
INSERT INTO TB_GROUP VALUES ('402881c4408c7d2301408c870ed10003', 'WIFI商户', null, '1', '03', null, 0);

--插入资源数据
	--插入资源 菜单类型
INSERT INTO TB_RESOURCE (ID, "PERMISSION", REMARK, SORT, "NAME", "TYPE", "VALUE", FK_PARENT_ID, ICON, LEAF) 
	VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0028', NULL, NULL, 26, 'WIFI管理', '01', '#', NULL, NULL, true);
	--插入资源 安全类型
INSERT INTO TB_RESOURCE (ID, "PERMISSION", REMARK, SORT, "NAME", "TYPE", "VALUE", FK_PARENT_ID, ICON, LEAF) 
	VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0029', 'perms[usermanager:view]', '', 27, '查看会员管理', '02', '/shop/usermanager/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0028', NULL, false);

INSERT INTO TB_RESOURCE (ID, "PERMISSION", REMARK, SORT, "NAME", "TYPE", "VALUE", FK_PARENT_ID, ICON, LEAF) 
	VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0030', 'perms[usermanager:tocategory]', '', 28, '对Wifi用户分组', '02', '/shop/usermanager/tocategory/**', 'SJDK3849CKMS3849DJCK2039ZMSK0028', NULL, false);	

INSERT INTO TB_RESOURCE (ID, "PERMISSION", REMARK, SORT, "NAME", "TYPE", "VALUE", FK_PARENT_ID, ICON, LEAF) 
	VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0031', 'perms[usermanager:deluserslog]', '', 29, '删除商户分组中的wifi用户', '02', '/shop/usermanager/deluserslog/**', 'SJDK3849CKMS3849DJCK2039ZMSK0028', NULL, false);		

--插入用户数据
INSERT INTO TB_USER (ID, EMAIL, PASSWORD, PORTRAIT, REALNAME, "STATE", USERNAME) 
	VALUES ('17909124407b8d7901407be4996c0008', 'jeekly@foxmail.com', 'e10adc3949ba59abbe56e057f20f883e', NULL, 'zyt', 1, 'jeekly');

INSERT INTO TB_USER (ID, EMAIL, PASSWORD, PORTRAIT, REALNAME, "STATE", USERNAME) 
	VALUES ('17909124407b8d7901407be4996c0009', 'zj@foxmail.com', 'e10adc3949ba59abbe56e057f20f883e', NULL, 'zhengjian', 1, 'zhengjian');
	
--插入组与资源中间表数据
INSERT INTO TB_GROUP_RESOURCE (FK_RESOURCE_ID, FK_GROUP_ID) 
	VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0028', '402881c4408c7d2301408c870ed10003');
INSERT INTO TB_GROUP_RESOURCE (FK_RESOURCE_ID, FK_GROUP_ID) 
	VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0029', '402881c4408c7d2301408c870ed10003');
INSERT INTO TB_GROUP_RESOURCE (FK_RESOURCE_ID, FK_GROUP_ID) 
	VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0030', '402881c4408c7d2301408c870ed10003');
INSERT INTO TB_GROUP_RESOURCE (FK_RESOURCE_ID, FK_GROUP_ID) 
	VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0031', '402881c4408c7d2301408c870ed10003');	

--插入组与用户中间表数据
INSERT INTO TB_GROUP_USER (FK_GROUP_ID, FK_USER_ID) 
	VALUES ('402881c4408c7d2301408c870ed10003', '17909124407b8d7901407be4996c0008');
INSERT INTO TB_GROUP_USER (FK_GROUP_ID, FK_USER_ID) 
	VALUES ('402881c4408c7d2301408c870ed10003', '17909124407b8d7901407be4996c0009');	
-- jy system init end
