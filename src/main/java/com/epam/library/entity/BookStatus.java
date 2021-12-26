package com.epam.library.entity;

public enum BookStatus {
    OPENED("Open"),
    USING("Using"),
    CLOSED("Closed");


    private String value;

    BookStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BookStatus getStatus(String value) {
        if (OPENED.getValue().equalsIgnoreCase(value)) {
            return OPENED;
        } else if(USING.getValue().equalsIgnoreCase(value)) {
            return USING;
        }
        return CLOSED;
    }
}
