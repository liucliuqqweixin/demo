package com.imooc.sell.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.form.OrderDetailForm;
import com.imooc.sell.common.form.Page;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.service.IOrderDetailService;
import com.imooc.sell.util.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单详情表 前端控制器
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@RestController
@RequestMapping("/order-detail")
@Api(tags = "订单详情接口",description = "OrderDetail订单详情接口文档")
public class OrderDetailController implements BaseController<OrderDetail,String>{

    @Autowired
    private IOrderDetailService orderDetailService;

    @Override
    public Result list(OrderDetail orderDetail, Page page) {
        return null;
    }
    @GetMapping
    public Result listPage(OrderDetailForm orderDetail, Page page) {
        return orderDetailService.page(orderDetail,page);
    }


    @Override
    @PostMapping
    public Result insert(@RequestBody @Validated OrderDetail orderDetail, BindingResult bindingResult) {
        return null;
    }

    @Override
    @PutMapping
    public Result update(@RequestBody @Validated OrderDetail orderDetail, BindingResult bindingResult) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        boolean flag = orderDetailService.removeById(id);
        return ResultUtil.success(flag);
    }
}
