package com.wav.rzpp.common;

import jdk.net.SocketFlow;

import java.util.HashMap;

/**
 * @author: hbw
 **/
public class AjaxResult extends HashMap<String, Object> {
    /**
     * 状态码标签字符串常量
     */
    private static final String CODE_TAG = "code";

    /**
     * 状态信息标签字符串常量
     */
    private static final String MESSAGE_TAG = "message";

    /**
     * 数据标签字符串常量
     */
    private static final String DATA_TAG = "data";

    public AjaxResult() {
    }

    public AjaxResult(Integer code, String message) {
        this.put(CODE_TAG, code);
        this.put(MESSAGE_TAG, message);
        this.put(DATA_TAG, new HashMap<>());
    }

    /**
     * 在data标签中添加数据
     *
     * @param key   数据键
     * @param value 数据值
     * @return 链式对象
     */
    public AjaxResult add(String key, Object value) {
        ((HashMap) this.get(DATA_TAG)).put(key, value);
        return this;
    }

    /**
     * 通过该方法完成成功状态的默认构造
     *
     * @return 链式对象
     */
    public static AjaxResult success() {
        return new AjaxResult(HttpStatus.SUCCESS.getCode(), HttpStatus.SUCCESS.getMessage());

    }

    /**
     * 直接返回数据
     *
     * @param data 数据
     * @return 数据
     */
    public static AjaxResult success(Object data) {
        AjaxResult ajaxResult = new AjaxResult(HttpStatus.SUCCESS.getCode(), HttpStatus.SUCCESS.getMessage());
        ajaxResult.put("data", data);
        return ajaxResult;
    }

    /**
     * 通过该方法完成失败状态的默认构造
     *
     * @return 链式对象
     */
    public static AjaxResult fail() {
        return new AjaxResult(HttpStatus.ERROR.getCode(), HttpStatus.ERROR.getMessage());
    }

    /**
     * 失败返回自定义提示提示
     *
     * @param message 错误提示
     * @return 链式对象
     */
    public static AjaxResult failed(String message) {
        return new AjaxResult(HttpStatus.ERROR.getCode(), message);
    }
}
