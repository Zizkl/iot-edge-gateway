package com.jwwd.gateway.device.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jwwd.gateway.common.ApiResponse;
import com.jwwd.gateway.device.entity.DeviceInfo;
import com.jwwd.gateway.device.mapper.DeviceInfoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceInfoMapper deviceInfoMapper;

    public DeviceController(DeviceInfoMapper deviceInfoMapper) {
        this.deviceInfoMapper = deviceInfoMapper;
    }

    @GetMapping
    public ApiResponse<List<DeviceInfo>> list() {
        List<DeviceInfo> devices = deviceInfoMapper.selectList(
                new LambdaQueryWrapper<DeviceInfo>().orderByDesc(DeviceInfo::getCreateTime)
        );
        return ApiResponse.ok(devices);
    }
}
