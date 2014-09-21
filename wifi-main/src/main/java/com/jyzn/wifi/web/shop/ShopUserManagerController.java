package com.jyzn.wifi.web.shop;

import com.github.dactiv.orm.core.PropertyFilter;
import com.github.dactiv.orm.core.PropertyFilters;
import com.google.common.collect.Lists;
import com.jyzn.wifi.common.SystemVariableUtils;
import com.jyzn.wifi.common.annotation.OperatingAudit;
import com.jyzn.wifi.entity.account.User;
import com.jyzn.wifi.entity.shop.ValidateLog;
import com.jyzn.wifi.entity.shop.WifiUser;
import com.jyzn.wifi.entity.shop.WifiUserGroup;
import com.jyzn.wifi.service.shop.ShopService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping("view")
    public String view(Model model) {
        model.addAttribute("groupsList", shopservice.findGroupBySysUserId(getCurrentUser().getId()));
        return "shop/rzfs1";
    }

    @RequestMapping(value = "getlist", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map wifiuserlist(
            @RequestParam(value = "dt") String dt,
            @RequestParam(value = "ct", defaultValue = "0") int ct,
            @RequestParam(value = "grid", defaultValue = "") String grid,
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
        filters.add(PropertyFilters.get("EQS_sid", getCurrentUser().getId()));
        filters.add(PropertyFilters.get("GED_dt", dt.split(" To ")[0]));
        filters.add(PropertyFilters.get("LED_dt", dt.split(" To ")[1]));

        if (StringUtils.isNotEmpty(grid)) {
            /*
             为了避免findWifiUserCountByFilters参数膨胀,将WifiUserGroup.membersList 关联的WIFIUSER.id 命名为grid
             必须将grid放置list的最后一个元素,并且命名为EQS_grid,将在条件构造后移除此元素
             */
            filters.add(PropertyFilters.get("EQS_grid", grid));
        }
        return shopservice.findWifiUserCountByFilters(filters, ct, new PageRequest(pageIndex, pageSize));
    }

    @RequestMapping(value = "tocategory", method = RequestMethod.GET)
    @ResponseBody
    public String WifiusersToCategory(@Valid @ModelAttribute("group") WifiUserGroup usergroup, @RequestParam("ids") List<String> ids) {
        logger.info("Wifiusergroup" + usergroup.getId() + "<--Wifiuser:" + ids.toString());
        usergroup.setMembersList(shopservice.getWifiUsers(ids));
        shopservice.saveWifiuserGroupRestriction(usergroup);
        return "success";
    }

    /*
     删除此商户下用户为ids对象的验证记录，并且清除用户与此商户组的关联
     没有选商户组也就是清除所有商户组中的此用户
     */
    @RequestMapping(value = "deluserslog", method = RequestMethod.GET)
    @ResponseBody
    public String deleteLog(@Valid @ModelAttribute("group") WifiUserGroup usergroup, @RequestParam("ids") List<String> ids) {
        List<PropertyFilter> filters = Lists.newArrayList(
                PropertyFilters.get("EQS_sid", getCurrentUser().getId()),
                PropertyFilters.get("INS_wifiuser.id", StringUtils.join(ids, ","))
        );
        List<ValidateLog> logs_Delbefore = shopservice.findValidateLogsByFilters(filters);

        //删除log
        shopservice.delValidateLogs(logs_Delbefore);
        //请求移除的用户列表
        List<WifiUser> req_userlist = shopservice.getWifiUsers(ids);
        List<WifiUser> userlist;
        if (StringUtils.isNotEmpty(usergroup.getId())) {
            //a-b的新List
            userlist = ListUtils.subtract(usergroup.getMembersList(), req_userlist);
            usergroup.setMembersList(userlist);
            shopservice.saveWifiuserGroupRestriction(usergroup);
        } else {
            logger.warn("usergroup is null,will be del for each List<WifiUserGroup> from SysUser ");
            //没有指定WifiUserGroup则表示删除此商户下所有组中的WifiUser
            List<WifiUserGroup> groupList = shopservice.findGroupBySysUserId(getCurrentUser().getId());
            for (WifiUserGroup gr : groupList) {
                userlist = ListUtils.subtract(gr.getMembersList(), req_userlist);
                gr.setMembersList(userlist);
                shopservice.saveWifiuserGroupRestriction(gr);
            }

        }

        return "success";
    }

    private User getCurrentUser() {
        return SystemVariableUtils.getSessionVariable().getUser();
    }

    /**
     * 所有RequestMapping方法调用前的Model准备方法, 实现Preparable二次部分绑定的效果 先根据Request的{id}从数据库查出WifiUserGroup对象,再把Request提交的内容绑定到该对象上。 因为仅update()方法的form中有grid属性，因此仅在update时实际执行.
     *
     * @param id
     * @param model
     */
    @ModelAttribute
    public void getWifiUserGroup(@RequestParam(value = "grid", defaultValue = "") String id, Model model) {
        if (StringUtils.isNotEmpty(id)) {
            model.addAttribute("group", shopservice.getWifiUserGroup(id));
        }
    }
}
