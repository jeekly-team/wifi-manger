/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.dao.shop;

import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;
import com.jyzn.wifi.entity.shop.ValidateLog;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class ValidateLogDao extends HibernateSupportDao<ValidateLog, String> {

    public List<ValidateLog> getCountByDate(String startDate, String endDate) {
        String sql = "select *  from ValidateLog t where t.dt >= cast(? as dateTime) and t.dt <= cast(? as dateTime)";
        return createSQLQuery(sql, startDate, endDate).list();

    }

    public List<ValidateLog> getCountByDateAndType(Date startDate, Date endDate, String type) {
        String hql = "from ValidateLog t where cast(t.dt as date) between cast(?1 as date) and cast(?2 as date) and type=?3";

        return createQuery(hql, startDate, endDate, type).list();
    }

    public List<ValidateLog> getAllCountLog() {
        //String sql = "select *, (select count(1) from validatelog)  as allcount from (select type,count(1) as count from validatelog group by type)";
        String sql = "select * from ValidateLog";
        List<ValidateLog> list = createSQLQuery(sql).list();
        return list;
    }

    public List getLastLogCount() {
        String query = "select new Map(l.wifiuser.name as name,count(l) as count,max(l.dt) as maxdt) from ValidateLog l group by l.wifiuser";
        return createQuery(query).list();
    }

}
