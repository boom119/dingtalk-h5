package com.dingtalk.service.impl;

import com.dingtalk.mapper.RoleMapper;
import com.dingtalk.model.Role;
import com.dingtalk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Override
    public void insertRole(Role role) {
        roleMapper.insertRole(role);
    }

    @Override
    public void deleteRoleById(String roleId) {
        roleMapper.deleteRoleById(roleId);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    @Override
    public Role selectRoleById(String roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    @Override
    public List<Role> selectAllRoles() {
        return roleMapper.selectAllRoles();
    }

    @Override
    public List<Role> selectRolesByCondition(Map<String, Object> params) {
        return roleMapper.selectRolesByCondition(params);
    }
}
