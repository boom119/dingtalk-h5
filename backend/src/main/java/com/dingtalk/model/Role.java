package com.dingtalk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private String roleId;
    private String roleName;
    private String roleGroupId;
    private String roleGroupName;
}
