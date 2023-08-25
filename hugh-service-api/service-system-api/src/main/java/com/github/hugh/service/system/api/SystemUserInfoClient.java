package com.github.hugh.service.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
//        contextId = "sysDept",
        value = "car-system", // 服务名称
//        fallback = SysDeptClientFallback.class,
        path = "car-system-api" // 统一前缀路径
)
public interface SystemUserInfoClient {

    @GetMapping("user/get")
    String get(@RequestParam("username") String username);
}
