package com.limitway.current.util;

import org.springframework.util.DigestUtils;

import java.util.concurrent.atomic.AtomicReference;

public class LockUtils {

    private AtomicReference<Thread> atomicref = new AtomicReference<Thread>();
    public void lock(){
        Thread thread = Thread.currentThread();
        for (;;){
            if(!atomicref.compareAndSet(null,thread)){
                return;
            }
        }
    }

    public void unlock(){
        Thread  thread = Thread.currentThread();
        atomicref.compareAndSet(thread,null);
    }

    public static void main(String[] args) {
        String md5 = "8ab3e942-7927-11e9-9b87-43d45f5ebb8f";
        String md5j = DigestUtils.md5DigestAsHex((md5+"1234").getBytes());
        System.out.println("md5加密："+md5j);
        String md5jm = DigestUtils.md5DigestAsHex((md5+md5j).getBytes());
        System.out.println("md5解密："+String.valueOf(md5jm));
        md5jm = DigestUtils.md5DigestAsHex((md5+md5jm).getBytes());
        System.out.println("md5解密："+String.valueOf(md5jm));
    }
}
