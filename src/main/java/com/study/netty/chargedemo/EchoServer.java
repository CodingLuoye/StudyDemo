package com.study.netty.chargedemo;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.concurrent.*;

/**
 * @author YCKJ1409
 * 双方采用Socket报文协议，同步短连接方式，json报文格式，UTF-8格式编码，明文方式（加密暂时不启用），传递报文
 * socket查询服务--netty方式，支持高并发
 */
@Component
public class EchoServer implements InitializingBean {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${port:4000}")
    private Integer port;

    public void start(Integer port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            // 绑定线程池 绑定客户端连接时候触发操作
            sb.group(group, bossGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            logger.info("报告 信息：有一客户端链接到本服务端");
                            logger.info("IP:" + ch.localAddress().getHostName());
                            logger.info("Port:" + ch.localAddress().getPort());
                            ch.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                            // 客户端触发操作
                            ch.pipeline().addLast(new EchoServerHandler());
                            ch.pipeline().addLast(new ByteArrayEncoder());
                        }
                    });
            // 服务器异步创建绑定
            ChannelFuture cf = sb.bind().sync();
            // 关闭服务器通道
            cf.channel().closeFuture().sync();
        } finally {
            // 释放线程池资源
            group.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();
        }
    }

    @Override
    public void afterPropertiesSet(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("SocketTest-server-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(1,1,0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(5),threadFactory);
        executorService.execute((new Runnable() {
            @Override
            public void run() {
                try {
                    start(port);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }})
        );
    }
}