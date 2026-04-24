package com.jwwd.gateway.metric.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwwd.gateway.alarm.service.AlarmCheckService;
import com.jwwd.gateway.common.util.HashUtils;
import com.jwwd.gateway.metric.entity.DeviceMetricData;
import com.jwwd.gateway.metric.enums.MetricSyncStatus;
import com.jwwd.gateway.metric.service.DeviceMetricDataService;
import com.jwwd.gateway.metric.service.MetricIngestService;
import com.jwwd.gateway.protocol.DeviceMessage;
import com.jwwd.gateway.sync.entity.SyncTask;
import com.jwwd.gateway.sync.enums.SyncTaskStatus;
import com.jwwd.gateway.sync.enums.SyncTaskType;
import com.jwwd.gateway.sync.service.SyncTaskService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class MetricIngestServiceImpl implements MetricIngestService {

    private final DeviceMetricDataService metricDataService;
    private final SyncTaskService syncTaskService;
    private final AlarmCheckService alarmCheckService;
    private final ObjectMapper objectMapper;

    public MetricIngestServiceImpl(DeviceMetricDataService metricDataService,
                                   SyncTaskService syncTaskService,
                                   AlarmCheckService alarmCheckService,
                                   ObjectMapper objectMapper) {
        this.metricDataService = metricDataService;
        this.syncTaskService = syncTaskService;
        this.alarmCheckService = alarmCheckService;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeviceMetricData ingest(DeviceMessage message) {
        DeviceMetricData metricData = buildMetricData(message);
        try {
            metricDataService.save(metricData);
        } catch (DuplicateKeyException ex) {
            return metricDataService.getOne(
                    Wrappers.<DeviceMetricData>lambdaQuery().eq(DeviceMetricData::getDataHash, metricData.getDataHash()),
                    false
            );
        }

        createMetricSyncTask(metricData);
        alarmCheckService.checkMetric(metricData);
        return metricData;
    }

    private DeviceMetricData buildMetricData(DeviceMessage message) {
        LocalDateTime collectTime = message.collectTime() == null ? LocalDateTime.now() : message.collectTime();

        DeviceMetricData metricData = new DeviceMetricData();
        metricData.setDeviceCode(message.deviceCode());
        metricData.setMetricCode(message.metricCode());
        metricData.setMetricValue(message.metricValue());
        metricData.setUnit(message.unit());
        metricData.setCollectTime(collectTime);
        metricData.setReceiveTime(LocalDateTime.now());
        metricData.setRawPayload(message.rawPayload());
        metricData.setSyncStatus(MetricSyncStatus.PENDING.getCode());
        metricData.setDataHash(buildDataHash(message, collectTime));
        return metricData;
    }

    private String buildDataHash(DeviceMessage message, LocalDateTime collectTime) {
        String rawPayload = StringUtils.hasText(message.rawPayload()) ? message.rawPayload() : "";
        return HashUtils.sha256(message.deviceCode()
                + "|"
                + message.metricCode()
                + "|"
                + collectTime
                + "|"
                + rawPayload);
    }

    private void createMetricSyncTask(DeviceMetricData metricData) {
        SyncTask syncTask = new SyncTask();
        syncTask.setTaskType(SyncTaskType.METRIC.getCode());
        syncTask.setBusinessId(metricData.getId());
        syncTask.setIdempotentKey(SyncTaskType.METRIC.getCode() + ":" + metricData.getId());
        syncTask.setPayload(toJson(metricData));
        syncTask.setStatus(SyncTaskStatus.PENDING.getCode());
        syncTask.setRetryCount(0);
        syncTask.setMaxRetryCount(5);
        syncTask.setNextRetryTime(LocalDateTime.now());
        syncTaskService.save(syncTask);
    }

    private String toJson(DeviceMetricData metricData) {
        try {
            return objectMapper.writeValueAsString(metricData);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException("Failed to serialize metric sync payload", ex);
        }
    }
}
