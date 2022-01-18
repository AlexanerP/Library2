package com.epam.library.service.utill.Chiper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CipherImpl implements Cipher {

    private final static Logger logger = LoggerFactory.getLogger(CipherImpl.class);

    @Override
    public String getCipherString(String line) {
        StringBuilder lineCipher = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(line.getBytes());
            lineCipher = new StringBuilder();
            for (byte b : bytes) {
                lineCipher.append(String.format("%02X", b));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("Method getCipherString... Error build CipherString... line = {}", line);
            logger.error(e.toString());
        }
        return lineCipher.toString();
    }
}
