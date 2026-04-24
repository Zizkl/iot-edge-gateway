package com.jwwd.gateway.alarm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwwd.gateway.alarm.entity.AlarmRule;
import com.jwwd.gateway.alarm.mapper.AlarmRuleMapper;
import com.jwwd.gateway.alarm.service.AlarmRuleService;
import org.springframework.stereotype.Service;

@Service
public class AlarmRuleServiceImpl extends ServiceImpl<AlarmRuleMapper, AlarmRule> implements AlarmRuleService {
}
