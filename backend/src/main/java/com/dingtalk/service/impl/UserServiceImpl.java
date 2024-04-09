package com.dingtalk.service.impl;

import com.dingtalk.mapper.UserMapper;
import com.dingtalk.model.User;
import com.dingtalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:26
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void batchInsertUsers(List<User> users) {
        userMapper.batchInsert(users);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void deleteUserById(String userId) {
        userMapper.deleteUserById(userId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User selectUserById(String userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public List<User> selectAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public List<User> selectUsersByStatus(Byte status) {
        return null;
    }
}
