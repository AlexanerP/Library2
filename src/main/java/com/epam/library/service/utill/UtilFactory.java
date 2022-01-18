package com.epam.library.service.utill;

import com.epam.library.service.utill.Chiper.Cipher;
import com.epam.library.service.utill.Chiper.CipherImpl;

public class UtilFactory {

    private static UtilFactory instance = new UtilFactory();
    private static Cipher cipher = new CipherImpl();

    public static UtilFactory getInstance() {
        return instance;
    }

    public Cipher getCipher() {
        return cipher;
    }
}
