package com.neothedeveloper.synapser.handlers;

import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.server.PlayerSocket;

public class LegacySLPPacketHandler extends PacketHandler {
    public LegacySLPPacketHandler() {
        super(ClientState.HANDSHAKING);
    }

    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        socket.SetState(ClientState.STATUS);
    }
}
