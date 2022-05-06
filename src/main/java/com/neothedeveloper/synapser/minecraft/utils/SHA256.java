package com.neothedeveloper.synapser.minecraft.utils;

import com.neothedeveloper.synapser.utils.ByteManipulation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public static byte[] generate(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
