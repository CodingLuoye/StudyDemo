package com.study.netty.javaSerialize;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * @author YCKJ1409
 * 发送TCP沾包sclientHandler
 */
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {

    public SubReqClientHandler(){

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i =0;i<10;i++){
            ctx.writeAndFlush(subReq(i));
        }
    }

    private SubscribeReq subReq(int i){
        SubscribeReq req = new SubscribeReq();
        req.setAddress("上海浦东新区花木苑31号楼301");
        req.setPhoneNumber("1338*****");
        req.setSubReqId(i);
        req.setUserName("JackChen");
        return req;
    }

    /**
     * 解决粘包使用下面的
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This times receive server: ["+msg+"]");
    }
}
