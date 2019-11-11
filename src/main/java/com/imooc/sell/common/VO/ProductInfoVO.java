package com.imooc.sell.common.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductInfoVO {
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private String icon;
}
