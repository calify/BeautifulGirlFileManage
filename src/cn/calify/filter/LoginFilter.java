package cn.calify.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//取得requset response session
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		
		String path = servletRequest.getRequestURI();
		String isPass= (String) session.getAttribute("pass");
		
		if(path.indexOf("/login.jsp") > -1){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		if(isPass == null || !isPass.equals("ok")){
			servletResponse.sendRedirect("/BeatifulGirlFileManage/admin/login.jsp");
		}
		else{
			chain.doFilter(request, response);
		}
		
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
