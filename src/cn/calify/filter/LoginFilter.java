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
		String role= (String) session.getAttribute("role");
		
		String localpath = servletRequest.getContextPath();
		String basePath = servletRequest.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+localpath+"/";
		
		if(path.indexOf("/login.jsp") > -1){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		else if(path.indexOf("/userManager.jsp") > -1){
			if( role==null || !role.equals("admin")){
				servletResponse.sendRedirect("adminIndex.jsp");
				return;
			}
			else{
				chain.doFilter(servletRequest, servletResponse);
				return;
			}
		}
		
		if(role == null){
			servletResponse.sendRedirect(basePath+"admin/login.jsp");
		}
		else{
			chain.doFilter(request, response);
		}
		
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
