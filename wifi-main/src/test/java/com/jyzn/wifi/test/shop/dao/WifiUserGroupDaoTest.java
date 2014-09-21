/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.shop.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyzn.wifi.dao.shop.WifiUserGroupDao;
import com.jyzn.wifi.entity.account.User;
import com.jyzn.wifi.entity.shop.WifiUser;
import com.jyzn.wifi.entity.shop.WifiUserGroup;
import com.jyzn.wifi.test.manager.ManagerTestCaseSupport;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zyt
 */
public class WifiUserGroupDaoTest extends ManagerTestCaseSupport {

    @Autowired
    private WifiUserGroupDao groupdao;

    private final ObjectMapper om = new ObjectMapper();

    @Test
    @Transactional("transactionManager")
    public void Test1() throws IOException {

        User user = groupdao.get("402881e437d47b250137d481b6920001").getUser();
        List<?> gl = user.getGroupsList();
        assertNotEquals(gl.size(), 0);

        List<WifiUserGroup> wl = user.getWifiusergrouplist();
        assertNotEquals(wl.size(), 0);

        List<WifiUser> ml = wl.get(0).getMembersList();
        //om.writeValue(System.out, wl.get(0));

        System.out.print("\n");
    }

    @Test
    @Transactional("transactionManager")
    public void Test2() throws IOException {

        List<WifiUserGroup> wl = groupdao.findByProperty("user.id", "SJDK3849CKMS3849DJCK2039ZMSK0001");

        System.out.print("\n");
    }

}
