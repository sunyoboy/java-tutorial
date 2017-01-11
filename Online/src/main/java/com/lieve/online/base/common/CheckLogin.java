package com.lieve.online.base.common;

public class CheckLogin {

    public static String SESSION_USER_KEY = "Session_User";// "sesion_curuser" ;
/*
    public static void saveToSession(UserInfo userInfo) {
        saveToSession(getHttpSession(), userInfo);
    }

    public static void saveToSession(HttpSession session, UserInfo userInfo) {
        session.setAttribute(SESSION_USER_KEY, userInfo);
    }

    *//**
     * 功能：检查用户是否登录,当用户登录超时或在其它地方登录时抛出ActionException异常
     * 
     * @param request
     * @return
     *//*
    public static UserInfo check(HttpServletRequest request) {

        HttpSession session = request.getSession();

        return checkLoginUser(session);

    }

    public static UserInfo checkLoginUser(HttpSession session) {
        UserInfo userInfo = null;
        userInfo = (UserInfo) session.getAttribute(SESSION_USER_KEY);
        if (session == null
                || (userInfo = (UserInfo) session
                        .getAttribute(SESSION_USER_KEY)) == null) {

            throw ActionException.LoginOutException;
        }
        return userInfo;
    }

    *//**
     * 功能：得到当前用户的权利
     * 
     * @param session
     * @return
     * @author zhenjw
     *//*
    public static String getUserRight(HttpSession session) {

        String result = "";

        if (session != null) {

            UserInfo userInfo = checkLoginUser(session);

            if (userInfo != null) {
                result = userInfo.getRoleRight();
            }
        }
        return result;
    }

    public static UserInfo checkLoginUser() {

        WebContext webContext = WebContextFactory.get();

        return checkLoginUser(webContext.getSession());

    }

    public static HttpSession getHttpSession() {
        WebContext webContext = WebContextFactory.get();
        return webContext.getSession();
    }

    public static HttpServletResponse getResponse() {
        WebContext webContext = WebContextFactory.get();
        return webContext.getHttpServletResponse();
    }

    public static UserInfo getCurrentLoginUser(HttpSession session) {
        return (UserInfo) session.getAttribute(SESSION_USER_KEY);
    }

    public static UserInfo getCurrentLoginUser() {
        return (UserInfo) getHttpSession().getAttribute(SESSION_USER_KEY);
    }*/

}
