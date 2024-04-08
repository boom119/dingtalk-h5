package com.dingtalk.service;

import com.dingtalk.model.User;
import com.dingtalk.model.UserDepartment;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:21
 */
public interface UserDepartmentService {
    void batchInsertUserDepartments(List<UserDepartment> userDepartments);
}
