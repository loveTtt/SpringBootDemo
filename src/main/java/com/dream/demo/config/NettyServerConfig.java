package com.dream.demo.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServerConfig {

    public static void main(String[] args) {
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //Netty服务启动辅助类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workGroup);
            //设置TCP socket通道为NioServerSocketChannel
            //若是UDP通信则设置为DatagramChannel
            serverBootstrap.channel(NioServerSocketChannel.class);
            //设置TCP参数
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    //当有客户端链路注册读写事件时，初始化Handle,并将Handle加入到管道中
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 向Work线程的管道双向链表中添加处理率ServerHandler
                            //整个处理流向如下：HeadContext-channelRead读数据--->ServerHandler-channelRead
                            //读取数据进行业务逻辑判断，最后将结果返回给客户端-->TailContext-write->HeadContext-write
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    });
            //同步绑定端口
            ChannelFuture future = serverBootstrap.bind(8080).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            boosGroup.shutdownGracefully();
        }

    }
}
