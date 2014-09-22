/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.shop.dao;

import com.jyzn.wifi.dao.shop.WifiUserDao;
import com.jyzn.wifi.entity.shop.WifiUser;
import com.jyzn.wifi.test.manager.ManagerTestCaseSupport;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
public class WifiUserDaoTest extends ManagerTestCaseSupport {

    @Autowired
    private WifiUserDao wifiUserDao;

    
    @Before
    @Override
    public void install() throws Exception {
        executeScript(dataSource, "classpath:data/h2/h2-jy-wifi-data.sql");      
    }    
    @Test
    @Transactional("transactionManager")
    public void getAllUser() {
        List<WifiUser> list = wifiUserDao.getAllUser();
        System.out.print(list);
    }

}
