<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ChatNews" %>
<%@ page import="service.UserService" %>
<%@ page import="service.ChatNewsService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>
	<title>Private Message</title>
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

	<link href= "message.css" type="text/css" rel="stylesheet" />
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
</head>

<body>
<%
    User user = (User)session.getAttribute("curUser");
    UserService userService = new UserService();
    ChatNewsService chatNewsService = new ChatNewsService();
    List<ChatNews> newsList=(List<ChatNews>)session.getAttribute("newsList");
    List<String> chatList = (List<String>)session.getAttribute("chatList");
    String clickUserNum = (String)session.getAttribute("clickUser");
    String clickUserName = userService.getStuName(clickUserNum);
    List<ChatNews> curNewsList = (List<ChatNews>)session.getAttribute("curNewsList");
%>
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
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-primary">Go</button></span>
                    </div>
                </form>

				<div class="navbar-btn navbar-btn-right">
					<a class="btn btn-success update-pro" href="login.html" title="" target=""><i class="fa fa-rocket"></i> <span>退出登录</span></a>
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
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="assets/img/user.png" class="img-circle" alt="Avatar"> <span>Samuel</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
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
                                <a href="homepage.html" class="">
                                    <i class="lnr lnr-home"></i>
                                    <span>主页</span></a>
                            </li>

                            <li>
                                <a href="community.html" class="">
                                    <i class="lnr lnr-location"></i>
                                    <span>我的社团</span></a>
                            </li>

                            <li>
                                <a href="userInfo.html" class="">
                                    <i class="lnr lnr-cog"></i>
                                    <span>个人信息</span></a>
                            </li>

                            <li>
                                <a href="communityList.html" class="">
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
		      <div class="panel panel-headline">
                <div class="panel-heading">
                    <h3 class="panel-title">私信</h3>
                    <p class="panel-subtitle">Private Message</p>
                </div>

                <div class="panel-body">
			<div class="chatbox" style="text-align: center;margin-left:auto;margin-right:auto;">
        <!-- 列表 -->
        <div class="col-md-1"></div>
        <div id="friends" class="">
                <form action="/chatnews.do" method="post">
                <%
                    for(String tempUser:chatList)
                    {
                        int unReadCount=chatNewsService.getUnreadCount(user.getStuNum(),userService.getstuNum(tempUser));
                        //System.out.println("======"+user.getStuNum()+"=========="+userService.getstuNum(tempUser));
                        if(tempUser.equals(clickUserName)){
                %>
                <button name="action" value="更改私信用户,<%=userService.getstuNum(tempUser)%>" type="submit" style="width:240px;background: #e3e9ed;border: 1px solid #e7ebee;">
				<div class="friend" >

					<img src="<%=userService.getStuSrc(tempUser)%>">
					<strong><%=tempUser%></strong>
                    <%
                        if(unReadCount!=0)
                        {
                    %>
					<span class="badge bg-danger dot" id="NoticeCount" ><%=unReadCount%></span>
                    <%
                        }
                    %>
				</div>
				</button>
                    <%
                        }else {
                    %>
                    <button name="action" value="更改私信用户,<%=userService.getstuNum(tempUser)%>" type="submit"  style="width:240px;background: transparent;border: 1px solid #e7ebee;" onmouseover="this.style.background='rgba(227,233,237,0.5)';" onmouseout="this.style.background='white';"onclick="$("#username").val($("#friend1 strong").text());">
                    <div class="friend" >

                        <img src="<%=userService.getStuSrc(tempUser)%>">
                        <strong><%=tempUser%></strong>
                        <%
                            if(unReadCount!=0){
                        %>
                        <span class="badge bg-danger dot" id="NoticeCount" ><%=unReadCount%></span>
                        <%
                            }
                        %>
                    </div>
                    </button>
                    <%
                        }
                        }
                    %>
			</form>								
		</div>
		<!-- END 列表 -->
        
        <!-- 对话窗口 -->
        <div class="chatview col-md-7">
        
             <!-- 发送消息 -->
			<div id="sendmessage" class="">
			    <hr>
			    <form action="/chatnews.do" method="post">
				  <textarea class="form-control" placeholder="write here..." rows="5" style="" name="content"></textarea>
				  <input type="hidden" id="username" name="usernum" value="<%=clickUserNum%>" />
				  <button id="send" class="btn btn-success" name="action" value="sendMessage">发送</button>
				</form>
			</div>
			<!-- END 发送消息 -->

			<!-- 对话 -->



			<div id="chat-messages" class="">
              <!-- 某人的私信 p1-->
                <form action="/chatnews.do" method="post">
              <div id="<%=clickUserName%>" class="">
                <!-- 一条私信 -->
                  <%
                      for(ChatNews tempNews : curNewsList)
                      {
                  %>
				<div class="message">
				  <hr>
				  <img src="<%=userService.getStuSrc(userService.getStuName(tempNews.getStartNum()))%>">
				  <div class="desc" >
				    <span><%=tempNews.getNews()%></span>
				  </div> 
				  <div class="" style="margin-top:-15px;margin-left:65px;font-size:14px;"><!----> 
				    <time  datetime="Sun Jul 08 2018 21:39:49 GMT+0800 (中国标准时间)" title="2018-07-08T21:39:49+08:00"><%=tempNews.getNewTime()%></time>
					  <%
						  if(user.getStuNum().equals(tempNews.getStartNum())){
					  %>
				     <button type="submit" id="reply3" class="trans" onmouseover="this.style.color='#3287B2';" onmouseout="this.style.color='';" name="action" value="delete,<%=tempNews.getnNum()%>">删除
				     </button>
					  <%
						  }
					  %>
				  </div>    
			    </div>
                  <%
                      }
                  %>
			    <!-- END 一条私信 -->
			</div>
                </form>

			<!-- END 某人的私信 p1-->	



			</div>
            <!-- END 对话 -->

         

        </div>
        <!-- END 对话窗口 -->
        <div class="col-md-1"></div>
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
