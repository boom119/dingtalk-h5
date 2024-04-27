package com.dingtalk.model;



import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductInfo {

    private Integer productId;
    private String categoryIds;
    private String productCode;
    private String productName;
    private String modelNumber;
    private BigDecimal voltage;
    private BigDecimal powerKw;
    private BigDecimal production;
    private String otherParameters;
    private String dimensionsMm;
    private BigDecimal weightKg;
    private String packagingSizeMm;
    private BigDecimal totalWeightKg;
    private BigDecimal dealerPrice;
    private BigDecimal distributionPrice;
    private BigDecimal factoryPrice;
    private LocalDateTime  createTime;
    private LocalDateTime updateTime;
    private String notes;
    private String mainImage;
    private String files;
//    private List<ProductCategory> productCategorieList;

}
