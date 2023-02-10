package com.wav.rzpp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: hbw
 **/
public class DataSecurityUtil {

    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 对str进行加密
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        return passwordEncoder.encode(str);
    }


    /**
     * 判断str字符串与加密字符串是否匹配
     *
     * @param str           非加密字符串
     * @param encryptionStr 加密字符串
     * @return 是否匹配
     */
    public static boolean isMatch(String str, String encryptionStr) {
        return passwordEncoder.matches(str, encryptionStr);
    }
}
