package com.dingtalk.service;

import com.dingtalk.model.User;
import com.dingtalk.model.UserDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:21
 */
public interface UserDepartmentService {
    void batchInsertUserDepartments(List<UserDepartment> userDepartments);

    int insertUserDepartment(UserDepartment userDepartment);
    int deleteUserDepartment(@Param("userId") String userId, @Param("departmentId") String departmentId);
    int updateUserDepartment(UserDepartment userDepartment);

    List<UserDepartment> selectUserDepartmentAll();
    UserDepartment selectUserDepartment(@Param("userId") String userId, @Param("departmentId") String departmentId);
}
