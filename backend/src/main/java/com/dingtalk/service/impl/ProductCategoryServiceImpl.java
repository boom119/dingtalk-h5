package com.dingtalk.service.impl;

import com.dingtalk.mapper.ProductCategoryMapper;
import com.dingtalk.model.ProductCategory;
import com.dingtalk.service.ProductCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
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

    @Override
    public List<ProductCategory> getAll() {
        // 从数据库获取所有类别
        List<ProductCategory> allCategories = categoryMapper.selectAll();

        // 使用哈希映射存储所有类别，以类别ID作为键
        Map<String, ProductCategory> categoryMap = new HashMap<>();
        for (ProductCategory category : allCategories) {
            category.setChildren(new ArrayList<>());
            categoryMap.put(category.getCategoryId(), category);
        }

        // 创建一个列表来储存根类别
        List<ProductCategory> rootCategories = new ArrayList<>();

        // 遍历所有类别，构建树形结构
        for (ProductCategory category : allCategories) {
            String parentId = category.getParentId();
            if (parentId == null || StringUtils.isEmpty(parentId)) {
                setCategoryPath(category,category.getCategoryId());
                // `parentId`为`null`表示当前类别是根类别
                rootCategories.add(category);
                category.setPath(category.getCategoryId());
            } else {
                // 找到当前类别的父类别，并将当前类别加到父类别的子列表中
                ProductCategory parentCategory = categoryMap.get(parentId);
                if (parentCategory != null) {
                    // 确保父类别的子列表已经被初始化
                    parentCategory.getChildren().add(category);
                    // 子类别的路径是父类别路径加上子类别的ID，并以逗号分隔
                    String childPath = parentCategory.getPath() + "," + category.getCategoryId();
                    category.setPath(childPath);
                }
            }
        }

        return rootCategories;
    }

    @Override
    public int insertProductCategory(ProductCategory productInfo) {
        //检查当前节点是否已经存在相同的类型,已存在则不让插入
        if(checkProductCategory(productInfo)){
            return -1;
        }
        return categoryMapper.insertProductCategory(productInfo);
    }

    @Override
    public int updateProductCategory(ProductCategory productInfo) {
        //检查当前节点是否已经存在相同的类型,已存在则不让更新
        if(checkProductCategory(productInfo)){
            return -1;
        }
        return categoryMapper.updateProductCategory(productInfo);
    }

    @Override
    public int deleteById(String categoryId) {
        //删除前先查询是否存在子类型
        List<ProductCategory> productCategories = categoryMapper.selectByParentId(categoryId);
        if (!productCategories.isEmpty() && productCategories.size()>0){
            return categoryMapper.deleteById(categoryId);
        }
        return 0;
    }
    private boolean checkProductCategory(ProductCategory productCategory){
        List<ProductCategory> productCategories = categoryMapper.selectByNameAndParent(productCategory);
        if(!productCategories.isEmpty()){
            for(ProductCategory p : productCategories){
                if(!p.getCategoryId().equals(productCategory.getCategoryId())){
                    return true;
                }
            }
        }
        return false;
    }

    private void setCategoryPath(ProductCategory category, String path) {
        // 设置当前类别的路径
        category.setPath(path);

        // 遍历子类别并递归设置它们的路径
        for (ProductCategory child : category.getChildren()) {
            // 子类别的路径是父类别路径加上子类别的ID，并以逗号分隔
            String childPath = path + "," + child.getCategoryId();
            child.setPath(childPath);
            setCategoryPath(child, childPath);
        }
    }
}
