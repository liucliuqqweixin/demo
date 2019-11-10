package com.imooc.sell.service.impl;

import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.mapper.OrderMasterMapper;
import com.imooc.sell.service.IOrderMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单主表 服务实现类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements IOrderMasterService {

}
