package com.study.netty;

import com.study.netty.xml.XMLRequest;
import com.study.netty.xml.XMLResponse;
import com.study.netty.xml.XMLUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author YCKJ1409
 * socket充值服务 服务端处理类
 */
public class EchoRechargeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *  channel 通道 action 活跃的
     *  当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress().toString() + " 通道已激活！");
    }

    /**
     * channel 通道 Inactive 不活跃的
     *  当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress().toString() + " 通道不活跃！");
        ctx.close();
    }

    /**
     * 避免中文乱码
     * @param buf
     * @return
     */
    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能：读取服务器发送过来的信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            ByteBuf buf = (ByteBuf) msg;
            String rev = getMessage(buf);
            System.out.println("接收到客户端请求："+rev);
            if(rev.length()<5){
                throw new Exception("客户端请求内容异常");
            }
            String xmlString = rev.substring(5);
            XMLRequest xmlRequest = XMLUtils.generateBean(xmlString);
            XMLResponse xmlResponse = this.handleXmlRequest(xmlRequest);
            String resp = XMLUtils.generateXML(xmlResponse);
            resp = String.format("%05d",resp.length()) + resp;
            ctx.writeAndFlush(resp);
        }catch (Exception e){
            ctx.writeAndFlush(e.getMessage());
            e.printStackTrace();
        }
    }

    public XMLResponse handleXmlRequest(XMLRequest xmlRequest){
        XMLResponse xmlResponse  = new XMLResponse();
        xmlResponse.setRetcode("00");
        try {

        }catch (Exception e){
            xmlResponse.setRetmsg("传过来的数据格式不正确");
            xmlResponse.setRetcode("03");
        }
        return xmlResponse;
    }

    /**
     * 功能：读取完毕客户端发送过来的数据之后的操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收数据完毕..");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 功能：服务端发生异常的操作
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }

}