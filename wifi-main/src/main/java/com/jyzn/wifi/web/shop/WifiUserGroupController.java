/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.web.shop;

import com.jyzn.wifi.common.annotation.OperatingAudit;
import com.jyzn.wifi.entity.shop.ValidateLog;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


@Controller
@OperatingAudit("商户会员分类")
public class WifiUserGroupController {
     private static final Logger logger = LoggerFactory.getLogger(WifiUserGroupController.class);
     
     
      public List<ValidateLog> getLogByFilters(){
          
          
          
          return null;
      }
     
     
     
}
