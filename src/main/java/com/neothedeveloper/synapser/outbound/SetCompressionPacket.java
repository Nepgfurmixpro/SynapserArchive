package com.neothedeveloper.synapser.outbound;

import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.Logger;

public class SetCompressionPacket {
    public static void send(PlayerSocket socket) {
        OutboundPacketBuilder builder = new OutboundPacketBuilder(0x03);
        builder.AddVarIntField(-1);
        socket.Write(builder.Build());
    }
}
