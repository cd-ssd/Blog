<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "form" uri= "http://www.springframework.org/tags/form" %>
<%@taglib prefix= "spring" uri= "http://www.springframework.org/tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/main.css" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-bp.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/navigation.js" ></script>
<script type="text/javascript">
	function checkData(){
		var q=document.getElementById("q").value.trim();
		if(q==null || q==""){
			alert("请输入您要查询的关键字！");
			return false;
		}else{
			return true;
		}
	}
</script>
<div class="row">
	<div class="col-md-12" style="padding-top: 10px">
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		       <a class="navbar-brand" href="${pageContext.request.contextPath}/index.html"><font color="black"><strong>首页</strong></font></a> 
		    </div>

		  
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" >
		       <ul class="nav navbar-nav">

		        <li><a href="${pageContext.request.contextPath}/blogger/aboutMe.html"><font color="black"><strong>博主简介</strong></font></a></li>
		        <li><a href="${pageContext.request.contextPath}/download.html"><font color="black"><strong>系统介绍</strong></font></a></li>
		        <li><a href="${pageContext.request.contextPath}/foreground/system/leftmess.jsp"><font color="black"><strong>提点建议</strong></font></a></li>
		        <li><a href="/Blog/login.jsp"><font color="black"><strong>后台管理</strong></font></a></li>
	<!-- 	        <li><a href="registerForm?request_locale=zh_CN"><font color="black"><strong>中文</strong></font></a></li>
		        <li><a href="registerForm?request_locale=en_US"><font color="black"><strong>英文</strong></font></a></li> -->
		      </ul> 
		      <form action="${pageContext.request.contextPath}/blog/q.html" class="navbar-form navbar-right" role="search" method="post" onsubmit="return checkData()">
		        <div class="form-group" >
		          <input type="text" id="q" name="q" value="" class="form-control" placeholder="请输入要查询的关键字...">
		        </div>
		        <button type="submit" class="btn btn-default">搜索一下</button> 
		      </form>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
	</div>
</div>