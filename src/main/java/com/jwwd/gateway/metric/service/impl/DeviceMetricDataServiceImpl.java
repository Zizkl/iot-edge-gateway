package com.jwwd.gateway.metric.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwwd.gateway.metric.entity.DeviceMetricData;
import com.jwwd.gateway.metric.mapper.DeviceMetricDataMapper;
import com.jwwd.gateway.metric.service.DeviceMetricDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceMetricDataServiceImpl extends ServiceImpl<DeviceMetricDataMapper, DeviceMetricData>
        implements DeviceMetricDataService {

    @Override
    public List<DeviceMetricData> listLatest(Integer limit) {
        int safeLimit = Math.max(1, Math.min(limit == null ? 50 : limit, 200));
        return list(new LambdaQueryWrapper<DeviceMetricData>()
                .orderByDesc(DeviceMetricData::getCollectTime)
                .last("LIMIT " + safeLimit));
    }
}
