package com.imooc.sell.service.impl;

import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.ConstantEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.mapper.ProductInfoMapper;
import com.imooc.sell.service.IProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements IProductInfoService {

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        cartDTOList.forEach(cartDTO -> {
            ProductInfo productInfo = checkProductInfoById(cartDTO.getProductId());
            Integer result = cartDTO.getProductQuantity() + productInfo.getProductStock();
            productInfo.setProductStock(result);
            boolean flag = this.updateById(productInfo);
            if (!flag) {
                throw new ServiceException(ResultEnum.UPDATE_ERROR);
            }
        });
    }

    /**
     * 根据id检查是否存在
     *
     * @param productId
     * @return
     */
    private ProductInfo checkProductInfoById(String productId) {
        ProductInfo productInfo = this.getById(productId);
        if (productInfo == null) {
            throw new ServiceException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return productInfo;
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        cartDTOList.forEach(cartDTO -> {
            ProductInfo productInfo = checkProductInfoById(cartDTO.getProductId());
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < ConstantEnum.ZERO.getCode()) {
                throw new ServiceException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            boolean flag = this.updateById(productInfo);
            if (!flag) {
                throw new ServiceException(ResultEnum.UPDATE_ERROR);
            }
        });
    }
}
