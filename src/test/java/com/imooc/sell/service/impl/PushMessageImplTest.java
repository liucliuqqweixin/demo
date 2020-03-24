package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.IOrderService;
import com.imooc.sell.service.PushMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PushMessageImplTest {
    @Autowired
    private PushMessage pushMessage;
    @Autowired
    private IOrderService iOrderService;
    @Test
    void orderStatus() {
        OrderDTO orderDTO = iOrderService.findOne("sdsds");
        pushMessage.orderStatus(orderDTO);
    }
}