<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>友情链接管理页面</title>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script> --%>
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/slide.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/flat-ui.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/jquery.nouislider.css">
        
<script type="text/javascript">

	/* var url;

	function deleteLink(){
		var selectedRows=$("#dg").datagrid("getSelections");//获取选中的选框id
		if(selectedRows.length==0){//说明没有选择任何的选框
			 $.messager.alert("系统提示","请选择要删除的数据！");
			 return;
		 }
		 var strIds=[];//将选择的选框用数组装起来，一个也要装
		 for(var i=0;i<selectedRows.length;i++){//遍历出来（因为可能你多选了几个，所以眼遍历）
			 strIds.push(selectedRows[i].id);//获取每个选框的id，然后赋给数组
		 }
		 var ids=strIds.join(",");//数组中加入逗号分隔开来（例如本来[1,2,3,4,'巴德','merge']这样的，变成了1,2,3,4,巴德,merge这样)    其实就是js 数组转为了字符串,然后给后台将字符串转为json数组
		 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){//easyUI之Messager确认框
				if(r){
					$.post("${pageContext.request.contextPath}/admin/link/delete.do",{ids:ids},function(result){//将id一起发送到后台进行逻辑处理，若没有ids发送给后台，则无法删除
						if(result.success){//成功后响应的方法
							 $.messager.alert("系统提示","数据已成功删除！");//easyUI之Messager提示框
							 $("#dg").datagrid("reload");//加载数据
						}else{//反之失败后的响应
							$.messager.alert("系统提示","数据删除失败！");//easyUI之Messager提示框
						}
					},"json");//这是$.post(url,data,success(data, textStatus, jqXHR),dataType),的用法（参数是可选的）
				} 
	   });
	}
	
	function openLinkAddDialog(){//添加一条新的链接信息
		$("#dlg").dialog("open").dialog("setTitle","添加友情链接信息");//跳出框的标题
		url="${pageContext.request.contextPath}/admin/link/save.do";//处理方法的逻辑后台
	}
	
	function openLinkModifyDialog(){//修改一条已有的链接信息
		var selectedRows=$("#dg").datagrid("getSelections");//获取选中的选框id或者某一行
		 if(selectedRows.length==0){//如果没有勾选框，那么会提示你选择
			 $.messager.alert("系统提示","请选择一条要编辑的数据！");
			 return;
		 }
		 var row=selectedRows[0];//获取用户选定的行的集合，默认为0也就是某一行
		 $("#dlg").dialog("open").dialog("setTitle","编辑友情链接信息");//跳出来的编辑框标题
		 $("#fm").form("load",row);//fm：form标签的id，row：得到的一条数据一个对象，easyui中选中一条数据，form中数据接收用<input name="row对应的属性名"/>  简单的说就是打开一条数据编辑，原本的数据会回填(让表单做一个加载的动作)
		 url="${pageContext.request.contextPath}/admin/link/save.do?id="+row.id;//告诉后台选择数据的id号
	 }
	
	function saveLink(){//弹框后的保存选项（点击保存执行的方法）
		 $("#fm").form("submit",{//去做一个提交动作
			url:url,
			onSubmit:function(){
				return $(this).form("validate");//EasyUI 验证表单提交，满足时为true则提交
			},
			success:function(results){
				var results=eval('('+results+')');
				if(results.success){
					$.messager.alert("系统提示","保存成功！");
					resetValue();
					$("#dlg").dialog("close");//保存后自动关闭弹框
					$("#dg").datagrid("reload");//立即加载修改后保存的数据，类似于异步
				}else{
					$.messager.alert("系统提示","保存失败！");
					return;
				}
			}
		 });
	 }
	 
	function resetValue(){
		 $("#linkName").val("");//给对象赋一个空值
		 $("#linkUrl").val("");
		 $("#orderNo").val("");
	 }
	
	 function closeLinkDialog(){
		 $("#dlg").dialog("close");//关闭框easyUI组件
		 resetValue();
	 } */
	 function modifyPassword(){
			$("#fm").form("submit",{
				url:"${pageContext.request.contextPath}/admin/blogger/modifyPassword.do?id=${currentUser.id}",
				onSubmit:function(){
					var newPassword=$("#newPassword").val();
					var newPassword2=$("#newPassword2").val();
					/* if(!$(this).form("validate")){
						return false;
					} */
					if(newPassword!=newPassword2){
						$.messager.alert("系统提示","确认密码输入错误！");
						return false;
					}
					return true;
				},
				success:function(result){
					var result=eval('('+result+')');
					if(result.success){
						$.messager.alert("系统提示","密码修改成功，下一次登录生效！");
						resetValue();
						//$("#dlg").dialog("close");
					}else{
						$.messager.alert("系统提示","密码修改失败！");
						return;
					}
				}
			 });
		}
	 function resetValue(){
			$("#oldPassword").val("");
			$("#newPassword").val("");
			$("#newPassword2").val("");
		}
</script>
</head>
<body style="margin: 1px">

  <div role="tabpanel" class="tab-pane" id="chan">
               
                <div style="padding: 50px 0;margin-top: 50px;background-color: #fff; text-align: right;width: 420px;margin: 50px auto;">
                    <form class="form-horizontal" id="fm" method="post">
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">用户名：</label>
                            <div class="col-xs-5">
                                <input type="" class="form-control input-sm duiqi" id="sKnot" placeholder="" value="${currentUser.userName }" style="margin-top: 7px;">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">新密码：</label>
                            <div class="col-xs-5">
                                <input type="" class="form-control input-sm duiqi" id="sKnot" placeholder="" style="margin-top: 7px;">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">重复密码：</label>
                            <div class="col-xs-5">
                                <input type="" class="form-control input-sm duiqi" id="sKnot" placeholder="" style="margin-top: 7px;">
                            </div>
                        </div>
                        <div class="form-group text-right">
                            <div class="col-xs-offset-4 col-xs-5" style="margin-left: 169px;">
                                <button type="reset"  class="btn btn-xs btn-white">取 消</button>
                                <a type="submit" class="btn btn-xs btn-green" href="javascript:modifyPassword()">保存</a>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
 </table>
 <!-- <div id="tb">
 	 <div>
 	    <a href="javascript:openLinkAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
 		<a href="javascript:openLinkModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:deleteLink()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
 </div> -->
 
 
 <%-- <div id="dlg" class="easyui-dialog" style="width:500px;height:200px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons">
   
   <form id="fm" method="post">
   	<table cellspacing="8px">
   		<tr>
   			<td>用户名：</td>
   			<td><input type="text" id="userName" name="userName" readonly="readonly" value="${currentUser.userName }" style="width: 200px"/></td>
   		</tr>
   		<tr>
   			<td>新密码：</td>
   			<td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox" required="true" style="width: 200px"/></td>
   		</tr>
   		<tr>
   			<td>确认新密码：</td>
   			<td><input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox" required="true" style="width: 200px"/></td>
   		</tr>
   	</table>
   </form>
 </div>
 
 <div id="dlg-buttons">
 	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div> --%>

 

</body>
</html>