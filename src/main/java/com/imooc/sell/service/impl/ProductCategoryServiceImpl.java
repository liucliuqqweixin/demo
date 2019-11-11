package com.imooc.sell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.form.Page;
import com.imooc.sell.common.form.ProductCategoryForm;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.mapper.ProductCategoryMapper;
import com.imooc.sell.service.IProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.sell.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public Result listPage(ProductCategoryForm productCategoryForm, Page page) {
        LambdaQueryWrapper<ProductCategory> wrapper = Wrappers.lambdaQuery();
        if (productCategoryForm != null) {
            if (StringUtils.isNotEmpty(productCategoryForm.getName())) {
                wrapper.like(ProductCategory::getCategoryName, productCategoryForm.getName());
            }
            if (productCategoryForm.getType() != null) {
                wrapper.eq(ProductCategory::getCategoryType, productCategoryForm.getType());
            }
        }
        IPage<ProductCategory> iPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        iPage.setCurrent(page.getIndex());
        iPage.setSize(page.getSize());
        IPage<ProductCategory> categoryIPage = productCategoryMapper.selectPage(iPage, wrapper);
        return ResultUtil.success(categoryIPage);
    }
}
