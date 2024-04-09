package com.dingtalk.model;

import lombok.*;

import java.util.Date;

/**
 * 部门
 * 可以自行添加需要传入的字段
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Department {

    private String departmentId;

    private String parentId;

    private String departmentName;

}
