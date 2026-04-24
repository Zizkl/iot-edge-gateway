package com.jwwd.gateway.alarm.service.impl;

import com.jwwd.gateway.alarm.entity.AlarmEvent;
import com.jwwd.gateway.alarm.entity.AlarmRule;
import com.jwwd.gateway.alarm.service.AlarmEventService;
import com.jwwd.gateway.alarm.service.AlarmCheckService;
import com.jwwd.gateway.alarm.service.AlarmRuleEvaluator;
import com.jwwd.gateway.alarm.service.AlarmRuleService;
import com.jwwd.gateway.metric.entity.DeviceMetricData;
import com.jwwd.gateway.sync.enums.SyncTaskType;
import com.jwwd.gateway.sync.service.SyncTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlarmCheckServiceImpl implements AlarmCheckService {

    private final AlarmRuleService alarmRuleService;
    private final AlarmRuleEvaluator alarmRuleEvaluator;
    private final AlarmEventService alarmEventService;
    private final SyncTaskService syncTaskService;

    public AlarmCheckServiceImpl(AlarmRuleService alarmRuleService,
                                 AlarmRuleEvaluator alarmRuleEvaluator,
                                 AlarmEventService alarmEventService,
                                 SyncTaskService syncTaskService) {
        this.alarmRuleService = alarmRuleService;
        this.alarmRuleEvaluator = alarmRuleEvaluator;
        this.alarmEventService = alarmEventService;
        this.syncTaskService = syncTaskService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkMetric(DeviceMetricData metricData) {
        List<AlarmRule> alarmRules = alarmRuleService.listEnabledRules(metricData);
        for (AlarmRule alarmRule : alarmRules) {
            if (!alarmRuleEvaluator.matchesAlarm(metricData, alarmRule)) {
                continue;
            }
            if (alarmEventService.findActiveAlarm(metricData.getDeviceCode(), metricData.getMetricCode()).isPresent()) {
                continue;
            }
            AlarmEvent alarmEvent = alarmEventService.createAlarmEvent(metricData, alarmRule);
            syncTaskService.createPendingTask(SyncTaskType.ALARM, alarmEvent.getId(), alarmEvent);
            break;
        }
    }
}
