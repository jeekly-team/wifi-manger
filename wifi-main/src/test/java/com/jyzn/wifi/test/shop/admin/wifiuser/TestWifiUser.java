/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.shop.admin.wifiuser;

import com.jyzn.wifi.dao.shop.CountValidataLogDao;
import com.jyzn.wifi.dao.shop.WifiUserDao;
import com.jyzn.wifi.entity.shop.WifiUser;
import com.jyzn.wifi.test.manager.ManagerTestCaseSupport;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
public class TestWifiUser extends ManagerTestCaseSupport {

    @Autowired
    private WifiUserDao wifiUserDao;

    @Autowired
    private CountValidataLogDao countValidateDao;
    
    @Test
    @Transactional
    public void getAllUser() {
       List list = countValidateDao.getCentumByType("wx");
        System.out.print(list);

    }

}
