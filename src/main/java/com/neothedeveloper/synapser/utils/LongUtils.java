package com.neothedeveloper.synapser.utils;

import java.nio.ByteBuffer;

public class LongUtils {
    private static ByteBuffer buffer;
    public static byte[] GetBytesFromLong(long l) {
        buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(0, l);
        buffer.flip();
        return buffer.array();
    }

    public static long GetLongFromBytes(byte[] bytes) {
        buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }
}
