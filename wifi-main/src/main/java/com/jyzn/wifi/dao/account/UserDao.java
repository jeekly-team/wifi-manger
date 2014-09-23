package com.jyzn.wifi.dao.account;

import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;
import com.jyzn.wifi.entity.account.User;
import org.springframework.stereotype.Repository;

/**
 * 用户数据访问
 * @author maurice
 *
 */
@Repository
public class UserDao extends HibernateSupportDao<User, String>{

	/**
	 * 通过用户id更新用户密码
	 * 
         * @param user
	 */
	public void updatePassword(User user) {
            save(user);
	}

	
}
