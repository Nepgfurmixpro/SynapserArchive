package com.neothedeveloper.synapser.handlers;

import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.datatypes.VarIntLong;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.Logger;

import static com.neothedeveloper.synapser.server.SynapserServer.GetState;
import static com.neothedeveloper.synapser.server.SynapserServer.SetState;

public class HandshakePacketHandler extends PacketHandler {
    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        StringBuilder sb = new StringBuilder();
        for (byte b : packet.PacketData()) {
            sb.append(String.format("0x%02X ", b));
        }
        Logger.Log(LogType.DEBUG, sb.toString());
        sb = new StringBuilder();
        for (byte b : VarIntLong.CreateVarInt(758)) {
            sb.append(String.format("0x%02X ", b));
        }
        Logger.Log(LogType.DEBUG, sb.toString());
        Logger.Log(LogType.DEBUG, String.format("Protocol Version: %d", packet.GetFieldVarInt()));
        Logger.Log(LogType.DEBUG, String.format("Server Address: %s", packet.GetFieldString()));
        Logger.Log(LogType.DEBUG, String.format("Server Port: %d", packet.GetFieldUnsignedShort()));
        SetState(ClientState.fromInteger(packet.GetFieldVarInt()));
        Logger.Log(LogType.DEBUG, String.format("Next State: %s", GetState().toString()));
    }
}
