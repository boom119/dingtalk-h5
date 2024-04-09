package com.dingtalk.service.impl;

import com.dingtalk.mapper.DepartmentMapper;
import com.dingtalk.model.Department;
import com.dingtalk.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:24
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public void batchInsertDepartments(List<Department> departments) {
        departmentMapper.batchInsert(departments);

    }

    @Override
    public void insertDepartment(Department department) {
        departmentMapper.insertDepartment(department);
    }

    @Override
    public void deleteDepartmentById(String departmentId) {
        departmentMapper.deleteDepartmentById(departmentId);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);
    }

    @Override
    public Department selectDepartmentById(String departmentId) {
        return departmentMapper.selectDepartmentById(departmentId);
    }

    @Override
    public List<Department> selectAllDepartments() {
        return departmentMapper.selectAllDepartments();
    }

    @Override
    public List<Department> selectDepartmentsByCondition(Map<String, Object> params) {
        return departmentMapper.selectDepartmentsByCondition(params);
    }
}
