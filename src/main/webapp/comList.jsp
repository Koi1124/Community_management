<%@ page import="model.Community" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="service.CommunityService" %>
<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: ThinkPad
  Date: 2018/7/9
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Community> communities = new ArrayList<>();
    CommunityService communityService = new CommunityService();
    communities = communityService.getCommFromDB();
    UserService userService = new UserService();

%>
<!DOCTYPE html>
<html>
<head>
    <title>community List</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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

    <link href= "assets/css/communityList.css" type="text/css" rel="stylesheet" />
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
            <a href="userList.jsp"><img src="assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
        </div>

        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
            </div>

            <div class="navbar-btn navbar-btn-right">
                <a class="btn btn-success update-pro" href="login.do?action=out" title="" target=""><i class="fa fa-rocket"></i> <span>退出登录</span></a>
            </div>

            <div id="navbar-menu">

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
                        <a href="login.do?action=reflash" class="">
                            <i class="lnr lnr-user"></i>
                            <span>用户列表</span></a>
                    </li>

                    <li>
                        <a href="applicationReview.jsp" class="">
                            <i class="lnr lnr-alarm"></i>
                            <span>申请审核</span></a>
                    </li>

                    <li>
                        <a href="comList.jsp" class="active">
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
                <h3 class="page-title">社团列表</h3>

                <%for(int i = 0; i < communities.size(); i++){
                %>
                <div class="col-md-8"><!--社团列表-->

                    <div class="panel" style="border-radius: 5px 5px 0 0;">
                        <div style="background-color: #CCCCCC ;border-radius: 5px 5px 0 0;">

                            <div align="right">
                                <%--<button type="button" class="btn btn-danger" >--%>
                                    <%--解散</button>--%>
                                    <button type="button" class="btn btn-danger" onclick="edit('<%=communities.get(i).getcNum()%>')">删除</button>


                            </div>
                        </div>

                        <div class="panel-heading">
                            <h3 class="panel-title"><%out.print(communities.get(i).getcName());%></h3>
                        </div>

                        <div class="panel-body" style="display: none;">
                            <div style="display: inline-block ">
                                <img src=<%=communities.get(i).getcSrc()%> height="120" width="120" style="vertical-align: -120%" />
                            </div>

                            <div style="display: inline-block  " align="left" >
                                <span class="label label-success">类型</span>
                                <a><%out.print(communities.get(i).getcType());%></a>
                                </br>
                                <span class="label label-warning">成立时间</span>
                                <a><%out.print(communities.get(i).getcStartTime());%></a>
                                </br>
                                <span class="label label-danger">社长</span>
                                <a><%=userService.getStuName(communities.get(i).getcStuNum())%></a>
                                </br>
                            </div>

                            <div></br>
                                <span class="label label-info">简介</span>
                                <a><%out.print(communities.get(i).getSyn());%></a>
                            </div>
                        </div>

                        <div id="arrow<%=i%>" class="arrow">
                            <button type="button" id="btn-arrow<%=i%>" class="btn-arrow" >
                                <i id="ico-arrow<%=i%>" class="lnr lnr-chevron-down "></i>
                            </button>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </div>

</body>


    <!-- Javascript -->
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.js"></script>
    <script src="assets/vendor/jquery/jquery.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="assets/scripts/klorofil-common.js"></script>
    <script>


       function edit(id){

            $.get("DeleteCommServlet?cNum="+id,function(msg)
            {
                location.reload();
                alert("SUCCEED");
            })
        }
        <%
            for(int i=0;i<communities.size();i++)
                {
        %>
        $("#btn-arrow<%=i%>").click(function(){

            $("#arrow<%=i%>").prev().toggle();
            if( $("#ico-arrow<%=i%>").hasClass("lnr-chevron-down"))
                $("#ico-arrow<%=i%>").removeClass("lnr-chevron-down").addClass("lnr-chevron-up");
            else
                $("#ico-arrow<%=i%>").removeClass("lnr-chevron-up").addClass("lnr-chevron-down");



        });
        <%
        }
        %>
    </script>

</html>