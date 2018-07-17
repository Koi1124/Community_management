<%@ page import="service.CommunityService" %>
<%@ page import="service.ActivityService" %>
<%@ page import="service.MessageService" %>
<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/5
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Activity activity=new Activity();
    ActivityService activityService=new ActivityService();
    String act=request.getParameter("act");
    if (act!=null){
        Activity activityByPara=activityService.getAcByID(act);
        activity.setaNum(act);
        activity.setaContent(activityByPara.getaContent());
        activity.setaTitle(activityByPara.getaTitle());
        activity.setaDate(activityByPara.getaDate());
        activity.setcNum(activityByPara.getcNum());
        activity.setView(activityByPara.getView());
    }else activity=(Activity)request.getAttribute("selectedActInfo");
    CommunityService communityService=new CommunityService();
    User client=(User)session.getAttribute("curUser");
    String cNum=activity.getcNum();
    activity.setView(activity.getView()+1);
    activityService.doUpdateView(activity);
    session.setAttribute("passAct",activity);

    UserService userService=new UserService();
    MessageService messageService=new MessageService();
    String stuNum = client.getStuNum();
    List<Message> messages = messageService.getMessagesNotRead(stuNum);
%>


<!doctype html>
<html lang="en">

<head>
    <title>社团部落-活动详情</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/vendor/linearicons/style.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="assets/css/main.css">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="assets/css/demo.css">
    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
    <link href= "assets/css/activityCom.css" type="text/css" rel="stylesheet"/>
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
                <a class="btn btn-success update-pro" href="login.do?action=out" title="" target="">
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
                    <!-- <li>
                        <a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
                    </li> -->
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

                    <div class="panel-body">
                        <h1><%=activity.getaTitle()%></h1>
                        <a href="Community?getCNum=<%=cNum%>&type=normal"><h3><%=communityService.getCNameByCommID(cNum)%></h3></a>
                        <h5><%=activity.getaDate()%></h5>
                        <p><%=activity.getaContent()%></p>
                        <p>访问量：<%=activity.getView()%></p>
                    </div>
                </div>
            </div>

            <%
                List<ActComment> commentList=activityService.getCommentByANum(activity.getaNum());
            %>

            <div class="container-fluid">
                <div class="common">
                    <div class="col-md-12">
                        <div class="panel panel-headline">
                            <div class="panel-heading">
                                <h3 class="panel-title">评论<font style="margin-left: 20px;"><%=activityService.getCommentCountByANum(activity.getaNum())%></font></h3>
                            </div>

                            <div class="panel-body">
                                <div class="" style="width:85%;margin-left:auto;margin-right: auto;">
                                    <div style="height:140px;">
                                        <form action="Comment" method="post">
                                            <input type="hidden" name="commentInfo" value="<%=client.getStuNum()%>&<%=activity.getaNum()%>">
                                            <textarea class="form-control" style="" name="comment" placeholder="write here..." rows="4" style=""></textarea>
                                            <button type="submit" id="send" style="margin-top:10px;float: right;" class="btn btn-success">发送</button>
                                        </form>
                                    </div>
                                    <div id="c1" class="">
                                        <!-- 一楼 -->
                                        <%
                                            for (int i=0;i<commentList.size();i++) {
                                                ActComment comment=commentList.get(i);
                                                User commentUser=userService.getUserByID(comment.getStuNum());
                                                List<ActReply> replyList=activityService.getReplyByACNum(comment.getAcNum());
                                        %>
                                        <div class="common-body">
                                            <hr class="hr_1">
                                            <a href=User?stuNum=<%=commentUser.getStuNum()%>><img src="<%=commentUser.getStuSrc()%>" class="user-icon"></a>
                                            <div class="name">
                                                <a href=User?stuNum=<%=commentUser.getStuNum()%>><%=commentUser.getuName()%></a>
                                            </div>
                                            <div class="text">
                                                <span class="cont"><%=comment.getContent()%></span>
                                            </div>
                                            <div class="cominfo" ><!---->
                                                <span><%=i+1%>楼&nbsp;</span>
                                                <time datetime><%=comment.getAcDate()%>&nbsp;</time>

                                                <span id="reply<%=i%>" class="trans" onmouseover="this.style.color='#3287B2';" onmouseout="this.style.color='';">回复</span>
                                                <span id="cancel<%=i%>" class="trans" onmouseover="this.style.color='#3287B2';" onmouseout="this.style.color='';" style="display:none;">取消</span>
                                            </div>
                                        </div>
                                        <!-- END 一楼 -->
                                        <%
                                            for (int j=0;j<replyList.size();j++) {
                                                ActReply reply=replyList.get(j);
                                                User replyUser=userService.getUserByID(reply.getFromID());

                                        %>
                                        <div class="replybody">
                                            <hr class="hr_2">
                                            <a href="User?stuNum=<%=replyUser.getStuNum()%>"><img src="<%=replyUser.getStuSrc()%>" class="replyicon"></a>
                                            <div class="replyname name">
                                                <a href="User?stuNum=<%=replyUser.getStuNum()%>"><%=replyUser.getuName()%></a>
                                            </div>
                                            <br>
                                            <div class="replytext">
                                                <%
                                                    if (reply.getToID().equals(commentUser.getStuNum())) {
                                                %>
                                                <span><%=reply.getContent()%></span>
                                                <%
                                                }else {
                                                %>
                                                <span>回复 <a href="User?stuNum=<%=reply.getToID()%>">@<%=userService.getUserByID(reply.getToID()).getuName()%></a> :<%=reply.getContent()%></span>
                                                <%
                                                    }
                                                %>
                                            </div>
                                            <div class="replyinfo" ><!---->
                                                <time datetime><%=reply.getAcrDate()%>&nbsp;</time>
                                                <span id="reply<%=i%><%=j%>" class="trans" onmouseover="this.style.color='#3287B2';" onmouseout="this.style.color='';">回复</span>
                                                <span id="cancel<%=i%><%=j%>" class="trans" onmouseover="this.style.color='#3287B2';" onmouseout="this.style.color='';" style="display:none;">取消</span>
                                            </div>
                                        </div>

                                        <%
                                                }
                                            }
                                        %>

                                        <!-- END 一条私信 -->
                                    </div>
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
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>
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

    <script>

        <%
        for (int i=0;i<commentList.size();i++) {
        %>
    $("#reply<%=i%>").click(function(){

    $("#reply<%=i%>").hide();
    $("#cancel<%=i%>").show();
    $("#cancel<%=i%>").after("<div id='add<%=i%>'style='height:140px;margin-top:15px;'><form action='Comment' method='post'>" +
    "<input type='hidden' name='replyInfo' value='<%=client.getStuNum()%>&<%=commentList.get(i).getStuNum()%>&<%=commentList.get(i).getAcNum()%>'>" +
    "<textarea class='form-control' name='reply' style='' placeholder='write here...' rows='4' style=''></textarea>" +
    "<button id='send' type='submit' style='float:right;margin-top:10px;' class='btn btn-success'>发送</button>" +
    "</form></div>");

    });

    $("#cancel<%=i%>").click(function(){

    $("#add<%=i%>").remove();
    $("#cancel<%=i%>").hide();
    $("#reply<%=i%>").show();
    });

        <%
        }
        for (int i=0;i<commentList.size();i++) {
            List<ActReply> actReplyList=activityService.getReplyByACNum(commentList.get(i).getAcNum());
            for (int j=0;j<actReplyList.size();j++) {
        %>
    $("#reply<%=i%><%=j%>").click(function(){

    $("#reply<%=i%><%=j%>").hide();
    $("#cancel<%=i%><%=j%>").show();
    $("#cancel<%=i%><%=j%>").after("<div id='add<%=i%><%=j%>'style='height:140px;margin-top:15px;'><form action='Comment' method='post'>" +
    "<input type='hidden' name='replyInfo' value='<%=client.getStuNum()%>&<%=actReplyList.get(j).getFromID()%>&<%=commentList.get(i).getAcNum()%>'>" +
    "<textarea class='form-control' name='reply' style='' placeholder='write here...' rows='4' style=''></textarea>" +
    "<button id='send' type='submit' style='float:right;margin-top:10px;' class='btn btn-success'>发送</button>" +
    "</form></div>");

    });

    $("#cancel<%=i%><%=j%>").click(function(){

    $("#add<%=i%><%=j%>").remove();
    $("#cancel<%=i%><%=j%>").hide();
    $("#reply<%=i%><%=j%>").show();
    });


        <%
            }
        }
        %>


    </script>
</body>

</html>

