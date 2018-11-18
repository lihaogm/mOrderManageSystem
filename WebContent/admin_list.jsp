<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script>
		function refresh(){
			setTimeout(function(){
				location.replace(location.href);
			},500);
		}
	</script>
  </head>
  
  <body>
  	<div class="x-nav">
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i>
      </a>
    </div>
    <!-- 刷新事件，弹出层结束刷新此页面 -->
  	<i id="irefresh" onclick="refresh()"></i>
    <div class="x-body">
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="deleteAllAdmin()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加用户','./admin_add.jsp')"><i class="layui-icon"></i>添加</button>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>登录名</th>
            <th>角色</th>
            <th>加入时间</th>
            <th>操作</th>
        </thead>
        <tbody>
	        <c:forEach items="${allAdmins }" var="admin" varStatus="vs">
		          <tr>
		            <td>
		              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
		              <i class="mi" style="display:none">${admin.aid })</i>
		            </td>
		            <td>${vs.count }</td>
		            <td class="mtd" style="display:none">${admin.aid })</td>
		            <td>${admin.adminName }</td>
					<td>${admin.adminPrivilege }</td>
					<td>${admin.create_time}</td>
		            <td class="td-manage">
						<a title="编辑" onclick="x_admin_show('编辑','${pageContext.request.contextPath }/adminPreparedUpdate?aid=${admin.aid }')" href="javascript:;"> 
							<i class="layui-icon">&#xe642;</i>
						</a> 
						<a title="删除" onclick="deleteAdmin('${admin.aid}')" href="javascript:;"> 
							<i class="layui-icon">&#xe640;</i>
						</a>
					</td>
		          </tr>
	         </c:forEach>
        </tbody>
      </table>
    </div>
    <script>
      layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });
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
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
  </body>

</html>