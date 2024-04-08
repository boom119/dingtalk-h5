package com.dingtalk.mapper;

import com.dingtalk.model.User;
import com.dingtalk.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:26
 */
@Mapper
public interface UserMapper {
    void batchInsert(@Param("users") List<User> users);
}
