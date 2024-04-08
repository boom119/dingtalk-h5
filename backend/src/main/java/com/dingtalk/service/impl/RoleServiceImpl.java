package com.dingtalk.service.impl;

import com.dingtalk.mapper.RoleMapper;
import com.dingtalk.model.Role;
import com.dingtalk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:25
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void batchInsertRoles(List<Role> roles) {
        roleMapper.batchInsert(roles);
    }
}
