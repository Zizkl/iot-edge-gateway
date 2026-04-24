package com.jwwd.gateway.alarm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwwd.gateway.alarm.entity.AlarmActionLog;
import com.jwwd.gateway.alarm.mapper.AlarmActionLogMapper;
import com.jwwd.gateway.alarm.service.AlarmActionLogService;
import org.springframework.stereotype.Service;

@Service
public class AlarmActionLogServiceImpl extends ServiceImpl<AlarmActionLogMapper, AlarmActionLog>
        implements AlarmActionLogService {
}
