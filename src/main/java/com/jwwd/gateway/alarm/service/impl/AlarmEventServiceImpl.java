package com.jwwd.gateway.alarm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwwd.gateway.alarm.entity.AlarmRule;
import com.jwwd.gateway.alarm.entity.AlarmEvent;
import com.jwwd.gateway.alarm.enums.AlarmStatus;
import com.jwwd.gateway.alarm.mapper.AlarmEventMapper;
import com.jwwd.gateway.alarm.service.AlarmEventService;
import com.jwwd.gateway.metric.entity.DeviceMetricData;
import com.jwwd.gateway.metric.enums.MetricSyncStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlarmEventServiceImpl extends ServiceImpl<AlarmEventMapper, AlarmEvent> implements AlarmEventService {

    private static final DateTimeFormatter ALARM_NO_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    public Optional<AlarmEvent> findActiveAlarm(String deviceCode, String metricCode) {
        AlarmEvent alarmEvent = getOne(new LambdaQueryWrapper<AlarmEvent>()
                .eq(AlarmEvent::getDeviceCode, deviceCode)
                .eq(AlarmEvent::getMetricCode, metricCode)
                .eq(AlarmEvent::getAlarmStatus, AlarmStatus.ALARMING.getCode())
                .last("LIMIT 1"), false);
        return Optional.ofNullable(alarmEvent);
    }

    @Override
    public AlarmEvent createAlarmEvent(DeviceMetricData metricData, AlarmRule alarmRule) {
        AlarmEvent alarmEvent = new AlarmEvent();
        alarmEvent.setAlarmNo(generateAlarmNo());
        alarmEvent.setDeviceCode(metricData.getDeviceCode());
        alarmEvent.setMetricCode(metricData.getMetricCode());
        alarmEvent.setAlarmLevel(alarmRule.getAlarmLevel());
        alarmEvent.setAlarmStatus(AlarmStatus.ALARMING.getCode());
        alarmEvent.setTriggerValue(metricData.getMetricValue());
        alarmEvent.setThresholdValue(alarmRule.getThresholdValue());
        alarmEvent.setTriggerTime(metricData.getCollectTime());
        alarmEvent.setLastMetricDataId(metricData.getId());
        alarmEvent.setSyncStatus(MetricSyncStatus.PENDING.getCode());
        save(alarmEvent);
        return alarmEvent;
    }

    @Override
    public AlarmEvent recoverAlarmEvent(AlarmEvent alarmEvent, DeviceMetricData metricData) {
        alarmEvent.setAlarmStatus(AlarmStatus.RECOVERED.getCode());
        alarmEvent.setRecoverValue(metricData.getMetricValue());
        alarmEvent.setRecoverTime(metricData.getCollectTime());
        alarmEvent.setLastMetricDataId(metricData.getId());
        alarmEvent.setSyncStatus(MetricSyncStatus.PENDING.getCode());
        updateById(alarmEvent);
        return alarmEvent;
    }

    private String generateAlarmNo() {
        return "AL" + LocalDateTime.now().format(ALARM_NO_TIME_FORMATTER)
                + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }
}
