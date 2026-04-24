package com.jwwd.gateway.protocol;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DeviceMessage(
        String deviceCode,
        String deviceType,
        String messageType,
        String metricCode,
        BigDecimal metricValue,
        String unit,
        LocalDateTime collectTime,
        String rawPayload
) {
}
