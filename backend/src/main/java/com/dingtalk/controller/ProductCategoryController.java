/**
 * @author OpenTheDoor
 * @date 2024/4/17 23:42
 * @version 1.0
 */
package com.dingtalk.controller;

import cn.hutool.core.util.ObjectUtil;
import com.dingtalk.model.ProductCategory;
import com.dingtalk.service.ProductCategoryService;
import com.dingtalk.util.ApiResponse;
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
    public ApiResponse<List<ProductCategory>> getByParentId(@RequestParam(name = "parentId") String parentId) {
        return ApiResponse.success(productCategoryService.selectCategoryByParentId(parentId));
    }

    @PostMapping("/getById")
    public ApiResponse<ProductCategory> getById(@RequestParam(name = "id") String id) {
        return ApiResponse.success(productCategoryService.selectCategoryById(id));
    }
}
