package com.lieve.online.common;


import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * import com.lieve.online.base.dao.DaoException;
 * import com.lieve.online.base.dao.Messages;
 */

/**
 * @author zhenjw
 */
public class LogicTools {

    public static String getConnString(int paramNum) {
        String result = " where ";

        if (paramNum > 0)

            result = " and ";

        return result;

    }

    public static long getLongFromObject(Object o) {
        return o == null ? 0 : Long.parseLong(o.toString());
    }

    public static Date getDateFromObject(Object o) {

        Date result = null;

        if (o != null) {
            if (o instanceof java.sql.Date) {
                result = new Date(((java.sql.Date) o).getTime());
            } else if (o instanceof java.sql.Timestamp) {
                result = new Date(((java.sql.Timestamp) o).getTime());
            }

        }
        return result;// o == null ? null : new Date(((java.sql.Date)
        // o).getTime());
    }

    public static int getIntFromObject(Object o) {
        return o == null ? 0 : Integer.parseInt(o.toString());
    }

    public static String getStringFromObject(Object o) {
        return o == null ? null : o.toString();
    }

    /**
     * 功能：得到格式化后的日期
     *
     * @param date
     * @return 经过格式化后的
     * @auth zhenjw
     * @date 2008-12-4 下午03:28:19
     */
    public static String getStringDate(Date date) {
        String format = "yyyy-MM-dd HH:mm:ss";

        return getStringDate(date, format);

    }

    /**
     * 功能：根据传入的format参数指定的格式，格式化数据。
     *
     * @param date
     * @param format
     * @return
     * @auth zhenjw
     * @date 2008-12-4 下午03:42:59
     */
    public static String getStringDate(Date date, String format) {
        String result = "";

        try {

            SimpleDateFormat DATE_FORMATE_SHOW = new SimpleDateFormat(format);

            if (date != null)

                result = DATE_FORMATE_SHOW.format(date);

        } catch (Exception e) {

            e.printStackTrace();

            result = null;

        }

        return result;
    }

    /**
     * 功能：开始时间的字符串 格式：yyyy-MM-dd 00:00:00
     *
     * @param beginDate
     * @return
     * @auth zhenjw
     * @date 2008-12-18 下午03:06:39
     */
    public static String getBeginDate(Date beginDate) {
        return getStringDate(beginDate, "yyyy-MM-dd 00:00:00");
    }

    /**
     * 功能：得到结束时间的字符串 格式：yyyy-MM-dd 23:59:59
     *
     * @param endDate
     * @return
     * @auth zhenjw
     * @date 2008-12-18 下午03:07:17
     */
    public static String getEndDate(Date endDate) {
        return getStringDate(endDate, "yyyy-MM-dd 23:59:59");

    }

    /**
     * 功能：得到开始时间，设置时分秒都为0，主要用于进行查询时对时间段进行判断.
     *
     * @param beginDate
     * @return
     * @date 2009-4-15 下午01:52:14
     * @author zhenjw
     */
    public static void setBegindate(Date beginDate) {
        beginDate.setHours(0);
        beginDate.setMinutes(0);
        beginDate.setSeconds(0);

    }

    /**
     * 功能：得到结束时间，设置时分秒为23:59:59主要用于进行查询时对时间段进行判断。
     *
     * @param endDate
     * @return
     * @date 2009-4-15 下午01:53:02
     * @author zhenjw
     */
    public static void setEnddate(Date endDate) {
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
    }

    /*
     * public static String getQueryRecordCountString(String queryString) {
     * int form_index = queryString.indexOf("from ");
     * int orderby_index = queryString.indexOf(" order by ");
     * if (form_index < 0) {
     * throw new
     * DaoException(Messages.getString("BaseDao.getTotalCount.notFromkeyword"));
     * }
     * String strsql = " select count(*) ";
     * if (orderby_index > -1) {
     * strsql = strsql + queryString.substring(form_index, orderby_index);
     * } else {
     * strsql = strsql + queryString.substring(form_index);
     * }
     * return strsql;
     * }
     */

    /**
     * 功能：用于在逻辑中判断字符串是不是为空
     *
     * @param str
     * @return
     * @author zhenjw
     */
    public static boolean isNull(String str) {
        return str == null || "".equals(str.trim()) ? true : false;
    }

    public static String formatStringDelComma(String str) {
        String result = "";
        if (str != null) {
            str = str.replaceAll(",,", ",");

            if (str.startsWith(",")) {
                str = str.substring(1);
            }
            if (str.endsWith(",")) {
                str = str.substring(0, str.length() - 1);
            }
            result = str;
        }
        return result;
    }

    public static void main(String[] args) {
        String str = ",22,42,82,83,102,";

        // System.out.println(formatStringDelComma(str));

        str = "A1_del(23)";
        String delFlag = "_del(";

        System.out.println(getShowString(str, delFlag));
    }

    public static String arrayToString(long[] id) {
        StringBuilder ID = new StringBuilder();

        if (id != null) {
            for (long i : id) {
                ID.append(i).append(",");
            }

            ID.deleteCharAt(ID.length() - 1);

        }

        return ID.toString();

    }

    public static boolean StringEqual(String str1, String str2) {
        return (LogicTools.isNull(str1) && LogicTools.isNull(str2))
                || (str1 != null && str1.equals(str2));
    }

    public static String getShowString(String str, String delFlag) {
        if (isNull(str)) {
            str = "";
        } else {
            int index = str.indexOf(delFlag);
            if (index > 0) {
                str = str.substring(0, index);
            }
        }

        return str;
    }
}
