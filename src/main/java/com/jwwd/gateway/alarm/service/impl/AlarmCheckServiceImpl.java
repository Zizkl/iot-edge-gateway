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
import java.util.Optional;

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
            Optional<AlarmEvent> activeAlarm = alarmEventService.findActiveAlarm(
                    metricData.getDeviceCode(),
                    metricData.getMetricCode()
            );
            if (activeAlarm.isPresent()) {
                recoverIfNeeded(metricData, alarmRule, activeAlarm.get());
                continue;
            }
            if (!alarmRuleEvaluator.matchesAlarm(metricData, alarmRule)) {
                continue;
            }
            AlarmEvent alarmEvent = alarmEventService.createAlarmEvent(metricData, alarmRule);
            syncTaskService.createPendingTask(SyncTaskType.ALARM, alarmEvent.getId(), alarmEvent);
            break;
        }
    }

    private void recoverIfNeeded(DeviceMetricData metricData, AlarmRule alarmRule, AlarmEvent activeAlarm) {
        if (!alarmRuleEvaluator.matchesRecovery(metricData, alarmRule)) {
            return;
        }
        AlarmEvent recoveredAlarm = alarmEventService.recoverAlarmEvent(activeAlarm, metricData);
        syncTaskService.createPendingTask(SyncTaskType.ALARM, recoveredAlarm.getId(), recoveredAlarm);
    }
}
