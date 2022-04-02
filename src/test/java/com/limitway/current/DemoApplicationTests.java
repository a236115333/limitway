package com.limitway.current;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	RedissonClient redissonClient;

	@Test
	void contextLoads() {
		for (int i = 0; i <10000 ; i++) {

		}
	}




}
