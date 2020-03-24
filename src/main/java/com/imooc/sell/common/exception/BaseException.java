package com.imooc.sell.common.exception;

import com.imooc.sell.enums.LEnum;
import com.imooc.sell.mapper.OrderDetailMapper;
import com.imooc.sell.mapper.OrderMasterMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class BaseException extends RuntimeException{
    protected Integer code;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderMasterMapper orderMasterMapper;

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }
    public BaseException(LEnum lEnum) {
        super(lEnum.getMsg());
        this.code = lEnum.getCode();
    }
}
