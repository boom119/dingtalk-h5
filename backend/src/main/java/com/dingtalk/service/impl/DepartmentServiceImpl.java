package com.dingtalk.service.impl;

import com.dingtalk.mapper.DepartmentMapper;
import com.dingtalk.model.Department;
import com.dingtalk.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
