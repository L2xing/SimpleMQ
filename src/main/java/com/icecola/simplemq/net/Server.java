package com.icecola.simplemq.net;

import com.icecola.simplemq.net.config.NetConfig;
import com.icecola.simplemq.net.handle.ProtocolDecoder;
import com.icecola.simplemq.net.handle.ProtocolEncoder;
import com.icecola.simplemq.net.handle.ServerProtocolHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * description:
 * 服务器
 *
 * @author liuxingxing
 * @date 2021.05.22 00:02
 */
@Slf4j
public class Server {

    /**
     * server boot启动类
     */
    private final static ServerBootstrap serverBootstrap = new ServerBootstrap();

    /**
     * boss 线程池
     */
    private final static EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    /**
     * worker 线程池
     */
    private final static EventLoopGroup workerGroup = new NioEventLoopGroup();

    private Server() {
        initServerBootStrap();
    }

    public static void start(){
        new Server();
    }


    private static void initServerBootStrap() {
        try {
            long startTime = System.currentTimeMillis();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 需要考虑参数的含义
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    //字符串解码和编码
                                    .addLast(new StringEncoder())
                                    .addLast(new ProtocolEncoder())
                                    .addLast(new StringDecoder())
                                    .addLast(new ProtocolDecoder())
                                    //服务器的逻辑 需要自行实现
                                    .addLast(new ServerProtocolHandler());
                        }
                    });
            //绑定端口，同步等待成功
            ChannelFuture f = serverBootstrap.bind(NetConfig.getPort()).sync();
            long endTime = System.currentTimeMillis();
            if (f != null && f.isSuccess()) {
                log.info("【Netty创建成功】：端口占用：{}，耗时{}", NetConfig.getPort(), endTime - startTime);
            } else {
                log.info("Netty server start fail");
            }
            //等待服务器监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("【Netty创建失败】：sync() 同步等待失败。", e);
        } catch (Exception e) {
            log.error("【Netty创建失败】：位置错误：", e);
        } finally {
            //优雅退出，释放线程池资源
            log.info("【netty Server关闭】");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
