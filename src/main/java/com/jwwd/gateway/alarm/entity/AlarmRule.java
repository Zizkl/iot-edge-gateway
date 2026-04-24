package com.jwwd.gateway.alarm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jwwd.gateway.common.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("alarm_rule")
public class AlarmRule extends BaseEntity {

    private String ruleName;
    private String deviceType;
    private String deviceCode;
    private String metricCode;
    private String operator;
    private BigDecimal thresholdValue;
    private String recoverOperator;
    private BigDecimal recoverValue;
    private String alarmLevel;
    private Integer debounceSeconds;
    private Integer enabled;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public BigDecimal getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(BigDecimal thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public String getRecoverOperator() {
        return recoverOperator;
    }

    public void setRecoverOperator(String recoverOperator) {
        this.recoverOperator = recoverOperator;
    }

    public BigDecimal getRecoverValue() {
        return recoverValue;
    }

    public void setRecoverValue(BigDecimal recoverValue) {
        this.recoverValue = recoverValue;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Integer getDebounceSeconds() {
        return debounceSeconds;
    }

    public void setDebounceSeconds(Integer debounceSeconds) {
        this.debounceSeconds = debounceSeconds;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
