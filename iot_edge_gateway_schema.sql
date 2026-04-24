CREATE DATABASE IF NOT EXISTS `iot_edge_gateway`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE `iot_edge_gateway`;

CREATE TABLE IF NOT EXISTS `device_info` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `device_code` VARCHAR(64) NOT NULL COMMENT '设备编码',
  `device_name` VARCHAR(128) NOT NULL COMMENT '设备名称',
  `device_type` VARCHAR(32) NOT NULL COMMENT '设备类型',
  `protocol_type` VARCHAR(32) NOT NULL COMMENT '协议类型',
  `ip` VARCHAR(64) DEFAULT NULL COMMENT '最近连接IP',
  `port` INT DEFAULT NULL COMMENT '最近连接端口',
  `online_status` TINYINT NOT NULL DEFAULT 0 COMMENT '在线状态：0离线，1在线',
  `last_heartbeat_time` DATETIME DEFAULT NULL COMMENT '最近心跳时间',
  `last_online_time` DATETIME DEFAULT NULL COMMENT '最近上线时间',
  `last_offline_time` DATETIME DEFAULT NULL COMMENT '最近离线时间',
  `enabled` TINYINT NOT NULL DEFAULT 1 COMMENT '是否启用：0否，1是',
  `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_device_code` (`device_code`),
  KEY `idx_device_type` (`device_type`),
  KEY `idx_online_status` (`online_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='设备基础信息表';

CREATE TABLE IF NOT EXISTS `device_metric_data` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `device_code` VARCHAR(64) NOT NULL COMMENT '设备编码',
  `metric_code` VARCHAR(64) NOT NULL COMMENT '指标编码',
  `metric_name` VARCHAR(128) DEFAULT NULL COMMENT '指标名称',
  `metric_value` DECIMAL(18,4) NOT NULL COMMENT '指标值',
  `unit` VARCHAR(32) DEFAULT NULL COMMENT '单位',
  `collect_time` DATETIME NOT NULL COMMENT '采集时间',
  `receive_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '网关接收时间',
  `raw_payload` TEXT DEFAULT NULL COMMENT '原始报文',
  `data_hash` VARCHAR(64) NOT NULL COMMENT '去重哈希',
  `sync_status` TINYINT NOT NULL DEFAULT 0 COMMENT '同步状态：0未同步，1已同步，2同步失败',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_data_hash` (`data_hash`),
  KEY `idx_device_metric_time` (`device_code`, `metric_code`, `collect_time`),
  KEY `idx_sync_status` (`sync_status`),
  KEY `idx_collect_time` (`collect_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='监测数据表';

CREATE TABLE IF NOT EXISTS `alarm_rule` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_name` VARCHAR(128) NOT NULL COMMENT '规则名称',
  `device_type` VARCHAR(32) NOT NULL COMMENT '设备类型',
  `device_code` VARCHAR(64) DEFAULT NULL COMMENT '设备编码，为空表示适用于该类型全部设备',
  `metric_code` VARCHAR(64) NOT NULL COMMENT '指标编码',
  `operator` VARCHAR(16) NOT NULL COMMENT '比较符：GT、GE、LT、LE、EQ',
  `threshold_value` DECIMAL(18,4) NOT NULL COMMENT '告警阈值',
  `recover_operator` VARCHAR(16) DEFAULT NULL COMMENT '恢复比较符',
  `recover_value` DECIMAL(18,4) DEFAULT NULL COMMENT '恢复阈值',
  `alarm_level` VARCHAR(16) NOT NULL COMMENT '告警等级：LOW、MEDIUM、HIGH、CRITICAL',
  `debounce_seconds` INT NOT NULL DEFAULT 0 COMMENT '去抖时间，单位秒',
  `enabled` TINYINT NOT NULL DEFAULT 1 COMMENT '是否启用：0否，1是',
  `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_rule_match` (`enabled`, `device_type`, `device_code`, `metric_code`),
  KEY `idx_metric_code` (`metric_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='告警规则表';

CREATE TABLE IF NOT EXISTS `alarm_event` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `alarm_no` VARCHAR(64) NOT NULL COMMENT '告警编号',
  `device_code` VARCHAR(64) NOT NULL COMMENT '设备编码',
  `metric_code` VARCHAR(64) NOT NULL COMMENT '指标编码',
  `alarm_level` VARCHAR(16) NOT NULL COMMENT '告警等级',
  `alarm_status` VARCHAR(16) NOT NULL COMMENT '告警状态：ALARMING、RECOVERED',
  `trigger_value` DECIMAL(18,4) NOT NULL COMMENT '触发值',
  `threshold_value` DECIMAL(18,4) NOT NULL COMMENT '阈值',
  `trigger_time` DATETIME NOT NULL COMMENT '触发时间',
  `recover_value` DECIMAL(18,4) DEFAULT NULL COMMENT '恢复值',
  `recover_time` DATETIME DEFAULT NULL COMMENT '恢复时间',
  `last_metric_data_id` BIGINT DEFAULT NULL COMMENT '最近关联监测数据ID',
  `sync_status` TINYINT NOT NULL DEFAULT 0 COMMENT '同步状态：0未同步，1已同步，2同步失败',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_alarm_no` (`alarm_no`),
  KEY `idx_active_alarm` (`device_code`, `metric_code`, `alarm_status`),
  KEY `idx_trigger_time` (`trigger_time`),
  KEY `idx_sync_status` (`sync_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='告警事件表';

CREATE TABLE IF NOT EXISTS `alarm_action_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `alarm_event_id` BIGINT NOT NULL COMMENT '告警事件ID',
  `action_type` VARCHAR(32) NOT NULL COMMENT '动作类型：BROADCAST、LIGHT',
  `target_device_code` VARCHAR(64) NOT NULL COMMENT '目标设备编码',
  `command_payload` TEXT DEFAULT NULL COMMENT '指令内容',
  `execute_status` VARCHAR(16) NOT NULL COMMENT '执行状态：SUCCESS、FAILED',
  `execute_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '执行时间',
  `error_message` VARCHAR(512) DEFAULT NULL COMMENT '失败原因',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_alarm_event_id` (`alarm_event_id`),
  KEY `idx_target_device_code` (`target_device_code`),
  KEY `idx_execute_time` (`execute_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='联动动作日志表';

CREATE TABLE IF NOT EXISTS `sync_task` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_type` VARCHAR(32) NOT NULL COMMENT '任务类型：METRIC、ALARM、ACTION_LOG',
  `business_id` BIGINT NOT NULL COMMENT '业务数据ID',
  `idempotent_key` VARCHAR(128) NOT NULL COMMENT '幂等key',
  `payload` LONGTEXT NOT NULL COMMENT '同步内容JSON',
  `status` VARCHAR(16) NOT NULL DEFAULT 'PENDING' COMMENT '状态：PENDING、PROCESSING、SUCCESS、FAILED、DEAD',
  `retry_count` INT NOT NULL DEFAULT 0 COMMENT '已重试次数',
  `max_retry_count` INT NOT NULL DEFAULT 5 COMMENT '最大重试次数',
  `next_retry_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下次重试时间',
  `last_error` VARCHAR(1024) DEFAULT NULL COMMENT '最近错误',
  `last_sync_time` DATETIME DEFAULT NULL COMMENT '最近同步时间',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_idempotent_key` (`idempotent_key`),
  KEY `idx_status_next_retry` (`status`, `next_retry_time`),
  KEY `idx_business` (`task_type`, `business_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='同步任务表';

CREATE TABLE IF NOT EXISTS `operation_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_name` VARCHAR(64) NOT NULL COMMENT '模块名称',
  `operation_type` VARCHAR(64) NOT NULL COMMENT '操作类型',
  `operation_content` VARCHAR(512) DEFAULT NULL COMMENT '操作内容',
  `operator` VARCHAR(64) DEFAULT NULL COMMENT '操作人',
  `result_status` VARCHAR(16) NOT NULL COMMENT '结果状态：SUCCESS、FAILED',
  `error_message` VARCHAR(512) DEFAULT NULL COMMENT '失败原因',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_module_operation` (`module_name`, `operation_type`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志表';

