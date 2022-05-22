package com.hugh.integration.control;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.github.hugh.aop.constraints.IpV4;
import com.github.hugh.json.gson.JsonObjectUtils;
import com.github.hugh.util.ip.Ip2regionUtils;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author AS
 * @date 2020/9/7 17:28
 */
@Validated
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

    @GetMapping("test01")
    public void test01(@IpV4(nullable = true) String ip
            , @IpV4 String ip2) {
        String s = Thread.currentThread().getName();
        if (num > 0) {
            System.out.println(s + "排号成功，号码是：" + num);
            num--;
        } else {
            System.out.println(s + "排号失败,号码已经被抢光");
        }
        System.out.println("=ip==isEmpty==false======" + ip);
        System.out.println("=ip2==isEmpty==true======" + ip2);
    }


    @GetMapping("test02")
    public void test02(HttpServletRequest request) {
        String s = Thread.currentThread().getName();
        if (num > 0) {
            System.out.println(s + "排号成功，号码是：" + num);
            num--;
        } else {
            System.out.println(s + "排号失败,号码已经被抢光");
        }
//        System.out.println("=ip==isEmpty==false======" + str);
//        while (true){
//            try {
//                Thread.sleep(50000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }


    @GetMapping("parse")
    public String parse( String ip) {
//        return userService.find(id);
        System.out.println(Ip2regionUtils.parse(ip));
        return JsonObjectUtils.toJson(Ip2regionUtils.parse(ip));
    }

    public static void main(String[] args) {

        byte[] data = "我是一段测试字符串".getBytes();
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
//签名
        byte[] signed = sign.sign(data);
//验证签名
        boolean verify = sign.verify(data, signed);
        System.out.println("--->>" + verify);
        KeyPair rsa = SecureUtil.generateKeyPair("RSA");
        System.out.println("===>>" +rsa.getPrivate());
//        System.out.println("===>>" + SecureUtil.generateSignature(AsymmetricAlgorithm.RSA, DigestAlgorithm.SHA256));
    }
}
