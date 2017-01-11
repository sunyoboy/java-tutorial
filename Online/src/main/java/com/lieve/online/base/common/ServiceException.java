package com.lieve.online.base.common;

public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }

    /**
     * @param message
     *            the detail message
     * @param cause
     *            {@link #getCause()} method
     * 
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     *            the detail message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * @param cause
     *            cause the cause (which is saved for later retrieval by the
     *            {@link #getCause()} method). (A <tt>null</tt> value is
     *            permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

}
