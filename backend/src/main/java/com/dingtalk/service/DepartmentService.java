package com.dingtalk.service;

import com.dingtalk.model.Department;
import com.dingtalk.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:21
 */
public interface DepartmentService {
    void batchInsertDepartments(List<Department> departments);
    void insertDepartment(Department department);
    void deleteDepartmentById(@Param("departmentId") String departmentId);
    void updateDepartment(Department department);
    Department selectDepartmentById(@Param("departmentId") String departmentId);
    List<Department> selectAllDepartments();
    List<Department> selectDepartmentsByCondition(Map<String, Object> params);
}
