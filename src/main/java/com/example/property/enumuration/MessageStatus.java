package com.example.property.enumuration;

public enum MessageStatus {
    Scheduled(" "),
    Sent(" "),
    Draft(" "),
    Canceled(" "),
    FAILED("Operation is failed"),
    SUCCESSFUL("Operation is successful");

    private String value;

    MessageStatus(String value) {
        this.value = value;
    }
}
