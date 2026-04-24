package com.jwwd.gateway.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwwd.gateway.operation.entity.OperationLog;
import com.jwwd.gateway.operation.mapper.OperationLogMapper;
import com.jwwd.gateway.operation.service.OperationLogService;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
}
