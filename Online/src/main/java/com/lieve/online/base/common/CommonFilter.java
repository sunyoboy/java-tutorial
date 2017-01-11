package com.lieve.online.base.common;

/**
 * @author zhen
 *	??????--?????ó???????UTF-8
 */
import javax.servlet.*;
import java.io.IOException;

public class CommonFilter implements Filter {

    private FilterConfig filterConfig;

    private String defaultEncoding = null;

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (defaultEncoding == null) {
		    defaultEncoding = "UTF-8";
		}
		request.setCharacterEncoding(this.defaultEncoding);
		response.setCharacterEncoding(this.defaultEncoding);
		response.setContentType("text/html;charset="+this.defaultEncoding);
		chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
		this.defaultEncoding = filterConfig.getInitParameter("encoding");
    }

}
