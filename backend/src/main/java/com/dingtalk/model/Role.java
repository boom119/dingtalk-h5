package com.dingtalk.model;

import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Role {
    private String roleId;
    private String roleName;
    private String roleGroupId;
    private String roleGroupName;

}