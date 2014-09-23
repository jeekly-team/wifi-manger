/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.service.shop;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.dactiv.orm.core.RestrictionNames;
import com.jyzn.wifi.dao.shop.WifiUserGroupDao;
import com.jyzn.wifi.dao.shop.WifiUserGroupWifiUserDao;
import com.jyzn.wifi.entity.shop.WifiUserGroup;
import com.jyzn.wifi.entity.shop.WifiUserGroupWifiUser;

/**
 *
 * @author WangLiang
 */
@Service
@Transactional
public class WifiUserGroupServices {

    @Autowired
    private WifiUserGroupDao wifiUserGroupDao;
    @Autowired
    private WifiUserGroupWifiUserDao wifiUserGroupWifiUserDao;
    
    public List<WifiUserGroup> listWifiUserGroup(String groupName){
    	if (StringUtils.isEmpty(groupName)) {
    		return wifiUserGroupDao.getAll(null);
    	}
        return wifiUserGroupDao.findByProperty("name", "%" + groupName + "%", RestrictionNames.LIKE, null);
    }
    
    public WifiUserGroup getById(String id){
		WifiUserGroup wifiUserGroup = wifiUserGroupDao.get(id);
	    return wifiUserGroup;
    }
    
    @Transactional("transactionManager")
    public void delete(String userGroupId){
    	//删除关联关系
    	WifiUserGroupWifiUser entity = new WifiUserGroupWifiUser();
    	entity.setfKGroupId(userGroupId);
    	wifiUserGroupWifiUserDao.delete(entity);
    	
    	//删除分组
    	delete(userGroupId);
    }
    
    public void updateById(String id, String name){
    	WifiUserGroup entity = new WifiUserGroup();
    	entity.setId(id);
    	entity.setName(name);
    	
    	wifiUserGroupDao.update(entity);    	
    }
    
    public void save(String name){
    	WifiUserGroup entity = new WifiUserGroup();
    	entity.setName(name);
    	
    	wifiUserGroupDao.save(entity);    	
    }
}
