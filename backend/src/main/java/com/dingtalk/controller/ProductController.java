/**
 * @author OpenTheDoor
 * @date 2024/4/17 23:42
 * @version 1.0
 */
package com.dingtalk.controller;

import cn.hutool.core.util.ObjectUtil;
import com.dingtalk.model.ProductInfo;
import com.dingtalk.model.dto.ProductInfoQueryDTO;
import com.dingtalk.service.ProductInfoService;
import com.dingtalk.util.ApiResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductInfoService productInfoService;

    public ProductController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @PostMapping("/page")
    public ApiResponse<PageInfo<ProductInfo>> getProductInfos(@RequestBody ProductInfoQueryDTO queryDTO) {
        // 使用 PageHelper 开启分页
        PageHelper.startPage(queryDTO.getPage().getPageNumber(), queryDTO.getPage().getPageSize());
        return ApiResponse.success(productInfoService.selectProductInfoByPage(queryDTO));
    }

    @PostMapping("/selectById")
    public ApiResponse<ProductInfo> selectById(@RequestParam(name = "id")  Integer id) {
        return ApiResponse.success(productInfoService.selectById(id));
    }

    @PostMapping("/insertProductInfo")
    public ApiResponse<Integer> insertProductInfo(@RequestBody ProductInfo productInfo) {
        if(checkProduct(productInfo)){
            return  ApiResponse.error(500,"产品名称或产品编号重复");
        }
        return  ApiResponse.success(productInfoService.insertProductInfo(productInfo));
    }

    @PostMapping("/updateProductInfo")
    public ApiResponse<Integer> updateProductInfo(@RequestBody ProductInfo productInfo) {
        if(checkProduct(productInfo)){
            return  ApiResponse.error(501,"产品名称或产品编号重复");
        }
        return  ApiResponse.success(productInfoService.updateProductInfo(productInfo));
    }

    @PostMapping("/deleteById")
    public ApiResponse<Object> deleteById(@RequestParam(name = "id") Integer id) {
        ProductInfo productInfo = productInfoService.selectById(id);
        if(ObjectUtil.isEmpty(productInfo)){
            return  ApiResponse.error(502,"产品id不存在");
        }
        return ApiResponse.success(productInfoService.deleteById(id));
    }

    private boolean checkProduct(ProductInfo productInfo){
        ProductInfoQueryDTO queryDTO = new ProductInfoQueryDTO();
        queryDTO.setProductCode(productInfo.getProductCode());
        queryDTO.setProductName(productInfo.getProductName());
        PageInfo<ProductInfo> productInfoPageInfo = productInfoService.selectProductInfoByPage(queryDTO);
        if(!productInfoPageInfo.getList().isEmpty()){
            if(!productInfoPageInfo.getList().get(0).getProductId().equals(productInfo.getProductId())){
                return true;
            }
        }
        return false;
    }
}
