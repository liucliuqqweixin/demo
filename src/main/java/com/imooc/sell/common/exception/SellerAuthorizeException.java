package com.imooc.sell.common.exception;

import com.imooc.sell.enums.LEnum;

public class SellerAuthorizeException extends BaseException{
    public SellerAuthorizeException(String message, Integer code) {
        super(message, code);
    }

    public SellerAuthorizeException(LEnum lEnum) {
        super(lEnum);
    }
    public SellerAuthorizeException() {
        super("未登录错误", 500);
    }
}
