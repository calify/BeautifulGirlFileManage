<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>美女档案管理系统V5.0</title>
    
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
    <h1>美女档案管理系统V5.0</h1>
    <h2> 更新内容：</h2>
    <ul>
    <li>新增分页功能</li>
    <li>新增组合查询功能</li>
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
