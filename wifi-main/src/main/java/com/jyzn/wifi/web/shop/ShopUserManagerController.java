package com.jyzn.wifi.web.shop;

import com.github.dactiv.orm.core.PropertyFilter;
import com.github.dactiv.orm.core.PropertyFilters;
import com.jyzn.wifi.common.SystemVariableUtils;
import com.jyzn.wifi.common.annotation.OperatingAudit;
import com.jyzn.wifi.entity.account.User;
import com.jyzn.wifi.service.shop.ShopService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author zyt
 */
@Controller
@OperatingAudit("商户会员管理")
@RequestMapping("/shop/usermanager")
public class ShopUserManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ShopUserManagerController.class);

    @Autowired
    private ShopService shopservice;

    @RequestMapping("/view")
    public String view() {

        return "shop/rzfs1";
    }

    @RequestMapping(value = "/getlist", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map wifiuserlist(
            @RequestParam(value = "dt") String dt,
            @RequestParam(value = "ct", defaultValue = "0") int ct,
            @RequestParam(value = "pageindex", defaultValue = "1") int pageIndex,
            @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
            HttpServletRequest request
    ) {
        List<PropertyFilter> filters = PropertyFilters.get(request, true);

        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isEmpty(dt)) {
            String s = dtf.format(new Date());
            dt = s + " To " + s;
            logger.warn("dt 为空 重置为当前日期(yyyy-MM-dd):" + dt);

        }
        logger.info(dt);
        filters.add(PropertyFilters.get("EQS_sid", getCurrentUserId()));
        filters.add(PropertyFilters.get("GED_dt", dt.split(" To ")[0]));
        filters.add(PropertyFilters.get("LED_dt", dt.split(" To ")[1]));

        return shopservice.findWifiUserCountByFilters(filters, ct, new PageRequest(pageIndex, pageSize));
    }

    private String getCurrentUserId() {
        User user = SystemVariableUtils.getSessionVariable().getUser();
        return user.getId();
    }
}
