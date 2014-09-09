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





