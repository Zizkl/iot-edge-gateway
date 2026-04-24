package com.jwwd.gateway.alarm.service.impl;

import com.jwwd.gateway.alarm.service.AlarmCheckService;
import com.jwwd.gateway.metric.entity.DeviceMetricData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AlarmCheckServiceImpl implements AlarmCheckService {

    private static final Logger log = LoggerFactory.getLogger(AlarmCheckServiceImpl.class);

    @Override
    public void checkMetric(DeviceMetricData metricData) {
        log.debug("Alarm check placeholder for metric data id: {}", metricData.getId());
    }
}
