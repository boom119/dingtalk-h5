/**
 * @author OpenTheDoor
 * @date 2024/4/18 0:01
 * @version 1.0
 */
package com.dingtalk.service.impl;

import com.dingtalk.mapper.FileMapper;
import com.dingtalk.mapper.ProductCategoryMapper;
import com.dingtalk.mapper.ProductInfoMapper;
import com.dingtalk.model.File;
import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.dingtalk.service.ProductInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class ProductInfoServiceImpl implements ProductInfoService {
    private final ProductInfoMapper productInfoMapper;
    private final ProductCategoryMapper categoryMapper;
    private final FileMapper fileMapper;

    public ProductInfoServiceImpl(ProductInfoMapper productInfoMapper, ProductCategoryMapper categoryMapper, FileMapper fileMapper) {
        this.productInfoMapper = productInfoMapper;
        this.categoryMapper = categoryMapper;
        this.fileMapper = fileMapper;
    }

    public PageInfo<ProductInfo> selectProductInfoByPage(ProductInfoQueryDTO queryDTO) {
        // 调用 Mapper 方法获取查询及结果
        Page<ProductInfo> productInfoPageInfo = productInfoMapper.selectProductInfoByPage(queryDTO);
        List<ProductInfo> productInfoList = productInfoPageInfo.getResult();
        // 返回分页对象，需确保PageInfo中可以接受List<ProductInfo>作为参数
        return new PageInfo<>(productInfoList);
    }

    @Override
    public ProductInfo selectById(Integer productId) {

        ProductInfo productInfo = productInfoMapper.selectById(productId);
        List<File> fileList = fileMapper.selectByProductId(productId);
        productInfo.setFiles(fileList);
        return productInfo;
    }

    @Override
    public void insertProductInfo(ProductInfo productInfo) {
        productInfo.setCreateTime(LocalDateTime.now());
        productInfo.setUpdateTime(LocalDateTime.now());
        productInfoMapper.insertProductInfo(productInfo);
        // 获取产品id
        Integer productId = productInfo.getProductId(); // 获取自增主键值
        for (File file : productInfo.getFiles()) {
            file.setProductId(productId);
            fileMapper.insert(file);
        }
    }

    @Override
    public void updateProductInfo(ProductInfo productInfo) {
        productInfo.setUpdateTime(LocalDateTime.now());
        productInfoMapper.updateProductInfo(productInfo);
        //删除历史关联的文件
        fileMapper.deleteByProductId(productInfo.getProductId());
        //插入新关联的文件
        for (File file : productInfo.getFiles()) {
            file.setProductId(productInfo.getProductId());
            fileMapper.insert(file);
        }
    }

    @Override
    public void deleteById(List<Integer> idList) {
        productInfoMapper.deleteByIds(idList);
        for (Integer id : idList) {
            fileMapper.deleteByProductId(id);
        }
    }


}
