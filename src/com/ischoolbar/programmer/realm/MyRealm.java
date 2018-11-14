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
	 * Ϊ����ǰ��¼���û������ɫ��Ȩ��
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * ��֤��ǰ��¼���û�
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String)token.getPrincipal();//�������л�ȡ�û���Ϣ(username)
		Blogger blogger=bloggerService.getByUserName(userName);//ͨ���û�����ѯ�û�
		if(blogger!=null){//�鵽��
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger); // ��ǰ�û���Ϣ�浽һ��session��
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(blogger.getUserName(),blogger.getPassword(),"xx");//AuthenticationInfo�ĵڶ������������ݿ����룬�ڲ�ʵ�ֻ�ȶ�token�����룬������󣬻᷵�ر�����Ϣ, ��login()����catch��ҳ����ʾ����   //��ǰrealm������realname������������ַ���
			return authcInfo;//������췽����˶����ݿ�õ������ݺ�token�ڵ������Ƿ�ƥ�䣬ƥ��������뿪����������������󣬾ͻᱨ����������ͨ��throws�����׳��������ǵ�catchץ��
		}else{
			return null;				
		}
	}

}
