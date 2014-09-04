/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.service.shop;



import com.jyzn.wifi.dao.shop.CountValidataLogDao;
import com.jyzn.wifi.dao.shop.ValidateLogDao;
import com.jyzn.wifi.entity.shop.ValidateLog;
import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class ValidateLogServices {
    
    @Autowired
    private ValidateLogDao validateLogDao;
    
    @Autowired
    CountValidataLogDao countValidtaLogDao;
    /**
     * 通过时间获取该时间段中所有验证日志
     * @param beginDate
     * @param endDate
     * @return 
     */
    public List<ValidateLog> getValidateLogByDate(String beginDate, String endDate){
        return validateLogDao.getCountByDate(beginDate, endDate);
    }
    
    public List<ValidateLog> countLog(){
        return validateLogDao.getAllCountLog();
    }
    
    public SQLQuery getCountLog(){
       return countValidtaLogDao.getOldUserCount();
    }
    
}
