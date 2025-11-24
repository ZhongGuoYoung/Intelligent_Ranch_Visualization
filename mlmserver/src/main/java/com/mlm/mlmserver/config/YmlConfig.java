package com.mlm.mlmserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.yml")        // 配置文件路径，可省略
public class YmlConfig {

    @Value("${custom.path.word}")
    private String wordPath;

    public String getWordPath() {
        return wordPath;
    }


    /**
     * 示例
     * <pre>{@code
     * YmlConfig ymlConfig = new YmlConfig();
     * String port = ymlConfig.getProperty("server.port");
     * }</pre>
     */
    public String getProperty(String key) {
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(new ClassPathResource("application.yml"));
            properties = yamlFactory.getObject();
        } catch (Exception e) {
            System.out.println("application.yml 异常");
        }
        return properties != null ? properties.getProperty(key) : null;
    }
}
