package com.imooc.sell.mapper;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;

import java.util.Date;

import com.imooc.sell.enums.StateEnum;
import com.imooc.sell.enums.PayStatusEnum;

import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterMapperTest {
    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Test
    void test1() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("123456789123");
        orderMaster.setBuyerAddress("慕课网");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal("50"));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW);
        orderMaster.setPayStatus(PayStatusEnum.WAIT);
        System.out.println(orderMasterMapper.insert(orderMaster));
    }

    @Test
    void selectOrderDTOById() {
        System.out.println(JsonUtil.toJson(orderMasterMapper.selectOrderDTOById("1573651622426752128")));
    }

    @Test
    void selectOrderDTOList() {
        Page page = new Page();
        page.setSearchCount(false);
        Page<OrderDTO> dtoPage = orderMasterMapper.selectOrderDTOList(page, "oTgZpwUYcj3qUXDoCM0ObMLHtduY");
        dtoPage.setTotal(orderMasterMapper.selectCount(null));
        System.out.println(JsonUtil.toJson(dtoPage));
    }
}