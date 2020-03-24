package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "project-url")
public class ProjectUrl {
    /**
     * 微信公众平台授权url
     */
    private String wechatMpAuthorize;
    /**
     * 微信开发平台授权url
     */
    private String wechatOpenAuthorize;
    /**
     * 系统url
     */
    private String sell;
}
