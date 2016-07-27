package com.myweb.framework.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rola
 */
public class StringUtil {

    //字符串分隔符
    public static final String SEPARATOR = String.valueOf((char)29);

    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    public static String defaultIfEmpty(String str,String defaultValue) {
        return StringUtils.defaultIfEmpty(str,defaultValue);
    }

    /**
     * 替换固定格式的字符串（支持正则）
     * @param str
     * @param regex
     * @param replacement
     * @return
     */
    public static String replaceAll(String str,String regex,String replacement) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(m.find()) {
            m.appendReplacement(sb,replacement);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static boolean isNumber(String str) {
        return NumberUtils.isNumber(str);
    }

    public static boolean isDigits(String str) {
        return NumberUtils.isDigits(str);
    }

    public static String camelhumpToUnder(String str) {
        Matcher matcher = Pattern.compile("[A-Z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for(int i = 0;matcher.find();i++) {
            builder.replace(matcher.start() + i,matcher.end()+i,"_"+matcher.group().toLowerCase());
        }
        if(builder.charAt(0) == '_') {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    public static String underlineToCamelhump(String str) {
        Matcher matcher = Pattern.compile("[A-Z]").matcher(str);

        StringBuilder builder = new StringBuilder(str);
        for(int i=0;matcher.find();i++) {
            builder.replace(matcher.start()-i,matcher.end()-i,matcher.group().substring(1).toUpperCase());
        }
        if(Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0,1,String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }

    public static String[] splitString(String str,String separator) {
        return StringUtils.splitByWholeSeparator(str,separator);
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String firstToUpper(String str) {
        return Character.toUpperCase(str.charAt(0))+str.substring(1);
    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String firstToLower(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

}



















