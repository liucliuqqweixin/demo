package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

public interface BuyerService {
    OrderDTO findOrderOne(String openid, String orderId);

    OrderDTO cancel(String openid, String orderId);
}
