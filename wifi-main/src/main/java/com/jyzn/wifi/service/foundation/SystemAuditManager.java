package com.jyzn.wifi.service.foundation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.dactiv.orm.core.Page;
import com.github.dactiv.orm.core.PageRequest;
import com.github.dactiv.orm.core.PropertyFilter;
import com.jyzn.wifi.dao.foundation.audit.OperatingRecordDao;
import com.jyzn.wifi.entity.foundation.audit.OperatingRecord;

/**
 * 系统审计业务逻辑
 * 
 * @author maurice
 *
 */
@Service
@Transactional("transactionManager")
public class SystemAuditManager {

	@Autowired
	private OperatingRecordDao operatingRecordDao;
	
	//---------------------------------------操作记录管理---------------------------------------//
	
	/**
	 * 获取操作记录实体
	 * 
	 * @param id 操作记录id
	 */
	public OperatingRecord getOperatingRecord(String id) {
		return operatingRecordDao.load(id);
	}
	
	/**
	 * 插入操作记录
	 * 
	 * @param entity 操作记录实体
	 */
	public void insertOperatingRecord(OperatingRecord entity) {
		operatingRecordDao.insert(entity);
	}
	
	/**
	 * 获取操作记录分页对象
	 * 
	 * @param request 分页参数请求
	 * @param filters 属性过滤器集合
	 * 
	 * @return Page
	 */
	public Page<OperatingRecord> searchOperatingRecordPage(PageRequest request,List<PropertyFilter> filters) {
		return operatingRecordDao.findPage(request, filters);
	}
	
}
