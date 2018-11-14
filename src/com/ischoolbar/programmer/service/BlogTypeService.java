package com.ischoolbar.programmer.service;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.BlogType;

/**
 * 博客类型Service接口
 *
 */
public interface BlogTypeService {

	/**
	 * 查询所有博客类型 以及对应的博客数量
	 */
	public List<BlogType> countList();
	
	/**
	 * 分页查询博客类别信息
	 */
	public List<BlogType> list(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加博客类别信息
	 */
	public Integer add(BlogType blogType);
	
	/**
	 * 修改博客类别信息
	 */
	public Integer update(BlogType blogType);
	
	/**
	 * 删除博客类别信息
	 */
	public Integer delete(Integer id);
}
