package com.neothedeveloper.synapser.server;

import com.google.gson.JsonObject;
import com.neothedeveloper.latte.Latte;
import com.neothedeveloper.synapser.Synapser;
import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.minecraft.datatypes.Texture;
import com.neothedeveloper.synapser.minecraft.utils.ChatParser;
import com.neothedeveloper.synapser.utils.ByteManipulation;
import com.neothedeveloper.synapser.utils.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class PlayerSocket {
    private final ChannelHandlerContext m_context;
    private ClientState m_state;
    private String m_username = "";
    private byte[] m_verifyToken;
    private boolean m_encryptionEnabled = false;
    private UUID m_uuid;
    private Texture m_textureData;
    private int m_protocolVersion;

    public PlayerSocket(ChannelHandlerContext context) {
        this.m_context = context;
        this.m_state = ClientState.HANDSHAKING;
        this.m_verifyToken = new byte[4];
    }
    ByteBuf out;
    public void Write(byte[] bytes) {
        out = ByteBufAllocator.DEFAULT.buffer();
        Logger.Log(LogType.PACKET_EVENT, ByteManipulation.GetByteString(bytes));
        InboundPacketDecoder packetDecoder = new InboundPacketDecoder(bytes);
        Logger.Log(LogType.PACKET_EVENT, String.format("OUT: PacketID: %s; Packet Length: %d; Status: %s", ByteManipulation.GetByteString(new byte[]{(byte)packetDecoder.PacketID()}), packetDecoder.PacketLength(), this.GetState()));
        out.writeBytes(bytes);
        this.m_context.writeAndFlush(out);
    }

    public void SetState(ClientState state) {
        this.m_state = state;
    }
    public ClientState GetState() {
        return this.m_state;
    }
    public void SetUsername(String username) { this.m_username = username; }
    public String GetUsername() { return this.m_username; }
    public UUID GetUUID() { return this.m_uuid; }
    public void SetUUID(UUID uuid) { m_uuid = uuid; }
    public void SetTexture(Texture texture) { m_textureData = texture; }
    public Texture GetTexture() { return m_textureData; }
    public void SetProtocolVersion(int version) { m_protocolVersion = version; }
    public int GetProtocolVersion() { return m_protocolVersion; }

    public int GetMessageId() {
        int id = 0;
        for (byte b : m_verifyToken) {
            id += b;
        }
        return id;
    }
    public boolean checkVerifyToken(byte[] token) {
        for (int i = 0; i < m_verifyToken.length; i++) {
            if (token[i] != m_verifyToken[i]) return false;
        }
        return true;
    }
    public void SetVerifyToken(byte[] token) { m_verifyToken = token; }
    public byte[] GetVerifyToken() { return m_verifyToken; }
    public boolean IsEncryptionEnabled() { return m_encryptionEnabled; }
    public void SetEncryptionEnabled(boolean value) { m_encryptionEnabled = value; }

    public boolean IsAlive() {
        return this.m_context.channel().isOpen();
    }

    public void Close(JsonObject reason) {
        if (this.GetState() == ClientState.LOGIN) Write(new OutboundPacketBuilder(0x00).AddChatField(reason).Build());
        if (this.GetState() == ClientState.PLAY) Write(new OutboundPacketBuilder(0x1A).AddChatField(reason).Build());
        this.m_context.close();
    }
    public void Close() {
        JsonObject reason = ChatParser.ParseString(Synapser.SERVER_PROPERTIES.GetProperty("default-disconnect-msg"));
        if (this.GetState() == ClientState.LOGIN) Write(new OutboundPacketBuilder(0x00).AddChatField(reason).Build());
        if (this.GetState() == ClientState.PLAY) Write(new OutboundPacketBuilder(0x1A).AddChatField(reason).Build());
        this.m_context.close();
    }
}
