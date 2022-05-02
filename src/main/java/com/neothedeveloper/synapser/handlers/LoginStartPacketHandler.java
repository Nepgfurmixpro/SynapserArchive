package com.neothedeveloper.synapser.handlers;

import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.outbound.LoginPacket;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.Logger;

public class LoginStartPacketHandler extends PacketHandler {
    public LoginStartPacketHandler() {
        super(ClientState.LOGIN);
    }

    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        socket.SetUsername(packet.GetFieldString());
        Logger.Log(LogType.DEBUG, socket.GetUsername());
        LoginPacket.send(socket);
    }
}
