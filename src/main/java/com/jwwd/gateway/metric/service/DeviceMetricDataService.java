package com.jwwd.gateway.metric.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jwwd.gateway.metric.entity.DeviceMetricData;

import java.util.List;

public interface DeviceMetricDataService extends IService<DeviceMetricData> {

    List<DeviceMetricData> listLatest(Integer limit);
}
