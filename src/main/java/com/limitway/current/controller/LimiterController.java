package com.limitway.current.controller;

import com.limitway.current.redisUtils.LimitType;
import com.limitway.current.redisUtils.annotation.Limit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class LimiterController {

    private static final AtomicInteger ATOMIC_INTEGER_1 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_2 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_3 = new AtomicInteger();

    /**
     * @author fu
     * @description
     * @date 2020/4/8 13:42
     */
    @Limit(key = "limitTest", period = 60, count = 2)
    @GetMapping("/limitTest1")
    @ResponseBody
    public int testLimiter1() {
        return ATOMIC_INTEGER_1.incrementAndGet();
    }

    /**
     * @author fu
     * @description
     * @date 2020/4/8 13:42
     */
    @Limit(key = "customer_limit_test", period = 10, count = 4, limitType = LimitType.CUSTOMER)
    @GetMapping("/limitTest2")
    @ResponseBody
    public int testLimiter2() {

        return ATOMIC_INTEGER_2.incrementAndGet();
    }

    /**
     * @author fu
     * @description
     * @date 2020/4/8 13:42
     */
    @Limit(key = "ip_limit_test", period = 10, count = 3, limitType = LimitType.IP)
    @GetMapping("/limitTest3")
    @ResponseBody
    public int testLimiter3() {
        System.out.println("123123123");
        System.out.println("11111111");
        return ATOMIC_INTEGER_3.incrementAndGet();
    }
    
     /**
     * @author fu
     * @description
     * @date 2020/4/8 13:42
     */
    @Limit(key = "test_test", period = 10, count = 3, limitType = LimitType.IP)
    @GetMapping("/test_test")
    @ResponseBody
    public int testTest() {

        return ATOMIC_INTEGER_3.incrementAndGet();
    }

}

