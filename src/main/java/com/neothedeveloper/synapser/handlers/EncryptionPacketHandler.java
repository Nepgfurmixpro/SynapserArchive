package com.neothedeveloper.synapser.handlers;

import com.neothedeveloper.latte.Latte;
import com.neothedeveloper.synapser.datatypes.ClientState;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.decoders.InboundPacketDecoder;
import com.neothedeveloper.synapser.minecraft.utils.BrokenHash;
import com.neothedeveloper.synapser.outbound.LoginPluginPacket;
import com.neothedeveloper.synapser.server.PlayerSocket;
import com.neothedeveloper.synapser.utils.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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
        byte[] decryptedVerifyToken = Latte.synapser().decryptWithPrivate(verifyToken);
        String authURLString = String.format("https://sessionserver.mojang.com/session/minecraft/hasJoined?username=%s&serverId=%s", socket.GetUsername(), BrokenHash.hash(""));
        try {
            URL authURL = new URL(authURLString);
            HttpsURLConnection con = (HttpsURLConnection) authURL.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            Logger.Log(LogType.DEBUG, content.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
