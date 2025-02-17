package com.dingtalk.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AppConfig {

    @Value("${dingtalk.app_key}")
    private String appKey;

    @Value("${dingtalk.app_secret}")
    private String appSecret;

    @Value("${dingtalk.corp_id}")
    private String corpId;

    @Value("${dingtalk.agent_id}")
    private String agentId;

}
