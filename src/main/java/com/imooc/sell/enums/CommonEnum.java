package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum CommonEnum implements LEnum {
    SUCCESS(200, "成功"),
    ERROR(500, "失败"),
    UNKNOWNERROR(-1, "未知错误");;

    CommonEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;
}
