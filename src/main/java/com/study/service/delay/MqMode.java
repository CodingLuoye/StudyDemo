package com.study.service.delay;

import com.google.gson.Gson;
import com.study.model.OrderExp;
import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
/**
 * @author YCKJ1409
 */
@Service
public class MqMode {

    private Logger logger = LoggerFactory.getLogger(MqMode.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void orderDelay(OrderExp order,long expireTime){
        logger.info("订单[超时时长："+expireTime+"秒]将被发送给消息队列，详情"+order);
        try{
            jmsTemplate.send("order.delay", new CreateMessage(order,expireTime) {
            });
        }catch (JmsException e){
            e.printStackTrace();
        }
    }

    private static class CreateMessage implements MessageCreator{

        private OrderExp orderExp;

        private Long expireTime;

        public CreateMessage(OrderExp orderExp, Long expireTime) {
            this.orderExp = orderExp;
            this.expireTime = expireTime;
        }

        @Override
        public Message createMessage(Session session) throws JMSException {
            Gson gson = new Gson();
            //将订单gson化,转化为文本
            String txtMsg = gson.toJson(orderExp);
            Message message = session.createTextMessage(txtMsg);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,expireTime);
            return  message;
        }
    }
}
