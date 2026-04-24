package com.jwwd.gateway.common.enums;

public enum EnabledStatus implements CodeEnum<Integer> {

    DISABLED(0, "disabled"),
    ENABLED(1, "enabled");

    private final Integer code;
    private final String description;

    EnabledStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
