package com.dingtalk.service;

import com.dingtalk.model.Department;
import com.dingtalk.model.Role;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:21
 */
public interface DepartmentService {
    void batchInsertDepartments(List<Department> departments);
}
