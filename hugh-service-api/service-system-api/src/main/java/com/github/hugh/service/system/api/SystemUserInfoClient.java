package com.github.hugh.service.system.api;

import com.github.hugh.system.dao.UserTestDo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
//        contextId = "sysDept",
        value = "hugh-system", // 服务名称
//        fallback = SysDeptClientFallback.class,
        path = "system-api" // 统一前缀路径
)
public interface SystemUserInfoClient {

    @GetMapping("user/get")
    String get(@RequestParam("username") String username);

    @PostMapping("user/insert")
    String insert(@RequestBody UserTestDo userTestDo);
}
