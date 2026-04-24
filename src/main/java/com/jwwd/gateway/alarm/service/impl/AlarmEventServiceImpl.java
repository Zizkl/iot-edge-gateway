package com.jwwd.gateway.alarm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwwd.gateway.alarm.entity.AlarmEvent;
import com.jwwd.gateway.alarm.mapper.AlarmEventMapper;
import com.jwwd.gateway.alarm.service.AlarmEventService;
import org.springframework.stereotype.Service;

@Service
public class AlarmEventServiceImpl extends ServiceImpl<AlarmEventMapper, AlarmEvent> implements AlarmEventService {
}
