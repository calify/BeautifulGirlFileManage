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
		$("#addUser").hide();
		$("#updateuser").hide();
		$("#query").click(function(){
			var surl = url + "DataAction?action=query&role=user"
			var jsondata = {};
			jsondata.username = $("#user_name").val();
			result = postAjax(surl,jsondata);
			if(result.result == "success"){
				$("ul").empty();
				$("#addbeauty").hide();
				$("ul").show();
				$("ul").append("<li>" + result.obj.username + "</li>");
				$("li").append("<a class='update' href='javascript:void(0)'>修改</a>&nbsp<a class='del' href='javascript:void(0)'>删除</a>");
			}
		});
		surl = url + "Action?action=showUser";
		result = postAjaxNoData(surl);
		$.each(result.returnlist,function(i,n){
			var role;
			if(n.role == "admin"){
				role = "管理员";
			}
			else if(n.role == "user"){
				role = "普通用户";
			}
			$("ul").append("<li id='" + n.id + "'>" + n.username + "  " + role + "<br/><a class='update' href='javascript:void(0)'>修改</a>&nbsp" + "&nbsp<a class='del' href='javascript:void(0)'>删除</a>" +"</li>");
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
		
		$("a#add").click(function(){
			$("#addUser").show();
			$("ul").hide();
			$("#updatebeauty").hide();
		});	
		
		$("#add_new").click(function(){
			var aurl = url + "DataAction?action=add&role=user";
			var jsondata = {};
			
			//获取用户输入数据
			jsondata.username = $("#a_name").val();
			jsondata.password = $("#a_password").val();
			if($("#a_role").attr("value") == "管理员"){
				jsondata.role = "admin";				
			}
			else if($("#a_role").attr("value") == "普通用户"){
				jsondata.role = "user";				
			}
			
			//ajax请求
			var result = postAjax(aurl,jsondata);
			if(result.result == "success"){
				alert("添加成功！");
				history.go(0);
			}
			else{
				alert("添加失败！");
				history.go(0);
			}
		});
		
		$(".update").click(function(){
			var surl = url + 'Action?action=showAUser&id=' + $(this).parent().attr("id");
			
			//ajax请求，获得用户详细内容
			var result = postAjaxNoData(surl);
			
			//隐藏用户列表，显示修改页面
			$("#updateuser").show();
			$("ul").hide();
			
			//加载用户详细内容
			$("#u_id").attr("value",result.obj.id);
			$("#u_name").attr("value",result.obj.username);
			$("#u_password").attr("value",result.obj.password);
			if(result.obj.role == "admin"){
				$("#u_role").attr("value","管理员");
			}
			else if(result.obj.role == "user"){
				$("#u_role").attr("value","普通用户");
			}
		});
		
		$("#update_new").click(function(){
			var uurl = url + "DataAction?action=update&role=user";
			var jsondata = {};
			
			//获取用户输入数据
			jsondata.id = $("#u_id").val();
			jsondata.username = $("#u_name").val();
			jsondata.password = $("#u_password").val();
			if($("#u_role").attr("value") == "管理员"){
				jsondata.role = "admin";
			}
			else if($("#u_role").attr("value") == "普通用户"){
				jsondata.role = "user";
			}
			
			//ajax请求
			var result = postAjax(uurl,jsondata);
			if(result.result == "success"){
				alert("修改成功！");
				history.go(0);
			}
		})
		
		
		
		
	});
	</script>



  </head>
  
  <body>
    <h1>用户列表</h1>
    <a id="add" href="javascript:void(0)">添加新用户</a>
 	<a href="admin/adminIndex.jsp">返回管理首页</a>
 	<a href="Action?action=logout">退出管理</a><br/>
          用户名称<input id="user_name" type="text"/>
    <input type="submit" id="query" value="查找"/>
    <hr>
    <ul>
    </ul>
    <div id='addUser'>
  		用户名<input id='a_name' type='text'/><br/>
  		密码<input id='a_password' type='password'/><br/>
  		角色：
  		<select id='a_role'>
  		<option >普通用户</option>
  		<option>管理员</option>
  		</select><br/>
 	<input type="submit" id="add_new" value="添加"/>
 	
 	</div>
        <div id='updateuser'>
        <input id='u_id' style="display:none" value="" type='text'/>
  		用户名<input id='u_name' type='text'/><br/>
  		密码<input id='u_password' type='password'/><br/>
  		角色：
  		<select id='u_role'>
  		<option>普通用户</option>
  		<option>管理员</option>
  		</select><br/>
 	<input type="submit" id="update_new" value="确定修改"/>
 	</div>
    
  </body>
</html>
