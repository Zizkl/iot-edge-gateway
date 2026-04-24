package com.jwwd.gateway.tcp.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TcpServerProperties.class)
public class TcpServerConfig {
}
