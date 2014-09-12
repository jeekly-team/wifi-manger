/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.entity.shop.summary;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date maxdt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date mindt;
    private WifiUser user;

    public WifiUserCount() {

    }

    public WifiUserCount(WifiUser user, long count, Date maxdt, Date mindt) {
        this.count = count;
        this.user = user;
        this.maxdt = maxdt;
        this.mindt = mindt;
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

    public Date getMindt() {
        return mindt;
    }

    public void setMindt(Date mindt) {
        this.mindt = mindt;
    }

}
