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

select u.id,u.name,count(l.ID),max(l.dt) maxdt from validatelog l
join wifiuser u  on l.WIFIUSER_ID=u.ID
group by l.WIFIUSER_ID
Order by maxdt desc 

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


select u.id,u.name,count(l.ID) c ,max(l.dt) md from wifiuser u
left join  validatelog l on l.WIFIUSER_ID=u.ID
group by u.name

select count(*) from (select distinct u.name,l.WIFIUSER_ID,count(l.id),max(l.dt) maxdt,min(l.dt) mindt from validatelog l
 left outer join wifiuser u on u.id=l.WIFIUSER_ID 
where l.SID='test'
group by l.WIFIUSER_ID)





select num,dt, count(1) from(select t.wifiuser_id,t.dt,t1.num from 
(select wifiuser_id,to_char(dt, 'yyyy-mm-dd') as dt from validatelog group by wifiuser_id,to_char(dt, 'yyyy-mm-dd')) t --获取时间段内访问的用户
 left join (select count(1) as num ,wifiuser_id from validatelog group by wifiuser_id) t1 --统计老用户
on  t.wifiuser_id = t1.wifiuser_id) group by num,dt



