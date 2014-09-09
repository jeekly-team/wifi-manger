/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.entity.shop;

import com.jyzn.wifi.entity.IdEntity;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "WIFIUSER")
public class WifiUser extends IdEntity {

    @Column(name = "name", unique = true, nullable = false, updatable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wifiuser")
    private Set<ValidateLog> log;

    public WifiUser() {
    }

    public WifiUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
