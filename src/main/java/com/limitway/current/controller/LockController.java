package com.limitway.current.controller;


import com.alibaba.fastjson.JSON;
import com.limitway.current.base.ConfigTreeNode;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("lockController")
@Slf4j
public class LockController {

    @Autowired
    RedissonClient redissonClient;


    @GetMapping("getLockTest")
    public String getLockTest() {
        RLock rlock = redissonClient.getLock("baJie-lock");
        RReadWriteLock  rrlock = redissonClient.getReadWriteLock("read-lock");
        ReentrantLock rtlock = new ReentrantLock(false);
        rtlock.lock();
        try {
            System.out.println("加锁成功，执行后续代码。线程 ID：" + Thread.currentThread().getId());
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rlock.unlock();
        }
        return ";";
    }




    @GetMapping("park")
    public String park() throws InterruptedException {
        RSemaphore park = redissonClient.getSemaphore("park");
//        park.acquire();

        return "ok";
    }

    @GetMapping("leave")//注意：多次执行释放信号量操作，剩余信号量会一直增加，而不是到 3 后就封顶了。
    public String leave() throws InterruptedException {
        RSemaphore park = redissonClient.getSemaphore("park");

        park.release();

        return "ok";
    }

    @GetMapping("stddd")//注意：多次执行释放信号量操作，剩余信号量会一直增加，而不是到 3 后就封顶了。
    public String stddd(@RequestParam("str")  String str) throws InterruptedException {
        String jsonStr = JSON.toJSONString(str);
        ConfigTreeNode node = JSON.parseObject(str,ConfigTreeNode.class);
        System.out.println(node.toString());

        return "ok";
    }

}
