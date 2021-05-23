package com.icecola.simplemq.net.handle;

import com.icecola.simplemq.net.IOperationDistributor;
import com.icecola.simplemq.net.bean.Protocol;
import com.icecola.simplemq.net.impl.OperationDistributorImpl;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * description:
 *
 * @author liuxingxing23@jd.com
 * @date 2021/5/23 11:10
 */
@Slf4j
public class ServerProtocolHandler extends SimpleChannelInboundHandler<Protocol> {

    /**
     * 分发器
     */
    public static IOperationDistributor operationDistributor = new OperationDistributorImpl();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Protocol msg) throws Exception {
        log.info("【netty获取数据成功】：{}", msg);
        // 协议处理器
        Protocol handle = operationDistributor.handle(msg);
        //返回客户端消息
        ctx.writeAndFlush(handle);
    }

}
