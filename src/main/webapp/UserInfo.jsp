<%@ page import="model.User" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="dao.UserDAOImp" %>
<%@ page import="service.UserService" %>
<%@ page import="service.CommunityService" %>
<%@ page import="service.MessageService" %>
<%@ page import="model.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ThinkPad
  Date: 2018/7/8
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User client=(User)session.getAttribute("curUser");
    UserService userService=new UserService();
    CommunityService communityService=new CommunityService();
    String stuNum=client.getStuNum();
    User user=userService.getUserByID(stuNum);
    int commCount=communityService.getCountByUser(client);

    MessageService messageService=new MessageService();
    List<Message> messages = messageService.getMessagesNotRead(stuNum);
%>
<!doctype html>
<html lang="en">

<head>
    <title>社团部落-个人信息</title>
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

    <link href= "assets/css/userInfo.css" type="text/css" rel="stylesheet" />
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
                        <a href="UserInfo.jsp" class="active">
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

    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <div class="panel panel-profile">
                    <div class="clearfix">
                        <!-- PROFILE HEADER -->
                        <div class="profile-header">
                            <div class="overlay"></div>
                            <div class="profile-main">
                                <!--头像-->
                                <button id="replaceImg" for="changeImg" class="repalce">
								<img src=<%=user.getStuSrc()%> class="img-circle" alt="你的头像" id="YorImg" width="100px" height="100px">
								</button>
                                <!--用户名-->
                                <h3 class="name"><%=user.getuName()%></h3>
                            </div>

                            <div class="profile-stat">
                                <div class="row">
                                    <div class="col-md-4 stat-item">	</div>
                                    <div class="col-md-4 stat-item">
                                        <%=commCount%> <span>参加的社团</span>
                                    </div>
                                    <div class="col-md-4 stat-item"></div>
                                </div>
                            </div>
                        </div>
                        <!-- END PROFILE HEADER -->

                        <!-- 个人详细信息 -->
                        <div class="profile-detail">
                            <div class="profile-info">
                                <form action="Revise" method="post">
                                    <div class="row">
                                        <input type="hidden" name="type" value="infoRevise">
                                        <ul class="list-unstyled list-justify">
                                            <li>昵称 <input class="myinput" type="text" value=<%=user.getuName()%> name="uName" /></li>
                                            <li>姓名 <input class="myinput" type="text" value=<%=user.getStuName()%> name="stuName" readonly="readonly" /></li>
                                            <li>性别 <input class="myinput" type="text" value=<%=user.getStuSex()%> name="stuSex"  readonly="readonly" /></li>
                                            <li>学校 <input class="myinput" type="text" value=<%=user.getStuSchool()%> name="stuSchool" readonly="readonly" /></li>
                                            <li>专业 <input class="myinput" type="text" value=<%=user.getStuProfess()%> name="stuProfess" /></li>
                                            
                                            <li>电话 <input class="myinput" type="text" value=<%=user.getStuNumber()%> name="stuNumber" /></li>
                                            <li>生日 <input class="myinput" type="text" value=<%=user.getStuBirth()%> name="stuBirth" readonly="readonly"/></li>
                                        </ul>
                                    </div>
                                    <input type="submit" value="提交修改" class="btn btn-primary sub">




								<!--<button id="replaceImg" class="btn btn-primary btn-lg">更换头像</button>-->
                                        <!--图片裁剪框 start-->
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
                                </form>

                                <a href="password.jsp" ><button class="btn btn-primary alter">修改密码</button></a>
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

<!-- END WRAPPER -->
<!-- Javascript -->
<script src="assets/vendor/jquery/jquery.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/scripts/klorofil-common.js"></script>
<script src="assets/vendor/jquery/jquery.min.js"></script>
		
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
					//alert("======"+base64url);
                    $("#fileUrl").val(base64url);
 //                  

                    $("#YorImg").prop("src", base64url); //显示为图片的形式

                    //关闭裁剪框
                    closeTailor();
                }
            });

        //关闭裁剪框
        function closeTailor() {
            $(".tailoring-container").toggle();
        }



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

