package com.dingtalk.mapper;

import com.dingtalk.model.Department;
import com.dingtalk.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:27
 */
@Mapper
public interface DepartmentMapper {
    void batchInsert(@Param("departments") List<Department> departments);

}
