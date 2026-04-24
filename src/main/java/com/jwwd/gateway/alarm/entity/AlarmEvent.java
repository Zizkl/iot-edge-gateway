package com.jwwd.gateway.alarm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jwwd.gateway.common.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("alarm_event")
public class AlarmEvent extends BaseEntity {

    private String alarmNo;
    private String deviceCode;
    private String metricCode;
    private String alarmLevel;
    private String alarmStatus;
    private BigDecimal triggerValue;
    private BigDecimal thresholdValue;
    private LocalDateTime triggerTime;
    private BigDecimal recoverValue;
    private LocalDateTime recoverTime;
    private Long lastMetricDataId;
    private Integer syncStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public String getAlarmNo() {
        return alarmNo;
    }

    public void setAlarmNo(String alarmNo) {
        this.alarmNo = alarmNo;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getMetricCode() {
        return metricCode;
    }

    public void setMetricCode(String metricCode) {
        this.metricCode = metricCode;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public BigDecimal getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(BigDecimal triggerValue) {
        this.triggerValue = triggerValue;
    }

    public BigDecimal getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(BigDecimal thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public LocalDateTime getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(LocalDateTime triggerTime) {
        this.triggerTime = triggerTime;
    }

    public BigDecimal getRecoverValue() {
        return recoverValue;
    }

    public void setRecoverValue(BigDecimal recoverValue) {
        this.recoverValue = recoverValue;
    }

    public LocalDateTime getRecoverTime() {
        return recoverTime;
    }

    public void setRecoverTime(LocalDateTime recoverTime) {
        this.recoverTime = recoverTime;
    }

    public Long getLastMetricDataId() {
        return lastMetricDataId;
    }

    public void setLastMetricDataId(Long lastMetricDataId) {
        this.lastMetricDataId = lastMetricDataId;
    }

    public Integer getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
