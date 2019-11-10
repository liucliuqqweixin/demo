package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum implements LEnum {
    ORDER_DETAIL_ERROR(505,"失败"),
    ERROR(500,"失败")
    ;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;
}
