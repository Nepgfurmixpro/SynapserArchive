package com.neothedeveloper.synapser.utils;

public class ByteManipulation {
    public static String GetByteString(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (byte b : bytes) {
            s.append(String.format("%02X ", b));
        }
        return s.toString();
    }
}
