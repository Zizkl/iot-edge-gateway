package com.jwwd.gateway.sync.enums;

import com.jwwd.gateway.common.enums.CodeEnum;

public enum SyncTaskType implements CodeEnum<String> {

    METRIC("METRIC", "metric data"),
    ALARM("ALARM", "alarm event"),
    ACTION_LOG("ACTION_LOG", "alarm action log");

    private final String code;
    private final String description;

    SyncTaskType(String code, String description) {
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
