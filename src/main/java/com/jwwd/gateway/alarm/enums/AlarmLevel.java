package com.jwwd.gateway.alarm.enums;

import com.jwwd.gateway.common.enums.CodeEnum;

public enum AlarmLevel implements CodeEnum<String> {

    LOW("LOW", "low"),
    MEDIUM("MEDIUM", "medium"),
    HIGH("HIGH", "high"),
    CRITICAL("CRITICAL", "critical");

    private final String code;
    private final String description;

    AlarmLevel(String code, String description) {
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
