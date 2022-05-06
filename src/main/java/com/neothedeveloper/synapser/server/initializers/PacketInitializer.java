package com.neothedeveloper.synapser.server.initializers;

import com.neothedeveloper.synapser.handlers.*;
import com.neothedeveloper.synapser.server.HandlerRegistry;

public class PacketInitializer {
    public static final PacketHandler HANDSHAKE_PACKET_HANDLER = new HandshakePacketHandler();
    public static final PacketHandler SLP_PACKET_HANDLER = new SLPPacketHandler();
    public static final PacketHandler LEGACY_SLP_PACKET_HANDLER = new LegacySLPPacketHandler();
    public static final PacketHandler HANDSHAKE_FAILURE_PACKET = new HandshakeFailurePacket();
    public static final PacketHandler PING_PONG_HANDLER = new PingPacketHandler();
    public static final PacketHandler ENCRYPTION_PACKET_HANDLER = new EncryptionPacketHandler();
    public static final PacketHandler LOGIN_START_PACKET_HANDLER = new LoginStartPacketHandler();
    public static void initialize() {
        HandlerRegistry.register(0x00, HANDSHAKE_PACKET_HANDLER);
        HandlerRegistry.register(0x00, SLP_PACKET_HANDLER);
        HandlerRegistry.register(0xFE, LEGACY_SLP_PACKET_HANDLER);
        HandlerRegistry.register(0x7A, HANDSHAKE_FAILURE_PACKET);
        HandlerRegistry.register(0x01, PING_PONG_HANDLER);
        HandlerRegistry.register(0x01, ENCRYPTION_PACKET_HANDLER);
        HandlerRegistry.register(0x00, LOGIN_START_PACKET_HANDLER);
    }
}
