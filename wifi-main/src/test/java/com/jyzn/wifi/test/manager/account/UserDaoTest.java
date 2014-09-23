/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.manager.account;

import com.jyzn.wifi.dao.account.UserDao;
import com.jyzn.wifi.entity.account.User;
import com.jyzn.wifi.test.manager.ManagerTestCaseSupport;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zyt
 */
public class UserDaoTest extends ManagerTestCaseSupport {

    //用户数据访问
    @Autowired
    private UserDao userDao;

    @Test
    @Transactional("transactionManager")
    public void pwdTest() {
      //userDao.updatePassword("SJDK3849CKMS3849DJCK2039ZMSK0001","654321");
        //System.out.print("影响的行数:" + c + "\n");
        User user = userDao.get("SJDK3849CKMS3849DJCK2039ZMSK0001");
        System.out.print(user.getPassword() + "\n");

    }

    @Test
    @Transactional("transactionManager")
    public void update() {
        User user_befor = userDao.load("SJDK3849CKMS3849DJCK2039ZMSK0001");
        user_befor.setPassword("654321");
        userDao.update(user_befor);
        User user_after = userDao.load("SJDK3849CKMS3849DJCK2039ZMSK0001");
        System.out.print(user_befor.getPassword() + "\n");
        assertNotEquals(user_befor.getPassword(), "e10adc3949ba59abbe56e057f20f883e");

    }
}
