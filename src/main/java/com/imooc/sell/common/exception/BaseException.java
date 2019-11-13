package com.imooc.sell.common.exception;

import com.imooc.sell.enums.LEnum;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class BaseException extends RuntimeException{
    protected Integer code;
    @org.springframework.beans.factory.annotation.Autowired
    private com.imooc.sell.mapper.OrderDetailMapper orderDetailMapper;
    @org.springframework.beans.factory.annotation.Autowired
    private com.imooc.sell.mapper.OrderMasterMapper orderMasterMapper;

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }
    public BaseException(LEnum lEnum) {
        super(lEnum.getMsg());
        this.code = lEnum.getCode();
    }
}
