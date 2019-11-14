package com.imooc.sell.mapper;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.sell.enums.StateEnum;

import java.time.LocalDateTime;

import com.imooc.sell.entity.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductInfoMapperTest {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Test
    void test1() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal("6.2"));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的粥");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(StateEnum.OBTAINED);
        productInfo.setCategoryType(3);
        productInfoMapper.insert(productInfo);
    }

    @Test
    void test2() {
        LambdaQueryWrapper<ProductInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ProductInfo::getProductStatus, StateEnum.NORMAL);
        System.out.println(productInfoMapper.selectList(wrapper));
    }
}