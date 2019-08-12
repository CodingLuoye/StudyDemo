package com.study.controller;

import com.study.cmb.request.HttpRequest;
import com.study.dao.OrderDao;
import com.study.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author YCKJ1409
 */
@Api("订单管理")
@Controller
public class OrderController {

    @Autowired
    private OrderDao orderDao;

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
}
