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
    CART_EMPTY(18, "购物车为空"),
    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),
    WECHAT_MP_ERROR(20, "微信公众号方面错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),
    ORDER_CANCEL_SUCCESS(200,"订单取消成功"),
    ORDER_FINISH_SUCCESS(200,"订单完结成功")
    ;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;


}
