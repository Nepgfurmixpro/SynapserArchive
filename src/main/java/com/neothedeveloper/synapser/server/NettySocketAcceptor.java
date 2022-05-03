package com.neothedeveloper.synapser.server;

import com.neothedeveloper.latte.Latte;
import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.utils.ByteManipulation;
import com.neothedeveloper.synapser.utils.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NettySocketAcceptor extends ChannelHandlerAdapter {
    private PlayerSocket m_playerSocket;
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.m_playerSocket = new PlayerSocket(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        List<Byte> packet = new ArrayList<>();
        try {
            while (buf.isReadable()) {
                packet.add(buf.readByte());
            }
            byte[] bytes = new byte[packet.size()];
            for (int i = 0; i < packet.size(); i++) {
                bytes[i] = packet.get(i);
            }
            if (0 < bytes.length) {
                Logger.Log(LogType.PACKET_EVENT, ByteManipulation.GetByteString(bytes));
                InboundPacketDecoder packetDecoder = new InboundPacketDecoder(bytes);
                Logger.Log(LogType.PACKET_EVENT, String.format("PacketID: %d; Packet Length: %d; Status: %s", packetDecoder.PacketID(), packetDecoder.PacketLength(), m_playerSocket.GetState()));
                HandlerRegistry.runHandler(packetDecoder.PacketID(), m_playerSocket, packetDecoder);
            }
        } finally {
            buf.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
