/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.web.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jyzn.wifi.common.annotation.OperatingAudit;
import com.jyzn.wifi.entity.shop.WifiUserGroup;
import com.jyzn.wifi.service.shop.WifiUserGroupServices;


@Controller
@OperatingAudit("商户会员分类")
@RequestMapping("/customer/group")
public class WifiUserGroupController {
     private static final Logger logger = LoggerFactory.getLogger(WifiUserGroupController.class);
     
     @Autowired
     private WifiUserGroupServices wifiUserGroupServices;
     
     /**
      * 列表查看
      * 
      * @param request
      * @param response
      * @return
      */
     @RequestMapping("/list")
     public String list(HttpServletRequest request, HttpServletResponse response){
    	 String groupName = request.getParameter("name");
         List<WifiUserGroup> groupList = wifiUserGroupServices.listWifiUserGroup(groupName);
         
         request.setAttribute("WifiUserGroup", groupList);
         request.setAttribute("groupName", groupName);
         
         return "admin/rzfs3";
     }
     
     /**
      * 打开修改页面
      * 
      * @param id
      * @param request
      * @param response
      * @return
      */
     @RequestMapping("/detail/{id}")
     public String detail(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){    	
    	 WifiUserGroup userGroup = wifiUserGroupServices.getById(id);
         
    	 request.setAttribute("userGroup", userGroup);
    	 
         return "admin/rzfs3_detail";
     }
     
     /**
      * 打开新增页面
      * 
      * @param id
      * @param request
      * @param response
      * @return
      */
     @RequestMapping("/detail")
     public String addPage(HttpServletRequest request, HttpServletResponse response){    	
    	 return "admin/rzfs3_detail";
     }
     
     /**
      * 根据 ID 修改
      * 
      * @param id
      * @param param
      * @param request
      * @param response
      * @return
      */
     @RequestMapping("/update")
     public ModelAndView update(@RequestParam String id, @RequestParam String name, HttpServletRequest request, HttpServletResponse response){
    	 System.out.println(request.getParameter("name"));
    	 wifiUserGroupServices.updateById(id, name);
         
    	 return new ModelAndView("redirect:/customer/group/list");
     }
     
     /**
      * 保存新增分类
      * 
      * @param param
      * @param request
      * @param response
      * @return
      */
     @RequestMapping("/save")
     public ModelAndView save(@RequestParam String name, HttpServletRequest request, HttpServletResponse response){    	
    	 wifiUserGroupServices.save(name);
         
    	 return new ModelAndView("redirect:/customer/group/list");
     }
     
     /**
      * 根据 ID 删除
      * 
      * @param id
      * @param request
      * @param response
      * @return
      */
     @RequestMapping("/delete/{id}")
     public ModelAndView delete(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){    	
    	 wifiUserGroupServices.delete(id);
         
         return new ModelAndView("redirect:/customer/group/list");
     }
     
}
