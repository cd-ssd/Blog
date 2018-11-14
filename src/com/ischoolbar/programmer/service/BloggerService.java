package com.ischoolbar.programmer.service;

import com.ischoolbar.programmer.entity.Blogger;


public interface BloggerService {

	/**
	 * 查询博主信息
	 */
	public Blogger find();
	
	/**
	 * 通过用户名查询用户
	 */
	public Blogger getByUserName(String userName);
	
	/**
	 * 更新博主信息
	 */
	public Integer update(Blogger blogger);
}
