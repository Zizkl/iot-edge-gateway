package com.jwwd.gateway.alarm.enums;

import com.jwwd.gateway.common.enums.CodeEnum;

public enum ActionType implements CodeEnum<String> {

    BROADCAST("BROADCAST", "broadcast"),
    LIGHT("LIGHT", "light");

    private final String code;
    private final String description;

    ActionType(String code, String description) {
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
