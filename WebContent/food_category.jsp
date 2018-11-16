<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>菜品类别列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
  </head>
  
  <body>
  	<div class="x-nav">
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i>
      </a>
    </div>
    <div class="x-body">
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="deleteAllFoodCategory()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加菜品类别','./food_category_add.jsp')"><i class="layui-icon"></i>添加</button>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>类别</th>
            <th>创建时间</th>
            <th>修改时间</th>
            <th>操作</th>
        </thead>
        <tbody>
	        <c:forEach items="${foodCategories }" var="category" varStatus="vs">
		          <tr>
		            <td>
		              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
		              <i class="mi" style="display:none">${category.pk_fcwc_id })</i>
		            </td>
		            <td>${vs.count }</td>
		            <td style="display:none">${category.pk_fcwc_id }</td>
		            <td>${category.fcwc_name }</td>
					<td>${category.fcwc_create_time }</td>
					<td>${category.fcwc_modified_time}</td>
		            <td class="td-manage">
						<a title="编辑" onclick="x_admin_show('编辑','${pageContext.request.contextPath}/foodCategoryPreparedUpdate?fcid=${category.pk_fcwc_id }')" href="javascript:;"> 
							<i class="layui-icon">&#xe642;</i>
						</a> 
						<a title="删除" onclick="deleteFoodCategory('${category.pk_fcwc_id}')" href="javascript:;"> 
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
		function deleteFoodCategory(fc_id){
			var isDel=confirm("确认要删除吗？");
			if(isDel){
				location.href="${pageContext.request.contextPath}/foodCategoryDelete?fc_id="+fc_id;
			}		
		}
		/* 删除选中的admin */
		function deleteAllFoodCategory(){
			var isDel=confirm("确认要删除所选项吗？");
			if(isDel){
				var fcids=$("div.layui-form-checked + i.mi").text();
				/* alert(aids); */
				
				location.href="${pageContext.request.contextPath}/foodCategoryMultiDelete?fcids="+fcids;
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