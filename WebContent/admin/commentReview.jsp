<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论审核管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">

	function commentReview(state){
		var selectedRows=$("#dg").datagrid("getSelections");//选中的数据id
		if(selectedRows.length==0){//没有选中任何数据
			 $.messager.alert("系统提示","请选择要删除的数据！");
			 return;
		 }
		 var strIds=[];//用于存放选中的多个数据id
		 for(var i=0;i<selectedRows.length;i++){//遍历多个选中的数据id
			 strIds.push(selectedRows[i].id);//放进数组来
		 }
		 var ids=strIds.join(",");//加入逗号分隔开来
		$.messager.confirm("系统提示","您确定要审核这<font color=red>"+selectedRows.length+"</font>条评论吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/comment/review.do",{ids:ids,state:state},function(result){//选中的id和状态发送给controller
					if(result.success){
						 $.messager.alert("系统提示","提交成功！");
						 $("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","提交失败！");
					}
				},"json");
			} 
  	   });
	}

	
	function formatBlogTitle(val,row){
		if(val==null){
			return "<font color='red'>该博客已被删除！</font>";
		}else{
			return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+val.id+".html'>"+val.title+"</a>";			
		}
	}
	
</script>
</head>
<body style="margin: 1px">
<table id="dg" title="评论审核管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${pageContext.request.contextPath}/admin/comment/list.do?state=0" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="id" width="20" align="center">编号</th>
   		<th field="blog" width="200" align="center" formatter="formatBlogTitle">博客标题</th>
   		<th field="userIp" width="100" align="center">用户IP</th>
   		<th field="content" width="200" align="center">评论内容</th>
   		<th field="commentDate" width="50" align="center">评论日期</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:commentReview(1)" class="easyui-linkbutton" iconCls="icon-ok" plain="true">审核通过</a>
 		<a href="javascript:commentReview(2)" class="easyui-linkbutton" iconCls="icon-no" plain="true">审核不通过</a>
 	</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
 </div>
 
 
</body>
</html>