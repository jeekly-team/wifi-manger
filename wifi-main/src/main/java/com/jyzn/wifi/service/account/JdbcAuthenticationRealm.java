package com.jyzn.wifi.service.account;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;

import com.jyzn.wifi.common.SessionVariable;
import com.jyzn.wifi.common.enumeration.entity.State;
import com.jyzn.wifi.entity.account.User;

/**
 * 
 * apache shiro 的身份验证类
 * 
 * @author maurice
 *
 */
public class JdbcAuthenticationRealm extends AuthorizationRealm{
	
	@Autowired
	private AccountManager accountManager;

	/**
	 * 用户登录的身份验证方法
	 * 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String username = usernamePasswordToken.getUsername();
        
        User user = accountManager.getUserByUsername(username);
        
        if (user == null) {
            throw new IncorrectCredentialsException();
        }
        
        if (user.getState().equals(State.Disable.getValue())) {
        	 throw new DisabledAccountException("你的账户已被禁用,请联系管理员开通.");
        }
        //登陆请求时初始化
        SessionVariable model = new SessionVariable(user);
        
        return new SimpleAuthenticationInfo(model,user.getPassword(),getName());
	}
	

}
