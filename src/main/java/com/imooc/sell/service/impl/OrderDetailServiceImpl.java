package com.imooc.sell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.common.form.OrderDetailForm;
import com.imooc.sell.common.form.Page;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.enums.ExceptionEnum;
import com.imooc.sell.mapper.OrderDetailMapper;
import com.imooc.sell.service.IOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.sell.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

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
            if(orderDetail.getProductPrice() != null){
                wrapper.le(OrderDetail::getProductPrice,orderDetail.getProductPrice());
            }
            if(orderDetail.getProductQuantity() != null){
                wrapper.le(OrderDetail::getProductQuantity,orderDetail.getProductQuantity());
            }
            if(orderDetail.getState() != null){
                wrapper.eq(OrderDetail::getState,orderDetail.getState());
            }
        }
        IPage<OrderDetail> iPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        iPage.setCurrent(page.getIndex());
        iPage.setSize(page.getSize());
        IPage<OrderDetail> detailIPage = orderDetailMapper.selectPage(iPage, wrapper);
        return ResultUtil.success(detailIPage);

    }
}
