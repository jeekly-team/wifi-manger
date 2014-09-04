/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.dao.shop;

import org.hibernate.SQLQuery;
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
public class CountValidataLogDao extends HibernateDaoSupport{
    
    @Autowired  
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {   
      super.setSessionFactory(sessionFactory);   
    } 
    public SQLQuery getOldUserCount(){
        Session session  = getSessionFactory().getCurrentSession();
        String sql = "select count(1) from dual";
        return session.createSQLQuery(sql);
    }
    
}
