package com.ischoolbar.programmer.dao;

import com.ischoolbar.programmer.entity.Blogger;


public interface BloggerDao {

	/**
	 * ��ѯ������Ϣ
	 * @return
	 */
	public Blogger find();
	
	/**
	 * ͨ���û�����ѯ�û�
	 * @param userName
	 * @return
	 */
	public Blogger getByUserName(String userName);
	
	/**
	 * ���²�����Ϣ
	 * @param blogger
	 * @return
	 */
	public Integer update(Blogger blogger);
}
