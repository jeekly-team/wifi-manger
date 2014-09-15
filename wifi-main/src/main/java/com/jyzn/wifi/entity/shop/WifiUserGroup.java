/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.entity.shop;

import com.jyzn.wifi.entity.IdEntity;
import com.jyzn.wifi.entity.account.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WIFIUSERGROUP")
public class WifiUserGroup extends IdEntity {

    //名称
    private String name;
    //成员
    private List<WifiUser> membersList = new ArrayList<WifiUser>();
    // 商户


    public WifiUserGroup() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "WIFIUSERGROUP_WIFIUSER", joinColumns = { @JoinColumn(name = "FK_GROUP_ID")}, inverseJoinColumns = {@JoinColumn(name = "FK_USER_ID")})
    public List<WifiUser> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<WifiUser> membersList) {
        this.membersList = membersList;
    }

}
