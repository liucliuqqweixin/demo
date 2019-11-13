package com.imooc.sell.service.impl;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.service.IOrderService;
import com.imooc.sell.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderServiceImplTest {
    @Autowired
    private IOrderService iOrderService;

    private final String BUYER_OPENID = "110110";

    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("小小liuc");
        orderDTO.setBuyerPhone("17778045265");
        orderDTO.setBuyerAddress("竹园小区");
        orderDTO.setBuyerOpenid("220220");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("6836af6f22bfe686a4f6ea1300490510");
        o1.setProductQuantity(5);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("a18c14bc59005cac21119ac4f3309177");
        o2.setProductQuantity(4);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = iOrderService.create(orderDTO);
        log.info("【创建订单】result={}", result);


    }

    @Test
    void findOne() {
        System.out.println(JsonUtil.toJson(iOrderService.findOne("1573651622426752128")));
    }

    @Test
    void findList() {
        System.out.println(JsonUtil.toJson(iOrderService.findList(BUYER_OPENID, new Page())));
    }

    @Test
    void cancel() {
        OrderDTO orderDTO = iOrderService.findOne("1573661151503224975");
        iOrderService.cancel(orderDTO);
    }

    @Test
    void finish() {
        OrderDTO orderDTO = iOrderService.findOne("1573651622426752128");
        iOrderService.finish(orderDTO);
    }

    @Test
    void paid() {
        OrderDTO orderDTO = iOrderService.findOne("1573661151503224975");
        iOrderService.paid(orderDTO);
    }
}