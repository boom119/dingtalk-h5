package com.dingtalk.mapper;

import com.dingtalk.model.User;
import com.dingtalk.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:26
 */
@Mapper
public interface UserMapper {
    void batchInsert(@Param("users") List<User> users);
    void insertUser(User user);
    void deleteUserById(@Param("userId") String userId);
    void updateUser(User user);
    User selectUserById(@Param("userId") String userId);
    List<User> selectAllUsers();
    List<User> selectUsersByStatus(@Param("status") Byte status);
    List<User> selectUserByCondition(Map<String, Object> params);


}
