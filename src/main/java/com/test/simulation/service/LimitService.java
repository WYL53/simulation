package com.test.simulation.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;

@Service
public class LimitService {

    private Boolean shutDown = false;

    private BlockingQueue queue = new ArrayBlockingQueue(20);

    public synchronized void shutDown(){
        if(!this.shutDown) {
            this.shutDown = true;
        }
    }

    @Async
    public Future<Object> start()throws InterruptedException{
        while (!this.shutDown) {
            queue.put(new Object());
            Thread.sleep(3000);
        }
        return new AsyncResult(new Object());
    }

    public Object require() throws InterruptedException{
        return queue.take();
    }
}
