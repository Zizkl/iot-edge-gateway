package com.jwwd.gateway.metric.enums;

import com.jwwd.gateway.common.enums.CodeEnum;

public enum MetricSyncStatus implements CodeEnum<Integer> {

    PENDING(0, "pending"),
    SUCCESS(1, "success"),
    FAILED(2, "failed");

    private final Integer code;
    private final String description;

    MetricSyncStatus(Integer code, String description) {
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
