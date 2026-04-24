package com.jwwd.gateway.alarm.enums;

import com.jwwd.gateway.common.enums.CodeEnum;

public enum AlarmStatus implements CodeEnum<String> {

    ALARMING("ALARMING", "alarming"),
    RECOVERED("RECOVERED", "recovered");

    private final String code;
    private final String description;

    AlarmStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
