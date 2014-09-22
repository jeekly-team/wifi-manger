package com.jyzn.wifi.dao.account;

import com.github.dactiv.orm.core.hibernate.support.HibernateSupportDao;
import com.jyzn.wifi.entity.account.User;
import org.springframework.stereotype.Repository;

/**
 * 用户数据访问
 *
 * @author maurice
 *
 */
@Repository
public class UserDao extends HibernateSupportDao<User, String> {

    /**
     * 通过用户id更新用户密码
     *
     * @param userId 用户id
     * @param password 密码
     * @return
     */
    public void updatePassword(String userId, String password) {
//         Query q =this.createQuery(User.UpdatePassword, password, userId);
//         int c = q.executeUpdate();
//         return c;
        //很奇怪 参数的顺序是反的 /但是在mac 平台是正常的
        executeUpdate(User.UpdatePassword, userId, password);
    }

}
