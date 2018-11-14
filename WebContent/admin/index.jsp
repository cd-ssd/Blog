<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>


<title>H-ui.admin v3.1</title>
<meta name="keywords" content="">
<meta name="description" content="">

<script type="text/javascript">
var url;

function openTab(text,url,iconCls){
	if($("#tabs").tabs("exists",text)){
		$("#tabs").tabs("select",text);
	}else{
		var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/"+url+"'></iframe>";
		$("#tabs").tabs("add",{
			title:text,
			iconCls:iconCls,
			closable:true,
			content:content
		});
	}
}


function openPasswordModifyDialog(){
	$("#dlg").dialog("open").dialog("setTitle","修改密码");
	url="${pageContext.request.contextPath}/admin/blogger/modifyPassword.do?id=${currentUser.id}";
}

function modifyPassword(){
	$("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			var newPassword=$("#newPassword").val();
			var newPassword2=$("#newPassword2").val();
			if(!$(this).form("validate")){
				return false;
			}
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
				$("#dlg").dialog("close");
			}else{
				$.messager.alert("系统提示","密码修改失败！");
				return;
			}
		}
	 });
}

function closePasswordModifyDialog(){
	resetValue();
	$("#dlg").dialog("close");
}

function resetValue(){
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#newPassword2").val("");
}

function logout(){
	$.messager.confirm("系统提示","您确定要退出系统吗？",function(r){
		if(r){
			window.location.href='${pageContext.request.contextPath}/admin/blogger/logout.do';
		} 
	 });
}

function refreshSystem(){
	$.post("${pageContext.request.contextPath}/admin/system/refreshSystem.do",{},function(result){
		if(result.success){
			alert("已成功刷新系统缓存！");
		}else{
			alert("刷新系统缓存失败！");
		}
	},"json");
}
</script>

</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">博客后台管理</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">认证</span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="article_add('添加资讯','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
							<li><a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
							<li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
							<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
					</ul>
				</li>
			</ul>
		</nav>
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>超级管理员</li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">${currentUser.userName }<i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
						<li><a href="${pageContext.request.contextPath}/">博客主页</a></li>
						<li><a href="${pageContext.request.contextPath}/login.jsp">退出</a></li>
				</ul>
			</li>
				<li id="Hui-msg"> <a href="javascript:;" onClick="message()" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 常用操作<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="writeBlog.jsp" data-title="攥写博客" href="javascript:void(0)">攥写博客</a></li>
			</ul>
			<ul>
					<li><a data-href="commentReview.jsp" data-title="审核评论" href="javascript:void(0)">审核评论</a></li>
			</ul>
			<!-- <ul>
					<li><a data-href="timeline.jsp" data-title="读者来信" href="javascript:void(0)">读者来信</a></li>
			</ul> -->
		</dd>
	</dl>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 博客管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="writeBlog.jsp" data-title="攥写博客" href="javascript:void(0)">攥写博客</a></li>
			</ul>
			<ul>
					<li><a data-href="blogManage.jsp" data-title="博客信息管理" href="javascript:void(0)">博客信息管理</a></li>
			</ul>
		</dd>
	</dl>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe620;</i> 博客类别管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="blogTypeManage.jsp" data-title="博客类别信息管理" href="javascript:void(0)">博客类别信息管理</a></li>
					
			</ul>
		</dd>
	</dl>
		<dl id="menu-comments">
			<dt><i class="Hui-iconfont">&#xe622;</i> 评论管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="commentReview.jsp" data-title="评论审核" href="javascript:;">评论审核</a></li>
					<li><a data-href="commentManage.jsp" data-title="评论信息管理" href="javascript:void(0)">评论信息管理</a></li>
			</ul>
		</dd>
	</dl>
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 个人信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="modifyInfo.jsp" data-title="修改个人信息" href="javascript:;">修改个人信息</a></li>
					
			</ul>
		</dd>
	</dl>
		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="linkManage.jsp" data-title="友情链接管理" href="javascript:void(0)">友情链接管理</a></li>
					<li><a data-href="modifypass.jsp" data-title="修改密码" href="javascript:openPasswordModifyDialog()" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a></li>
					<li><a data-href="javascript:refreshSystem()" data-title="刷新系统缓存" href="javascript:refreshSystem()" data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">刷新系统缓存</a></li>
					<li><a data-href="system-shielding.jsp" data-title="屏蔽词" href="javascript:void(0)">屏蔽词</a></li>
					<li><a data-href="${pageContext.request.contextPath}/login.jsp" data-title="安全退出" href="${pageContext.request.contextPath}/login.jsp" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a></li>
			</ul>
		</dd>
	</dl>
		<dl id="menu-tongji">
			<dt><i class="Hui-iconfont">&#xe61a;</i> 系统统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="charts-1.jsp" data-title="折线图" href="javascript:void(0)">折线图</a></li>
					<li><a data-href="charts-2.jsp" data-title="时间轴折线图" href="javascript:void(0)">时间轴折线图</a></li>
					<li><a data-href="charts-3.jsp" data-title="区域图" href="javascript:void(0)">区域图</a></li>
					<li><a data-href="charts-4.jsp" data-title="柱状图" href="javascript:void(0)">柱状图</a></li>
					<li><a data-href="charts-5.jsp" data-title="饼状图" href="javascript:void(0)">饼状图</a></li>
					
			</ul>
		</dd>
	</dl>
		
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面" data-href="welcome.jsp">我的桌面</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="${pageContext.request.contextPath}/admin/welcome.jsp"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
$(function(){
	/*$("#min_title_list li").contextMenu('Huiadminmenu', {
		bindings: {
			'closethis': function(t) {
				console.log(t);
				if(t.find("i")){
					t.find("i").trigger("click");
				}		
			},
			'closeall': function(t) {
				alert('Trigger was '+t.id+'\nAction was Email');
			},
		}
	});*/
});
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div><ul><li>博主:<span  style="color:#F00">Ahobby</span></li><li>性别:<span style="color:#F00">男</span></li><li>爱好:<span style="color:#F00">看书,旅游,健身，睡觉...</span></li><li>个性签名:<span style="color:#F00">编程是一门技术，编好是一门艺术！</span></li></div>'
	});
}

function message(){
	layer.open({
		type: 1,
		area: ['600px','400px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '消息提醒',
		content: '<div><center><a href="commentReview.jsp">有一条新评论赶快处理哦!</a></center></div>'
	});
}

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 

<div id="dlg" class="easyui-dialog" style="width:400px;height:200px;padding: 10px 20px"
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
 </div>

</body>
</html>