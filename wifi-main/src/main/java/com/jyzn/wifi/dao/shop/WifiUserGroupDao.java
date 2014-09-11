/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.dao.shop;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;
import com.jyzn.wifi.entity.shop.WifiUserGroup;

/**
 *
 * @author Administrator
 */
@Repository
public class WifiUserGroupDao extends HibernateSupportDao<WifiUserGroup, String>{
	
    public List<WifiUserGroup> listWifiUserGroup(String groupName) {
        String sql = "select * from WIFIUSERGROUP";
        if (null != groupName && !"".equals(groupName)) {
        	sql += " where name like '%" + groupName + "%'";
        }
        List<WifiUserGroup>list = createSQLQuery(sql).list();
        return list;
    }
    
    public WifiUserGroup getById(String id){
        WifiUserGroup wifiUserGroup = findUniqueByProperty("id", id);
        return wifiUserGroup;
    }
    
    public void deleteById(String id){
    	delete(id);
    }
    
    public void updateById(String id, String name){
    	WifiUserGroup entity = new WifiUserGroup();
    	entity.setId(id);
    	entity.setName(name);
    	
    	update(entity);
    }
    
    public void save(String name){
    	WifiUserGroup entity = new WifiUserGroup();
    	entity.setName(name);
    	
    	save(entity);
    }
}
