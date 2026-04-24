package com.jwwd.gateway.metric.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jwwd.gateway.common.ApiResponse;
import com.jwwd.gateway.metric.entity.DeviceMetricData;
import com.jwwd.gateway.metric.mapper.DeviceMetricDataMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    private final DeviceMetricDataMapper metricDataMapper;

    public MetricController(DeviceMetricDataMapper metricDataMapper) {
        this.metricDataMapper = metricDataMapper;
    }

    @GetMapping("/latest")
    public ApiResponse<List<DeviceMetricData>> latest(@RequestParam(defaultValue = "50") Integer limit) {
        int safeLimit = Math.max(1, Math.min(limit, 200));
        List<DeviceMetricData> data = metricDataMapper.selectList(
                new LambdaQueryWrapper<DeviceMetricData>()
                        .orderByDesc(DeviceMetricData::getCollectTime)
                        .last("LIMIT " + safeLimit)
        );
        return ApiResponse.ok(data);
    }
}
