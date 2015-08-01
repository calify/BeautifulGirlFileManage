package cn.calify.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.calify.beans.Beauty;
import cn.calify.beans.TemplateJson;
import cn.calify.beans.User;
import cn.calify.services.factory.BeautyServicesImpFactory;
import cn.calify.services.factory.UserServicesImpFactor;
import cn.calify.services.imp.BeautyServicesImp;
import cn.calify.services.imp.UserServicesImp;
import cn.calify.util.GetJsonStringFromRequest;

public class Action extends HttpServlet {

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
		TemplateJson returnjson = new TemplateJson();
		returnjson.setResult("fail");
		
		if(action.equals("login")){
			UserServicesImp userservicesimp = UserServicesImpFactor.generaterUserServicesImp();
			User user = null;
			try {
				JSONObject loginjson = JSONObject.fromObject(GetJsonStringFromRequest.getJsonString(request));
				user = (User)JSONObject.toBean(loginjson,User.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(userservicesimp.doLogin(user)){
				//新建session
				HttpSession hs = request.getSession();
				hs.setAttribute("pass","ok");
				returnjson.setResult("success");
			}
		}
		
		else if(action.equals("showAll")){
			BeautyServicesImp beautyservicesimp = BeautyServicesImpFactory.generaterBeautyServicesImp();
			List<Beauty> list = beautyservicesimp.doQueryALL();
			if(list != null){
				returnjson.setReturnlist(list);
				returnjson.setResult("success");
			}
		}
		
		else if(action.equals("showDetail")){
			int id = Integer.parseInt(request.getParameter("id"));
			BeautyServicesImp beautyservicesimp = BeautyServicesImpFactory.generaterBeautyServicesImp();
			Beauty beauty = (Beauty) beautyservicesimp.doQueryById(id);
			if(beauty != null){
				returnjson.setObj(beauty);
				returnjson.setResult("success");
			}
		}
		else if(action.equals("logout")){
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("/BeatifulGirlFileManage/index.jsp");
		}

		response.getWriter().write(JSONObject.fromObject(returnjson).toString());
	}

}