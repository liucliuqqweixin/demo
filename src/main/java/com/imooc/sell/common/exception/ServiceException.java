package com.imooc.sell.common.exception;

import com.imooc.sell.enums.LEnum;

public class ServiceException extends BaseException {

    public ServiceException(Integer code, String message) {
        super(message, code);
    }

    public ServiceException(LEnum lEnum) {
        super(lEnum);
    }
}
