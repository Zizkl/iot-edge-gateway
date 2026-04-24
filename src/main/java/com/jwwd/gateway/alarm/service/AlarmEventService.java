package com.jwwd.gateway.alarm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jwwd.gateway.alarm.entity.AlarmEvent;
import com.jwwd.gateway.alarm.entity.AlarmRule;
import com.jwwd.gateway.metric.entity.DeviceMetricData;

import java.util.Optional;

public interface AlarmEventService extends IService<AlarmEvent> {

    Optional<AlarmEvent> findActiveAlarm(String deviceCode, String metricCode);

    AlarmEvent createAlarmEvent(DeviceMetricData metricData, AlarmRule alarmRule);
}
