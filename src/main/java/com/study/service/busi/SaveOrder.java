package com.study.service.busi;

import com.study.dao.OrderExpDao;
import com.study.model.OrderExp;
import com.study.service.delay.DqMode;
import com.study.service.delay.MqMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Random;
/**
 * @author YCKJ1409
 */
public class SaveOrder {

    private Logger logger = LoggerFactory.getLogger(SaveOrder.class);

    //未支付
    public final static short UNPAY = 0;
    //已支付
    public final static short PAYED = 1;
    //已过期
    public final static short EXPIRED = -1;

    @Autowired
    private OrderExpDao orderExpDao;

    @Autowired
    private DqMode delayOrder;

    @Autowired
    private MqMode delayORder;


    public void insertOrders(int orderNumber){
        Random r = new Random();
        OrderExp orderExp;
        for(int i =0;i<orderNumber;i++){
            long expireTime = r.nextInt(20)+5;
            orderExp = new OrderExp();
            String orderNo = "DD00_"+expireTime+"S";
            orderExp.setOrderNo(orderNo);
            orderExp.setOrderNote("福利"+expireTime+" 号，过期时长为："+orderNo);
            orderExp.setOrderStatus(UNPAY);
        }
    }


    @PostConstruct
    public void initDelayOrder(){
        logger.info("系统成功，扫描表中过期的未支付的订单并处理。。。");
        int counts = orderExpDao.updateExpireOrders();
        logger.info("系统成功，处理了表中["+counts+"]个过期未处理的订单！");

        List<OrderExp> orderExpList = orderExpDao.selectUnPayOrders();
        logger.info("系统成功，系统种还有["+orderExpList.size()+"]个未到期未支付的订单！");
        for (OrderExp order:orderExpList) {
            long expireTime = order.getExpireTime().getTime() -(System.currentTimeMillis());
            delayOrder.orderDelay(order,expireTime);
        }

    }


}
