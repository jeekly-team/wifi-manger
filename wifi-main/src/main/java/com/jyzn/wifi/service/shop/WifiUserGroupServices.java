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

import com.jyzn.wifi.dao.shop.WifiUserGroupDao;
import com.jyzn.wifi.dao.shop.WifiUserGroupWifiUserDao;
import com.jyzn.wifi.entity.shop.WifiUserGroup;

/**
 *
 * @author Administrator
 */
@Service
@Transactional("transactionManager")
public class WifiUserGroupServices {

    @Autowired
    private WifiUserGroupDao wifiUserGroupDao;
    @Autowired
    private WifiUserGroupWifiUserDao wifiUserGroupWifiUserDao;
    
    public List<WifiUserGroup> listWifiUserGroup(String groupName){
        return wifiUserGroupDao.listWifiUserGroup(groupName);
    }
    
    public WifiUserGroup getById(String id){
    	return wifiUserGroupDao.getById(id);
    }
    
    @Transactional("transactionManager")
    public void delete(String userGroupId){
    	//删除关联关系
    	wifiUserGroupWifiUserDao.deleteByUserGroupId(userGroupId);
    	//删除分组
    	wifiUserGroupDao.deleteById(userGroupId);
    }
    
    public void updateById(String id, String name){
    	wifiUserGroupDao.updateById(id, name);
    }
    
    public void save(String name){
    	wifiUserGroupDao.save(name);
    }
}
