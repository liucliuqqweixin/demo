package com.imooc.sell.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.io.Serializable;


public enum OrderStatusEnum implements IEnum {
    NEW(0, "新订单"),

    FINISHED(1, "完结"),

    CANCEL(2, "取消");

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    @Override
    public Serializable getValue() {
        return this.code;
    }

    @JsonValue
    public String getMsg() {
        return msg;
    }
}
