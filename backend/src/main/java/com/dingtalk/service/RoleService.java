package com.dingtalk.service;

import com.dingtalk.model.Role;
import com.dingtalk.model.UserDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:20
 */
public interface RoleService {
    void batchInsertRoles(List<Role> roles);

    void insertRole(Role role);
    void deleteRoleById(String roleId);
    void updateRole(Role role);
    Role selectRoleById(String roleId);
    List<Role> selectAllRoles();
    List<Role> selectRolesByCondition(Map<String, Object> params);
}
