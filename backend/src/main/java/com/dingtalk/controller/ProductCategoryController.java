/**
 * @author OpenTheDoor
 * @date 2024/4/17 23:42
 * @version 1.0
 */
package com.dingtalk.controller;

import com.dingtalk.model.ProductCategory;
import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.dingtalk.service.ProductCategoryService;
import com.dingtalk.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/getByParentId")
    public List<ProductCategory> getByParentId(@RequestParam String parentId) {
        return productCategoryService.selectCategoryByParentId(parentId);
    }

    @PostMapping("/getById")
    public ProductCategory getById(@RequestParam String id) {
        return productCategoryService.selectCategoryById(id);
    }
}
