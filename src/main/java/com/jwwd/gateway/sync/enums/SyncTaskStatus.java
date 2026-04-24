package com.jwwd.gateway.sync.enums;

import com.jwwd.gateway.common.enums.CodeEnum;

public enum SyncTaskStatus implements CodeEnum<String> {

    PENDING("PENDING", "pending"),
    PROCESSING("PROCESSING", "processing"),
    SUCCESS("SUCCESS", "success"),
    FAILED("FAILED", "failed"),
    DEAD("DEAD", "dead");

    private final String code;
    private final String description;

    SyncTaskStatus(String code, String description) {
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
