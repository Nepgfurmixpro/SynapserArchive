package com.neothedeveloper.latte.server;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class Synapser {
    protected PrivateKey m_privKey = null;
    protected PublicKey m_pubKey = null;
    protected Cipher m_decrypter = null;
    protected Cipher m_encrypter = null;
    public void generateKeys() {
        if (m_privKey != null || m_pubKey != null) return;
        KeyPairGenerator gen = null;
        try {
            gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(1024);
            KeyPair pair = gen.generateKeyPair();
            m_privKey = pair.getPrivate();
            m_pubKey = pair.getPublic();
            m_decrypter = Cipher.getInstance("RSA");
            m_encrypter = Cipher.getInstance("RSA");
            m_decrypter.init(Cipher.DECRYPT_MODE, m_privKey);
            m_encrypter.init(Cipher.ENCRYPT_MODE, m_privKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    public byte[] decryptWithPrivate(byte[] bytes) {
        try {
            return m_decrypter.doFinal(bytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
    public byte[] encryptWithPrivate(byte[] bytes) {
        try {
            return m_encrypter.doFinal(bytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
    public PublicKey getPublicKey() {
        return m_pubKey;
    }
}
