<%@ page import="model.User" %>
<%@ page import="model.Community" %>
<%@ page import="service.RemarkService" %>
<%@ page import="service.UserService" %>
<%@ page import="service.CommunityService" %>
<%@ page import="model.Remark" %>
<%@ page import="java.util.List" %>
<%@ page import="service.ActivityService" %>
<%@ page import="model.Activity" %>
<%@ page import="org.omg.IOP.Encoding" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/9
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User client=(User) session.getAttribute("curUser");
    RemarkService remarkService=new RemarkService();
    UserService userService=new UserService();
    CommunityService communityService=new CommunityService();
    String cNum=(String) session.getAttribute("cNum");
    List<Remark> remarks=remarkService.getRMByComm(cNum);
    List<User> users=communityService.getUserByComm(cNum);
    ActivityService activityService=new ActivityService();
    Community community=communityService.getCommByID(cNum);
    List<Activity> activities=activityService.getAcByComm(community.getcName());
    String cName=community.getcName();
    cName= URLEncoder.encode(URLEncoder.encode(cName,"UTF-8"),"UTF-8");
%>
<!doctype html>
<html lang="en">

<head>
    <title>社团部落-管理社团</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/vendor/linearicons/style.css">
    <link rel="stylesheet" href="assets/vendor/chartist/css/chartist-custom.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="assets/css/main.css">

    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">

    <link href= "assets/css/communityManager.css" type="text/css" rel="stylesheet" />
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
                <a class="btn btn-success update-pro" href="login.do?action=out" title="" target=""><i class="fa fa-rocket"></i> <span>退出登录</span></a>
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
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="assets/img/user.png" class="img-circle" alt="Avatar"> <span><%=client.getuName()%></span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
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
                        <a href="Community.jsp" class="active">
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

    <!-- MAIN -->
    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <!--

                    描述：社团详情
                -->
                <h2 class="page-title"><%=community.getcName()%></h2>
                <!--社团简介-->
                <div class="panel panel-headline">
                    <div class="panel-body">
                        <h3>简介</h3>
                        <hr>
                        <p><%=community.getSyn()%></p>
                    </div>
                </div>
                <!--

                    描述：活动列表
               -->
                <div class="panel">
                    <div class="panel-heading" style="padding-bottom: 0;">
                        <h2 class="panel-title">活动</h2>
                        <div class="newact">
                            <a href="createAct.jsp?cName=<%=cName%>">发布活动</a>
                        </div>
                    </div>
                    <div class="panel-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>名称</th>
                                <th>日期</th>
                                <th ></th>
                            </tr>
                            </thead>

                            <form action="Activity">
                                <input type="hidden" name="type" value="manage">
                                <tbody>
                                <%
                                    for (int i=0;i<activities.size();i++) {
                                        Activity activity=activities.get(i);


                                %>
                                <tr>
                                    <td><a href="Activity?aNum=<%=activity.getaNum()%>"><%=activity.getaTitle()%></a></td>
                                    <td><%=activity.getaDate()%></td>
                                    <td style="width:100px;">
                                        <button  type="submit" name="aNum" value="<%=activity.getaNum()%>" class="trans" onmouseover="this.style.color='#3287B2';" onmouseout="this.style.color='';">删除
                                        </button>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                                </tbody>
                            </form>
                        </table>
                    </div>
                </div>

                <div class="row">
                    <!--

                    描述：成员列表
                -->
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-heading">
                                <h2 class="panel-title">成员</h2>
                            </div>
                            <div class="panel-body">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>用户</th>
                                            <th>姓名</th>
                                            <th>职务</th>
                                            <th style="width:80px;"></th>
                                        </tr>
                                        </thead>

                                        <form action="Community">
                                            <input type="hidden" name="type" value="manage">
                                            <tbody>
                                            <%
                                                for (int i=0;i<users.size();i++) {
                                                    User user=users.get(i);
                                            %>
                                            <tr>
                                                <td><a href=User?stuNum=<%=user.getStuNum()%>><%=user.getuName()%></a></td>
                                                <td><%=user.getStuName()%></td>
                                                <%
                                                    String iden=communityService.getIdenByNum(user.getStuNum(),cNum);
                                                    if (iden.equals("1")){
                                                        iden="社长";
                                                    }else iden="成员";
                                                %>
                                                <td><%=iden%></td>
                                                <td style="width:80px;">
                                                    <button type="submit" name="memberDel" value="<%=community.getcNum()%>&<%=user.getStuNum()%>" class="trans" onmouseover="this.style.color='#3287B2';" onmouseout="this.style.color='';">删除
                                                    </button>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                            </tbody>
                                        </form>

                                    </table>
                            </div>
                        </div>
                    </div>
                    <!--

                        描述：留言列表
                    -->
                    <div class="col-md-6">
                        <div class="panel">
                            <div class="panel-heading">
                                <h1 class="panel-title">留言</h1>
                            </div>
                            <div class="panel-body">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>用户</th>
                                        <th>留言</th>
                                        <th>时间</th>
                                        <td style="width:80px;"></td>
                                    </tr>
                                    </thead>

                                    <form action="Remark">
                                        <input type="hidden" name="type" value="manage">
                                        <tbody>
                                        <%
                                            for (int i=0;i<remarks.size();i++) {
                                                Remark remark=remarks.get(i);
                                        %>

                                        <tr>
                                            <%
                                                User remarker=userService.getUserByID(remark.getStuNum());
                                            %>
                                            <td><a href="Remark?rNum=<%=remark.getrNum()%>&type=normal"><%=remarker.getuName()%></a></td>
                                            <td><%=remark.getrContent()%></td>
                                            <td><%=remark.getrDate()%></td>
                                            <td style="width:80px;">
                                                <input type="hidden" name="type" value="remarkDel">
                                                <button type="submit" name="remarkDel" value="<%=remark.getrNum()%>" class="trans" onmouseover="this.style.color='#3287B2';" onmouseout="this.style.color='';">删除
                                                </button>
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </form>
                                </table>
                                <!--

                                    描述：写留言
                                -->

                            </div>
                        </div>

                    </div>
                </div>

                <

            </div>
        </div>
        <!-- END MAIN CONTENT -->
    </div>
    <!-- END MAIN -->
    <!-- END WRAPPER -->

    <!-- Javascript -->
    <script src="assets/vendor/jquery/jquery.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
    <script src="assets/vendor/chartist/js/chartist.min.js"></script>
    <script src="assets/scripts/klorofil-common.js"></script>
    <script>
        $(function() {
            var data, options;

            // headline charts
            data = {
                labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                series: [
                    [23, 29, 24, 40, 25, 24, 35],
                    [14, 25, 18, 34, 29, 38, 44],
                ]
            };

            options = {
                height: 300,
                showArea: true,
                showLine: false,
                showPoint: false,
                fullWidth: true,
                axisX: {
                    showGrid: false
                },
                lineSmooth: false,
            };

            new Chartist.Line('#headline-chart', data, options);


            // visits trend charts
            data = {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                series: [{
                    name: 'series-real',
                    data: [200, 380, 350, 320, 410, 450, 570, 400, 555, 620, 750, 900],
                }, {
                    name: 'series-projection',
                    data: [240, 350, 360, 380, 400, 450, 480, 523, 555, 600, 700, 800],
                }]
            };

            options = {
                fullWidth: true,
                lineSmooth: false,
                height: "270px",
                low: 0,
                high: 'auto',
                series: {
                    'series-projection': {
                        showArea: true,
                        showPoint: false,
                        showLine: false
                    },
                },
                axisX: {
                    showGrid: false,

                },
                axisY: {
                    showGrid: false,
                    onlyInteger: true,
                    offset: 0,
                },
                chartPadding: {
                    left: 20,
                    right: 20
                }
            };

            new Chartist.Line('#visits-trends-chart', data, options);


            // visits chart
            data = {
                labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                series: [
                    [6384, 6342, 5437, 2764, 3958, 5068, 7654]
                ]
            };

            options = {
                height: 300,
                axisX: {
                    showGrid: false
                },
            };

            new Chartist.Bar('#visits-chart', data, options);


            // real-time pie chart
            var sysLoad = $('#system-load').easyPieChart({
                size: 130,
                barColor: function(percent) {
                    return "rgb(" + Math.round(200 * percent / 100) + ", " + Math.round(200 * (1.1 - percent / 100)) + ", 0)";
                },
                trackColor: 'rgba(245, 245, 245, 0.8)',
                scaleColor: false,
                lineWidth: 5,
                lineCap: "square",
                animate: 800
            });

            var updateInterval = 3000; // in milliseconds

            setInterval(function() {
                var randomVal;
                randomVal = getRandomInt(0, 100);

                sysLoad.data('easyPieChart').update(randomVal);
                sysLoad.find('.percent').text(randomVal);
            }, updateInterval);

            function getRandomInt(min, max) {
                return Math.floor(Math.random() * (max - min + 1)) + min;
            }

        });
    </script>
</body>

</html>
