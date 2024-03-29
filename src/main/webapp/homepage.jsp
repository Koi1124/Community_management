<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="model.Activity" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="service.ActivityService" %>
<%@ page import="service.MessageService" %>
<%@ page import="model.Message" %>
<%@ page import="service.CommunityService" %>
<!doctype html>
<html lang="en">
    
    <head>
        <title>社团部落</title>
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
        <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png"></head>
    
    <body>
	<%
        User user=(User)session.getAttribute("curUser");
        List<Activity> alist = new ArrayList<>();
        ActivityService acs = new ActivityService();
        acs.initActivityList(alist);
        CommunityService communityService=new CommunityService();
        MessageService messageService = new MessageService();
        String stuNum = user.getStuNum();
        List<Message> messages = messageService.getMessagesNotRead(stuNum);

    %>
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
                                    <img src="<%=user.getStuSrc()%>" class="img-circle" alt="Avatar">
                                    <span><%=user.getuName()%></span>
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
                                <a href="homepage.jsp" class="active">
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
                        <!-- OVERVIEW -->
                        <div class="panel panel-headline">
                            <div class="panel-heading">
                                <h3 class="panel-title"><a></a>最新活动</h3>
                                <p class="panel-subtitle">New Activity</p></div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-9">
                                        <div class="rolling">
                                            <div class="roll-title roll-title clearfix">
                                                <a class="next">
                                                    <img src="assets/img/arrow-fr.png" alt=""></a>
                                                <a class="prev">
                                                    <img src="assets/img/arrow-fl.png" alt=""></a>
                                                
                                            </div>
                                            <div class="content">
                                                <ul class="roll-list">
                                                    <%
                                                         for (Activity temp :alist)
                                                         {
                                                    %>
                                                    <li>
                                                        <a href="Activity?aNum=<%=temp.getaNum()%>" target="_blank"><%=temp.getaTitle()%></a> &emsp; &emsp;&emsp;——
                                                        <a href="Community?getCNum=<%=temp.getcNum()%>&type=normal"><%=communityService.getCNameByCommID(temp.getcNum())%></a>
                                                        <span><%=temp.getaDate()%></span>
                                                    </li>
                                                    <%
                                                        }
                                                    %>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END OVERVIEW --></div>
                </div>
                <!-- END MAIN CONTENT --></div>
            <!-- END MAIN -->
           
        <!-- END WRAPPER -->
        <!-- Javascript -->
        <script src="assets/vendor/jquery/jquery.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/scripts/klorofil-common.js"></script>
        <script type="text/javascript" src="assets/scripts/jquery.SuperSlide.2.1.1.js"></script>
        <script type="text/javascript">jQuery(".rolling").slide({
                titCell: ".roll-title ul",
                mainCell: ".content ul",
                autoPage: true,
                effect: "topLoop",
                autoPlay: true,
                scroll: 2,
                vis: 5,
                delayTime: 1000,
                trigger: "click"
            });</script>
        <script>$(function() {
                var data, options;

                // headline charts
                data = {
                    labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                    series: [[23, 29, 24, 40, 25, 24, 35], [14, 25, 18, 34, 29, 38, 44], ]
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
                    },
                    {
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
                    series: [[6384, 6342, 5437, 2764, 3958, 5068, 7654]]
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
                },
                updateInterval);

                function getRandomInt(min, max) {
                    return Math.floor(Math.random() * (max - min + 1)) + min;
                }

            });</script>
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