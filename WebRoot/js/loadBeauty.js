function loadBeauty(pagenow,flag){
	jsondata.page.pagenow = pagenow;
	surl = url + "DataAction?action=showByBean";
	result = postAjax(surl,jsondata);
	pagecount = result.obj.pagecount;
	$("ul").empty();
	$.each(result.returnlist,function(i,n){
		$("ul").append("<li id='" + n.id + "'><a style='float:left' class='detail' href='detail.jsp?id=" + n.id + "'>" + n.name + "</a><p>" + n.age + "岁" + n.area + "</li>");
	});
	if(flag == "manage"){
		$("li").append("<a class='update' onclick='showUpdate(this)' href='javascript:void(0)'>修改</a>&nbsp<a class='del' href='javascript:void(0)' onclick='delBeauty(this)'>删除</a>&nbsp<a class='add_pid' href='javascript:void(0)' onclick='managePic(this)'>管理图片</a>");
	}
	//显示翻页
	$("#page").empty();
	if(result.obj.rowcount > 0){
		if(pagenow!=1){
  			$("#page").append("<a onclick='loadBeauty(" + (pagenow-1) + ",\"" + flag  + "\")' href='javascript:void(0)'>上一页</a>");
  		}
		for(i=pagenow-2; i < pagenow && i <= pagecount; i++){
			if(i>0){
				$("#page").append("<a onclick='loadBeauty(" + i + ",\"" + flag  + "\")' href='javascript:void'>" + i + "</a>.");
			}
		}
		$("#page").append("<a>" + pagenow + "</a>.");
 		for(i=pagenow+1; i <= pagenow+2 && i <= pagecount; i++){
 			$("#page").append("<a onclick='loadBeauty(" + i + ",\"" + flag  + "\")' href='javascript:void'>" + i + "</a>.");
		}
		if(pagenow!=pagecount){
			var next = pagenow +1;
			$("#page").append("<a onclick='loadBeauty(" + (pagenow+1) + ",\"" + flag  + "\")' href='javascript:void'>下一页</a>");
		}
		$("#page").append("总共" + pagecount + "页，" + "总共" + result.obj.rowcount + "条记录");
	}
	else{
		$("#page").append("查无记录！");
	}
	//，转到<input id='page_index' type='text' />页<input onclick='loadBeauty(" + $("#page_index").val() + ",\"" + flag  + "\")' type='submit' value='go'/>
	
}