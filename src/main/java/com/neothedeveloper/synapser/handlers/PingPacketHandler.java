package com.neothedeveloper.synapser.handlers;

import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.server.PlayerSocket;
public class PingPacketHandler extends PacketHandler {
    public PingPacketHandler() {
        super(ClientState.STATUS);
    }
    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        socket.Write(new OutboundPacketBuilder((byte) 0x01).AddLongField(packet.GetFieldLong()).Build());
    }
}
