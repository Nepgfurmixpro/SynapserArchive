package com.neothedeveloper.synapser.server;

import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.utils.Logger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SynapserServer {
    private final EventLoopGroup m_serverBossGroup;
    private final EventLoopGroup m_serverWorkerGroup;
    private final String m_ip;
    private final int m_port;
    public SynapserServer(String ip, String port) {
        this.m_ip = ip;
        this.m_port = Integer.parseInt(port);
        this.m_serverBossGroup = new NioEventLoopGroup();
        this.m_serverWorkerGroup = new NioEventLoopGroup();
        Logger.Log(LogType.LOG, String.format("Starting server on %s:%s", ip, port));
        SynapserHandlers.registerAll();
    }

    public void run() throws Exception {
        try {
            ServerBootstrap m_server = new ServerBootstrap();
            m_server.group(m_serverBossGroup, m_serverWorkerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new NettySocketAcceptor());
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture m_future = m_server.bind(this.m_ip, this.m_port).sync();
            if (m_future.isSuccess()) Logger.Log(LogType.EVENT, "Server has started.");
            m_future.channel().closeFuture().sync();
        } finally {
            Logger.Log(LogType.EVENT, "Stopping server");
            this.m_serverWorkerGroup.shutdownGracefully();
            this.m_serverBossGroup.shutdownGracefully();
        }
    }
}
