package com.epam.library.entity;

public class Status {

    private int statusId;
    private String value;

    public Status() {
    }

    public Status(String value) {
        this.value = value;
    }

    public Status(int statusId, String value) {
        this.statusId = statusId;
        this.value = value;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Status status = (Status) obj;

        if (statusId != status.statusId) {
            return false;
        }
        return value != null ? value.equals(status.value) : status.value == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Integer.hashCode(statusId);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        line.append("Status{").append("statusId='").append(statusId).append("', value='").append(value).append('}');
        return line.toString();
    }
}
