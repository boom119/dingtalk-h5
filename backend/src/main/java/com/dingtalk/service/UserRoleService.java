package com.dingtalk.service;

import com.dingtalk.model.UserRole;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 22:55
 */
public interface UserRoleService {
    void batchInsertUserRoles(List<UserRole> userRoles);
}
