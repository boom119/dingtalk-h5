package com.dingtalk.model;

import lombok.*;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 21:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class UserDepartment {
    private String userId;
    private String departmentId;
    private Boolean isLeader;
}
