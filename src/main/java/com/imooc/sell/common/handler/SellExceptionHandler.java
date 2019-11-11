package com.imooc.sell.common.handler;

import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.exception.BaseException;
import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.enums.CommonEnum;
import com.imooc.sell.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SellExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handlerSellerException(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException exception = (ServiceException) e;
            return ResultUtil.error(exception.getCode(), exception.getMessage());
        } else {
            e.printStackTrace();
            return ResultUtil.error(CommonEnum.ERROR.getCode(), CommonEnum.ERROR.getMsg());
        }

    }
}
