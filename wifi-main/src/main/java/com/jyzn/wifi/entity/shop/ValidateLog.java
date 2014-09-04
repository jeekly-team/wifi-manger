/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.entity.shop;

import com.jyzn.wifi.entity.IdEntity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "validatelog")
public class ValidateLog extends IdEntity{
    
    private Date dt;
    
    private String sid;
    
    private String type;
    
    private String wifiuser_id;

    /**
     * @return the dt
     */
    public Date getDt() {
        return dt;
    }

    /**
     * @param dt the dt to set
     */
    public void setDt(Date dt) {
        this.dt = dt;
    }

    /**
     * @return the sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the wifiuser_id
     */
    public String getWifiuser_id() {
        return wifiuser_id;
    }

    /**
     * @param wifiuser_id the wifiuser_id to set
     */
    public void setWifiuser_id(String wifiuser_id) {
        this.wifiuser_id = wifiuser_id;
    }
    
}
