<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript" src="js/loadBeauty.js"></script>
	
	<script>
	url = "<%=basePath%>" ;
	$(document).ready(function(){
		surl = url + "Action?action=showUser";
		result = postAjaxNoData(surl);
		$.each(result.returnlist,function(i,n){
			$("ul").append("<li id='" + n.id + "'>" + n.username + "</li>");
			$("ul").append("<a class='update' href='javascript:void(0)'>修改</a><br/>");
			$("ul").append("<a class='del' href='javascript:void(0)'>删除</a>");
			});
	});
	</script>



  </head>
  
  <body>
    <h1>用户列表</h1>
    <hr>
    <ul>
    </ul>
  </body>
</html>
