/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.test.shop.admin.wifiuser;

import com.jyzn.wifi.dao.shop.admin.wifiuser.WifiUserDao;
import com.jyzn.wifi.entity.shop.adimn.WifiUser;
import com.jyzn.wifi.test.founction.FunctionTestCaseSupport;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrator
 */
public class TestWifiUser extends FunctionTestCaseSupport{
    
    @Autowired
    WifiUserDao wifiUserDao;
    
    @Test
    public void getAllUser(){
        
        List<WifiUser> list = wifiUserDao.getAllUser();
        System.out.print(list);
        
    }
   
    
}
