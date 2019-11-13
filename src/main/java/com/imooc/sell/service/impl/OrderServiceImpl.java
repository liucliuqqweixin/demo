package com.imooc.sell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.common.form.OrderDetailForm;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.ConstantEnum;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.mapper.OrderDetailMapper;
import com.imooc.sell.mapper.OrderMasterMapper;
import com.imooc.sell.mapper.ProductInfoMapper;
import com.imooc.sell.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.sell.service.IProductInfoService;
import com.imooc.sell.util.KeyUtil;
import com.imooc.sell.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private IProductInfoService iProductInfoService;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Override
    public Result page(OrderDetailForm orderDetail, Page page) {
        LambdaQueryWrapper<OrderDetail> wrapper = Wrappers.lambdaQuery();
        if (orderDetail != null) {
            if (StringUtils.isNotEmpty(orderDetail.getProductName())) {
                wrapper.like(OrderDetail::getProductName, orderDetail.getProductName());
            }
            if (orderDetail.getCreateTime() != null) {
                wrapper.ge(OrderDetail::getCreateTime, orderDetail.getCreateTime());
            }
            if (orderDetail.getProductPrice() != null) {
                wrapper.le(OrderDetail::getProductPrice, orderDetail.getProductPrice());
            }
            if (orderDetail.getProductQuantity() != null) {
                wrapper.le(OrderDetail::getProductQuantity, orderDetail.getProductQuantity());
            }

        }
        IPage<OrderDetail> iPage = new Page<>();
        iPage.setCurrent(page.getCurrent());
        iPage.setSize(page.getSize());
        IPage<OrderDetail> detailIPage = orderDetailMapper.selectPage(iPage, wrapper);
        return ResultUtil.success(detailIPage);

    }

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1. 查询商品（数量, 价格）
        if (orderDTO != null && orderDTO.getOrderDetailList().size() > ConstantEnum.ZERO.getCode()) {
            for (OrderDetail dto : orderDTO.getOrderDetailList()) {
                ProductInfo productInfo = productInfoMapper.selectById(dto.getProductId());
                if (productInfo == null) {
                    throw new ServiceException(ResultEnum.PRODUCT_NOT_EXIST);
                }
                //2. 计算订单总价
                orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(dto.getProductQuantity()))
                        .add(orderAmount);

                dto.setOrderId(orderId);
                dto.setDetailId(KeyUtil.genUniqueKey());
                BeanUtils.copyProperties(productInfo, dto);
                orderDetailList.add(dto);
            }
            //订单详情入库
            boolean flag = this.saveBatch(orderDetailList);
            if (!flag) {
                throw new ServiceException(ResultEnum.INSERT_ERROR);
            }

            //3.写入订单数据库（orderMaster和orderDetail）
            OrderMaster orderMaster = new OrderMaster();
            BeanUtils.copyProperties(orderDTO, orderMaster);
            orderMaster.setOrderId(orderId)
                    .setOrderAmount(orderAmount);


            int insertNum = orderMasterMapper.insert(orderMaster);
            if (insertNum != ConstantEnum.ONE.getCode()) {
                throw new ServiceException(ResultEnum.INSERT_ERROR);
            }
            //4.扣库存
            List<CartDTO> cartDTOS = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
            iProductInfoService.decreaseStock(cartDTOS);
        }

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderDTO orderDTO = orderMasterMapper.selectOrderDTOById(orderId);
        if (orderDTO == null) {
            throw new ServiceException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, com.baomidou.mybatisplus.extension.plugins.pagination.Page page) {
        Page<OrderDTO> orderDTOList = orderMasterMapper.selectOrderDTOList(page, buyerOpenid);
        return orderDTOList;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        checkOrderDTOAndUpdate(orderDTO, OrderStatusEnum.CANCEL);
        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            throw new ServiceException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOS = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        iProductInfoService.increaseStock(cartDTOS);
        //如果已经支付 需要退款
        if (orderDTO.getOrderStatus().equals(PayStatusEnum.SUCCESS.getValue())) {
            //TODO
        }
        return orderDTO;
    }

    private OrderDTO checkOrderDTOAndUpdate(OrderDTO orderDTO, OrderStatusEnum orderStatusEnum) {
        //判断订单状态
        checkOrderStatus(orderDTO);
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(orderStatusEnum);
        int num = orderMasterMapper.updateById(orderMaster);
        if (num < ConstantEnum.ONE.getCode()) {
            throw new ServiceException(ResultEnum.UPDATE_ERROR);
        }
        return orderDTO;
    }

    private void checkOrderStatus(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getValue())) {
            throw new ServiceException(ResultEnum.ORDER_STATUS_ERROR);
        }
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        orderDTO = checkOrderDTOAndUpdate(orderDTO, OrderStatusEnum.FINISHED);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        checkOrderStatus(orderDTO);
        //判断支付状态
        checkPayStatus(orderDTO);
        //修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS);
        int num = orderMasterMapper.updateById(orderMaster);
        if (num < ConstantEnum.ONE.getCode()) {
            throw new ServiceException(ResultEnum.UPDATE_ERROR);
        }
        return orderDTO;
    }

    private void checkPayStatus(OrderDTO orderDTO) {
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getValue())) {
            throw new ServiceException(ResultEnum.PAY_STATUS_ERROR);
        }
    }
}
