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
    /**
     * 获取验证日志表中所有数据
     * @return 
     */
    public List<ValidateLog> countLog(){
        return validateLogDao.getAllCountLog();
    }
    /**
     * 通过实践段获取统计日志信息
     * @param startDate
     * @param endDate
     * @return 
     */
    public List getCountLog(String startDate, String endDate){
       return countValidtaLogDao.getCountUser(startDate, endDate);
    }
    
    /**
     * 获取验证日志中的统计信息
     * @return 
     */
    public List getCountAllUserLog(){
       return countValidtaLogDao.getCountUserTT();
    }
    
    /**
     * 通过类型获取不同回访次数所占比例及次数
     * @param type
     * @return 
     */
    public List getCentumByType(String type){
        return countValidtaLogDao.getCentumByType(type);
    }
    
    /**
     * 获取日志分页结果集
     * @param count
     * @param type
     * @param page
     * @param pagesize
     * @return 
     */
    public List getValidateLogByCount(int count, String type, int page, int pagesize){
        return countValidtaLogDao.getValidateLogByCount(count, type, page, pagesize);
    }
    
    /**
     * 获取日志分页记录数目
     * @param count
     * @param type
     * @return 
     */
    public int getMaxValidateLogByCount(int count, String type){
        return countValidtaLogDao.getMaxValidateLogByCount(count, type);
    }
    
    /**
     * 获取用户平均来店次数和最近三十天来店次数
     * @return 
     */
    public List getAvgCount(){
        return countValidtaLogDao.getAvgCount();
    }
    
    public List getActiveUser(String startDate, String endDate){
        return countValidtaLogDao.getActiveUser(startDate, endDate);
    }
    
}
