package com.imooc.sell.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.common.form.ProductForm;
import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.service.IProductCategoryService;
import com.imooc.sell.service.IProductInfoService;
import com.imooc.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 * Created by 廖师兄
 * 2017-07-23 15:12
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private IProductInfoService productService;

    @Autowired
    private IProductCategoryService categoryService;

    /**
     * 列表
     *
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Model model) {
        Page<ProductInfo> page1 = new Page();
        page1.setCurrent(page);
        page1.setSize(size);
        IPage<ProductInfo> productInfoPage = productService.page(page1);
        model.addAttribute("productInfoPage", productInfoPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "product/list";
    }

    /**
     * 商品上架
     *
     * @param productId
     * @param model
     * @return
     */
    @RequestMapping("/on_sale")
    public String onSale(@RequestParam("productId") String productId,
                         Model model) {
        try {
            productService.onSale(productId);
        } catch (ServiceException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/product/list");
            return "common/error";
        }

        model.addAttribute("url", "/sell/seller/product/list");
        return "common/success";
    }

    /**
     * 商品下架
     *
     * @param productId
     * @param model
     * @return
     */
    @RequestMapping("/off_sale")
    public String offSale(@RequestParam("productId") String productId,
                          Model model) {
        try {
            productService.offSale(productId);
        } catch (ServiceException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/product/list");
            return "common/error";
        }

        model.addAttribute("url", "/sell/seller/product/list");
        return "common/success";
    }

    @GetMapping("/index")
    public String index(@RequestParam(value = "productId", required = false) String productId,
                        Model model) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.getById(productId);
            model.addAttribute("productInfo", productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.list();
        model.addAttribute("categoryList", categoryList);

        return "product/index";
    }

    /**
     * 保存/更新
     *
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/save")
    @CacheEvict(cacheNames = "product", allEntries = true, beforeInvocation = true)
    public String save(@Validated ProductForm form,
                       BindingResult bindingResult,
                       Model model) {
        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.getById(form.getProductId());
                BeanUtils.copyProperties(form, productInfo);
                productService.updateById(productInfo);
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
                BeanUtils.copyProperties(form, productInfo);
                productService.save(productInfo);
            }

        } catch (ServiceException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/sell/seller/product/index");
            return "common/error";
        }

        model.addAttribute("url", "/sell/seller/product/list");
        return "common/success";
    }
}