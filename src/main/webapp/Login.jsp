<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/4
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!doctype html>
<html lang="en" class="fullscreen-bg">

<head>
    <title>社团部落-登录</title>
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
                        <form class="form-auth-small" action="/login.do" method="post">
                            <div class="form-group">
                                <label for="username" class="control-label sr-only">username</label>
                                <input type="username" class="form-control" id="username" value="" name="username" placeholder="用户名">
                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label sr-only">Password</label>
                                <input type="password" class="form-control" id="password" name="password"value="" placeholder="密码">
                            </div>
                            <div class="form-group clearfix">
                                <label class="fancy-checkbox element-left">
                                </label>
                            </div>
                            <button type="submit" class="btn btn-primary btn-lg btn-block" name="action" value="登录">
                                <font color="white" face="verdana,arial,sans-serif">登录</font>
                            </button>
                            <div class="bottom">
									<span class="helper-text"> <button type="submit" name="action" value="点击注册" class="btn btn-primary btn-lg btn-block">
									<font color="white" face="verdana,arial,sans-serif">点击注册</font>
								</button></span>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="right">
                    <div class="overlay"></div>
                    <div class="content text">
                        <h1 class="heading"><font color="#EDEDED" face="verdana,arial,sans-serif">欢迎,</font></h1>
                        <p><font #EDEDED="whitesmoke" face="verdana,arial,sans-serif">来到社团部落!</font></p>
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
        alert("用户名或密码不正确!");
    }
</script>
</html>
