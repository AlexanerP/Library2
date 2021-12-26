package com.epam.library.entity;

public enum UserRole {

    MANAGER("Manager"),
    ADMIN("Admin"),
    USER("User");

    private String value;

    UserRole(String  value) {
        this.value = value;

    }

    public String getValue() {
        return value;
    }

    public static UserRole getRoleByValue(String role) {
        if(UserRole.MANAGER.getValue().equalsIgnoreCase(role)) {
            return MANAGER;
        } else if(UserRole.ADMIN.getValue().equalsIgnoreCase(role)) {
            return ADMIN;
        }
        return USER;
    }

}
