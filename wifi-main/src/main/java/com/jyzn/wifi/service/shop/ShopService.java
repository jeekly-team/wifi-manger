/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.service.shop;

import com.jyzn.wifi.dao.shop.ValidateLogDao;
import com.jyzn.wifi.dao.shop.WifiUserDao;
import com.jyzn.wifi.dao.shop.WifiUserGroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopService {

    @Autowired
    private WifiUserDao userDao;

    @Autowired
    private WifiUserGroupDao userGroupDao;

    @Autowired
    private ValidateLogDao logDao;

    
    
    
    
    
}
