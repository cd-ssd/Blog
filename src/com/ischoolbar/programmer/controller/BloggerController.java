package com.ischoolbar.programmer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.Blogger;
import com.ischoolbar.programmer.service.BloggerService;
import com.ischoolbar.programmer.util.CryptographyUtil;


@Controller
@RequestMapping("/blogger")
public class BloggerController {

	@Resource
	private BloggerService bloggerService;
	
	/**
	 * �û���¼
	 */
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request){
		Subject subject=SecurityUtils.getSubject();//��õ�ǰ���û���¼//Ϊÿ�����󴴽�һ��Subject, �����浽ThreadContext��resources��ThreadLocal<Map<Object, Object>>�������У�Ҳ����һ��http����һ��subject,���󶨵���ǰ�̡߳�����˵���ǻ�õ�ǰ��¼�û�  ��
		UsernamePasswordToken token=new UsernamePasswordToken(blogger.getUserName(), CryptographyUtil.md5(blogger.getPassword(), "programmer.ischoolbar.com"));//�õ�Subject�������û��������������֤Token�����û����/ƾ֤��   ����ҳ���ȡ���û����������װ��һ��token������
		try{
			subject.login(token); // ��¼��֤���������֤����ʱ������Զ���realm  
			return "redirect:/admin/index.jsp";//�ɹ�����ת
		}catch(Exception e){//�����쳣
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "�û������������");
			return "login";
		}
	}
	
	/**
	 * ���Ҳ�����Ϣ
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("blogger",bloggerService.find());
		mav.addObject("mainPage", "foreground/blogger/info.jsp");
		mav.addObject("pageTitle","���ڲ���_�ҵĲ���ϵͳ");
		mav.setViewName("mainTemp");
		return mav;
	}
//	public static void main(String[] args){
//		System.out.println(CryptographyUtil.md5("123", "programmer.ischoolbar.com"));
//	}
}
