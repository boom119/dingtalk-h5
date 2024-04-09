package com.dingtalk.service.impl;

import com.dingtalk.mapper.UserDepartmentMapper;
import com.dingtalk.model.UserDepartment;
import com.dingtalk.service.UserDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:25
 */
@Service
public class UserDepartmentServiceImpl implements UserDepartmentService {

    @Autowired
    private UserDepartmentMapper userDepartmentMapper;
    @Override
    public void batchInsertUserDepartments(List<UserDepartment> userDepartments) {
        userDepartmentMapper.batchInsert(userDepartments);
    }

    @Override
    public int insertUserDepartment(UserDepartment userDepartment) {
        return userDepartmentMapper.insertUserDepartment(userDepartment);
    }

    @Override
    public int deleteUserDepartment(String userId, String departmentId) {
        return userDepartmentMapper.deleteUserDepartment(userId,departmentId);
    }

    @Override
    public int updateUserDepartment(UserDepartment userDepartment) {
        return userDepartmentMapper.updateUserDepartment(userDepartment);
    }

    @Override
    public List<UserDepartment> selectUserDepartmentAll() {
        return userDepartmentMapper.selectUserDepartmentAll();
    }

    @Override
    public UserDepartment selectUserDepartment(String userId, String departmentId) {
        return userDepartmentMapper.selectUserDepartment(userId,departmentId);
    }
}
