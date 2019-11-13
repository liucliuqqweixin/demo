package com.imooc.sell.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.form.ProductCategoryForm;
import com.imooc.sell.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
public interface IProductCategoryService extends IService<ProductCategory> {
    Result listPage(ProductCategoryForm productCategoryForm, Page page);
}
