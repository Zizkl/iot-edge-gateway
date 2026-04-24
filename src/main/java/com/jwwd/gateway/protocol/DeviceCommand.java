package com.jwwd.gateway.protocol;

public record DeviceCommand(String deviceCode, String commandType, String payload) {
}
