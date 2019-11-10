package com.imooc.sell.service.impl;

import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.mapper.ProductCategoryMapper;
import com.imooc.sell.service.IProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

}
