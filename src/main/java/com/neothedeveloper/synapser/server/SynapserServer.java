package com.neothedeveloper.synapser.server;

import com.neothedeveloper.synapser.Synapser;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.VarIntLong;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.utils.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SynapserServer extends Thread {
    private ServerSocket m_serverSocket;
    private static List<PlayerSocket> m_playerSockets;
    public SynapserServer(String ip, String port) {
        try {
            this.m_serverSocket = new ServerSocket(Integer.parseInt(port), 50, InetAddress.getByName(ip));
            Logger.Log(LogType.LOG, String.format("Starting server on %s:%s", ip, port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean Running = false;
    public static void SetRunning(boolean running) { Running = running; }

    @Override
    public void run() {
        m_playerSockets = new ArrayList<>();
        Running = true;
        while (Running) {
            try {
                Logger.Log(LogType.VERBOSE, "Accepting Client");
                ClientAccept(m_serverSocket.accept());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int CountOnlinePlayers() {
        return m_playerSockets.size();
    }

    private static ClientState m_state = ClientState.NONE;

    public static void SetState(ClientState state) {
        m_state = state;
    }
    public static ClientState GetState() {
        return m_state;
    }

    private void ClientAccept(Socket clientSocket) throws IOException {
        PlayerSocket playerSocket = new PlayerSocket(clientSocket);
        m_playerSockets.add(playerSocket);
        JSONObject slp = new JSONObject();
        Map<String, Serializable> m = new LinkedHashMap<>();
        m.put("name", "1.18.2");
        m.put("protocol", 758);
        slp.put("version", m);
        m = new LinkedHashMap<>();
        m.put("max", Synapser.SERVER_PROPERTIES.GetProperty("max-players"));
        m.put("online", CountOnlinePlayers());
        JSONArray sample = new JSONArray();
        m.put("sample", sample);
        slp.put("players", m);
        m = new LinkedHashMap<>();
        m.put("text", Synapser.SERVER_PROPERTIES.GetProperty("motd"));
        slp.put("description", m);

        //playerSocket.Write(new OutboundPacketBuilder((byte) 0x00).AddField(slp.toJSONString()).Build());



        boolean Stop = false;

        while (playerSocket.IsAlive()) {
            byte[] bytes = playerSocket.Read();
            if (0 < bytes.length) {
                Stop = true;
                InboundPacketDecoder packetDecoder = new InboundPacketDecoder(bytes);
                Logger.Log(LogType.DEBUG, String.format("PacketID: %d; Packet Length: %d", packetDecoder.PacketID(), packetDecoder.PacketLength()));
                HandlerRegistry.runHandler(packetDecoder.PacketID(), playerSocket, packetDecoder);
            }
        }
    }
}
