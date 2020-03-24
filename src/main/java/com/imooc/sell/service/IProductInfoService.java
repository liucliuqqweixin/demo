package com.imooc.sell.service;

import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.entity.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
public interface IProductInfoService extends IService<ProductInfo> {
    void increaseStock(List<CartDTO> cartDTOList);

    void decreaseStock(List<CartDTO> cartDTOList);

    void onSale(String productId);

    void offSale(String productId);
}
