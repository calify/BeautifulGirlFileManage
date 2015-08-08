<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>美女详细内容</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"> <meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="js/jquery-1.7.min.js"></script>
	<script type="text/javascript" src="js/ajax.js"></script>
	
	<script>
	url = "<%=basePath%>";
	function support(support,id){
		jsondata={};
		jsondata.support = support + 1;
		jsondata.id = id;
		var result = postAjax(url+"DataAction?action=support",jsondata);
		if(result.result == "success"){
			support++;
			$("#support").children("p").text("点赞(" + support + ")");
			$("#support").children("a").attr("onclick","support(" + support + "," + id + ")");
		}
	}
	function against(against,id){
		jsondata={};
		jsondata.against = against + 1;
		jsondata.id = id;
		var result = postAjax(url+"DataAction?action=against",jsondata);
		if(result.result == "success"){
			against++;
			$("#against").children("p").text("恶心(" + against + ")");
			$("#against").children("a").attr("onclick","against(" + against + "," + id + ")");
		}
	}
	
	$(document).ready(function(){
		durl = url + 'Action?action=showDetail&id=<%=request.getParameter("id")%>';
		var result = postAjaxNoData(durl);
		$("ul").append("<li>姓名：" + result.obj.name + "</li>");
		$("ul").append("<li>身高：" + result.obj.height + "</li>");
		$("ul").append("<li>体重：" + result.obj.weight + "</li>");
		$("ul").append("<li>年龄：" + result.obj.age + "</li>");
		$("ul").append("<li>地区：" + result.obj.area + "</li>");
		$("ul").append("<li>备注：" + result.obj.instruction + "</li>");
		$("ul").after("<div id='show'><div id='support' style='float:left;'><p>点赞(" + result.obj.support + ")</p><a href='javascript:void(0)' style='display:block;width:88px;' onclick='support(" + result.obj.support + "," + result.obj.id + ")'><img width=55px height=45px src='img/点赞.jpg'/></a></div><div id='against' style='float:left;'><p>恶心(" + result.obj.against + ")</p><a href='javascript:void(0)' onclick='against(" + result.obj.against + "," + result.obj.id + ")' style='display:block;width:88px;'><img width=41px height=55px src='img/恶心.jpg'/></a></div></div>");
		//加载图片
		purl = url + 'DataAction?action=query&role=bpic';
		jsondata = {};
		jsondata.id = <%=request.getParameter("id")%>;
		var result = postAjax(purl,jsondata);
		if(result.result == "success"){
			
			$.each(result.returnlist,function(i,n){
				//本地
				//$("#against").after("<img style='clear:both;' src='DownLoadPhoto?filename=" + n.path + "'/><br/><p>" + n.description + "</p><br/>");
				//阿里云
				$("#against").after("<img style='clear:both;' src='uploadFile/images/" + n.path + "'/><br/><p>" + n.description + "</p><br/>");
			});
		}
		else{
		$("ul").append("<p>该美女暂时没有图片</p>");
		}
	});
	</script>
	
  </head>
  
  <body>
    <ul>
    </ul>
    <a href="javascript:void(0)" onclick="javascript:history.back(-1)">返回</a>
  </body>
</html>
