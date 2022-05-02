package com.neothedeveloper.synapser.handlers;

import com.google.gson.JsonObject;
import com.neothedeveloper.synapser.Synapser;
import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.datatypes.VarIntLong;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.server.HandlerRegistry;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.ByteManipulation;
import com.neothedeveloper.synapser.utils.Logger;

import java.util.Arrays;

public class HandshakePacketHandler extends PacketHandler {
    public HandshakePacketHandler() {
        super(ClientState.HANDSHAKING);
    }
    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        int protocolVersion = packet.GetFieldVarInt();
        String serverAddress = packet.GetFieldString();
        int serverPort = packet.GetFieldUnsignedShort();
        socket.SetState(ClientState.fromInteger(packet.GetFieldVarInt()));
        InboundPacketDecoder leftOverPacket = new InboundPacketDecoder(packet.PacketData());
        HandlerRegistry.runHandler(leftOverPacket.PacketID(), socket, leftOverPacket);
    }
}
