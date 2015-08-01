<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>美女档案管理系统V2.0</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>美女档案管理系统V2.0</h1>
    <h2> 更新内容：</h2>
    <ul>
    <li>增加用户管理功能</li>
    <li>对不同用户角色的权限管理，非管理员无法进入用户管理界面</li>
    <li>修补了一些bug</li>
    <li>后台查找功能支持模糊查找，不过目前只能返回一个对象，下个更新版本将会修改</li>
    </ul>
    <h1>测试账号</h1>
    <p>管理员</p>
    <p>账号：testadmin</p>
    <p>密码：testadmin</p>
    <p>普通用户</p>
    <p>账号：testuser</p>
    <p>密码：testuser</p>
    <a href="girl.jsp">点击进入</a>
  </body>
</html>
