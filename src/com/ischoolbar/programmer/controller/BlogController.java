package com.ischoolbar.programmer.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.Blog;
import com.ischoolbar.programmer.lucene.BlogIndex;
import com.ischoolbar.programmer.service.BlogService;
import com.ischoolbar.programmer.service.CommentService;
import com.ischoolbar.programmer.util.StringUtil;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Resource
	private BlogService blogService;
	
	@Resource
	private CommentService commentService;
	
	// ��������
	private BlogIndex blogIndex=new BlogIndex();
	
	
	/**
	 * ���󲩿���ϸ��Ϣ
	 */
	@RequestMapping("/articles/{id}")//�û������ĳһ������£������id����������д���
	public ModelAndView details(@PathVariable("id") Integer id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		Blog blog=blogService.findById(id);//����鿴��Ӧ�����£�����id��һ������)
		String keyWords=blog.getKeyWord();//��ùؼ���
		if(StringUtil.isNotEmpty(keyWords)){//����ؼ��ʲ�Ϊ��
			String arr[]=keyWords.split(" ");//�޳��ո�["xxx","yyy"]
			mav.addObject("keyWords",StringUtil.filterWhite(Arrays.asList(arr)));//������ת��Ϊ�б�list��List��һ�����͵����Ծ����䳤���ǿɱ�ģ����ǿ��Ժܷ���ض������в����ɾ��Ԫ�صĲ�����			
		}else{
			mav.addObject("keyWords",null);			
		}
		mav.addObject("blog", blog);//�����·���view�в鿴(view.jsp����ʾÿƪ���͵ľ�������)   ��jspҳ�����
		blog.setClickHit(blog.getClickHit()+1); // ���͵��������1(�����ж�Ӧ��id���ȥ�ˣ����Եü�һ)
		blogService.update(blog);//����һ��
		Map<String,Object> map=new HashMap<String,Object>();//һ�Զ����ݵļ���(�ַ���,����)�����ַ���ӵ�ж���Ĺ���
		map.put("blogId", blog.getId());//����鿴��Ӧ���µ����ۣ���û���������ͬʱ��ƪ���³�����ͬ����//��Ӧ���ݿ��еĲ���id
		map.put("state", 1); // ��ѯ���ͨ��������(1Ϊ����ͨ����˵����ۣ����Էų���)
		mav.addObject("commentList", commentService.list(map)); //������ģ�����������б�(���ͨ��������)
		mav.addObject("pageCode", this.genUpAndDownPageCode(blogService.getLastBlog(id),blogService.getNextBlog(id),request.getServletContext().getContextPath()));//������ģ������"��һƪ����һƪ"
		mav.addObject("mainPage", "foreground/blog/view.jsp");//${mainPage}�м���view.jspҳ��(��ʾÿƪ���͵ľ�������)
		mav.addObject("pageTitle",blog.getTitle()+"�ҵĲ���ϵͳ");
		mav.setViewName("mainTemp");//��ת��ָ����ҳ��
		return mav;
	}
	
	/**
	 * ���ݹؼ��ֲ�ѯ��ز�����Ϣ
	 */
	@RequestMapping("/q")
	public ModelAndView search(@RequestParam(value="q",required=false)String q,@RequestParam(value="page",required=false)String page,HttpServletRequest request)throws Exception{//@RequestParam(value="q"���ǰ�˲�ѯ������
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "foreground/blog/result.jsp");//��ѯ����ʾҳ��
		List<Blog> blogList=blogIndex.searchBlog(q.trim());
		Integer toIndex=blogList.size()>=Integer.parseInt(page)*10?Integer.parseInt(page)*10:blogList.size();//�ж����������ǲ��Ǵ���һҳ��10��
		mav.addObject("blogList",blogList.subList((Integer.parseInt(page)-1)*10, toIndex));//��ʾ����������½��(����ǰ̨blogList)
		mav.addObject("pageCode",this.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q,10,request.getServletContext().getContextPath()));//��һҳ����һҳ
		mav.addObject("q",q);//�������Ĵ�
		mav.addObject("resultTotal",blogList.size());//�ѳ��Ľ������
		mav.addObject("pageTitle","�����ؼ���'"+q+"'���ҳ��--�ҵĲ���ϵͳ");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	/**
	 * ��ȡ��һƪ���ͺ���һƪ���ʹ���
	 */
	private String genUpAndDownPageCode(Blog lastBlog,Blog nextBlog,String projectContext){//projectContext��������·���е�Blog(����·��Ϊ:http://localhost:8080/Blog/blog/articles/88.html)
		StringBuffer pageCode=new StringBuffer();//�����ַ��������޸ĵ�ʱ����Ҫʹ�� StringBuffer (����ƴ��)  //StringBuffer���Ƕ����Դ洢�Ͳ����ַ���
		if(lastBlog==null || lastBlog.getId()==null){
			pageCode.append("<p>��һƪ��û����</p>");//ƴ���Ϻ���
		}else{
			pageCode.append("<p>��һƪ��<a href='"+projectContext+"/blog/articles/"+lastBlog.getId()+".html'>"+lastBlog.getTitle()+"</a></p>");
		}
		if(nextBlog==null || nextBlog.getId()==null){
			pageCode.append("<p>��һƪ��û����</p>");
		}else{
			pageCode.append("<p>��һƪ��<a href='"+projectContext+"/blog/articles/"+nextBlog.getId()+".html'>"+nextBlog.getTitle()+"</a></p>");
		}
		return pageCode.toString();
	}
	
	/**
	 * ��ȡ��һҳ����һҳ���� ��ѯ�����õ�
	 */
	private String genUpAndDownPageCode(Integer page,Integer totalNum,String q,Integer pageSize,String projectContext){//�õõ��Ļ�����������������
		long totalPage=(totalNum-1)/pageSize+1;
		StringBuffer pageCode=new StringBuffer();//����StringBuffer()��ʾҪ"ƴ��"(String��������)
		if(totalPage==0){
			return "";
		}else{
			pageCode.append("<nav>");
			pageCode.append("<ul class='pager' >");
			if(page>1){//˵������ҳ��ǰ�治ֹһҳ
				pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(page-1)+"&q="+q+"'>��һҳ</a></li>");//qΪ��ѯ�Ĺؼ��֣�pageΪ��ǰҳ
			}else{
				pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
			}
			if(page<totalPage){//˵��������ҳ�����滹���Է���һҳ
				pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(page+1)+"&q="+q+"'>��һҳ</a></li>");//qΪ��ѯ�Ĺؼ��֣�pageΪ��ǰҳ				
			}else{
				pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");				
			}
			pageCode.append("</ul>");
			pageCode.append("</nav>");
		}
		return pageCode.toString();
	}
}
