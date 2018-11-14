package com.ischoolbar.programmer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.Blog;
import com.ischoolbar.programmer.entity.PageBean;
import com.ischoolbar.programmer.service.BlogService;
import com.ischoolbar.programmer.util.PageUtil;
import com.ischoolbar.programmer.util.StringUtil;

/**
 * 主页Controller
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@Resource
	private BlogService blogService;
	
	
	/**
	 * 请求主页
	 */
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value="page",required=false)String page,@RequestParam(value="typeId",required=false)String typeId,@RequestParam(value="releaseDateStr",required=false)String releaseDateStr,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("typeId", typeId);//日志类别(链接按日志类别)  没有这句的话:不会按日志类别分类
		map.put("releaseDateStr", releaseDateStr);//发布日期(链接按日志日期)  没有这句的话:不会按日志日期分类
		List<Blog> blogList=blogService.list(map);
		for(Blog blog:blogList){//主页中一小节文章
			List<String> imagesList=blog.getImagesList();//主页列表里的略缩图
			String blogInfo=blog.getContent();//一小节博客里的内容
			Document doc=Jsoup.parse(blogInfo);//解析出内容文字
			Elements jpgs=doc.select("img[src$=.jpg]"); //　查找扩展名是jpg的图片或者说是匹配jpg结尾的元素
			for(int i=0;i<jpgs.size();i++){//查出所有jpg图片
				Element jpg=jpgs.get(i);
				imagesList.add(jpg.toString());
				if(i==2){
					break;
				}
			}
		}
		mav.addObject("blogList", blogList);//列出每小节文章    没有这句的话:首页文章位置为空
		StringBuffer param=new StringBuffer(); // 查询参数
		if(StringUtil.isNotEmpty(typeId)){
			param.append("typeId="+typeId+"&");
		}
		if(StringUtil.isNotEmpty(releaseDateStr)){
			param.append("releaseDateStr="+releaseDateStr+"&");
		}
		mav.addObject("pageCode",PageUtil.genPagination(request.getContextPath()+"/index.html", blogService.getTotal(map), Integer.parseInt(page), 10, param.toString()));//在文章末尾加上(首页，上一页，当前页，下一页，尾页)
		mav.addObject("mainPage", "foreground/blog/list.jsp");//主页一节小文章
		mav.addObject("pageTitle","我的博客系统主页");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	/**
	 * 系统介绍
	 */
	@RequestMapping("/download")
	public ModelAndView download()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "foreground/system/download.jsp");//系统介绍页面
		mav.addObject("pageTitle","系统介绍--我的博客系统");
		mav.setViewName("mainTemp");
		return mav;
	}
}
