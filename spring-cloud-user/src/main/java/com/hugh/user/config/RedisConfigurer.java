package com.hugh.user.config;

import com.github.hugh.cache.redis.EasyRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
@Configuration
public class RedisConfigurer {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.password}")
    private String password;

    public static EasyRedis easyRedis = null;

    /**
     * 初始化redis连接池
     */
    @Bean(name = "JedisPool1")
    public JedisPool redisPoolFactory() {
        log.info("初始化redis pool start...");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最小基础数
        jedisPoolConfig.setMinIdle(10);
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(500);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(1000);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(10000);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(60000);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(10);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(true);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, Integer.parseInt(port), 10000, password);
        easyRedis = EasyRedis.getInstance(jedisPool);
        log.info("初始化redis pool end...");
        return jedisPool;
    }

	/*public void redisConnectionFactory(int db) {
		LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
		jedisConnectionFactory.setDatabase(db);
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		jedisConnectionFactory.resetConnection();
		ValueOperations valueOperations = redisTemplate.opsForValue();
	}*/
}