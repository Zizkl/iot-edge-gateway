package com.jwwd.gateway.device.controller;

import com.jwwd.gateway.common.ApiResponse;
import com.jwwd.gateway.device.entity.DeviceInfo;
import com.jwwd.gateway.device.service.DeviceInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceInfoService deviceInfoService;

    public DeviceController(DeviceInfoService deviceInfoService) {
        this.deviceInfoService = deviceInfoService;
    }

    @GetMapping
    public ApiResponse<List<DeviceInfo>> list() {
        return ApiResponse.ok(deviceInfoService.listDevices());
    }
}
