package com.jwwd.gateway.alarm.service.impl;

import com.jwwd.gateway.alarm.entity.AlarmRule;
import com.jwwd.gateway.alarm.enums.RuleOperator;
import com.jwwd.gateway.alarm.service.AlarmRuleEvaluator;
import com.jwwd.gateway.common.enums.EnabledStatus;
import com.jwwd.gateway.metric.entity.DeviceMetricData;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class AlarmRuleEvaluatorImpl implements AlarmRuleEvaluator {

    @Override
    public boolean matchesAlarm(DeviceMetricData metricData, AlarmRule alarmRule) {
        if (!matchesBasicCondition(metricData, alarmRule)) {
            return false;
        }
        return matches(alarmRule.getOperator(), metricData.getMetricValue(), alarmRule.getThresholdValue());
    }

    @Override
    public boolean matchesRecovery(DeviceMetricData metricData, AlarmRule alarmRule) {
        if (!matchesBasicCondition(metricData, alarmRule)) {
            return false;
        }
        if (!StringUtils.hasText(alarmRule.getRecoverOperator()) || alarmRule.getRecoverValue() == null) {
            return !matchesAlarm(metricData, alarmRule);
        }
        return matches(alarmRule.getRecoverOperator(), metricData.getMetricValue(), alarmRule.getRecoverValue());
    }

    private boolean matchesBasicCondition(DeviceMetricData metricData, AlarmRule alarmRule) {
        if (metricData == null || alarmRule == null) {
            return false;
        }
        if (!Objects.equals(alarmRule.getEnabled(), EnabledStatus.ENABLED.getCode())) {
            return false;
        }
        if (!Objects.equals(metricData.getMetricCode(), alarmRule.getMetricCode())) {
            return false;
        }
        return !StringUtils.hasText(alarmRule.getDeviceCode())
                || Objects.equals(metricData.getDeviceCode(), alarmRule.getDeviceCode());
    }

    private boolean matches(String operatorCode, BigDecimal actual, BigDecimal expected) {
        if (!StringUtils.hasText(operatorCode) || actual == null || expected == null) {
            return false;
        }
        return RuleOperator.fromCode(operatorCode).matches(actual, expected);
    }
}
