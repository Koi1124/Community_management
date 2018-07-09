<%@ page import="model.User" %>
<%@ page import="service.CommunityService" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/5
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user=(User) request.getAttribute("UserInfo");
    User client=(User) session.getAttribute("curUser");
    CommunityService communityService=new CommunityService();
    int commCount=communityService.getCountByUser(user);
%>
<!doctype html>
<html lang="en">

<head>
    <title>社团部落-用户信息</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/vendor/linearicons/style.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="assets/css/main.css">

    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">

    <link href= "assets/css/userInfo.css" type="text/css" rel="stylesheet" />
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
</head>

<body>
<!-- WRAPPER -->
<div id="wrapper">
    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand">
            <a href="index.html"><img src="assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
        </div>
        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
            </div>
            <form class="navbar-form navbar-left">
                <div class="input-group">
                    <input type="text" value="" class="form-control" placeholder="Search dashboard...">
                    <span class="input-group-btn"><button type="button" class="btn btn-primary">Go</button></span>
                </div>
            </form>
            <div class="navbar-btn navbar-btn-right">
                <a class="btn btn-success update-pro" href="login.do?action=out" title="" target="">
                    <i class="fa fa-rocket"></i>
                    <span>退出登录</span></a>
            </div>
            <div id="navbar-menu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
                            <i class="lnr lnr-alarm"></i>
                            <span class="badge bg-danger">5</span>
                        </a>
                        <ul class="dropdown-menu notifications">
                            <li><a href="#" class="notification-item"><span class="dot bg-warning"></span>System space is almost full</a></li>
                            <li><a href="#" class="notification-item"><span class="dot bg-danger"></span>You have 9 unfinished tasks</a></li>
                            <li><a href="#" class="notification-item"><span class="dot bg-success"></span>Monthly report is available</a></li>
                            <li><a href="#" class="notification-item"><span class="dot bg-warning"></span>Weekly meeting in 1 hour</a></li>
                            <li><a href="#" class="notification-item"><span class="dot bg-success"></span>Your request has been approved</a></li>
                            <li><a href="#" class="more">See all notifications</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="lnr lnr-question-circle"></i> <span>Help</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Basic Use</a></li>
                            <li><a href="#">Working With Data</a></li>
                            <li><a href="#">Security</a></li>
                            <li><a href="#">Troubleshooting</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="assets/img/user.png" class="img-circle" alt="Avatar"><span><%=client.getuName()%></span><i class="icon-submenu lnr lnr-chevron-down"></i></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><i class="lnr lnr-user"></i> <span>My Profile</span></a></li>
                            <li><a href="#"><i class="lnr lnr-envelope"></i> <span>Message</span></a></li>
                            <li><a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a></li>
                            <li><a href="#"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- END NAVBAR -->
    <!-- LEFT SIDEBAR -->
    <div id="sidebar-nav" class="sidebar">
        <div class="sidebar-scroll">
            <nav>
                <ul class="nav">
                    <li>
                        <a href="homepage.jsp" class="">
                            <i class="lnr lnr-home"></i>
                            <span>主页</span></a>
                    </li>

                    <li>
                        <a href="Community.jsp" class="">
                            <i class="lnr lnr-location"></i>
                            <span>我的社团</span></a>
                    </li>

                    <li>
                        <a href="UserInfo.jsp" class="">
                            <i class="lnr lnr-cog"></i>
                            <span>个人信息</span></a>
                    </li>

                    <li>
                        <a href="CommunityList.jsp" class="">
                            <i class="lnr lnr-dice"></i>
                            <span>社团列表</span></a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <!-- END LEFT SIDEBAR -->

    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <div class="panel panel-profile">
                    <div class="clearfix">
                        <!-- PROFILE HEADER -->
                        <div class="profile-header">
                            <div class="overlay"></div>
                            <div class="profile-main">
                                <!--头像-->
                                <img src=<%=user.getStuSrc()%> class="img-circle" alt="你的头像">
                                <!--用户名-->
                                <h3 class="name"><%=user.getuName()%></h3>
                            </div>

                            <div class="profile-stat">
                                <div class="row">
                                    <div class="col-md-4 stat-item">	</div>
                                    <div class="col-md-4 stat-item">
                                        <%=commCount%> <span>参加的社团</span>
                                    </div>
                                    <div class="col-md-4 stat-item"></div>
                                </div>
                            </div>
                        </div>
                        <!-- END PROFILE HEADER -->

                        <!-- 个人详细信息 -->
                        <div class="profile-detail">
                            <div class="profile-info">
                                <form action="#" method="get">
                                    <div class="row">

                                        <ul class="list-unstyled list-justify">
                                            <li>昵称 <input class="myinput" type="text" value="<%=user.getuName()%>" readonly="true"/></li>
                                            <li>姓名 <input class="myinput" type="text" value="<%=user.getStuName()%>" readonly="true"/></li>
                                            <li>性别 <input class="myinput" type="text" value="<%=user.getStuSex()%>" readonly="true"/></li>
                                            <li>学校 <input class="myinput" type="text" value="<%=user.getStuSchool()%>" readonly="true"/></li>
                                            <li>专业 <input class="myinput" type="text" value="<%=user.getStuProfess()%>" readonly="true"/></li>
                                            <li>电话 <input class="myinput" type="text" value="<%=user.getStuNumber()%>" readonly="true"/></li>
                                            <li>生日 <input class="myinput" type="text" value="<%=user.getStuBirth()%>" readonly="true"/></li>
                                        </ul>
                                    </div>


                                </form>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- END MAIN CONTENT -->
</div>
<!-- END MAIN -->

<div class="clearfix"></div>
<footer>
    <div class="container-fluid"></div>
</footer>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>
</body>

</html>
