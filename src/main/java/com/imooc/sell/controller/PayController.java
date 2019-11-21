package com.imooc.sell.controller;

import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.service.IOrderService;
import com.imooc.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("pay")
public class PayController {
    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public String create(@RequestParam("orderId") String orderId, @RequestParam("returnUrl") String returnUrl, Model model) {
        OrderDTO orderDTO = iOrderService.findOne(orderId);
        if (orderDTO == null) {
            throw new ServiceException(ResultEnum.ORDER_NOT_EXIST);
        }
        PayResponse payResponse = payService.create(orderDTO);
        model.addAttribute("orderId",orderId);
        model.addAttribute("rs", payResponse);
        return "pay/create";
    }
    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public String notify(@RequestBody String notifyData) {
        payService.notify(notifyData);
        //返回给微信处理结果
        return "pay/success";
    }
}
