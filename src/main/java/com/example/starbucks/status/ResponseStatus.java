package com.example.starbucks.status;

public enum ResponseStatus {
    SUCCESS(200, "Success"),
    FAIL(400,"Fail"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    ERROR(500, "Error");

    private int code;
    private String description;

    ResponseStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
