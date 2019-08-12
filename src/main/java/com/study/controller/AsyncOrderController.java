package com.study.controller;

import com.study.JamesDefferdQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

@Controller
public class AsyncOrderController {

    @ResponseBody
    @RequestMapping("/order1")
    public Callable<String> order1(){
        System.out.println("主线程"+Thread.currentThread() + "===》start..." +System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("副线程"+Thread.currentThread() + "===》start..." +System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("副线程"+Thread.currentThread() + "===》end..." +System.currentTimeMillis());
                return "order buy success...";
            }
        };
        System.out.println("主线程"+Thread.currentThread() + "===》end..." +System.currentTimeMillis());
        return callable;
    }

    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder(){
        DeferredResult<Object> deferredResult = new DeferredResult<>((long)3000,"create fail...");
        JamesDefferdQueue.save(deferredResult);
        return deferredResult;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String create(){
        String orderId = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = JamesDefferdQueue.get();
        deferredResult.setResult(orderId);
        return "create success  orderId=="+orderId;
    }

}
