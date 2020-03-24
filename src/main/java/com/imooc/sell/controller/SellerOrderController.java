package com.imooc.sell.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.OrderDetailExcel;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.entity.SellerInfo;
import com.imooc.sell.enums.ConstantEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.service.IOrderService;
import com.imooc.sell.util.ExcelUtil;
import com.imooc.sell.util.ResponseUtil;
import com.imooc.sell.util.ResultUtil;
import com.lly835.bestpay.rest.type.Get;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 卖家信息表 前端控制器
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private IOrderService iOrderService;

    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Model model) {
        Page page1 = new Page();
        page1.setCurrent(page);
        page1.setSize(size);
        Page<OrderDTO> orderDTOPage = iOrderService.findList(null, page1);
        model.addAttribute("dtoPage", orderDTOPage);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("pageT", orderDTOPage.getTotal() % size == 0 ? orderDTOPage.getTotal() / size : orderDTOPage.getTotal() / size + ConstantEnum.ONE.getCode());
        return "sys/list";
    }

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public String cancel(@RequestParam("orderId") String orderId,
                         Model model) {
        try {
            OrderDTO orderDTO = iOrderService.findOne(orderId);
            iOrderService.cancel(orderDTO);
        } catch (ServiceException e) {
            log.error("【卖家端取消订单】发生异常{}", e);
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/order/list");
            return "common/error";
        }
        model.addAttribute("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        model.addAttribute("url", "/sell/seller/order/list");
        return "common/success";
    }

    /**
     * 订单详情
     *
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("orderId") String orderId,
                         Model model) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = iOrderService.findOne(orderId);
        } catch (ServiceException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e);
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/order/list");
            return "common/error";
        }

        model.addAttribute("orderDTO", orderDTO);
        return "sys/detail";
    }

    /**
     * 完结订单
     *
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("/finish")
    public String finished(@RequestParam("orderId") String orderId,
                           Model model) {
        try {
            OrderDTO orderDTO = iOrderService.findOne(orderId);
            iOrderService.finish(orderDTO);
        } catch (ServiceException e) {
            log.error("【卖家端完结订单】发生异常{}", e);
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/order/list");
            return "common/error";
        }

        model.addAttribute("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        model.addAttribute("url", "/sell/seller/order/list");
        return "common/success";
    }

    @GetMapping("/eportExcel")
    @ResponseBody
    public void eportExcel(HttpServletResponse response) throws IOException {
        ServletOutputStream servletOutputStream = ResponseUtil.getServletOutputStream(response);
        List<OrderDetail> orderDetails = iOrderService.list();
        List<OrderDetailExcel> orderDetailExcels = new ArrayList<>();
        orderDetails.forEach(o -> {
            OrderDetailExcel orderDetailExcel = new OrderDetailExcel();
            BeanUtils.copyProperties(o, orderDetailExcel);
            orderDetailExcels.add(orderDetailExcel);
        });
        ExcelUtil.writeExcelWithModel(servletOutputStream, orderDetailExcels, OrderDetailExcel.class, ExcelTypeEnum.XLSX);
    }

    @PostMapping("/readNoEntity")
    @ResponseBody
    public Result noEntity(@RequestParam("file") MultipartFile excelFile) throws IOException {
        List<OrderDetailExcel> orderDetailExcels = ExcelUtil.readExcelWithModel(excelFile.getInputStream(), OrderDetailExcel.class, ExcelTypeEnum.XLSX);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetailExcels.forEach(o -> {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(o, orderDetail);
            orderDetails.add(orderDetail);
        });
        iOrderService.saveBatch(orderDetails);
        return ResultUtil.success();
    }
}
