package cn.calify.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.calify.beans.Beauty;
import cn.calify.beans.TemplateJson;
import cn.calify.dao.factory.BeautyDAOImpFactory;
import cn.calify.services.factory.UserServicesImpFactor;
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
		if(action.equals("query")){
			if(role.equals("beauty")){
				JSONObject jsondata = null;
				try {
					jsondata = JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request));
				} catch (Exception e) {
					e.printStackTrace();
				}
				Beauty beauty = (Beauty)BeautyDAOImpFactory.generaterBeautyDAOImp().doQueryByName((String)jsondata.get("name"));
				returnjson.setObj(beauty);
				returnjson.setResult("success");
			}
			else if(role.equals("user")){
				
			}
		}
		else if(action.equals("add")){
			if(role.equals("beauty")){
				JSONObject jsondata = null;
				Beauty beauty = null;
				try {
					beauty =(Beauty) JSONObject.toBean(JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request)),Beauty.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(BeautyDAOImpFactory.generaterBeautyDAOImp().doAddByBean(beauty)){
					returnjson.setResult("success");
				}
			}
			else if(role.equals("user")){
				
			}
		}
		else if(action.equals("del")){
			if(role.equals("beauty")){
				JSONObject jsondata = null;
				try {
					jsondata = JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(BeautyDAOImpFactory.generaterBeautyDAOImp().doDelById(jsondata.getInt("id"))){
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
				if(UserServicesImpFactor.generaterUserServicesImp().doDelById(jsondata.getInt("id"))){
					returnjson.setResult("success");
				}
			}
		}
		else if(action.equals("update")){
			if(role.equals("beauty")){
				JSONObject jsondata = null;
				Beauty beauty = null;
				try {
					beauty =(Beauty) JSONObject.toBean(JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request)),Beauty.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(BeautyDAOImpFactory.generaterBeautyDAOImp().doUpdata(beauty)){
					returnjson.setResult("success");
				}
			}
			else if(role.equals("user")){
				
			}			
		}
		response.getWriter().write(JSONObject.fromObject(returnjson).toString());
	}

}
