package com.neothedeveloper.synapser.outbound;

import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.Logger;

import java.nio.charset.StandardCharsets;

public class LoginSuccessPacket {
    public static void send(PlayerSocket socket) {
        OutboundPacketBuilder builder = new OutboundPacketBuilder(0x02);
        builder.AddUUIDField(socket.GetUUID());
        builder.AddStringField(socket.GetUsername());
        socket.Write(builder.Build());
        socket.SetState(ClientState.PLAY);
        JoinGamePacket.send(socket);
    }
}
