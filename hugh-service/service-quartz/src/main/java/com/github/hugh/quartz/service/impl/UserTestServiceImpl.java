package com.github.hugh.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.hugh.quartz.mapper.UserTestMapper;
import com.github.hugh.quartz.service.UserTestService;
import com.github.hugh.service.system.api.SystemUserInfoClient;
import com.zxt.system.bo.UserTestBo;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author AS
 * @date 2023/8/23 14:48
 */
@Slf4j
@Service
public class UserTestServiceImpl extends ServiceImpl<UserTestMapper, UserTestBo> implements UserTestService {
    @Resource
    private SystemUserInfoClient systemUserInfoClient;
    @Resource
    private UserTestMapper userTestMapper;

    @Override
    public void approve(String username) {
        log.info("insert，第一端获取rootID：{}", RootContext.getXID());
        UserTestBo userTestBo = new UserTestBo();
        userTestBo.setId(1);
        userTestBo.setName("张三");
        userTestBo.setAge(18);
        userTestBo.setStatus(0);
        userTestBo.setCreated(new Date());
        userTestMapper.insert(userTestBo);

        log.info("-----quartz--服务调用feign");
//        int a = 1/0;
        systemUserInfoClient.get(username);
        // 抛出异常测试分布式事务
//        throw new Exception("出现异常：测试分布式事务");
    }

}
