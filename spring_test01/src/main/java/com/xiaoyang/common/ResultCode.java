package com.xiaoyang.common;


import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200,"success"),
    FAILURE(500,"failed"),
    BAD_REQUEST(400, "请求参数错误"),
    NOT_FOUND(404,"未找到");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
