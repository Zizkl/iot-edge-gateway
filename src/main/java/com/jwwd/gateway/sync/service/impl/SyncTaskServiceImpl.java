package com.jwwd.gateway.sync.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jwwd.gateway.sync.entity.SyncTask;
import com.jwwd.gateway.sync.enums.SyncTaskStatus;
import com.jwwd.gateway.sync.enums.SyncTaskType;
import com.jwwd.gateway.sync.mapper.SyncTaskMapper;
import com.jwwd.gateway.sync.service.SyncTaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SyncTaskServiceImpl extends ServiceImpl<SyncTaskMapper, SyncTask> implements SyncTaskService {

    private static final int DEFAULT_MAX_RETRY_COUNT = 5;

    private final ObjectMapper objectMapper;

    public SyncTaskServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public SyncTask createPendingTask(SyncTaskType taskType, Long businessId, Object payload) {
        SyncTask syncTask = new SyncTask();
        syncTask.setTaskType(taskType.getCode());
        syncTask.setBusinessId(businessId);
        syncTask.setIdempotentKey(taskType.getCode() + ":" + businessId);
        syncTask.setPayload(toJson(payload));
        syncTask.setStatus(SyncTaskStatus.PENDING.getCode());
        syncTask.setRetryCount(0);
        syncTask.setMaxRetryCount(DEFAULT_MAX_RETRY_COUNT);
        syncTask.setNextRetryTime(LocalDateTime.now());
        save(syncTask);
        return syncTask;
    }

    private String toJson(Object payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException("Failed to serialize sync payload", ex);
        }
    }
}
