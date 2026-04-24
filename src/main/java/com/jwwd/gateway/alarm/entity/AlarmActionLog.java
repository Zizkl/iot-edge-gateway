package com.jwwd.gateway.alarm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jwwd.gateway.common.BaseEntity;

import java.time.LocalDateTime;

@TableName("alarm_action_log")
public class AlarmActionLog extends BaseEntity {

    private Long alarmEventId;
    private String actionType;
    private String targetDeviceCode;
    private String commandPayload;
    private String executeStatus;
    private LocalDateTime executeTime;
    private String errorMessage;
    private LocalDateTime createTime;

    public Long getAlarmEventId() {
        return alarmEventId;
    }

    public void setAlarmEventId(Long alarmEventId) {
        this.alarmEventId = alarmEventId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getTargetDeviceCode() {
        return targetDeviceCode;
    }

    public void setTargetDeviceCode(String targetDeviceCode) {
        this.targetDeviceCode = targetDeviceCode;
    }

    public String getCommandPayload() {
        return commandPayload;
    }

    public void setCommandPayload(String commandPayload) {
        this.commandPayload = commandPayload;
    }

    public String getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(String executeStatus) {
        this.executeStatus = executeStatus;
    }

    public LocalDateTime getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(LocalDateTime executeTime) {
        this.executeTime = executeTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
