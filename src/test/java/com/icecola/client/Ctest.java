package com.icecola.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/**
 * description:
 *
 * @author liuxingxing23@jd.com
 * @date 2021/5/23 11:30
 */
public class Ctest {

    static String addr = "127.0.0.1";

    static Integer port = 6666;

    public static void main(String[] args) {
        sendMsg();
    }

    public static void sendMsg() {
        Bootstrap client = new Bootstrap();
        EventLoopGroup worker = new NioEventLoopGroup();
        client.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder());
                    }
                });
        try {
            ChannelFuture sync = client.connect(addr, port).sync();
            System.out.println("客户端启动成功");

            sync.addListener((ChannelFutureListener)future -> {
                //如果连接成功
                if (future.isSuccess()) {
                    Channel channel = sync.channel();
                    System.out.println("发送消息");
                    channel.writeAndFlush("Client:12312312");
                }

            });

            // 关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                worker.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
