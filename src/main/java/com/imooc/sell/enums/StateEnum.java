package com.imooc.sell.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum StateEnum implements IEnum {
    DELETE(1,"未在售"),
    NO_DELETE(0,"在售");
    private Integer code;
    private String msg;

    StateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }
    @JsonValue
    public String getMsg() {
        return msg;
    }
}
