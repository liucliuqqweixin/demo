package com.imooc.sell.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.io.Serializable;

public enum PayStatusEnum implements IEnum, LEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    RETURN(2, "退款");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
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
