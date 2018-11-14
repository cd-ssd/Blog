package com.ischoolbar.programmer.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ischoolbar.programmer.entity.Blog;
import com.ischoolbar.programmer.entity.PageBean;
import com.ischoolbar.programmer.lucene.BlogIndex;
import com.ischoolbar.programmer.service.BlogService;
import com.ischoolbar.programmer.util.ResponseUtil;
import com.ischoolbar.programmer.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * 管理员博客Controller层
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

	@Resource
	private BlogService blogService;
	
	// 博客索引
	private BlogIndex blogIndex=new BlogIndex();
	
	/**
	 * 添加或者修改博客信息
	 * 
	 */
	@RequestMapping("/save")
	public String save(Blog blog,HttpServletResponse response)throws Exception{
		int resultTotal=0; // 操作的记录条数
		if(blog.getId()==null){//如果没有选择任何博客id，则代表在添加博客
			resultTotal=blogService.add(blog);//保证能够写博客成功，否则博客添加失败
			blogIndex.addIndex(blog); // 添加博客索引
		}else{
			resultTotal=blogService.update(blog);//保证能够成功修改博客内容，否则修改失败
			blogIndex.updateIndex(blog); // 更新博客索引
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);//前台js回调执行success后的操作（与前台js代码连起来好理解）
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 分页查询博客信息
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Blog s_blog,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", StringUtil.formatLike(s_blog.getTitle()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Blog> blogList=blogService.list(map);
		Long total=blogService.getTotal(map);
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(blogList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 删除博客信息
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			blogService.delete(Integer.parseInt(idsStr[i]));
			blogIndex.deleteIndex(idsStr[i]); // 删除对应博客的索引
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 通过ID查找实体
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Blog blog=blogService.findById(Integer.parseInt(id));
		JSONObject jsonObject=JSONObject.fromObject(blog);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	
	
	
}
