package com.imooc.sell.config.function;

import com.imooc.sell.enums.LEnum;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.util.EnumUtil;
import org.beetl.core.Context;
import org.beetl.core.Function;

import java.text.SimpleDateFormat;

public class EnumFunction implements Function {
    @Override
    public Object call(Object[] objects, Context context) {
        Integer code = (Integer) objects[0];
        String clzz = (String) objects[1];
        LEnum lEnum = EnumUtil.getMsgByCode(code, clzz);
        return lEnum.getMsg();
    }
}
