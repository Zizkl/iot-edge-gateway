# IoT Edge Gateway

隧道施工现场物联网边缘网关系统，支持本地 TCP 设备接入、监测数据采集、告警规则判断、本地联动控制和远程异步补传。

## 技术栈

- Java 17
- Spring Boot 3
- Maven
- MyBatis Plus
- MySQL
- Netty
- WebSocket

## 本地启动

1. 创建数据库并执行 `iot_edge_gateway_schema.sql`。
2. 修改 `src/main/resources/application.yml` 中的 MySQL 账号密码。
3. 启动应用：

```bash
mvn spring-boot:run
```

默认端口：

- HTTP: `8080`
- TCP: `9000`

