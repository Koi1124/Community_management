<%@ page import="model.User" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="service.MessageService" %>
<%@ page import="model.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/9
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user=(User)session.getAttribute("curUser");
    String cNum=request.getParameter("cNum");

    MessageService messageService=new MessageService();
    String stuNum = user.getStuNum();
    List<Message> messages = messageService.getMessagesNotRead(stuNum);
%>

<!doctype html>
<html lang="en">

<head>
    <title>社团部落-创建活动</title>
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
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">



    <!--->

    <link rel="stylesheet" type="text/css" href="wangEditor/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="wangEditor/css/HideScrollBar.css">

    <link rel="stylesheet" type="text/css" href="wangEditor/css/common.css">
    <link rel="stylesheet" type="text/css" href="wangEditor/css/index.css">


    <style type="text/css">
        .toolbar {
            border: 1px solid #ccc;
        }
        .text {
            border: 1px solid #ccc;
            height: auto;
        }
    </style>
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
                <a class="btn btn-success update-pro" href="login.do?action=out" title="" target=""><i class="fa fa-rocket"></i> <span>退出登录</span></a>
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
                            <img src="<%=user.getStuSrc()%>" class="img-circle" alt="Avatar">
                            <span><%=user.getuName()%></span>
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
                <div class="panel panel-headline">
                    <form action="Activity" method="post" id="form1" name="form1">
                        <input type="hidden" name="cNum" value="<%=cNum%>">
                        <div class="panel-body">
                            <h3>创建活动</h3>
                            </br>
                            <input class="form-control input-lg" placeholder="活动名称" name="aTitle" type="text">
                            </br>

                            <input type="hidden" name="aContent" id="aContent">
                            <div id="div1" class="toolbar">
                            </div>
                            <div id="div2" class="text" style="height: 400px;">
                                <p>请编辑活动详情</p>
                            </div>
                            </br>
                            <div style="float: right;">
                                <button  type="button" onclick="submitContent()" class="btn btn-primary">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
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
    <script src="assets/scripts/klorofil-common.js"></script>

        <!--   -->
    <script type="text/javascript" src="wangEditor/js/wangEditor.min.js"></script>
    <script type="text/javascript" src='wangEditor/js/jquery-1.10.2.min.js'></script>
    <script type="text/javascript" src='wangEditor/js/bootstrap.min.js'></script>
    <script type="text/javascript" src="wangEditor/js/index.js"></script>
    <script type="text/javascript">
        var E = window.wangEditor;
        var editor = new E('#div1', '#div2');
        editor.customConfig.zIndex=100;
        editor.customConfig.menus = [
            'head',  // 标题
            'bold',  // 粗体
            'fontSize',  // 字号
            'fontName',  // 字体
            'italic',  // 斜体
            'underline',  // 下划线
            'list',  // 列表
            'strikeThrough',  // 删除线
            'foreColor',  // 文字颜色
            'backColor',  // 背景颜色
            'link',  // 插入链接
            'justify',  // 对齐方式
            'quote',  // 引用
            'image',  // 插入图片
            'undo',  // 撤销
            'redo'  // 重复
        ];
        editor.customConfig.uploadImgShowBase64 = true;
        editor.customConfig.showLinkImg = false;
        editor.create()
    </script>
    <script>
        function submitContent()
        {
            var str=editor.txt.html()
            document.getElementById("aContent").value = str;
            document.forms["form1"].submit();
        }
    </script>
        <!--  -->

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

