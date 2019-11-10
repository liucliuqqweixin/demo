package com.imooc.sell.controller;


import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.form.Page;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.OrderMaster;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单主表 前端控制器
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@RestController
@RequestMapping("/order-master")
public class OrderMasterController implements BaseController<OrderMaster,String>{

    @Override
    public Result list(OrderMaster orderMaster, Page page) {
        return null;
    }

    @Override
    public Result insert(OrderMaster orderMaster, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Result update(OrderMaster orderMaster, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Result delete(String s) {
        return null;
    }
}
