package com.neothedeveloper.synapser.server;

import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.utils.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PlayerSocket {
    private Socket m_socket;
    private OutputStream m_socketOutStream;
    private InputStream m_socketInStream;
    public PlayerSocket(Socket tcpSocket) throws IOException {
        this.m_socket = tcpSocket;
        this.m_socketOutStream = tcpSocket.getOutputStream();
        this.m_socketInStream = tcpSocket.getInputStream();
    }

    public void Write(byte[] bytes) {
        try {
            this.m_socketOutStream.write(bytes);
        } catch (IOException e) {
            Logger.Log(LogType.ERROR, "Failed to write packet to player");
            throw new RuntimeException(e);
        }
    }

    public byte[] Read() {
        try {
            return this.m_socketInStream.readAllBytes();
        } catch (IOException e) {
            Logger.Log(LogType.ERROR, "Failed to read packet from player");
            throw new RuntimeException(e);
        }
    }

    public boolean IsAlive() {
        return this.m_socket.isConnected();
    }

    public void Cloes() throws IOException {
        this.m_socket.close();
    }
}
