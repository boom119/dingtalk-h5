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
public class Department {

    private Long department_id;

    private Long parent_id;

    private String department_name;

//    private Long order;

//    private Date create_time;

//    private Date update_time;
}
