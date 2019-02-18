<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
	<meta charset="UTF-8">
	<title>后台系统管理</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./pages/css/font.css">
	<link rel="stylesheet" href="./pages/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="./pages/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./pages/js/xadmin.js"></script>

</head>
<body class="login-bg">
    
    <div class="login">
        <div class="message">管理员登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" action="${pageContext.request.contextPath }/admin?method=login" >
            <input name="admin_name" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" 
            	onfocus="hideError()">
            <hr class="hr15">
            <input name="admin_password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input type="checkbox" class="layui-input" name="checkbox_autoLogin" value="auto">自动登录
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit" class="layui-input">
            <hr class="hr20" >
        </form>
        <div id="error-div">
        	<span style="color:red;">
        		<%=request.getAttribute("loginError")==null?"":request.getAttribute("loginError") %>
        	</span>
        </div>
    </div>

    <script>
        function hideError(){
        	$("error-div").css("display","none");
        }
    </script>

    
    <!-- 底部结束 -->
    
</body>
</html>