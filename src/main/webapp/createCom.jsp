<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>
	<title>createCom</title>
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
					<a class="btn btn-success update-pro" href="" title="" target=""><i class="fa fa-rocket"></i> <span>退出登录</span></a>
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
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="col-lg-8">
					<div class="panel panel-headline">
									
						<div class="panel-body">
								
							<h3>创建社团</h3>
							<hr>
							
							<!--
                            	描述：社团创建表单
                            -->
                            <form action="login.do" method="post">
                            	<p>社团名称</p>
                            	
							<input class="form-control" placeholder="请勿超过15个字" type="text" id="cName" name="cname">	
							                           	
							</br>
							</br>
							<p>社团类型</p>
							<select class="form-control" id="cType" name="ctype">
										<option value="学习">学习</option>
										<option value="艺术">艺术</option>
										<option value="体育">体育</option>
										<option value="公益">公益</option>
										<option value="休闲娱乐">休闲娱乐</option>
										<option value="咨询沟通">咨询沟通</option>
										<option value="其他">其他</option>
							</select>
						    </br>
						    </br>
						    <p>社团简介</p>
						    
						    <textarea class="form-control" placeholder="" rows="5" name="syn" style="overflow:hidden;resize:none;"></textarea>
						    <br>
						    <hr>
						    <!--
                            	描述：社团封面暂时不完成功能
                            -->
							<a href="#" style="color:#00AAFF;" onmouseover="this.style.color='#3287B2';" onmouseout="this.style.color='#00AAFF';">上传封面</a>
							<hr>
							<div style="float: right;">
							<button type="submit" class="btn btn-primary" name="action"value="createComm" >提交</button>
							</div>
							
							</form>
						
						</div>
						</div>	
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
		<script>

            //取出传回来的参数error并与yes比较
            var errori ='<%=request.getParameter("error")%>';
            if(errori=='yes'){
                alert("社团名不可用!");
            }
		</script>
</body>

</html>
