package com.wav.rzpp.common;

/**
 * @author: hbw
 **/
public enum HttpStatus {
    SUCCESS(200, "操作成功"),
    NO_CONTENT(204, "操作成功,无返回数据"),
    ERROR_IDENTITY(305, "用户名或密码错误"),
    TOKEN_INVALID(306, "登陆状态过期请重新登陆"),
    MOVED_PERM(301, "资源已被移除"),
    SEE_OTHER(303, "重定向"),
    NOT_MODIFIED(304, "资源未修改"),
    BAD_REQUEST(400, "格式不匹配"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "访问受限"),
    NOT_FOUND(404, "资源未找到"),
    UNSUPPORTED_TYPE(405, "不受支持的媒体类型"),
    ERROR(500, "系统内部错误");

    private final Integer code;

    private final String message;

    HttpStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
