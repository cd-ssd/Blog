<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Expires" content="0"> 
<title>后台登录</title> 
<link href="${pageContext.request.contextPath}/static/bootstrap3/css/login.css" type="text/css" rel="stylesheet"> 
</head> 
<body> 

<div class="login">
    <div class="message">博客后台管理登录</div>
    <div id="darkbannerwrap"></div>
    
    <form action="${pageContext.request.contextPath}/blogger/login.do" method="post">
		<input name="action" value="login" type="hidden">
		<input id="userName" name="userName" placeholder="请输入用户名" required="" type="text" value="${blogger.userName }">
		<hr class="hr15">
		<input id="password" name="password" placeholder="请输入密码" required="" type="password" value="${blogger.password }">
		<hr class="hr15">
		<input value="登录" style="width:100%;" type="submit">
		<hr class="hr20">
	</form>

	
</div>



</body>
</html>