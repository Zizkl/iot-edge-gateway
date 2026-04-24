package com.jwwd.gateway.common.enums;

public enum ExecuteStatus implements CodeEnum<String> {

    SUCCESS("SUCCESS", "success"),
    FAILED("FAILED", "failed");

    private final String code;
    private final String description;

    ExecuteStatus(String code, String description) {
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
