/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.test.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dactiv.orm.core.PropertyFilter;
import com.github.dactiv.orm.core.PropertyFilters;
import com.google.common.collect.Lists;
import com.jyzn.wifi.entity.shop.ValidateLog;
import com.jyzn.wifi.entity.shop.summary.WifiUserCount;
import com.jyzn.wifi.service.shop.ShopService;
import com.jyzn.wifi.test.manager.ManagerTestCaseSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Administrator
 */
public class ShopServiceTest extends ManagerTestCaseSupport {

    @Autowired
    private ShopService ser;

    private final ObjectMapper om = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(ShopServiceTest.class);

    @Test
    public void findAllValidateLogPageBySpecificationTest() throws IOException {

        List<PropertyFilter> filters = Lists.newArrayList(
                PropertyFilters.get("EQS_wifiuser.id", "202881e437d47b250137d481b6920002")
        );
        Page<WifiUserCount> log = ser.findValidateLogsByFilters(filters, new PageRequest(0, 10));

        System.out.print("\n log: \n");
        om.writeValue(System.out, log);
        System.out.print("\n end log: \n");
    }

    @Test
    public void findValidateLogsByFiltersTest() {
        List<String> ids = Lists.newArrayList("202881e437d47b250137d481b6920001","202881e437d47b250137d481b6920002");
        logger.info(StringUtils.join(ids, ","));
        List<PropertyFilter> filters = Lists.newArrayList(
                PropertyFilters.get("EQS_sid", "SJDK3849CKMS3849DJCK2039ZMSK0001"),
                PropertyFilters.get("INS_wifiuser.id", StringUtils.join(ids, ","))
        );
        List<ValidateLog> logs_Delbeforel = ser.findValidateLogsByFilters(filters);
        assertNotEquals(logs_Delbeforel.size(), 0);
        
        ser.delValidateLogs(logs_Delbeforel);
        
        List<ValidateLog> logs_Delafter = ser.findValidateLogsByFilters(filters);
         assertEquals(logs_Delafter.size(), 0);

    }

    @Test
    public void findWifiUserCountByFiltersTest() throws IOException {

        List<PropertyFilter> filters = Lists.newArrayList(
                //                PropertyFilters.get("GED_dt", "2014-07-01"),
                //                PropertyFilters.get("LED_dt", "2014-09-10"),
                PropertyFilters.get("EQS_sid", "SJDK3849CKMS3849DJCK2039ZMSK0001"),
                PropertyFilters.get("EQS_grid", "402881e437d47b250137d481b6920001")
        );

        String f = filters.get(filters.size() - 1).getSinglePropertyName();
        System.out.print("\n log: \n");
        System.out.print(f + "\n");

        Map log = ser.findWifiUserCountByFilters(
                filters,
                0,
                new PageRequest(1, 1)
        );

        System.out.print("\n log: \n");
        om.writeValue(System.out, log);
        System.out.print("\n end log: \n");
    }

    @Test
    public void findWifiUserCountBySidTest() {
        Page page = ser.findWifiUserCountBySid("test", new PageRequest(0, 5));
        assertNotEquals(page.getSize(), 0);
    }

    @Test
    public void findGroupByWifiUserTest() {
        List gr = ser.findGroupBySysUserId("SJDK3849CKMS3849DJCK2039ZMSK0001");
        assertNotEquals(gr.size(), 0);
    }

    @Test
    public void getWifiUsersTest() {
        List ul = ser.getWifiUsers(Lists.newArrayList("202881e437d47b250137d481b6920001"));
        assertNotEquals(ul.size(), 0);
    }

    private static Date StringToDate(String dateStr, String formatStr) {
        DateFormat dd = new SimpleDateFormat(formatStr, Locale.CHINA);
        Date date = null;
        try {
            date = dd.parse(dateStr);
        } catch (ParseException e) {
        }
        return date;
    }
}
