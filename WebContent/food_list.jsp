<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>菜品列表</title>
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
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="x-nav">
      <!--<span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>-->
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
	        <form class="layui-form layui-col-md12 x-so">
	          <!-- <input class="layui-input" placeholder="开始日" name="start" id="start">
	          <input class="layui-input" placeholder="截止日" name="end" id="end"> -->
	          	<div class="layui-inline">
	          		<select name="foodcook_category" lay-verfiy="required" >
			          	<option value="">菜品种类</option>
			          	<option value="0">冷菜</option>
			          	<option value="1">炒菜</option>
			          	<option value="2">烧菜</option>
			          	<option value="3">蒸菜</option>
			          	<option value="4">汤羹</option>
			          	<option value="5">主食</option>
	          		</select> 
	          	</div>
	          	<div class="layui-inline">
	          		<select name="foodtype_category" lay-verfiy="required" >
			          	<option value="">荤/素</option>
			          	<option value="0">素菜</option>
			          	<option value="1">小荤</option>
			          	<option value="1">大荤</option>
	          		</select>
	          	</div>
	          	<div class="layui-inline">
	          		<input type="text" name="username"  placeholder="请输入菜名" autocomplete="off" class="layui-input">
	          	</div>
	          	<div class="layui-inline">
	          		<button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
	          	</div>
	        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加用户','./admin-add.html')"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：？条</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>菜名</th>
            <th>市场价</th>
            <th>店内价/优惠价</th>
            <th>图片</th>
            <th>加入时间</th>
            <th>是否热门</th>
            <th>描述</th>
            <th>类别</th>
            <th>是否在售</th>
            <th>操作</th>
        </thead>
        <tbody>
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>1</td>
            <td>凉拌黄瓜</td>
            <td>￥10</td>
            <td>￥8</td>
            <td>图片</td>
            <td>2017-01-01</td>
            <!--<td class="td-status">-->
              <!--<span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span></td>-->
            <td>热门</td>
            <td>这是凉拌黄瓜</td>
            <td>冷菜，素菜</td>
            <td>在售</td>
            <td class="td-manage">
              <!--<a onclick="member_stop(this,'10001')" href="javascript:;"  title="启用">
                <i class="layui-icon">&#xe601;</i>
              </a>-->
              <a title="编辑"  onclick="x_admin_show('编辑','admin-edit.html')" href="javascript:;">
                <i class="layui-icon">&#xe642;</i>
              </a>
              <a title="删除" onclick="member_del(this,'要删除的id')" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
        </tbody>
      </table>
      <!--<div class="page">
        <div>
          <a class="prev" href="">&lt;&lt;</a>
          <a class="num" href="">1</a>
          <span class="current">2</span>
          <a class="num" href="">3</a>
          <a class="num" href="">489</a>
          <a class="next" href="">&gt;&gt;</a>
        </div>
      </div>-->

    </div>
	<script>
		layui.use('laydate', function() {
			var laydate = layui.laydate;

			//执行一个laydate实例
			laydate.render({
				elem : '#start' //指定元素
			});

			//执行一个laydate实例
			laydate.render({
				elem : '#end' //指定元素
			});
		});

		/*用户-停用*/
		//    function member_stop(obj,id){
		//        layer.confirm('确认要停用吗？',function(index){
		//
		//            if($(obj).attr('title')=='启用'){
		//
		//              //发异步把用户状态进行更改
		//              $(obj).attr('title','停用')
		//              $(obj).find('i').html('&#xe62f;');
		//
		//              $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
		//              layer.msg('已停用!',{icon: 5,time:1000});
		//
		//            }else{
		//              $(obj).attr('title','启用')
		//              $(obj).find('i').html('&#xe601;');
		//
		//              $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
		//              layer.msg('已启用!',{icon: 5,time:1000});
		//            }
		//            
		//        });
		//    }
		/*用户-删除*/
		/* function member_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				$(obj).parents("tr").remove();
				layer.msg('已删除!', {
					icon : 1,
					time : 1000
				});
			});
		}

		function delAll(argument) {

			var data = tableCheck.getData();

			layer.confirm('确认要删除吗？' + data, function(index) {
				//捉到所有被选中的，发异步进行删除
				layer.msg('删除成功', {
					icon : 1
				});
				$(".layui-form-checked").not('.header').parents('tr').remove();
			});
		} */
		/* 删除单个admin */
		function deleteAdmin(aid){
			var isDel=confirm("确认要删除吗？");
			if(isDel){
				location.href="${pageContext.request.contextPath}/adminDelete?aid="+aid;
			}		
		}
		/* 删除选中的admin */
		function deleteAllAdmin(){
			var isDel=confirm("确认要删除所选项吗？");
			if(isDel){
				var aids=$("div.layui-form-checked + i.mi").text();
				/* alert(aids); */
				
				location.href="${pageContext.request.contextPath}/adminMultiDelete?aids="+aids;
			}
			
			
		}
	</script>
	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
</body>
</html>