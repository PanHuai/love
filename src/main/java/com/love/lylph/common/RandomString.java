package com.love.lylph.common;

import java.util.Random;

/**
 * @author PanHuai
 * @data 2018/8/1 17:52
 */
public class RandomString {

    private static final String msg = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPLKJHGFDSAZXCVBNM";

    private static final RandomString randomString = new RandomString();

    public static RandomString getRandomString() {
        return randomString;
    }

    /**
     * 生成 lengths 位随机字符串
     */
    public String getMsg(int lengths){
        StringBuffer sb = new StringBuffer(32);
        Random random = new Random();
        for (int i = 0; i < lengths; i++) {
            int num = random.nextInt(msg.length());
            sb.append(msg.charAt(num));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RandomString randomString = RandomString.getRandomString();
        for (int i = 0; i <50 ; i++) {
            String s = randomString.getMsg(32);
            System.out.println(s+",长度："+s.length());
        }

    }



}
