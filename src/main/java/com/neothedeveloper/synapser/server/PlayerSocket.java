package com.neothedeveloper.synapser.server;

import com.neothedeveloper.latte.Latte;
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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PlayerSocket {
    private final ChannelHandlerContext m_context;
    private ClientState m_state;
    private String m_username = "";
    private byte[] m_verifyToken;
    private boolean m_encryptionEnabled = false;
    private String m_hash = "";

    public PlayerSocket(ChannelHandlerContext context) {
        this.m_context = context;
        this.m_state = ClientState.HANDSHAKING;
        this.m_verifyToken = new byte[4];
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
    public void SetUsername(String username) { this.m_username = username; }
    public String GetUsername() { return this.m_username; }
    public void GenerateHash(String secret) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(secret.getBytes(StandardCharsets.UTF_8));
            md.update(Latte.synapser().getPublicKey().getEncoded());
            StringBuilder sb = new StringBuilder();
            for (byte b : md.digest()) {
                sb.append(String.format("%02x", b));
            }
            m_hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
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

    public void Close() {
        this.m_context.close();
    }
}
