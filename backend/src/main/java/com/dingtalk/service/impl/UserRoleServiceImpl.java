package com.dingtalk.service.impl;

import com.dingtalk.mapper.UserRoleMapper;
import com.dingtalk.model.UserRole;
import com.dingtalk.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 22:56
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Transactional
    public void batchInsertUserRoles(List<UserRole> userRoles) {
        userRoleMapper.batchInsert(userRoles);
    }
}
