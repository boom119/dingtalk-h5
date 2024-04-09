package com.dingtalk.mapper;

import com.dingtalk.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    void batchInsert(@Param("userRoles") List<UserRole> userRoles);
    int insertUserRole(UserRole userRole);
    int deleteUserRole(@Param("userId") String userId, @Param("roleId") String roleId);
    int updateUserRole(UserRole userRole);
    UserRole selectUserRole(@Param("userId") String userId, @Param("roleId") String roleId);
    List<UserRole> selectUserRoleAll();
}
