package com.neothedeveloper.synapser.server;

import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.handlers.PacketHandler;
import com.neothedeveloper.synapser.utils.Logger;
import com.neothedeveloper.synapser.utils.MultiMap;

import java.util.Hashtable;
import java.util.List;

public class HandlerRegistry {
    private static final MultiMap<Integer, PacketHandler> m_handlers = new MultiMap<>();
    public static void register(int packetID, PacketHandler packetHandler) {
        m_handlers.add(packetID, packetHandler);
    }
    public static void runHandler(int packetID, PlayerSocket socket, InboundPacketDecoder packet) {
        List<PacketHandler> handlers = m_handlers.getMultiple(packetID);
        if (handlers.size() == 0) {
            Logger.Log(LogType.ERROR, String.format("Packet ID not handled. Packet ID: %d", packetID));
            return;
        }
        for (PacketHandler handler : handlers) {
            if (handler.allowedState() == socket.GetState()) {
                handler.handle(socket, packet);
                break;
            }
        }
    }
}
