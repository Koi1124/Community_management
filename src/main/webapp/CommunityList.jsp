<%@ page import="java.util.List" %>
<%@ page import="model.Community" %>
<%@ page import="service.CommunityService" %>
<%@ page import="model.User" %>
<%@ page import="service.UserService" %>
<%@ page import="service.MessageService" %>
<%@ page import="model.Message" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/8
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User client=(User)session.getAttribute("curUser");
    CommunityService communityService=new CommunityService();
    UserService userService=new UserService();
    List<Community>communityList=communityService.getCommFromDB();

    MessageService messageService=new MessageService();
    String stuNum = client.getStuNum();
    List<Message> messages = messageService.getMessagesNotRead(stuNum);
%>
<!DOCTYPE html>
<html>
<head>
    <title>社团部落-社团列表</title>
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
            <a href="homepage.jsp"><img src="assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
        </div>

        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
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
                            <span class="badge bg-danger" id="NoticeCount"><%=messageService.getCount(stuNum)%></span></a>
                        <ul class="dropdown-menu notifications" style="overflow-y:auto;width:320px; max-height:400px;">
                            <div class="panel-heading b-light bg-light">
                                <strong>通知</strong>
                            </div>
                            <%
                                for(int i = 0; i < messages.size(); i++){%>
                            <li id="<%=i%>" class="media list-group-item" style="display: block" data-stopPropagation="true">
                                        <span class="pull-left thumb-sm text-center">
                                            <a href="<%=messages.get(i).getmSrc()%>" onclick="setNoticeReaded('<%=i%>','<%=messages.get(i).getmNum()%>','0')">

                                                <i class="fa fa-check fa-2x text-success"></i>
                                            </a>
                                        </span>
                                <span class="media-body m-b-none"><%=messages.get(i).getmContent()%>
                                            <br>
                                            <small class="text-muted"><%=messages.get(i).getmTime()%></small></span>
                            </li>
                            <%}
                            %>
                            <div class="panel-footer text-sm">
                                <a href="javascript:void(0)" onclick="setAllNoticeReaded('1','<%=stuNum%>')">全部标记为已读</a>
                            </div>
                        </ul>
                    </li>



                    <li class="dropdown">
                        <a href="UserInfo.jsp">
                            <img src="<%=client.getStuSrc()%>" class="img-circle" alt="Avatar">
                            <span><%=client.getuName()%></span>
                        </a>

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
                        <a href="CommunityList.jsp" class="active">
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

                <div class="col-md-10"><!--社团列表-->

                    <form action="Community">
                        <input type="hidden" name="type" value="normal">
                    <%
                        for (int i=0;i<communityList.size();i++) {
                            Community community=communityList.get(i);
                            int cState=community.getState();
                            int state=communityService.getUStateByNum(client.getStuNum(),community.getcNum());
                            if (state==3){}
                            else {
                                if (cState==0) {}
                                else {
                    %>
                    <div class="panel" style="border-radius: 5px 5px 0 0;">
                        <div style="background-color: #CCCCCC ;border-radius: 5px 5px 0 0;">

                            <div align="right">
                                <%
                                    if (state==2){
                                %>
                                <button type="submit" name="join" value="<%=client.getStuNum()%>&<%=community.getcNum()%>" class="btn btn-success">
                                    加入</button>
                                <%
                                    }else if (state==1){
                                %>
                                <button type="button" class="btn btn-default" disabled="disabled">已加入</button>
                                <%
                                    }else if (state==0){
                                %>
                                <button type="button" class="btn btn-default" disabled="disabled">申请审核中</button>
                                <%
                                    }
                                %>
                                <button type="submit" name="getCNum" class="btn btn-info" value="<%=community.getcNum()%>">
                                    更多</button>
                            </div>
                        </div>

                        <div class="panel-heading">
                            <h3 class="panel-title"><%=community.getcName()%></h3>
                        </div>

                        <div class="panel-body" style="display: none;">
                            <div style="display: inline-block ">
                                <img src=<%=community.getcSrc()%> height="120" width="120" style="vertical-align: -120%" />
                            </div>

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
                        <div id="arrow<%=i%>" class="arrow">
                            <button type="button" id="btn-arrow<%=i%>" class=" btn-arrow" >
                                <i id="ico-arrow<%=i%>" class="lnr lnr-chevron-down "></i>
                            </button>
                        </div>
                    </div>
                    <%
                                }
                            }
                        }
                    %>
                    </form>
                </div>
            </div>
        </div>
    </div>



    <!-- Javascript -->
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.js"></script>
    <script src="assets/vendor/jquery/jquery.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="assets/scripts/klorofil-common.js"></script>
    <script>
        <%
        for (int i=0;i<communityList.size();i++) {
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
    <script>
        //标记1条已读
        function setNoticeReaded(noticeId, mNum, btn) {
            $("#" + noticeId).slideUp();
            event.stopPropagation();
            document.getElementById("NoticeCount").innerText-=1;

            $.ajax({
                url:"MessageServlet?mNum="+mNum+"&btn="+btn,
                type:"POST",
                success:function(e){

                }
            });
        }

        //全部标记为已读
        function setAllNoticeReaded(btn, stuNum){
            $.ajax({
                url:"MessageServlet?btn="+btn+"&stuNum="+stuNum,
                type:"POST",
                success:function(e){
                    location.reload();
                }
            });
        }
        //下拉框查询组件点击区域不关闭下拉框
        //              $("body").on('click','[data-stopPropagation]',function (e) {
        //              e.stopPropagation();
        //  });
        $('.dropdown-menu').on('click', '[data-stopPropagation]', function(e) {
            e.stopPropagation();
        });

    </script>
</body>
</html>
