package com.neothedeveloper.synapser.outbound;

import com.neothedeveloper.latte.Latte;
import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.server.PlayerSocket;

import java.util.Random;

public class LoginPacket {
    public static void send(PlayerSocket socket) {
        OutboundPacketBuilder builder = new OutboundPacketBuilder(0x01);
        builder.AddStringField("");
        byte[] pubKey = Latte.synapser().getPublicKey().getEncoded();
        builder.AddVarIntField(pubKey.length);
        builder.AddField(pubKey);
        byte[] token = new byte[4];
        Random r = new Random();
        r.nextBytes(token);
        builder.AddVarIntField(token.length);
        builder.AddField(token);
        socket.SetVerifyToken(token);
        socket.Write(builder.Build());
    }
}
