package com.dingtalk.service;

import com.dingtalk.model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 22:55
 */
public interface UserRoleService {
    void batchInsertUserRoles(List<UserRole> userRoles);
    int insertUserRole(UserRole userRole);
    int deleteUserRole(String userId,  String roleId);
    int updateUserRole(UserRole userRole);
    UserRole selectUserRole(String userId,String roleId);
    List<UserRole> selectUserRoleAll();
}
