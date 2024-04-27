/**
 * @author OpenTheDoor
 * @date 2024/4/18 0:03
 * @version 1.0
 */
package com.dingtalk.mapper;

import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface ProductInfoMapper {

    Page<ProductInfo> selectProductInfoByPage(ProductInfoQueryDTO productInfoQueryDTO);
    ProductInfo selectById(Integer id);
    int insertProductInfo(ProductInfo productInfo);
    int updateProductInfo(ProductInfo productInfo);
    int deleteByIds(List<Integer> idList);

}