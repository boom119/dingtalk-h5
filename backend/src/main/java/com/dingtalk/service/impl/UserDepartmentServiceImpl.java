package com.dingtalk.service.impl;

import com.dingtalk.mapper.UserDepartmentMapper;
import com.dingtalk.model.UserDepartment;
import com.dingtalk.service.UserDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
