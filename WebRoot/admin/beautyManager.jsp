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
	function ajaxFileUpload(bid) {
		$.ajaxFileUpload({
			url : 'upload',
			secureuri : false,
			fileElementId : 'fileToUpload',
			dataType : 'json',
			data : {username : $("#username").val()},
			success : function(data, status) {
				jsondata = {};
				jsondata.path = data.obj;
				jsondata.bid = bid;
				jsondata.name = $("#picname").val();
				jsondata.description = $("#picdes").val();
				result = postAjax(url + "DataAction?action=add&role=bpic", jsondata);
				alert("添加成功！");
				history.go(0);
			},
			error : function(data, status, e) {
				alert('上传出错');
			}
		})

		return false;

	}
	</script>

	<script>
	
	url = "<%=basePath%>";
	var pagenow;
	if(<%=request.getParameter("pagenow")%> != null){
		pagenow = <%=request.getParameter("pagenow")%>;
	}
	else{
		pagenow = 1;
	}
	presult = postAjaxNoData(url + "Action?action=getPageCount");
	var pagecount = presult.obj;
	
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
	
	function queryBeautyByBean(){
		jsondata.beauty.name = $("#beauty_name").val();
		if($("#beauty_age").val() != ""){
			jsondata.beauty.age = $("#beauty_age").val();
		}
		else{
			jsondata.beauty.age = -1;
		}
		jsondata.beauty.area = $("#beauty_area").val();
		loadBeauty(1,"manage");
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
	
	function delPic(id){
		del(url + "DataAction?action=del&role=bpic",id);
	}
	function showPic(p,i){
		//本地
		//$("img").attr("src","DownLoadPhoto?filename=" + p);
		//阿里云
		$("img").attr("src","uploadFile/images/" + p);
		$("p").text(i);
	}
	
	function managePic(that){
		//加载图片列表
		bid = $(that).parent().attr("id");
		purl = url + 'DataAction?action=query&role=bpic';
		jsondata = {};
		jsondata.id = $(that).parent().attr("id");
		query(purl,jsondata);
		$("#fileToUpload").show();
		$("#bt_upload").show();
		$("#pic").show();
		$("#bt_upload").attr("onclick","return ajaxFileUpload(" + bid + ");");
		if(result.result == "success"){
			$.each(result.returnlist,function(i,n){
			$("ul").append("<li><a href='javascript:void(0)' onclick='showPic(\"" + n.path + "\",\"" + n.description + "\")'>" + n.name + "</a> <a href='javascript:void(0)' onclick='delPic(" + n.id + ")' >删除</a></li>");
			});
		}
		else{
		$("ul").append("<p>该美女暂时没有图片</p>");
		}
	}
	var jsondata ={
		page:{
		pagesize:5
		},
		beauty:{
		age:-1
		}
	};
	$(document).ready(function(){
		
		//隐藏其它元素
		$("#container").children().hide();
		$("ul").empty();
		
       	//载入美女列表
        loadBeauty(1,"manage");
        $("ul").show();
		$("#page").show();                					
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
      美女年龄<input id="beauty_age" type="text"/>
      美女地区<input id="beauty_area" type="text"/>
  <input type="submit" id="query" value="查找" onclick="queryBeautyByBean()" /><hr/>
  
  <div id='container'>
 
  <ul>
  </ul>
  
  <div id='page'>
  </div>
  
  <div id='inputbeauty'>
  <input style="display:none" id='id' type='text'/><br/>
  美女名称<input id='name' type='text'/><br/>
  美女身高<input id='height' type='text'/><br/>
  美女体重<input id='weight' type='text'/><br/>
  美女年龄<input id='age' type='text'/><br/>
  美女地区<input id='area' type='text'/><br/>
  美女简介<input id='instruction' type='text'/><br/>
 <input type="submit" id="add_new" value="添加" onclick="addBeauty()"/>
 <input type="submit" id="update_new" value="确定修改" onclick="updateBeauty()"/>
 </div>
 
 <div id="pic">
 图片名<input id="picname" type="text"/><br/>
 图片描述<input id="picdes" type="text"/><br/>
 </div>
 <input id="fileToUpload" type="file" size="45" name="fileToUpload" class="input">
<button id="bt_upload" class="button" onclick="return ajaxFileUpload();">添加</button>
 
  </div>
  <img/>
  <p></p>
  </body>
</html>
