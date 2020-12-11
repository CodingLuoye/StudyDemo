package com.study.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * @author YCKJ1409
 * 发送TCP沾包sclientHandler
 */
public class TimeClientHandler2 extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(TimeClientHandler2.class.getName());

    private int counter;

    private byte[] req;

    public TimeClientHandler2(){
        req = (("QUERY TIME ORDER")+System.getProperty("line.separator")).getBytes();

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ByteBuf message = null;
        for(int i =0;i<100;i++){
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
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
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req,"UTF-8");
        String body = (String) msg;
        System.out.println("Now is :" + body+" ; the counter is :" + ++counter);
    }
}
