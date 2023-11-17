package com.example.enums;

public enum TimerActionEnum {

    INCREMENT_COUNTER("increment"),
    RESET_COUNTER("reset");

    private final String value;

    TimerActionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TimerActionEnum fromValue(String value) {
        for (TimerActionEnum action : values()) {
            if (action.value.equals(value)) {
                return action;
            }
        }
        throw new IllegalArgumentException("Invalid TimerActionEnum value: " + value);
    }
}
