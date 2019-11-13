package com.imooc.sell.mapper;

import com.imooc.sell.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    Integer insertBatch(@Param("orderDetailList") List<OrderDetail> orderDetailList);
}
