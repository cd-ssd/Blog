package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ischoolbar.programmer.entity.Comment;
import com.ischoolbar.programmer.entity.PageBean;
import com.ischoolbar.programmer.service.CommentService;
import com.ischoolbar.programmer.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * ����Ա����Controller��
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

	@Resource
	private CommentService commentService;
	
	/**
	 * ��ҳ��ѯ������Ϣ
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,@RequestParam(value="state",required=false)String state,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("state", state); // ����״̬(0��������)
		List<Comment> commentList=commentService.list(map);//������ݿ���state=0״̬������
		Long total=commentService.getTotal(map);//����ܵ�state=0״̬�ĸ�������
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();//�ö����ܹ����Ӱ��Java����ת��json�ַ�������Ϊ
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));//ת��Ϊyyyy-MM-dd��ʽ
		JSONArray jsonArray=JSONArray.fromObject(commentList,jsonConfig);//������ֱ��ת��ΪJSONArray(json����)�ĸ�ʽ
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * ɾ��������Ϣ
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			commentService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * �������
	 */
	@RequestMapping("/review")
	public String review(@RequestParam(value="ids")String ids,@RequestParam(value="state")Integer state,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");//�и��
		for(int i=0;i<idsStr.length;i++){
			Comment comment=new Comment();
			comment.setState(state);//ͨ�����Ϊ1����ͨ��Ϊ2�����յ��״̬ȷ��ѡ�����1����2
			comment.setId(Integer.parseInt(idsStr[i]));
			commentService.update(comment);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);//�ɹ�����ǰ̨result.success()����
		ResponseUtil.write(response, result);//��Ӧ��ǰ̨js��
		return null;
	}
}
