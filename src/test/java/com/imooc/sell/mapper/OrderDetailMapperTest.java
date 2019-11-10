package com.imooc.sell.mapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderDetailMapperTest {
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Test
    public void test1(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("sdsadsa4");
        orderDetail.setProductId("sdasdfdf34");
        orderDetail.setProductName("sdsadas");
        orderDetail.setProductPrice(new BigDecimal("0"));
        orderDetail.setProductQuantity(0);
        orderDetail.setProductIcon("");
        System.out.println(orderDetailMapper.insert(orderDetail));
    }
    @Test
    void test2(){
        IPage<OrderDetail> page = new Page<>();
        page.setCurrent(1L);
        page.setSize(2);
        System.out.println(JsonUtil.toJson(orderDetailMapper.selectPage(page, null)));
    }

}