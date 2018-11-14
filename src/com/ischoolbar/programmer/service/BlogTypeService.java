package com.ischoolbar.programmer.service;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.BlogType;

/**
 * ��������Service�ӿ�
 *
 */
public interface BlogTypeService {

	/**
	 * ��ѯ���в������� �Լ���Ӧ�Ĳ�������
	 */
	public List<BlogType> countList();
	
	/**
	 * ��ҳ��ѯ���������Ϣ
	 */
	public List<BlogType> list(Map<String,Object> map);
	
	/**
	 * ��ȡ�ܼ�¼��
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * ��Ӳ��������Ϣ
	 */
	public Integer add(BlogType blogType);
	
	/**
	 * �޸Ĳ��������Ϣ
	 */
	public Integer update(BlogType blogType);
	
	/**
	 * ɾ�����������Ϣ
	 */
	public Integer delete(Integer id);
}
