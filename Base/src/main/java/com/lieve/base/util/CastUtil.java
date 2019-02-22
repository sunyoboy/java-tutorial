package com.lieve.base.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 转型操作工具类
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 10:04 AM
 */
public final class CastUtil {

    /**
     * 转型为String类型
     * @param obj
     * @return
     */
    public static String castString(Object obj) {
        return castString(obj, "");
    }

    /**
     *转型为String类型(提供默认值)
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }


    public static int castInt(Object obj) {
        return castInt(obj, 0);
    }

    /**
     * 转为int型(提供默认值)
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

}
