function loadBeauty(){
	surl = url + "Action?action=showAll";
	result = postAjaxNoData(surl);
	$.each(result.returnlist,function(i,n){
		$("ul").append("<li><a style='float:left' class='detail' id='" + n.id + "' href='detail.jsp?id=" + n.id + "'>" + n.name + "</a><p>" + n.age + "岁" + n.area + "</li>");
	});
}
