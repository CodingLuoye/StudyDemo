package com.study.service.busi;

import com.study.dao.OrderDao;
import com.study.dao.OrderExpDao;
import com.study.model.Order;
import com.study.model.OrderExp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author YCKJ1409
 */
@Service
public class DlyOrderProcessor {

    private Logger logger = LoggerFactory.getLogger(DlyOrderProcessor.class);

    @Autowired
    private OrderExpDao orderExpDao;

    @Autowired
    private OrderDao orderDao;

    public void checkDelayOrder(OrderExp record){
        OrderExp dbOrder = orderExpDao.selectByPrimaryKey(record.getId());

        if(dbOrder.getOrderStatus() == SaveOrder.UNPAY){
            logger.info("订单【"+record+"】未支付已过期，需要更新为过期订单");
            orderExpDao.updateExpireOrder(record.getId());
        }else{
            logger.info("已支付订单【"+record+"】无需修改");
        }
    }

    @EventListener
    public void handleOrderEvent(Order order){
        orderDao.insert(order);
    }

}
