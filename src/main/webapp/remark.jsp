<%@ page import="java.util.List" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="service.*" %>
<%@ page import="model.*" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/5
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    List<Activity> activities=activityService.getAcByComm(cNum);

    MessageService messageService=new MessageService();
    String stuNum = client.getStuNum();
    List<Message> messages = messageService.getMessagesNotRead(stuNum);

%>
<!doctype html>
<html lang="en">

<head>
    <title>社团部落-社团详情</title>
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
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="assets/css/demo.css">
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
            <a href="homepage.jsp"><img src="assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
        </div>
        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
            </div>

            <form class="navbar-form navbar-left" action="Search" method="get">
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
                    	作者：872775803@qq.com
                    	时间：2018-07-05
                    	描述：活动列表
                   -->
                   <div class="panel">
                   	<div class="panel-heading">
									<h2 class="panel-title">活动</h2>
                    </div>
                       <form  method="post" action="Activity">
                           <div class="panel-body">
                               <table class="table">
                                   <thead>
                                   <tr>
                                       <th>活动名</th>
                                       <th>日期</th>
                                   </tr>
                                   </thead>
                                   <tbody>
                                   <%
                                       for (int i=0;i<activities.size();i++) {
                                           Activity activity= activities.get(i);
                                   %>
                                   <tr>
                                       <td><a href="Activity?aNum=<%=activity.getaNum()%>"><%=activity.getaTitle()%></a></td>
                                       <td><%=activity.getaDate()%></td>
                                   </tr>
                                   <%
                                       }
                                   %>
                                   </tbody>
                               </table>
                           </div>
                       </form>

                   </div>




                    <div class="row">
                    	<!--
                    	作者：872775803@qq.com
                    	时间：2018-07-05
                    	描述：成员列表
                    -->
					<div class="col-md-6">
						<div class="panel">
								<div class="panel-heading">
									<h2 class="panel-title">成员列表</h2>
								</div>
                            <form method="post" action="User">
                                <div class="panel-body">

                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th style="width:60px;">用户</th>
                                            <th style="width:180px;"></th>
                                            <th>姓名</th>
                                            <th>社团身份</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            for (int i=0;i<users.size();i++) {
                                                User user=users.get(i);
                                                int state=communityService.getUStateByNum(user.getStuNum(),cNum);
                                                if (state==1) {
                                        %>
                                        <tr>
                                            <td><img src="<%=user.getStuSrc()%>" class="img-circle" alt="Avatar" style="width: 35px;height:35px"></td>
                                            <td><a href=User?stuNum=<%=user.getStuNum()%>><%=user.getuName()%></a></td>
                                            <td><%=user.getStuName()%></td>
                                            <%
                                                String iden=communityService.getIdenByNum(user.getStuNum(),cNum);
                                                if (iden.equals("1")){
                                                    iden="社长";
                                                }else if (iden.equals("2")) iden="管理员";
                                                else if (iden.equals("3")) iden="成员";
                                            %>
                                            <td><%=iden%></td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                        </tbody>
                                    </table>

                                </div>
                            </form>

                        </div>
					</div>
					<!--
                    	作者：872775803@qq.com
                    	时间：2018-07-05
                    	描述：留言列表
                    -->
					<div class="col-md-6">
						<div class="panel">
								<div class="panel-heading">
									<h1 class="panel-title">留言</h1>
								</div>
                            <form method="post" action="Remark">
                                <input type="hidden" name="type" value="normal">
                                <div class="panel-body">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th style="width:75px;">用户</th>
                                            <th style="width:130px;"></th>
                                            <th>留言</th>
                                            <th>时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            for (int i=0;i<remarks.size();i++){
                                                Remark remark = remarks.get(i);
                                        %>
                                        <tr>
                                            <%
                                                User remarker=userService.getUserByID(remark.getStuNum());
                                            %>
                                            <td><img src="<%=remarker.getStuSrc()%>" class="img-circle" alt="Avatar" style="width: 35px;height:35px"></td>
                                            <td><a href="Remark?rNum=<%=remark.getrNum()%>"><%=remarker.getuName()%></a></td>
                                            <td><%=remark.getrContent()%></td>
                                            <td><%=remark.getrDate()%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                    <!--
                                        作者：872775803@qq.com
                                        时间：2018-07-05
                                        描述：写留言
                                    -->
                                    <br>

                                    <textarea class="form-control" placeholder="请输入留言" rows="4" name="rContent"></textarea>
                                    <br>
                                    <input type="hidden" name="cNum" value="<%=cNum%>">
                                    <input type="submit" name="submit" class="btn btn-default" value="提交留言">
                                </div>
                            </form>

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

