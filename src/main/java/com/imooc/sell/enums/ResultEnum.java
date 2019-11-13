package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum implements LEnum {
    PRODUCT_NOT_EXIST(10, "产品不存在"),
    INSERT_ERROR(20, "写入错误"),
    UPDATE_ERROR(21, "修改错误"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(22, "订单不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    PAY_STATUS_ERROR(17, "订单状态不正确"),
    PARAM_ERROR(501, "参数不正确"),
    CART_EMPTY(18, "购物车为空");

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;


}
