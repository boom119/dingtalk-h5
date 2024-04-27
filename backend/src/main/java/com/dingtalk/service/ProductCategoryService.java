package com.dingtalk.service;

import com.dingtalk.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 根据parentId查询所有的子类型
     */
     List<ProductCategory> selectCategoryByParentId(String parentId);

    /**
     * 根据Id查询类型
     */
     ProductCategory selectCategoryById(String Id);

    List<ProductCategory> getAll();

    int insertProductCategory(ProductCategory productInfo);
    int updateProductCategory(ProductCategory productInfo);
    int deleteById(String categoryId);
}
