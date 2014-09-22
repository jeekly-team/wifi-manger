--字典分类
select * from tb_dictionary_category dc
--字典
select * from tb_data_dictionary dd
SELECT dd.*,dc.code,dc.name FROM tb_data_dictionary dd left join tb_dictionary_category dc on dc.id=dd.fk_category_id

--dd.code in (1,2)  都是'resource-type'
SELECT r.* from tb_resource r
left join tb_data_dictionary dd on r.type=dd.value
left join tb_dictionary_category dc on dc.id=dd.fk_category_id
where dc.code='resource-type' and dd.value='01'
Order by r.SORT

SELECT r.* from tb_resource r Order by r.SORT

--资源与组

--某用户组拥有的权限
select * from tb_group_resource grr

select grr.FK_RESOURCE_ID ,r.name,grr.FK_GROUP_ID,gr.name from tb_group_resource grr 
left join tb_group gr on gr.ID=grr.FK_GROUP_ID
left join tb_resource r on r.ID=grr.FK_RESOURCE_ID
where gr.ID in ('402881c4408c7d2301408c870ed10003')

select * from tb_group gr
--用户与组
select * from tb_user u where u.ID='SJDK3849CKMS3849DJCK2039ZMSK0001'

select * from tb_group_user

select gu.FK_GROUP_ID ,gr.name,gu.FK_USER_ID,u.USERNAME from tb_group_user  gu left join tb_group gr on gr.ID=gu.FK_GROUP_ID left join tb_user u on u.ID=gu.FK_USER_ID