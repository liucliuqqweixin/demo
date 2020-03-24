package com.imooc.sell.common.handler;

import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.exception.BaseException;
import com.imooc.sell.common.exception.SellerAuthorizeException;
import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.config.ProjectUrl;
import com.imooc.sell.enums.CommonEnum;
import com.imooc.sell.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {
    @Autowired
    private ProjectUrl projectUrlConfig;

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Result handlerSellerException(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException exception = (ServiceException) e;
            return ResultUtil.error(exception.getCode(), exception.getMessage());
        } else {
            e.printStackTrace();
            return ResultUtil.error(CommonEnum.UNKNOWN_ERROR.getCode(), CommonEnum.UNKNOWN_ERROR.getMsg());
        }
    }

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public String handlerSellerAuthorizeException(Exception e) {
        return "redirect:"
                + (projectUrlConfig.getSell())
                +("/sell/wechat/qrAuthorize")
                +("?returnUrl=")
                +(projectUrlConfig.getSell())
                +("/sell/wechat/qrUserInfo");
    }
}
