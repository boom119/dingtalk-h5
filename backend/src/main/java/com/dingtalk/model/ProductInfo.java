package com.dingtalk.model;



import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ProductInfo {

    private Integer productId;
    private Integer categoryId;
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
    private Timestamp updateTime;
    private String notes;
    private String mainImage;
    private String files;

    // Getters and setters for all properties
    // Ensure to generate them using your IDE or write them manually
}
