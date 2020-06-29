package com.kool.lessons.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * @author luyu
 */
public class MyTcpServer {
    Logger log = Logger.getLogger(MyTcpServer.class);

    private int port = 9091;
    private volatile boolean isRunning = false;
    private int nThreads = 2;
    private int workThreads = 2;
    private final NioEventLoopGroup bossGroup = new NioEventLoopGroup(nThreads);
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup(workThreads);

    public void init() throws InterruptedException {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer())
        .option(ChannelOption.SO_BACKLOG, 1024).childOption(ChannelOption.SO_KEEPALIVE, true);
        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
        if(channelFuture.isSuccess()){
            System.out.println("TCP服务启动 成功---------------");
        }
    }
    /**
     * 服务启动
     */
    public synchronized void startServer() {
        try {
            this.init();
        }catch(Exception ex) {

        }
    }
    public synchronized void stopServer() {
        if (!this.isRunning) {
            throw new IllegalStateException(this.getName() + " 未启动 .");
        }
        this.isRunning = false;
        try {
            Future<?> future = this.workerGroup.shutdownGracefully().await();
            if (!future.isSuccess()) {
                log.error("workerGroup 无法正常停止:{}", future.cause());
            }

            future = this.bossGroup.shutdownGracefully().await();
            if (!future.isSuccess()) {
                log.error("bossGroup 无法正常停止:{}", future.cause());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.log.info("TCP服务已经停止...");
    }

    private String getName() {
        return "TCP-Server";
    }

    public static void main(String[] args) {
        MyTcpServer myTcpServer = new MyTcpServer();
        myTcpServer.startServer();
    }
}
