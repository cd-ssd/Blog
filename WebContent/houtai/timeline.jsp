<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Bootflat-Admin Template</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="shortcut icon" href="favicon_16.ico"/>
    <link rel="bookmark" href="favicon_16.ico"/>
    <!-- site css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/dist/css/site.min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/dist/js/site.min.js"></script>
  </head>
  <body>
    <!--nav-->
    
    <!--header-->
    <div class="container-fluid">
    <!--documents-->
        <!-- <div class="row row-offcanvas row-offcanvas-left">
          <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
            <ul class="list-group panel">
                <li class="list-group-item"><i class="glyphicon glyphicon-align-justify"></i> <b>SIDE PANEL</b></li>
                <li class="list-group-item"><input type="text" class="form-control search-query" placeholder="Search Something"></li>
                <li class="list-group-item"><a href="index.html"><i class="glyphicon glyphicon-home"></i>Dashboard </a></li>
                <li class="list-group-item"><a href="icons.html"><i class="glyphicon glyphicon-certificate"></i>Icons </a></li>
                <li class="list-group-item"><a href="list.html"><i class="glyphicon glyphicon-th-list"></i>Tables and List </a></li>
                <li class="list-group-item"><a href="forms.html"><i class="glyphicon glyphicon-list-alt"></i>Forms</a></li>
                <li class="list-group-item"><a href="alerts.html"><i class="glyphicon glyphicon-bell"></i>Alerts</a></li>
                <li class="list-group-item"><a href="timeline.html" ><i class="glyphicon glyphicon-indent-left"></i>Timeline</a></li>
                <li class="list-group-item"><a href="calendars.html" ><i class="glyphicon glyphicon-calendar"></i>Calendars</a></li>
                <li class="list-group-item"><a href="typography.html" ><i class="glyphicon glyphicon-font"></i>Typography</a></li>
                <li class="list-group-item"><a href="footers.html" ><i class="glyphicon glyphicon-minus"></i>Footers</a></li>
                <li class="list-group-item"><a href="panels.html" ><i class="glyphicon glyphicon-list-alt"></i>Panels</a></li>
                <li class="list-group-item"><a href="navs.html" ><i class="glyphicon glyphicon-th-list"></i>Navs</a></li>
                <li class="list-group-item"><a href="colors.html" ><i class="glyphicon glyphicon-tint"></i>Colors</a></li>
                <li class="list-group-item"><a href="flex.html" ><i class="glyphicon glyphicon-th"></i>Flex</a></li>
                <li class="list-group-item"><a href="login.html" ><i class="glyphicon glyphicon-lock"></i>Login</a></li>
                <li>
                  <a href="#demo3" class="list-group-item " data-toggle="collapse">Item 3  <span class="glyphicon glyphicon-chevron-right"></span></a>
                  <div class="collapse" id="demo3">
                    <a href="#SubMenu1" class="list-group-item" data-toggle="collapse">Subitem 1  <span class="glyphicon glyphicon-chevron-right"></span></a>
                    <div class="collapse list-group-submenu" id="SubMenu1">
                      <a href="#" class="list-group-item">Subitem 1 a</a>
                      <a href="#" class="list-group-item">Subitem 2 b</a>
                      <a href="#SubSubMenu1" class="list-group-item" data-toggle="collapse">Subitem 3 c <span class="glyphicon glyphicon-chevron-right"></span></a>
                      <div class="collapse list-group-submenu list-group-submenu-1" id="SubSubMenu1">
                        <a href="#" class="list-group-item">Sub sub item 1</a>
                        <a href="#" class="list-group-item">Sub sub item 2</a>
                      </div>
                      <a href="#" class="list-group-item">Subitem 4 d</a>
                    </div>
                    <a href="javascript:;" class="list-group-item">Subitem 2</a>
                    <a href="javascript:;" class="list-group-item">Subitem 3</a>
                  </div>
                </li>
                <li>
                  <a href="#demo4" class="list-group-item " data-toggle="collapse">Item 4  <span class="glyphicon glyphicon-chevron-right"></span></a>
                    <li class="collapse" id="demo4">
                      <a href="" class="list-group-item">Subitem 1</a>
                      <a href="" class="list-group-item">Subitem 2</a>
                      <a href="" class="list-group-item">Subitem 3</a>
                    </li>
                </li>
              </ul>
          </div> -->
          <div class="col-xs-12 col-sm-9 content">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h3 class="panel-title"><a href="javascript:void(0);" class="toggle-sidebar"><span class="fa fa-angle-double-left" data-toggle="offcanvas" title="Maximize Panel"></span></a> Panel Title</h3>
              </div>
              <div class="panel-body">
                <div class="content-row">
                    <h2 class="content-row-title">TimeLine</h2>
                        <div class="row">
                          <div class="col-md-12">
                            <div class="timeline">
                              <dl>
                                <dt>Apr 2014</dt>
                                <dd class="pos-right clearfix">
                                  <div class="circ"></div>
                                  <div class="time">Apr 14</div>
                                  <div class="events">
                                    <div class="pull-left">
                                      <img class="events-object img-rounded" src="${pageContext.request.contextPath}/static/dist/img/photo-1.jpg">
                                    </div>
                                    <div class="events-body">
                                      <h4 class="events-heading">Bootstrap</h4>
                                      <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica.</p>
                                    </div>
                                  </div>
                                </dd>
                                <dd class="pos-left clearfix">
                                  <div class="circ"></div>
                                  <div class="time">Apr 10</div>
                                  <div class="events">
                                    <div class="pull-left">
                                      <img class="events-object img-rounded" src="${pageContext.request.contextPath}/static/dist/img/photo-2.jpg">
                                    </div>
                                    <div class="events-body">
                                      <h4 class="events-heading">Bootflat</h4>
                                      <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica.</p>
                                    </div>
                                  </div>
                                </dd>
                                <dt>Mar 2014</dt>
                                <dd class="pos-right clearfix">
                                  <div class="circ"></div>
                                  <div class="time">Mar 15</div>
                                  <div class="events">
                                    <div class="pull-left">
                                      <img class="events-object img-rounded" src="${pageContext.request.contextPath}/static/dist/img/photo-3.jpg">
                                    </div>
                                    <div class="events-body">
                                      <h4 class="events-heading">Flat UI</h4>
                                      <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica.</p>
                                    </div>
                                  </div>
                                </dd>
                                <dd class="pos-left clearfix">
                                  <div class="circ"></div>
                                  <div class="time">Mar 8</div>
                                  <div class="events">
                                    <div class="pull-left">
                                      <img class="events-object img-rounded" src="${pageContext.request.contextPath}/static/dist/img/photo-4.jpg">
                                    </div>
                                    <div class="events-body">
                                      <h4 class="events-heading">UI design</h4>
                                      <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica.</p>
                                    </div>
                                  </div>
                                </dd>

                              </dl>
                            </div>
                          </div>
                       </div>
                    </div>
              </div><!-- panel body -->
            </div>
        </div><!-- content -->
      </div>
    </div>
    <!--footer-->
    <!-- <div class="site-footer">
      <div class="container">
        <div class="download">
          <span class="download__infos">You simply have to <b>try it</b>.</span>&nbsp;&nbsp;&nbsp;&nbsp;
          <a class="btn btn-primary" href="https://github.com/silverbux/bootflat-admin/archive/master.zip">Download Bootflat-Admin</a>&nbsp;&nbsp;&nbsp;&nbsp;
            SmartAddon BEGIN
            <script type="text/javascript">
            (function() {
            var s=document.createElement('script');s.type='text/javascript';s.async = true;
            s.src='http://s1'+'.smartaddon.com/share_addon.js';
            var j =document.getElementsByTagName('script')[0];j.parentNode.insertBefore(s,j);
            })();
            </script>

            <a href="http://www.smartaddon.com/?share" title="Share Button" onClick="return sa_tellafriend('','bookmarks')"><img alt="Share" src="http://bootflat.github.io/img/share.gif" border="0" /></a>
            SmartAddon END
        </div>
        <hr class="dashed" />
        <div class="row">
          <div class="col-md-4">
            <h3>Get involved</h3>
            <p>Bootflat is hosted on <a href="https://github.com/silverbux/bootflat-admin" target="_blank" rel="external nofollow">GitHub</a> and open for everyone to contribute. Please give us some feedback and join the development!</p>
          </div>
          <div class="col-md-4">
            <h3>Contribute</h3>
            <p>You want to help us and participate in the development or the documentation? Just fork Bootflat on <a href="https://github.com/silverbux/bootflat-admin" target="_blank" rel="external nofollow">GitHub</a> and send us a pull request.</p>
          </div>
          <div class="col-md-4">
            <h3>Found a bug?</h3>
            <p>Open a <a href="https://github.com/silverbux/bootflat-admin/issues" target="_blank" rel="external nofollow">new issue</a> on GitHub. Please search for existing issues first and make sure to include all relevant information.</p>
          </div>
        </div>
        <hr class="dashed" />
        <div class="row">
          <div class="col-md-6">
            <h3>Talk to us</h3>
            <ul>
              <li>Tweet us at @YourTwitter&nbsp;&nbsp;&nbsp;&nbsp;Email us at <span class="connect">info@yourdomain.com</span></li>
              <li>
                <i class="icon" data-icon="&#xe121"></i>
                <i class="icon" data-icon="&#xe10b"></i>
                <i class="icon" data-icon="&#xe110"></i>
                <i class="icon" data-icon="&#xe10e"></i>
              </li>
            </ul>
          </div>
          <div class="col-md-6">
            Begin MailChimp Signup Form
            
            <div id="mc_embed_signup">
            <h3 style="margin-bottom: 15px;">Newsletter</h3>
            <form action="" method="post" id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" class="validate" novalidate>
                <input style="margin-bottom: 10px;" type="email" value="" name="EMAIL" class="email form-control" id="mce-EMAIL" placeholder="email address" required>
                <span class="clear"><input type="submit" value="Subscribe" name="subscribe" id="mc-embedded-subscribe" class="btn btn-primary"></span>
            </form>
            </div>
            End mc_embed_signup
          </div>
        </div>
        <hr class="dashed" />
        <div class="copyright clearfix">
          <p><b>Bootflat</b>&nbsp;&nbsp;&nbsp;&nbsp;<a href="getting-started.html">Getting Started</a>&nbsp;&bull;&nbsp;<a href="index.html">Documentation</a>&nbsp;&bull;&nbsp;Free PSD&nbsp;&bull;&nbsp;<a href="colors.html">Color Picker</a></p>
          <p>Code licensed under , documentation under .</p>
        </div>
      </div>
    </div> -->
  </body>
</html>
