package com.neothedeveloper.synapser.server;

import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.utils.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class PlayerSocket {
    private final ChannelHandlerContext m_context;
    private ClientState m_state;

    public PlayerSocket(ChannelHandlerContext context) {
        this.m_context = context;
        this.m_state = ClientState.HANDSHAKING;
    }
    ByteBuf out;
    public void Write(byte[] bytes) {
        out = ByteBufAllocator.DEFAULT.buffer();
        out.writeBytes(bytes);
        this.m_context.writeAndFlush(out);
    }

    public void SetState(ClientState state) {
        this.m_state = state;
    }

    public ClientState GetState() {
        return this.m_state;
    }

    public boolean IsAlive() {
        return this.m_context.channel().isOpen();
    }

    public void Close() {
        this.m_context.close();
    }
}
