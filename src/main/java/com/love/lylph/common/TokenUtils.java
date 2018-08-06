package com.love.lylph.common;

import org.apache.commons.codec.binary.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author PanHuai
 * @data 2018/8/4 10:59
 */
public class TokenUtils {

    private static SecureRandom secureRandom = null;

    public static String createToken() {
        String msg = RandomString.getRandomString().getMsg(32);
        if (secureRandom == null) {
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            secureRandom.setSeed(msg.getBytes());
        }
        byte bytes[]=new byte[16];
        secureRandom.nextBytes(bytes);
        byte[] base64Bytes = Base64.decodeBase64(bytes);
        return Base64.encodeBase64String(base64Bytes);
    }

    public static void main(String[] args) {
        String token = TokenUtils.createToken();
        System.out.println(token+":  sss");
    }


}
