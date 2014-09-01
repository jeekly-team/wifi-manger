package com.jyzn.wifi.dao.login.log;

import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;
import com.jyzn.wifi.entity.foundation.variable.DataDictionary;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class LoginLogDao extends HibernateSupportDao<DataDictionary, String>{
    
    public int getCountByTypeAndDate(int type , Date startDate, Date endDate){
        return 0;
    }
    
}
