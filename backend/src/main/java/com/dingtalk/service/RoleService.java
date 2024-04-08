package com.dingtalk.service;

import com.dingtalk.model.Role;
import com.dingtalk.model.UserDepartment;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:20
 */
public interface RoleService {
    void batchInsertRoles(List<Role> roles);
}
