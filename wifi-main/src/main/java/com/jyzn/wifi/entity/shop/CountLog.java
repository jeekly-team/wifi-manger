/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.entity.shop;

import com.github.dactiv.orm.annotation.TreeEntity;
import java.io.Serializable;
import javax.persistence.Entity;

import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Administrator
 */
@Table(name="VALIDATELOG")
@NamedQuery(name="countLog",query="select dt,num, (case when count(1) = 1 then 'new' else 'old' end) as type from(select t.wifiuser_id,t.dt,t1.num from " +
                                "(select wifiuser_id,to_char(dt, 'yyyy-mm-dd') as dt from validatelog where dt >= cast(? as dateTime) and dt <= cast(? as dateTime) group by wifiuser_id,to_char(dt, 'yyyy-mm-dd')) t " +
                                " left join (select count(1) as num ,wifiuser_id from validatelog where dt <= cast(? as dateTime) group by wifiuser_id) t1 " +
                                "on  t.wifiuser_id = t1.wifiuser_id) group by num,dt")
public class CountLog implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String date;
    
    private int count;
    
    private String type;

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
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
 
}
