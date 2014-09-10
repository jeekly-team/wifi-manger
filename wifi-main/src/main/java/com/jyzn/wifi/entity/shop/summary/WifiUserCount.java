/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.entity.shop.summary;

import com.jyzn.wifi.entity.shop.WifiUser;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author zyt
 */
public class WifiUserCount implements Serializable {

    private static final long serialVersionUID = 1L;

    private long count;
    private Date maxdt;
    private WifiUser user;

    
    public WifiUserCount(){
        
    }
    
    public WifiUserCount(WifiUser user, long count, Date maxdt) {
        this.count = count;
        this.user = user;
        this.maxdt = maxdt;
    }

    public long getCount() {
        return count;
    }

    public Date getMaxdt() {
        return maxdt;
    }

    public void setMaxdt(Date maxdt) {
        this.maxdt = maxdt;
    }

    public WifiUser getUser() {
        return user;
    }

}
