package com.dingtalk.mapper;

import com.dingtalk.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:27
 */
@Mapper
public interface RoleMapper {

    void batchInsert(@Param("roles") List<Role> roles);

    void insertRole(Role role);
    void deleteRoleById(@Param("roleId") String roleId);
    void updateRole(Role role);
    Role selectRoleById(@Param("roleId") String roleId);
    List<Role> selectAllRoles();
    List<Role> selectRolesByCondition(Map<String, Object> params);
}
