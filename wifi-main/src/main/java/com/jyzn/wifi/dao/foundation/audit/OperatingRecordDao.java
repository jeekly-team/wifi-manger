package com.jyzn.wifi.dao.foundation.audit;

import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;
import com.jyzn.wifi.entity.foundation.audit.OperatingRecord;
import org.springframework.stereotype.Repository;

/**
 * 操作记录数据访问
 * 
 * @author maurice
 *
 */
@Repository
public class OperatingRecordDao extends HibernateSupportDao<OperatingRecord, String>{
	
}
