/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.entity.login.log;


import com.jyzn.wifi.entity.IdEntity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  登陆日志实体类
 * @author Administrator
 */
@Entity
@Table(name="LOGIN_LOG_VIEW")
public class LoginLog extends IdEntity {
    
    //登陆用户名
    private String userName;
    //登陆类型
    private String type;
    //登陆日期
    private Date lgoinDate;
    //登陆类型描述
    private String description;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return the date
     */
    public Date getDate() {
        return lgoinDate;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.lgoinDate = date;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
