package com.neothedeveloper.synapser.utils;

import com.neothedeveloper.synapser.Synapser;
import com.neothedeveloper.synapser.datatypes.LogType;

public class Logger {
    private static String GetLogType(LogType type) {
        String out = "UNKNOWN";
        switch (type) {
            case LOG -> {
                out = "LOG";
            }
            case DEBUG -> {
                out = "DEBUG";
            }
            case ERROR -> {
                out = "ERROR";
            }
            case EVENT -> {
                out = "EVENT";
            }
            case VERBOSE -> {
                out = "VERBOSE";
            }
            case VERBOSE_DEBUG -> {
                out = "VERBOSE_DEBUG";
            }
        }
        return String.format("[%s/%s]", Synapser.SERVER_NAME, out);
    }
    public static void Log(LogType type, String msg) {
        System.out.printf("%s %s\n", GetLogType(type), msg);
    }
}
