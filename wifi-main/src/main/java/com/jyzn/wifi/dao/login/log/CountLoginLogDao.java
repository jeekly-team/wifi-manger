/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jyzn.wifi.dao.login.log;

import com.jyzn.wifi.entity.login.log.CountLonginLog;
import java.util.List;
import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;

/**
 *
 * @author Administrator
 */
public class CountLoginLogDao extends HibernateSupportDao{
    
    public List<CountLonginLog> countByType(int type){
        String sql = "";
        return (List<CountLonginLog>) this.createQuery(sql);
    }
    
}
