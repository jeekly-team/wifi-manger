/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.dao.shop;

import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;
import com.jyzn.wifi.entity.shop.WifiUser;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class WifiUserDao extends HibernateSupportDao<WifiUser, String> {

    public List<WifiUser> getAllUser() {
        //String sql = "SELECT ID, NAME FROM WIFIUSER ";
        //return getAll(new Order);
        String hql = "from WifiUser";
        return createQuery(hql).list();
    }
    /*
    public List<WifiUserCount> getWifiUserCount(WifiUser user) {
        String hql = "select new com.jyzn.wifi.entity.shop.summary.WifiUserCount(l.wifiuser,count(l), max(l.dt)) from WifiUser u "
            + "left join u.log l on l.wifiuser=u where l.wifiuser = ?1 group by u";        
        return distinct(hql,user);
    }
    */
    
    
}
