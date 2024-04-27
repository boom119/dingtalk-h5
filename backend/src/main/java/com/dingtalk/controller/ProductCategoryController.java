/**
 * @author OpenTheDoor
 * @date 2024/4/17 23:42
 * @version 1.0
 */
package com.dingtalk.controller;

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

    @PostMapping("/getAll")
    public ApiResponse<List<ProductCategory>> getAll() {
        return ApiResponse.success(productCategoryService.getAll());
    }

    @PostMapping("/deleteById")
    public ApiResponse<Integer> deleteById(@RequestParam(name = "id") String id) {
        List result = productCategoryService.selectCategoryByParentId(id);
        if (result.size() > 0) {
            return  ApiResponse.error(504,"该类型有关联的子类型，不能直接删除");
        }
        return ApiResponse.success(productCategoryService.deleteById(id));
    }

    @PostMapping("/update")
    public ApiResponse<Integer> updateProductCategory(@RequestBody ProductCategory productCategory) {
        int result = productCategoryService.updateProductCategory(productCategory);
        if(result == -1){
            return  ApiResponse.error(506,"更新失败，该节点下已存在("+productCategory.getCategoryName()+")类型");
        }
        return ApiResponse.success( result);
    }

    @PostMapping("/insert")
    public ApiResponse<Integer> insertProductCategory(@RequestBody ProductCategory productCategory) {
        int result = productCategoryService.insertProductCategory(productCategory);
        if(result == -1){
            return  ApiResponse.error(506,"新增失败，该节点下已存在("+productCategory.getCategoryName()+")类型");
        }
        return ApiResponse.success(result);
    }


}
