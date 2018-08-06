package com.love.lylph.common;

/**
 * @author PanHuai
 * @data 2018/7/31 10:37
 */
public class StringUtils {

    /**
     * 判断是否为null或者"" "  "
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        if (cs != null && cs.length() != 0) {
            for(int i = 0; i < cs.length(); ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}
