package com.github.hugh.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxt.system.bo.UserTestBo;

public interface UserTestService extends IService<UserTestBo>{

//    @GlobalTransactional(rollbackFor = Exception.class)
//    @Transactional(rollbackFor = Exception.class)
    void approve(String username);
}
