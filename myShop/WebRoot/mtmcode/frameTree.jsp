<%@ page language="java" pageEncoding="utf-8"%> 
<html>
<head>
<title>frameTree.jsp</title>
<script language="JAVASCRIPT">
	function locked(){
		document.getElementById("menuId").contentWindow.document.body.scroll="no"; 
		window.text.locked();
		window.menu.locked();
	}
</script>
</head>
	<frameset name="treeframeset1" frameborder="0" framespacing="0" border="0" cols="200,*" rows="*">
		<frameset name="treeframeset2" frameborder="0" framespacing="0" border="0" cols="1,195" rows="*">
  			<frame marginwidth="0" marginheight="0" src="tree1.htm" name="code" scrolling="no" frameborder="1">
  			<frame marginwidth="5" marginheight="5" src="menu_empty.html" name="menu" id="menuId" scrolling="auto" frameborder="1">
		</frameset>
		<frame marginwidth="5" marginheight="5" src="right.html" name="text">
	</frameset>
<body bgcolor="#FFFFFF" text="#000000">
<noframes></noframes>
</body>
</html>
