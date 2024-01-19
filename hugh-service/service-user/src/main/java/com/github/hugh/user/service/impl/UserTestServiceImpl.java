package com.github.hugh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.hugh.json.gson.GsonUtils;
import com.github.hugh.service.system.api.SystemUserInfoClient;
import com.github.hugh.system.dao.UserTestDo;
import com.github.hugh.user.mapper.UserTestMapper;
import com.github.hugh.user.service.UserTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * user 测试service
 * @author AS
 * @date 2023/8/23 14:48
 */
@Slf4j
@Service
public class UserTestServiceImpl extends ServiceImpl<UserTestMapper, UserTestDo> implements UserTestService {
    @Resource
    private SystemUserInfoClient systemUserInfoClient;
    @Resource
    private UserTestMapper userTestMapper;

    /**
     * 在事务中，如果前一个为插入数据。后面跟着查询是可以查询到数据的，就算查询后面跟着报错
     * <p>seata事务中，如果异常了，自增ID还是会网上递增，但是不会插入到数据库</p>
     *
     * @param username
     * @param flag
     */
    @Override
    public void approve(String username, int flag) {
        log.info("-----user--服务调用feign");
        UserTestDo userTestDo = new UserTestDo();
        userTestDo.setName(username);
        String insert = systemUserInfoClient.insert(userTestDo);
        log.info("-----user服务调用feign---end，结果：{}", insert);
        QueryWrapper<UserTestDo> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("name", username);
        UserTestDo one = userTestMapper.selectOne(objectQueryWrapper);
        log.info("user查询结果：{}", GsonUtils.toJson(one));
        if (flag == 0) {
            int i = 5 / 0;
        }
        // 抛出异常测试分布式事务
//        throw new Exception("出现异常：测试分布式事务");
    }

    @Override
    public void insert(String username) {
        log.info("-----user--服务调用feign");
        String s = systemUserInfoClient.get(username);
        log.info("-----user服务调用feign---end，结果：{}", s);
        int i = 5 / 0;

    }


}
