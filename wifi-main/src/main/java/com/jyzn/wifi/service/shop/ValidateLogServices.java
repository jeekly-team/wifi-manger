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
     * @param sid
     * @return 
     */
    public List<ValidateLog> getValidateLogByDate(String beginDate, String endDate, String sid){
        return validateLogDao.getCountByDate(beginDate, endDate, sid);
    }
    /**
     * 获取验证日志表中所有数据
     * @param sid
     * @return 
     */
    public List<ValidateLog> countLog(String sid){
        return validateLogDao.getAllCountLog(sid);
    }
    /**
     * 通过实践段获取统计日志信息
     * @param startDate
     * @param endDate
     * @param sid
     * @return 
     */
    public List getCountLog(String startDate, String endDate,String sid){
       return countValidtaLogDao.getCountUser(startDate, endDate, sid);
    }
    
    /**
     * 获取验证日志中的统计信息
     * @param sid
     * @return 
     */
    public List getCountAllUserLog(String sid){
       return countValidtaLogDao.getCountUserTT(sid);
    }
    
    /**
     * 通过类型获取不同回访次数所占比例及次数
     * @param type
     * @param sid
     * @return 
     */
    public List getCentumByType(String type, String sid){
        return countValidtaLogDao.getCentumByType(type, sid);
    }
    
    /**
     * 获取日志分页结果集
     * @param count
     * @param type
     * @param page
     * @param pagesize
     * @param sid
     * @return 
     */
    public List getValidateLogByCount(int count, String type, int page, int pagesize, String sid){
        return countValidtaLogDao.getValidateLogByCount(count, type, page, pagesize, sid);
    }
    
    /**
     * 获取日志分页记录数目
     * @param count
     * @param type
     * @param sid
     * @return 
     */
    public int getMaxValidateLogByCount(int count, String type, String sid){
        return countValidtaLogDao.getMaxValidateLogByCount(count, type, sid);
    }
    
    /**
     * 获取用户平均来店次数和最近三十天来店次数
     * @param sid
     * @return 
     */
    public List getAvgCount(String sid){
        return countValidtaLogDao.getAvgCount(sid);
    }
    
    public List getActiveUser(String startDate, String endDate, String sid){
        return countValidtaLogDao.getActiveUser(startDate, endDate, sid);
    }
    
}
