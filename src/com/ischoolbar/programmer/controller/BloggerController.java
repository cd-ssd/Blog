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
	 * 用户登录
	 */
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request){
		Subject subject=SecurityUtils.getSubject();//获得当前的用户登录//为每个请求创建一个Subject, 并保存到ThreadContext的resources（ThreadLocal<Map<Object, Object>>）变量中，也就是一个http请求一个subject,并绑定到当前线程。（简单说就是获得当前登录用户  ）
		UsernamePasswordToken token=new UsernamePasswordToken(blogger.getUserName(), CryptographyUtil.md5(blogger.getPassword(), "programmer.ischoolbar.com"));//得到Subject及创建用户名或密码身份验证Token（即用户身份/凭证）   即从页面获取的用户名和密码封装到一个token令牌中
		try{
			subject.login(token); // 登录验证，即身份验证，这时会调用自定义realm  
			return "redirect:/admin/index.jsp";//成功的跳转
		}catch(Exception e){//捕获异常
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "用户名或密码错误！");
			return "login";
		}
	}
	
	/**
	 * 查找博主信息
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("blogger",bloggerService.find());
		mav.addObject("mainPage", "foreground/blogger/info.jsp");
		mav.addObject("pageTitle","关于博主_我的博客系统");
		mav.setViewName("mainTemp");
		return mav;
	}
//	public static void main(String[] args){
//		System.out.println(CryptographyUtil.md5("123", "programmer.ischoolbar.com"));
//	}
}
