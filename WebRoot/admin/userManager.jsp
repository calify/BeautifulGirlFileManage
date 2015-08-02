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
	<script type="text/javascript" src="js/operation.js"></script>
	
	<script>
	url = "<%=basePath%>" ;
	
	//获取用户输入数据
	function getInfo(){
		var jsondata = {};	
		jsondata.id = $("#id").val();
		jsondata.username = $("#username").val();
		jsondata.password = $("#password").val();
		if($("#role").val() == "管理员"){
			jsondata.role = "admin";
		}
		else if($("#role").val() == "普通用户"){
			jsondata.role = "user";
		}
		return jsondata;		
	}
	
	function showUpdate(that){
		//隐藏其它元素，显示修改页面
		$("#container").children().hide();
		$("#inputuser").show();
		$("#add_new").hide();
		var durl = url + 'Action?action=showAUser&id=' + $(that).parent().attr("id");
		//ajax请求，获得用户详细内容
		var result = postAjaxNoData(durl);			
		//加载美女详细内容
		$("#id").attr("value",result.obj.id);
		$("#username").attr("value",result.obj.username);
		$("#password").attr("value",result.obj.password);
		if(result.obj.role == "admin"){
			$("#role").attr("value","管理员");
		}
		else if(result.obj.role == "user"){
			$("#role").attr("value","普通用户");
		}
	}
	
	function queryUser(){
		var json = {};
		json.username = $("#user_name").val()
		query(url + "DataAction?action=query&role=user",json);
		$.each(result.returnlist,function(i,n){
		$("ul").append("<li id='" + n.id + "'>" + n.username + "<a class='update' onclick='showUpdate(this)' href='javascript:void(0)'>修改</a>&nbsp<a onclick='delUser(this)' class='del' href='javascript:void(0)' >删除</a></li>");
	});
		/*$("ul").append("<li id='" + result.obj.id + "'>" + result.obj.username + "</li>");
        $("li").append("<a class='update' onclick='showUpdate(this)' href='javascript:void(0)'>修改</a>&nbsp<a onclick='delUser(this)' class='del' href='javascript:void(0)' >删除</a>");*/
    }
	
	function delUser(that){
		del(url + "DataAction?action=del&role=user",$(that).parent().attr("id"));
    }
	
	function addUser(){
		add(url + "DataAction?action=add&role=user", getInfo());
	}
	
	function updateUser(){
		update(url + "DataAction?action=update&role=user", getInfo());
	}
	
	$(document).ready(function(){
		//隐藏其它元素
		$("#container").children().hide();
		$("ul").empty();
		
		//载入用户列表
		$("ul").show();
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
			$("ul").append("<li id='" + n.id + "'>" + n.username + "  " + role + "<br/><a class='update' href='javascript:void(0)' onclick='showUpdate(this)'>修改</a>&nbsp" + "&nbsp<a class='del' href='javascript:void(0)' onclick='delUser(this)'>删除</a>" +"</li>");
			});
		
		$("a#add").click(function(){
			//隐藏其它元素，显示添加页面
			$("#container").children().hide();
			$("#inputuser").show();
			$("#add_new").show();
			$("#update_new").hide();
		});
		});	
	
	</script>



  </head>
  
  <body>
    <h1>用户列表</h1>
    <a id="add" href="javascript:void(0)">添加新用户</a>
 	<a href="admin/adminIndex.jsp">返回管理首页</a>
 	<a href="Action?action=logout">退出管理</a><br/>
          用户名称<input id="user_name" type="text"/>
    <input type="submit" id="query" value="查找" onclick="queryUser()"/>
    <hr>
    <div id="container">
    
    <ul>
    </ul>
    
    <div id='inputuser'>
    	<input id='id' type='text' style='display:none'/>
  		用户名<input id='username' type='text'/><br/>
  		密码<input id='password' type='password'/><br/>
  		角色：
  		<select id='role'>
  		<option >普通用户</option>
  		<option>管理员</option>
  		</select><br/>
 	<input type="submit" id="add_new" value="添加" onclick="addUser()"/>
 	<input type="submit" id="update_new" value="确定修改" onclick="updateUser()"/>
 	</div>
 	
    </div>
  </body>
</html>
