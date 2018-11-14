package com.ischoolbar.programmer.service;

import java.util.List;
import java.util.Map;

import com.ischoolbar.programmer.entity.Comment;

/**
 * ����Service�ӿ�
 *
 */
public interface CommentService {

	/**
	 * �������
	 */
	public int add(Comment comment);
	
	/**
	 * �޸�����
	 */
	public int update(Comment comment);
	
	/**
	 * �����û�������Ϣ
	 */
	public List<Comment> list(Map<String,Object> map);
	
	
	/**
	 * ��ȡ�ܼ�¼��
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * ɾ��������Ϣ
	 */
	public Integer delete(Integer id);
}
