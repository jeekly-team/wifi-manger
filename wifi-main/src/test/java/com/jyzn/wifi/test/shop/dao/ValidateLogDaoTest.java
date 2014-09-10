/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.shop.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dactiv.orm.core.Page;
import com.github.dactiv.orm.core.PageRequest;
import com.jyzn.wifi.dao.shop.ValidateLogDao;
import com.jyzn.wifi.entity.shop.summary.WifiUserCount;
import com.jyzn.wifi.test.manager.ManagerTestCaseSupport;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
public class ValidateLogDaoTest extends ManagerTestCaseSupport {

    @Autowired
    private ValidateLogDao log;

    private final ObjectMapper om = new ObjectMapper();

    @Test
    @Transactional("transactionManager")
    public void getLastLogCountList() throws IOException {
        List list = log.getLastLogCount();
        om.writeValue(System.out, list);
        System.out.print("\n");
    }

    @Test
    @Transactional("transactionManager")
    public void getLastLogCountPageTest() throws IOException {
        Page<WifiUserCount> p = log.getLastLogCountPage(new PageRequest(0, 10));
        //List<WifiUserCount> p = log.getLastLogCountPage();
        om.writeValue(System.out, p);
        System.out.print("\n");

    }

}
