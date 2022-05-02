package com.neothedeveloper.synapser.handlers;

import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.Logger;

public class HandshakeFailurePacket extends PacketHandler {
    public HandshakeFailurePacket() {
        super(ClientState.HANDSHAKING);
    }

    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        Logger.Log(LogType.ERROR, "Client failed to handshake.");
    }
}
