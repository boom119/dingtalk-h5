/**
 * @author OpenTheDoor
 * @date 2024/4/18 0:01
 * @version 1.0
 */
package com.dingtalk.service.impl;

import com.dingtalk.mapper.ProductInfoMapper;
import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.dingtalk.service.ProductInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    public PageInfo<ProductInfo> selectProductInfoByPage(ProductInfoQueryDTO queryDTO) {

        // 调用 Mapper 方法获取查询及结果
        Page<ProductInfo> productInfos = productInfoMapper.selectProductInfoByPage(queryDTO);
        // 返回分页对象
        return new PageInfo<>(productInfos);
    }
}
