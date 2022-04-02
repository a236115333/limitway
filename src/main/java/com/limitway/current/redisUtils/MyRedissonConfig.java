package com.limitway.current.redisUtils;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRedissonConfig {


    @Bean(destroyMethod = "shutdown")// 服务停止后调用 shutdown 方法。
    public RedissonClient redisson(){
        //创建配置
        Config config = new Config();

        // 集群模式
        // config.useClusterServers().addNodeAddress("127.0.0.1:7004", "127.0.0.1:7001");
        // 2.根据 Config 创建出 RedissonClient 示例。
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        return Redisson.create(config);
    }


}
