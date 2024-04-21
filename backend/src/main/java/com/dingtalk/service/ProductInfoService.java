/**
 * @author OpenTheDoor
 * @date 2024/4/18 0:00
 * @version 1.0
 */
package com.dingtalk.service;

import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.github.pagehelper.PageInfo;

public interface ProductInfoService {

    PageInfo<ProductInfo> selectProductInfoByPage(ProductInfoQueryDTO queryDTO) ;
    ProductInfo selectById(Integer id);
    int insertProductInfo(ProductInfo productInfo);
    int updateProductInfo(ProductInfo productInfo);
    int deleteById(Integer id);

}
