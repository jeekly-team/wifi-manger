/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.service.shop;


import com.jyzn.wifi.dao.shop.WifiUserDao;
import com.jyzn.wifi.entity.shop.WifiUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class WifiUserServices {
    
    @Autowired
    private WifiUserDao wifiUserDao;
    
    public List<WifiUser> getAllUser(){
        return wifiUserDao.getAllUser();
    }
    
}
