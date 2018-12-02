<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>编辑菜品</title>
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

<script>
	$(function(){
		$("option.mfc[value='${food.food.fcwc_id}']").attr("selected",true);
		$("option.mft[value='${food.food.ftc_id}']").attr("selected",true);
		$(":radio[value='${food.food.fis_onsale}']").attr("checked",true);
	});
</script>
</head>

<body>
	<div class="x-body">
		<form class="layui-form" action="${pageContext.request.contextPath }/foodEdit" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pk_fid" value="${food.food.pk_fid }">
			<!-- 填写菜名 -->
			<div class="layui-form-item">
				<label for="food_name" class="layui-form-label">菜名
				</label>
				<div class="layui-input-inline">
					<input type="text" id="food_name" name="fname" required=""
						lay-verify="required" autocomplete="off" value="${food.food.fname }"
						class="layui-input" autofocus="autofocus">
				</div>
				<div class="layui-form-mid layui-word-aux">
					<span class="x-red">*</span>
				</div>
			</div>
			<!-- 填写市场价/原价 -->
			<div class="layui-input-inline">
				<div class="layui-form-item">
					<label for="market_price" class="layui-form-label">市场价/原价
					</label>
					<div class="layui-input-inline">
						<input type="text" id="market_price" name="fmarket_price" required=""
							lay-verify="required" autocomplete="off" value="${food.food.fmarket_price }"
							class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>
					</div>
				</div>
			</div>
			<!-- 店内价/优惠价/现价 -->
			<div class="layui-input-inline">
				<div class="layui-form-item">
					<label for="shop_price" class="layui-form-label">优惠价/现价
					</label>
					<div class="layui-input-inline">
						<input type="text" id="shop_price" name="fshop_price" required=""
							lay-verify="required" autocomplete="off" value="${food.food.fshop_price }"
							class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<span class="x-red">*</span>
					</div>
				</div>
			</div>
			<!-- 图片 -->
			<div class="layui-form-item">
				<label for="food_image" class="layui-form-label">图片
				</label>
				<div class="layui-input-inline">
					<input type="file" name="add_pic" lay-verify="required" 
						style="padding-top: 10px"/>
				</div>
				<div class="layui-form-mid layui-word-aux">
					<span class="x-red">*</span>
				</div>
			</div>
			<!-- 描述 -->
			<div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">菜品描述</label>
			    <div class="layui-input-block">
			      <textarea name="fdesc" placeholder="请输入内容" class="layui-textarea">${food.food.fdesc }</textarea>
			    </div>
			</div>	
			<!-- 菜品类别 --><!-- 类别回显使用函数 -->
			<div class="layui-form-item">
			    <label class="layui-form-label">选择菜品类别</label>
			    <div class="layui-input-inline">
			      <select name="fcwc_id" lay-verify="required" >
			        <option value="">菜品种类</option>
			        <c:forEach items="${foodCategories }" var="fc">
		         		<option class="mfc" value="${fc.pk_fcwc_id }">${fc.fcwc_name }</option>
		         	</c:forEach>
			      </select>
			    </div>
			    <div class="layui-form-mid layui-word-aux">
					<span class="x-red">*</span>
				</div>
			    <div class="layui-input-inline">
			      <select name="ftc_id" lay-verfiy="required">
	         		<option value="">荤/素</option>
		         	<c:forEach items="${foodTypes }" var="ft">
			         	<option class="mft" value="${ft.pk_ftc_id }">${ft.ftc_name }</option>
		         	</c:forEach>
	       		  </select>
			    </div>
			    <div class="layui-form-mid layui-word-aux">
					<span class="x-red">*</span>
				</div>
			</div>
			<!-- 是否在售 --><!-- 回显使用函数 -->
			<div class="layui-form-item">
			    <label class="layui-form-label">是否在售</label>
			    <div class="layui-input-inline">
			      <input type="radio" name="fis_onsale" value="1" title="在售" >
			      <input type="radio" name="fis_onsale" value="0" title="下架" >
			    </div>
			    <div class="layui-form-mid layui-word-aux">
					<span class="x-red">*</span>
				</div>
			</div>
			<!-- 按钮 -->
			<div class="layui-form-item" >
				<div class="layui-input-block">
					<button class="layui-btn" type="submit" lay-filter="add" id="btn_submit"
						lay-submit="" >修改</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
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
						return '菜名至少得2个字符啊';
					}
				},
			});

			
			$("#btn_submit").click(function(){
				// 获得frame索引
				var index = parent.layer.getFrameIndex(window.name);
				// 父页面刷新事件
				parent.$("#irefresh").click();
				//关闭当前frame
				parent.layer.close(index);
				
			});
		});
	</script>
	
</body>
</html>