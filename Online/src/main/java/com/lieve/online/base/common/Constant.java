package com.lieve.online.base.common;

public class Constant {

    public static int OrderDirectFlag_Asc = 1;

    public static int OrderDirectFlag_Desc = 0;

    public static int VideoFormat_flv = 1;

    public static int VideoFormat_mp4 = 2;

    public static String COOKIE_LANGUAGEFLAG = "clientLanguageflag";

    private static String WebRootPath = "";

    public static String getWebRootPath() {
        return WebRootPath;
    }

    public static void setWebRootPath(String webRootPath) {
        WebRootPath = webRootPath;
    }

    private static String RootPath = null;

    public static String getRootPath() {
        return RootPath;
    }

    public static void setRootPath(String rootPath) {
        RootPath = rootPath;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

/*    public static final String getSystemName() {
        // systemname
        return ReadeSystemConfig.getsystem("systemname");
    }
    
    public static final String getDomain() {
        return ReadeSystemConfig.getsystem("domin");
    }*/
    

}
