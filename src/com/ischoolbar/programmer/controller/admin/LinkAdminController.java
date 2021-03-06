package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ischoolbar.programmer.entity.Link;
import com.ischoolbar.programmer.entity.PageBean;
import com.ischoolbar.programmer.service.LinkService;
import com.ischoolbar.programmer.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 友情链接Controller层
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {
	
	@Resource
	private LinkService linkService;
	
	/**
	 * 分页查询友情链接信息
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Link> linkList=linkService.list(map);
		Long total=linkService.getTotal(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(linkList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 添加或者修改友情链接信息
	 */
	@RequestMapping("/save")
	public String save(Link link,HttpServletResponse response)throws Exception{
		int resultTotal=0; // 操作的记录条数
		if(link.getId()==null){
			resultTotal=linkService.add(link);
		}else{
			resultTotal=linkService.update(link);
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 删除友情链接信息
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");//由于前台传来的为加了逗号隔开的字符串1,2,3,4,巴德,merge，因此需要先分隔（变为["1", "2", "3", "4", "巴德", "merge"]这样的json数组)
		for(int i=0;i<idsStr.length;i++){
			linkService.delete(Integer.parseInt(idsStr[i]));//转为int数据类型，因为删除是按照id来对应删除的
		}
		JSONObject result=new JSONObject();//创建一个json对象（与前台响应）
		result.put("success", true);
		ResponseUtil.write(response, result);//结果用response流去响应前台
		return null;
	}
}
