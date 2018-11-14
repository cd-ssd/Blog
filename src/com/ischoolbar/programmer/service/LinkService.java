package com.ischoolbar.programmer.service;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.Link;

/**
 * ��������Service�ӿ�
 */
public interface LinkService {

	/**
	 * �����������
	 */
	public int add(Link link);
	
	/**
	 * �޸���������
	 */
	public int update(Link link);
	
	/**
	 * ��������������Ϣ
	 */
	public List<Link> list(Map<String,Object> map);	
	
	/**
	 * ��ȡ�ܼ�¼��
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * ɾ����������
	 */
	public Integer delete(Integer id);
}
