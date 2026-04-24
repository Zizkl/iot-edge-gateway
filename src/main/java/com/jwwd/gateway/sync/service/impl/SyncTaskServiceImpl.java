package com.jwwd.gateway.sync.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwwd.gateway.sync.entity.SyncTask;
import com.jwwd.gateway.sync.mapper.SyncTaskMapper;
import com.jwwd.gateway.sync.service.SyncTaskService;
import org.springframework.stereotype.Service;

@Service
public class SyncTaskServiceImpl extends ServiceImpl<SyncTaskMapper, SyncTask> implements SyncTaskService {
}
