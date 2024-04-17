/**
 * @author OpenTheDoor
 * @date 2024/4/17 23:41
 * @version 1.0
 */
package com.dingtalk.model.dto;

import cn.hutool.db.Page;
import lombok.Data;

@Data
public class ProductInfoQueryDTO {
    private String productCode;
    private String productName;
    private Page page;
//    private String category;
}
