package com.imooc.sell.controller;

import com.imooc.sell.common.exception.ServiceException;
import com.imooc.sell.common.form.CategoryForm;
import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.service.IProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家类目
 * Created by 廖师兄
 * 2017-07-23 21:06
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private IProductCategoryService categoryService;

    /**
     * 类目列表
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        List<ProductCategory> categoryList = categoryService.list();
        model.addAttribute("categoryList", categoryList);
        return "category/list";
    }

    /**
     * 展示
     *
     * @param categoryId
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                        Model model) {
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.getById(categoryId);
            model.addAttribute("category", productCategory);
        }

        return "category/index";
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
    public String save(@Valid CategoryForm form,
                       BindingResult bindingResult,
                       Model model) {
        ProductCategory productCategory = new ProductCategory();
        model.addAttribute("url", "/sell/seller/category/list");
        if (form.getCategoryId() != null) {
            productCategory = categoryService.getById(form.getCategoryId());
            BeanUtils.copyProperties(form, productCategory);
            categoryService.updateById(productCategory);
            return "common/success";
        }
        BeanUtils.copyProperties(form, productCategory);
        categoryService.save(productCategory);
        return "common/success";
    }
}