package com.example.starbucks.status;

public enum ResultStatus {
    SUCCESS("SUCCESS"),
    FAIL("FAIL");

    private String result;

    ResultStatus(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
