package com.imooc.sell.service.impl;

import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.mapper.ProductInfoMapper;
import com.imooc.sell.service.IProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
