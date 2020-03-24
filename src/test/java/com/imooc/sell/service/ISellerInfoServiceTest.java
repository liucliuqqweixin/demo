package com.imooc.sell.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ISellerInfoServiceTest {
    @Autowired
    private ISellerInfoService iSellerInfoService;

    @Test
    void findSellerByOpenid() {
        System.out.println(iSellerInfoService.findSellerByOpenid("asd"));
    }
}