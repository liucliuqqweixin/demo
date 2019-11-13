package com.imooc.sell.mapper;

import java.util.Arrays;
import java.util.Date;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderDetailMapperTest {
    private static OrderDetailMapper mapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Test
    public void test1() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("11111111");
        orderDetail.setProductId("11111112");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal("1.2"));
        orderDetail.setProductQuantity(3);
        orderDetail.setProductIcon("http://xxxx.jpg");
        System.out.println(orderDetailMapper.insert(orderDetail));
    }

    @Test
    void test2() {
        IPage<OrderDetail> page = new Page<>();
        page.setCurrent(1L);
        page.setSize(2);
        System.out.println(JsonUtil.toJson(orderDetailMapper.selectPage(page, null)));
    }

    @Test
    void test3() {


        log.info("info.....................................");
        log.info("info.....................................");
        log.warn("warn.....................................");
        log.warn("warn.....................................");
        log.error("error....................................");
        log.error("error....................................");
    }

    @Test
    public void testInsertBatch() throws FileNotFoundException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1111");
        orderDetail.setOrderId("111");
        orderDetail.setProductId("111");
        orderDetail.setProductName("111");
        orderDetail.setProductPrice(new BigDecimal("0"));
        orderDetail.setProductQuantity(0);
        orderDetail.setProductIcon("111");
        orderDetail.setCreateTime(new Date());
        orderDetail.setDel(0);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setDetailId("222");
        orderDetail1.setOrderId("222");
        orderDetail1.setProductId("222");
        orderDetail1.setProductName("222");
        orderDetail1.setProductPrice(new BigDecimal("0"));
        orderDetail1.setProductQuantity(0);
        orderDetail1.setProductIcon("222");
        orderDetail1.setCreateTime(new Date());
        Integer integer = orderDetailMapper.insertBatch(Arrays.asList(orderDetail, orderDetail1));
        System.out.println(integer);
    }
}