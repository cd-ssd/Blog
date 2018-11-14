package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ischoolbar.programmer.entity.Comment;
import com.ischoolbar.programmer.entity.PageBean;
import com.ischoolbar.programmer.service.CommentService;
import com.ischoolbar.programmer.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 管理员评论Controller层
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

	@Resource
	private CommentService commentService;
	
	/**
	 * 分页查询评论信息
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,@RequestParam(value="state",required=false)String state,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("state", state); // 评论状态(0传过来了)
		List<Comment> commentList=commentService.list(map);//查出数据库中state=0状态的数据
		Long total=commentService.getTotal(map);//获得总的state=0状态的个数数据
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();//该对象能够深刻影响Java对象转成json字符串的行为
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));//转化为yyyy-MM-dd格式
		JSONArray jsonArray=JSONArray.fromObject(commentList,jsonConfig);//将对象直接转换为JSONArray(json数组)的格式
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 删除评论信息
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			commentService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 评论审核
	 */
	@RequestMapping("/review")
	public String review(@RequestParam(value="ids")String ids,@RequestParam(value="state")Integer state,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");//切割逗号
		for(int i=0;i<idsStr.length;i++){
			Comment comment=new Comment();
			comment.setState(state);//通过审核为1，不通过为2，按照点击状态确定选择的是1还是2
			comment.setId(Integer.parseInt(idsStr[i]));
			commentService.update(comment);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);//成功调用前台result.success()方法
		ResponseUtil.write(response, result);//响应到前台js中
		return null;
	}
}
