/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.web.shop;


import com.jyzn.wifi.entity.shop.ValidateLog;
import com.jyzn.wifi.entity.shop.WifiUser;
import com.jyzn.wifi.service.shop.ValidateLogServices;
import com.jyzn.wifi.service.shop.WifiUserServices;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
public class AdminController {
    
    
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
     
    @Autowired
    private WifiUserServices wifiUserServices;
    
    @Autowired
    private ValidateLogServices validateLogServices;

    
    @RequestMapping("/admin")
    public String adminPage(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");
        return page;
        //return "admin/rzfs1";
    }
    
    /**
     * 获取所有用户
     * @return 
     */
    @RequestMapping("/allUser")
    @ResponseBody
    public List<WifiUser> allUser(){
        logger.info("get all user");
        return wifiUserServices.getAllUser();
    }
    
    @RequestMapping("validateLogDate")
    @ResponseBody
    public List<ValidateLog> getLogByDate(HttpServletRequest request, HttpServletResponse response){
        try {
            String startDate_str = (String) request.getParameter("startDate") + " 00:00:00";
            String endDate_str = (String) request.getParameter("endDate") + " 23:59:59";
            return validateLogServices.getValidateLogByDate(startDate_str, endDate_str);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    @RequestMapping("countLog")
    @ResponseBody
    public List<ValidateLog> getCountLog(HttpServletRequest request, HttpServletResponse response){
        return validateLogServices.countLog();
    }
    
    @RequestMapping("countLogUser")
    @ResponseBody
    public List getCountLogUser(HttpServletRequest request, HttpServletResponse response){
        String startDate_str = (String) request.getParameter("startDate") + " 00:00:00";
        String endDate_str = (String) request.getParameter("endDate") + " 23:59:59";
        return validateLogServices.getCountLog(startDate_str,endDate_str);
    }
    
    @RequestMapping("countAllUser")
    @ResponseBody
    public List getCountAllUser(HttpServletRequest request, HttpServletResponse response){
        return validateLogServices.getCountAllUserLog();
    }
    
    @RequestMapping("centumByType")
    @ResponseBody
    public List getCentumByType(HttpServletRequest request, HttpServletResponse response){
        String type = (String) request.getParameter("type");
        return validateLogServices.getCentumByType(type);
    }
    
    @RequestMapping("validateLogByCount")
    @ResponseBody
    public List getValidateLogByCount(HttpServletRequest request, HttpServletResponse response){
        int count =  Integer.parseInt(request.getParameter("count"));
        String type = (String) request.getParameter("type");
        int page = Integer.parseInt(request.getParameter("page"));
        int pagesize = Integer.parseInt(request.getParameter("pagesize"));
        return validateLogServices.getValidateLogByCount(count, type, page, pagesize);
    }
    
    @RequestMapping("validateLogByCountSize")
    @ResponseBody
    public int getValidateLogByCountSize(HttpServletRequest request, HttpServletResponse response){
        int count =  Integer.parseInt(request.getParameter("count"));
        String type = (String) request.getParameter("type");
        return validateLogServices.getMaxValidateLogByCount(count, type);
    }
    
    @RequestMapping("loadAVGCount")
    @ResponseBody
    public List getAvgCount(){
        return validateLogServices.getAvgCount();
    }
    
    @RequestMapping("getActiveUser")
    @ResponseBody
    public List getActiveUser(HttpServletRequest request, HttpServletResponse response){
        String startDate_str = (String) request.getParameter("startDate") + " 00:00:00";
        String endDate_str = (String) request.getParameter("endDate") + " 23:59:59";
        return validateLogServices.getActiveUser(startDate_str, endDate_str);
    }
}
