package com.pujjr.antifraud.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * A HTTP server showing how to use the HTTP multipart package for file uploads.
 */
public class AntiFraudHttpServer {

    private final int port;
    public static boolean isSSL;

    public AntiFraudHttpServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new AntiFraudHttpServerInitializer());

            Channel ch = b.bind(port).sync().channel();
            System.out.println("服务启动成功，端口：" + port);
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 10080;
        }
        if (args.length > 1) {
            isSSL = true;
        }
        new AntiFraudHttpServer(port).run();
    }
}
