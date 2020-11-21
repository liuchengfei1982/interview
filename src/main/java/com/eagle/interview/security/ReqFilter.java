package com.eagle.interview.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ReqFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		ReqHttpServletRequestWrapper reqRequest = new ReqHttpServletRequestWrapper(
				(HttpServletRequest) request);
		chain.doFilter(reqRequest, response);
	}

	@Override
	public void destroy() {

	}
}
