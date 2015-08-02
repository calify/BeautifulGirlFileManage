/**
 * 封装增删改查的方法
 */

/**
 * 利用ajax进行查询
 * 参数：请求查询的url，请求查询的名字 
 */

function query(url,jsondata){
            result = postAjax(url,jsondata);
            if(result.result == "success"){
                $("#container").children().hide();
                $("ul").empty();
                $("ul").show();
            }
}

/**
 * 利用ajax进行删除
 * 参数：请求删除的url，请求删除的id
 */

function del(url,id){
			cf = window.confirm("确定要删除吗？");
			var jsondata = {};
			jsondata.id = id
			if(cf){
				var result = postAjax(url,jsondata);
			}
			if(result.result == "success"){
				alert("删除成功！");
				history.go(0);
			}
			else{
				alert("删除失败！");
				history.go(0);
			}
}

/**
 * 利用ajax进行增加
 * 参数：请求删除的url，请求删除的json数据
 */

function add(url,json){
			//ajax请求
			var result = postAjax(url,json);
			if(result.result == "success"){
				alert("添加成功！");
				history.go(0);
			}
			else{
				alert("添加失败！");
				history.go(0);
			}
}



function update(url,json){
			//ajax请求
			var result = postAjax(url,json);
			if(result.result == "success"){
				alert("修改成功！");
				history.go(0);
			}
			else{
				alert("修改失败！");
				history.go(0);
			}
}