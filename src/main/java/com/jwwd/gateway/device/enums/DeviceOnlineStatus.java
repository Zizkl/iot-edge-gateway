package com.jwwd.gateway.device.enums;

import com.jwwd.gateway.common.enums.CodeEnum;

public enum DeviceOnlineStatus implements CodeEnum<Integer> {

    OFFLINE(0, "offline"),
    ONLINE(1, "online");

    private final Integer code;
    private final String description;

    DeviceOnlineStatus(Integer code, String description) {
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
