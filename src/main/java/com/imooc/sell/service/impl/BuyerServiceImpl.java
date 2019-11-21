package com.imooc.sell.service.impl;

import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private IOrderService iOrderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = getOrderDTO(openid, orderId);
        if (orderDTO == null) return null;
        return orderDTO;
    }

    private OrderDTO getOrderDTO(String openid, String orderId) {
        OrderDTO orderDTO = iOrderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.info("【查询订单】订单的openid不一致,openid:{},orderDTO={}", openid, orderDTO);
            throw new ServiceException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO cancel(String openid, String orderId) {
        OrderDTO orderDTO = getOrderDTO(openid, orderId);
        if (orderDTO == null) {
            log.info("【取消订单】查询不到订单，orderId:{}", orderId);
            throw new ServiceException(ResultEnum.ORDER_NOT_EXIST);
        }
        return iOrderService.cancel(orderDTO);
    }
}
