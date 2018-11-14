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
 * ��ҳController
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@Resource
	private BlogService blogService;
	
	
	/**
	 * ������ҳ
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
		map.put("typeId", typeId);//��־���(���Ӱ���־���)  û�����Ļ�:���ᰴ��־������
		map.put("releaseDateStr", releaseDateStr);//��������(���Ӱ���־����)  û�����Ļ�:���ᰴ��־���ڷ���
		List<Blog> blogList=blogService.list(map);
		for(Blog blog:blogList){//��ҳ��һС������
			List<String> imagesList=blog.getImagesList();//��ҳ�б��������ͼ
			String blogInfo=blog.getContent();//һС�ڲ����������
			Document doc=Jsoup.parse(blogInfo);//��������������
			Elements jpgs=doc.select("img[src$=.jpg]"); //��������չ����jpg��ͼƬ����˵��ƥ��jpg��β��Ԫ��
			for(int i=0;i<jpgs.size();i++){//�������jpgͼƬ
				Element jpg=jpgs.get(i);
				imagesList.add(jpg.toString());
				if(i==2){
					break;
				}
			}
		}
		mav.addObject("blogList", blogList);//�г�ÿС������    û�����Ļ�:��ҳ����λ��Ϊ��
		StringBuffer param=new StringBuffer(); // ��ѯ����
		if(StringUtil.isNotEmpty(typeId)){
			param.append("typeId="+typeId+"&");
		}
		if(StringUtil.isNotEmpty(releaseDateStr)){
			param.append("releaseDateStr="+releaseDateStr+"&");
		}
		mav.addObject("pageCode",PageUtil.genPagination(request.getContextPath()+"/index.html", blogService.getTotal(map), Integer.parseInt(page), 10, param.toString()));//������ĩβ����(��ҳ����һҳ����ǰҳ����һҳ��βҳ)
		mav.addObject("mainPage", "foreground/blog/list.jsp");//��ҳһ��С����
		mav.addObject("pageTitle","�ҵĲ���ϵͳ��ҳ");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	/**
	 * ϵͳ����
	 */
	@RequestMapping("/download")
	public ModelAndView download()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "foreground/system/download.jsp");//ϵͳ����ҳ��
		mav.addObject("pageTitle","ϵͳ����--�ҵĲ���ϵͳ");
		mav.setViewName("mainTemp");
		return mav;
	}
}
