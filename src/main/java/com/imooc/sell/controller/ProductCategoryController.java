package com.imooc.sell.controller;


import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.form.Page;
import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.entity.ProductCategory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@RestController
@RequestMapping("/product-category")
public class ProductCategoryController implements BaseController<ProductCategory,String>{

    @Override
    public Result list(ProductCategory productCategory, Page page) {
        return null;
    }

    @Override
    public Result insert(ProductCategory productCategory, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Result update(ProductCategory productCategory, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Result delete(String s) {
        return null;
    }
}
