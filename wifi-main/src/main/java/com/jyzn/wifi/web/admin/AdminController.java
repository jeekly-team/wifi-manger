/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrator
 */
@Controller
public class AdminController {
    
    @RequestMapping("/admin")
    public String adminPage(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");
        return page;
        //return "admin/rzfs1";
    }
    
}
