package com.ischoolbar.programmer.service;

import com.ischoolbar.programmer.entity.Blogger;


public interface BloggerService {

	/**
	 * ��ѯ������Ϣ
	 */
	public Blogger find();
	
	/**
	 * ͨ���û�����ѯ�û�
	 */
	public Blogger getByUserName(String userName);
	
	/**
	 * ���²�����Ϣ
	 */
	public Integer update(Blogger blogger);
}
