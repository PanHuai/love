package com.love.lylph.common;

import java.util.regex.Pattern;

/**
 * @author PanHuai
 * @data 2018/8/6 14:10
 */
public class PatternUtils {

    /**
     * 验证Email
     * @param email email地址，格式：zhangsan@zuidaima.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证身份证号码
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isCardId(String idCard) {
        String regex = "[1-9]{2}[0-9]{4}(19|20)[0-9]{2}"
                + "((0[1-9]{1})|(1[1-2]{1}))((0[1-9]{1})|([1-2]{1}[0-9]{1}|(3[0-1]{1})))"
                + "[0-9]{3}[0-9x]{1}";
        return Pattern.matches(regex,idCard);
    }

    /**
     *     中国电信号段 133、149、153、173、177、180、181、189、199
     *     中国联通号段 130、131、132、145、155、156、166、175、176、185、186
     *     中国移动号段 134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
     *     其他号段
     *     14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
     *     虚拟运营商
     *     电信：1700、1701、1702
     *     移动：1703、1705、1706
     *     联通：1704、1707、1708、1709、171
     *     卫星通信：1349
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        return Pattern.matches(regex,mobile);
    }

}
