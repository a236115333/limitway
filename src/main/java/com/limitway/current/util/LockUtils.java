package com.limitway.current;

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
}
