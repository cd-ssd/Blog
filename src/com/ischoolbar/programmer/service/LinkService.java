package com.ischoolbar.programmer.service;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.Link;

/**
 * 友情链接Service接口
 */
public interface LinkService {

	/**
	 * 添加友情链接
	 */
	public int add(Link link);
	
	/**
	 * 修改友情链接
	 */
	public int update(Link link);
	
	/**
	 * 查找友情链接信息
	 */
	public List<Link> list(Map<String,Object> map);	
	
	/**
	 * 获取总记录数
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 删除友情链接
	 */
	public Integer delete(Integer id);
}
