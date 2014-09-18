/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.shop.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyzn.wifi.dao.shop.ValidateLogDao;
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


    }

}
