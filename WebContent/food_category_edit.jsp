<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>菜品分类添加</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
		content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="./css/font.css">
	<link rel="stylesheet" href="./css/xadmin.css">
	<script type="text/javascript"
		src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="./lib/layui/layui.js"
		charset="utf-8"></script>
	<script type="text/javascript" src="./js/xadmin.js"></script>

</head>

<body>
	<div class="x-body">
		<form class="layui-form" action="${pageContext.request.contextPath}/foodCategoryUpdate" method="post">
			<input type="hidden" name="fcid" value="${fcid }">
			<div class="layui-form-item">
				<label for="category_name" class="layui-form-label"> 
					<span class="x-red">*</span>类名
				</label>
				<div class="layui-input-inline">
					<input type="text" id="category_name" name="category_name" required=""
						lay-verify="required" autocomplete="off" value="${fc_name }"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					<span class="x-red">*</span>
				</div>
			</div>
			
			<div class="layui-form-item" >
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn" type="submit" lay-filter="add" id="btn_submit"
					lay-submit="" >修改</button>
			</div>
		</form>
	</div>
	
	<script>
		layui.use([ 'form', 'layer' ], function() {
			$ = layui.jquery;
			var form = layui.form, layer = layui.layer;

			//自定义验证规则
			form.verify({
				nikename : function(value) {
					if (value.length < 2) {
						return '昵称至少得2个字符啊';
					}
				}
			});

			$("#btn_submit").click(function(){
				// 获得frame索引
				var index = parent.layer.getFrameIndex(window.name);
				//关闭当前frame
				parent.layer.close(index);
			});
		});
	</script>

</body>
</html>