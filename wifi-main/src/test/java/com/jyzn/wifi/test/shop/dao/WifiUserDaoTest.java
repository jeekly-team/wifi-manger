/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.shop.dao;

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
public class WifiUserDaoTest extends ManagerTestCaseSupport {

    @Autowired
    private WifiUserDao wifiUserDao;
    @Autowired 
    private CountValidataLogDao countValidateLogDao; 

    @Test
    @Transactional("transactionManager")
    public void getAllUser() {
//        List list = countValidateLogDao.getValidateLogByCount(1, "wx", 0, 5);
//        int s = countValidateLogDao.getMaxValidateLogByCount(1, "wx");
//        System.out.print(list);
    }
    
    @Test
    @Transactional("transactionManager")
    public void getAVGCount(){
        List list = countValidateLogDao.getAvgCount();
    }

    @Test
    @Transactional("transactionManager")
    public void getActiveUser(){
        List list = countValidateLogDao.getActiveUser("2014-09-01 00:00:00", "2014-09-11 00:00:00");
    }
}
