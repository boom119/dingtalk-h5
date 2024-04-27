/**
 * @author OpenTheDoor
 * @date 2024/4/18 0:03
 * @version 1.0
 */
package com.dingtalk.mapper;

import com.dingtalk.model.File;
import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    List<File> selectByProductId(Integer productId);
    int insert(File file);
    int update(File file);
    int deleteByProductId(Integer id);

}