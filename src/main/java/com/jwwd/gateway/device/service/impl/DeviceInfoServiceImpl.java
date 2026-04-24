package com.jwwd.gateway.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwwd.gateway.device.entity.DeviceInfo;
import com.jwwd.gateway.device.mapper.DeviceInfoMapper;
import com.jwwd.gateway.device.service.DeviceInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo> implements DeviceInfoService {

    @Override
    public List<DeviceInfo> listDevices() {
        return list(new LambdaQueryWrapper<DeviceInfo>().orderByDesc(DeviceInfo::getCreateTime));
    }
}
