package com.jwwd.gateway.sync.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jwwd.gateway.sync.entity.SyncTask;
import com.jwwd.gateway.sync.enums.SyncTaskType;

public interface SyncTaskService extends IService<SyncTask> {

    SyncTask createPendingTask(SyncTaskType taskType, Long businessId, Object payload);
}
