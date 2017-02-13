package org.lieve.server.exception;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/11/17
 * Time: 11:29 AM
 */
public class ServletException extends Exception {

    public ServletException() {
        super();
    }

    public ServletException(String message) {
        super(message);
    }

    public ServletException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
