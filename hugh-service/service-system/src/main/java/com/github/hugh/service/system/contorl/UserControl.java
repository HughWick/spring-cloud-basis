package com.github.hugh.service.system.contorl;

import com.github.hugh.service.system.mapper.UserTestMapper;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @GetMapping("get")
    public String get(String username) {
        log.info("系统服务，用户服务获取根ID：{}", RootContext.getXID());
//        QueryWrapper<UserTestBo> query = Wrappers.query();
//        query.eq("name", username);
//        UserTestBo userTestBo = userTestMapper.selectOne(query);
//        userTestBo.setStatus(99);
//        userTestMapper.updateById(userTestBo);
        test01();
        return "用户信息：" + username;
    }

//    @GlobalTransactional(rollbackFor = Exception.class)
    private void test01() {
        int i = 1 / 0;
    }
}
