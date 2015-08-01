<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
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
		$("#addbeauty").hide();
		$("#updatebeauty").hide();
		$("ul").empty();
		loadBeauty();
		$("li").append("<a class='update' href='javascript:void(0)'>修改</a>&nbsp<a class='del' href='javascript:void(0)'>删除</a>");
		$("#query").click(function(){
			var surl = url + "DataAction?action=query&role=beauty"
			var jsondata = {};
			jsondata.name = $("#beauty_name").val();
			result = postAjax(surl,jsondata);
			if(result.result == "success"){
				$("ul").empty();
				$("#addbeauty").hide();
				$("ul").show();
				$("ul").append("<li>" + result.obj.name + "</li>");
				$("li").append("<a class='update' href='javascript:void(0)'>修改</a>&nbsp<a class='del' href='javascript:void(0)'>删除</a>");
			}
		});
		
		$("a.del").click(function(){
			cf = window.confirm("确定要删除吗？");
			var durl = url + "DataAction?action=del&role=beauty";
			var jsondata = {};
			jsondata.id = $(this).prev().prev().prev().attr("id");
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
			$("#addbeauty").show();
			$("ul").hide();
			$("#updatebeauty").hide();
		});
			
		$("#add_new").click(function(){
			var aurl = url + "DataAction?action=add&role=beauty";
			var jsondata = {};
			
			//获取用户输入数据
			jsondata.name = $("#a_name").val();
			jsondata.height = $("#a_height").val();
			jsondata.weight = $("#a_weight").val();
			jsondata.age = $("#a_age").val();
			jsondata.area = $("#a_area").val();
			jsondata.instruction = $("#a_instruction").val();
			
			//ajax请求
			var result = postAjax(aurl,jsondata);
			if(result.result == "success"){
				alert("添加成功！");
				history.go(0);
			}
		})
		
		$(".update").click(function(){
			var durl = url + 'Action?action=showDetail&id=' + $(this).prev().prev().attr("id");
			
			//ajax请求，获得美女详细内容
			var result = postAjaxNoData(durl);
			
			//隐藏美女列表，显示修改页面
			$("#updatebeauty").show();
			$("ul").hide();
			
			//加载美女详细内容
			$("#u_id").attr("value",result.obj.id);
			$("#u_name").attr("value",result.obj.name);
			$("#u_height").attr("value",result.obj.weight);
			$("#u_weight").attr("value",result.obj.weight);
			$("#u_age").attr("value",result.obj.age);
			$("#u_area").attr("value",result.obj.area);
			$("#u_instruction").attr("value",result.obj.instruction);
		});
		
		$("#update_new").click(function(){
			var uurl = url + "DataAction?action=update&role=beauty";
			var jsondata = {};
			
			//获取用户输入数据
			jsondata.id = $("#u_id").val();
			jsondata.name = $("#u_name").val();
			jsondata.height = $("#u_height").val();
			jsondata.weight = $("#u_weight").val();
			jsondata.age = $("#u_age").val();
			jsondata.area = $("#u_area").val();
			jsondata.instruction = $("#u_instruction").val();
			
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
  <h1>美女管理</h1>
  <a id="add" href="javascript:void(0)">添加美女档案</a>
  <a href="admin/adminIndex.jsp">返回管理首页</a>
  <a href="Action?action=logout">退出管理</a><br/>
      美女名称<input id="beauty_name" type="text"/>
  <input type="submit" id="query" value="查找"/>
  <hr>
  <ul>
  </ul>
  <div id='addbeauty'>
  美女名称<input id='a_name' type='text'/><br/>
  美女身高<input id='a_height' type='text'/><br/>
  美女体重<input id='a_weight' type='text'/><br/>
  美女年龄<input id='a_age' type='text'/><br/>
  美女地区<input id='a_area' type='text'/><br/>
  美女简介<input id='a_instruction' type='text' width="100px" height="200px"/>
 <input type="submit" id="add_new" value="提交"/>
 </div>
 
   <div id='updatebeauty'>
   <input id='u_id' style="display:none" value="" type='text'/>
  美女名称<input id='u_name' type='text'/><br/>
  美女身高<input id='u_height' type='text'/><br/>
  美女体重<input id='u_weight' type='text'/><br/>
  美女年龄<input id='u_age' type='text'/><br/>
  美女地区<input id='u_area' type='text'/><br/>
  美女简介<input id='u_instruction' type='text' width="100px" height="200px"/>
 <input type="submit" id="update_new" value="提交"/>
  </div>
  
  </body>
</html>
