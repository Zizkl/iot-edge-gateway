package com.jwwd.gateway.tcp.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpServerHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger log = LoggerFactory.getLogger(TcpServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("Device connected: {}", ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) {
        log.info("Received device message from {}: {}", ctx.channel().remoteAddress(), message.trim());
        ctx.writeAndFlush("OK\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("Device disconnected: {}", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn("TCP channel error: {}", cause.getMessage());
        ctx.close();
    }
}
