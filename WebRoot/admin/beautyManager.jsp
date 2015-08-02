<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>美女管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript" src="js/loadBeauty.js"></script>
	<script type="text/javascript" src="js/operation.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	
	<script type="text/javascript">
	function ajaxFileUpload() {
		$("#loading").ajaxStart(function() {
			$(this).show();
		}).ajaxComplete(function() {
			$(this).hide();
		});

		$.ajaxFileUpload({
			url : 'upload',
			secureuri : false,
			fileElementId : 'fileToUpload',
			dataType : 'json',
			data : {username : $("#username").val()},
			success : function(data, status) {
				picpath = data.obj;
				console.log(data);
				if(data.result == "success"){
					alert("上传成功！");
				}
			},
			error : function(data, status, e) {
				alert('上传出错');
			}
		})

		return false;

	}
	</script>

	<script>
	
	url = "<%=basePath%>" ;
	
	//获取用户输入数据
	function getInfo(){
		var jsondata = {};	
		jsondata.id = $("#id").val();
		jsondata.name = $("#name").val();
		jsondata.height = $("#height").val();
		jsondata.weight = $("#weight").val();
		jsondata.age = $("#age").val();
		jsondata.area = $("#area").val();
		jsondata.instruction = $("#instruction").val();
		jsondata.picpath = picpath;
		return jsondata;		
	}
	
	function showUpdate(that){
		//隐藏其它元素，显示修改页面
		$("#container").children().hide();
		$("#inputbeauty").show();
		$("#add_new").hide();
		var durl = url + 'Action?action=showDetail&id=' + $(that).parent().attr("id");
		//ajax请求，获得美女详细内容
		var result = postAjaxNoData(durl);			
		//加载美女详细内容
		$("#id").attr("value",result.obj.id);
		$("#name").attr("value",result.obj.name);
		$("#height").attr("value",result.obj.height);
		$("#weight").attr("value",result.obj.weight);
		$("#age").attr("value",result.obj.age);
		$("#area").attr("value",result.obj.area);
		$("#instruction").attr("value",result.obj.instruction);
	}
	
	function queryBeauty(){
		var json = {};
		json.name = $("#beauty_name").val()
		query(url + "DataAction?action=query&role=beauty",json);
		$.each(result.returnlist,function(i,n){
		$("ul").append("<li id='" + n.id + "'>" + n.name + "<a class='update' onclick='showUpdate(this)' href='javascript:void(0)'>修改</a>&nbsp<a onclick='delBeauty(this)' class='del' href='javascript:void(0)' >删除</a></li>");
	});
    }
	
	function delBeauty(that){
		del(url + "DataAction?action=del&role=beauty",$(that).parent().attr("id"));
    }
	
	function addBeauty(){
		add(url + "DataAction?action=add&role=beauty", getInfo());
	}
	
	function updateBeauty(){
		update(url + "DataAction?action=update&role=beauty", getInfo());
	}
	
	$(document).ready(function(){
		
		//隐藏其它元素
		$("#container").children().hide();
		$("ul").empty();
		
       	//载入美女列表
        loadBeauty();
        $("ul").show();
		$("li").append("<a class='update' onclick='showUpdate(this)' href='javascript:void(0)'>修改</a>&nbsp<a class='del' href='javascript:void(0)' onclick='delBeauty(this)'>删除</a>");
		                 					
		$("a#add").click(function(){
			//隐藏其它元素，显示添加页面
			$("#container").children().hide();
			$("#inputbeauty").show();
			$("#add_new").show();
			$("#update_new").hide();
		});
	});
	</script>

  </head>
  
  <body>
  <h1>&nbsp;美女管理</h1>
  <a id="add" href="javascript:void(0)">添加美女档案</a>
  <a href="admin/adminIndex.jsp">返回管理首页</a>
  <a href="Action?action=logout">退出管理</a><br/>
      美女名称<input id="beauty_name" type="text"/>
  <input type="submit" id="query" value="查找" onclick="queryBeauty()" /><hr/>
  
  <div id='container'>
 
  <ul>
  </ul>
  
  <div id='inputbeauty'>
  <input style="display:none" id='id' type='text'/><br/>
  美女名称<input id='name' type='text'/><br/>
  美女身高<input id='height' type='text'/><br/>
  美女体重<input id='weight' type='text'/><br/>
  美女年龄<input id='age' type='text'/><br/>
  美女地区<input id='area' type='text'/><br/>
  美女简介<input id='instruction' type='text'/><br/>
 <input id="fileToUpload" type="file" size="45" name="fileToUpload" class="input">
<button class="button" onclick="return ajaxFileUpload();">上传</button><br/>
 <input type="submit" id="add_new" value="添加" onclick="addBeauty()"/>
 <input type="submit" id="update_new" value="确定修改" onclick="updateBeauty()"/>
 </div>
 
  </div>
  </body>
</html>
