/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.others;

import com.github.dactiv.common.utils.ConvertUtils;
import com.github.dactiv.orm.core.PropertyFilter;
import com.github.dactiv.orm.core.PropertyFilters;
import com.jyzn.wifi.test.manager.ManagerTestCaseSupport;


import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class ConverUtilsTest extends ManagerTestCaseSupport {

    @Test
    public void Test1() {
        PropertyFilter property = PropertyFilters.get("LED_dt", "2014-09-10");
        //Object c = convert("2014-07-10", property.getFieldType());
       // ConvertUtils.registerDateConverter();

        
        Object c =  ConvertUtils.convertToObject("2014-07-10", property.getFieldType());
        System.out.print(c);

    }

}
