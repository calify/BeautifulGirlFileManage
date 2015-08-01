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
			$("ul").append("<li id='" + n.id + "'>" + n.username + "<br/><a class='update' href='javascript:void(0)'>修改</a>" + "<a class='del' href='javascript:void(0)'>删除</a>" +"</li>");
			});
		$("a.del").click(function(){
			cf = window.confirm("确定要删除吗？");
			var durl = url + "DataAction?action=del&role=user";
			var jsondata = {};
			jsondata.id = $(this).parent().attr("id");
			console.log(jsondata);
			if(cf){
				var result = postAjax(durl,jsondata);
			}
			if(result.result == "success"){
				alert("删除成功！");
				history.go(0);
			}
			else{
				alert("删除失败！");
				history.go(0);
			}
		});	
		
	});
	</script>



  </head>
  
  <body>
    <h1>用户列表</h1>
    <a id="add" href="javascript:void(0)">添加新用户</a>
 	<a href="admin/adminIndex.jsp">返回管理首页</a>
 	<a href="Action?action=logout">退出管理</a><br/>
          用户名称<input id="beauty_name" type="text"/>
    <input type="submit" id="query" value="查找"/>
    <hr>
    <ul>
    </ul>
  </body>
</html>
