package cn.calify.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.calify.beans.Beauty;
import cn.calify.beans.Bpic;
import cn.calify.beans.Page;
import cn.calify.beans.TemplateJson;
import cn.calify.beans.User;
import cn.calify.services.factory.BeautyServicesImpFactory;
import cn.calify.services.factory.BpicServicesImpFactory;
import cn.calify.services.factory.UserServicesImpFactory;
import cn.calify.util.GetJsonStringFromRequest;

public class DataAction extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String role = request.getParameter("role");
		TemplateJson returnjson = new TemplateJson();
		returnjson.setResult("fail");
	
		//查找
		if(action.equals("query")){
			if(role.equals("bpic")){
				JSONObject jsondata = null;
				try {
					jsondata = JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request));
				} catch (Exception e) {
					e.printStackTrace();
				}
				List<Bpic> list = BpicServicesImpFactory.generaterBpicServicesImp().doQueryBybid(jsondata.getInt("id"));
				if(list.size() > 0){
					returnjson.setReturnlist(list);
					returnjson.setResult("success");					
				}
			}
		}
		
		//添加
		else if(action.equals("add")){
			if(role.equals("beauty")){
				Beauty beauty = null;
				try {
					beauty =(Beauty) JSONObject.toBean(JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request)),Beauty.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(BeautyServicesImpFactory.generaterBeautyServicesImp().doAddByBean(beauty)){
					returnjson.setResult("success");
				}
			}
			else if(role.equals("user")){
				User user = null;
				try {
					user =(User) JSONObject.toBean(JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request)),User.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(UserServicesImpFactory.generaterUserServicesImp().doAddByBean(user)){
					returnjson.setResult("success");
				}
			}
			else if(role.equals("bpic")){
				Bpic bpic = null;
				try {
					bpic =(Bpic) JSONObject.toBean(JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request)),Bpic.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(BpicServicesImpFactory.generaterBpicServicesImp().doAddByBean(bpic)){
					returnjson.setResult("success");
				}
			}
		}
		
		//删除
		else if(action.equals("del")){
			if(role.equals("beauty")){
				JSONObject jsondata = null;
				try {
					jsondata = JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(BeautyServicesImpFactory.generaterBeautyServicesImp().doDelById(jsondata.getInt("id"))){
					returnjson.setResult("success");
				}				
			}
			else if(role.equals("user")){
				JSONObject jsondata = null;
				try {
					jsondata = JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(UserServicesImpFactory.generaterUserServicesImp().doDelById(jsondata.getInt("id"))){
					returnjson.setResult("success");
				}
			}
			else if(role.equals("bpic")){
				JSONObject jsondata = null;
				try {
					jsondata = JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(BpicServicesImpFactory.generaterBpicServicesImp().doDelById(jsondata.getInt("id"))){
					returnjson.setResult("success");
				}
			}
		}
		
		
		//修改
		else if(action.equals("update")){
			if(role.equals("beauty")){
				JSONObject jsondata = null;
				Beauty beauty = null;
				try {
					beauty =(Beauty) JSONObject.toBean(JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request)),Beauty.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(BeautyServicesImpFactory.generaterBeautyServicesImp().doUpdata(beauty)){
					returnjson.setResult("success");
				}
			}
			else if(role.equals("user")){
				JSONObject jsondata = null;
				User user = null;
				try {
					user =(User) JSONObject.toBean(JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request)),User.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(UserServicesImpFactory.generaterUserServicesImp().doUpdata(user)){
					returnjson.setResult("success");
				}
			}			
		}
		else if(action.equals("support")){
			JSONObject jsondata = null;
			try {
				jsondata = JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(BeautyServicesImpFactory.generaterBeautyServicesImp().doSupport(jsondata.getInt("support"), jsondata.getInt("id"))){
				returnjson.setResult("success");
			}
			
		}
		else if(action.equals("against")){
			JSONObject jsondata = null;
			try {
				jsondata = JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(BeautyServicesImpFactory.generaterBeautyServicesImp().doAgainst(jsondata.getInt("against"), jsondata.getInt("id"))){
				returnjson.setResult("success");
			}
			
		}
		//组合查询
		else if(action.equals("showByBean")){
			JSONObject jsondata = null;
			Beauty beauty = null;
			Page page = null;
			try {
				jsondata = JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request));
				beauty = (Beauty)JSONObject.toBean(JSONObject.fromObject(jsondata.getJSONObject("beauty")),Beauty.class);
				page = (Page)JSONObject.toBean(JSONObject.fromObject(jsondata.getJSONObject("page")),Page.class);
				returnjson = BeautyServicesImpFactory.generaterBeautyServicesImp().doQueryByBean(beauty, page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		response.getWriter().write(JSONObject.fromObject(returnjson).toString());
	}

}
