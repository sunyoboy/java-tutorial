package org.lieve.server;

import org.lieve.server.exception.ServletException;

import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/11/17
 * Time: 11:25 AM
 */
public class GenericServlet implements ServletConfig, Servlet {

    private ServletConfig config;

    @Override
    public void destory() {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        this.init();
    }

    public void init() {

    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public String getServletName() {
        return null;
    }

    public ServletContext getServletContext() {
        ServletConfig sc = getServletConfig();
        if(sc == null) {
            throw new IllegalStateException("err.servlet_config_not_initialized");
        }
        return sc.getServletContext();
    }

    @Override
    public String getInitParameter(String name) {
        return null;
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return null;
    }

    //
    public void log(String message) {
        // getServletContext().log(getServletName() + ": " + message);
    }

    public void log(String message, Throwable t) {
        // getServletContext().log(getServletName() + ": " + message, t);
    }
}
