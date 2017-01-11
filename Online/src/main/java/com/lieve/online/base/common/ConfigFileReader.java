package com.lieve.online.base.common;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// 即时读取配置文件的某一项的值
public class ConfigFileReader {

    private static java.util.Properties pro = new java.util.Properties();

    private static String proValue = "";

    /*
     * 方法：getProperty(String FileName,String propKey)
     * 参数：String FileNam　ini文件名,为绝对路径的文件名
     * String propKey 关键字
     * 返回值：String 字符串
     */
    public static String getProperty(String FileName, String propKey) {

        try {
            FileInputStream fs = new FileInputStream(FileName);
            try {
                pro.load(fs);
                proValue = "";
                proValue = pro.getProperty(propKey);// 得到参数的值
                if (proValue == null) {
                    proValue = "";
                }
                fs.close();// 关闭文件
                pro.clear();// 清空
                pro = null;

            } catch (IOException e) {
                // 加载文件失败
                System.out.println(e + "file " + FileName + " not found");
            }

        } catch (FileNotFoundException e) {
            // 读取文件失败
            System.out.println(e + "file " + FileName + " not found");
        }
        return proValue;
    }

    /*
     * 方法：getProperty(String FileName,String propKey,String defaultValue)
     * 参数：String FileNam　 ini文件名
     * String propKey 关键字
     * String defaultValue 关键字的默认值
     * 返回值： String 字符串
     */
    public static String getProperty(String FileName, String propKey,
                                     String defaultValue) {

        try {
            FileInputStream fs = new FileInputStream(FileName);
            try {
                pro.load(fs);
                proValue = pro.getProperty(propKey, defaultValue);// 得到某项的值，可以设置默认值
                if (proValue == null) {
                    proValue = "";
                }
                fs.close();// 关闭文件
                pro.clear();// 清空
                pro = null;

            } catch (IOException e) {
                // 加载文件失败
                System.out.println(e + "file " + FileName + " not found");
            }
        } catch (FileNotFoundException e) {
            // 读取文件失败
            System.out.println(e + "file " + FileName + " not found");
        }
        return proValue;
    }

    public static String getPropertyFromBuffer(String FileName, String propKey) {

        try {
            FileBuffer fileBuffer = FileBuffer.getInstance();
            proValue = fileBuffer.getProperty(FileName, propKey);

        } catch (Exception e) {
            // 读取文件失败
            System.out.println(e + "file " + FileName + " not found");
        }
        return proValue;
    }

    /**
     * 从Buffer中获取配置的键值: <br>
     * 〈功能详细描述〉
     *
     * @param FileName     　 ini文件名
     * @param propKey      关键字
     * @param defaultValue 关键字的默认值
     * @return proValue 配置参数值
     * @since 1.0
     */
    public static String getPropertyFromBuffer(String FileName, String propKey,
                                               String defaultValue) {

        try {
            FileBuffer fileBuffer = FileBuffer.getInstance();
            proValue = fileBuffer.getProperty(FileName, propKey, defaultValue);

        } catch (Exception e) {
            // 读取文件失败
            System.out.println(e + "file " + FileName + " not found");
        }
        return proValue;
    }

}
