package com.jwwd.gateway.protocol;

public interface DeviceProtocolHandler {

    boolean support(DeviceProtocolContext context);

    DeviceMessage parse(byte[] payload);

    byte[] buildCommand(DeviceCommand command);
}
