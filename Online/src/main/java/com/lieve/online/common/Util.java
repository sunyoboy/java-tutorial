package com.lieve.online.common;

/**
 * Created by DD240 on 2016/2/29.
 */
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {

    public static String getSessionKey() {
        int i;
        int nKey;
        char chKey;
        long lResult = 0;
        java.util.Random tt = new java.util.Random();
        String result = "";
        for (i = 0; i < 20; i++) {
            nKey = tt.nextInt();
            if (nKey < 0)
                nKey = -nKey;
            nKey %= 256;
            if (nKey < 128)
                nKey = nKey * 26 / 128 + 65;
            else
                nKey = (nKey - 128) * 10 / 128 + 48;
            chKey = (char) nKey;
            result += chKey;
        }

        return result;
    }

    // 产生随机字符串
    private static java.util.Random tt = new java.util.Random(
            (new java.util.Date()).getTime());

    public static long getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        long BeginYear = cal.get(Calendar.YEAR);
        return BeginYear;
    }

    public Util() {
    }

    /**
     * 把相应的字符串转换成长整型
     */
    public static long StringToLong(String str, long defaultvalue) { // in: 缺省的值
        long value = defaultvalue;
        try {
            value = Long.parseLong(str);
        } catch (NumberFormatException e) {
            // e.printStackTrace();
        }
        return value;
    }

    /**
     * 用于字符串去空格之类的
     *
     * @param str
     * @return
     */
    public static String StringToString(String str) {
        return StringToString(str, "");
    }

    public static String StringToString(String str, String defaultvalue) {
        if (str == null) {
            return defaultvalue;
        } else {
            return str.trim();
        }
    }

    /**
     * 把相应的字符串转换成整型
     */
    public static int StringToInt(String str, int defaultvalue) { // in: 缺省的值
        int value = defaultvalue;
        try {
            value = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // e.printStackTrace();
        }
        return value;
    }

    /**
     * 把相应的字符串转换成浮点数
     */
    public static float StringToFloat(String str, float defaultvalue) { // in:
        // 缺省的值
        float value = defaultvalue;
        try {
            value = Float.parseFloat(str);
        } catch (NumberFormatException e) {
            // e.printStackTrace();
        }
        return value;
    }

    /**
     * BytesToInt()将byte[256]数组中的4位转换为32bit的int。
     */
    public static int BytesToInt(byte in[], int s) {
        int i;
        int flag = 0;
        if (flag == 0) {
            i = (in[s] & 0x000000ff) | ((in[s + 1] << 8) & 0x0000ffff)
                    | ((in[s + 2] << 16) & 0x00ffffff)
                    | ((in[s + 3] << 24) & 0xffffffff);
        } else {
            i = (in[s + 3] & 0x000000ff) | ((in[s + 2] << 8) & 0x0000ffff)
                    | ((in[s + 1] << 16) & 0x00ffffff)
                    | ((in[s] << 24) & 0xffffffff);
        }
        return i;
    }

    /**
     * BytesToInt2
     */
    public static int BytesToInt2(byte in[], int s) {
        int i = (in[s] & 0x000000ff) << 8 | (in[s + 1] & 0x000000ff);
        return i;
    }

    /**
     * IntToBytes()将32bit的int转换为byte[256]数组中的4位。
     */
    public static void IntToBytes(byte in[], int l, int s) {
        /**
         * flag标志，当Int和Bytes相互转换时，byte[0]和byte[3]谁是高位，0时byte[3]为高,为小端系统。
         */
        int flag = 0;
        byte btmp;
        int i;
        if (l == -1) { // 如果是-1,则组合成0xffffffff
            for (i = 0; i < 4; i++) {
                in[s + i] = (byte) 0xff;
            }
            return;
        }
        if (flag == 0) {
            int tmp1;
            int tmp2;
            for (i = 3; i >= 0; i--) {
                tmp1 = l;

                btmp = (byte) ((tmp1 >> (i * 8)) & 0xff);
                tmp2 = btmp;

                l = l - ((tmp2 << (i * 8)));

                in[s + i] = (byte) (btmp & 0xff);
            }
        } else {
            for (i = 3; i >= 0; i--) {
                if (i != 0) {
                    btmp = (byte) (l / (256 << (i - 1) * 8));
                    l = l - btmp * (256 << (i - 1) * 8);
                } else {
                    btmp = (byte) (l / (256 >> 8));
                    l = l - btmp * (256 >> 8);
                }
                in[s + 3 - i] = (byte) (btmp & 0xff);
            }
        }
    }

    public static void IpToBytes(byte in[], String str, int s) {
        str = str + ".";
        int len = str.length(), index, start = 0;
        char ch = '.';
        for (int i = 0; i < 4; i++) {
            index = str.indexOf(ch, start);
            String tmp = str.substring(start, index);
            str = str.substring(index + 1, len);
            len = str.length();
            IntToBytes(in, Integer.parseInt(tmp) & 0x000000ff, s + i);
        }
    }

    public static String BytesToIp(byte in[], int s) {
        String ip = "";
        for (int j = 0; j < 4; j++) {
            byte tmp = in[s + j];
            ip = ip + (tmp >= 0 ? tmp : 256 + tmp) + ".";
        }
        ip = ip.substring(0, ip.length() - 1);
        return ip;
    }

    /**
     * 转换字符串IP地址为整数值
     */
    public static String InttoIP(int nip) {
        byte[] bb = new byte[4];
        IntToBytes(bb, nip, 0);
        String sip = BytesToIp(bb, 0);
        return sip;
    }

    public static String InttoIP2(int nip) {
        int tmp = (nip >> 3 * 8) & 0xff;
        String sip = "" + tmp;
        tmp = (nip >> 2 * 8) & 0xff;
        sip = sip + "." + tmp;
        tmp = (nip >> 1 * 8) & 0xff;
        sip = sip + "." + tmp;
        tmp = (nip >> 0 * 8) & 0xff;
        sip = sip + "." + tmp;
        return sip;
    }

    public static int IPtoInt(String sip) { // 192.168.11.2组合为2*0xffffff+11*0xffff+192*0xff+168
        // 这是network order
        try {
            int ip = 0;
            for (int i = 0; i < 4; i++) {
                int end = sip.indexOf(".");
                int addr = 0;
                if (end == -1) {
                    addr = Integer.parseInt(sip.substring(0));
                } else {
                    addr = Integer.parseInt(sip.substring(0, end));
                    sip = sip.substring(end + 1, sip.length());
                }
                // addr = (addr&0x000000ff) << ((3-i)*8);
                addr = (addr & 0x000000ff) << (i * 8);
                ip = ip + addr;
            }
            return ip;
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return -1;
    }

    public static long IPtoLong2(String sip) { // 192.168.11.2组合为192*0xffffff+168*0xffff+11*0xff+2
        try {
            long ip = 0;
            for (int i = 0; i < 4; i++) {
                int end = sip.indexOf(".");
                long addr = 0;
                if (end == -1) {
                    addr = Integer.parseInt(sip.substring(0));
                } else {
                    addr = Integer.parseInt(sip.substring(0, end));
                    sip = sip.substring(end + 1, sip.length());
                }
                // addr = (addr&0x000000ff) << ((3-i)*8);
                addr = (addr & 0x000000ff) << ((3 - i) * 8);
                ip = ip + addr;
            }
            return ip;
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return -1;
    }

    public static int IPtoInt2(String sip) { // 192.168.11.2组合为192*0xffffff+168*0xffff+11*0xff+2
        try {
            int ip = 0;
            for (int i = 0; i < 4; i++) {
                int end = sip.indexOf(".");
                int addr = 0;
                if (end == -1) {
                    addr = Integer.parseInt(sip.substring(0));
                } else {
                    addr = Integer.parseInt(sip.substring(0, end));
                    sip = sip.substring(end + 1, sip.length());
                }
                // addr = (addr&0x000000ff) << ((3-i)*8);
                addr = (addr & 0x000000ff) << ((3 - i) * 8);
                ip = ip + addr;
            }
            return ip;
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return -1;
    }

    /*
     * 把一个16进制的数转化成int
     */
    public static int HexStringToInt(String str, int defaultValue) {
        try {
            int result = Integer.decode(str).intValue();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /*
     * 把一个int的数转化成2**int次幂
     */
    public static long IntToPower(int value) {
        if (value <= 0) {
            return 1;
        }
        return (1 << value);
    }

    /* 返回记录在数据库中的数据 */
    public static int FormDate(int year, int month, int day) {
        return (day + month * 100 + year * 10000);
    }

    public static int FormTime(int hour, int min, int sec) {
        return (sec + min * 100 + hour * 10000);
    }

    /*****************************************************************
     * 功能：显示日期格式
     *****************************************************************/
    public static String formatDate(int date) {
        int day = date % 100;
        date = date / 100;
        int month = date % 100;
        date = date / 100;
        int year = date;
        String t2 = "", t3 = "";
        if (month < 10) {
            t2 = "0";
        }
        if (day < 10) {
            t3 = "0";
        }
        return year + "-" + t2 + month + "-" + t3 + day;
    }

    /*****************************************************************
     * 功能：显示日期格式
     *****************************************************************/
    public static String formatDate2(int date) {
        int day = date % 100;
        date = date / 100;
        int month = date % 100;
        date = date / 100;
        int year = date;
        String t2 = "", t3 = "";
        if (month < 10) {
            t2 = "0";
        }
        if (day < 10) {
            t3 = "0";
        }
        return month + "月" + day + "日";
    }

    /*****************************************************************
     * 功能：显示日期格式
     *****************************************************************/
    /**
     *
     * @param date
     * @return XXXX.XX.XX
     */
    public static String formatDate3(int date) {
        int day = date % 100;
        date = date / 100;
        int month = date % 100;
        date = date / 100;
        int year = date;
        String t2 = "", t3 = "";
        if (month < 10) {
            t2 = "0";
        }
        if (day < 10) {
            t3 = "0";
        }
        return year + "." + t2 + month + "." + t3 + day;
    }

    /*****************************************************************
     * 功能：显示日期格式
     *****************************************************************/
    /**
     *
     * @param date
     * @return XXXX年X月X日
     */
    public static String formatDate4(int date) {
        int day = date % 100;
        date = date / 100;
        int month = date % 100;
        date = date / 100;
        int year = date;
        return year + "年" + month + "月" + day + "日";
    }

    /*****************************************************************
     * 功能：显示时间格式
     *****************************************************************/
    public static String formatTime(int time) {
        int second = time % 100;
        time = time / 100;
        int minute = time % 100;
        time = time / 100;
        int hour = time;
        String t1 = "", t2 = "", t3 = "";
        if (hour < 10) {
            t1 = "0";
        }
        if (minute < 10) {
            t2 = "0";
        }
        if (second < 10) {
            t3 = "0";
        }
        return t1 + hour + ":" + t2 + minute; // +":"+t3+second;
    }

    public static String formatTime2(int time) {
        int second = time % 100;
        time = time / 100;
        int minute = time % 100;
        time = time / 100;
        int hour = time;
        String t1 = "", t2 = "", t3 = "";
        if (hour < 10) {
            t1 = "0";
        }
        if (minute < 10) {
            t2 = "0";
        }
        if (second < 10) {
            t3 = "0";
        }
        return t1 + hour + "点" + t2 + minute + "分"; // +":"+t3+second;
    }

    /* 处理显示的字符串，如果过长则用右键停留的方法显示 */
    public static String DisplayString(String in, int size) {
        if (in == null) {
            return "";
        }
        if (in == null || in.length() <= size) {
            return in;
        }
        return "<span title='" + in + "'><a href='#'>" + in.substring(0, size)
                + "...</a></span>";
    }

    public static String DisplayString2(String in, int size) {
        if (in == null) {
            return "";
        }
        if (in == null || in.length() <= size) {
            return in;
        }
        return " " + in.substring(0, size) + "... ";
    }

    /* 处理日程日期的处理 */
    public static boolean isLeapYear(int nYear) {
        if (((nYear % 4) == 0) && ((nYear % 100) != 0) || ((nYear % 400) == 0)) {
            return true;
        } else {
            return false;
        }

    }

    public static int getDaysInMonth(int nMonth, int nYear) {
        int days = 0;
        if (nMonth == 1 || nMonth == 3 || nMonth == 5 || nMonth == 7
                || nMonth == 8 || nMonth == 10 || nMonth == 12) {
            days = 31;
        } else if (nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11) {
            days = 30;
        } else if (nMonth == 2) {
            if (isLeapYear(nYear)) {
                days = 29;
            } else {
                days = 28;
            }
        }
        return days;
    }

    // added by Vega 2002-12-15
    public static String showMultiText(String input) {
        String result = input;
        int i;
        if (result == null) {
            result = "";
        }
        while ((i = result.indexOf('\n')) > -1) {
            result = result.substring(0, i - 1) + "<br>"
                    + result.substring(i + 1);
        }
        return result;
    }

    /*
     * 显示错误信息，并跳转到指定页面
     * 返回值：无
     */
    public static void ErrorBack(String lpErrorMsg, // in：错误信息，NULL时候则显示nErrorCode对应的内定错误信息
                                 String lpNextURL, // in：显示完信息后要去的URL地址，NULL表示回退前一历史页面
                                 int nBackDegree, // in：当lpNextURL=null时有效，表示回退前到第几历史页面
                                 String lpFrameName, // in：打开lpNextURL的frame页面名称，NULL表示当前frame页面
                                 javax.servlet.jsp.JspWriter myout) throws java. // 输出的句柄
            io.IOException {
        myout.println("<html><head><title>提示信息</title></head>\n");
        myout.print("<body onload=\"alert('");
        if (lpErrorMsg != null) {
            myout.print(lpErrorMsg);
        } else {
            myout.print("未知错误");
        }
        myout.print("');");
        if (lpNextURL == null) {
            if (nBackDegree > 1) {
                myout.print("history.back(" + (-nBackDegree) + ");");
            } else {
                myout.print("history.back();");
            }
        } else {
            myout.print("window.open('" + lpNextURL + "','"
                    + ((lpFrameName != null) ? lpFrameName : "_self") + "');");
        }
        myout.println("\">");
        myout.println("</body></html>\n");
    }

    /*
     * 显示错误信息，并跳转到指定页面
     * 返回值：无
     */
    public static void ErrorClose(String lpErrorMsg, // in：错误信息，NULL时候则显示nErrorCode对应的内定错误信息
                                  javax.servlet.jsp.JspWriter myout) throws java. // 输出的句柄
            io.IOException {
        myout.println("<html><head><title>提示信息</title></head>\n");
        myout.print("<body onload=\"alert('");
        if (lpErrorMsg != null) {
            myout.print(lpErrorMsg);
        } else {
            myout.print("未知错误");
        }
        myout.print("');");
        myout.print("window.close();");

        myout.println("\">");
        myout.println("</body></html>\n");
    }

    public static void ErrorAlert(String lpErrorMsg, // in：错误信息
                                  javax.servlet.jsp.JspWriter myout) throws java. // 输出的句柄
            io.IOException {
        myout.println("<html><head><title>提示信息</title></head>\n");
        myout.print("<body onload=\"alert('");
        if (lpErrorMsg != null) {
            myout.print(lpErrorMsg);
        } else {
            myout.print("未知错误");
        }
        myout.print("');\">");
        myout.println("</body></html>\n");
    }

    /**
     * 把相应的长整型转换成格式字符串
     */
    public static String formatLong(long str, // 原始整型
                                    int bit) { // in: 显示位数

        long value = 1;
        String pre = "";
        int ii = 0;
        for (ii = 0; ii < bit - 1; ii++) {
            value = value * 10;
            if (str < value) {
                break;
            }
        }
        for (int jj = 0; jj < bit - ii - 1; jj++) {
            pre = pre + "0";
        }
        return pre + String.valueOf(str);
    }

    /**
     * 随机产生一个长度为nLength的字符串
     */
    public static synchronized String getRandomStr(int nLength) {
        int i;
        int nKey;
        char chKey;
        long lResult = 0;
        // java.util.Random tt = new java.util.Random((new
        // java.util.Date()).getTime());
        String RandomString = "";
        for (i = 0; i < nLength; i++) {
            nKey = tt.nextInt();
            if (nKey < 0) {
                nKey = -nKey;
            }
            nKey %= 256;
            if (nKey < 128) {
                nKey = nKey * 26 / 128 + 65;
            } else {
                nKey = (nKey - 128) * 10 / 128 + 48;
            }
            chKey = (char) nKey;
            RandomString += chKey;
        }
        return RandomString;
    }

    /* 递归删除目录 */
    public static void deleteDirectory(File dir) throws IOException {
        if ((dir == null) || !dir.isDirectory()) {
            throw new IllegalArgumentException("Argument " + dir
                    + " is not a directory. ");
        }
        File[] entries = dir.listFiles();
        int sz = entries.length;
        for (int i = 0; i < sz; i++) {
            if (entries[i].isDirectory()) {
                deleteDirectory(entries[i]);
            } else {
                entries[i].delete();
            }
        }
        dir.delete();
    }

    public static String RandomPwd() {
        return "11111111";
    }

    public static String getLogoDir() {
        return ("");
    }

    // 产生分页面的Script脚本与分页面HTML代码
    public static StringBuffer getTurnPageString(int nTotalPage, // 总页数
                                                 int nPage, // 当前页
                                                 String toAction) { // 翻页后转到页面
        StringBuffer content = new StringBuffer();

        content.append("<script language=\"javascript\">\n");
        content.append("<!--\n");
        content.append("function jumpto(){\n");
        content.append("    ss = document.turnpage.page1.value;\n");
        content.append("\n");
        content.append("    if(ss.length==0)\n");
        content.append("    {\n");
        content.append("        alert(\"请输入一个跳转的页面!\");\n");
        content.append("        return 0;\n");
        content.append("    }\n");
        content.append("    for (ii=0;ii<ss.length;ii++)\n");
        content.append("    {\n");
        content.append("        if ((ss.charAt(ii)<'0'||ss.charAt(ii)>'9'))\n");
        content.append("        {\n");
        content.append("            alert(\"输入的页码不是数字!\");\n");
        content.append("            return 0;\n");
        content.append("        }\n");
        content.append("    }\n");
        content.append("\n");
        content.append("    if(ss/1<=0)\n");
        content.append("    {\n");
        content.append("        alert(\"输入的页码不能为0!\");\n");
        content.append("        return 0;\n");
        content.append("    }\n");
        content.append("    if(ss/1>" + nTotalPage + ")\n");
        content.append("    {\n");
        content.append("        alert(\"总共只有" + nTotalPage + "页!\");\n");
        content.append("        return 0;\n");
        content.append("    }\n");
        content.append("    document.turnpage.page.value = document.turnpage.page1.value;\n");
        content.append("\n");
        content.append("    document.turnpage.action=\"" + toAction + "\";\n");
        content.append("    document.turnpage.method=\"post\";\n");
        content.append("    document.turnpage.submit();\n");
        content.append("}\n");
        content.append("\n");
        content.append("function nextpage(){\n");
        content.append("    document.turnpage.page.value = " + (nPage + 1)
                + ";\n");
        content.append("    document.turnpage.action=\"" + toAction + "\";\n");
        content.append("    document.turnpage.method=\"post\";\n");
        content.append("    document.turnpage.submit();\n");
        content.append("}\n");
        content.append("\n");
        content.append("function previuspage(){\n");
        content.append("    document.turnpage.page.value = " + (nPage - 1)
                + ";\n");
        content.append("\n");
        content.append("    document.turnpage.action=\"" + toAction + "\";\n");
        content.append("    document.turnpage.method=\"post\";\n");
        content.append("    document.turnpage.submit();\n");
        content.append("}\n");
        content.append("//-->\n");
        content.append("</script>\n");
        content.append("\n");
        content.append("  <a ");
        if (nPage > 1) {
            content.append("href=\"#\" onclick=\"previuspage();return false;\"");
        }
        content.append(">上一页</a>\n");
        content.append("  <a ");
        if (nPage < nTotalPage) {
            content.append("href=\"#\" onclick=\"nextpage();return false;\"");
        }
        content.append(">下一页</a>\n");
        content.append("    第" + nPage + "页/共" + nTotalPage + "页 到第\n");
        content.append("    <input type=\"text\" name=\"page1\" size=\"3\">\n");
        content.append("        <input type=hidden name=\"page\" size=\"3\">\n");
        content.append("    页 <input onclick=\"jumpto();return false;\" type=\"button\" class=\"buttonstyle\" name=\"Submit1212\" value=\"跳到\">\n");

        return content;
    }

    // 产生判断选择钮选择的Script脚本(单选)
    public static StringBuffer getRadioScript(String sFormName, // 表单Form名称
                                              String sRadioName // 选择钮名称
    ) {
        StringBuffer content = new StringBuffer();

        content.append("//判断当前是否有选择,有返回true,否则返回false\n");
        content.append("function getChecked(){\n");
        content.append("    bb=false;\n");
        content.append("    ss = document." + sFormName + ".all['" + sRadioName
                + "'];\n");
        content.append("    if(ss != null)	{\n"); // 存在有选择项
        content.append("         if(ss.length > 1){\n"); // 有多个选择项
        content.append("             for(ii=0; ii < ss.length; ii ++){\n");
        content.append("                 if(ss[ii].checked) bb = true;\n");
        content.append("             }\n");
        content.append("         }else{\n"); // 只有一个选择项
        content.append("             if(ss.checked) bb = true;\n");
        content.append("         }\n");
        content.append("     }\n");
        content.append("     return bb;\n");
        content.append("}\n");

        return content;
    }

    // 产生判断选择钮选择的Script脚本(多选)
    public static StringBuffer getCheckBoxScript(String sFormName, // 表单Form名称
                                                 String sRadioName // 选择钮名称
    ) {
        StringBuffer content = new StringBuffer();

        content.append("//监控，判断是否加上全选或去掉全选\n");
        content.append("function dounselectall(bcheck){\n");
        content.append("    bb = true;\n");
        content.append("    ss = document." + sFormName + ".all['" + sRadioName
                + "'];\n");
        content.append("    if(ss != null)	{\n"); // 存在有选择项
        content.append("         if(ss.length > 1){\n"); // 有多个选择项
        content.append("             for(ii=0; ii < ss.length; ii ++){\n");
        content.append("                 if(!ss[ii].checked) bb= false;\n");
        content.append("             }\n");
        content.append("         }else{\n"); // 只有一个选择项
        content.append("             if(!ss.checked) bb= false;\n");
        content.append("         }\n");
        content.append("     }\n");
        content.append("     document." + sFormName
                + ".selectall[0].checked = bb;\n");
        content.append("     document." + sFormName
                + ".selectall[1].checked = bb;\n");
        content.append("}\n");
        content.append("\n");
        content.append("//全选调用：全选或全不选\n");
        content.append("function doselectall(no){\n");
        content.append("    tobechecked = true;\n");
        content.append("    if (document." + sFormName
                + ".selectall[no].checked)\n");
        content.append("        tobechecked = true;\n");
        content.append("    else\n");
        content.append("        tobechecked = false;\n");
        content.append("    document." + sFormName
                + ".selectall[0].checked = tobechecked;\n");
        content.append("    document." + sFormName
                + ".selectall[1].checked = tobechecked;\n");
        content.append("    ss = document." + sFormName + ".all['" + sRadioName
                + "'];\n");
        content.append("    if(ss != null)	{\n"); // 存在有选择项
        content.append("         if(ss.length > 1){\n"); // 有多个选择项
        content.append("             for(ii=0; ii < ss.length; ii ++){\n");
        content.append("                 ss[ii].checked = tobechecked;\n");
        content.append("             }\n");
        content.append("         }else{\n"); // 只有一个选择项
        content.append("             ss.checked = tobechecked;\n");
        content.append("         }\n");
        content.append("     }\n");
        content.append("}\n");
        content.append("\n");
        content.append("//得到当前选择数\n");
        content.append("function getCheckCount(){\n");
        content.append("    ee=0;\n");
        content.append("    ss = document." + sFormName + ".all['" + sRadioName
                + "'];\n");
        content.append("    if(ss != null)	{\n"); // 存在有选择项
        content.append("         if(ss.length > 1){\n"); // 有多个选择项
        content.append("             for(ii=0; ii < ss.length; ii ++){\n");
        content.append("                 if(ss[ii].checked) ee++;\n");
        content.append("             }\n");
        content.append("         }else{\n"); // 只有一个选择项
        content.append("             if(ss.checked) ee++;\n");
        content.append("         }\n");
        content.append("     }\n");
        content.append("     return ee;\n");
        content.append("}\n");

        return content;
    }

    /**
     * Get the date of monday in this week
     *
     * @return yyyy-MM-dd
     */
    public static int getLastDayOfThisWeek(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        int dayofweek = 7 - c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DATE, dayofweek);

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int date = 0;
        date = (c.get(Calendar.YEAR)) * 10000 + (c.get(Calendar.MONTH) + 1)
                * 100 + (c.get(Calendar.DAY_OF_MONTH));
        return date; // sdf.format(c.getTime());
    }

    /**
     * Get the date of sunday in this week
     *
     * @return yyyy-MM-dd
     */
    public static int getFirstDayOfThisWeek(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;

        c.add(Calendar.DATE, -dayofweek);
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int date = 0;
        date = (c.get(Calendar.YEAR)) * 10000 + (c.get(Calendar.MONTH) + 1)
                * 100 + (c.get(Calendar.DAY_OF_MONTH));
        return date; // sdf.format(c.getTime());

    }

    // 得到本月最后一天
    public static int getLastDayOfMonth(int year, int month) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, 1);
        int maxDayOfMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDayOfMonth;
    }

    public static String getpageSql(String sql, int fromIndex, int endIndex) {
        String result = "select * from ( select row_.*, rownum rownum_ from ( "
                + sql + " ) row_ where rownum <= " + endIndex
                + ") where rownum_ >=" + fromIndex;
        return result;

    }

    public static boolean checkright(long rightno, String privilege) {
        boolean result = false;
        if (privilege != null) {
            if (!privilege.startsWith(","))
                privilege = "," + privilege;

            if (!privilege.endsWith(","))
                privilege += ",";

        }
        if (privilege != null && privilege.indexOf("," + rightno + ",") >= 0)
            result = true;
        return result;
    }

    public static boolean checkright(int rightno, String privilege) {
        boolean result = false;
        if (privilege != null) {
            if (!privilege.startsWith(","))
                privilege = "," + privilege;

            if (!privilege.endsWith(","))
                privilege += ",";

        }
        if (privilege != null && privilege.indexOf("," + rightno + ",") >= 0)
            result = true;
        return result;
    }

    /**
     * 得到格式后后的 d1/d2的结果，保留小数点后两位
     *
     * @param d1
     * @param d2
     * @return
     */
    public static String div(double d1, double d2) {
        DecimalFormat format = new DecimalFormat("0.00");
        String str = format.format(d1 / d2);
        return str;
    }

    /**
     * 得到格式后后的 d1/d2的结果，格式化后的结果样式有 formatStyle
     *
     * @param d1
     * @param d2
     * @param formatStyle
     * @return
     */
    public static String div(double d1, double d2, String formatStyle) {
        DecimalFormat format = new DecimalFormat(formatStyle);
        String str = format.format(d1 / d2);
        return str;
    }

    /**
     * add by zhanggf 2008-7-1 批量增加时写日志文件
     *
     * @param filename
     * @param logstr
     */
    public static void writelog(String filename, String logstr) {

        PrintWriter log = null;
        try {
            log = new PrintWriter(new FileWriter(filename, true), true);
        } catch (IOException e) {
            System.err.println("!!!error open log file: ");
            log = null;
        }

        if (log != null) {
            log.println((new java.util.Date()).toLocaleString() + ": " + logstr);

        }

    }

    public static String getStringDate(Date date) {
        String format = "yyyy-MM-dd HH:mm:ss";

        return getStringDate(date, format);

    }

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


    public static String showString(String str) {
        return str == null ? "" : str;
    }

    public static String showDate(Date date) {
        String format = "yyyy-MM-dd";

        return getStringDate(date, format);
    }

    public static String showDateTime(Date date) {
        String format = "yyyy-MM-dd HH:mm:ss";

        return getStringDate(date, format);

    }

    public static String replaceFirst(String content, String key, String value) {
        StringBuilder result = new StringBuilder();
        int start = content.indexOf(key);

        if (start >= 0) {
            String begin = "";
            if (start > 0) {
                begin = content.substring(0, start);
            }
            String end = content.substring(start + key.length());
            result.append(begin).append(value).append(end);
        } else {
            result.append(content);
        }

        return result.toString();
    }

    public static String replaceAll(String content, String key, String value) {
        String result = content;

        while (result.indexOf(key) >= 0) {
            result = replaceFirst(result, key, value);
        }

        return result;
    }


    public static boolean isNull(String str) {
        return str == null || "".equals(str.trim())
                || "null".equalsIgnoreCase(str) ? true : false;
    }

    public static String formatStringDelComma(String str) {
        String result = "";
        if (str != null) {
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

    public static String arrayToString(long[] id) {
        StringBuilder ID = new StringBuilder();

        if (id != null) {
            for (long i : id) {
                ID.append(i).append(",");
            }

            ID.deleteCharAt(ID.length() - 1);

        }

        return id.toString();

    }

    public static String arrayToString(int[] no) {
        StringBuilder ID = new StringBuilder();

        if (no != null) {
            for (long i : no) {
                ID.append(i).append(",");
            }

            ID.deleteCharAt(ID.length() - 1);

        }

        return ID.toString();

    }

    public static int[] stringToArray(String ids) {
        return stringToArray(ids, ",");
    }

    public static int[] stringToArray(String ids, String seg) {
        int[] result = null;

        if (!isNull(ids)) {
            ids = Util.formatStringDelComma(ids);
            String[] tempA = ids.split(seg);
            result = new int[tempA.length];
            for (int i = 0, j = tempA.length; i < j; i++) {
                result[i] = Util.StringToInt(tempA[i], 0);
            }

        }
        return result;
    }

    public static Date getDateFromString(String strDate) {
        return getDateFromString(strDate, "yyyy-MM-dd");
    }

    public static Date getDateTimeFromString(String strDate) {
        return getDateFromString(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getDateFromString(String strDate, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date result = null;
        try {
            result = sdf.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static void writeParamLog(HttpServletRequest request, Logger logger) {
        try {
            Enumeration<String> e = request.getParameterNames();
            StringBuilder params = new StringBuilder();
            Map<String, String> paramKV = new HashMap();
            String name = null;
            String value = null;
            while (e.hasMoreElements()) {
                name = e.nextElement();
                value = request.getParameter(name);
                if (params.length() > 0)
                    params.append(",");
                params.append(name).append("=").append(value);
            }
            logger.debug("params=" + params);
        } catch (Exception e) {
            logger.debug("writeParamLog Errror", e);
        }

    }

    public static void writeParamLog(HttpServletRequest request,
                                     String logString) {
        Logger logger = Logger.getLogger(logString);
        writeParamLog(request, logger);
    }

    public static String formatUrl(String pre, String uri) {
        String result = "";
        if (!Util.isNull(pre)) {

            if (!pre.endsWith("/")) {
                result = pre + "/";
            } else {
                result = pre;
            }

        }
        if (!Util.isNull(uri)) {
            if (uri.startsWith("/")) {
                uri = uri.substring(1);
            }
            result = result + uri;
        }
        return result;

    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日 H:mm");
        if (date == null) {
            return "";
        } else {
            return sdf.format(date);
        }
    }
}
