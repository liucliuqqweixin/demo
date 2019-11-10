package com.imooc.sell.util;

import com.imooc.sell.common.VO.Result;
import com.imooc.sell.enums.CommonEnum;
import com.imooc.sell.enums.LEnum;

public class ResultUtil {
    public static Result success(Object o){
        Result result = new Result();
        result.setCode(CommonEnum.SUCCESS.getCode());
        result.setMsg(CommonEnum.SUCCESS.getMsg());
        result.setData(o);
        return result;
    }
    public static Result success(){
        return success(null);
    }
    public static Result error(){
        Result result = new Result();
        result.setCode(CommonEnum.ERROR.getCode());
        result.setMsg(CommonEnum.ERROR.getMsg());
        result.setData(null);
        return result;
    }
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
