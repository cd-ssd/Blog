package com.ischoolbar.programmer.realm;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.ischoolbar.programmer.entity.Blogger;
import com.ischoolbar.programmer.service.BloggerService;


public class MyRealm extends AuthorizingRealm{

	@Resource
	private BloggerService bloggerService;
	
	/**
	 * 为当限前登录的用户授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String)token.getPrincipal();//从令牌中获取用户信息(username)
		Blogger blogger=bloggerService.getByUserName(userName);//通过用户名查询用户
		if(blogger!=null){//查到有
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger); // 当前用户信息存到一次session中
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(blogger.getUserName(),blogger.getPassword(),"xx");//AuthenticationInfo的第二个参数是数据库密码，内部实现会比对token的密码，如果错误，会返回报错信息, 在login()方法catch，页面提示错误   //当前realm的名字realname可以是随意的字符串
			return authcInfo;//这个构造方法会核对数据库得到的数据和token内的数据是否匹配，匹配就悄悄离开，否则例如密码错误，就会报错，这个错误会通过throws往上抛出，被我们的catch抓到
		}else{
			return null;				
		}
	}

}
