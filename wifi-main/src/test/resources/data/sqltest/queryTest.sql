/*
select c_customer,sum(1) as  c_customercount
from t_table
where c_date>=开始日期 and c_date<=结束日期 group by c_customer
-- ---------------------------------
select c_customer,count(c_customer) as  c_customercount
from t_table
where c_date>=开始日期 and c_date<=结束日期 group by c_customer
*/
select * from wifiuser
/*
如果只是比较varchar类型的时间值与日期类型的值比较
select cast('2014-10-20'as datetime) as d1,'2015-1-2' as d2 from dual having d2>d1
会自动转换varchar为datetime类型（2009-2-27转换为2009-02-27），并进行比较，
*/

select cast('2014-10-20'as datetime) as d1,'2015-1-2' as d2 from dual having d2>d1
select cast('2014-7-1' as datetime) from dual



select c.*,wgp.name ,wu.name from wifiusergroup_wifiuser c 
left join wifiusergroup wgp on wgp.ID=c.FK_GROUP_ID
left join wifiuser wu on wu.ID=c.FK_USER_ID
where c.FK_GROUP_ID = '402881e437d47b250137d481b6920003'

select * from wifiusergroup  wgp where wgp.SYSUSER_ID='SJDK3849CKMS3849DJCK2039ZMSK0001'

select B.id,B.name,count(A.WIFIUSER_ID),max(A.dt) maxdt from validatelog A 
    left join wifiuser B on  B.ID=A.WIFIUSER_ID 
    left join wifiusergroup_wifiuser C on C.FK_USER_ID= B.ID
    where A.SID = 'SJDK3849CKMS3849DJCK2039ZMSK0001' and C.FK_GROUP_ID = '402881e437d47b250137d481b6920001'  group by A.WIFIUSER_ID


select  D.name,B.id,B.name,count(A.WIFIUSER_ID),max(A.dt) maxdt from validatelog A 
    left join wifiuser B on  B.ID=A.WIFIUSER_ID 
    left join wifiusergroup_wifiuser C on C.FK_USER_ID= B.ID
    left join wifiusergroup D on C.FK_GROUP_ID = D.ID 
    where A.SID = '17909124407b8d7901407be4996c0004' and D.ID = '402881e437d47b250137d481b6920007'  group by A.WIFIUSER_ID


select u.id,u.name,count(l.ID),max(l.dt) maxdt,tu.USERNAME from validatelog l
left join wifiuser u  on u.ID=l.WIFIUSER_ID
left join tb_user tu on tu.id=l.SID
where l.DT>=cast('2014-07-01' as datetime) and l.DT<=cast ('2014-09-10' as datetime) and l.SID='SJDK3849CKMS3849DJCK2039ZMSK0001'
group by l.WIFIUSER_ID
Order by maxdt desc 

 


select gp.*,su.USERNAME from WIFIUSERGROUP gp 
left join tb_user su on su.ID=gp.SYSUSER_ID
where gp.SYSUSER_ID='SJDK3849CKMS3849DJCK2039ZMSK0001'
and gp.ID='402881e437d47b250137d481b6920001'






select u.id,u.name,count(l.ID) c,max(l.dt) maxdt from validatelog l
join wifiuser u  on l.WIFIUSER_ID=u.ID
where l.DT>='2014-07-01' and l.DT<='2014-09-10' and l.SID='SJDK3849CKMS3849DJCK2039ZMSK0001'
group by l.WIFIUSER_ID
HAVING c>=0

select count(l.ID) c,max(l.dt) maxdt from validatelog l
where l.DT>=cast('2014-07-01' as datetime) and l.DT<=cast ('2014-09-10' as datetime) 
group by l.WIFIUSER_ID
HAVING c>=2


select distinct * from validatelog l where l.DT>=cast('2014-07-01' as datetime) and l.DT<=cast ('2014-09-10' as datetime) and l.SID='SJDK3849CKMS3849DJCK2039ZMSK0001'


select u.id,u.name,count(l.ID) c ,max(l.dt) md from wifiuser u
left join  validatelog l on l.WIFIUSER_ID=u.ID
group by u.name
Order by md desc 


select count(*) from (select distinct u.name,l.WIFIUSER_ID,count(l.id),max(l.dt) maxdt,min(l.dt) mindt from validatelog l
 left outer join wifiuser u on u.id=l.WIFIUSER_ID 
where l.SID='test'
group by l.WIFIUSER_ID)

---------------纯手写

select  D.name,B.id,B.name,count(A.WIFIUSER_ID),max(A.dt) maxdt from validatelog A 
    left join wifiuser B on  B.ID=A.WIFIUSER_ID 
    left join wifiusergroup_wifiuser C on C.FK_USER_ID= B.ID
    left join wifiusergroup D on C.FK_GROUP_ID = D.ID 
    where A.SID = '17909124407b8d7901407be4996c0004' and D.ID = '402881e437d47b250137d481b6920007'  group by A.WIFIUSER_ID

-------根据测试改造手工的sql
select  D.name,B.id,B.name,count(A.WIFIUSER_ID),max(A.dt) maxdt from validatelog A 
    inner join wifiuser B on  B.ID=A.WIFIUSER_ID 
    left join wifiusergroup_wifiuser C on C.FK_USER_ID= B.ID
     join wifiusergroup D  on C.FK_GROUP_ID = D.ID 
    where A.SID = 'SJDK3849CKMS3849DJCK2039ZMSK0001' and D.ID = '402881e437d47b250137d481b6920001'  group by A.WIFIUSER_ID
-----------------------------------------test 
    select gr.name,l.wifiuser_id , count(l.id),max(l.dt),min(l.dt) from validatelog l 
    inner join WIFIUSER wu on l.wifiuser_id=wu.id cross
    join wifiusergroup gr 
    left outer join  WIFIUSERGROUP_WIFIUSER ml on  ml.FK_USER_ID=wu.id
    --left outer join  WIFIUSER gr_user on ml.FK_USER_ID=gr_user.id 
    where  l.SID='SJDK3849CKMS3849DJCK2039ZMSK0001' and gr.ID='402881e437d47b250137d481b6920001' --and gr.ID=ml.FK_GROUP_ID
    group by
        l.wifiuser_id 
--------------------SNAPSHOT
    select gr.name,wu.name,l.wifiuser_id , count(l.id),max(l.dt),min(l.dt) from validatelog l 
    inner join WIFIUSER wu on l.wifiuser_id=wu.id cross
    join WIFIUSERGROUP gr 
    left outer join WIFIUSERGROUP_WIFIUSER ml on gr.id=ml.FK_GROUP_ID 
    left outer join WIFIUSER gr_user  on ml.FK_USER_ID=gr_user.id 
    where l.SID='SJDK3849CKMS3849DJCK2039ZMSK0001' and  gr.ID='402881e437d47b250137d481b6920001' and ml.FK_USER_ID=wu.id
    group by
        l.wifiuser_id 
-----------------------
--and ml.FK_USER_ID=wu.id
---------------------------release
   select distinct wu.name,l.wifiuser_id,count(l.id),max(l.dt),min(l.dt) from validatelog l 
    inner join WIFIUSER wu  on l.wifiuser_id=wu.id cross 
    join WIFIUSERGROUP gr 
    left outer join WIFIUSERGROUP_WIFIUSER ml  on gr.id=ml.FK_GROUP_ID 
    left outer join  WIFIUSER gr_user  on ml.FK_USER_ID=gr_user.id 
    where
        gr_user.id=l.wifiuser_id and l.SID='SJDK3849CKMS3849DJCK2039ZMSK0001' and  gr.ID='402881e437d47b250137d481b6920001'
        and l.dt>=cast('2014-07-01' as datetime) and l.dt<=cast('2014-08-01' as datetime)
    group by
        l.wifiuser_id 
-----------------------------final
   select  distinct l.wifiuser_id, count(l.id), max(l.dt),min(l.dt) from validatelog l 
    inner join  WIFIUSER wu  on l.wifiuser_id=wu.id cross 
    join WIFIUSERGROUP gr 
    left outer join  WIFIUSERGROUP_WIFIUSER ml  on gr.id=ml.FK_GROUP_ID 
    left outer join WIFIUSER gr_user on ml.FK_USER_ID=gr_user.id 
    where gr_user.id=l.wifiuser_id  and l.sid='SJDK3849CKMS3849DJCK2039ZMSK0001'  and gr.id='402881e437d47b250137d481b6920001'
    group by
        l.wifiuser_id 
    having
        count(l.id)>=0 