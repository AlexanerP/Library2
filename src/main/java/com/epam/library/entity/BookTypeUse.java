package com.epam.library.entity;

public enum BookTypeUse {
    READ_ROOM("Read room"),
    TAKE_HOME("Take home");


    private String value;

    BookTypeUse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BookTypeUse getStatus(String value) {
        if (READ_ROOM.getValue().equalsIgnoreCase(value)) {
            return READ_ROOM;
        }
        return TAKE_HOME;
    }
}
