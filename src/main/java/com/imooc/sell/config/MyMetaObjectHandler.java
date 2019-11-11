package com.imooc.sell.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean flag = metaObject.hasSetter("createTime");
        if (flag) {
            System.out.println("insert执行了 .....");
            setInsertFieldValByName("createTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        boolean flag = metaObject.hasSetter("updateTime");
        if (flag) {
            System.out.println("update执行了 .....");
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }
}
