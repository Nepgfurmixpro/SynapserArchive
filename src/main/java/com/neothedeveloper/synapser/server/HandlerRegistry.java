package com.neothedeveloper.synapser.server;

import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.handlers.PacketHandler;
import com.neothedeveloper.synapser.utils.Logger;

import java.util.HashMap;
import java.util.Map;

public class HandlerRegistry {
    private static final Map<Integer, PacketHandler> m_handlers = new HashMap<>();
    public static void register(int packetID, PacketHandler packetHandler) {
        m_handlers.put(packetID, packetHandler);
    }
    public static void runHandler(int packetID, PlayerSocket socket, InboundPacketDecoder packet) {
        PacketHandler handler = m_handlers.get(packetID);
        if (handler == null) Logger.Log(LogType.ERROR, String.format("Packet ID not handled. Packet ID: %d", packetID));
        handler.handle(socket, packet);
    }
}
