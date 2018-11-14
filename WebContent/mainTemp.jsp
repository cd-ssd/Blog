<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle }-Powered by java1234</title>

<link href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/static/bootstrap3/css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<!-- font-awesome icons -->
<link href="${pageContext.request.contextPath}/static/bootstrap3/css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!--skycons-icons-->
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/skycons.js"></script>
<!--//skycons-icons-->
<link href="${pageContext.request.contextPath}/static/bootstrap3/css/custom.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/bootstrap3/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
  <script src="js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/modernizr.custom.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery.sparkline.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/Chart.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/clndr.css" type="text/css" />
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/underscore-min.js" type="text/javascript"></script>
<script src= "${pageContext.request.contextPath}/static/bootstrap3/js/moment-2.2.1.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/clndr.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/site.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/classie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
				<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery.nicescroll.js"></script>
				<script src="${pageContext.request.contextPath}/static/bootstrap3/js/scripts.js"></script>
				<link href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap3/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap3/css/demo.css">
<link href="${pageContext.request.contextPath}/static/bootstrap3/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/style.css">



<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/blog.css">
<link href="/Blog/favicon.ico" rel="SHORTCUT ICON">
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap3/css/style1.css" />
<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap3/css/jquery.jscrollpane.css" rel="stylesheet" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery.easing.1.3.js"></script>
<!-- the mousewheel plugin - optional to provide mousewheel support -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery.mousewheel.js"></script>
<!-- the jScrollPane script -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery.jscrollpane.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery.transform-0.9.3.min_.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-ui.min.js"></script>

<script type="text/javascript">
    /* <![CDATA[ */
    $(function() {
        /** This code runs when everything has been loaded on the page */
        /* Inline sparklines take their values from the contents of the tag */
        $('.inlinesparkline').sparkline(); 

        /* Sparklines can also take their values from the first argument passed to the sparkline() function */
        var myvalues = [10,8,5,7,4,4,1];
        $('.dynamicsparkline').sparkline(myvalues);

        /* The second argument gives options such as specifying you want a bar chart11 */
        $('.dynamicbar').sparkline(myvalues, {type: 'bar', barColor: '#fff'} );

        /* Use 'html' instead of an array of values to pass options to a sparkline with data in the tag */
        $('.inlinebar').sparkline('html', {type: 'bar', barColor: '#fff'} );

    });
    /* ]]> */
    </script>
<script>
					var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
						showLeftPush = document.getElementById( 'showLeftPush' ),
						body = document.body;
						
					showLeftPush.onclick = function() {
						classie.toggle( this, 'active' );
						classie.toggle( body, 'cbp-spmenu-push-toright' );
						classie.toggle( menuLeft, 'cbp-spmenu-open' );
						disableOther( 'showLeftPush' );
					};
					

					function disableOther( button ) {
						if( button !== 'showLeftPush' ) {
							classie.toggle( showLeftPush, 'disabled' );
						}
					}
				</script>
				
				
<script>
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?aa5c701f4f646931bf78b6f40b234ef5";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
</script>

<script type="text/javascript">
	$(function() {
		var $menu			= $('#mb_menu'),
		$menuItems			= $menu.children('a'),
		$mbWrapper			= $('#mb_content_wrapper'),
		$mbClose			= $mbWrapper.children('.mb_close'),
		$mbContentItems		= $mbWrapper.children('.mb_content'),
		$mbContentInnerItems= $mbContentItems.children('.mb_content_inner');
		$mbPattern			= $('#mb_pattern'),
		$works				= $('#mb_imagelist > li'),
		$mb_bgimage			= $('#mb_background > img'),
		
		Menu		 		= (function(){
			
			var init		= function() {
				preloadImages();
				initPlugins();
				initPattern();
				initEventsHandler();
			},
			//preloads the images for the work area (data-bgimg attr)
			preloadImages	= function() {
				$works.each(function(i) {
					$('<img/>').attr('src' , $(this).children('img').data('bgimg'));
				});
			},
			//initialise the jScollPane (scroll plugin)
			initPlugins		= function() {
				$mbContentInnerItems.jScrollPane({
					verticalDragMinHeight: 40,
					verticalDragMaxHeight: 40
				});
			},
			/*
				draws 16 boxes on a specific area of the page.
				we randomly calculate the top, left, and rotation angle for each one of them
			 */
			initPattern		= function() {
				for(var i = 0; i < 16 ; ++i) {
					//random opacity, top, left and angle
					var o		= 0.1,
					t		= Math.floor(Math.random()*196) + 5, // between 5 and 200
					l		= Math.floor(Math.random()*696) + 5, // between 5 and 700
					a		= Math.floor(Math.random()*101) - 50; // between -50 and 50
							
					$el		= $('<div>').css({
						opacity			: o,
						top				: t + 'px',
						left			: l + 'px'
					});
						
					if (!$.browser.msie)
						$el.transform({'rotate'	: a + 'deg'});
						
					$el.appendTo($mbPattern);
				}
				$mbPattern.children().draggable(); //just for fun
			},
			/*
				when the User closes a content item, we move the boxes back to the original place,
				with new random values for top, left and angle though
			 */
			disperse 		= function() {
				$mbPattern.children().each(function(i) {
					//random opacity, top, left and angle
					var o			= 0.1,
					t			= Math.floor(Math.random()*196) + 5, // between 5 and 200
					l			= Math.floor(Math.random()*696) + 5, // between 5 and 700
					a			= Math.floor(Math.random()*101) - 50; // between -50 and 50
					$el			= $(this),
					param		= {
						width	: '50px',
						height	: '50px',
						opacity	: o,
						top		: t + 'px',
						left	: l + 'px'
					};
							
					if (!$.browser.msie)
						param.rotate	= a + 'deg';
							
					$el.animate(param, 1000, 'easeOutExpo');
				});
			},
			initEventsHandler	= function() {
				/*
					click a link in the menu
				 */
				$menuItems.bind('click', function(e) {
					var $this	= $(this),
					pos		= $this.index(),
					speed	= $this.data('speed'),
					easing	= $this.data('easing');
					//if an item is not yet shown
					if(!$menu.data('open')){
						//if current animating return
						if($menu.data('moving')) return false;
						$menu.data('moving', true);
						$.when(openItem(pos, speed, easing)).done(function(){
							$menu.data({
								open	: true,
								moving	: false
							});
							showContentItem(pos);
							$mbPattern.children().fadeOut(500);
						});
					}
					else
						showContentItem(pos);
					return false;
				});
					
				/*
					click close makes the boxes animate to the top of the page
				 */
				$mbClose.bind('click', function(e) {
					$menu.data('open', false);
					/*
						if we would want to show the default image when we close:
						changeBGImage('images/default.jpg');
					 */
					$mbPattern.children().fadeIn(500, function() {
						$mbContentItems.hide();
						$mbWrapper.hide();
					});
						
					disperse();
					return false;
				});
					
				/*
					click an image from "Works" content item,
					displays the image on the background
				 */
				$works.bind('click', function(e) {
					var source	= $(this).children('img').data('bgimg');
					changeBGImage(source);
					return false;
				});
						
			},
			/*
				changes the background image
			 */
			changeBGImage		= function(img) {
				//if its the current one return
				if($mb_bgimage.attr('src') === img || $mb_bgimage.siblings('img').length > 0)
					return false;
							
				var $itemImage = $('<img src="'+img+'" alt="Background" class="mb_bgimage" style="display:none;"/>');
				$itemImage.insertBefore($mb_bgimage);
					
				$mb_bgimage.fadeOut(1000, function() {
					$(this).remove();
					$mb_bgimage = $itemImage;
				});
				$itemImage.fadeIn(1000);
			},
			/*
				This shows a content item when there is already one shown:
			 */
			showContentItem		= function(pos) {
				$mbContentItems.hide();
				$mbWrapper.show();
				$mbContentItems.eq(pos).show().children('.mb_content_inner').jScrollPane();
			},
			/*
				moves the boxes from the top to the center of the page,
				and shows the respective content item
			 */
			openItem			= function(pos, speed, easing) {
				return $.Deferred(
				function(dfd) {
					$mbPattern.children().each(function(i) {
						var $el			= $(this),
						param		= {
							width	: '100px',
							height	: '100px',
							top		: 154 + 100 * Math.floor(i/4),
							left	: 200 + 100 * (i%4),
							opacity	: 1
						};
								
						if (!$.browser.msie)
							param.rotate	= '0deg';
								
						$el.animate(param, speed, easing, dfd.resolve);
					});
				}
			).promise();
			};
				
			return {
				init : init
			};
			
		})();
	
		/*
			call the init method of Menu
		 */
		Menu.init();
	});
</script>


<style type="text/css">
	  body {
        padding-top: 10px;
        padding-bottom: 40px;
      }
      
</style>
</head>
<body>

<div class="container">
	<jsp:include page="/foreground/common/head.jsp"/>
	
	<jsp:include page="/foreground/common/menu.jsp"/>
	
	<div id="mb_pattern" class="mb_pattern"></div>
	
	<div id="mb_menu" class="mb_menu">
	<a href="#" data-speed="1000" data-easing="easeOutBack">About</a>
	<a href="#" data-speed="1000" data-easing="easeInExpo">Work</a>
	<a href="#" data-speed="1000" data-easing="easeOutBack">Media</a>
	<a href="#" data-speed="1000" data-easing="easeInExpo">Contact</a>
</div>



	
	<div class="row">
		<div class="col-md-9">
			<jsp:include page="${mainPage }"></jsp:include>
		</div>
	</div>
		
		<div class="main-content">
		<!--left-fixed -navigation-->
		<div class="sidebar" role="navigation">
            <div class="navbar-collapse">
				<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right dev-page-sidebar mCustomScrollbar _mCS_1 mCS-autoHide mCS_no_scrollbar" id="cbp-spmenu-s1">
					<div class="scrollbar scrollbar1">
						<ul class="nav" id="side-menu">
							<li>
								<a href="index.html" class="active"><i class="fa fa-home nav_icon"></i>导航</a>
							</li>
							<li>
								<a href="#"><i class="fa fa-cogs nav_icon"></i>博主信息 <span class="fa arrow"></span></a>
								<ul style="list-style-type: none">
						<li style="color:#F00"><span>博主:Ahobby</span></li>
							<li style="color:#F00"><span>性别:男</span></li>
							<li style="color:#F00"><span>爱好:看书,旅游,健身，睡觉...</span></li>
							<li style="color:#F00"><span>${blogger.nickName }</span></li>
							<li style="color:#F00"><span>${blogger.sign }</span></li>
						
					</ul>
								
								<!-- /nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-book nav_icon"></i>按日志类别 <span class="fa arrow"></span></a>
								<ul style="list-style-type: none">
					  <c:forEach var="blogTypeCount" items="${blogTypeCountList }">
							<li><span><a href="${pageContext.request.contextPath}/index.html?typeId=${blogTypeCount.id }">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a></span></li>
						</c:forEach> 
					</ul>
								
								<!-- /nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-th-large nav_icon"></i>按日志日期</a>
							</li>
							<ul style="list-style-type: none">
						  <c:forEach var="blogCount" items="${blogCountList }">
							<li><span><a href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogCount.releaseDateStr }">${blogCount.releaseDateStr }(${blogCount.blogCount })</a></span></li>
						</c:forEach> 
					</ul>
							
							<li>
								<a href="#"><i class="fa fa-envelope nav_icon"></i>广告链接<span class="fa arrow"></span></a>
								<ul style="list-style-type: none">
						 <c:forEach var="link" items="${linkList }">
							<li><span><a href="${link.linkUrl }" target="_blank">${link.linkName }</a></span></li>
						</c:forEach>  
					</ul>
								<!-- //nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-location-arrow nav_icon"></i>Maps</a>
							</li>
							
							
								</ul>
								<!-- //nav-second-level -->
							</li>
						</ul>
					</div>
					<!-- //sidebar-collapse -->
				</nav>
			</div>
		</div>
		
	
	<jsp:include page="/foreground/common/foot.jsp"/>
</div>
</body>
</html>