package com.study.netty.chargedemo;

import com.alibaba.fastjson.JSON;
import com.study.socket.SocketResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author YCKJ1409
 * 客户端处理
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 向服务端发送数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String readline = "{\"Header\": {\"TranCode\": \"56230002\",\"SCode\": \"104\",\"TCode\": " +
                "\"56230001\",\"OpNo\": \"操作员号\",\"TermId\": \"终端编号\",\"TranTime\": \"操作日期时间\",\"TraceNo\": " +
                "\"交易流水号\"},\"Body\": {\"IdType\": \"01\",\"IdNo\": \"420115199306054013\",\"Name\": " +
                "\"1345\",\"CustCodeType\": 1,\"CustCode\": \"15623193802\"}}";
        System.out.println("客户端准备发送的数据包：" + readline);
        // 必须有flush
        ctx.writeAndFlush(Unpooled.copiedBuffer(readline, CharsetUtil.UTF_8));
    }

    /**
     * channelInactive
     *
     * channel 通道 Inactive 不活跃的
     *
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     *
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端通道-关闭：" + ctx.channel().localAddress() + "channelInactive");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("读取客户端通道信息..");
        ByteBuf buf = msg.readBytes(msg.readableBytes());
        String message = buf.toString(Charset.forName("utf-8"));
        System.out.println("客户端接收到的服务端信息为:" + message);
        SocketResponse socketResponse = JSON.parseObject(message,SocketResponse.class);
        System.out.println("对象为"+ socketResponse);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常退出:" + cause.getMessage());
    }
}