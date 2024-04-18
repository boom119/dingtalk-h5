package com.dingtalk.service.impl;

import com.dingtalk.mapper.ProductCategoryMapper;
import com.dingtalk.model.ProductCategory;
import com.dingtalk.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryMapper categoryMapper;

    public ProductCategoryServiceImpl(ProductCategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<ProductCategory> selectCategoryByParentId(String parentId) {
        return categoryMapper.selectByParentId(parentId);
    }

    @Override
    public ProductCategory selectCategoryById(String id) {
        return categoryMapper.selectById(id);
    }
}
