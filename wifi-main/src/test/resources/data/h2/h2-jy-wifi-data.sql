
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
INSERT INTO validatelog VALUES('ff80808146f76ade0146f76d02c30001', '2014-07-01 22:14:14', '17909124407b8d7901407be4996c0008', 'phmsg', '202881e437d47b250137d481b6920001');
INSERT INTO validatelog VALUES('ff80808146f76ade0146f77102c30002', '2014-07-01 22:18:37', '17909124407b8d7901407be4996c0008', 'phmsg', '202881e437d47b250137d481b6920001');

INSERT INTO validatelog VALUES('ff80808146f76ade0146f77d1bb30013', '2014-07-02 22:31:49', '17909124407b8d7901407be4996c0008', 'phmsg', '202881e437d47b250137d481b6920002');

INSERT INTO validatelog VALUES('ff80808146f76ade0146f77d1bb30014', '2014-07-02 22:31:49', '17909124407b8d7901407be4996c0009', 'phmsg', '202881e437d47b250137d481b6920002');
INSERT INTO validatelog VALUES('ff80808146f76ade0146f784d6d40016', '2014-07-04 22:40:16', '17909124407b8d7901407be4996c0009', 'phmsg', '202881e437d47b250137d481b6920002');

--validatecodelog
insert into validatecodelog values('302881e437d47b250137d481b6920001','test001','17909124407b8d7901407be4996c0008');
insert into validatecodelog values('302881e437d47b250137d481b6920002','test002','17909124407b8d7901407be4996c0009');


--WIFIUSERGROUP
--402881e437d47b250137d481b6920002','B' no user
insert into WIFIUSERGROUP values('402881e437d47b250137d481b6920001','A','17909124407b8d7901407be4996c0008');
insert into WIFIUSERGROUP values('402881e437d47b250137d481b6920002','B','17909124407b8d7901407be4996c0008');

insert into WIFIUSERGROUP values('402881e437d47b250137d481b6920007','C','17909124407b8d7901407be4996c0009');
insert into WIFIUSERGROUP values('402881e437d47b250137d481b6920008','D','17909124407b8d7901407be4996c0009');

--WIFIUSERGROUP_WIFIUSER
insert into WIFIUSERGROUP_WIFIUSER values('402881e437d47b250137d481b6920001','202881e437d47b250137d481b6920001');
insert into WIFIUSERGROUP_WIFIUSER values('402881e437d47b250137d481b6920001','202881e437d47b250137d481b6920002');


insert into WIFIUSERGROUP_WIFIUSER values('402881e437d47b250137d481b6920007','202881e437d47b250137d481b6920002');

-- shop admin end
