package com.imooc.sell.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderMaster;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单主表 Mapper 接口
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
public interface OrderMasterMapper extends BaseMapper<OrderMaster> {
    OrderDTO selectOrderDTOById(String id);

    Page<OrderDTO> selectOrderDTOList(@Param("page") Page page, @Param("buyerOpenid") String buyerOpenid);
}
