package com.imooc.sell.util;

import com.imooc.sell.enums.LEnum;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.StateEnum;

public class EnumUtil {
    public static <T extends LEnum> T getMsgByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

    public static <T extends LEnum> T getMsgByCode(Integer code, String clzz) {
        switch (clzz) {
            case "PayStatusEnum":
                return (T) getMsgByCode(code, PayStatusEnum.class);
            case "OrderStatusEnum":
                return (T) getMsgByCode(code, OrderStatusEnum.class);
            case "StateEnum":
                return (T) getMsgByCode(code, StateEnum.class);
        }
        return null;
    }
}
