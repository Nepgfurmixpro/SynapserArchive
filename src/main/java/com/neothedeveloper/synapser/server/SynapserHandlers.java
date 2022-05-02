package com.neothedeveloper.synapser.server;

import com.neothedeveloper.synapser.handlers.*;

public class SynapserHandlers {
    public static final PacketHandler HANDSHAKE_PACKET_HANDLER = new HandshakePacketHandler();
    public static final PacketHandler SLP_PACKET_HANDLER = new SLPPacketHandler();
    public static final PacketHandler LEGACY_SLP_PACKET_HANDLER = new LegacySLPPacketHandler();
    public static final PacketHandler PING_PONG_HANDLER = new PingPacketHandler();
    public static void registerAll() {
        HandlerRegistry.register(0x00, HANDSHAKE_PACKET_HANDLER);
        HandlerRegistry.register(0x00, SLP_PACKET_HANDLER);
        HandlerRegistry.register(0xFE, LEGACY_SLP_PACKET_HANDLER);
        HandlerRegistry.register(0x01, PING_PONG_HANDLER);
    }
}
