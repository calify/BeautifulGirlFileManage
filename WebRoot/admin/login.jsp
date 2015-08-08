<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
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
		$("img").attr("src","safecode?"+Math.random());
		$("#pwd").attr("value","");
		$("#safecodeText").attr("value","");
		var jsondata={};
		$("#login").click(function(){
			var lurl = url + "?action=login&safecode="+$("#safecodeText").val();
			jsondata.username=$("#user").val();
			jsondata.password=$("#pwd").val();
			result = postAjax(lurl,jsondata);
			if(result.result == "success"){
				window.location="admin/adminIndex.jsp";
			}
			else{
				alert("登录失败！"+result.error);
				history.go(0);
			}
		});
		$("a").click(function(){
			$(this).children().attr("src","safecode?"+Math.random());	
		});
	});
	</script>
  </head>
  
  <body>
      <h1>登录美女档案管理系统V5.0</h1>
  用户名<input value="" id="user" type="text"/><br/>
  密&nbsp&nbsp&nbsp码<input value="" id="pwd" type="password"/><br/>
 验证码<input value="" id="safecodeText" type="text"/><a href="javascript:void(0)"><img alt="验证码" src=""></a><br/>
 <input id="login" type="submit" value="登录"></input>
  </body>
</html>
