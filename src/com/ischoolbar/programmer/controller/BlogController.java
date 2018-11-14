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
	
	// 博客索引
	private BlogIndex blogIndex=new BlogIndex();
	
	
	/**
	 * 请求博客详细信息
	 */
	@RequestMapping("/articles/{id}")//用户点击了某一项博客文章，传入的id，在这里进行处理
	public ModelAndView details(@PathVariable("id") Integer id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		Blog blog=blogService.findById(id);//点击查看对应的文章（根据id找一条数据)
		String keyWords=blog.getKeyWord();//获得关键词
		if(StringUtil.isNotEmpty(keyWords)){//如果关键词不为空
			String arr[]=keyWords.split(" ");//剔除空格["xxx","yyy"]
			mav.addObject("keyWords",StringUtil.filterWhite(Arrays.asList(arr)));//将数组转化为列表list（List的一个典型的特性就是其长度是可变的，我们可以很方便地对它进行插入和删除元素的操作）			
		}else{
			mav.addObject("keyWords",null);			
		}
		mav.addObject("blog", blog);//将文章放入view中查看(view.jsp是显示每篇博客的具体内容)   给jsp页面调用
		blog.setClickHit(blog.getClickHit()+1); // 博客点击次数加1(由于有对应的id点进去了，所以得加一)
		blogService.update(blog);//更新一下
		Map<String,Object> map=new HashMap<String,Object>();//一对对数据的集合(字符串,对象)其中字符串拥有对象的功能
		map.put("blogId", blog.getId());//点击查看对应文章的评论，若没有这句则是同时多篇文章出现相同评论//对应数据库中的博客id
		map.put("state", 1); // 查询审核通过的评论(1为都是通过审核的评论，所以放出来)
		mav.addObject("commentList", commentService.list(map)); //让数据模型增加评论列表(审核通过的评论)
		mav.addObject("pageCode", this.genUpAndDownPageCode(blogService.getLastBlog(id),blogService.getNextBlog(id),request.getServletContext().getContextPath()));//让数据模型增加"上一篇，下一篇"
		mav.addObject("mainPage", "foreground/blog/view.jsp");//${mainPage}中加入view.jsp页面(显示每篇博客的具体内容)
		mav.addObject("pageTitle",blog.getTitle()+"我的博客系统");
		mav.setViewName("mainTemp");//跳转到指定的页面
		return mav;
	}
	
	/**
	 * 根据关键字查询相关博客信息
	 */
	@RequestMapping("/q")
	public ModelAndView search(@RequestParam(value="q",required=false)String q,@RequestParam(value="page",required=false)String page,HttpServletRequest request)throws Exception{//@RequestParam(value="q"获得前端查询的文字
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "foreground/blog/result.jsp");//查询后显示页面
		List<Blog> blogList=blogIndex.searchBlog(q.trim());
		Integer toIndex=blogList.size()>=Integer.parseInt(page)*10?Integer.parseInt(page)*10:blogList.size();//判断文章总数是不是大于一页的10条
		mav.addObject("blogList",blogList.subList((Integer.parseInt(page)-1)*10, toIndex));//显示查出来的文章结果(传给前台blogList)
		mav.addObject("pageCode",this.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q,10,request.getServletContext().getContextPath()));//下一页，上一页
		mav.addObject("q",q);//你搜索的词
		mav.addObject("resultTotal",blogList.size());//搜出的结果条数
		mav.addObject("pageTitle","搜索关键字'"+q+"'结果页面--我的博客系统");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	/**
	 * 获取下一篇博客和下一篇博客代码
	 */
	private String genUpAndDownPageCode(Blog lastBlog,Blog nextBlog,String projectContext){//projectContext就是请求路径中的Blog(完整路径为:http://localhost:8080/Blog/blog/articles/88.html)
		StringBuffer pageCode=new StringBuffer();//当对字符串进行修改的时候，需要使用 StringBuffer (比如拼接)  //StringBuffer他们都可以存储和操作字符串
		if(lastBlog==null || lastBlog.getId()==null){
			pageCode.append("<p>上一篇：没有了</p>");//拼接上和下
		}else{
			pageCode.append("<p>上一篇：<a href='"+projectContext+"/blog/articles/"+lastBlog.getId()+".html'>"+lastBlog.getTitle()+"</a></p>");
		}
		if(nextBlog==null || nextBlog.getId()==null){
			pageCode.append("<p>下一篇：没有了</p>");
		}else{
			pageCode.append("<p>下一篇：<a href='"+projectContext+"/blog/articles/"+nextBlog.getId()+".html'>"+nextBlog.getTitle()+"</a></p>");
		}
		return pageCode.toString();
	}
	
	/**
	 * 获取上一页，下一页代码 查询博客用到
	 */
	private String genUpAndDownPageCode(Integer page,Integer totalNum,String q,Integer pageSize,String projectContext){//用得到的基本参数都在这里了
		long totalPage=(totalNum-1)/pageSize+1;
		StringBuffer pageCode=new StringBuffer();//这里StringBuffer()表示要"拼接"(String操作对象)
		if(totalPage==0){
			return "";
		}else{
			pageCode.append("<nav>");
			pageCode.append("<ul class='pager' >");
			if(page>1){//说明有上页，前面不止一页
				pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(page-1)+"&q="+q+"'>上一页</a></li>");//q为查询的关键字，page为当前页
			}else{
				pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
			}
			if(page<totalPage){//说明还有下页，后面还可以翻下一页
				pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(page+1)+"&q="+q+"'>下一页</a></li>");//q为查询的关键字，page为当前页				
			}else{
				pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");				
			}
			pageCode.append("</ul>");
			pageCode.append("</nav>");
		}
		return pageCode.toString();
	}
}
