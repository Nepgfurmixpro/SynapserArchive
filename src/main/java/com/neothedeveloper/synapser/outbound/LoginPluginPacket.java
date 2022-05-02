package com.neothedeveloper.synapser.outbound;

import com.neothedeveloper.latte.Latte;
import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.server.PlayerSocket;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class LoginPluginPacket {
    public static void send(PlayerSocket socket) {
        OutboundPacketBuilder builder = new OutboundPacketBuilder(0x04);
        builder.AddVarIntField(socket.GetMessageId());
        builder.AddStringField("synapser:main");
        builder.AddField("Hello".getBytes(StandardCharsets.UTF_8));
        socket.Write(builder.Build());
    }
}
