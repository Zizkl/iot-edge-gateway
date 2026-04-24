package com.jwwd.gateway.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jwwd.gateway.device.entity.DeviceInfo;

import java.util.List;

public interface DeviceInfoService extends IService<DeviceInfo> {

    List<DeviceInfo> listDevices();
}
