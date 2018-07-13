<%@ page import="service.CommunityService" %>
<%@ page import="model.User" %>
<%@ page import="model.Community" %>
<%@ page import="java.util.List" %>
<%@ page import="service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    CommunityService communityService=new CommunityService();
    UserService userService=new UserService();
    User client=(User)session.getAttribute("curUser");
    String stuNum=client.getStuNum();
    List<Community>communities=communityService.getCommByUID(stuNum);
    List<Community>communityList=communityService.getManCommByID(stuNum);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
    <head>
        <title>社团部落-我的社团</title>
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
                    <a href="homepage.jsp">
                        <img src="assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
                </div>
                <div class="container-fluid">
                    <div class="navbar-btn">
                        <button type="button" class="btn-toggle-fullwidth">
                            <i class="lnr lnr-arrow-left-circle"></i>
                        </button>
                    </div>
                    <form class="navbar-form navbar-left" action="Search" method="post">
                        <div class="input-group">
                            <input type="text" value="" name="search" class="form-control" placeholder="请输入要查询的内容">
                            <span class="input-group-btn">
                                <button type="submit" class="btn btn-primary">搜索</button></span>
                        </div>
                    </form>
                    <div class="navbar-btn navbar-btn-right">
                        <a class="btn btn-success update-pro" href="login.do?action=out" title="Upgrade to Pro" target="_blank">
                            <i class="fa fa-rocket"></i>
                            <span>退出登录</span></a>
                    </div>
                    <div id="navbar-menu">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
                                    <i class="lnr lnr-alarm"></i>
                                    <span class="badge bg-danger">5</span></a>
                                <ul class="dropdown-menu notifications">
                                    <li>
                                        <a href="#" class="notification-item">
                                            <span class="dot bg-warning"></span>System space is almost full</a>
                                    </li>
                                    <li>
                                        <a href="#" class="notification-item">
                                            <span class="dot bg-danger"></span>You have 9 unfinished tasks</a>
                                    </li>
                                    <li>
                                        <a href="#" class="notification-item">
                                            <span class="dot bg-success"></span>Monthly report is available</a>
                                    </li>
                                    <li>
                                        <a href="#" class="notification-item">
                                            <span class="dot bg-warning"></span>Weekly meeting in 1 hour</a>
                                    </li>
                                    <li>
                                        <a href="#" class="notification-item">
                                            <span class="dot bg-success"></span>Your request has been approved</a>
                                    </li>
                                    <li>
                                        <a href="#" class="more">See all notifications</a></li>
                                </ul>
                            </li>

                            <li class="dropdown">
                                <a href="UserInfo.jsp">
                                    <img src="<%=client.getStuSrc()%>" class="img-circle" alt="Avatar">
                                    <span><%=client.getuName()%></span>
                                </a>

                            </li>
                            <!-- <li>
                            <a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a></li> -->
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
                        <h3 class="page-title">我的社团</h3>
                        <div class="newact" style="text-align:right; font-size:18px;">
                            <a href="createCom.jsp" style="color:#00AAFF;" onmouseover="this.style.color='#3287B2';" onmouseout="this.style.color='#00AAFF';">
                                <i class="lnr lnr-leaf"></i>&nbsp创建社团</a>

                        </div>
                        <div class="custom-tabs-line tabs-line-bottom left-aligned">
							<ul class="nav" role="tablist">
								<li class="active"><a href="#join" role="tab" data-toggle="tab">我加入的社团</a></li>
								<li><a href="#manage" role="tab" data-toggle="tab">我管理的社团</a></li>					

							</ul>
						</div>

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="join">
                            <form action="Community">
                            <% 	for(int i=0;i<communities.size();i++){

                                Community community=communities.get(i);
                                String identity=communityService.getIdenByNum(stuNum,community.getcNum());
                                int state=community.getState();
                                if (state==1) {
                            %>
                            <div class="join">

                                <div class="col-md-6"><!--我加入的-->

                                    <!--一行两个-->
                                    <div class="panel" style="border-radius: 5px 5px 0 0;">
                                        <div style="background-color: #CCCCCC ;border-radius: 5px 5px 0 0;">
                                            <div align="right">
                                                <input type="hidden" name="type" value="normal">
                                                <%
                                                    if (identity.equals("2")||identity.equals("3")) {
                                                %>
                                                <button type="submit" name="quit" value="<%=community.getcNum()%>&<%=stuNum%>" class="btn btn-danger">
                                                    <i class="btn-danger"></i>退出</button>
                                                <%
                                                    } else if (identity.equals("1")) {
                                                %>
                                                <button type="button" class="btn btn-danger" onclick="edit('<%=community.getcNum()%>')">
                                                    <i class="btn-danger"></i>解散</button>
                                                <%
                                                    }
                                                %>
                                                <button type="submit" name="getCNum" value="<%=community.getcNum()%>" class="btn btn-info">
                                                    更多</button>
                                            </div>
                                        </div>
                                        <div class="panel-heading">
                                            <h3 class="panel-title"><%=community.getcName()%></h3></div>
                                        <div class="panel-body">
                                            <div style="display: inline-block ">
                                                <img src="<%=community.getcSrc() %>" height="120" width="120" style="vertical-align: -120%" /></div>
                                            <div style="display: inline-block  " align="left" >
                                                <span class="label label-success">类型</span>
                                                <a><%=community.getcType() %></a>
                                                </br>
                                                <span class="label label-warning">成立时间</span>
                                                <a><%=community.getcStartTime() %></a>
                                                </br>
                                                <span class="label label-danger">社长</span>
                                                <a><%=userService.getStuName(community.getcStuNum())%></a>
                                                </br>
                               
                                            </div>
                                            <div></br>
                                                <span class="label label-info">简介</span>
                                                <a><%=community.getSyn()%></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                                    <%
                                    }
                                }
                            %>
                            </form>
                        </div>


                        <div class="tab-pane fade" id="manage">
                            <form action="Community">
                                <% 	for(int i=0;i<communityList.size();i++){

                                    Community community=communityList.get(i);
                                %>
                        <div class="row">
                            <!--一行-->

                            <div class="col-md-6"><!--我管理的-->

                                    <!--一行两个-->
                                    <div class="panel" style="border-radius: 5px 5px 0 0;">
                                        <div style="background-color: #CCCCCC ;border-radius: 5px 5px 0 0;">
                                            <div align="right">
                                                <input type="hidden" name="type" value="manage">
                                                <%
                                                    if (community.getState()==1){
                                                %>
                                                <button type="submit" name="getCNum" value="<%=community.getcNum()%>" class="btn btn-warning">
                                                    <i class="btn-warning"></i>管理</button>
                                                <%
                                                    } else if (community.getState()==0) {
                                                %>
                                                <button type="button" class="btn btn-warning" disabled="disabled">申请审核中</button>
                                                <%
                                                    }
                                                %>
                                            </div>
                                        </div>
                                        <div class="panel-heading">
                                            <h3 class="panel-title"><%=community.getcName()%></h3></div>
                                        <div class="panel-body">
                                            <div style="display: inline-block ">
                                                <img src="<%=community.getcSrc()%>" height="120" width="120" style="vertical-align: -120%" /></div>
                                            <div style="display: inline-block  " align="left" >
                                                <span class="label label-success">类型</span>
                                                <a><%=community.getcType()%></a>
                                                </br>
                                                <span class="label label-warning">成立时间</span>
                                                <a><%=community.getcStartTime()%></a>
                                                </br>
                                                <span class="label label-danger">社长</span>
                                                <a><%=userService.getStuName(community.getcStuNum())%></a>
                                                </br>
                                            </div>
                                            <div></br>
                                                <span class="label label-info">简介</span>
                                                <a><%=community.getSyn()%></a>
                                            </div>
                                        </div>
                                    </div>

                            </div>
                        </div>
                                <%
                                    }
                                %>
                            </form>
                        </div>

                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT --></div>
            <!-- END MAIN -->

        <!-- END WRAPPER -->
        <!-- Javascript -->
        <script src="assets/vendor/jquery/jquery.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/vendor/chartist/js/chartist.min.js"></script>
        <script src="assets/scripts/klorofil-common.js"></script>
        <script src="test.js"></script>
        <script>
            function edit(id){
                $.get("DeleteCommServlet?cNum="+id,function(msg)
                {
                    location.reload();
                    alert("SUCCEED");
                })
            }



            $(function() {
                var options;

                var data = {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                    series: [[200, 380, 350, 320, 410, 450, 570, 400, 555, 620, 750, 900], ]
                };

                // line chart
                options = {
                    height: "300px",
                    showPoint: true,
                    axisX: {
                        showGrid: false
                    },
                    lineSmooth: false,
                };

                new Chartist.Line('#demo-line-chart', data, options);

                // bar chart
                options = {
                    height: "300px",
                    axisX: {
                        showGrid: false
                    },
                };

                new Chartist.Bar('#demo-bar-chart', data, options);

                // area chart
                options = {
                    height: "270px",
                    showArea: true,
                    showLine: false,
                    showPoint: false,
                    axisX: {
                        showGrid: false
                    },
                    lineSmooth: false,
                };

                new Chartist.Line('#demo-area-chart', data, options);

                // multiple chart
                var data = {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                    series: [{
                        name: 'series-real',
                        data: [200, 380, 350, 320, 410, 450, 570, 400, 555, 620, 750, 900],
                    },
                    {
                        name: 'series-projection',
                        data: [240, 350, 360, 380, 400, 450, 480, 523, 555, 600, 700, 800],
                    }]
                };

                var options = {
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

                new Chartist.Line('#multiple-chart', data, options);

            });
        </script>
    </body>

</html>
