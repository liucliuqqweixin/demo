package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

public interface PushMessage {
    void orderStatus(OrderDTO orderDTO);
}
