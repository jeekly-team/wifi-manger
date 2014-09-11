/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.entity.shop;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.jyzn.wifi.entity.IdEntity;

@Entity
@Table(name = "WIFIUSERGROUP_WIFIUSER")
public class WifiUserGroupWifiUser extends IdEntity {

    //分组ID
    private String fKGroupId;
    //用户ID
    private String fKUserId;
    
	public String getfKGroupId() {
		return fKGroupId;
	}
	public void setfKGroupId(String fKGroupId) {
		this.fKGroupId = fKGroupId;
	}
	public String getfKUserId() {
		return fKUserId;
	}
	public void setfKUserId(String fKUserId) {
		this.fKUserId = fKUserId;
	}
    
    
}
