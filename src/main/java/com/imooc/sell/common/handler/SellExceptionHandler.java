package com.imooc.sell.common.handler;

import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SellExceptionHandler {
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Result handlerSellerException(ServiceException e) {
        return ResultUtil.error(e.getCode(), e.getMessage());
    }
}
