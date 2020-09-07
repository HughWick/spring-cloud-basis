package com.hugh.user.service.impl;

import com.hugh.user.mapper.UserMapper;
import com.hugh.user.model.User;
import com.hugh.user.service.UserService;
import org.apache.ibatis.annotations.Mapper;
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
