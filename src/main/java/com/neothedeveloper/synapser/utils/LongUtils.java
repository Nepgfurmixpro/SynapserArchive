package com.neothedeveloper.synapser.utils;

import java.nio.ByteBuffer;

public class LongUtils {
    private static final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    public static byte[] GetBytesFromLong(long l) {
        buffer.putLong(0, l);
        buffer.flip();
        return buffer.array();
    }

    public static long GetLongFromBytes(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }
}
