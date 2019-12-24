package com.study.controller;

import com.study.cmb.request.HttpRequest;
import com.study.dao.OrderCountDao;
import com.study.dao.OrderDao;
import com.study.model.Order;
import com.study.model.OrderCount;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author YCKJ1409
 */
@Api("订单管理")
@Controller
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderCountDao orderCountDao;

    @Resource
    private ApplicationContext context;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test")
    @ApiOperation("获取列表")
    public void query(){
        HttpRequest httpRequest = new HttpRequest(restTemplate);
        String str = httpRequest.getPublicKey();
        System.out.println(str);
    }

    @RequestMapping("/insert")
    @ApiOperation("插入列表")
    public void insert(){
        Order order = new Order();
        order.setId(System.currentTimeMillis());
        order.setName("xxx");
        order.setContent("xxx您已消费多少钱");
        order.setCreateBy("chen");
        order.setCreateTime("2019-08-30");
//        orderDao.insert(order);

        context.publishEvent(order);
        return;
    }

    @RequestMapping("/testCount")
    @ApiOperation("测试并发更新")
    public void update(){
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for(int i =0;i<=200;i++){
            Thread testThread1 = new Thread(new TestThread(orderCountDao,countDownLatch));
            testThread1.start();
            countDownLatch.countDown();
        }
    }

    class TestThread implements Runnable{

        private OrderCountDao orderCountDao;

        private CountDownLatch countDownLatch;

        public TestThread(OrderCountDao orderCountDao, CountDownLatch countDownLatch){
            this.orderCountDao = orderCountDao;
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            try {
                OrderCount order = new OrderCount();
                order.setId(Long.valueOf(1));
                order.setCount(1);
                System.out.println("等待执行");
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName()+"开始执行");
                int i = orderCountDao.update(order);
                System.out.println(Thread.currentThread().getName() + " 执行结果为：" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
