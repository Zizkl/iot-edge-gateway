package com.jwwd.gateway.alarm.service;

import com.jwwd.gateway.alarm.entity.AlarmRule;
import com.jwwd.gateway.metric.entity.DeviceMetricData;

public interface AlarmRuleEvaluator {

    boolean matchesAlarm(DeviceMetricData metricData, AlarmRule alarmRule);

    boolean matchesRecovery(DeviceMetricData metricData, AlarmRule alarmRule);
}
