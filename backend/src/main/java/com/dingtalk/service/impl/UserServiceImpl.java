package com.dingtalk.service.impl;

import com.dingtalk.mapper.UserMapper;
import com.dingtalk.model.User;
import com.dingtalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OpenTheDoor
 * @version 1.0
 * @date 2024/4/8 23:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void batchInsertUsers(List<User> users) {
        userMapper.batchInsert(users);

    }
}
