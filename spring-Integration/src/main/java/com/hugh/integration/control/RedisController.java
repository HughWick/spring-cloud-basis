package com.hugh.integration.control;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author AS
 * @date 2020/9/7 17:28
 */
@Slf4j
@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    private RedisLockRegistry redisLockRegistry;

    private int num = 200;

    /**
     * 测试redis分布式锁(没有锁)
     */
    @GetMapping("testUnLock")
    public void testUnLock() {
        String s = Thread.currentThread().getName();
        if (num > 0) {
            System.out.println(s + "排号成功，号码是：" + num);
            num--;
        } else {
            System.out.println(s + "排号失败,号码已经被抢光");
        }
    }

    /**
     * 测试redis分布式锁(有锁)
     */
    @GetMapping("/lock")
    public void lock(@RequestParam("lockKey") String lockKey) {
        log.info("lockKey={}", lockKey);
        Lock lock = redisLockRegistry.obtain(lockKey);
        try {
            boolean isLock = lock.tryLock(3, TimeUnit.SECONDS);//获取锁、等待时间10秒
            //TODO 具体业务
            String s = Thread.currentThread().getName();
            if (num > 0 && isLock) {
                System.out.println(s + "排号成功，号码是：" + num);
                num--;
            } else {
                System.out.println(s + "排号失败,号码已经被抢光");
            }
        } catch (Exception e) {
            log.error("获取锁失败={}", Throwables.getStackTraceAsString(e));
        } finally {
            log.info("{}-{}释放锁", Thread.currentThread(), new SimpleDateFormat("HH:mm:ss.S").format(new Date()));
            lock.unlock();
        }
    }
}
