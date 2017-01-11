package com.lieve.online.base.common;

/**
 * 
 * @ClassName: ActionException
 * @Description: TODO(针对此项目的可能出现的运行时异常的定义)
 * @author sunlj
 * @date 2015-10-26 下午3:58:18
 * 
 */
public class ActionException extends RuntimeException {

    public static ActionException LoginOutException = new ActionException(
            Messages.getString("user.loginout"), "loginout");

    private String name = "sjddException";

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public ActionException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public ActionException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public ActionException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param message
     * @param name
     */
    public ActionException(String message, String name) {
        super(message);
        this.name = name;
    }

    /**
     * @param cause
     */
    public ActionException(Throwable cause) {
        super(cause);
    }

}
