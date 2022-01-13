package com.hugh.config.service.impl;

import com.hugh.config.mapper.UserMapper;
import com.hugh.config.model.User;
import com.hugh.config.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author AS
 * @date 2020/9/7 14:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper mapper;

    @Override
    public User find(long id) {
        return mapper.selectById(id);
    }

}
