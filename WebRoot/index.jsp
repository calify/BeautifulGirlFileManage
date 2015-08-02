<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>美女档案管理系统V3.0</title>
    
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
    <h1>美女档案管理系统V3.0</h1>
    <h2> 更新内容：</h2>
    <ul>
    <li>优化用户管理和美女管理页面的js代码，将一些常用的操作封装成<a href="js/operation.js">operation.js</a></li>
    <li>修补了查找以后点击修改删除按钮没反应的bug</li>
    <li>detail页面的返回首页超链接修改为返回上一页</li>
    <li>支持图片上传功能，然而我只会用这个功能，还没弄懂原理</li>
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
