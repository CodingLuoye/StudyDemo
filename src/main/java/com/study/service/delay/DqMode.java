package com.study.service.delay;

import com.study.model.OrderExp;
import com.study.service.busi.DlyOrderProcessor;
import com.study.vo.ItemVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.concurrent.DelayQueue;

/**
 * @author YCKJ1409
 */
public class DqMode {

    private Logger logger = LoggerFactory.getLogger(DqMode.class);

    @Autowired
    private DlyOrderProcessor dlyOrderProcessor;

    private static DelayQueue<ItemVo<OrderExp>> delayOrder = new DelayQueue<>();


    public void orderDelay(OrderExp order, long expireTime) {
        ItemVo<OrderExp> itemVo = new ItemVo<OrderExp>(expireTime,order);
        delayOrder.put(itemVo);
        logger.info("订单[超时时长：]"+expireTime+"呗推入本地检查队列，订单详情："+order);
    }

    /*处理到期订单的任务实现*/
    private class TakeOrder implements Runnable{

        private DlyOrderProcessor dlyOrderProcessor;

        public TakeOrder(DlyOrderProcessor dlyOrderProcessor){
            super();
            this.dlyOrderProcessor = dlyOrderProcessor;
        }

        @Override
        public void run() {
            logger.info("处理到期订单的线程已启动");
            while(!Thread.currentThread().isInterrupted()){
                try{
                    /*获取队列中的订单*/
                    ItemVo<OrderExp> itemOrder = delayOrder.take();
                    if(null != itemOrder){
                        dlyOrderProcessor.checkDelayOrder(itemOrder.getData());
                    }
                }catch (Exception e){
                    logger.error("Thre thread is Interrupted");
                }
            }
        }
    }

    private Thread takeOrder;

    @PostConstruct
    public void init(){
        takeOrder = new Thread(new TakeOrder(dlyOrderProcessor));
        takeOrder.start();
    }

    public void close(){
        takeOrder.isInterrupted();
    }
}
