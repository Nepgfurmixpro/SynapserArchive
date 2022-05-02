package com.neothedeveloper.synapser.server;

import com.neothedeveloper.synapser.handlers.HandshakePacketHandler;
import com.neothedeveloper.synapser.handlers.PacketHandler;
import com.neothedeveloper.synapser.handlers.PingPacketHandler;
import com.neothedeveloper.synapser.handlers.StatusPacketHandler;

public class SynapserHandlers {
    public static final PacketHandler HANDSHAKE_PACKET_HANDLER = new HandshakePacketHandler();
    public static final PacketHandler STATUS_PACKET_HANDLER = new StatusPacketHandler();
    public static final PacketHandler PING_PONG_HANDLER = new PingPacketHandler();
    public static void registerAll() {
        HandlerRegistry.register(0x00, HANDSHAKE_PACKET_HANDLER);
        HandlerRegistry.register(0x00, STATUS_PACKET_HANDLER);
        HandlerRegistry.register(0x01, PING_PONG_HANDLER);
    }
}
