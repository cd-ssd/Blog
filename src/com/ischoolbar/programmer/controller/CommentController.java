package com.ischoolbar.programmer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ischoolbar.programmer.entity.Blog;
import com.ischoolbar.programmer.entity.Comment;
import com.ischoolbar.programmer.service.BlogService;
import com.ischoolbar.programmer.service.CommentService;
import com.ischoolbar.programmer.util.ResponseUtil;
import com.ischoolbar.programmer.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 评论Controller层
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private BlogService blogService;
	
	/**
	 * 添加或者修改评论
	 */
	@RequestMapping("/save")
	public String save(Comment comment,@RequestParam("imageCode") String imageCode,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception{
		String sRand=(String) session.getAttribute("sRand"); // 获取系统生成的验证码  sRand(生成的验证码)
		JSONObject result=new JSONObject();
		int resultTotal=0; // 操作的记录条数
		if(!imageCode.equals(sRand)){
			result.put("success", true);
			result.put("errorInfo", "验证码填写错误！");
		}else{
			String userIp=request.getRemoteAddr(); // 获取用户IP
			comment.setUserIp(userIp);
			if(comment.getId()==null){
				resultTotal=commentService.add(comment);//向数据库增加一条评论信息
				// 该博客的回复次数加1(不论有没有审核评论通过，评论次数都加一)
				Blog blog=blogService.findById(comment.getBlog().getId());//查询对应博客id(从而为了能够让其评论数加一)
				blog.setReplyHit(blog.getReplyHit()+1);//让博客文章回复加一(对应的博客加一)
				blogService.update(blog);
			}else{
				
			}
			if(resultTotal>0){
				result.put("success", true);
			}else{
				result.put("success", false);
			}
		}
		ResponseUtil.write(response, result);
		return null;
	}

}
