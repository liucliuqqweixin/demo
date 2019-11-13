package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum ConstantEnum {
    ZERO(0),
    ONE(1);
    private Integer code;

    ConstantEnum(Integer code) {
        this.code = code;
    }
}
