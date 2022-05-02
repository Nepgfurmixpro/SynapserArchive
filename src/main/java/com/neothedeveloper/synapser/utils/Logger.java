package com.neothedeveloper.synapser.utils;

import com.neothedeveloper.synapser.Synapser;
import com.neothedeveloper.synapser.datatypes.LogType;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static String GetLogType(LogType type) {
        String out = type.toString();
        return String.format("[%s -> %s] : ", Synapser.SERVER_NAME, out);
    }
    public static void Log(LogType type, String msg) {
        System.out.printf("%s %s\n", GetLogType(type), msg);
    }
}
