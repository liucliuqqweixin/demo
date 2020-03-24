package com.imooc.sell.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.form.OrderDetailForm;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.sell.entity.SellerInfo;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
public interface IOrderService extends IService<OrderDetail>{

    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     *
     * @param buyerOpenid
     * @param page
     * @return
     */
    Page<OrderDTO> findList(String buyerOpenid, Page page);

    /**
     * 取消订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 完结订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO paid(OrderDTO orderDTO);
}
