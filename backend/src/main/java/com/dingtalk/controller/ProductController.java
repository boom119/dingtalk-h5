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
import com.dingtalk.util.ExcelExporter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ApiResponse<Object> insertProductInfo(@RequestBody ProductInfo productInfo) {
        if(checkProduct(productInfo)){
            return  ApiResponse.error(500,"产品名称或产品编号重复");
        }
        productInfoService.insertProductInfo(productInfo);
        return  ApiResponse.success("ok");
    }

    @PostMapping("/updateProductInfo")
    public ApiResponse<Object> updateProductInfo(@RequestBody ProductInfo productInfo) {
        if(checkProduct(productInfo)){
            return  ApiResponse.error(501,"产品名称或产品编号重复");
        }
        productInfoService.updateProductInfo(productInfo);
        return  ApiResponse.success("ok");
    }

    @PostMapping("/deleteByIds")
    public ApiResponse<Object> deleteByIds(@RequestBody Map<String, String> obj) {
        String idsCommaSeparated = obj.get("ids");
        List<Integer> idList = Arrays.stream(idsCommaSeparated.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        ProductInfo productInfo = new ProductInfo();
        for(Integer id : idList){
            productInfo =  productInfoService.selectById(id);
            if(ObjectUtil.isEmpty(productInfo)){
                return  ApiResponse.error(502,"id为："+id+" 的产品不存在");
            }
        }
        productInfoService.deleteById(idList);
        return ApiResponse.success("ok");
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

    @GetMapping("/export")
    public void exportById(@RequestParam(name = "id") Integer id, HttpServletResponse response) {
        ProductInfo productInfo = productInfoService.selectById(id);
        List<ProductInfo> productInfoList = new ArrayList<>();
        productInfoList.add(productInfo);
        try {
            ExcelExporter.exportProducts(productInfoList,"test.xlsx",response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
