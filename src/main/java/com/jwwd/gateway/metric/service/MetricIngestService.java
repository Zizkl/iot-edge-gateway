package com.jwwd.gateway.metric.service;

import com.jwwd.gateway.metric.entity.DeviceMetricData;
import com.jwwd.gateway.protocol.DeviceMessage;

public interface MetricIngestService {

    DeviceMetricData ingest(DeviceMessage message);
}
