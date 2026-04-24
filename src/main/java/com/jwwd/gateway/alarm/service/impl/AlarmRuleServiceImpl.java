package com.jwwd.gateway.alarm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwwd.gateway.alarm.entity.AlarmRule;
import com.jwwd.gateway.alarm.mapper.AlarmRuleMapper;
import com.jwwd.gateway.alarm.service.AlarmRuleService;
import com.jwwd.gateway.common.enums.EnabledStatus;
import com.jwwd.gateway.metric.entity.DeviceMetricData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmRuleServiceImpl extends ServiceImpl<AlarmRuleMapper, AlarmRule> implements AlarmRuleService {

    @Override
    public List<AlarmRule> listEnabledRules(DeviceMetricData metricData) {
        return list(new LambdaQueryWrapper<AlarmRule>()
                .eq(AlarmRule::getEnabled, EnabledStatus.ENABLED.getCode())
                .eq(AlarmRule::getMetricCode, metricData.getMetricCode())
                .and(wrapper -> wrapper
                        .isNull(AlarmRule::getDeviceCode)
                        .or()
                        .eq(AlarmRule::getDeviceCode, metricData.getDeviceCode())));
    }
}
