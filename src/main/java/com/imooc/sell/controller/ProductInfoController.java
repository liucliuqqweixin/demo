package com.imooc.sell.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.form.ProductCategoryForm;
import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@RestController
@RequestMapping("/buyer")
public class ProductInfoController {
    @Autowired
    private IProductCategoryService iProductCategoryService;


    @GetMapping("product/list")
    public Result getList(ProductCategoryForm form, Page page) {
        return iProductCategoryService.listPage(form, page);
    }

}
