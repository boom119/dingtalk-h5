package com.dingtalk.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategory {
    private String categoryId;
    private String categoryName;
    private String parentId;
    private String path;


    private List<ProductCategory> children;
}
