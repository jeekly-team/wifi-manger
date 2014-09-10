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

select u.id,u.name,count(l.ID),max(l.dt) maxdt from validatelog l
join wifiuser u  on l.WIFIUSER_ID=u.ID
group by l.WIFIUSER_ID
Order by maxdt desc 


select u.id,u.name,count(l.ID),max(l.dt) md from wifiuser u
left join  validatelog l on l.WIFIUSER_ID=u.ID
group by u.name
Order by md desc 

select u.name,l.WIFIUSER_ID,count(l.id),max(l.dt) md from validatelog l left outer join wifiuser u on u.id=l.WIFIUSER_ID group by l.WIFIUSER_ID

select * from validatelog



select num,dt, count(1) from(select t.wifiuser_id,t.dt,t1.num from 
(select wifiuser_id,to_char(dt, 'yyyy-mm-dd') as dt from validatelog group by wifiuser_id,to_char(dt, 'yyyy-mm-dd')) t --获取时间段内访问的用户
 left join (select count(1) as num ,wifiuser_id from validatelog group by wifiuser_id) t1 --统计老用户
on  t.wifiuser_id = t1.wifiuser_id) group by num,dt



