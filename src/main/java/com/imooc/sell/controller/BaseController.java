package com.imooc.sell.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.common.VO.Result;
import org.springframework.validation.BindingResult;

public interface BaseController<T,S> {
    Result list(T t, Page page);
    Result insert(T t, BindingResult bindingResult);
    Result update(T t,BindingResult bindingResult);
    Result delete(S s);
}
