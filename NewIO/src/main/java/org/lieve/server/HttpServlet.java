package org.lieve.server;

import com.sun.deploy.net.HttpRequest;
import com.sun.tools.internal.jxc.apt.Const;
import org.lieve.nio.common.Constant;
import org.lieve.server.exception.ServletException;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/11/17
 * Time: 11:26 AM
 */
public class HttpServlet extends GenericServlet {
    @Override
    public void destory() {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;

        // 如果请求类型不相符,则抛出异常
        if(!(request instanceof HttpServletRequest &&
                response instanceof HttpServletResponse)) {
            throw new ServletException("non-HTTP request or response");
        }

        // 转换request和response的类型
        request = (HttpServletRequest) req;
        response = (HttpServletResponse) resp;

        // 调用http的处理方法
        service(request, response);
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // get request type
        String method = req.getMethod();

        // 将不同的请求类型路由到不同的处理方法
        if (method.equals(Constant.METHOD_GET)) {
            long lastModified = getLastModified(req);
            if (lastModified == -1) {
                // doGet(req, resp);
            } else {
                long ifModifiedSince = (long)0.0; // req.getDateHeader(HEADER_IFMODSINCE);
                if(ifModifiedSince < lastModified) {
                    // maybeSetLastModified(resp, lastModified);
                    // doGet(req, resp);
                } else {
                    // resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                }
            }

        } else if (method.equals(Constant.METHOD_HEAD)) {
            long lastModified = getLastModified(req);
            maybeSetLastModified(resp, lastModified);
            doHead(req, resp);
        } else if (method.equals(Constant.METHOD_POST)) {
            doPost(req, resp);
        } else if (method.equals(Constant.METHOD_PUT)) {
            doPut(req, resp);
        } else if (method.equals(Constant.METHOD_DELETE)) {
            doDelete(req, resp);
        } else if (method.equals(Constant.METHOD_OPTIONS)) {
            doOptions(req, resp);
        } else if (method.equals(Constant.METHOD_TRACE)) {
            doTrace(req, resp);
        } else {
            String errMsg = "http.method_not_implemented";
            Object[] errArgs = new Object[1];
            errArgs[0] = method;
            errMsg = MessageFormat.format(errMsg, errArgs);
            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, errMsg);
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String protocol = req.getProtocol();
        String message = "http.method_get_not_supported";
        if (protocol.endsWith("1.1")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, message);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
        }
    }
}
