package com.imooc.sell.mapper;

import com.imooc.sell.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void insertBach() {
//        List<User> list = new LinkedList<>();
//        for (int i = 0; i < 1000000; i++) {
//            User user = new User("aa", i);
//            list.add(user);
//        }
//        System.out.println(list.size());
//        long start = System.currentTimeMillis();
//        System.out.println("开始时间： " + start);
//        userMapper.insertBach(list);
//        long end = System.currentTimeMillis();
//        System.out.println("结束时间：" + end);
//        System.out.println("花费时间：" + (end - start) / 1000 + "s");
        long start = System.currentTimeMillis();
        System.out.println("开始时间： " + start);
        List<User> users = userMapper.seletListUser();
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end);
        System.out.println("花费时间：" + (end - start) / 1000 + "s");
    }


}