<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	User client=(User) session.getAttribute("curUser");
%>
<!doctype html>
<html lang="en">

<head>
	<title>社团部落-创建社团</title>
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
	<link rel="stylesheet" href="assets/css/cropper.min.css">
	<link rel="stylesheet" href="assets/css/ImgCropping.css">

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

							 <br>

                            <button id="replaceImg" for="changeImg" class="repalce">
                            	<a href="#">上传封面</a>
                            </button>

								<!--图片裁剪框 start-->
								<div style="display: none" class="tailoring-container">
									<div class="black-cloth" onClick="closeTailor(this)"></div>
									<div class="tailoring-content">
										<div class="tailoring-content-one">
											<div style="float: left;">
												<label title="上传图片" for="chooseImg" class="btn btn-primary btn-sm">
													<input type="file" accept="image/jpg,image/jpeg,image/png" name="file" id="chooseImg" class="hidden" onChange="selectImg(this)">选择图片</label></div>
                                            <input type="hidden" id="fileUrl" name="fileUrl" value="" />
                                            <div class="close-tailoring" onclick="closeTailor(this)">×</div></div>
										<div class="tailoring-content-two">
											<div class="tailoring-box-parcel">
												<img id="tailoringImg"></div>
											<div class="preview-box-parcel">
												<p>图片预览：</p>
												<div class="square previewImg"></div>
												<div class="circular previewImg"></div>
											</div>
										</div>
										<div class="tailoring-content-three">
											<div style="float:right">

												<span class="btn btn-primary btn-sm" id="sureCut">确定</span></div>
										</div>
									</div>
									<!--图片裁剪框 end--></div>

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
						    
						
							<hr>
							<div style="float: right;">
							<button type="submit" class="btn btn-primary" name="action" value="createComm" >提交</button>
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

		<script src="assets/vendor/jquery/jquery.min.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/scripts/cropper.min.js"></script>
		<script type="text/javascript">//弹出框水平垂直居中
        (window.onresize = function() {
            var win_height = $(window).height();
            var win_width = $(window).width();
            if (win_width <= 768) {
                $(".tailoring-content").css({
                    "top": (win_height - $(".tailoring-content").outerHeight()) / 3,
                    "left": 0
                });
            } else {
                $(".tailoring-content").css({
                    "top": (win_height - $(".tailoring-content").outerHeight()-50),
                    "left": (win_width - $(".tailoring-content").outerWidth()) / 2
                });
            }
        })();

        //弹出图片裁剪框
        $("#replaceImg").on("click",
            function() {
                $(".tailoring-container").toggle();
            });
        //图像上传
        function selectImg(file) {
            if (!file.files || !file.files[0]) {
                return;
            }
            var reader = new FileReader();
            reader.onload = function(evt) {
                var replaceSrc = evt.target.result;
                //更换cropper的图片
                $('#tailoringImg').cropper('replace', replaceSrc, false); //默认false，适应高度，不失真
            }
            reader.readAsDataURL(file.files[0]);
        }
        //cropper图片裁剪
        $('#tailoringImg').cropper({
            aspectRatio: 1 / 1,
            minCropBoxWidth: 100,
            minCropBoxHeight: 100,

            preview: '.previewImg',
            //预览视图
            guides: false,
            //裁剪框的虚线(九宫格)
            autoCropArea: 0.5,
            //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
            movable: false,
            //是否允许移动图片
            dragCrop: true,
            //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
            movable: true,
            //是否允许移动剪裁框
            resizable: false,
            //是否允许改变裁剪框的大小
            zoomable: true,
            //是否允许缩放图片大小
            mouseWheelZoom: true,
            //是否允许通过鼠标滚轮来缩放图片
            touchDragZoom: true,
            //是否允许通过触摸移动来缩放图片
            rotatable: true,
            //是否允许旋转图片
            crop: function(e) {
                // 输出结果数据裁剪图像。
            }
        });


        //裁剪后的处理
        $("#sureCut").on("click",
            function() {
                if ($("#tailoringImg").attr("src") == null) {
                    return false;
                } else {
                    var cas = $('#tailoringImg').cropper('getCroppedCanvas'); //获取被裁剪后的canvas
                    var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
                    $("#fileUrl").val(base64url);
 //                   $("#YorImg").prop("src", base64url); //替换原有图片

                    var newImg = new Image;//缓冲图片
                    newImg.src=cas.toDataURL("image/png");
//
                    var img = $('#imgId').attr("src");
                    //var alink = document.createElement("a");
                    //alink.href = newImg.src;
                    //alert("=============="+alink.href);
//                  alink.download = "testImg.jpg";
//                  alink.click();


                    //关闭裁剪框
                    closeTailor();
                }
            });

        //关闭裁剪框
        function closeTailor() {
            $(".tailoring-container").toggle();
        }



		</script>
</body>

</html>
