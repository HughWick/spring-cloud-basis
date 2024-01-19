package com.github.hugh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.hugh.system.dao.UserTestDo;

public interface UserTestService extends IService<UserTestDo>{

    void approve(String username , int flag);

    void insert(String username);
}
