package com.neothedeveloper.synapser.handlers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.neothedeveloper.latte.Latte;
import com.neothedeveloper.synapser.builders.OutboundPacketBuilder;
import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.minecraft.datatypes.Texture;
import com.neothedeveloper.synapser.minecraft.utils.ChatParser;
import com.neothedeveloper.synapser.minecraft.utils.SHA1;
import com.neothedeveloper.synapser.outbound.LoginSuccessPacket;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.util.UUID;

public class EncryptionPacketHandler extends PacketHandler {
    public EncryptionPacketHandler() {
        super(ClientState.LOGIN);
    }

    @Override
    public void handle(PlayerSocket socket, InboundPacketDecoder packet) {
        int secretLength = packet.GetFieldVarInt();
        byte[] secret = packet.GetFieldByteArray(secretLength);
        int verifyLength = packet.GetFieldVarInt();
        byte[] verifyToken = packet.GetFieldByteArray(verifyLength);
        byte[] sk = Latte.synapser().decryptWithPrivate(secret);

        byte[] pk = Latte.synapser().getPublicKey().getEncoded();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(sk);
            baos.write(pk);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        byte[] serverIdHash = SHA1.generate(baos.toByteArray());

        byte[] decryptedVerifyToken = Latte.synapser().decryptWithPrivate(verifyToken);
        if (!socket.checkVerifyToken(decryptedVerifyToken))
            socket.Write(new OutboundPacketBuilder(0x00).AddChatField(ChatParser.ParseWithCodeChar('&', "Client couldn't be authenticated", true)).Build());
        String authURLString = String.format("https://sessionserver.mojang.com/session/minecraft/hasJoined?username=%s&serverId=%s", socket.GetUsername(), new BigInteger(serverIdHash).toString(16));
        try {
            URL authURL = new URL(authURLString);
            HttpsURLConnection con = (HttpsURLConnection) authURL.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            JsonObject obj = (JsonObject) JsonParser.parseString(content.toString());
            if (status != 200) {
                socket.Close(ChatParser.ParseString("Failed to login: Invalid session (Try restarting your game and the launcher)"));
                return;
            }
            String uuidString = obj.get("id").getAsString().replaceFirst("([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)", "$1-$2-$3-$4-$5");
            socket.SetUUID(UUID.fromString(uuidString));
            JsonObject property0 = (JsonObject) obj.get("properties").getAsJsonArray().get(0);
            socket.SetTexture(new Texture(property0.get("value").getAsString(), property0.get("signature").getAsString()));
            Logger.Log(LogType.USER_AUTHENTICATOR, String.format("UUID of player %s is %s", socket.GetUsername(), socket.GetUUID().toString()));
            LoginSuccessPacket.send(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
