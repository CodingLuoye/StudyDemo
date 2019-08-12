package com.study.service.mq;

import com.google.gson.Gson;
import com.study.model.OrderExp;
import com.study.service.busi.DlyOrderProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 * @author YCKJ1409
 */
public class MqConsume implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(MqConsume.class);

    @Autowired
    private DlyOrderProcessor dlyOrderProcessor;

    @Override
    public void onMessage(Message message) {
        try {
            String txtMsg = ((TextMessage)message).getText();
            Gson gson = new Gson();
            OrderExp orderExp = gson.fromJson(txtMsg,OrderExp.class);
            dlyOrderProcessor.checkDelayOrder(orderExp);
        }catch (JMSException e){
            e.printStackTrace();
        }
    }
}
