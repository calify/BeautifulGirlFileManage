package cn.calify.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetEncodingServlet implements Filter {
	private String encoding;
	public CharsetEncodingServlet(){}
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException, ServletException{
		request.setCharacterEncoding(encoding);//设置请求的编码
		response.setContentType("text/htm;charset="+encoding);
		chain.doFilter(request,response);
	}
	public void init(FilterConfig fConfig) throws ServletException{
		this.encoding = fConfig.getInitParameter("encoding");
	}
	public void destroy(){
		
	}
}
