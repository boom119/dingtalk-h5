/**
 * @author OpenTheDoor
 * @date 2024/4/18 0:01
 * @version 1.0
 */
package com.dingtalk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.dingtalk.mapper.ProductCategoryMapper;
import com.dingtalk.mapper.ProductInfoMapper;
import com.dingtalk.model.ProductCategory;
import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.dingtalk.service.ProductInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    private final ProductInfoMapper productInfoMapper;
    private final ProductCategoryMapper categoryMapper;

    public ProductInfoServiceImpl(ProductInfoMapper productInfoMapper, ProductCategoryMapper categoryMapper) {
        this.productInfoMapper = productInfoMapper;
        this.categoryMapper = categoryMapper;
    }

    public PageInfo<ProductInfo> selectProductInfoByPage(ProductInfoQueryDTO queryDTO) {
        // 调用 Mapper 方法获取查询及结果
        Page<ProductInfo> productInfoPageInfo = productInfoMapper.selectProductInfoByPage(queryDTO);
        List<ProductInfo> productInfoList = productInfoPageInfo.getResult();
        /*if (!ObjectUtil.hasNull(queryDTO.getCategoryIds())) {
            Set<String> queryCategoryIdsSet = new HashSet<>(queryDTO.getCategoryIds());
            // 筛选出包含参数中任一categoryId的ProductInfo列表
            productInfoList = productInfoList.stream()
                    .filter(productInfo -> {
                        Set<String> productCategoryIdsSet = Arrays.stream(productInfo.getCategoryIds().split(","))
                                .collect(Collectors.toSet());
                        // 检查productCategoryIdsSet中是否包含queryCategoryIdsSet中的任意一个ID
                        return productCategoryIdsSet.stream().anyMatch(queryCategoryIdsSet::contains);
                    })
                    .collect(Collectors.toList());
        }
        productInfoList = productUpdateCategory(productInfoList);*/

        // 返回分页对象，需确保PageInfo中可以接受List<ProductInfo>作为参数
        return new PageInfo<>(productInfoList);
    }

    @Override
    public ProductInfo selectById(Integer id) {

        ProductInfo productInfo = productInfoMapper.selectById(id);
//        if(!ObjectUtil.isEmpty(productInfo)){
//            productInfo = productUpdateCategory(productInfo);
//        }
        return productInfo;
    }

    @Override
    public int insertProductInfo(ProductInfo productInfo) {
        productInfo.setCreateTime(LocalDateTime.now());
        productInfo.setUpdateTime(LocalDateTime.now());
        return productInfoMapper.insertProductInfo(productInfo);
    }

    @Override
    public int updateProductInfo(ProductInfo productInfo) {
        productInfo.setUpdateTime(LocalDateTime.now());
        return productInfoMapper.updateProductInfo(productInfo);
    }

    @Override
    public int deleteById(List<Integer> idList) {
        return productInfoMapper.deleteByIds(idList);
    }

    /*private List<ProductInfo> productUpdateCategory(List<ProductInfo> productInfoList) {
        // 所有产品的类别ID集合
        List<ProductCategory> categoryList = categoryMapper.selectAll();

        // 将类别列表转换为类别ID到ProductCategory对象的映射，以优化查找性能
        Map<String, ProductCategory> categoryMap = categoryList.stream()
                .collect(Collectors.toMap(ProductCategory::getCategoryId, Function.identity()));

        // 遍历每个产品信息，设置产品类别
        productInfoList.forEach(productInfo -> {
            List<ProductCategory> productCategories = Arrays.stream(productInfo.getCategoryIds().split(","))
                    .map(categoryMap::get) // 这里利用了之前构建的Id到Category的映射
                    .filter(Objects::nonNull) // 保证不存在的ID不会添加至列表
                    .collect(Collectors.toList());
            productInfo.setProductCategorieList(productCategories);
        });
        return productInfoList;
    }*/

   /* private ProductInfo productUpdateCategory(ProductInfo productInfo) {
        // 所有产品的类别ID集合
        List<ProductCategory> categoryList = categoryMapper.selectAll();

        // 将类别列表转换为类别ID到ProductCategory对象的映射，以优化查找性能
        Map<String, ProductCategory> categoryMap = categoryList.stream()
                .collect(Collectors.toMap(ProductCategory::getCategoryId, Function.identity()));


        List<ProductCategory> productCategories = Arrays.stream(productInfo.getCategoryIds().split(","))
                .map(categoryMap::get) // 这里利用了之前构建的Id到Category的映射
                .filter(Objects::nonNull) // 保证不存在的ID不会添加至列表
                .collect(Collectors.toList());
        productInfo.setProductCategorieList(productCategories);

        return productInfo;
    }*/


}
