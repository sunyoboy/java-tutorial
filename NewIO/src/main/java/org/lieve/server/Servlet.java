package org.lieve.server;

import org.lieve.server.exception.ServletException;

import java.io.IOException;

/**
 * inteface Servlet Servlet 3.1
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/11/17
 * Time: 11:24 AM
 */
public interface Servlet {

    public void init(ServletConfig config) throws ServletException;

    public ServletConfig getServletConfig();

    public void service(ServletRequest req, ServletResponse resp)
            throws ServletException, IOException;

    public String getServletInfo();

    public void destory();
}
