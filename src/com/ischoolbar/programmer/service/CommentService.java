package com.ischoolbar.programmer.service;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.Comment;

/**
 * 评论Service接口
 *
 */
public interface CommentService {

	/**
	 * 添加评论
	 */
	public int add(Comment comment);
	
	/**
	 * 修改评论
	 */
	public int update(Comment comment);
	
	/**
	 * 查找用户评论信息
	 */
	public List<Comment> list(Map<String,Object> map);
	
	
	/**
	 * 获取总记录数
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 删除评论信息
	 */
	public Integer delete(Integer id);
}
