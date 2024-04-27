/**
 * @author OpenTheDoor
 * @date 2024/4/18 0:00
 * @version 1.0
 */
package com.dingtalk.service;

import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {

    PageInfo<ProductInfo> selectProductInfoByPage(ProductInfoQueryDTO queryDTO) ;
    ProductInfo selectById(Integer id);
    void insertProductInfo(ProductInfo productInfo);
    void updateProductInfo(ProductInfo productInfo);
    void deleteById(List<Integer> idList);

}
