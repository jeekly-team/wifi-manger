/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.shop.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyzn.wifi.dao.account.UserDao;
import com.jyzn.wifi.dao.shop.ValidateLogJpaDao;
import com.jyzn.wifi.dao.shop.WifiUserGroupDao;
import com.jyzn.wifi.entity.account.User;
import com.jyzn.wifi.test.manager.ManagerTestCaseSupport;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
public class ValidateLogJpaDaoTest extends ManagerTestCaseSupport {

    @Autowired
    private ValidateLogJpaDao log;
    @Autowired
    private UserDao userDao;
    private final ObjectMapper om = new ObjectMapper();

    @Before
    @Override
    public void install() throws Exception {
        executeScript(dataSource, "classpath:data/h2/h2-jy-wifi-data.sql");      
    }

    @Test
    @Transactional("jpaTransactionManager")
    public void getLastLogCountPageTest() throws IOException {
        Page p = log.findWifiUserCountBySid("test", new PageRequest(0, 10));
        om.writeValue(System.out, p);
        System.out.print("\n");

    }

}
