<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap Contact Form Template</title>

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/form-elements.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/style.css">


        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/static/assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/static/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/static/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/static/assets/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>

		<!-- Top menu -->
		<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="top-navbar-1">
					<ul class="nav navbar-nav navbar-right">
						<li>
							
							<span class="li-social">
								<a href="#"><i class="fa fa-facebook"></i></a> 
								<a href="#"><i class="fa fa-twitter"></i></a> 
								<a href="#"><i class="fa fa-envelope"></i></a> 
								<a href="#"><i class="fa fa-skype"></i></a>
							</span>
						</li>
					</ul>
				</div>
			</div>
		</nav>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            
                            <div class="description">
                            	
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Contact us</h3>
                            		<p>如果你是善于发现的人，那么提点建议给我们:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-envelope"></i>
                        		</div>
                            </div>
                            <div class="form-bottom contact-form">
			                    <form role="form" action="assets/contact.php" method="post">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="contact-email">邮件</label>
			                        	<input type="text" name="email" placeholder="邮件..." class="contact-email form-control" id="email">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="contact-subject">主题</label>
			                        	<input type="text" name="subject" placeholder="主题..." class="contact-subject form-control" id="subject">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="contact-message">详情</label>
			                        	<textarea name="message" placeholder="详情..." class="contact-message form-control" id="message"></textarea>
			                        </div>
			                        <button type="submit" class="btn">发送</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>


        <!-- Javascript -->
        <script src="${pageContext.request.contextPath}/static/assets/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/js/jquery.backstretch.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/js/retina-1.1.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/js/scripts.js"></script>

    </body>

</html>