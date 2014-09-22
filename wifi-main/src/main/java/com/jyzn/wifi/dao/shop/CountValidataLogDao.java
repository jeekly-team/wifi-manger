/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.dao.shop;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class CountValidataLogDao extends HibernateDaoSupport {

    @Autowired
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 通过日期获取新老顾客所占比例
     *
     * @param startDate
     * @param endDate
     * @param sid
     * @return
     */
    public List getCountUser(String startDate, String endDate, String sid) {

        try {
            Session session = getSessionFactory().getCurrentSession();
            String sql = "select count(1),date, (case when num = 1 then 'new' else 'old' end) as type from(select t.wifiuser_id,t.date,t1.num from "
                    + "(select wifiuser_id,to_char(dt, 'yyyy-mm-dd') as date from validatelog where dt >= cast('" + startDate + "' as dateTime) "
                    + "and dt <= cast('" + endDate + "' as dateTime) and sid = '" + sid + "' group by wifiuser_id,to_char(dt, 'yyyy-mm-dd')) t "
                    + " left join (select count(1) as num ,wifiuser_id from validatelog where dt <= cast('" + endDate + "' as dateTime) and sid = '" + sid + "' group by wifiuser_id) t1 "
                    + "on  t.wifiuser_id = t1.wifiuser_id) group by num,date";

            List list = session.createSQLQuery(sql).list();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 统计三十天内新顾客，老顾客，所有老顾客
     *
     * @param sid
     * @return
     */
    public List getCountUserTT(String sid) {
        
        try {
            Session session = getSessionFactory().getCurrentSession();
            String sql = "select (select count(1) from (select count(1) as num from validatelog where sid = '" + sid + "' group by wifiuser_id) where num >= 2) as allOldUser,"
                    + "(select count(*) as oldUsertt from (select t.wifiuser_id,t1.num from (SELECT wifiuser_id FROM validatelog where sid = '" + sid + "' and dt >= (getdate() - 30) and dt <= (getdate() + 1) group by wifiuser_id) t "
                    + "left join (select count(1) as num ,wifiuser_id from validatelog where sid = '" + sid + "' and dt <= (getdate() - 30) group by wifiuser_id) t1 on t.wifiuser_id = t1.wifiuser_id where num >= 2) ) as oldUsertt,"
                    + "(select count(*) as allUsertt from (SELECT wifiuser_id FROM validatelog where sid = '" + sid + "' and dt >= (getdate() - 30) and dt <= (getdate() + 1) group by wifiuser_id))  as allUsertt,"
                    + "select count(*) from (select count(1) as sum,wifiuser_id from validatelog where sid = '" + sid + "' group by wifiuser_id) where sum >= 2 and wifiuser_id not in"
                    + "(select wifiuser_id from (select count(1) as num ,wifiuser_id from validatelog where sid = '" + sid + "' and dt <= (getdate() - 30) group by wifiuser_id) where num >= 2) as changeUsertt";

            List list = session.createSQLQuery(sql).list();
            return list;
        } catch (Exception e) {
            return null;
        } 
    }

    /**
     * 通过类型获取回访次数所占比例统计
     * @param type
     * @param sid
     * @return 
     */
    public List getCentumByType(String type, String sid) {
        try {
             Session session = getSessionFactory().getCurrentSession();
            String sql = "select name , munt , round(munt*100/(select sum (dnum) from (select count(1) as dnum from "
                    + "(select count(1) as sum from validatelog where sid = '" + sid + "' and type ='" + type + "' group by wifiuser_id ) group by sum))+0.0,2) || '%' as centum "
                    + "from (select case when sum = 1 then '一次回访' when sum = 2 then '两次回访' when sum = 3 then '三次回访' when sum = 4 then '四次回访' else '五次以上' end as name"
                    + ",count(1) as munt from (select count(1) as sum from validatelog where sid = '" + sid + "' and type = '" + type + "' group by wifiuser_id order by sum) group by sum) group by name";

            List list = session.createSQLQuery(sql).list();
            return list;
        } catch (Exception e) {
            return null;
        } 
    }
    
    public List getValidateLogByCount(int count, String type, int page, int pagesize, String sid){
        try {
            Session session = getSessionFactory().getCurrentSession();
            String sql = "select t.sum,to_char(t.maxdate,'yyyy-MM-dd HH:mm:ss'),to_char(t.mindate,'yyyy-MM-dd HH:mm:ss'),w.name from (select count(1) as sum,max(dt) as maxdate,min(dt) as mindate," +
                         "wifiuser_id from validatelog where sid = '" + sid + "' and type ='" + type + "' group by wifiuser_id) t left join wifiuser w on t.wifiuser_id = w.id where t.sum = " + count
                         +" limit " + page * pagesize + "," +  (page + 1)* pagesize;
            List list = session.createSQLQuery(sql).list();
            return list;
        } catch (Exception e) {
            return null;
        } 
    }
    
    public int getMaxValidateLogByCount(int count, String type, String sid){
        try {
            Session session = getSessionFactory().getCurrentSession();
            String sql = "select count(1) from (select t.sum,t.maxdate,t.mindate,w.name from (select count(1) as sum,max(dt) as maxdate,min(dt) as mindate," 
                        +"wifiuser_id from validatelog where sid = '" + sid + "' and type ='" + type + "' group by wifiuser_id) t left join wifiuser w on "
                        //如果大于5次则为大于等于5次的纪录明细
                        + "t.wifiuser_id = w.id where t.sum " + ((count >=5) ? ">=":"=") + count + ")";
            List list = session.createSQLQuery(sql).list();
            return Integer.parseInt(list.get(0).toString());
        } catch (Exception e) {
            return 0;
        }
    }
    /**
     * 获取平均顾客平均来店次数及最近三十天来店次数
     * @param sid
     * @return 
     */
      public List getAvgCount(String sid){
        try {
            Session session = getSessionFactory().getCurrentSession();
            String sql = "select (select round((count(wifiuser_id) + 0.0)/(count(distinct(wifiuser_id))),2)"
                    + " from validatelog where sid = '" + sid + "' ) as avgall,(select round((count(wifiuser_id) + 0.0)/(count(distinct(wifiuser_id))),2) "
                    + "from (select * from validatelog where sid = '" + sid + "' and dt <= getdate() and dt >= (getdate() -30))) as avg30";
            List list = session.createSQLQuery(sql).list();
            return list;
        } catch (Exception e) {
            return null;
        } 
    }
      
      public List getActiveUser(String startDate, String endDate, String sid){
        try {
            Session session = getSessionFactory().getCurrentSession();
            String sql = "select sc || '次回访',count(1),"
                    + "round((count(1)*100 + 0.0 )/(select count(distinct(wifiuser_id)) from "
                    + "validatelog where sid = '" + sid + "' and dt >= cast('" + startDate + "' as dateTime) and dt <= cast('" + endDate + "' as dateTime)),2) || '%'"
                    + " as bfb from (select count(1) as sc from validatelog where sid = '" + sid + "' and dt >= cast"
                    + "('" + startDate + "' as dateTime) and dt <= cast('" + endDate + "' as dateTime) group by wifiuser_id) group by sc ";
            List list = session.createSQLQuery(sql).list();
            return list;
        } catch (Exception e) {
            return null;
        } 
    }
}
