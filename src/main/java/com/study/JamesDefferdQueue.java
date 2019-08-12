package com.study;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author YCKJ1409
 * 模拟队列
 */
public class JamesDefferdQueue {

    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedQueue<>();

    public static void save(DeferredResult<Object> object){
        queue.add(object);
    }
    public static DeferredResult<Object> get(){
        return  queue.poll();
    }
}
