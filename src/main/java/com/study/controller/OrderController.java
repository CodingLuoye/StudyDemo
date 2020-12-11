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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.UUID;
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

    @RequestMapping("/insertOrder")
    @ApiOperation("插入数字")
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor =Exception.class )
    public void insertOrder() throws Exception{
        OrderCount orderCount = new OrderCount();
        orderCount.setId(Long.valueOf("333"));
        orderCount.setCount(1);
        orderCountDao.insert(orderCount);
       test();
       throw new Exception("111");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void test2(){
        OrderCount orderCount = new OrderCount();
        orderCount.setId(Long.valueOf("555"));
        orderCount.setCount(1);
        orderCountDao.insert(orderCount);
        return;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test(){
        OrderCount orderCount = new OrderCount();
        orderCount.setId(Long.valueOf("444"));
        orderCount.setCount(1);
        orderCountDao.insert(orderCount);
        return;
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
    public void update(@RequestParam Integer count){
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for(int i =0;i<=count;i++){
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
                order.setCount(1);
                order.setId(Thread.currentThread().getId());
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName()+"开始执行");
                int i = orderCountDao.insert(order);
                System.out.println(Thread.currentThread().getName() + " 执行结果为：" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
