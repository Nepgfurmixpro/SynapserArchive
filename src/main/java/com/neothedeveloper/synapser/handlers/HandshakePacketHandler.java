package com.neothedeveloper.synapser.handlers;

import com.google.gson.JsonObject;
import com.neothedeveloper.latte.Latte;
import com.neothedeveloper.synapser.Synapser;
import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.minecraft.utils.ChatParser;
import com.neothedeveloper.synapser.server.HandlerRegistry;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.Logger;

public class HandshakePacketHandler extends PacketHandler {
    public HandshakePacketHandler() {
        super(ClientState.HANDSHAKING);
    }
    private JsonObject parseInvalidVersionText(String string) {
        return ChatParser.ParseString(string.replace("{version}", Latte.server().minecraftVersion()));
    }
    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        socket.SetProtocolVersion(packet.GetFieldVarInt());
        packet.GetFieldString();
        packet.GetFieldUnsignedShort();
        socket.SetState(ClientState.fromInteger(packet.GetFieldVarInt()));
        if (socket.GetState() == ClientState.LOGIN) {
            if (socket.GetProtocolVersion() < Latte.server().protocolVersion()) {
                String msg = "Outdated client! I'm running {version}!";
                if (Synapser.SERVER_PROPERTIES.GetProperty("outdated-client-msg") != null) {
                    msg = Synapser.SERVER_PROPERTIES.GetProperty("outdated-client-msg");
                }
                socket.Close(parseInvalidVersionText(msg));
                return;
            }
            if (socket.GetProtocolVersion() > Latte.server().protocolVersion()) {
                String msg = "Outdated client! I'm still using {version}!";
                if (Synapser.SERVER_PROPERTIES.GetProperty("outdated-server-msg") != null) {
                    msg = Synapser.SERVER_PROPERTIES.GetProperty("outdated-server-msg");
                }
                socket.Close(parseInvalidVersionText(msg));
                return;
            }
        }
        if (packet.PacketData().length > 0) {
            InboundPacketDecoder leftOverPacket = new InboundPacketDecoder(packet.PacketData());
            HandlerRegistry.runHandler(leftOverPacket.PacketID(), socket, leftOverPacket);
        }
    }
}
