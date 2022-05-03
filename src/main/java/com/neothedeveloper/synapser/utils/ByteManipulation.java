package com.neothedeveloper.synapser.utils;

public class ByteManipulation {
    public static String GetByteString(byte[] bytes) {
        return GetByteString(" ", bytes);
    }
    public static String GetByteString(String separator, byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (byte b : bytes) {
            s.append(String.format("%02x%s", b, separator));
        }
        return s.toString();
    }
}
