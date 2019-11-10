package com.imooc.sell.controller;


import com.imooc.sell.common.VO.Result;
import com.imooc.sell.common.form.Page;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.entity.SellerInfo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 卖家信息表 前端控制器
 * </p>
 *
 * @author liuc
 * @since 2019-11-10
 */
@RestController
@RequestMapping("/seller-info")
public class SellerInfoController implements BaseController<SellerInfo,String>{

    @Override
    public Result list(SellerInfo sellerInfo, Page page) {
        return null;
    }

    @Override
    public Result insert(SellerInfo sellerInfo, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Result update(SellerInfo sellerInfo, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Result delete(String s) {
        return null;
    }
}
