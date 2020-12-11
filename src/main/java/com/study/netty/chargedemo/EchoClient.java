package com.study.netty.chargedemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;

/**
 * @author YCKJ1409
 * 双方采用Socket报文协议，同步短连接方式，json报文格式，UTF-8格式编码，明文方式（加密暂时不启用），传递报文
 * socket查询服务--客户端
 */
public class EchoClient {
    private static Integer count = 260;
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(this.host, this.port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("客户端正在连接中...");
                            ch.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                            ch.pipeline().addLast(new EchoClientHandler());
                            ch.pipeline().addLast(new ByteArrayEncoder());
                            ch.pipeline().addLast(new ChunkedWriteHandler());
                        }
                    });
            // 异步连接服务器
            ChannelFuture cf = b.connect().sync();
            // 连接完成
            System.out.println("连接服务端成功...");
            // 异步等待关闭连接channel
            cf.channel().closeFuture().sync();
            // 关闭完成
            System.out.println("连接已关闭..");
        } finally {
            // 释放线程池资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i =0;i<count;i++){
            new TestThread(String.valueOf(i),countDownLatch).start();
        }
    }

    static class TestThread extends Thread {

        private String name;

        private CountDownLatch countDownLatch;

        public TestThread(String name, CountDownLatch countDownLatch){
            this.name = name;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            countDownLatch.countDown();
            try {
                new EchoClient("10.128.134.235", 8804).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}