package com.jwwd.gateway.alarm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jwwd.gateway.alarm.entity.AlarmRule;
import com.jwwd.gateway.metric.entity.DeviceMetricData;

import java.util.List;

public interface AlarmRuleService extends IService<AlarmRule> {

    List<AlarmRule> listEnabledRules(DeviceMetricData metricData);
}
