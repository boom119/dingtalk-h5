package com.dingtalk.service;

import com.dingtalk.model.User;
import com.dingtalk.model.UserRole;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:20
 */
public interface UserService {
    void batchInsertUsers(List<User> users);
    void insertUser(User user);
    void deleteUserById(String userId);
    void updateUser(User user);
    User selectUserById(String userId);
    List<User> selectAllUsers();
    List<User> selectUsersByStatus(Byte status);
}
