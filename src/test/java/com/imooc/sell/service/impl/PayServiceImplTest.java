package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.IOrderService;
import com.imooc.sell.service.PayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PayServiceImplTest {
    @Autowired
    private PayService payService;
    @Autowired
    private IOrderService iOrderService;

    @Test
    void create() {
        payService.create(iOrderService.findOne("1573743960669458627"));
    }
}