/**
 * @author OpenTheDoor
 * @date 2024/4/17 23:42
 * @version 1.0
 */
package com.dingtalk.controller;

import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.dingtalk.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductInfoService productInfoService;

    public ProductController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @PostMapping("/page")
    public PageInfo<ProductInfo> getProductInfos(@RequestBody ProductInfoQueryDTO queryDTO) {
        // 使用 PageHelper 开启分页
        PageHelper.startPage(queryDTO.getPage().getPageNumber(), queryDTO.getPage().getPageSize());
        return productInfoService.selectProductInfoByPage(queryDTO);
    }
}
