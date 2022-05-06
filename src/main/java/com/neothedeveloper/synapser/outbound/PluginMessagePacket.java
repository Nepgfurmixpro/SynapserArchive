package com.neothedeveloper.synapser.outbound;

import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.server.PlayerSocket;

public class PluginMessagePacket {
    public static void send(PlayerSocket socket, String channel, byte[] bytes) {
        OutboundPacketBuilder builder = new OutboundPacketBuilder(0x18);
        builder.AddStringField(channel);
        builder.AddField(bytes);
        socket.Write(builder.Build());
    }
}
