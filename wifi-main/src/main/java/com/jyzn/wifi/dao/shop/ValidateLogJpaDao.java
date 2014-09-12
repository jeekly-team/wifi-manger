/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.dao.shop;


import com.jyzn.wifi.entity.shop.ValidateLog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ValidateLogJpaDao extends JpaRepository<ValidateLog, String>, JpaSpecificationExecutor<ValidateLog> {

    @Query("select new com.jyzn.wifi.entity.shop.summary.WifiUserCount(l.wifiuser,count(l),max(l.dt),min(l.dt)) from ValidateLog as l "
            + " where l.sid=:sid"
            + " group by l.wifiuser")
    Page findWifiUserCountBySid(@Param("sid") String sid, Pageable pageable);

}
