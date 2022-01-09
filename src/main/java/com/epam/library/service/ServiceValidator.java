package com.epam.library.service;

public class ServiceValidator {

    private static final int MAX_LENGTH = 20;
    private static final String REGEX_EMAIL = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
    private final String REGEX_PASSWORD  = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";

    public boolean isLength(String line) {
        if (line != null && line.length() <= MAX_LENGTH && line != "") {
            return true;
        }
        return false;
    }

    public boolean isLengthForUpdate(String line) {
        if (line != null && line.length() <= MAX_LENGTH) {
            return true;
        }
        return false;
    }

    public boolean isNumber(String line) {
        if (line != "") {
            return line.trim().matches("\\d+");
        }
        return false;
    }

    public boolean isEmail(String email) {
        if (isLength(email)) {
            return email.trim().matches(REGEX_EMAIL);
        }
        return false;
    }

    public boolean isPassword(String password) {
        if (isLength(password)) {
            return password.trim().matches(REGEX_PASSWORD);
        }
        return false;
    }



}
