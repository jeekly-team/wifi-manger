--删除所有表
drop table TB_DATA_DICTIONARY if exists;
drop table TB_DICTIONARY_CATEGORY if exists;
drop table TB_GROUP if exists;
drop table TB_GROUP_RESOURCE if exists;
drop table TB_GROUP_USER if exists;
drop table TB_RESOURCE if exists;
drop table TB_USER if exists;
drop table TB_OPERATING_RECORD if exists;

--创建系统字典表
create table TB_DATA_DICTIONARY (id varchar(32) not null, name varchar(256) not null, remark varchar(512), type varchar(1) not null, value varchar(32) not null, fk_category_id varchar(32) not null, primary key (id));
create table TB_DICTIONARY_CATEGORY (id varchar(32) not null, code varchar(128) not null, name varchar(256) not null, remark varchar(512), fk_parent_id varchar(32), leaf boolean, primary key (id));

--创建权限表
create table TB_GROUP (id varchar(32) not null, name varchar(32) not null, remark varchar(512), state integer not null, type varchar(2) not null, fk_parent_id varchar(32), leaf boolean, primary key (id));
create table TB_GROUP_RESOURCE (fk_resource_id varchar(32) not null, fk_group_id varchar(32) not null);
create table TB_GROUP_USER (fk_group_id varchar(32) not null, fk_user_id varchar(32) not null);
create table TB_RESOURCE (id varchar(32) not null, permission varchar(64), remark varchar(512), sort integer not null, name varchar(32) not null, type varchar(2) not null, value varchar(256), fk_parent_id varchar(32), icon varchar(32), leaf boolean, primary key (id));
create table TB_USER (id varchar(32) not null, email varchar(128), password varchar(32) not null, portrait varchar(256), realname varchar(64) not null, state integer not null, username varchar(32) not null, primary key (id));

--创建审计表
create table TB_OPERATING_RECORD (id varchar(32) not null, end_date timestamp not null, fk_user_id varchar(32), operating_target varchar(512) not null, start_date timestamp not null, username varchar(32), function varchar(128), ip varchar(64) not null, method varchar(256) not null, module varchar(128), remark clob, state integer not null, primary key (id));

--创建所有表关联
alter table TB_DICTIONARY_CATEGORY add constraint UK_9qkei4dxobl1lm4oa0ys8c3nr unique (code);
alter table TB_GROUP add constraint UK_byw2jrrrxrueqimkmgj3o842j unique (name);
alter table TB_RESOURCE add constraint UK_aunvlvm32xb4e6590jc9oooq unique (name);
alter table TB_USER add constraint UK_4wv83hfajry5tdoamn8wsqa6x unique (username);
alter table TB_DATA_DICTIONARY add constraint FK_layhfd1butuigsscgucmp2okd foreign key (fk_category_id) references TB_DICTIONARY_CATEGORY;
alter table TB_DICTIONARY_CATEGORY add constraint FK_bernf41kympxy2kjl4vbq5q44 foreign key (fk_parent_id) references TB_DICTIONARY_CATEGORY;
alter table TB_GROUP add constraint FK_idve4hc50mytxm181wl1knw28 foreign key (fk_parent_id) references tb_group;
alter table TB_GROUP_RESOURCE add constraint FK_q82fpmfh128qxoeyymrkg71e2 foreign key (fk_group_id) references TB_GROUP;
alter table TB_GROUP_RESOURCE add constraint FK_3tjs4wt3vvoibo1fvcvog5srd foreign key (fk_resource_id) references TB_RESOURCE;
alter table TB_GROUP_USER add constraint FK_7k068ltfepa1q75qtmvxuawk foreign key (fk_user_id) references TB_USER;
alter table TB_GROUP_USER add constraint FK_rgmkki7dggfag6ow6eivljmwv foreign key (fk_group_id) references TB_GROUP;
alter table TB_RESOURCE add constraint FK_k2heqvi9muk4cjyyd53r9y37x foreign key (fk_parent_id) references TB_RESOURCE;

--插入字典类别数据
INSERT INTO TB_DICTIONARY_CATEGORY VALUES ('402881e437d467d80137d46fc0e50001', 'state', '状态', null, null, 0);
INSERT INTO TB_DICTIONARY_CATEGORY VALUES ('402881e437d467d80137d4709b9c0002', 'resource-type', '资源类型', null, null, 0);
INSERT INTO TB_DICTIONARY_CATEGORY VALUES ('402881e437d467d80137d4712ca70003', 'group-type', '组类型', null, null, 0);
INSERT INTO TB_DICTIONARY_CATEGORY VALUES ('402881e437d47b250137d485274b0004', 'value-type', '值类型', null, null, 0);
INSERT INTO TB_DICTIONARY_CATEGORY VALUES ('402881e437d47b250137d485274b0005', 'operating-state', '操作状态', null, null, 0);

--插入数据字典数据
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d47b250137d481b6920001', '启用', null, 'I', '1', '402881e437d467d80137d46fc0e50001');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d47b250137d481dda30002', '禁用', null, 'I', '2', '402881e437d467d80137d46fc0e50001');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d47b250137d481f23a0003', '删除', null, 'I', '3', '402881e437d467d80137d46fc0e50001');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d47b250137d4870b230005', 'String', null, 'S', 'S', '402881e437d47b250137d485274b0004');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d47b250137d487328e0006', 'Integer', null, 'S', 'I', '402881e437d47b250137d485274b0004');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d47b250137d487a3af0007', 'Long', null, 'S', 'L', '402881e437d47b250137d485274b0004');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d47b250137d487e23a0008', 'Double', null, 'S', 'N', '402881e437d47b250137d485274b0004');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d47b250137d488416d0009', 'Date', null, 'S', 'D', '402881e437d47b250137d485274b0004');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d47b250137d4885686000a', 'Boolean', null, 'S', 'B', '402881e437d47b250137d485274b0004');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d49e430137d4a5e8570003', '菜单类型', null, 'S', '01', '402881e437d467d80137d4709b9c0002');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d49e430137d4a61cec0004', '资源类型', null, 'S', '02', '402881e437d467d80137d4709b9c0002');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d49e430137d4a6f1aa0005', '部门', null, 'S', '02', '402881e437d467d80137d4712ca70003');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d49e430137d4a7783d0006', '机构', null, 'S', '01', '402881e437d467d80137d4712ca70003');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d49e430137d4a7ba1a0007', '权限组', null, 'S', '03', '402881e437d467d80137d4712ca70003');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d49e430137d4a7783d0008', '成功', null, 'I', '1', '402881e437d47b250137d485274b0005');
INSERT INTO TB_DATA_DICTIONARY VALUES ('402881e437d49e430137d4a7ba1a0009', '失败', null, 'I', '2', '402881e437d47b250137d485274b0005');

--插入组数据
INSERT INTO TB_GROUP VALUES ('402881c4408c7d2301408c86b7a80001', '普通用户', null, '1', '03', null, 0);
INSERT INTO TB_GROUP VALUES ('402881c4408c7d2301408c870ed10002', '运维人员', null, '1', '03', null, 0);
INSERT INTO TB_GROUP VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0002', '超级管理员', null, '1', '03', null, 0);

--插入资源数据
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0003', null, null, '1', '权限管理', '01', '#', null, 'glyphicon-briefcase', 1);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0004', 'perms[user:view]', null, '2', '用户管理', '01', '/account/user/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0003', 'glyphicon-user', 1);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0005', 'perms[user:create]', null, '3', '创建用户', '02', '/account/user/insert/**', 'SJDK3849CKMS3849DJCK2039ZMSK0004', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0006', 'perms[user:update]', null, '4', '修改用户', '02', '/account/user/update/**', 'SJDK3849CKMS3849DJCK2039ZMSK0004', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0007', 'perms[user:delete]', null, '5', '删除用户', '02', '/account/user/delete/**', 'SJDK3849CKMS3849DJCK2039ZMSK0004', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0008', 'perms[user:read]', null, '6', '查看用户', '02', '/account/user/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0004', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0009', 'perms[group:view]', null, '7', '组管理', '01', '/account/group/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0003', 'glyphicon-briefcase', 1);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0011', 'perms[group:save]', null, '9', '创建和编辑组', '02', '/account/group/save/**', 'SJDK3849CKMS3849DJCK2039ZMSK0009', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0012', 'perms[group:read]', null, '10', '查看组', '02', '/account/group/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0009', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0013', 'perms[group:delete]', null, '11', '删除组', '02', '/account/group/delete/**', 'SJDK3849CKMS3849DJCK2039ZMSK0009', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0010', 'perms[resource:view]', null, '8', '资源管理', '01', '/account/resource/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0003', 'glyphicon-link', 1);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0014', 'perms[resource:save]', null, '12', '创建和编辑资源', '02', '/account/resource/save/**', 'SJDK3849CKMS3849DJCK2039ZMSK0010', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0015', 'perms[resource:read]', null, '13', '查看资源', '02', '/account/resource/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0010', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0016', 'perms[resource:delete]', null, '14', '删除资源', '02', '/account/resource/delete/**', 'SJDK3849CKMS3849DJCK2039ZMSK0010', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0017', null, null, '15', '系统管理', '01', '#', null, 'glyphicon-cog', 1);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0018', 'perms[data-dictionary:view]', '', '16', '数据字典管理', '01', '/foundation/variable/data-dictionary/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0017', 'glyphicon-list-alt', 1);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0022', 'perms[data-dictionary:save]', null, '20', '创建和编辑数据字典', '02', '/foundation/variable/data-dictionary/save/**', 'SJDK3849CKMS3849DJCK2039ZMSK0018', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0024', 'perms[data-dictionary:read]', null, '22', '查看数据字典', '02', '/foundation/variable/data-dictionary/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0018', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0023', 'perms[data-dictionary:delete]', null, '21', '删除数据字典', '02', '/foundation/variable/data-dictionary/delete/**', 'SJDK3849CKMS3849DJCK2039ZMSK0018', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0019', 'perms[dictionary-category:view]', null, '17', '字典类别管理', '01', '/foundation/variable/dictionary-category/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0017', 'glyphicon-folder-close', 1);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0020', 'perms[dictionary-category:save]', null, '18', '创建和编辑字典类别', '02', '/foundation/variable/dictionary-category/save/**', 'SJDK3849CKMS3849DJCK2039ZMSK0019', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0025', 'perms[dictionary-category:read]', '', '23', '查看字典类别', '02', '/foundation/variable/dictionary-category/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0019', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0021', 'perms[dictionary-category:delete]', null, '19', '删除字典类别', '02', '/foundation/variable/dictionary-category/delete/**', 'SJDK3849CKMS3849DJCK2039ZMSK0019', null, 0);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0026', 'perms[operating-record:view]', null, '24', '操作记录管理', '01', '/foundation/audit/operating-record/view/**', 'SJDK3849CKMS3849DJCK2039ZMSK0017', 'glyphicon-eye-open', 1);
INSERT INTO TB_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0027', 'perms[operating-record:read]', '', '25', '查看操作日志', '02', '/foundation/audit/operating-record/read/**', 'SJDK3849CKMS3849DJCK2039ZMSK0026', null, 0);

--插入用户数据
INSERT INTO TB_USER VALUES ('17909124407b8d7901407be4996c0004', 'es.nick@es.com', 'e10adc3949ba59abbe56e057f20f883e', null, 'nick.lu', '1', 'es001');
INSERT INTO TB_USER VALUES ('17909124407b8d7901407be4996c0005', 'es.nick@es.com', 'e10adc3949ba59abbe56e057f20f883e', null, 'user1', '1', 'es002');
INSERT INTO TB_USER VALUES ('17909124407b8d7901407be4996c0006', 'es.nick@es.com', 'e10adc3949ba59abbe56e057f20f883e', null, 'user2', '1', 'es003');
INSERT INTO TB_USER VALUES ('17909124407b8d7901407be4996c0007', 'es.nick@es.com', 'e10adc3949ba59abbe56e057f20f883e', null, 'user3', '1', 'es004');
INSERT INTO TB_USER VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0001', 'es.chenxiaobo@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', null, 'maurice.chen', '1', 'maurice');

--插入组与资源中间表数据
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0003', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0004', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0005', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0006', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0007', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0008', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0009', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0010', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0011', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0012', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0013', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0014', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0015', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0016', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0017', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0018', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0019', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0020', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0021', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0022', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0023', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0024', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0025', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0026', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0027', 'SJDK3849CKMS3849DJCK2039ZMSK0002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0003', '402881c4408c7d2301408c86b7a80001');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0004', '402881c4408c7d2301408c86b7a80001');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0008', '402881c4408c7d2301408c86b7a80001');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0009', '402881c4408c7d2301408c86b7a80001');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0010', '402881c4408c7d2301408c86b7a80001');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0012', '402881c4408c7d2301408c86b7a80001');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0015', '402881c4408c7d2301408c86b7a80001');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0017', '402881c4408c7d2301408c870ed10002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0018', '402881c4408c7d2301408c870ed10002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0019', '402881c4408c7d2301408c870ed10002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0020', '402881c4408c7d2301408c870ed10002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0021', '402881c4408c7d2301408c870ed10002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0022', '402881c4408c7d2301408c870ed10002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0023', '402881c4408c7d2301408c870ed10002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0024', '402881c4408c7d2301408c870ed10002');
INSERT INTO TB_GROUP_RESOURCE VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0025', '402881c4408c7d2301408c870ed10002');

--插入组与用户中间表数据
INSERT INTO TB_GROUP_USER VALUES ('SJDK3849CKMS3849DJCK2039ZMSK0002', 'SJDK3849CKMS3849DJCK2039ZMSK0001');
INSERT INTO TB_GROUP_USER VALUES ('402881c4408c7d2301408c86b7a80001', '17909124407b8d7901407be4996c0005');
INSERT INTO TB_GROUP_USER VALUES ('402881c4408c7d2301408c86b7a80001', '17909124407b8d7901407be4996c0006');
INSERT INTO TB_GROUP_USER VALUES ('402881c4408c7d2301408c86b7a80001', '17909124407b8d7901407be4996c0007');
INSERT INTO TB_GROUP_USER VALUES ('402881c4408c7d2301408c870ed10002', '17909124407b8d7901407be4996c0004');

-- shop admin begin


drop table WIFIUSERGROUP if exists;
drop table WIFIUSERGROUP_WIFIUSER if exists;
DROP TABLE wifiuser IF EXISTS;
DROP TABLE validatelog IF EXISTS;
DROP TABLE validatecodelog IF EXISTS;

--建表
create table WIFIUSERGROUP (id varchar(32) not null,name varchar(32) not null,sysuser_id varchar(32) not null, primary key (id));
create table WIFIUSERGROUP_WIFIUSER (fk_group_id varchar(32) not null, fk_user_id varchar(32) not null);
CREATE TABLE wifiuser (id varchar(32) not null,name varchar(64) not null unique, primary key (id));
CREATE TABLE validatelog (id varchar(32) not null,dt timestamp,sid varchar(32) not null,type varchar(16) not null,wifiuser_id varchar(32) not null,primary key (id));
CREATE TABLE validatecodelog ( id varchar(32) not null, vcode varchar(32) not null, log_id varchar(32) not null, primary key (id));
--建表关联
alter table WIFIUSERGROUP_WIFIUSER add constraint FK_1k068ltfepa1q01qtmvxuawk foreign key (fk_user_id) references WIFIUSER;
alter table WIFIUSERGROUP_WIFIUSER add constraint FK_1k068ltfepa1q02qtmvxuawk foreign key (fk_group_id) references WIFIUSERGROUP;

--WifiUser
INSERT INTO wifiuser VALUES ('202881e437d47b250137d481b6920001', '18691525183');
INSERT INTO wifiuser VALUES ('202881e437d47b250137d481b6920002', 'zyt');


--ValidateLog
INSERT INTO validatelog VALUES('ff80808146f76ade0146f76d02c30001', '2014-07-01 22:14:14', 'SJDK3849CKMS3849DJCK2039ZMSK0001', 'phmsg', '202881e437d47b250137d481b6920001');
INSERT INTO validatelog VALUES('ff80808146f76ade0146f77102c30002', '2014-07-01 22:18:37', 'SJDK3849CKMS3849DJCK2039ZMSK0001', 'phmsg', '202881e437d47b250137d481b6920001');

INSERT INTO validatelog VALUES('ff80808146f76ade0146f77d1bb30013', '2014-07-02 22:31:49', 'SJDK3849CKMS3849DJCK2039ZMSK0001', 'phmsg', '202881e437d47b250137d481b6920002');

INSERT INTO validatelog VALUES('ff80808146f76ade0146f77d1bb30014', '2014-07-02 22:31:49', '17909124407b8d7901407be4996c0004', 'phmsg', '202881e437d47b250137d481b6920002');
INSERT INTO validatelog VALUES('ff80808146f76ade0146f784d6d40016', '2014-07-04 22:40:16', '17909124407b8d7901407be4996c0004', 'phmsg', '202881e437d47b250137d481b6920002');

--validatecodelog
insert into validatecodelog values('302881e437d47b250137d481b6920001','test001','SJDK3849CKMS3849DJCK2039ZMSK0001');
insert into validatecodelog values('302881e437d47b250137d481b6920002','test002','17909124407b8d7901407be4996c0004');


--WIFIUSERGROUP
--402881e437d47b250137d481b6920002','B' no user
insert into WIFIUSERGROUP values('402881e437d47b250137d481b6920001','A','SJDK3849CKMS3849DJCK2039ZMSK0001');
insert into WIFIUSERGROUP values('402881e437d47b250137d481b6920002','B','SJDK3849CKMS3849DJCK2039ZMSK0001');

insert into WIFIUSERGROUP values('402881e437d47b250137d481b6920007','C','17909124407b8d7901407be4996c0004');
insert into WIFIUSERGROUP values('402881e437d47b250137d481b6920008','D','17909124407b8d7901407be4996c0004');

--WIFIUSERGROUP_WIFIUSER
insert into WIFIUSERGROUP_WIFIUSER values('402881e437d47b250137d481b6920001','202881e437d47b250137d481b6920001');
insert into WIFIUSERGROUP_WIFIUSER values('402881e437d47b250137d481b6920001','202881e437d47b250137d481b6920002');


insert into WIFIUSERGROUP_WIFIUSER values('402881e437d47b250137d481b6920007','202881e437d47b250137d481b6920002');

-- shop admin end
