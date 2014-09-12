/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.shop.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyzn.wifi.dao.shop.ValidateLogJpaDao;
import com.jyzn.wifi.test.manager.ManagerTestCaseSupport;
import java.io.IOException;
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
    private final ObjectMapper om = new ObjectMapper();
    
    @Test
    @Transactional("jpaTransactionManager")
    public void getLastLogCountPageTest() throws IOException {
        Page p = log.findWifiUserCountBySid("test",new PageRequest(0, 10));
        om.writeValue(System.out, p);
        System.out.print("\n");

    }


}
