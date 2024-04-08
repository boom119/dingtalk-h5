package com.dingtalk.mapper;

import com.dingtalk.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    void batchInsert(@Param("userRoles") List<UserRole> userRoles);
}
