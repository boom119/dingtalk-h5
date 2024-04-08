package com.dingtalk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 21:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    private String userId;
    private String roleId;
}
