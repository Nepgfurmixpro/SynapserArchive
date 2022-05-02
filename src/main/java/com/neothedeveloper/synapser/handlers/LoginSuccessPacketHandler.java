package com.neothedeveloper.synapser.handlers;

import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.Logger;

import java.util.UUID;

public class LoginSuccessPacketHandler extends PacketHandler {
    public LoginSuccessPacketHandler() {
        super(ClientState.LOGIN);
    }
    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        UUID playerUUID = packet.GetFieldUUID();
        String playerName = packet.GetFieldString();
        Logger.Log(LogType.DEBUG, String.format("UUID: %s; Player Name: %s", playerUUID.toString(), playerName));
    }
}
