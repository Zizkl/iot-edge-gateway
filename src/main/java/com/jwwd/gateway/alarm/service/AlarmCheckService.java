package com.jwwd.gateway.alarm.service;

import com.jwwd.gateway.metric.entity.DeviceMetricData;

public interface AlarmCheckService {

    void checkMetric(DeviceMetricData metricData);
}
