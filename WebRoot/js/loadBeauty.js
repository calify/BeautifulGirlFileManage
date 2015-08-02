function loadBeauty(){
	surl = url + "Action?action=showAll";
	result = postAjaxNoData(surl);
	$.each(result.returnlist,function(i,n){
		$("ul").append("<li id='" + n.id + "'><a style='float:left' class='detail' href='detail.jsp?id=" + n.id + "'>" + n.name + "</a><p>" + n.age + "岁" + n.area + "</li>");
	});
}
