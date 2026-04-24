package com.jwwd.gateway.metric.controller;

import com.jwwd.gateway.common.ApiResponse;
import com.jwwd.gateway.metric.entity.DeviceMetricData;
import com.jwwd.gateway.metric.service.DeviceMetricDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    private final DeviceMetricDataService metricDataService;

    public MetricController(DeviceMetricDataService metricDataService) {
        this.metricDataService = metricDataService;
    }

    @GetMapping("/latest")
    public ApiResponse<List<DeviceMetricData>> latest(@RequestParam(defaultValue = "50") Integer limit) {
        return ApiResponse.ok(metricDataService.listLatest(limit));
    }
}
