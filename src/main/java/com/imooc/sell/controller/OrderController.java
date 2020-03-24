package com.imooc.sell.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.convert.OrderForm2OrderDTOConverter;
import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.common.form.OrderForm;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.IOrderService;
import com.imooc.sell.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单详情表 前端控制器
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@RestController
@RequestMapping("/buyer/order")
@Api(tags = "订单详情接口", description = "OrderDetail订单详情接口文档")
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping(value = "/create")
    @ApiOperation("创建订单接口")
    public Result create(@Validated OrderForm orderForm, BindingResult bindingResult) {
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new ServiceException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public Result list(@RequestParam("openid") String openid,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new ServiceException(ResultEnum.PARAM_ERROR);
        }

        Page request = new Page();
        request.setCurrent(page + 1);
        request.setSize(size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

        return ResultUtil.success(orderDTOPage.getRecords());
    }


    //    订单详情
    @GetMapping("/detail")
    public Result detail(@RequestParam("openid") String openid,
                         @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public Result cancel(@RequestParam("openid") String openid,
                         @RequestParam("orderId") String orderId) {
        buyerService.cancel(openid, orderId);
        return ResultUtil.success();
    }

}
