package com.dingtalk.mapper;

import com.dingtalk.model.UserDepartment;
import com.dingtalk.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:27
 */
@Mapper
public interface UserDepartmentMapper {

    void batchInsert(@Param("userDepartments") List<UserDepartment> userDepartments);

    int insertUserDepartment(UserDepartment userDepartment);
    int deleteUserDepartment(@Param("userId") String userId, @Param("departmentId") String departmentId);
    int updateUserDepartment(UserDepartment userDepartment);
    List<UserDepartment> selectUserDepartmentAll();
    UserDepartment selectUserDepartment(@Param("userId") String userId, @Param("departmentId") String departmentId);
}
