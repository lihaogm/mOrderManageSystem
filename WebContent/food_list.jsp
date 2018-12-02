<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>菜品列表</title>
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
<script>
	function refresh(){
		setTimeout(function(){
			location.replace(location.href);
		},500);
	}
</script>


<body>
  <div class="x-nav">
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新"><!--   -->
      <i class="layui-icon" style="line-height:30px" >ဂ</i>
    </a>
  </div>
  <!-- 刷新事件，弹出层关闭刷新此页面 -->
  <i id="irefresh" onclick="refresh()"></i>
  <div class="x-body">
    <div class="layui-row">
      <form class="layui-form layui-col-md12 x-so" action="${pageContext.request.contextPath }/foodSearch" method="post">
	        <div class="layui-inline">
	       		<select name="foodcook_category" lay-verfiy="required" >
		         	<option value="">菜品种类</option>
		         	<c:forEach items="${foodCategories }" var="fc">
		         		<option value="${fc.pk_fcwc_id }">${fc.fcwc_name }</option>
		         	<!-- <option value="1">炒菜</option>
		         	<option value="2">烧菜</option>
		         	<option value="3">蒸菜</option>
		         	<option value="4">汤羹</option>
		         	<option value="5">主食</option> -->
		         	</c:forEach>
	       		</select> 
	       	</div>
	       	<div class="layui-inline">
	       		<select name="foodtype_category" lay-verfiy="required" >
	         		<option value="">荤/素</option>
	         		<c:forEach items="${foodTypes }" var="ft">
			         	<option value="${ft.pk_ftc_id }">${ft.ftc_name }</option>
			         	<!-- <option value="1">小荤</option>
			         	<option value="1">大荤</option>	 -->	
		         	</c:forEach>	
	       		</select>
	       	</div>
	       	<div class="layui-inline">
	       		<input type="text" name="foodname"  placeholder="请输入菜名" autocomplete="off" class="layui-input">
	       	</div>
	       	<div class="layui-inline">
	       		<button class="layui-btn"  lay-submit="" lay-filter="sreach" type="submit"><i class="layui-icon">&#xe615;</i></button>
	       	</div>
      </form>
    </div>
    <xblock>
      <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
      <button class="layui-btn" onclick="x_admin_show('添加菜品','${pageContext.request.contextPath}/foodPreparedAdd')"><i class="layui-icon"></i>添加</button>
      <span class="x-right" style="line-height:40px">共有数据：${foodCounts } 条</span>
    </xblock>
    <table class="layui-table" id="dataTable">
      <thead>
        <tr>
          <th>
            <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
          </th>
          <th>ID</th>
          <th>菜名</th>
          <th>市场价<br>(原价)</th>
          <th>店内价<br>(优惠价)</th>
          <th>图片</th>
          <th>创建时间</th>
          <th>修改时间</th>
          <th>是否热门</th>
          <th>描述</th>
          <th>类别</th>
          <th>是否在售</th>
          <th>操作</th>
      </thead>
      <tbody>
	      <c:forEach items="${foods }" var="food" varStatus="vs">
	        <tr>
	          <td>
	            <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
	          	<i class="mi" style="display:none">${food.food.pk_fid };</i>
	          </td>
	          <td>${vs.count }</td>
	          <td>${food.food.fname }</td>
	          <td>￥${food.food.fmarket_price }</td>
	          <td>￥${food.food.fshop_price }</td>
	          <td>
	          	<img src="${pageContext.request.contextPath }/${food.food.fimage}" width="45" height="40" ></td>
	          <td>${food.food.fcreat_date }</td>
	          <td>${food.food.fmodified_date }</td>
	          <td>${food.food.fis_hot==1?"热门":"" }</td>
	          <td>
	          	<span onclick="showDesc('${food.food.fdesc}')" title="${food.food.fdesc}">...</span>
	          </td>
	          <td>${food.foodCategoryName }
	          	<c:if test="${food.foodTypeName=='无'} ">
		          	
		        </c:if>
		        <c:if test="${food.foodTypeName!='无' }">
					, ${food.foodTypeName }		        
		        </c:if>
		      </td>
	          <td>${food.food.fis_onsale==1?"在售":"下架" }</td>
	          <td class="td-manage">
	            <a title="编辑"  onclick="x_admin_show('编辑','${pageContext.request.contextPath }/foodPreparedEdit?fid=${food.food.pk_fid }')" href="javascript:;">
	              <i class="layui-icon">&#xe642;</i>
	            </a>
	            <a title="删除" onclick="member_del(this,'${food.food.pk_fid}')" href="javascript:;">
	              <i class="layui-icon">&#xe640;</i>
	            </a>
	          </td>
	        </tr>
	      </c:forEach>
      </tbody>
    </table>
    <!-- 分页 -->
    <!-- <div class="page">
      <div>
        <a class="prev" href="">&lt;&lt;</a>
        <a class="num" href="">1</a>
        <span class="current">2</span>
        <a class="num" href="">3</a>
        <a class="num" href="">489</a>
        <a class="next" href="">&gt;&gt;</a>
      </div>
    </div> -->

  </div>
  <script>
	  /* 显示具体的菜品描述信息 */
 	  function showDesc(str){
	  	alert(str);
	  } 
  </script>
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

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            //发异步删除数据
            /* $(obj).parents("tr").remove(); */
            /* layer.msg('已删除!',{icon:1,time:1000}); */
            location.href="${pageContext.request.contextPath}/foodDelete?fcId="+id;
        });
    }

    function delAll () {

      /* var data = tableCheck.getData(); */

      layer.confirm('确认要删除吗？',function(index){
          /* //捉到所有被选中的，发异步进行删除
          layer.msg('删除成功', {icon: 1});
          $(".layui-form-checked").not('.header').parents('tr').remove(); */
    	  var fcids=$("div.layui-form-checked + i.mi").text();
		  location.href="${pageContext.request.contextPath}/foodMultiDelete?fcids="+fcids;
      });
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