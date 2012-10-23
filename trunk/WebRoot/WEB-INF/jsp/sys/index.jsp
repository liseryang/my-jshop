<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/basePath.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
<head>
<title>后台管理</title>
<style type="text/css">
	html,body,iframe {
		margin: 0;
		padding: 0;
		overflow-x: hidden;
	}
</style>
<script type="text/javascript" src="<%=basePath%>/scripts/easyui/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>/jsp/admin/css/custom.css" type="text/css" />
<style type="text/css"></style>
<base target="sysMain"/>
</head>

<body  style="overflow-x:hidden;overflow-y:hidden;">
	<table width="100%"   style="height:100%;border-bottom:2px solid #99BBE8;margin:0;padding:0;" border="0" align="center"
		cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3">
				<div id="top">
					<div class="admin-header">
						<div id="title">
							菜籽商城后台管理
						</div>
						<div id="topnav">
							<a ><b>Admin</b> 您好!&nbsp; 现在是: 2012/09/09 19:05:30</a> |
							<a href="#">个人资料</a> |
							<a href="#"> 网站首页</a> |
							<a href="#"> 退出</a>
							<br />
							
						</div>
						<ul id="mainMenu" class="sf-menu">
							<li id="menu_shop" class="current">
								<a href="#" target="_self">商品管理</a>
							</li>
							<li id="menu_order">
								<a href="#" target="_self">订单处理</a>
							</li>
							<li id="menu_member">
								<a href="#" target="_self">会员管理</a>
							</li>
							<li id="4">
								<a href="#" target="_self">模板管理</a>
							</li>
							<li id="menu_sysmng">
								<a href="#" target="_self">系统管理</a>
							</li>
						</ul>
					</div>
					<div class="pagetitle"></div>
				</div>
			</td>
		</tr>
		<tr  height="100%">
			<td id="left" width="162px" height="100%" valign="top">
				<div id="leftMenu"  style="width: 162px;overflow:auto;overflow-x:hidden;">
				  <div id="shop">
					<div>商品管理<span class="opendown"></span></div>
					 <ul>
					 	<li><a href="#">商品列表</a></li>
					 	<li><a href="#">添加商品</a></li>
					 </ul>
					<div>商品分类管理<span class="opendown"></span></div>
					<ul>
					 	<li><a href="#">分类维护</a></li>
					 	<li><a href="#">添加分类</a></li>
					 </ul>
					<div>品牌管理<span class="opendown"></span></div>
					<ul>
					 	<li><a href="#">品牌维护</a></li>
					 	<li><a href="#">添加品牌</a></li>
					 </ul>
				  </div>
				 <div id="order" style="display:none">
					<div>订单管理<span class="opendown"></span></div>
					 <ul>
					 	<li><a href="#">订单维护</a></li>
					 </ul>
					<div>快递单管理<span class="opendown"></span></div>
					<ul>
					 	<li><a href="#">发货点管理</a></li>
					 	<li><a href="#">快递单模板</a></li>
					 </ul>
				  </div>
				 <div id="member" style="display:none">
					<div>会员维护<span class="opendown"></span></div>
					 <ul>
					 	<li><a href="#">会员维护</a></li>
					 	<li><a href="#">会员审核</a></li>
					 </ul>
					<div>会员充值 <span class="opendown"></span></div>
					<ul>
					 	<li><a href="#">充值信息</a></li>
					 </ul>
				  </div>
				   <div id="sysmng" style="display:none">
				   	<div>人员权限<span class="opendown"></span></div>
					 <ul>
					 	<li><a href="<%=basePath%>/sys/user_mng.do?userId=123" >人员管理</a></li>
					 	<li><a href="#">人员权限关联</a></li>
					 </ul>
					<div>角色权限 <span class="opendown"></span></div>
					<ul>
					 	<li><a href="#">角色管理</a></li>
					 	<li><a href="#">角色权限关联</a></li>
					 </ul>
					 <div>菜单管理<span class="opendown"></span></div>
					 <ul>
					 	<li><a href="#">菜单管理</a></li>
					 	<li><a href="#">菜单功能管理</a></li>
					 </ul>
					  <div>业务字典管理<span class="opendown"></span></div>
					 <ul>
					 	<li><a href="#">业务字典类型</a></li>
					 	<li><a href="#">业务字典项</a></li>
					 </ul>
				  </div>
				</div>
			</td>
			<td width="6px"><div id="hideLeft" class="expandLeftMenu">&nbsp;</div></td>
			<td width="100%" id="maintd" class="adminBody" align="center" height="100%">
				<iframe id="sysMain" name="sysMain" src="<%=basePath%>/sys/user_mng.do" marginwidth="0" marginheight="0"
				  scrolling="auto" style="overflow-x:hidden;" class="main_frame" 
				   width="100%" height="100%" frameborder="0" >
					[管理项对应操作页面]
				</iframe>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
$(function(){
	
	//显示二级菜单
   $("#mainMenu li").click(function(){
  	   $("#mainMenu li").removeClass("current");
   	   $(this).addClass("current");
   	   $("#leftMenu div").hide();
   	    var menuId= ($(this)[0].id).substr(5); 
   	   $("#"+menuId).show();
   	   $("#"+menuId).find("div").show();
   })
   
   //隐藏/展开三级菜单
   $("#leftMenu div div").click(function(){
  	   $("#leftMenu div div").removeClass("current");
   	   $(this).next("ul").toggle("fast");
   	    $(this).find("span").toggleClass("openup");
   })
   
    $("#leftMenu div div").mouseover(function(){
   	    $(this).find("span").toggleClass("openup");
   })
   
    $("#leftMenu div div").mouseout(function(){
   	    $(this).find("span").toggleClass("openup");
   })
   
   
    $("#leftMenu div ul li").mouseover(function(){
   	   $(this).toggleClass("leftlibg");
   })
   
   $("#leftMenu div ul li").mouseout(function(){
   	   $(this).removeClass("leftlibg");
   })
   
   //点击三级菜单字体变色
   $("#leftMenu div ul li a").click(function(){
    	$("#leftMenu div ul li a").css("color","#003D79");
   	    $(this).css("color","#FF8040");
   })
   
   //隐藏左菜单
   $("#hideLeft").click(function(){
  	   $("#left").toggle();
  	   $("#hideLeft").toggleClass("hideLeftMenu");
   })
   
    $("#hideLeft").mouseover(function(){
  	   $("#hideLeft").toggleClass("hideLeftMenu");
   })
   
});
</script>
</body>
</html>
