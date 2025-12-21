package com.xiaoyang.common;

public enum ResultCode {
    SUCCESS(200,"success"),
    FAILURE(500,"failed"),
    BAD_REQUEST(400, "请求参数错误"),
    NOT_FOUND(404,"未找到");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
