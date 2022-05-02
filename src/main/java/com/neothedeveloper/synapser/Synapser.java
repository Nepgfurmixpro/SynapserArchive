package com.neothedeveloper.synapser;

import com.neothedeveloper.synapser.exceptions.PropertyException;
import com.neothedeveloper.synapser.server.SynapserServer;
import com.neothedeveloper.synapser.datatypes.LogType;
import com.neothedeveloper.synapser.utils.Logger;
import com.neothedeveloper.synapser.utils.Properties;

import java.io.*;

public class Synapser {
    public static void main(String[] args) throws Exception {
        new Synapser().Start();
    }
    public static Properties SERVER_PROPERTIES;
    public static String SERVER_NAME = "Synapser";
    SynapserServer m_server;
    public void Start() throws PropertyException {
        SERVER_PROPERTIES = new Properties();
        Logger.Log(LogType.LOG, String.format("Starting \"%s\" Server", SERVER_NAME));
        try {
            if (new File("server.properties").createNewFile()) {
                FileWriter writer = new FileWriter("server.properties");
                String output = "";
                output += "server-ip=\n";
                output += "server-port=25565\n";
                output += "motd=A Synapser Minecraft Server\n";
                output += "max-players=20\n";
                writer.write(output);
                writer.close();
            }
            File serverProps = new File("server.properties");
            SERVER_PROPERTIES.ReadProps(serverProps);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String ip = SERVER_PROPERTIES.GetProperty("server-ip").equals("") ? "0.0.0.0" : SERVER_PROPERTIES.GetProperty("server-ip");
        // Confirm that all server properties are valid
        if (SERVER_PROPERTIES.GetProperty("server-ip").matches("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"))
            throw new PropertyException("server-ip is invalid.");
        if (Integer.parseInt(SERVER_PROPERTIES.GetProperty("server-port")) > 65535)
            throw new PropertyException("server-port is invalid");
        if (!(Integer.parseInt(SERVER_PROPERTIES.GetProperty("max-players")) < Integer.MAX_VALUE))
            throw new PropertyException("max-players is invalid");


        this.m_server = new SynapserServer(ip, SERVER_PROPERTIES.GetProperty("server-port"));
        try {
            this.m_server.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
