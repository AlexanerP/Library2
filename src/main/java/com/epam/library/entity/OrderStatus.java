package com.epam.library.entity;

public enum OrderStatus {

    OPENED("Открыта"),
    CONSIDERATION("Рассмотрение"),
    SENT("Отправлено"),
    AWAITING("В ожидании"),
    RETURN("Возвращение"),
    CLOSED("Закрыто"),
    DELETE("Удалён");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static OrderStatus getStatus(String value) {
        if (OPENED.getValue().equalsIgnoreCase(value)) {
            return OPENED;
        } else if (CONSIDERATION.getValue().equalsIgnoreCase(value)) {
            return CONSIDERATION;
        } else if (SENT.getValue().equalsIgnoreCase(value)) {
            return SENT;
        } else if (AWAITING.getValue().equalsIgnoreCase(value)) {
            return AWAITING;
        } else if (RETURN.getValue().equalsIgnoreCase(value)) {
            return RETURN;
        } else if(CLOSED.getValue().equalsIgnoreCase(value)) {
            return CLOSED;
        }
        return DELETE;
    }
}
