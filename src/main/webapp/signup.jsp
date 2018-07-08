<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!doctype html>
<html lang="en" class="fullscreen-bg">

<head>
	<title>社团部落-注册</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
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
	<script type="text/javascript">
            function check(){
                var nameValue=window.document.getElementById("username").value;
                var passwordValue=window.document.getElementById("password").value;
                var password2Value=window.document.getElementById("password2").value;
                var sex1Value=window.document.getElementById("sex1").value;
                var sex2Value=window.document.getElementById("sex2").value;
                var snameValue=window.document.getElementById("name").value;
                var schoolValue=window.document.getElementById("school").value;
                var birthValue=window.document.getElementById("meeting").value;
                if (nameValue == "") // 或者是!nameValue
                {
                    window.alert("用户名不能为空!");
                    return false;
                }
                if (passwordValue == "")
                {
                    window.alert("密码不能为空!");
                    return false;
                }
                if (password2Value == "")
                {
                    window.alert("确认密码不能为空!");
                    return false;
                }
                if (passwordValue!=password2Value )
                {
                    window.alert("两次密码不相同!");
                    return false;
                }
                if (snameValue == "")
                {
                    window.alert("姓名不能为空!");
                    return false;
                }
                if (schoolValue == "")
                {
                    window.alert("学校不能为空!");
                    return false;
                }
                if (sex1Value == ""&&sex2Value=="")
                {
                    window.alert("性别不能为空!");
                    return false;
                }
                if (birthValue == "")
                {
                    window.alert("生日不能为空!");
                    return false;
                }

                return true;
            }
		</script>
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center"><img src="assets/img/logo-dark.png" alt="Klorofil Logo"></div>
								<p class="lead"></p>
							</div>
							<form class="form-auth-small" method="post" action="/login.do"onsubmit="return check()">
								<div class="form-group">
									<label class="control-label sr-only">username</label>
									<input type="username" class="form-control"  id="username" name="username" value="" placeholder="用户名">
								</div>
								<div class="form-group">
									<label class="control-label sr-only">Password</label>
									<input type="password" class="form-control"  name="password" id="password" value="" placeholder="密码">
								</div>
								<div class="form-group">
									<label class="control-label sr-only">Confirm</label>
									<input type="password" class="form-control" id="password2" value="" placeholder="再次输入">
								</div>
								<div class="form-group">
									<label class="control-label sr-only">name</label>
									<input type="username" class="form-control"  id="name" value="" name="stuname" placeholder="姓名">
								</div>
								<div class="form-group">
									<label class="control-label sr-only">school</label>
									<input type="username" class="form-control" id="school" value="" name="school" placeholder="学校">
								</div>
								<div class="form-group">
									<!--<label class="fancy-radio">
										<input name="gender" value="male" type="radio">
										<span><i></i>男</span>
									</label>
									<label class="fancy-radio">
										<input name="gender" value="female" type="radio">
										<span><i></i>女</span>
									</label>-->
									<label><font color="#737373" face="verdana,arial,sans-serif">性别:</font></label>
									<label><font color="#737373" face="verdana,arial,sans-serif">男</font></label>
									<input type="radio" checked="checked" name="sex" id="sex1" value="男" />
									<label for="sex2"><font color="#737373" face="verdana,arial,sans-serif">女</font></label>
									<input type="radio" name="sex" id="sex2" value="女"/>
									<label>   </label>
									<label for="meeting"><font color="#737373" face="verdana,arial,sans-serif">生日:</font></label>
									<input id="meeting" type="date" value="" name="birthday"/>
								</div>
								<div class="form-group clearfix">
									<label class="fancy-checkbox element-left">		
									</label>
								</div>
								<div class="bottom">
									<span class="helper-text"> <button type="submit" name="action" value="注册" class="btn btn-primary btn-lg btn-block">
									<font color="white" face="verdana,arial,sans-serif">注册</font>
								</button></span>
								</div>
								
							</form>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 class="heading"><font color="whitesmoke" face="verdana,arial,sans-serif">欢迎,</font></h1>
							<p><font color="whitesmoke" face="verdana,arial,sans-serif">来到社团部落!</font></p>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
</body>
<script>

//取出传回来的参数error并与yes比较
var errori ='<%=request.getParameter("error")%>';
if(errori=='yes'){
alert("用户名已存在!");
}
</script>

</html>
