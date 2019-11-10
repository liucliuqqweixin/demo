package com.imooc.sell.controller;


import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.form.Page;
import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.entity.ProductInfo;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/product-info")
public class ProductInfoController implements BaseController<ProductInfo,String>{

    @Override
    public Result list(ProductInfo productInfo, Page page) {
        return null;
    }

    @Override
    public Result insert(ProductInfo productInfo, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Result update(ProductInfo productInfo, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Result delete(String s) {
        return null;
    }
}
