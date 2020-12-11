package com.study.netty.delimit;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * @author YCKJ1409
 * 发送TCP沾包sclientHandler
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(EchoClientHandler.class.getName());

    private int counter;

    static final String ECHO_REQ = "Hi, Lilinfeng. Welcome to Netty.$_";

    public EchoClientHandler(){

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream :" +cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i =0;i<10;i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    /**
     * 解决粘包使用下面的
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is "+ ++counter + " times receive server: ["+msg+"]");
    }
}
