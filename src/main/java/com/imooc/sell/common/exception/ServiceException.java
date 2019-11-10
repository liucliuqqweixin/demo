package com.imooc.sell.common.exception;

import com.imooc.sell.enums.LEnum;

public class ServiceException extends BaseException {

    public ServiceException(String message, Integer code) {
        super(message, code);
    }

    public ServiceException(LEnum lEnum) {
        super(lEnum);
    }
}
