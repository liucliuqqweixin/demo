package com.imooc.sell.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum StateEnum implements IEnum ,LEnum{
    OBTAINED(1, "下架"),
    NORMAL(0, "正常");
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

    @Override
    public Integer getCode() {
        return code;
    }

    @JsonValue
    public String getMsg() {
        return msg;
    }
}
