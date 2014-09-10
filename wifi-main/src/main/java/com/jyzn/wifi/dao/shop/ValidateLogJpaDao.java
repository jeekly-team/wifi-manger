/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.dao.shop;

import com.jyzn.wifi.entity.shop.ValidateLog;
import com.jyzn.wifi.entity.shop.summary.WifiUserCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Administrator
 */
public interface ValidateLogJpaDao extends Repository<ValidateLog, String> {
    @Query("select new com.jyzn.wifi.entity.shop.summary.WifiUserCount(l.wifiuser,count(l),max(l.dt)) from ValidateLog as l group by l.wifiuser")
    Page<WifiUserCount> findLastLogCountPage(Pageable pageable);
}
