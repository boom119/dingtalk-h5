package com.dingtalk.mapper;

import com.dingtalk.model.ProductCategory;
import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductCategoryMapper {

    ProductCategory selectById(String categoryId);
    List<ProductCategory> selectByIds(String categoryIds);
    List<ProductCategory> selectAll();
    List<ProductCategory> selectByParentId(String parentId);
    int insertProductCategory(ProductCategory productInfo);
    int updateProductCategory(ProductCategory productInfo);
    int deleteById(Integer categoryId);
}
