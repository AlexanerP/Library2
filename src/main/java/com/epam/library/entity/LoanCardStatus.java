package com.epam.library.entity;

public enum LoanCardStatus {
    OPEN("Open"),
    CLOSED("Closed");

    private String value;

    LoanCardStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LoanCardStatus getStatus(String value) {
        if (OPEN.getValue().equalsIgnoreCase(value)) {
            return OPEN;
        }
            return CLOSED;
    }
}
