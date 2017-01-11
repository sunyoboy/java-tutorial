package com.lieve.online.common;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 */
public class StringUtils {
    static String classPath = StringUtils.class.toString();

    /**
     * 判断字符串为空
     *
     * @param str
     *            字符串
     * @return true(空) false(非空)
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) || "null".equals(str)
                || str.length() == 0;
    }

    /**
     * 字符串输出(为空也显示）
     *
     * @param args
     *            原字符串
     * @return 字符串
     */
    public static String toString(String args) {
        if (args != null)
            return args;
        else
            return "";
    }

    /**
     * 验证 正则表达式
     *
     * @param regex
     *            规则
     * @param value
     *            字符串
     * @return boolean
     */
    public static boolean regex(String regex, String value) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        return m.find();
    }

    /**
     * 清除过滤掉 内容中的html标记 一般
     *
     * @param content
     * @return
     */
    public static String filterHtmlTag(String content) {
        boolean flag = true;
        StringBuffer stringBuffer = new StringBuffer(2048);
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '<') {
                flag = false;
                continue;
            }
            if (content.charAt(i) == '>') {
                flag = true;
                continue;
            }
            if (content.charAt(i) == '\n') {
                flag = true;
                continue;
            }
            if (flag) {
                stringBuffer.append(content.charAt(i));
            }
        }
        return stringBuffer.toString().replaceAll("&nbsp;", "")
                .replaceAll("&ldquo;", "").replaceAll("&rdquo;", "")
                .replaceAll("\n", "");
    }

    /**
     * 清除过滤掉 内容中的html标记 根据正则表达式
     * String yourRegEx =
     * "( <\\s*[a-zA-Z][^> ]*> )|( </\\s*[a-zA-Z][^> ]*> ) ";/
     * /这个就是对应的去掉HTML标记的正则表达式
     *
     * @param content
     * @param regex
     * @return
     */
    public static String filterHtmlTagRegex(String content, String regex) {
        if (content == null)
            content = "";
        String resultstr = " ";
        if (regex == null)
            regex = "(<\\s*[a-zA-Z][^>]*>)|(</\\s*[a-zA-Z][^>]*>)";// 这个就是对应的去掉HTML标记的正则表达式
        try {
            Pattern p = Pattern.compile(regex);// 设置比较模式
            Matcher m = p.matcher(content);
            resultstr = p.matcher(content).replaceAll(resultstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultstr;
    }

    /**
     * 清除过滤掉 内容中的html标记 根据正则表达式
     *
     * @param content
     * @return
     */
    public static String filterHtmlTagRegex(String content) {
        String regex = "(<\\s*[a-zA-Z][^>]*>)|(</\\s*[a-zA-Z][^>]*>)";// 这个就是对应的去掉HTML标记的正则表达式
        return filterHtmlTagRegex(content, regex);
    }

    /**
     * 手工生成主键，主要用于附件上传功能时使用 使用 hibernate生成策略
     *
     * @return
     */
/*    public static String getIdentifierGenerator(String type) {
        IdentifierGenerator gen = new UUIDHexGenerator(); // uuid.hex
        if ("uuid".equalsIgnoreCase(type)) {
            return (String) gen.generate(null, null);
        } else {
            return (String) gen.generate(null, null);
        }
    }*/

    /**
     * 手工生成主键，主要用于附件上传功能时使用 默认使用 hibernate uuid生成策略
     *
     * @return
     */
/*    public static String getIdentifierGenerator() {
        return getIdentifierGenerator("uuid");
    }*/

    public static void main(String[] args) {
        String content = "<b font dfdf><font</b>";
        content = "增加了一条菜单记录          属性如下----------   &#10; <br/> &nbsp <br/><b>存储编号：</b>4028e95220b235240120b23685da0036   &#10;<br/>   <b>资源：</b>   &#10;<br/>   <b>应用系统：</b>4028e4d51fa6fbc8011fa736f32a0008   &#10;<br/>   <b>上级菜单：</b>   &#10;<br/>   <b>菜单名称：</b><xiaixanfg   &#10;<br/>   <b>目标：</b>1   &#10;<br/>   <b>图标：</b>resource/icons/0.gif   &#10;<br/>   <b>是否隐藏：</b>2   &#10;<br/>   <b>菜单类型：</b>1   &#10;<br/>   <b>排序：</b>1066   &#10;<br/>   <b>描述：</b>&nbsp;xiaixanfg肖祥   &#10;<br/>   <b>是否展开：</b>2   &#10;<br/>   <b>页签标题：</b>xiaixanfg>   &#10;<br/>   ";
        String regex = "(<\\s*[a-zA-Z]*>)|(</\\s*[a-zA-Z]*>)";
        System.out.println(filterHtmlTag(content));
        System.out.println(filterHtmlTagRegex(content));
        System.out.println(filterHtmlTagRegex(content, regex));
    }

    public static String formatStringDelComma(String str) {
        String result = "";
        if (!isEmpty(str)) {
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

    public static int[] StringToIntArray(String str, String reg) {
        String[] sarray = str.split(reg);
        int[] result = new int[sarray.length];
        for (int i = 0, j = sarray.length; i < j; i++) {
            result[i] = Util.StringToInt(sarray[i], -1);
        }
        return result;
    }

    public static int[] formatStringToArray(String str) {
        str = formatStringDelComma(str);
        return StringToIntArray(str, ",");

    }
}
