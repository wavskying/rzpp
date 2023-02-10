package com.wav.rzpp.utils;

import sun.security.validator.ValidatorException;

/**
 * @author: hbw
 **/
public class ValidatorUtil {
    // 错误信息模板
    private static final String IS_EMPTY = "%s不能为空";
    private static final String LESS_THAN_ZERO = "%s不能小于0";
    private static final String NOT_WHOLE = "参数不完整";

    /**
     * 校验参数是否为null
     *
     * @param param
     * @param fieldName
     */
    public static void checkNull(Object param, String fieldName) throws ValidatorException {
        if (param == null) {
            // ValidatorException是自定义异常
            throw new ValidatorException(String.format(IS_EMPTY, fieldName));
        }
    }

    /**
     * 校验id是否合法
     *
     * @param id
     * @param fieldName
     */
    public static void checkId(Long id, String fieldName) throws ValidatorException {
        if (id == null) {
            throw new ValidatorException(String.format(IS_EMPTY, fieldName));
        }
        if (id < 0) {
            throw new ValidatorException(String.format(LESS_THAN_ZERO, fieldName));
        }
    }

    public static void checkLogin(Object... args) throws ValidatorException {
        for (Object arg : args) {
            if (arg == null) {
                throw new ValidatorException(NOT_WHOLE);
            }
        }
    }
}
