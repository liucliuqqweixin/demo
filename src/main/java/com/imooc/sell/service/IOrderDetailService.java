package com.imooc.sell.service;

import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.form.OrderDetailForm;
import com.imooc.sell.common.form.Page;
import com.imooc.sell.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
public interface IOrderDetailService extends IService<OrderDetail> {
    Result page(OrderDetailForm orderDetail, Page page);
}
