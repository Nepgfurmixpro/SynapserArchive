package com.neothedeveloper.synapser.handlers;

import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.Logger;

import java.util.Arrays;

public class LoginPluginPacketHandler extends PacketHandler {
    public LoginPluginPacketHandler() {
        super(ClientState.LOGIN);
    }

    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        Logger.Log(LogType.PACKET_EVENT, String.format("Message ID: %d", packet.GetFieldVarInt()));
        Logger.Log(LogType.PACKET_EVENT, String.format("Channel: %s", packet.GetFieldString()));
        Logger.Log(LogType.PACKET_EVENT, String.format("Data: %s", Arrays.toString(packet.GetFieldByteArray(5))));
    }
}
