package com.github.hugh.service.system.contorl;

import com.github.hugh.service.system.mapper.UserTestMapper;
import com.github.hugh.system.dao.UserTestDo;
import com.github.hugh.util.RandomUtils;
import io.seata.core.context.RootContext;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author AS
 * @date 2023/8/22 15:44
 */
@Slf4j
@Api(tags = "登录")
@RestController
@RequestMapping("user")
public class UserControl {

    @Resource
    private UserTestMapper userTestMapper;

    //    @GlobalTransactional(rollbackFor = Exception.class)
    @PostMapping("insert")
    public String insert(@RequestBody UserTestDo userTestDo) {
        log.info("系统服务，用户服务获取根ID：{}", RootContext.getXID());
//        UserTestDo userTestDo = new UserTestDo();
//        userTestDo.setName(username);
        userTestDo.setAge(RandomUtils.randomInt(1, 90));
        userTestDo.setStatus(0);
        userTestDo.setCreated(new Date());
        userTestMapper.insert(userTestDo);
        return "用户信息：" + userTestDo.getName();
    }

    //    @GlobalTransactional(rollbackFor = Exception.class)
    private void test01() {
        int i = 1 / 0;
    }
}
