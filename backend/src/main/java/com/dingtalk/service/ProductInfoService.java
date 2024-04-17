/**
 * @author OpenTheDoor
 * @date 2024/4/18 0:00
 * @version 1.0
 */
package com.dingtalk.service;

import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface ProductInfoService {

    PageInfo<ProductInfo> selectProductInfoByPage(ProductInfoQueryDTO queryDTO) ;

}
