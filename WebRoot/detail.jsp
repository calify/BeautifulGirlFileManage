<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>美女详细内容</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
	<script type="text/javascript" src="js/ajax.js"></script>
	<script>
	url = "<%=basePath%>Action";
	$(document).ready(function(){
		durl = url + '?action=showDetail&id=<%=request.getParameter("id")%>';
		var result = postAjaxNoData(durl);
		$("ul").append("<li>姓名：" + result.obj.name + "</li>");
		$("ul").append("<li>身高：" + result.obj.height + "</li>");
		$("ul").append("<li>体重：" + result.obj.weight + "</li>");
		$("ul").append("<li>年龄：" + result.obj.age + "</li>");
		$("ul").append("<li>地区：" + result.obj.area + "</li>");
		$("ul").append("<li>备注：" + result.obj.instruction + "</li>");
		if(result.obj.picpath != ""){
			$("ul").append("<img src='uploadFile/images/" +result.obj.picpath + "' />");
		}
	});
	</script>
	
  </head>
  
  <body>
    <ul>
    </ul>
    <a href="javascript:void(0)" onclick="javascript:history.back(-1)">返回</a>
  </body>
</html>
