package com.dingtalk.model;

import lombok.Data;

@Data
public class ProductCategory {
    private String categoryId;
    private String categoryName;
    private String parentId;
    private String level;
}
